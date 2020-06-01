package com.chenhao.authority.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 22:37
 */
@Slf4j
public class JwtUtil {

    private static final Integer EXPIRE_TIME = 30;

    private static final Integer REFRESH_TOKEN_TIME_LIMIT = 5; //刷新token的时间限制

    private static final String SECRET = "m2wty8ecsp6yujib1u";

    private static final String ISSUER = "AUTHORITY_ADMIN";

    private final static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    private static final String CLAIM_LOGIN_NAME = "loginName";

    private static final String CLAIM_PASSWORD_LAST_MODIFY_TIME = "lastModify";


    public static String createJwtToken(String loginName, Long pwdLastModifyTime) {
        String token = JWT.create()
                .withIssuer(ISSUER)    // 发布者
                .withIssuedAt(new Date())   // 生成签名的时间
                .withExpiresAt(DateTime.now().plusMinutes(EXPIRE_TIME).toDate())   // 生成签名的有效期 30分钟
                .withClaim(CLAIM_LOGIN_NAME, loginName) // 登录名
                .withClaim(CLAIM_PASSWORD_LAST_MODIFY_TIME, pwdLastModifyTime) //密码最后修改时间
                .sign(ALGORITHM);
        return token;
    }

    public static VerifyResponse verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            //校验密码最后修改日期
            if (decodedJWT.getExpiresAt().before(DateTime.now().plusMinutes(REFRESH_TOKEN_TIME_LIMIT).toDate())) {
                //token快到期时刷新token
                return VerifyResponse.refreshToken(decodedJWT, token);
            }
            return VerifyResponse.verifySuccess(decodedJWT.getClaim(CLAIM_LOGIN_NAME).asString());
        } catch (JWTVerificationException e) {
            log.error("JWT验证失败：{}", e.getMessage(), e);
            return VerifyResponse.verifyFailure(e.getMessage().startsWith("The Token has expired on") ? "当前登录状态已过期": e.getMessage());
        }

    }

    @Getter
    @Setter
    @ToString
    @Accessors(chain = true)
    public static class VerifyResponse {
        /**
         * 是否验证通过
         */
        private Boolean verified = false;

        private String verifyMsg;

        private String loginName;

        /**
         * 是否刷新token
         */
        private Boolean refreshToken = false;

        private String oldToken;

        private String newToken;


        public static VerifyResponse verifySuccess(String loginName) {
            return new VerifyResponse().setVerified(true)
                    .setLoginName(loginName);
        }

        public static VerifyResponse refreshToken(DecodedJWT decodedJWT, String oldToken) {
            Claim loginNameClaim = decodedJWT.getClaim(CLAIM_LOGIN_NAME);
            Claim pwdLastModify = decodedJWT.getClaim(CLAIM_PASSWORD_LAST_MODIFY_TIME);
            if (Objects.isNull(loginNameClaim) || Objects.isNull(pwdLastModify)) {
                log.warn("loginNameClaim = {} , pwdLastModify = {}", loginNameClaim, pwdLastModify);
                return verifyFailure("登录信息有误，请重新登录");
            }
            return new VerifyResponse()
                    .setVerified(true)
                    .setRefreshToken(true)
                    .setNewToken(createJwtToken(loginNameClaim.asString(), pwdLastModify.asLong()))
                    .setOldToken(oldToken)
                    .setLoginName(loginNameClaim.asString());
        }

        public static VerifyResponse verifyFailure(String verifyMsg) {
            return new VerifyResponse().setVerified(false).setVerifyMsg(verifyMsg);

        }
    }

}
