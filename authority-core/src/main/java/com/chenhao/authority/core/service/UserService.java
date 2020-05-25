package com.chenhao.authority.core.service;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenhao.authority.core.mapper.UserMapper;
import com.chenhao.authority.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 20:04
 */
@Service
public class UserService extends BaseService<User, UserMapper> {

    @Autowired
    private UserMapper userMapper;

    @Override
    protected UserMapper getMapper() {
        return userMapper;
    }

    /**
     * 根据登录名查询信息
     * @param loginName 登录名
     * @return
     */
    public User getByLoginName(String loginName){
        Assert.notBlank(loginName);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name",loginName);
        return userMapper.selectOne(wrapper);
    }
}
