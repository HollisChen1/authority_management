package com.chenhao.authority.vo;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/6/1 21:47
 */
@Data
public class RoleVO {
    private Integer roleId;

    private String roleName;

    private String roleDesc;

    private Byte status;


}
