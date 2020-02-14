package com.cn.lp.test;

import com.cn.lp.buffercall.annotation.BufferCall;
import com.cn.lp.buffercall.annotation.CallKey;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by qirong on 2019/5/10.
 */
@Component
public class TestService {

    @BufferCall(waitTime = 100)
    public String test(String name, byte[] bytes, Integer data, String[] testStr, Map<String, String> dataMap) {
//        return name + "- Hello !";
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "123";
    }

}
