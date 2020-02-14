package com.cn.lp.buffercall;

/**
 * 1个参数调用
 * @author qirong
 * @date 2019/5/5
 */
public interface P1BufferCall<T, P1> {

    T call(P1 p1) throws Throwable;

}
