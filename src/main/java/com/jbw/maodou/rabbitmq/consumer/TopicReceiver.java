package com.jbw.maodou.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

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

    @RabbitHandler
    public void process(String msg, Channel channel, Message message) throws IOException {
        //拿到消息延迟消费
        try {
            Thread.sleep(1000*1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**
         * 确认一条消息：<br>
         * channel.basicAck(deliveryTag, false); <br>
         * deliveryTag:该消息的index <br>
         * multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息 <br>
         */
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
            //消费者处理出了问题，需要告诉队列信息消费失败
            /**
             * 拒绝确认消息:<br>
             * channel.basicNack(long deliveryTag, boolean multiple, boolean requeue) ; <br>
             * deliveryTag:该消息的index<br>
             * multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。<br>
             * requeue：被拒绝的是否重新入队列 <br>
             */
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
        }
            /**
			 * 拒绝一条消息：<br>
			 * channel.basicReject(long deliveryTag, boolean requeue);<br>
			 * deliveryTag:该消息的index<br>
			 * requeue：被拒绝的是否重新入队列
             */
            //channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
            System.out.println("get message success!!! message>> = "+msg);
    }

}
