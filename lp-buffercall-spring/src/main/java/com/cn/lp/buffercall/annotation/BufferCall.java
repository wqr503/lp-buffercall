package com.cn.lp.buffercall.annotation;

import java.lang.annotation.*;

/**
 * Created by qirong on 2019/5/8.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface BufferCall {

    /**
     * 关联Class
     * @return
     */
    Class<?> object() default Object.class;

    /**
     * 等待时间 （小于等于0则表示无）
     * @return
     */
    long waitTime() default -1;

}
