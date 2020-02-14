package com.cn.lp.buffercall.annotation;

import java.lang.annotation.*;

/**
 * 缓冲调用生成Key关键字, 如果参数不是基本类型，将调用Object.toString()生成Key，所以如果希望匹配更准确
 * 请重写toString()方法
 * Created by qirong on 2019/5/8.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CallKey {

}
