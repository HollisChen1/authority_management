package com.chenhao.authority.core.security;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.chenhao.authority.common.response.ApiResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 *  登录成功处理器
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/29 01:08
 */
@Component
public class AppAuthenticationSuccessHandler extends JsonResponseProcessor implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        super.writeJson(httpServletResponse, ApiResult.success("登录成功"));
    }
}
