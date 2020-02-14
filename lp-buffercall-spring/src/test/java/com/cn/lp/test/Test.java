package com.cn.lp.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by qirong on 2019/5/10.
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
        TestService testService = ac.getBean(TestService.class);
        String[] testStr = new String[2];
        testStr[0] = "123";
        testStr[1] = "1231423";
        HashMap<String, String> dataMap = new HashMap<>();
        dataMap.put("123", "123");
        dataMap.put("3245435", "23432");
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(() -> {
            System.out.println(testService.test("123", "Jack".getBytes(), 2, testStr, dataMap));
        });
        executorService.submit(() -> {
            System.out.println(testService.test("123", "Jack".getBytes(), 2, testStr, dataMap));
        });
}

}
