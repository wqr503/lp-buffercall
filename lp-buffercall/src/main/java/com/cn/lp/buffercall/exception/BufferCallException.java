package com.cn.lp.buffercall.exception;

/**
 * 缓冲调用异常
 * @author qirong
 */
public class BufferCallException extends RuntimeException {

    private String callKey;

    public BufferCallException(String callKey) {
        this.callKey = callKey;
    }

    public BufferCallException(String callKey, String message, Exception e) {
        super(message, e);
        this.callKey = callKey;
    }

    public BufferCallException(String callKey, Exception e) {
        super(e);
        this.callKey = callKey;
    }

}