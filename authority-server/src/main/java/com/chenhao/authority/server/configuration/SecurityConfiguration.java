package com.chenhao.authority.server.configuration;

import com.chenhao.authority.core.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/28 23:04
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private AppUserDetailService userDetailService;

    @Autowired
    private AppAccessDecisionManager accessDecisionManager;

    @Autowired
    private AppAuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private AppAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AppAccessDeniedHandler accessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .accessDecisionManager(accessDecisionManager)
                .anyRequest()
                .authenticated();
        http.formLogin()
                .usernameParameter("userName")
                .passwordParameter("password")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
