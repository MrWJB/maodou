package com.jbw.maodou.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicReceiver {

    @RabbitListener(queues = "phone")
    public void handle1(String message){
        System.out.println("PhoneReceiver:" + message);
    }

    @RabbitListener(queues = "xiaomi")
    public void handle2(String message){
        System.out.println("XiaoMiReceiver:" + message);
    }

    @RabbitListener(queues = "huawei")
    public void handle3(String message){
        System.out.println("HuaWeiReceiver:" + message);
    }
}
