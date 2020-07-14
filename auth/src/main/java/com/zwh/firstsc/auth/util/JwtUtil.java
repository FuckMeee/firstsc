package com.zwh.firstsc.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    // 创建
    public static String createToken(Map<String, String> map) {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create().withIssuer("auth0")
                .withExpiresAt(DateUtil.addHour(new Date(), 24 * 14))
                .withClaim("userInfo", map)
                .sign(algorithm);
        return token;
    }

    // 验证
    public static boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0") // 匹配指定的token发布者 auth0
                    .build();
            // 解码JWT ，verifier 可复用
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt);
            return true;
        } catch (JWTVerificationException e) {
            // 无效的签名/声明
            System.out.println("666");
            return false;
        }
    }

    // 解码
    public static DecodedJWT decodeToken(String token) {
        return JWT.decode(token);
    }

    // 获取用户信息
    public static Map<String, Object> getUserInfo(String token) {
        return decodeToken(token).getClaim("userInfo").asMap();
    }

    // 获取用户信息
    public static String getUserInfoByKey(String token, String key) {
        return (String) getUserInfo(token).get(key);
    }
}
