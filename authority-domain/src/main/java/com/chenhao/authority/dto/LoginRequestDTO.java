package com.chenhao.authority.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 21:51
 */
@Data
public class LoginRequestDTO {

    @NotBlank(message = "登录用户名不能为空")
    private String loginName;

    @NotBlank(message = "登录密码不能为空")
    private String password;

}
