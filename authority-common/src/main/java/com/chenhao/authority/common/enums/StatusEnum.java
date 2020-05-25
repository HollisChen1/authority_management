package com.chenhao.authority.common.enums;

import lombok.Getter;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 22:03
 */
@Getter
public enum  StatusEnum {
    DISABLE((byte) 0, "禁用"),
    ENABLE((byte) 1, "启用")
    ;
    private Byte code;

    private String description;

    StatusEnum(Byte code, String description) {
        this.code = code;
        this.description = description;
    }
}
