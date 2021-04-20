package com.jbw.maodou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.jbw.maodou.mapper")
public class MaodouApplication {

    public static void main(String[] args) {
        SpringApplication.run(MaodouApplication.class, args);
    }

}
