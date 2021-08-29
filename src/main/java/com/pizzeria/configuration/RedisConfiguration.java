package com.pizzeria.configuration;

import java.util.Map;
import java.util.UUID;

import com.cloudinary.utils.ObjectUtils;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableAutoConfiguration
public class RedisConfiguration {
    
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }


    @Bean
    public RedisTemplate<UUID, String> redisTemplate() {
        RedisTemplate<UUID, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    /*public static final Map config = ObjectUtils.asMap(
        "cloud_name", System.getenv("CLOUD_NAME"),
        "api_key", System.getenv("API_KEY"),
        "api_secret", System.getenv("API_SECRET"),
        "secure", true
    );*/
}
