package com.chenhao.authority.core.service;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 20:06
 */
public abstract class BaseService<T extends Serializable, Mapper extends BaseMapper<T>> {
    private final static String DATA_CANNOT_BE_NULL = "参数不能为空";

    protected abstract Mapper getMapper();

    public Integer insert(T data) {
        Assert.notNull(data, DATA_CANNOT_BE_NULL);
        return getMapper().insert(data);
    }

    public T get(Serializable id) {
        Assert.notNull(id, DATA_CANNOT_BE_NULL);
        return getMapper().selectById(id);
    }

    public List<T> findByCondition(Wrapper<T> wrapper) {
        return getMapper().selectList(wrapper);
    }

    public Integer updateById(T record) {
        Assert.notNull(record, DATA_CANNOT_BE_NULL);
        return getMapper().updateById(record);
    }

    public Integer deleteById(Serializable id) {
        Assert.notNull(id, DATA_CANNOT_BE_NULL);
        return getMapper().deleteById(id);
    }

    public List<T> getByIds(List<? extends Serializable> ids) {
        Assert.notNull(ids, DATA_CANNOT_BE_NULL);
        if(CollectionUtil.isEmpty(ids)){
            return Collections.emptyList();
        }
        return getMapper().selectBatchIds(new HashSet<>(ids));
    }

}
