//package com.jbw.maodou.controller.shiro;
//
//import org.apache.shiro.authz.AuthorizationException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.servlet.ModelAndView;
//
///**
// * ControllerAdvice 全局处理异常
// */
//@ControllerAdvice
//public class ExceptionController {
//
//    @ExceptionHandler(AuthorizationException.class)
//    public ModelAndView error(AuthorizationException e) {
//        ModelAndView mv = new ModelAndView("unauthorized");
//        mv.addObject("error", e.getMessage());
//        return mv;
//    }
//}
