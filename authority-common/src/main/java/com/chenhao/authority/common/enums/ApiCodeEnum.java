package com.chenhao.authority.common.enums;

import lombok.Getter;

/**
 * <p>
 *  API 返回码枚举
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/24 23:10
 */
@Getter
public enum ApiCodeEnum {
    SUCCESS(200, "成功"),
    FORBIDDEN(403, "无访问权限"),
    PARAMETER_ERROR(400, "请求参数错误"),
    SYSTEM_ERROR(500, "系统错误"),

    //用户相关
    ACCOUNT_DISABLED(1001,"账户被禁用")
    ;

    private Integer code;

    private String description;

    ApiCodeEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
