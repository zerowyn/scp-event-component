package com.eg.egsc.scp.eventcomponent.client;

import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.scp.eventcomponent.EventComponentServiceApplication;
import com.eg.egsc.scp.eventcomponent.dto.CommonECDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerLogDto;
import com.eg.egsc.scp.eventcomponent.service.InstanceClassTest;
import com.eg.egsc.scp.eventcomponent.service.TriggerLogService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjun
 * @since 2018-01-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EventComponentServiceApplication.class })
public class TriggerLogClientTest {

    @Autowired
    private TriggerLogClient triggerLogClientImpl;

    @Autowired
    private TriggerLogService triggerLogServiceImpl;

    /**
     * 测试数据库已存在该数据情况下的保存方法，实际是更新数据
     * 根据triggerLogId查询数据：121231232
     */
    @Test
    public void saveOrUpdate() {
        TriggerLogDto triggerLogDto = InstanceClassTest.getInstance().newTriggerLogDto();
        triggerLogClientImpl.saveOrUpdate(triggerLogDto);
        triggerLogDto.setUpdateUser("UpdateClient");
        triggerLogServiceImpl.deleteTriggerLogById(triggerLogDto.getUuid());

    }

    /**
     * 测试数据库不存在该数据情况下的保存方法
     * 保证triggerLogId是数据库中没有的
     */
    @Test
    public void testSave() {
        TriggerLogDto triggerLogDto = InstanceClassTest.getInstance().newTriggerLogDto();
        triggerLogClientImpl.saveOrUpdate(triggerLogDto);
        triggerLogServiceImpl.deleteTriggerLogById(triggerLogDto.getUuid());
    }

    /**
     * 测试根据eventIds查询triggerLog方法
     */
    @Test
    public void getTriggerLogsByEventIds() {
        CommonECDto commonECDto = new CommonECDto();
        Map<String, Object> map = new HashMap<>();
        map.put("eventIds","7629d0f286e24dd6bf59b35a07449d66");
        commonECDto.setMap(map);
        ResponseDto responseDto = triggerLogClientImpl.getTriggerLogByEventIds(commonECDto);
        Assert.assertEquals("00000",responseDto.getCode());

    }

    /**
     * 测试分页查询
     */
    @Test
    public void getTriggerLogsByCondition() {
        PageQueryDto pageQueryDto = new PageQueryDto();
        pageQueryDto.setPageNo(1);
        pageQueryDto.setPageSize(10);
        triggerLogClientImpl.getTriggerLogByCondition(pageQueryDto);
    }

}
