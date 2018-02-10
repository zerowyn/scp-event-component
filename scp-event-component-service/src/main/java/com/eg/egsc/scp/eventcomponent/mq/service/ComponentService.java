/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.mq.service;

import com.eg.egsc.scp.eventcomponent.service.DictService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangjun
 * @create 2018-01-08 9:32
 * @description：
 */
@Component
public class ComponentService implements CommandLineRunner{

    @Autowired
    private DictService dictServiceImpl;


    @Override
    public void run(String... args) {
        dictServiceImpl.fillTopicEventType();
    }


}
