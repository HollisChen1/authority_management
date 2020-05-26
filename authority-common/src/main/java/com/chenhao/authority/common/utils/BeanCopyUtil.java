package com.chenhao.authority.common.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ReflectUtil;
import net.sf.cglib.beans.BeanCopier;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 22:15
 */
public class BeanCopyUtil {

    /**
     * 拷贝对象
     * @param source
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T copyObject(Object source, Class<T> tClass) {
        Assert.notNull(source);
        Assert.notNull(tClass);
        T target = ReflectUtil.newInstance(tClass);
        final BeanCopier beanCopier = BeanCopier.create(source.getClass(), tClass, false);
        beanCopier.copy(source, target, null);
        return target;
    }

    /**
     * 复制List集合元素
     * @param sourceList
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> copyList(List<?> sourceList, Class<T> tClass){
        if(CollectionUtil.isEmpty(sourceList)){
            return Collections.emptyList();
        }
        return sourceList.stream()
                .map(source -> BeanCopyUtil.copyObject(source, tClass))
                .collect(Collectors.toList());
    }
}
