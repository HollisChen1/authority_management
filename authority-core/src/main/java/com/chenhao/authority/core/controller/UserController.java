package com.chenhao.authority.core.controller;

import com.chenhao.authority.common.response.ApiResult;
import com.chenhao.authority.core.service.UserService;
import com.chenhao.authority.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 20:19
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ApiResult<List<User>> listAll(){
        return ApiResult.success(userService.findByCondition(null));
    }
}
