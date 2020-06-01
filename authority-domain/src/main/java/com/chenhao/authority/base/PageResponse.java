package com.chenhao.authority.base;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/6/1 21:52
 */
public class PageResponse<T> {
    private int pageNo;

    private int pageSize;

    private int total;

    private List<T> data;
}
