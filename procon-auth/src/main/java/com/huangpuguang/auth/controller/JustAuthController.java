package com.huangpuguang.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huangpuguang.auth.cache.AuthStateRedisCache;
import com.huangpuguang.common.core.constant.*;
import com.huangpuguang.common.core.domain.ResultModel;
import com.huangpuguang.common.core.exception.ServiceException;
import com.huangpuguang.common.core.utils.JsonUtils;
import com.huangpuguang.common.core.utils.JwtUtils;
import com.huangpuguang.common.core.utils.ProconStringUtils;
import com.huangpuguang.common.core.utils.bean.BeanUtils;
import com.huangpuguang.common.core.utils.ip.IpUtils;
import com.huangpuguang.common.redis.service.RedisService;
import com.huangpuguang.common.security.service.TokenService;
import com.huangpuguang.common.security.utils.SecurityUtils;
import com.huangpuguang.system.api.RemoteUserService;
import com.huangpuguang.system.api.domain.SysUser;
import com.huangpuguang.system.api.model.LoginUser;
import com.xkcoding.http.config.HttpConfig;
import io.jsonwebtoken.Claims;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.enums.scope.AuthGiteeScope;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthToken;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.*;

/**
 * justAuth第三方登录
 *
 * @author procon
 * @version 1.0
 * @since 2020/12/7
 */
@RestController
@RequestMapping("justAuth")
public class JustAuthController {
    private static final Logger log = LoggerFactory.getLogger(JustAuthController.class);
    /**  gitee*/
    @Value("${justAuth.clientId.gitee}")
    private String giteeClientId;
    @Value("${justAuth.clientSecret.gitee}")
    private String giteeClientSecret;
    @Value("${justAuth.callbackUrl.gitee}")
    private String giteeCallbackUrl;
    /**  github*/
    @Value("${justAuth.clientId.github}")
    private String githubClientId;
    @Value("${justAuth.clientSecret.github}")
    private String githubClientSecret;
    @Value("${justAuth.callbackUrl.github}")
    private String githubCallbackUrl;

    @Value("${justAuth.webSiteUrl}")
    private String webSiteUrl;
    @Value("${justAuth.defaultPassword}")
    private String defaultPassword;
    @Autowired
    private RedisService redisService;

    @Autowired
    private AuthStateRedisCache stateCache;
    @Autowired
    private RemoteUserService remoteUserService;
    @Autowired
    private TokenService tokenService;


    /** 获取认证 */
    @RequestMapping("/render/{source}")
    public ResultModel renderAuth(@PathVariable("source") String source, HttpServletResponse response){
        log.info("进入render:{}",source);
        AuthRequest authRequest = getAuthRequest(source);
        String token = AuthStateUtils.createState();
        String authorizeUrl = authRequest.authorize(token);
        log.info(authorizeUrl);
        Map<String, String> map = new HashMap<>(16);
        map.put(AuthConstants.URL,authorizeUrl);
        return ResultModel.ok(map,"获取成功");
    }


    /**
     * oauth平台中配置的授权回调地址，以本项目为例，在创建gitee授权应用时的回调地址应为：http://127.0.0.1:9200/justAuth/callback/gitee
     */
    @RequestMapping("/callback/{source}")
    public void login(@PathVariable("source") String source, AuthCallback callback, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        log.info("进入callback：" + source + " callback params：" + JSONObject.toJSONString(callback));
        AuthRequest authRequest = getAuthRequest(source);
        AuthResponse<AuthUser> response = authRequest.login(callback);
        log.info(JSON.toJSONString(response));
        if (response.getCode() == Constants.NUM_5000) {
            // 跳转到500错误页面
            httpServletResponse.sendRedirect(webSiteUrl + 500);
            return;
        }
        String result = JSON.toJSONString(response);
        Map<String, Object> map = JsonUtils.jsonToMap(result);
        Map<String, Object> data = JsonUtils.jsonToMap(JsonUtils.objectToJson(map.get(AuthConstants.DATA)));
        Map<String, Object> token;
        String accessToken = "";
        if (data == null || data.get(BlogConstants.TOKEN) == null) {
            // 跳转到500错误页面
            httpServletResponse.sendRedirect(webSiteUrl + 500);
            return;
        } else {
            token = JsonUtils.jsonToMap(JsonUtils.objectToJson(data.get(BlogConstants.TOKEN)));
            accessToken = token.get(BlogConstants.ACCESS_TOKEN).toString();
        }

        String jwtToken = setUserInfo(data, accessToken, httpServletRequest);
        httpServletResponse.sendRedirect(webSiteUrl + "?token=" + jwtToken);
    }


    /**  撤销授权*/
    @RequestMapping("/revoke/{source}/{token}")
    public Object revokeAuth(@PathVariable("source") String source, @PathVariable("token") String token) throws IOException {
        AuthRequest authRequest = getAuthRequest(source);
        return authRequest.revoke(AuthToken.builder().accessToken(token).build());
    }

    /**  刷新授权*/
    @RequestMapping("/refresh/{source}")
    public Object refreshAuth(@PathVariable("source") String source, String token) {
        AuthRequest authRequest = getAuthRequest(source);
        return authRequest.refresh(AuthToken.builder().refreshToken(token).build());
    }

