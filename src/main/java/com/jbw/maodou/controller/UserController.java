package com.jbw.maodou.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jbw.maodou.entity.User;
import com.jbw.maodou.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangjibin
 * @since 2021-04-18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping("pages")
    public List<User> getUsers(){
        Page<User> page = userService.page(new Page<>(1, 2));
        return page.getRecords();
    }
}
