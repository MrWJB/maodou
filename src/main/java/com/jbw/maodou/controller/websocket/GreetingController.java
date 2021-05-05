//package com.jbw.maodou.controller.websocket;
//
//import com.jbw.maodou.entity.websocket.Chat;
//import com.jbw.maodou.entity.websocket.Message;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
//import java.security.Principal;
//
//@Controller
//public class GreetingController {
//
//    @Autowired
//    SimpMessagingTemplate messagingTemplate;
//
//    /**
//     * @MessageMapping("/hello") 注解的方法将用来接收/app/hello 路径发送来的消息，在注解方法中对消息进行处理后，再
//     * 将消息转发到@SendTo定义的路径上，而@SendTo路径是一个前缀为/topic的路径，因此该消息将被交给消息代理broker，
//     * 再由broker进行广播。
//     * @param message
//     * @return
//     * @throws Exception
//     */
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Message greeting(Message message) throws Exception {
//        return message;
//    }
//
//    /**
//     * @MessageMapping("/chat") 表示来自"/app/chat"路径的消息将被chat方法处理。
//     * @param principal 可以用来获取当前登录用户的信息。
//     * @param chat  客户端发送来的消息。
//     *   在chat方法中，首先获取当前用户的用户名，设置给chat对象的from属性，再将消息发送出去，发送的目标用户就是chat对象的to属性值。
//     *   消息发送使用的方法是convertAndSendToUser，该方法内部调用了convertAndSend方法，并对消息路径做了处理。
//     *              注意：这里destinationPrefix的默认值是"/user"，也就是说消息的最终发送路径是："/user/用户名/queue/chat"。
//     *              chat是一个普通的Javabean ,to 属性表示消息的目标对象，from表示消息从哪里来，content表示消息的主体内容。
//     */
//    @MessageMapping("/chat")
//    public void chat(Principal principal, Chat chat) {
//        String from = principal.getName();
//        chat.setFrom(from);
//        messagingTemplate.convertAndSendToUser(chat.getTo(),
//                "/queue/chat", chat); // destination比chat.js中订阅的地址少了一个/user前缀，是因为SimpMessagingTemplate自动为destination添加了/user前缀。
//    }
//}