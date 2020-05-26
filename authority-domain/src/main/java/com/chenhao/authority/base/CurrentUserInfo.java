package com.chenhao.authority.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUserInfo {

    private Integer userId;

    private String loginName;

    private String userName;
}
