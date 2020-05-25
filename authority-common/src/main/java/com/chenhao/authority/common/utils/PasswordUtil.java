package com.chenhao.authority.common.utils;


import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 21:41
 */
public class PasswordUtil {

    /**
     * 加密密码
     * @param loginName 登录名
     * @param originPassword 明文密码
     * @return 加密后的密码
     */
    public static final String encryptPassword(String loginName, String originPassword){
        return DigestUtil.bcrypt(originPassword + "-" + loginName);
    }

    /**
     * 验证密码是否正确
     * @param loginName
     * @param originPassword
     * @param encryptedPassword
     * @return
     */
    public static boolean verifyPassword(String loginName, String originPassword, String encryptedPassword){
        return DigestUtil.bcryptCheck(originPassword + "-" + loginName, encryptedPassword);
    }

    public static void main(String[] args) {
        System.out.println(encryptPassword("admin","123456"));
    }
}
