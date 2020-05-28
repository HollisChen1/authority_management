package com.chenhao.authority.common.enums;

import lombok.Getter;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/29 00:15
 */
@Getter
public enum ResourceType {
    MENU((byte) 1, "菜单"),
    FUNCTION((byte) 2, "功能");

    private Byte type;

    private String description;

    ResourceType(Byte type, String description) {
        this.type = type;
        this.description = description;
    }

}
