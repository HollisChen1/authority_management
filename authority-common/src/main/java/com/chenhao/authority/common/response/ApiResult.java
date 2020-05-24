package com.chenhao.authority.common.response;

import com.chenhao.authority.common.enums.ApiCodeEnum;
import com.sun.tools.javac.util.Assert;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * Api返回结果
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/24 23:05
 */
@Getter
@Setter
@ToString
@Accessors(chain = true)
public class ApiResult<T> {
    /**
     * 返回码
     */
    private Integer code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public static ApiResult success() {
        return fromEnum(ApiCodeEnum.SUCCESS);
    }

    public static <T> ApiResult<T> success(T data) {
        return fromEnum(ApiCodeEnum.SUCCESS, data);
    }

    public static ApiResult sysError() {
        return fromEnum(ApiCodeEnum.SYSTEM_ERROR);
    }

    public static ApiResult parameterError(String msg){
        return fromEnum(ApiCodeEnum.PARAMETER_ERROR).setMsg(msg);
    }

    public static ApiResult fromEnum(ApiCodeEnum apiCodeEnum) {
        Assert.checkNonNull(apiCodeEnum);
        return new ApiResult().setCode(apiCodeEnum.getCode()).setMsg(apiCodeEnum.getDescription());
    }

    public static <T> ApiResult<T> fromEnum(ApiCodeEnum apiCodeEnum, T data) {
        return fromEnum(apiCodeEnum).setData(data);
    }
}
