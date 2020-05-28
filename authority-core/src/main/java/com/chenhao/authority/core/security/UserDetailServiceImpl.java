package com.chenhao.authority.core.security;

import com.chenhao.authority.common.utils.BeanCopyUtil;
import com.chenhao.authority.core.manager.SecurityManager;
import com.chenhao.authority.core.service.UserService;
import com.chenhao.authority.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  UserDetailService实现
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/28 22:33
 */
@Component
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityManager securityManager;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("用户名:{} 登录中...", userName);
        User user = userService.getByLoginName(userName);
        if(user == null) {
            log.warn("用户名:{} 登录失败：用户名不存在", userName);
            throw new UsernameNotFoundException("用户名不存在");
        }
        LoginUserInfo loginUserInfo = BeanCopyUtil.copyObject(user, LoginUserInfo.class);
        loginUserInfo.setGrantedAuthorities(securityManager.getGrantedAuthorities(user.getId()));
        return loginUserInfo;
    }
}
