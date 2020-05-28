package com.chenhao.authority.common.enums;

import lombok.Getter;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/28 22:41
 */
@Getter
public enum  UserStatusEnum {
    DISABLE((byte) 0, "禁用"),
    ENABLE((byte) 1, "启用"),
    LOCKED((byte) 2, "锁定");


    private Byte code;

    private String description;

    UserStatusEnum(Byte code, String description) {
        this.code = code;
        this.description = description;
    }
}
