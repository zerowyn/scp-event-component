/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.utils.redis;

import com.eg.egsc.scp.eventcomponent.common.exception.EventComponentException;
import com.eg.egsc.scp.eventcomponent.utils.redis.annotation.RedisCacheEvict;
import com.eg.egsc.scp.eventcomponent.utils.redis.annotation.RedisCachePut;
import com.eg.egsc.scp.eventcomponent.utils.redis.annotation.RedisCacheable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author wangjun
 * @create 2017-12-26 15:54
 * @description：
 */
@Aspect
@Component
public class RedisCacheAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheAspect.class);

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    private static final String REDIS_CONN_EXCEPTION = "redis connection exception";

    @Around("@annotation(com.eg.egsc.scp.eventcomponent.utils.redis.annotation.RedisCacheable)")
    public Object cacheable(ProceedingJoinPoint pjp){
        Object obj = null;
        Method method = getMethod(pjp);
        RedisCacheable redisCacheable = method.getAnnotation(RedisCacheable.class);
        String key = getCacheKey(redisCacheable.key(), redisCacheable.value(), method, pjp.getArgs());
        long expire = redisCacheable.expire();

        try {
            obj = redisCacheUtils.get(key);
        }catch (Exception e){
            LOGGER.warn(REDIS_CONN_EXCEPTION+": {}",e);
        }

        if(!ObjectUtils.isEmpty(obj)){
            LOGGER.info("before method:{} cacheable get cache key:{} data:{}", new Object[]{method.getName(), key, obj});
            return obj;
        }else{
            try{
               obj = pjp.proceed();
            }catch (Throwable e){
                LOGGER.warn("redis cacheable aspect proceed exception:{}",e);
                throw new EventComponentException(e.getMessage());
            }

            if(!ObjectUtils.isEmpty(obj)){
                try{
                    if(expire == -1L){
                        redisCacheUtils.set(key,obj);
                    }else{
                        redisCacheUtils.set(key,obj,expire);
                    }
                }catch (Exception e){
                    LOGGER.warn(REDIS_CONN_EXCEPTION+" {}",e);
                }
                LOGGER.info("after method:{} cacheable set cache key:{} data:{} exipre:{}",new Object[]{method.getName(),key,obj,expire});
            }
        }

        return obj;
    }

    @Around("@annotation(com.eg.egsc.scp.eventcomponent.utils.redis.annotation.RedisCachePut)")
    public Object cachePut(ProceedingJoinPoint pjp){
        Object obj = null;
        Method method = getMethod(pjp);
        RedisCachePut redisCachePut = method.getAnnotation(RedisCachePut.class);
        String key = getCacheKey(redisCachePut.key(), redisCachePut.value(), method, pjp.getArgs());
        long expire = redisCachePut.expire();

        try {
            obj = pjp.proceed();
        } catch (Throwable var11) {
            LOGGER.warn("redis cacheput aspect procees exception : {}",var11);
            throw new EventComponentException(var11.getMessage());
        }
        if(obj != null){
            try{
                if(expire == -1L){
                    redisCacheUtils.set(key,obj);
                }else {
                    redisCacheUtils.set(key,obj,expire);
                }
            }catch (Exception var10){
                LOGGER.warn(REDIS_CONN_EXCEPTION+": {}",var10);
            }

            LOGGER.info("after method:{} cachePut set cache key:{} data:{} exipre:{}", new Object[]{method.getName(), key, obj, expire});
        }

        return obj;
    }


    @Around("@annotation(com.eg.egsc.scp.eventcomponent.utils.redis.annotation.RedisCacheEvict)")
    public void cacheEvict(ProceedingJoinPoint pjp){
        Method method = getMethod(pjp);
        RedisCacheEvict cacheEvict = method.getAnnotation(RedisCacheEvict.class);
        String key = getCacheKey(cacheEvict.key(), cacheEvict.value(), method, pjp.getArgs());
        String value = cacheEvict.value();
        boolean beforeInvocation = cacheEvict.beforeInvoation();
        boolean allEntries = cacheEvict.allEntries();

        if(beforeInvocation){
            try{
                clear(key,value,allEntries);
            }catch (Exception var11){
                LOGGER.warn(REDIS_CONN_EXCEPTION+": {}",var11);
             }
            LOGGER.info("before method:{} cacheEvict clear cache key:{} ",method.getName(),key);
        }

        try{
            pjp.proceed();
        }catch (Throwable var10){
            LOGGER.warn("redis cacheevict aspect proceed exception:{}", var10);
            throw new EventComponentException(var10.getMessage());
        }

        LOGGER.info("after method:{} cacheEvict clear cache key:{} ", method.getName(), key);
        if(!beforeInvocation){
            try{
                clear(key,value,allEntries);
            } catch (Exception vat9){
                LOGGER.warn(REDIS_CONN_EXCEPTION+" : {}",vat9);
            }
        }

    }

    private void clear(String key,String value,boolean allEntries){
        if(allEntries){
           redisCacheUtils.deletePrefixKey(value);
        }else{
            redisCacheUtils.deleteKey(key);
        }
    }

    private Method getMethod(ProceedingJoinPoint pjp){
        Object[] args = pjp.getArgs();
        Class[] argTypes = new Class[args.length];

        for(int i = 0;i<args.length;i++){
            if(args[i]!=null) {
                //将传入Arraylist转为List
                if(args[i] instanceof List){
                    argTypes[i] = args[i].getClass().getInterfaces()[0];
                    continue;
                }
                argTypes[i] = args[i].getClass();
            }
        }
        Method method = null;
        try {
            method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
            return method;
        } catch (NoSuchMethodException var6) {
            LOGGER.warn("redis cacheable aspect no such method exception:{}", var6);
            throw new EventComponentException(var6.getMessage());
        } catch (SecurityException var7) {
            LOGGER.warn("redis cacheable aspect method security exception:{}", var7);
            throw new EventComponentException(var7.getMessage());
        }
    }

    private String getCacheKey(String key,String value,Method method,Object[] args ){
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] parameterNames = u.getParameterNames(method);
        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for(int i = 0;i<parameterNames.length;i++){
            context.setVariable(parameterNames[i],args[i]);
        }
        return value + "." + parser.parseExpression(key).getValue(context,String.class);
    }
}
