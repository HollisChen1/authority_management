package com.chenhao.authority.core.resolver;

import com.chenhao.authority.common.response.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/26 22:53
 */
@RestControllerAdvice
@Slf4j
public class ExceptionResolver {

    @ExceptionHandler(Exception.class)
    public ApiResult handlerException(Exception ex){
        log.error("全局异常捕获",ex);
        return ApiResult.sysError(ex.getMessage());
    }
}
