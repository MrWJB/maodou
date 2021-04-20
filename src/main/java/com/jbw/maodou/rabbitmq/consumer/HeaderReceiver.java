package com.jbw.maodou.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class HeaderReceiver {

    @RabbitListener(queues = "name-queue")
    public void handle1(String message){
        System.out.println("HeaderReceiver:name" + message);
    }

    @RabbitListener(queues = "age-queue")
    public void handle2(String message){
        System.out.println("HeaderReceiver:age" + message);
    }

}
