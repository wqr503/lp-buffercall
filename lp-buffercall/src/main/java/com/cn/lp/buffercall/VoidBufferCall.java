package com.cn.lp.buffercall;

/**
 * 无参数调用
 * @author qirong
 */
public interface VoidBufferCall<T> {

    T call() throws Throwable;

}
