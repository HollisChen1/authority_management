package com.chenhao.authority.server.configuration;

import com.chenhao.authority.core.interceptor.AuthenticationInterceptor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/26 21:55
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Value("${webMvc.excludePaths:/login,/logout}")
    private String excludePaths;

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/**");
        if(StringUtils.isNotBlank(excludePaths)){
            registration.excludePathPatterns(excludePaths.split(","));
        }
    }
}
