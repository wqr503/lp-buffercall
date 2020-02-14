package com.cn.lp.buffercall.aop;


import com.cn.lp.buffercall.BufferCallActuator;
import com.cn.lp.buffercall.annotation.BufferCall;
import com.cn.lp.buffercall.annotation.CallKey;
import com.cn.lp.buffercall.exception.BufferCallException;
import com.cn.lp.buffercall.utils.BufferCallAide;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author qirong
 * @date 2019/4/3
 */
@Aspect
@Component
public class BufferCallAOP {

    private static final Logger LOGGER = LoggerFactory.getLogger(BufferCallAOP.class);

    /**
     * 此处的切点是注解的方式，也可以用包名的方式达到相同的效果
     * '@Pointcut("execution(* com.wwj.springboot.service.impl.*.*(..))")'
     */
    @Pointcut("@annotation(com.cn.lp.buffercall.annotation.BufferCall)")
    public void delayInvoke() {

    }

    private String createDelayKey(Object val) {
        if (val == null) {
            return "Object.NULL";
        }
        if (val.getClass().isArray()) {
            if (val instanceof boolean[]) {
                boolean[] objects = (boolean[]) val;
                List<String> dataList = new ArrayList<>();
                for (boolean data : objects) {
                    dataList.add(data + "");
                }
                return dataList.toString();
            } else if (val instanceof byte[]) {
                byte[] objects = (byte[]) val;
                List<String> dataList = new ArrayList<>();
                for (byte data : objects) {
                    dataList.add(data + "");
                }
                return dataList.toString();
            } else if (val instanceof char[]) {
                char[] objects = (char[]) val;
                List<String> dataList = new ArrayList<>();
                for (char data : objects) {
                    dataList.add(data + "");
                }
                return dataList.toString();
            } else if (val instanceof short[]) {
                short[] objects = (short[]) val;
                List<String> dataList = new ArrayList<>();
                for (short data : objects) {
                    dataList.add(data + "");
                }
                return dataList.toString();
            } else if (val instanceof int[]) {
                int[] objects = (int[]) val;
                List<String> dataList = new ArrayList<>();
                for (int data : objects) {
                    dataList.add(data + "");
                }
                return dataList.toString();
            } else if (val instanceof long[]) {
                long[] objects = (long[]) val;
                List<String> dataList = new ArrayList<>();
                for (long data : objects) {
                    dataList.add(data + "");
                }
                return dataList.toString();
            } else if (val instanceof float[]) {
                float[] objects = (float[]) val;
                List<String> dataList = new ArrayList<>();
                for (float data : objects) {
                    dataList.add(data + "");
                }
                return dataList.toString();
            } else if (val instanceof double[]) {
                double[] objects = (double[]) val;
                List<String> dataList = new ArrayList<>();
                for (double data : objects) {
                    dataList.add(data + "");
                }
                return dataList.toString();
            } else {
                Object[] objects = (Object[]) val;
                List<String> dataList = new ArrayList<>();
                for (Object data : objects) {
                    checkParamIsBaseType(data);
                    dataList.add(data.toString());
                }
                return dataList.toString();
            }
        } else if (val instanceof Collection) {
            Collection collection = (Collection) val;
            List<String> dataList = new ArrayList<>();
            for (Object data : collection) {
                checkParamIsBaseType(data);
                dataList.add(data.toString());
            }
            return dataList.toString();
        } else if (val instanceof Map) {
            Map map = (Map) val;
            Set<Map.Entry> entrySet = map.entrySet();
            List<String> dataList = new ArrayList<>();
            for (Map.Entry entry : entrySet) {
                checkParamIsBaseType(entry.getKey());
                checkParamIsBaseType(entry.getValue());
                List<String> valueList = new ArrayList<>();
                valueList.add(entry.getKey().toString());
                valueList.add(entry.getValue().toString());
                dataList.add(valueList.toString());
            }
            return dataList.toString();
        } else {
            checkParamIsBaseType(val);
            return val.toString();
        }
    }

    /**
     * 环绕增强，相当于MethodInterceptor
     */
    @Around("delayInvoke()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String invokeKey = null;
        Long waitTime = null;
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            BufferCall delayInvoke = method.getAnnotation(BufferCall.class);
            Object[] objects = joinPoint.getArgs();
            Annotation[][] annotations = method.getParameterAnnotations();
            List<String> keyList = new ArrayList<>();
            keyList.add(method.getName());
            String[] parameterNames = signature.getParameterNames();
            boolean hasCallKey = false;
            for(int index = 0; index < annotations.length; index ++) {
                if (annotations[index] != null && annotations[index].length > 0) {
                    for (Annotation annotation : annotations[index]) {
                        if (annotation instanceof CallKey) {
                            hasCallKey = true;
                            keyList.add(objects[index].getClass().getSimpleName() + "-" + parameterNames[index]);
                            keyList.add(createDelayKey(objects[index]));
                        }
                    }
                }
            }
            if (!hasCallKey) {
                for(int index = 0; index < objects.length; index ++) {
                    keyList.add(objects[index].getClass().getSimpleName() + "-" + parameterNames[index]);
                    keyList.add(createDelayKey(objects[index]));
                }
            }
            Class<?> objClass = delayInvoke.object() == Object.class ? joinPoint.getTarget().getClass() : delayInvoke.object();
            invokeKey = BufferCallAide.createKey(objClass, keyList);
            waitTime = delayInvoke.waitTime();
        } catch (Exception e) {
            LOGGER.warn("build BufferCallKey error", e);
        }
        if (invokeKey != null && waitTime != null) {
            try {
                return BufferCallActuator.getInstance().execute(invokeKey, waitTime, joinPoint::proceed);
            } catch (BufferCallException e) {
                LOGGER.warn("exec BufferCall error", e);
                return joinPoint.proceed();
            }
        } else {
            return joinPoint.proceed();
        }
    }

    private void checkParamIsBaseType(Object param) {
        if (param instanceof String ||
            param instanceof Boolean ||
            param instanceof Character ||
            param instanceof Short ||
            param instanceof Integer ||
            param instanceof Long ||
            param instanceof Float ||
            param instanceof Double ||
            param instanceof Byte
        ) {

        } else {
            throw new RuntimeException(param.getClass().getSimpleName() + "不是基本类型");
        }
    }

}
