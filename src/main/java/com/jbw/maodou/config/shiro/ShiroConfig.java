//package com.jbw.maodou.config.shiro;
//
//import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
//import org.apache.shiro.realm.Realm;
//import org.apache.shiro.realm.text.TextConfigurationRealm;
//import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
//import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Apache Shiro是一个开源的轻量级的Java安全框架，它提供了身份验证、授权、密码管理以及会话管理功能。
// * 相对Spring security，shiro框架更加直观】易用、同时也能提供健壮的安全性。
// */
//@Configuration
//public class ShiroConfig {
//
//    /**
//     * Realm 可以是自定义Realm，也可以是shiro提供的Realm。
//     * @return
//     */
//    @Bean
//    public Realm realm() {
//        TextConfigurationRealm realm = new TextConfigurationRealm();
//        //配置了两个用户sang=123角色user，admin=123角色admin
//        realm.setUserDefinitions("wangjibin=123,user\n admin=123,admin");
//        //配置角色权限，admin具有read、write权限，user具有read权限。
//        realm.setRoleDefinitions("admin=read,write\n user=read");
//        return realm;
//    }
//
//    /**
//     *ShiroFilterChainDefinition Bean中配置了基本的过滤规则，/login和/doLogin 可以是匿名访问，/logout是一个注销登陆的请求。其余的请求需要认证后才能访问。
//     * @return
//     */
//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
//        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
//        chainDefinition.addPathDefinition("/login", "anon");
//        chainDefinition.addPathDefinition("/doLogin", "anon");
//        chainDefinition.addPathDefinition("/logout", "logout");
//        chainDefinition.addPathDefinition("/**", "authc");
//        return chainDefinition;
//    }
//
//    /**
//     * 如果不在Thymeleaf中使用shiro标签，那么可以不提供ShiroDialect。
//     * @return
//     */
//    @Bean
//    public ShiroDialect shiroDialect() {
//        return new ShiroDialect();
//    }
//}
