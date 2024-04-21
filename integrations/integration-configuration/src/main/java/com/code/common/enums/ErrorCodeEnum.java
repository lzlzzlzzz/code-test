package com.code.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 返回码
 */
@AllArgsConstructor
@Getter
public enum ErrorCodeEnum {

    SUCCESS(200, "成功"),

    // 通用错误
    BAD_REQUEST(400, "请求参数不正确"),
    UNAUTHORIZED(401, "未登录"),
    PERMISSION_EXCEPTION(403, "权限异常"),
    NOT_FOUND(404, "请求未找到"),
    METHOD_NOT_ALLOWED(405, "请求方法错误"),
    MORE_REQUEST(429, "请求过于频繁，请稍后重试"),
    SERVER_ERROR(500, "系统异常"),

    // 自定义错误
    REPEATED_REQUEST(900, "请求重复"),
    UNKNOWN(999, "未知错误")
    ;

    /**
     * 编码
     */
    private final Integer code;

    /**
     * 提示
     */
    private final String msg;

}
