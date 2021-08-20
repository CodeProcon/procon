package com.huangpuguang.common.core.constant;

/**
 * 通用常量信息
 *
 * @author procon
 */
public class Constants
{

    /**文件分割符  */
    public static final String FILE_SEGMENTATION = ",";
    /**
     * 英文符号
     */
    public static final String SYMBOL_COMMA = ",";
    public static final String SYMBOL_POINT = ".";
    public static final String SYMBOL_QUESTION = "?";
    public static final String SYMBOL_COLON = ":";
    public static final String SYMBOL_STAR = "*";
    public static final String SYMBOL_WELL = "#";
    public static final String SYMBOL_HYPHEN = "-";
    public static final String SYMBOL_UNDERLINE = "_";
    public static final String SYMBOL_LEFT_BRACKET = "{";
    public static final String SYMBOL_RIGHT_BRACKET = "}";
    public static final String SYMBOL_RIGHT_EQUAL = "=";

    /**
     * 系统全局是否标识
     */
    public static final int YES = 1;
    public static final int NO = 0;

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK 字符集
     */
    public static final String GBK = "GBK";

    /**
     * RMI 远程方法调用
     */
    public static final String LOOKUP_RMI = "rmi://";

    /**
     * LDAP 远程方法调用
     */
    public static final String LOOKUP_LDAP = "ldap://";

    /**
     * http请求
     */
    public static final String HTTP = "http://";

    /**
     * https请求
     */
    public static final String HTTPS = "https://";

    /**
     * 成功标记
     */
    public static final Integer SUCCESS = 200;

    /**
     * 失败标记
     */
    public static final Integer FAILED = 500;

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 注册
     */
    public static final String REGISTER = "Register";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 验证码 redis key
     */
    public static final String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 验证码有效期（分钟）
     */
    public static final long CAPTCHA_EXPIRATION = 2;

    /**
     * 令牌有效期（分钟）
     */
    public static final  long TOKEN_EXPIRE = 720;

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 cache key
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

    /**
     * 资源映射路径 前缀
     */
    public static final String RESOURCE_PREFIX = "/profile";

    /**
     * 数字 0~11
     */
    public static final  int NUM_ZERO = 0;
    public static final  int NUM_ONE = 1;
    public static final  int NUM_TWO = 2;
    public static final  int NUM_THREE = 3;
    public static final  int NUM_FOUR = 4;
    public static final int NUM_FIVE = 5;
    public static final int NUM_SIX = 6;
    public static final int NUM_SEVEN = 7;
    public static final int NUM_EIGHT = 8;
    public static final int NUM_NINE = 9;
    public static final int NUM_TEN = 10;
    public static final int NUM_ELEVEN = 11;
    public static final int NUM_TWENTY = 20;
    public static final int NUM_1024 = 1024;
    public static final int NUM_5000 = 5000;

}
