package com.chenhao.authority.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * application_resouce
 * @author 
 */
@Data
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
    private Integer sort;

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

}