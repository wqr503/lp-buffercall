package com.cn.lp.buffercall;

/**
 * 6个参数调用
 * @author qirong
 */
public interface P6BufferCall<T, P1, P2, P3, P4, P5, P6> {

    T call(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6) throws Throwable;

}
