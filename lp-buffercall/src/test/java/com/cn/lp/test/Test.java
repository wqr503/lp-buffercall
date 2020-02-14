package com.cn.lp.test;

import com.cn.lp.buffercall.BufferCallActuator;
import com.cn.lp.buffercall.utils.BufferCallAide;

/**
 * Created by qirong on 2019/5/8.
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();
        String callKey = BufferCallAide.createKey(Test.class, "doTest");
        String data = BufferCallActuator.getInstance().speedyExecute(callKey, test::doTest);
        System.out.println(data);
    }

    public String doTest() {
        return "hello world";
    }

}
