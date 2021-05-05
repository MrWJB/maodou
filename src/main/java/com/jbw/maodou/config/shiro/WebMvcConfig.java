//package com.jbw.maodou.config.shiro;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 对于其他不需要的角色就能访问的接口，直接在WebMvc中配置就行。
// */
//@Configuration
//public class WebMvcConfig implements WebMvcConfigurer{
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("shiro/login");
//        registry.addViewController("/index").setViewName("shiro/index");
//        registry.addViewController("/unauthorized").setViewName("shiro/unauthorized");
//    }
//}
