package com.jbw.maodou;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(value = "com.jbw.maodou.mapper")
//@EnableScheduling
@EnableBatchProcessing
@EnableCaching
public class MaodouApplication {

    public static void main(String[] args) {
//        SpringApplication.run(MaodouApplication.class, args);
        SpringApplicationBuilder application = new SpringApplicationBuilder(MaodouApplication.class);
        application.bannerMode(Banner.Mode.OFF).run(args);
    }

}
