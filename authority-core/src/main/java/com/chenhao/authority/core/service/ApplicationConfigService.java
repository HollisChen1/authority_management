package com.chenhao.authority.core.service;

import com.chenhao.authority.core.mapper.ApplicationConfigMapper;
import com.chenhao.authority.core.mapper.ApplicationMapper;
import com.chenhao.authority.domain.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 21:16
 */
@Service
public class ApplicationConfigService extends BaseService<ApplicationConfig, ApplicationConfigMapper> {
    @Autowired
    private ApplicationConfigMapper applicationConfigMapper;

    @Override
    protected ApplicationConfigMapper getMapper() {
        return applicationConfigMapper;
    }
}
