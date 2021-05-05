package com.jbw.maodou.controller.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 基于数据库的验证。
 * 主要设计五个表：user、role、 user_role、menu、menu_role。
 * user：id,username,password,enabled,locked
 * role: id,name,nameZh
 * user_role: id,uid,rid
 * menu: id,pattern
 * menu_role: id,mid,rid
 */
@RestController("sesHelloController")
public class HelloController {

    @GetMapping("/admin/hello")
    public String admin() {
        return "hello admin";
    }

    @GetMapping("/db/hello")
    public String dba() {
        return "hello dba";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "hello user";
    }
}
