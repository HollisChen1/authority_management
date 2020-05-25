package com.chenhao.authority.common.context;

import com.chenhao.authority.base.CurrentUserInfo;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 23:58
 */
public class SessionContext {
    private static ThreadLocal<CurrentUserInfo> currentUser = new ThreadLocal<>();

    public static void setCurrentUserInfo(CurrentUserInfo currentUserInfo){
        currentUser.set(currentUserInfo);
    }

    public static void remove(){
        currentUser.remove();
    }

    public static CurrentUserInfo getCurrentUser(){
        return currentUser.get();
    }
}
