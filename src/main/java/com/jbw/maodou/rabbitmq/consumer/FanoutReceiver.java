package com.jbw.maodou.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutReceiver {

    @RabbitListener(queues = "queue-one")
    public void handle1(String message){
        System.out.println("FanoutReceiver:handler1:" + message);
    }

    @RabbitListener(queues = "queue-two")
    public void handle2(String message){
        System.out.println("FanoutReceiver:handler2:" + message);
    }
}
