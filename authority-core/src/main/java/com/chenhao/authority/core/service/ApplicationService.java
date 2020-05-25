package com.chenhao.authority.core.service;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenhao.authority.core.mapper.ApplicationMapper;
import com.chenhao.authority.domain.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 21:14
 */
@Service
public class ApplicationService extends BaseService<Application, ApplicationMapper> {
    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    protected ApplicationMapper getMapper() {
        return applicationMapper;
    }

    public Application getByAppCode(String applicationCode){
        Assert.notBlank(applicationCode);
        QueryWrapper<Application> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code", applicationCode);
        return applicationMapper.selectOne(queryWrapper);
    }
}
