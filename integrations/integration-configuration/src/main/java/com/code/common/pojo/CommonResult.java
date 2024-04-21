package com.code.common.pojo;

import com.code.common.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * 返回结果
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> implements Serializable {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public CommonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> CommonResult<T> success() {
        CommonResult<T> result = new CommonResult<>();
        result.code = ErrorCodeEnum.SUCCESS.getCode();
        result.msg = "操作成功";
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMsg());
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        Assert.isTrue(!ErrorCodeEnum.SUCCESS.getCode().equals(code), "code异常");
        return new CommonResult<>(code, message);
    }

    public static <T> CommonResult<T> error(ErrorCodeEnum errorCode) {
        return error(errorCode.getCode(), errorCode.getMsg());
    }

    public static <T> CommonResult<T> error(String msg) {
        return error(null, msg);
    }

}
