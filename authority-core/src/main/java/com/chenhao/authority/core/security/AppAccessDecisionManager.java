package com.chenhao.authority.core.security;

import cn.hutool.core.collection.CollectionUtil;
import com.chenhao.authority.core.service.ApplicationResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/28 23:37
 */
@Component
@Slf4j
public class AppAccessDecisionManager implements AccessDecisionManager {
    @Value("${webMvc.excludePaths:/login,/logout}")
    private String excludePaths;

    @Autowired
    private ApplicationResourceService applicationResourceService;

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        FilterInvocation filterInvocation = (FilterInvocation) object;
        String uri = filterInvocation.getRequestUrl();
        if(Stream.of(excludePaths.split(",")).anyMatch(uri::equals)){
            //白名单放行
            return;
        }
        if(authentication instanceof AnonymousAuthenticationToken){
            throw new BadCredentialsException("请先登录");
        }
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if(CollectionUtil.isNotEmpty(authorities)){
            for (GrantedAuthority authority : authorities) {
                AppGrantedAuthority grantedAuthority = (AppGrantedAuthority) authority;
                if(applicationResourceService.isGrantedUrl(grantedAuthority.getRoleId(), uri)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("您没有权限访问：" + uri);
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
