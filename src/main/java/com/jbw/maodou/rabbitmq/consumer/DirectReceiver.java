package com.jbw.maodou.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {

    /**
     * 通过@RabbitListener注解指定一个方法是一个消费方法，方法参数就是所接收到的消息。
     * @param msg
     */
    @RabbitListener(queues = "hello-queue")
    public void handler1(String msg){
        System.out.println("DirectReceiver:" + msg);
    }
}
