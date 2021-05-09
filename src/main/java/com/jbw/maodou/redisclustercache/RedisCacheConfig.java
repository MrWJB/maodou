package com.jbw.maodou.redisclustercache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * redis缓存配置
 */
@Configuration
public class RedisCacheConfig {
    @Autowired
    RedisConnectionFactory conFactory;

    @Bean
    RedisCacheManager redisCacheManager() {
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        RedisCacheConfiguration redisCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                        .prefixKeysWith("sang:")
                        .disableCachingNullValues()
                        .entryTtl(Duration.ofMinutes(30));
        configMap.put("c1", redisCacheConfig);
        RedisCacheWriter cacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(conFactory);
        /**
         * 构建RedisCacheManager需要三个参数，
         * 第一个参数是cacheWriter，直接通过nonLockingRedisCacheWriter方法构造出来即可；
         * 第二个参数是默认的缓存配置；
         * 第三个参数是提前定义好的缓存配置。
         *
         * RedisCacheManager构造方法中第三个参数是一个提前定义好的缓存参数，他是一个Map类型的参数，该Map中的key就是指缓存的名字，value就是
         * 该名称的缓存所对应的缓存配置，例如key的前缀，缓存过期时间等，若缓存注解中使用的缓存名称不存在于Map中，则使用RedisCacheManager构造
         * 方法中第二个参数所定义的缓存策略进行数据缓存。
         *
         * 例如如下两个配置：
         * 1: @Cacheable(value="c1")
         * 2: @Cacheable(value="c2")
         * 第一行的注解中，c1存在于configMap集合中，因此使用的缓存策略是configMap集合中c1所对应的缓存策略，c2不存在于ConfigMap集合中，因此使用的缓存策略
         * 是默认的缓存策略。
         *
         * **本案例中默认缓存策略通过调用RedisCacheConfiguration中的defaultCacheConfig()方法获取。
         * 源码：
         *  默认的缓存过期时间为0，即永不过期；
         *  第二个参数true表示允许存储null；
         *  第三个参数true表示开启key的前缀；
         *  第四个参数表示key的默认前缀是"缓存名：："，接下来两个参数表示key和value的序列化方式，最后一个参数则是一个类型转换器。
         *
         */
        RedisCacheManager redisCacheManager = new RedisCacheManager(cacheWriter,RedisCacheConfiguration.defaultCacheConfig(),configMap);
        return redisCacheManager;
    }
}
