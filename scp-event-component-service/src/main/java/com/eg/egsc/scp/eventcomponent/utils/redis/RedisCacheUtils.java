/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.utils.redis;

import com.eg.egsc.common.component.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;

/**
 * @author wangjun
 * @create 2017-12-26 15:40
 * @description：
 */
@Component
public class RedisCacheUtils extends RedisUtils{

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void deletePrefixKey(String prefix){
        Set keys = redisTemplate.keys(prefix + "*");
        Iterator iterator = keys.iterator();

        while(iterator.hasNext()) {
          String k = (String)iterator.next();
            redisTemplate.delete(k);
        }
    }

    public void deleteKey(String key){
        if(key==null){
            return ;
        }
        del(key);
    }

}
