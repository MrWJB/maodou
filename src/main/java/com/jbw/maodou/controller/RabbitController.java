package com.jbw.maodou.controller;

import com.jbw.maodou.config.mq.RabbitTopicConfig;
import com.jbw.maodou.rabbitmq.product.MessageForSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    MessageForSendService messageForSendService;

    @RequestMapping("/send")
    public String sendMessage(){
        for (int i = 0;i< 10;i++){
            messageForSendService.sendMessage(RabbitTopicConfig.TOPICNAME,"xiaomi.#","msg"+i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "send ok!";
    }
}