    /**
     * 鉴权
     * @param source 登陆来源
     * @return me.zhyd.oauth.request.AuthRequest
     */
    private AuthRequest getAuthRequest(String source){
        AuthRequest authRequest = null;
        switch (source) {
            case AuthConstants.GITHUB:
                authRequest = new AuthGithubRequest(AuthConfig.builder()
                        .clientId(githubClientId)
                        .clientSecret(githubClientSecret)
                        .redirectUri(githubCallbackUrl)
                        // 针对国外平台配置代理
                        .httpConfig(HttpConfig.builder()
                                .timeout(15000)
                                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 10080)))
                                .build()).build(),stateCache);
                break;
            case AuthConstants.GITEE:
                authRequest = new AuthGiteeRequest(AuthConfig.builder()
                        .clientId(giteeClientId)
                        .clientSecret(giteeClientSecret)
                        .redirectUri(giteeCallbackUrl)
                        .scopes(Arrays.asList(AuthGiteeScope.EMAILS.getScope()
                                ,AuthGiteeScope.USER_INFO.getScope()))
                        .build(),stateCache);

                break;
            default:
                break;
        }
        if (null == authRequest) {
            throw new ServiceException("请求授权失败");
        }
        return authRequest;
    }

    /**  设置用户信息*/
    private String setUserInfo(Map<String, Object> data,String accessToken,HttpServletRequest httpServletRequest){
        //判断用户是否存在
        boolean exist = false;
        SysUser user;
        String source = data.get(AuthConstants.SOURCE) == null?null:data.get(AuthConstants.SOURCE).toString();
        String uuid = data.get(AuthConstants.UUID)==null?null:data.get(AuthConstants.UUID).toString();
        if (uuid != null && source != null) {
            user = remoteUserService.getUserInfoBySource(source+uuid, source);
            if (user != null) {
                exist = true;
            } else {
                user = new SysUser();
            }
        } else {
            return ProconStringUtils.EMPTY;
        }

        // 判断邮箱是否存在
        if (data.get(AuthConstants.EMAIL) != null) {
            String email = data.get(AuthConstants.EMAIL).toString();
            user.setEmail(email);
        }

        // 判断用户性别
        if (data.get(AuthConstants.GENDER) != null && !exist) {
            String gender = data.get(AuthConstants.GENDER).toString();
            if (AuthConstants.MALE.equals(gender)) {
                user.setSex(UserConstants.MALE);
            } else if (UserConstants.FEMALE.equals(gender)) {
                user.setSex(UserConstants.FEMALE);
            } else {
                user.setSex(UserConstants.UNKNOWN);
            }
        }
        // 获取用户头像
        if(data.get(UserConstants.AVATAR) != null){
            user.setAvatar(data.get(UserConstants.AVATAR).toString());
        }
        //获取用户昵称
        if (data.get(UserConstants.NICKNAME) != null) {
            user.setNickName(data.get(UserConstants.NICKNAME).toString());
        }

        //设置登录ip 登录时间
        String ipAddr = IpUtils.getIpAddr(httpServletRequest);
        user.setLoginIp(ipAddr);
        user.setLoginDate(new Date());
        if (!exist) {
            user.setSource(source);
            user.setUserName(source+uuid);
            // 如果昵称为空，那么直接设置用户名
            if (ProconStringUtils.isEmpty(user.getNickName())) {
                user.setNickName(source+uuid);
            }
            // 默认密码
            user.setPassword(SecurityUtils.encryptPassword(defaultPassword));
        }
        SysUser sysUser = remoteUserService.saveAndUpdate(user);
        if(sysUser.getUserId() != null){
            LoginUser loginUser = new LoginUser();
            loginUser.setSysUser(sysUser);
            Map<String, Object> tokenMap = tokenService.createToken(loginUser);
            return tokenMap.get("access_token").toString();
        }
        return ProconStringUtils.EMPTY;
    }

    /** 获取用户信息*/
    @GetMapping("/verify/{accessToken}")
    public ResultModel<Map<String, Object>> verifyUser(@PathVariable("accessToken") String token) {

        Claims claims = JwtUtils.parseToken(token);
        if (claims == null)
        {
            return ResultModel.fail("token已过期或验证不正确！");
        }
        String userKey = JwtUtils.getUserKey(claims);
        String tokenKey = CacheConstants.LOGIN_TOKEN_KEY + userKey;

        boolean isLogin = redisService.hasKey(tokenKey);
        if (!isLogin)
        {
            return ResultModel.fail("登录状态已过期");
        }
        String userid = JwtUtils.getUserId(claims);
        String username = JwtUtils.getUserName(claims);
        if (ProconStringUtils.isEmpty(userid))
        {
            return ResultModel.fail("令牌验证失败");
        }

        List<SysUser> allUser = remoteUserService.getAllUser();
        SysUser sysUser = new SysUser();
        for(SysUser user : allUser){
            if(Long.parseLong(userid) == user.getUserId()){
                sysUser = user;
            }
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setToken(token);
        loginUser.setSysUser(sysUser);
        loginUser.setUsername(username);
        loginUser.setUserid(Long.parseLong(userid));
        Map<String, Object> map = BeanUtils.beanToMap(loginUser);
        return ResultModel.ok(map, "获取用户成功！");
    }

}
