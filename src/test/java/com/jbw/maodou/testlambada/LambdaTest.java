package com.jbw.maodou.testlambada;

import net.minidev.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class LambdaTest {

    @Test
    public void test01(){
        List<Long> list = Arrays.asList(1L, 2L);
//        list.forEach(item -> System.out.println(item));
        list.forEach(System.out::println);
    }
}
