package com.huangpuguang.common.core.constant;

/**
 * 缓存的key 常量
 *
 * @author procon
 */
public class CacheConstants
{
    /**
     * 缓存有效期，默认720（分钟）
     */
    public final static long EXPIRATION = 720;

    /**
     * 缓存刷新时间，默认120（分钟）
     */
    public final static long REFRESH_TIME = 120;
    /**
     * 权限缓存前缀
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";
}
