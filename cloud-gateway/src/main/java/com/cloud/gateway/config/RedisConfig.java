package com.cloud.gateway.config;

import com.cloud.common.util.RedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author 黄志强
 * @data 2018/12/10 10:34
 */
@Configuration
public class RedisConfig {

    @Bean(name = {"redisTemplate","stringRedisTemplate"})
    public RedisTemplate stringRedisTemplate(RedisConnectionFactory factory){
        RedisTemplate template=new RedisTemplate();
        template.setConnectionFactory(factory);
        return template;
    }

}
