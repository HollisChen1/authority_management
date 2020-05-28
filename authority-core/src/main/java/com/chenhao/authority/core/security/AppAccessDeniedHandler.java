package com.chenhao.authority.core.security;

import cn.hutool.json.JSONUtil;
import com.chenhao.authority.common.response.ApiResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/29 01:11
 */
@Component
public class AppAccessDeniedHandler extends JsonResponseProcessor  implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        super.writeJson(httpServletResponse,ApiResult.forbidden());
    }
}
