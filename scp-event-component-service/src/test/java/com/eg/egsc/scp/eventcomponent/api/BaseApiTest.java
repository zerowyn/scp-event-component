/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.api;

import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * @Description
 * @Author shiweisen
 * @Date 2018/1/10 21:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
@Transactional
@Rollback
public class BaseApiTest {

    private static final Logger logger = LoggerFactory.getLogger(EventLogApiTest.class);

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void setupTest() {
        logger.info("init test");
    }

    public void mockMvcPost(Object requestDto,String url){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String dto = objectMapper.writeValueAsString(requestDto);
            RequestBuilder request = MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
                    .content(dto.getBytes());
            mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    public void mockMvcPostError(Object requestDto,String url){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String dto = objectMapper.writeValueAsString(requestDto);
            RequestBuilder request = MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
                    .content(dto.getBytes());
            mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isInternalServerError()).andDo(MockMvcResultHandlers.print())
                    .andReturn();
        }catch (Exception e) {
            logger.error(e.getMessage());
        }

    }
}
