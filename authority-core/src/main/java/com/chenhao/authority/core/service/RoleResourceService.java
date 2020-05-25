package com.chenhao.authority.core.service;

import com.chenhao.authority.core.mapper.RoleResourceMapper;
import com.chenhao.authority.domain.RoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 21:37
 */
@Service
public class RoleResourceService extends BaseService<RoleResource, RoleResourceMapper> {
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    protected RoleResourceMapper getMapper() {
        return roleResourceMapper;
    }
}
