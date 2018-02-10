package com.eg.egsc.scp.eventcomponent.utils;

import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.utils.reflect.ReflectCriteria;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Constructor;

/**
 * @author HIK
 * @Description
 * @since 2018-01-19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
public class ReflectCriteriaTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectCriteriaTest.class) ;

    @Test
    public void testConstruct(){
        try {
            Constructor<ReflectCriteria> ecConstructor = ReflectCriteria.class.getDeclaredConstructor();
            ecConstructor.setAccessible(true);
            ecConstructor.newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Test
    public void testInvokeWithNull(){
        try {
            ReflectCriteria.invoke(null,null,null);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
