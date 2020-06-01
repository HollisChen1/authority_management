package com.chenhao.authority.core.manager;

import com.chenhao.authority.common.response.ApiResult;
import com.chenhao.authority.common.utils.BeanCopyUtil;
import com.chenhao.authority.common.utils.PasswordUtil;
import com.chenhao.authority.core.service.RoleService;
import com.chenhao.authority.core.service.RoleUserService;
import com.chenhao.authority.core.service.UserService;
import com.chenhao.authority.domain.Role;
import com.chenhao.authority.domain.RoleUser;
import com.chenhao.authority.domain.User;
import com.chenhao.authority.dto.AddUserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/6/1 22:05
 */
@Component
public class RoleManager {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private RoleService roleService;


    @Transactional
    public ApiResult addUser(AddUserRequestDTO requestDTO) {
        //校验loginName
        if (null != userService.getByLoginName(requestDTO.getLoginName())) {
            return ApiResult.parameterError(String.format("登录名[%s]已被使用，换一个试试", requestDTO.getLoginName()));
        }
        User user = BeanCopyUtil.copyObject(requestDTO, User.class);
        //密码
        user.setPassword(PasswordUtil.encryptPassword(user.getLoginName(),requestDTO.getPassword()));
        userService.insert(user);
        RoleUser roleUser = new RoleUser();
        roleUser.setUserId(user.getId());
        //写roleUser
        List<Integer> roleIdList = roleService.getEnabledByIds(requestDTO.getRoleIdList()).stream().map(Role::getId).collect(Collectors.toList());
        roleIdList.stream().forEach(roleId -> {
            roleUser.setRoleId(roleId);
            roleUserService.insert(roleUser);
        });
        return ApiResult.success();
    }
}
