package com.jbw.maodou.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FanoutExchange的数据交换策略是把所有到达FanoutExchange的消息转发给所有与它绑定的Queue，
 * 在这种策略中，routingkey将不起任何作用。
 */
@Configuration
public class RabbitFanoutConfig {

    public final static String FANOUTNAME = "sang-fanout";

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUTNAME,true,false);
    }

    @Bean
    Queue queueOne(){
        return new Queue("queue-one");
    }

    @Bean
    Queue queueTwo(){
        return new Queue("queue-two");
    }

    @Bean
    Binding bindingOne(){
        return BindingBuilder.bind(queueOne()).to(fanoutExchange());
    }

    @Bean
    Binding bindingTwo(){
        return BindingBuilder.bind(queueTwo()).to(fanoutExchange());
    }
}
