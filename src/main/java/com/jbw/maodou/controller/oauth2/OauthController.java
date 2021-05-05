//package com.jbw.maodou.controller.oauth2;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * oauth2
// * oauth2是一个开放标准，该标准允许用户让第三方应用访问该用户在某一网站上存储的私密资源（如头像，照片，视频等），而这一过程中无需将用户名和密码提供给
// * 第三方应用。
// * 实现这一功能是通过提供一个令牌（token），而不是用户名密码来访问他们存放在特定服务提供者的数据。
// * 每一个令牌授权一个特定的网站在特定的时段内访问特定的资源。
// *
// * 优点：
// *  传统的web开发登录验证一般都是基于session的，但是在前后端分离的架构中继续使用session会有很多不便，因为移动端要么不支持cookie（微信小程序），要么使用非常不便，对于这些问题，使用
// *  Oauth2认证都能解决。
// *
// *  Oauth2中的几个基本角色：
// *      1。资源所有者：资源所有者即用户、具有头像，照片，视频等资源。
// *      2。客户端：客户端即第三方应用，例如知乎
// *      3。授权服务器：授权服务器用来验证用户提供的信息是否正确，并返回一个令牌给第三方应用。
// *      4。资源服务器：资源服务器是提供给用户资源的服务器，例如头像，照片，视频等。
// *   一般来说资源服务器和授权服务器可以是同一台服务器。
// *
// *  Oauth2授权的大致流程：
// *      1。客户端（第三方应用）向用户请求授权
// *      2。用户单击客户端所呈现的服务授权页面上的同意授权按钮后，服务端返回一个授权许可凭证给客户端。
// *      3。客户端拿着授权许可凭证去授权服务器申请令牌。
// *      4。授权服务器验证信息无误后，发放令牌给客户端。
// *      5。客户端拿着令牌去资源服务器访问资源。
// *      6。资源服务器验证令牌无误后开放资源。
// */
//@RestController(value = "oauthController")
//public class OauthController {
//    @GetMapping("/admin/hello")
//    public String admin() {
//        return "Hello admin!";
//    }
//
//    @GetMapping("/user/hello")
//    public String user() {
//        return "Hello user!";
//    }
//
//    @GetMapping("/helloOauth")
//    public String hello() {
//        return "hello";
//    }
//}
