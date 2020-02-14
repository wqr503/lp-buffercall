package com.cn.lp.buffercall.utils;

import java.util.Collection;

/**
 * 缓冲调用工具
 * @author qirong
 * @date 2019/5/5
 */
public class BufferCallAide {

    public static String createKey(Class<?> clazz, Collection<String> keys) {
        StringBuilder sb = new StringBuilder();
        sb.append(clazz.getSimpleName());
        sb.append("-");
        for (String itemKey : keys) {
            sb.append(itemKey);
            sb.append("-");
        }
        return sb.toString();
    }

    public static String createKey(Class<?> clazz, String key) {
        return clazz.getSimpleName() + "-" + key;
    }

}
