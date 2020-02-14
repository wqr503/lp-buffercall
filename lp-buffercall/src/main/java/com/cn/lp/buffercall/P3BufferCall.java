package com.cn.lp.buffercall;

/**
 * 3个参数调用
 * @author qirong
 * @date 2019/5/5
 */
public interface P3BufferCall<T, P1, P2, P3> {

    T call(P1 p1, P2 p2, P3 p3) throws Throwable;

}
