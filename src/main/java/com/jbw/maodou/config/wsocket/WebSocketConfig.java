//package com.jbw.maodou.config.wsocket;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
//@Configuration
//@EnableWebSocketMessageBroker //表示开启WebSocket消息代理
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry config) {
//        /**
//         *  设置消息代理的前缀，如果消息的前缀是"/topic"或是 "/queue"，就会把消息转发给消息代理（broker），
//         *  再由消息代理将消息广播给当前连接的客户端。
//         */
//        config.enableSimpleBroker("/topic","/queue");
//        /**
//         * 表示配置一个或多个前缀，通过这些前缀过滤出需要被注解方法处理的消息。
//         * 例如：前缀为"/app"的destination可以通过@MessageMapping注解的方法处理，
//         * 而其他destination：例如：/topic，/queue 将被直接交给breaker处理。
//         */
//        config.setApplicationDestinationPrefixes("/app");
//    }
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        /**
//         * 表示定义个前缀为/chat 的endPoint，并开启sockjs支持，sockjs可以解决浏览器对WebSocket的兼容性问题，客户端
//         * 将通过这里配置的URL来建立WebSocket连接。
//         */
//        registry.addEndpoint("/chat").withSockJS();
//    }
//}