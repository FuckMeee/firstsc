package com.zwh.firstsc.gateway.limiter;

import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhuweihao
 * @date 2020/8/5
 */
@Component
public class MyRedisRateLimiter {
    // private static final Logger logger = LoggerFactory.getLogger(MyRedisRateLimiter.class);

    private final String keyNamespace="gateway:demo:limit:";

    private final RedisTemplate<String, Long> redisTemplate;

    private final RedisScript<List<Long>> redisScript;

    /**
     * @param redisScript 该RedisScript对象将会自动注入进来，该对象使用的正是上面介绍的request-rate-limiter.lua脚本
     */
    public MyRedisRateLimiter(RedisTemplate redisTemplate,
                              RedisScript<List<Long>> redisScript){
        this.redisTemplate = redisTemplate;
        this.redisScript = redisScript;
    }

    public boolean isAllowed(String key, int replenishRate, int burstCapacity){
        List<String> keys = Arrays.asList(keyNamespace+key+"tokens", keyNamespace+key+"timestamp");
        try {
            List<Long> response = this.redisTemplate.execute(this.redisScript, keys,
                    replenishRate+"",
                    burstCapacity+"",
                    Instant.now().getEpochSecond()+"",
                    1+"");
            if (response.get(0) == 0){
                return false;
            }else{
                return true;
            }
        } catch (Exception e){
            // logger.error(e.getMessage(), e);
            e.printStackTrace();
            System.out.println("--------------- ApiRateGlobalFilter222");
            return true;
        }

    }
}
