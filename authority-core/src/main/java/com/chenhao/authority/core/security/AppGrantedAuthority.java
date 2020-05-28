package com.chenhao.authority.core.security;

import com.chenhao.authority.domain.ApplicationResource;
import com.chenhao.authority.vo.ResourceVO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * <p>
 *  授权信息列表
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/28 23:58
 */
@Data
@Accessors(chain = true)
public class AppGrantedAuthority implements GrantedAuthority {

    //角色ID
    private Integer roleId;

    //角色名称
    private String roleName;


    @Override
    public String getAuthority() {
        return roleName;
    }
}
