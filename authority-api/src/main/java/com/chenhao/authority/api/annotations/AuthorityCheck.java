package com.chenhao.authority.api.annotations;

import java.lang.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/6/1 21:04
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorityCheck {
    String value();

    boolean noLogin() default false;
}
