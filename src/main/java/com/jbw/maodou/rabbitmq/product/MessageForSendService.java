package com.jbw.maodou.rabbitmq.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageForSendService implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {

    private static Logger log = LoggerFactory.getLogger(MessageForSendService.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    /*
    发送消息
     */
    public void sendMessage(String exchange,String routingKey,Object msg){
        //消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        //消息消费者确认收到消息后，手动ack回执
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
        //发送消息
        rabbitTemplate.convertAndSend(exchange,routingKey,msg);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            log.info("---- confirm ----ack==true  cause="+cause);
        }else {
            log.info("---- confirm ----ack==false  cause="+cause);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        log.info(returnedMessage.getReplyText(),returnedMessage.getReplyCode(),returnedMessage.getReplyText());
    }
}
