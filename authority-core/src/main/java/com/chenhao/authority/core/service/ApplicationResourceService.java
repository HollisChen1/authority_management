package com.chenhao.authority.core.service;

import com.chenhao.authority.core.mapper.ApplicationResourceMapper;
import com.chenhao.authority.domain.ApplicationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 21:18
 */
@Service
public class ApplicationResourceService extends BaseService<ApplicationResource, ApplicationResourceMapper> {
    @Autowired
    private ApplicationResourceMapper applicationResourceMapper;

    @Override
    protected ApplicationResourceMapper getMapper() {
        return applicationResourceMapper;
    }
}
