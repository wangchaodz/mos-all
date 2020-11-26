package com.mos.module.redis.annotation;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @Description: 缓存切面
 * @date: 2020/11/12 10:02
 * @author: wangchaodz@gmail.com
 */
@Aspect
@Component
public class UseCacheAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(UseCacheAspect.class);

    @Autowired
    private RedisTemplate<String, Serializable> lettuceRedisCacheTemplate;


    @Pointcut(value = "@annotation(com.mos.module.redis.annotation.UseCache)")
    public void doAction() {
        //TODO Pointcut是植入Advice的触发条件。
        //TODO 每个Pointcut的定义包括2部分，一是表达式，二是方法签名。
        //TODO 方法签名必须是 public及void型。
        //TODO 可以将Pointcut中的方法看作是一个被Advice引用的助记符，因为表达式不直观，因此我们可以通过方法签名的方式为 此表达式命名。
        //TODO 因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
    }

    @Before("doAction()")
    public void before(JoinPoint point) {
        //TODO 标识一个前置增强方法，相当于BeforeAdvice的功能，相似功能的还有
        LOGGER.info("@Before：模拟权限检查...");
        LOGGER.info("@Before：目标方法为：{}.{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        LOGGER.info("@Before：参数为：{} ", Arrays.toString(point.getArgs()));
        LOGGER.info("@Before：被织入的目标对象为：{}", point.getTarget());

        List<RedisClientInfo> redisClientInfos = lettuceRedisCacheTemplate.getClientList();
        LOGGER.info("@Before：目前有 {} 个redis客户", redisClientInfos == null ? 0 : redisClientInfos.size());
    }

    @Around("doAction()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //TODO 环绕增强，相当于MethodInterceptor
        LOGGER.info("@Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        //if (args != null && args.length > 0 && args[0].getClass() == String.class) {
        //    args[0] = "改变后的参数1";
        //}
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        UseCache anno = method.getAnnotation(UseCache.class);
        String k = point.getSignature().getName() + (args == null ? "" : Arrays.toString(args));

        //逻辑开始-----------拦截----------------------------->
        if (anno.useCache()) {
            if (anno.isDelRelation() && !"".equalsIgnoreCase(anno.relationKey())) {
                LOGGER.info("@Around：删除关联缓存,{}...", anno.relationKey());
                Set<String> keys = lettuceRedisCacheTemplate.keys(anno.relationKey());
                if (keys != null && keys.size() > 0) {
                    lettuceRedisCacheTemplate.delete(keys);
                }
            }
            Serializable v = lettuceRedisCacheTemplate.opsForValue().get(k);
            if (v != null) {
                if (anno.isUpdate()) {
                    lettuceRedisCacheTemplate.opsForValue().set(k, v, anno.expireUpdateTime(), anno.timeUnit());
                }
                LOGGER.info("@Around：缓存命中后,返回缓存值...");
                return new GenericFastJsonRedisSerializer().deserialize((byte[]) v);
            }
        }
        //逻辑结束-----------拦截----------------------------->

        //用改变后的参数执行目标方法
        LOGGER.info("@Around：缓存未命中后,执行目标方法...");
        Object v = point.proceed(args);

        //逻辑开始-----------赋值----------------------------->
        if (anno.useCache() && anno.isUpdate() && v != null) {
            LOGGER.info("@Around：缓存未命中后,存储缓存值...");
            lettuceRedisCacheTemplate.opsForValue().set(k, Objects.requireNonNull(new GenericFastJsonRedisSerializer().serialize(v)), anno.expireTime(), anno.timeUnit());
        }
        //逻辑结束-----------赋值----------------------------->

        LOGGER.info("@Around：执行目标方法之后...");
        LOGGER.info("@Around：被织入的目标对象为：{}", point.getTarget());
        return v;
    }

    @AfterReturning(value = "doAction()", returning = "returnValue")
    public void afterReturning(JoinPoint point, Object returnValue) {
        //TODO 后置增强，相当于AfterReturningAdvice，方法正常退出时执行
        LOGGER.info("@AfterReturning：模拟日志记录功能...");
        LOGGER.info("@AfterReturning：目标方法为：{}.{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        LOGGER.info("@AfterReturning：参数为：{}", Arrays.toString(point.getArgs()));
        LOGGER.info("@AfterReturning：返回值为：{}", returnValue);
        LOGGER.info("@AfterReturning：被织入的目标对象为：{}", point.getTarget());
    }

    @AfterThrowing(value = "doAction()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) { //注解标注的方法抛出的异常
        //TODO 异常抛出增强，相当于ThrowsAdvice
    }

    @After("doAction()")
    public void after(JoinPoint point) {
        //TODO final增强，不管是抛出异常或者正常退出都会执行
        LOGGER.info("@After：模拟释放资源...");
        LOGGER.info("@After：目标方法为：{}.{}", point.getSignature().getDeclaringTypeName(), point.getSignature().getName());
        LOGGER.info("@After：参数为：{}", Arrays.toString(point.getArgs()));
        LOGGER.info("@After：被织入的目标对象为：{}", point.getTarget());
    }

}
