package com.zwh.firstsc.gateway.filter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
@RefreshScope
public class TokenFilter implements GlobalFilter, Ordered {
    /**
     * 不进行token校验的请求地址
     */
//    @Value("#{'${jwt.ignoreUrlList}'.split(',')}")
    @Value("${jwt.ignore-path}")
    public List<String> ignoreUrl;

    @Value("${nacos.zwh}")
    public String config01;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("--------------- TokenFilter");
        System.out.println("---------------" + config01);
        ignoreUrl.forEach(str -> System.out.println(str));
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        // 获取地址路径
        String requestUrl = serverHttpRequest.getPath().toString();
        boolean needCheck = !(ignoreUrl.contains(requestUrl));
        System.out.println(needCheck);
        if (needCheck) {
            // 从请求头中获取token
            String token = serverHttpRequest.getHeaders().getFirst("token");
            //type用于区分不同的端，在做校验token时需要
            String type= serverHttpRequest.getHeaders().getFirst("type");


            if (StringUtils.isBlank(token) || StringUtils.isBlank(type)) {
                // 没有数据
                return response(exchange, "1001", "请重新授权1");
            } else {
                //校验token
                String userId = verifyJWT(token);
                if (StringUtils.isEmpty(userId)) {
                    return response(exchange, "1001", "请重新授权2");
                }
                //将现在的request，添加当前身份
                ServerHttpRequest mutableReq = serverHttpRequest.mutate().header("Authorization-UserId", userId).build();
                ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
                return chain.filter(mutableExchange);
            }
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    private Mono<Void> response(ServerWebExchange exchange, String code, String message) {
        ServerHttpResponse response = exchange.getResponse();
        Map<String, String> result = new HashMap<>();
        result.put("code", code);
        result.put("msg", message);
        byte[] bits = result.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bits);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "text/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    /**
     * JWT验证
     * @param token
     * @return userId
     */
    private String verifyJWT(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("auth0") // 匹配指定的token发布者 auth0
                    .build();
            // 解码JWT ，verifier 可复用
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(jwt);
            System.out.println(jwt.getClaim("userInfo").asMap());
            return (String) jwt.getClaim("userInfo").asMap().get("name");
        } catch (JWTVerificationException e) {
            // 无效的签名/声明
            e.printStackTrace();
            return "";
        }
    }
}
