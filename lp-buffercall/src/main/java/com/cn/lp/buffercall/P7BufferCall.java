package com.cn.lp.buffercall;

/** 7个参数调用
 * @author qirong
 * @date 2019/5/5
 */
public interface P7BufferCall<T, P1, P2, P3, P4, P5, P6, P7> {

    T call(P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7) throws Throwable;

}
