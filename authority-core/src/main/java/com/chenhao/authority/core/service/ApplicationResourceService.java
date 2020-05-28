package com.chenhao.authority.core.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chenhao.authority.common.enums.ResourceType;
import com.chenhao.authority.common.utils.BeanCopyUtil;
import com.chenhao.authority.core.mapper.ApplicationResourceMapper;
import com.chenhao.authority.core.mapper.custom.ResourceDao;
import com.chenhao.authority.domain.ApplicationResource;
import com.chenhao.authority.vo.ResourceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    private ResourceDao resourceDao;

    @Override
    protected ApplicationResourceMapper getMapper() {
        return applicationResourceMapper;
    }

    public List<ApplicationResource> findByApplicationAndIds(Integer applicationId, List<Integer> ids) {
        Assert.notNull(applicationId, "应用ID为空");
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        QueryWrapper<ApplicationResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("application_id", applicationId);
        queryWrapper.in("id", ids);
        return applicationResourceMapper.selectList(queryWrapper);
    }

    /**
     * 构建资源树
     *
     * @return
     */
    public List<ResourceVO> buildResourceTree(List<ApplicationResource> originData, Integer parentId) {
        if (CollectionUtils.isEmpty(originData)) {
            return Collections.emptyList();
        }
        List<ApplicationResource> parentResourceList = originData.stream()
                .filter(applicationResource -> Objects.equals(parentId, applicationResource.getParentId()))
                .sorted(Comparator.comparing(ApplicationResource::getSort))
                .collect(Collectors.toList());
        List<ResourceVO> resourceParentList = BeanCopyUtil.copyList(parentResourceList, ResourceVO.class);
        resourceParentList.stream()
                .forEach(resourceVO -> resourceVO.setSubMenu(buildResourceTree(originData.stream()
                        .filter(applicationResource -> !Objects.equals(applicationResource.getParentId(), parentId))
                        .collect(Collectors.toList()), resourceVO.getId())));
        return resourceParentList;
    }

    /**
     * 根据资源ID获取API的URL
     *
     * @param ids
     * @return
     */
    public List<String> getFunctionUrls(List<Integer> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        QueryWrapper<ApplicationResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        queryWrapper.eq("type", ResourceType.FUNCTION.getType());
        return applicationResourceMapper.selectList(queryWrapper)
                .stream().map(ApplicationResource::getUrl).collect(Collectors.toList());

    }

    /**
     * 校验角色能否访问该url
     * @param roleId
     * @param url
     * @return
     */
    public boolean isGrantedUrl(Integer roleId, String url) {
        return resourceDao.isGrantedUrl(roleId, url);
    }

}
