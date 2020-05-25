package com.chenhao.authority.domain;

import java.io.Serializable;

/**
 * application_config
 * @author 
 */
public class ApplicationConfig implements Serializable {
    private Integer id;

    /**
     * 应用ID
     */
    private Integer applicationId;

    /**
     * 是否鉴权(0:否 1:是)
     */
    private Byte authStatus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public Byte getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(Byte authStatus) {
        this.authStatus = authStatus;
    }
}