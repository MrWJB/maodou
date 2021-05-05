package com.jbw.maodou.quartz;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component("myFirstJob")
public class MyFirstJob {

    public void sayHello(){
        System.out.println("MyFirstJob:sayHello: "+ new Date());
    }
}
