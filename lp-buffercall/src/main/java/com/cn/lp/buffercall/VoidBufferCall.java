package com.cn.lp.buffercall;

/**
 * 无参数调用
 * @author qirong
 * @date 2019/5/5
 */
public interface VoidBufferCall<T> {

    T call() throws Throwable;

}
