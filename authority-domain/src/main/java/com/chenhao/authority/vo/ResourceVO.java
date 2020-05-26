package com.chenhao.authority.vo;

import lombok.Data;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/26 20:37
 */
@Data
public class ResourceVO {

    private Integer id;

    private String name;

    private String icon;

    private String url;

    private Byte type;

    private String bindComponent;

    private List<ResourceVO> subMenu;
}
