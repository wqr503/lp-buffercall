# lp-buffercall

java脑洞-缓冲调用

## 描述文档
[简书： java 脑洞 - 缓冲调用 开源模块](https://www.jianshu.com/p/985b4e53deec)

## 项目结构
1. lp-buffercall 缓冲调用的核心逻辑
2. lp-buffercall-spring 缓冲调用加上spring的AOP实现注解调用

## 脑洞由来
场景一：分页查询，某商品首页展示

场景二：分担缓存压力，缓存失效会导致大量请求去到下流服务，导致雪崩，缓冲调用能有效削峰


## 功能描述
同一时刻多条线程携带同一参数调用同一接口，把调用过程包装成FutureTask，每条线程通过获取到的FutureTask获得返回值
![image.png](https://upload-images.jianshu.io/upload_images/17317532-c1a44a6ef3e47757.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

## PS:
缓冲调用返回的对象是同一个对象，在使用时需要注意

## 调用示例
```
@Component
public class TestService {

    @BufferCall
    public String test(@CallKey String name) {
        return name + "- Hello !";
    }

}
```
## PS：
 @CallKey 如果没有则默认全参数