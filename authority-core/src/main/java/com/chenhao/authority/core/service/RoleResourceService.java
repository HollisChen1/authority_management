package com.chenhao.authority.core.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenhao.authority.core.mapper.RoleResourceMapper;
import com.chenhao.authority.domain.RoleResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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

    /**
     * 通过角色ID查询
     * @param roleIds
     * @return
     */
    public List<RoleResource> findByRoleIds(List<Integer> roleIds){
        if(CollectionUtil.isEmpty(roleIds)){
            return Collections.emptyList();
        }
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("role_id", roleIds);
        return roleResourceMapper.selectList(queryWrapper);
    }
}
