package com.cn.lp.buffercall;

/**
 * 4个参数调用
 * @author qirong
 */
public interface P4BufferCall<T, P1, P2, P3, P4> {

    T call(P1 p1, P2 p2, P3 p3, P4 p4) throws Throwable;

}
