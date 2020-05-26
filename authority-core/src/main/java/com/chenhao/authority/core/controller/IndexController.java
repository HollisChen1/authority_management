package com.chenhao.authority.core.controller;

import com.chenhao.authority.common.response.ApiResult;
import com.chenhao.authority.core.manager.IndexManager;
import com.chenhao.authority.dto.LoginRequestDTO;
import com.chenhao.authority.vo.LoginResponseVO;
import com.chenhao.authority.vo.ResourceVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 23:19
 */
@RestController
@Slf4j
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private IndexManager indexManager;

    @PostMapping("/login")
    public ApiResult<LoginResponseVO> doLogin(@Valid @RequestBody LoginRequestDTO requestDTO){
        return indexManager.doLogin(requestDTO);
    }

    @PostMapping("/logout")
    public ApiResult doLogout(){
        return ApiResult.success();
    }


    @GetMapping("/resources")
    public ApiResult<List<ResourceVO>> getResourceList(@RequestParam String appCode){
        return indexManager.getAccessResources(appCode);
    }
}
