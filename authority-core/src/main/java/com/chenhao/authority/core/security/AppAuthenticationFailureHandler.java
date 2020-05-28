package com.chenhao.authority.core.security;

import cn.hutool.json.JSONUtil;
import com.chenhao.authority.common.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
 * @date: 2020/5/29 01:10
 */
@Slf4j
@Component
public class AppAuthenticationFailureHandler extends JsonResponseProcessor implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        super.writeJson(httpServletResponse,ApiResult.parameterError("用户名或密码错误，登录失败"));
    }
}
