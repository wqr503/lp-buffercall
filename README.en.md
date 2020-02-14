# lp-buffercall

#### Description
Aggregate multiple requests for a single call, Usage scenarios :
1. Paging query
2. Sharing the cache pressure, cache failure can lead to a large number of requests to downstream services, leading to avalanches, buffering calls can effectively peak-clipping

#### Software Architecture
1. lp-buffercall the core logic of the buffercall
2. lp-buffercall-spring Use BufferCall based on annotations

#### call the process
![image.png](https://upload-images.jianshu.io/upload_images/17317532-c1a44a6ef3e47757.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### Key Annotations

    @BufferCall - use on buffercall method

    @CallKey - use on buffercall method params to create the method only key

#### Attention
1. The buffercall returns the same object, modify object must carefully
2. if method params not has @CallKey annotation, method only key will Built from all method parameters, so @CallKey annotation not must need

#### demo
```
@Component
public class TestService {

    @BufferCall
    public String testCallOneCallKey(@CallKey String name) {
        return name + "- Hello !";
    }
    
    @BufferCall
    public String testCallNoCallKey(String name) {
        return name + "- Hello !";
    }

}
```


