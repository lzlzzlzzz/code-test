package com.code.myweb.redis;

/**
 * redis缓存key
 */
public class CacheConstants {

    /**
     * 重复提交
     */
    public static final String REPEAT_KEY = "repeat_key:";

    /**
     * 登录用户 
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 参数管理 
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 字典管理 
     */
    public static final String SYS_DICT_KEY = "sys_dict:";

}
