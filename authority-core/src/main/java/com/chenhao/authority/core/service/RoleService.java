package com.chenhao.authority.core.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenhao.authority.common.enums.StatusEnum;
import com.chenhao.authority.core.mapper.RoleMapper;
import com.chenhao.authority.domain.Role;
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

    public List<Role> getEnabledByIds(List<Integer> ids){
        if(CollectionUtil.isEmpty(ids)){
            return Collections.emptyList();
        }
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        queryWrapper.eq("`status`", StatusEnum.ENABLE.getCode());
        return roleMapper.selectList(queryWrapper);
    }
}
