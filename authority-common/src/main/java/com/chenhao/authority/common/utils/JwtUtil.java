package com.chenhao.authority.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.*;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @description:
 * @author: chenhao
 * @date: 2020/5/25 22:37
 */

public class JwtUtil {

    private static final Integer EXPIRE_TIME = 30;

    private static final Integer REFRESH_TOKEN_TIME_LIMIT = 5; //刷新token的时间限制

    private static final String SECRET = "m2wty8ecsp6yujib1u";

    private static final String ISSUER = "AUTHORITY_ADMIN";

    private final static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    private static final String CLAIM_LOGIN_NAME = "loginName";


    public static String createJwtToken(String loginName) {
        String token = JWT.create()
                .withIssuer(ISSUER)    // 发布者
                .withIssuedAt(new Date())   // 生成签名的时间
                .withExpiresAt(DateTime.now().plusMinutes(EXPIRE_TIME).toDate())   // 生成签名的有效期 30分钟
                .withClaim(CLAIM_LOGIN_NAME, loginName) // 插入数据
                .sign(ALGORITHM);
        return token;
    }

    public static VerifyResponse verifyToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM)
                    .withIssuer(ISSUER)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            if (decodedJWT.getExpiresAt().after(DateTime.now().plusMinutes(REFRESH_TOKEN_TIME_LIMIT).toDate())) {
                //token快到期时刷新token
                return VerifyResponse.refreshToken(decodedJWT, token);
            }
            return VerifyResponse.verifySuccess();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return VerifyResponse.verifyFailure(e.getLocalizedMessage());
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
        private Boolean verified;

        private String verifyMsg;

        /**
         * 是否刷新token
         */
        private Boolean refreshToken;

        private String oldToken;

        private String newToken;

        public static VerifyResponse verifySuccess() {
            return new VerifyResponse().setVerified(true);
        }

        public static VerifyResponse refreshToken(DecodedJWT decodedJWT, String oldToken) {
            Claim loginNameClaim = decodedJWT.getClaim(CLAIM_LOGIN_NAME);
            if (Objects.isNull(loginNameClaim)) {
                return verifyFailure("登录信息有误，请重新登录");
            }
            return new VerifyResponse()
                    .setVerified(true)
                    .setRefreshToken(true)
                    .setNewToken(createJwtToken(loginNameClaim.asString()))
                    .setOldToken(oldToken);
        }

        public static VerifyResponse verifyFailure(String verifyMsg) {
            return new VerifyResponse().setVerified(false).setVerifyMsg(verifyMsg);

        }
    }

}
