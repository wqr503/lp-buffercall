package com.cn.lp.buffercall;

/**
 * 2个参数调用
 * @author qirong
 */
public interface P2BufferCall<T, P1, P2> {

    T call(P1 p1, P2 p2) throws Throwable;

}
