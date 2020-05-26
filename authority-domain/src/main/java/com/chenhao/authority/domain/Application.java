package com.chenhao.authority.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * application
 * @author 
 */
@Data
public class Application implements Serializable {
    private Integer id;

    /**
     * 应用编号(唯一)
     */
    private String code;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用描述
     */
    private String description;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date updateTime;

    /**
     * 创建人ID
     */
    private Integer creator;

    /**
     * 最后修改人ID
     */
    private Integer updator;

    private static final long serialVersionUID = 1L;

}