package com.eg.egsc.scp.eventcomponent.utils;

import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.utils.redis.RedisCacheUtils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author HIK
 * @Description
 * @since 2018-01-19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
public class RedisCacheUtilsTest {

    @Autowired
    private RedisCacheUtils redisCacheUtils ;

    @Test
    public void testDeletePrefixKey(){
        redisCacheUtils.set("pre","t");
        redisCacheUtils.deletePrefixKey("pre");
    }

    @Test
    public void testDeleteKey(){
        redisCacheUtils.deleteKey("PREFIX");
    }
}
