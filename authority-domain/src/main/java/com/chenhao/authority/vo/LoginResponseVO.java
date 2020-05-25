package com.chenhao.authority.vo;

import lombok.Data;

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
public class LoginResponseVO extends UserVO{
    /**
     * jwt token
     */
    private String token;

    private Date lastSuccessfulLoginTime;

    private String lastSuccessfulLoginIP;

    /**
     * 登录失败次数
     */
    private Integer loginAttemptTimes;
}
