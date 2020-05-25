package com.chenhao.authority.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * application_user
 * @author 
 */
public class ApplicationUser implements Serializable {
    private Integer id;

    /**
     * 应用ID
     */
    private Integer applicationId;

    /**
     * 用户ID
     */
    private Integer userId;

    private Integer creator;

    private Date createTime;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}