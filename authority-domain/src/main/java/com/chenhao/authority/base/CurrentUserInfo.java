package com.chenhao.authority.base;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @description: 当前登录人员信息
 * @author: chenhao
 * @date: 2020/5/26 00:00
 */
@Data
public class CurrentUserInfo {

    private Integer userId;

    private String loginName;

    private String userName;
}
