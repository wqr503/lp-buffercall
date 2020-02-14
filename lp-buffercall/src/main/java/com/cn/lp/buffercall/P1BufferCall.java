package com.cn.lp.buffercall;

/**
 * 1个参数调用
 * @author qirong
 */
public interface P1BufferCall<T, P1> {

    T call(P1 p1) throws Throwable;

}
