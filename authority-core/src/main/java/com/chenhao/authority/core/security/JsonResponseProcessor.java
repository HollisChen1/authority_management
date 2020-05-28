package com.chenhao.authority.core.security;

import cn.hutool.json.JSONUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 *  会写json
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/29 01:29
 */
public class JsonResponseProcessor {
    protected void writeJson(HttpServletResponse response, Object data) throws IOException {
        if(!Objects.isNull(data) && !Objects.isNull(response)){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSONUtil.toJsonStr(data));
            response.getWriter().flush();
        }
    }
}
