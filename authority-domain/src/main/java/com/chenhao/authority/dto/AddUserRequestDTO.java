package com.chenhao.authority.dto;

import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/6/1 22:01
 */
@Data
public class AddUserRequestDTO {
    private String loginName;

    private String userName;

    private String password;

    private List<Integer> roleIdList;

    private Byte sex;

    private String avatar;

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
    private Byte status = 1;
}
