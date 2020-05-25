package com.chenhao.authority.core.service;

import com.chenhao.authority.core.mapper.RoleMapper;
import com.chenhao.authority.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 21:35
 */
@Service
public class RoleService extends BaseService<Role, RoleMapper> {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    protected RoleMapper getMapper() {
        return roleMapper;
    }
}
