package com.chenhao.authority.core.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenhao.authority.core.mapper.RoleUserMapper;
import com.chenhao.authority.domain.RoleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 21:36
 */
@Service
public class RoleUserService extends BaseService<RoleUser, RoleUserMapper> {
    @Autowired
    private RoleUserMapper roleUserMapper;

    @Override
    protected RoleUserMapper getMapper() {
        return roleUserMapper;
    }


    public List<RoleUser> findByUserId(Integer userId){
        Assert.notNull(userId,"用户ID为空");
        QueryWrapper<RoleUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return findByCondition(queryWrapper);


    }
}
