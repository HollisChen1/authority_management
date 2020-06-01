package com.chenhao.authority.core.controller;

import com.chenhao.authority.common.response.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/6/1 21:43
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @GetMapping("/page")
    public ApiResult getRoleListPage(){
        return ApiResult.success();
    }
}
