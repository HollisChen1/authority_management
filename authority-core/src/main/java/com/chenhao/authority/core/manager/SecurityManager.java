package com.chenhao.authority.core.manager;

import cn.hutool.core.collection.CollectionUtil;
import com.chenhao.authority.base.CurrentUserInfo;
import com.chenhao.authority.common.context.SessionContext;
import com.chenhao.authority.common.enums.StatusEnum;
import com.chenhao.authority.common.response.ApiResult;
import com.chenhao.authority.common.utils.BeanCopyUtil;
import com.chenhao.authority.common.utils.JwtUtil;
import com.chenhao.authority.common.utils.PasswordUtil;
import com.chenhao.authority.core.security.AppGrantedAuthority;
import com.chenhao.authority.core.service.*;
import com.chenhao.authority.domain.*;
import com.chenhao.authority.dto.LoginRequestDTO;
import com.chenhao.authority.vo.LoginResponseVO;
import com.chenhao.authority.vo.ResourceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.chenhao.authority.common.enums.ApiCodeEnum.ACCOUNT_DISABLED;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 21:38
 */
@Component
@Slf4j
public class SecurityManager {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private ApplicationResourceService applicationResourceService;


    /**
     * 登录方法
     *
     * @param requestDTO
     * @return
     */
    public ApiResult<LoginResponseVO> doLogin(LoginRequestDTO requestDTO) {
        log.info("用户登录中... 参数: {}", requestDTO);
        String loginName = requestDTO.getLoginName();
        //查询用户
        User user = userService.getByLoginName(loginName);
        if (Objects.isNull(user)) {
            log.warn("登录名[{}]不存在", loginName);
            return ApiResult.parameterError("用户名或者密码错误");
        }
        if (!Objects.equals(user.getStatus(), StatusEnum.ENABLE.getCode())) {
            return ApiResult.fromEnum(ACCOUNT_DISABLED);
        }
        //校验密码
        if (!PasswordUtil.verifyPassword(loginName, requestDTO.getPassword(), user.getPassword())) {
            log.warn("登录名[{}]密码错误", loginName);
            return ApiResult.parameterError("用户名或者密码错误");
        }
        //颁发token
        LoginResponseVO responseVO = BeanCopyUtil.copyObject(user, LoginResponseVO.class);
        responseVO.setToken(JwtUtil.createJwtToken(loginName, user.getPasswordLastModify().getTime()));
        log.info("登录名[{}]用户登录成功", loginName);
        return ApiResult.success(responseVO);
    }

    /**
     * 获取可以访问的资源
     *
     * @param applicationCode 应用编号
     * @return
     */
    public ApiResult<List<ResourceVO>> getAccessResources(String applicationCode) {
        //校验应用编号
        Application application = applicationService.getByAppCode(applicationCode);
        if (Objects.isNull(application)) {
            log.warn("根据应用编号[{}]未查询到应用信息", applicationCode);
            return ApiResult.parameterError(String.format("appCode %s 不存在", applicationCode));
        }
        //获取登录用户信息
        CurrentUserInfo currentUser = SessionContext.getCurrentUser();
        //查询用户角色列表
        List<RoleUser> roleUserList = roleUserService.findByUserId(currentUser.getUserId());
        List<Role> roleList = roleService.getByIds(roleUserList.stream().map(RoleUser::getRoleId).collect(Collectors.toList()));
        List<Integer> enableRoleIdList = roleList.stream()
                .filter(role -> StatusEnum.ENABLE.getCode().equals(role.getStatus()))
                .map(Role::getId)
                .collect(Collectors.toList());
        //查询角色对应应用的资源列表
        List<RoleResource> roleResourceList = roleResourceService.findByRoleIds(enableRoleIdList);
        List<ApplicationResource> resourceList = applicationResourceService.findByApplicationAndIds(application.getId(), roleResourceList.stream().map(RoleResource::getResourceId).collect(Collectors.toList()));
        //构建菜单树
        return ApiResult.success(applicationResourceService.buildResourceTree(resourceList, 0));
    }

    /**
     * 获取用户的所有角色
     *
     * @param userId
     * @return
     */
    public List<GrantedAuthority> getGrantedAuthorities(Integer userId) {
        List<Role> roles = roleService.getEnabledByIds(roleUserService.findByUserId(userId).stream().map(RoleUser::getRoleId).collect(Collectors.toList()));
        if (CollectionUtil.isEmpty(roles)) {
            return Collections.emptyList();
        }
        return roles.stream()
                .map(role -> new AppGrantedAuthority()
                                .setRoleId(role.getId())
                                .setRoleName(role.getRoleName())
                ).collect(Collectors.toList());
    }


}
