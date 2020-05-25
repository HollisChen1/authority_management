package com.chenhao.authority.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * application_resouce
 * @author 
 */
public class ApplicationResource implements Serializable {
    private Integer id;

    /**
     * 应用ID
     */
    private Integer applicationId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源编号
     */
    private String code;

    /**
     * 资源类型(1:菜单 2:功能)
     */
    private Byte type;

    /**
     * 资源url（如果是菜单则为路由，如果为功能则为api）
     */
    private String url;

    /**
     * 父级资源ID
     */
    private Integer parentId;

    /**
     * 资源排序
     */
    private Integer order;

    /**
     * 绑定组件ID
     */
    private String bindComponent;

    /**
     * 菜单icon
     */
    private String icon;

    private Integer creator;

    private Integer updator;

    private Date createTime;

    private Date updateTime;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getBindComponent() {
        return bindComponent;
    }

    public void setBindComponent(String bindComponent) {
        this.bindComponent = bindComponent;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getCreator() {
        return creator;
    }

    public void setCreator(Integer creator) {
        this.creator = creator;
    }

    public Integer getUpdator() {
        return updator;
    }

    public void setUpdator(Integer updator) {
        this.updator = updator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}