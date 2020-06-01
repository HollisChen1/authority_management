package com.chenhao.authority.base;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/6/1 21:49
 */
@Data
public class PageRequest{
    /**
     * 起始页
     */
    private int pageNo = 0;

    /**
     * 每页显示条数
     */
    private int pageSize = 10;

    public int getOffset(){
        return this.pageNo * this.pageSize;
    }
}
