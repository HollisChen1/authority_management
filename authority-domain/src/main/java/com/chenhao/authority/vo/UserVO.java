package com.chenhao.authority.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 22:06
 */
@Data
public class UserVO implements Serializable {
    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别(1:男 2:女 3:未知)
     */
    private Byte sex;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 证件类型（1:身份证）
     */
    private Byte certificateType;

    /**
     * 证件号码
     */
    private String certificateNo;

    /**
     * 状态(0：禁用 1：启用)
     */
    private Byte status;

    /**
     * 创建人
     */
    private Integer creator;

    private Integer updator;

    private Date createTime;

    private Date updateTime;
}
