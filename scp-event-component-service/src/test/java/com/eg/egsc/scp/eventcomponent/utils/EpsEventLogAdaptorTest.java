/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.utils;

import java.lang.reflect.Constructor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.dto.EventLogDto;
import com.eg.egsc.scp.eventcomponent.mapper.entity.EventLog;
import com.eg.egsc.scp.eventcomponent.utils.adaptor.EpsEventLogAdaptor;

/**
 * @author HIK
 * @Description
 * @since 2018-01-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
public class EpsEventLogAdaptorTest {

    private static final Logger logger = LoggerFactory.getLogger(EpsEventLogAdaptorTest.class) ;
    @Test
    public void testToEntityNormal(){
        EpsEventLogAdaptor.toEntity(new EventLog(),new EventLogDto());
    }

    @Test
    public void testToDtoNull(){
        EpsEventLogAdaptor.toDto(new EventLogDto(),null);
    }

    @Test
    public void testToEntityNull(){
        EpsEventLogAdaptor.toEntity(null, new EventLogDto());
    }

    @Test
    public void testToDtoNormal(){
        try {
            Constructor<EpsEventLogAdaptor> constructor = EpsEventLogAdaptor.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        EpsEventLogAdaptor.toDto(new EventLogDto(),new EventLog());
    }

}
