/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.utils;

import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.dto.TriggerLogDto;
import com.eg.egsc.scp.eventcomponent.mapper.entity.TriggerLog;
import com.eg.egsc.scp.eventcomponent.utils.adaptor.TriggerLogAdaptor;
import com.eg.egsc.scp.eventcomponent.utils.reflect.Tool;

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
 * @since 2018-01-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
public class TriggerLogAdaptorTest {

    private static final Logger logger = LoggerFactory.getLogger(TriggerLogAdaptorTest.class) ;
    @Test
    public void testToEntityNormal(){
        TriggerLogAdaptor.toEntity(new TriggerLogDto(),new TriggerLog());
    }

    @Test
    public void testToDtoNull(){
        TriggerLogAdaptor.toDto(new TriggerLogDto(),new TriggerLog());
    }

    @Test
    public void testToEntityNull(){
        TriggerLogAdaptor.toEntity(new TriggerLogDto(),null);
    }

    @Test
    public void testToDtoNormal(){
        try {
            Constructor<TriggerLogAdaptor> constructor = TriggerLogAdaptor.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();

            Constructor<Tool> toolConstructor = Tool.class.getDeclaredConstructor();
            toolConstructor.setAccessible(true);
            toolConstructor.newInstance();

            Constructor<ValidateParamUtils> validateParamUtilsConstructor = ValidateParamUtils.class.getDeclaredConstructor();
            validateParamUtilsConstructor.setAccessible(true);
            validateParamUtilsConstructor.newInstance();

            Constructor<EcStringUtils> ecConstructor = EcStringUtils.class.getDeclaredConstructor();
            ecConstructor.setAccessible(true);
            ecConstructor.newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        EcStringUtils.generateUuid();
        TriggerLogAdaptor.toDto(new TriggerLogDto(),null);
    }

}
