package com.zwh.firstsc.gateway.filter;

import com.zwh.firstsc.gateway.limiter.MyRedisRateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author zhuweihao
 * @date 2020/8/5
 */
@Component
@RefreshScope
public class ApiRateGlobalFilter implements GlobalFilter, Ordered {
    @Autowired
    private MyRedisRateLimiter myRedisRateLimiter;

    // @Autowired
    // private PathRateService pathRateService;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("--------------- ApiRateGlobalFilter");
        // 获取到该接口路径
        String path = exchange.getRequest().getPath().value();
        // 从数据库中获取到该接口对于的限流参数
        // PathRate pathRate = pathRateService.get(path);

        // 如果允许同行，没有超过该接口的流量限制
        if (myRedisRateLimiter.isAllowed("path:"+path+":", 1, 1)){
            return chain.filter(exchange);
        } else {
            // 如果不允许此次请求通过，就返回429，请求太频繁的错误
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
