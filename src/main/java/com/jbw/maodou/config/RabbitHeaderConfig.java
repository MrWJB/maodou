package com.jbw.maodou.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitHeaderConfig {

    public final static String HEADERNAME = "sang-header";

    @Bean
    HeadersExchange headersExchange(){
        return new HeadersExchange(HEADERNAME,true,false);
    }

    @Bean
    Queue queueName(){
        return new Queue("name-queue");
    }

    @Bean
    Queue queueAge(){
        return new Queue("age-queue");
    }

    /**
     * 表示消息的Header中只要有一个Header匹配上map中的key/value，就把该消息路由到名为"name-queue"的Queue上。
     * @return
     */
    @Bean
    Binding bindingName(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","sang");
        return BindingBuilder
                    .bind(queueName())
                    .to(headersExchange())
                    .whereAny(map)
                    .match();
    }

    /**
     * where("age") 表示只要消息的Header中包含age,无论age值是多少，都将消息路由到名为"age-queue"的Queue上。
     * @return
     */
    @Bean
    Binding bindingAge(){
        return BindingBuilder
                .bind(queueAge())
                .to(headersExchange())
                .where("age")
                .exists();
    }
}
