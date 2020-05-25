package com.chenhao.authority.domain;

import java.io.Serializable;

/**
 * role_resource
 * @author 
 */
public class RoleResource implements Serializable {
    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 资源ID
     */
    private Integer resourceId;

    /**
     * 资源所属应用ID
     */
    private Integer resourceAppId;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getResourceAppId() {
        return resourceAppId;
    }

    public void setResourceAppId(Integer resourceAppId) {
        this.resourceAppId = resourceAppId;
    }
}