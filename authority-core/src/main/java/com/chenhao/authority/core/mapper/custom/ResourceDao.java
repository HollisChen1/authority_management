package com.chenhao.authority.core.mapper.custom;

import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/29 00:36
 */
public interface ResourceDao {
    boolean isGrantedUrl(@Param("appCode")String appCode, @Param("userId") Integer userId,@Param("url")String url);
}
