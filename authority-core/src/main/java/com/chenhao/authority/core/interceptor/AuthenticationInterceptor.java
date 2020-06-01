package com.chenhao.authority.core.interceptor;

import cn.hutool.json.JSONUtil;
import com.chenhao.authority.api.annotations.AuthorityCheck;
import com.chenhao.authority.base.CurrentUserInfo;
import com.chenhao.authority.common.context.SessionContext;
import com.chenhao.authority.common.enums.StatusEnum;
import com.chenhao.authority.common.response.ApiResult;
import com.chenhao.authority.common.utils.JwtUtil;
import com.chenhao.authority.core.service.ApplicationResourceService;
import com.chenhao.authority.core.service.ApplicationService;
import com.chenhao.authority.core.service.UserService;
import com.chenhao.authority.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/26 21:20
 */
@Component
@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {
    private static final String JWT_TOKEN_KEY_OF_HEADER = "Authorization";

    @Value("${base.ssoLoginUrl:http://www.baidu.com}")
    private String ssoLoginUrl;

    @Value("${spring.application.name}")
    private String appCode;

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationResourceService applicationResourceService;

    @Autowired
    private ApplicationService applicationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
        if(handler instanceof HandlerMethod){
            Method method = ((HandlerMethod) handler).getMethod();
            if(method.isAnnotationPresent(AuthorityCheck.class)){
                AuthorityCheck authorityCheck = method.getAnnotation(AuthorityCheck.class);
                if(authorityCheck.noLogin()){
                    return true;
                }
                requestUri = authorityCheck.value();
            }
        }
        String token = request.getHeader(JWT_TOKEN_KEY_OF_HEADER);
        if(StringUtils.isBlank(token)){
            redirectToLogin(request, response, "当前未登录，请先登录");
            return false;
        }
        final JwtUtil.VerifyResponse verifyResponse = JwtUtil.verifyToken(token);
        if (!verifyResponse.getVerified()) {
            //校验不通过重定向到登录页面
            redirectToLogin(request, response, verifyResponse.getVerifyMsg());
            return false;
        }
        String loginName = verifyResponse.getLoginName();
        User user = userService.getByLoginName(loginName);
        if (user == null || !Objects.equals(user.getStatus(), StatusEnum.ENABLE.getCode())) {
            redirectToLogin(request, response, user == null ? "当前登录用户信息错误" : "您的账号已被禁用");
            return false;
        }
        //校验权限
        if(handler instanceof HandlerMethod){
            if(applicationService.isCheckAuthority(appCode) && !applicationResourceService.isGrantedUrl(appCode,user.getId(), requestUri)){
                log.warn("用户[{}]访问[{}]的[{}]资源被拦截", loginName, appCode, requestUri);
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSONUtil.toJsonStr(ApiResult.forbidden()));
                response.getWriter().flush();
                return false;
            }
        }
        //设置当前登录用户信息，供后续业务使用
        SessionContext.setCurrentUserInfo(new CurrentUserInfo(user.getId(), user.getLoginName(), user.getUserName()));
        if (verifyResponse.getRefreshToken()) {
            //token被刷新，写入响应头
            response.setHeader(JWT_TOKEN_KEY_OF_HEADER, verifyResponse.getNewToken());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //释放ThreadLocal
        if (null != SessionContext.getCurrentUser()) {
            SessionContext.remove();
        }
    }

    private void redirectToLogin(HttpServletRequest request, HttpServletResponse response, String errorMsg) throws IOException {
        response.sendRedirect(ssoLoginUrl + "?redirectUrl=" + URLEncoder.encode(request.getRequestURL().toString(), "UTF-8") + "&msg=" + errorMsg);
    }
}
