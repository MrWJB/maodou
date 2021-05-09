package com.jbw.maodou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(value = "com.jbw.maodou.mapper")
//@EnableScheduling   //开启定时任务
@EnableBatchProcessing  //开启批处理
@EnableCaching  //开启缓存
public class MaodouApplication {

    public static void main(String[] args) {
//        SpringApplication.run(MaodouApplication.class, args);
        SpringApplicationBuilder application = new SpringApplicationBuilder(MaodouApplication.class);
        application.bannerMode(Banner.Mode.OFF).run(args);
    }

}
