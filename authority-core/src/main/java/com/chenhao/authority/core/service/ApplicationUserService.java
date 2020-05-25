package com.chenhao.authority.core.service;

import com.chenhao.authority.core.mapper.ApplicationUserMapper;
import com.chenhao.authority.domain.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 21:17
 */
@Service
public class ApplicationUserService extends BaseService<ApplicationUser, ApplicationUserMapper> {
    @Autowired
    private ApplicationUserMapper applicationUserMapper;


    @Override
    protected ApplicationUserMapper getMapper() {
        return applicationUserMapper;
    }
}
