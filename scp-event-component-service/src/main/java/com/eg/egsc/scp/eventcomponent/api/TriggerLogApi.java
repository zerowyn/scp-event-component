/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.api;

import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.service.base.api.BaseApiController;
import com.eg.egsc.scp.eventcomponent.common.CommonConstant;
import com.eg.egsc.scp.eventcomponent.utils.ValidateParamUtils;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.dto.TriggerLogDto;
import com.eg.egsc.scp.eventcomponent.common.exception.EventComponentException;
import com.eg.egsc.scp.eventcomponent.service.TriggerLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 联动规则日志的API接口类
 *
 * @author chenhao
 * @since 2017-12-11
 */
@RestController
@RequestMapping(value = "/api/triggerlog")
@Api(description = "联动日志")
public class TriggerLogApi extends BaseApiController {

    @Autowired
    TriggerLogService triggerLogServiceImpl;

    /**
     * 新增联动日志信息
     *
     * @param req 需要新增的联动日志信息
     * @return 返回新增结果
     */
    @RequestMapping(value = "/insertTriggerLog", method = RequestMethod.POST)
    @ApiOperation(value = "新增",notes = "新增")
    public ResponseDto saveOrUpdate(@RequestBody @Valid RequestDto<TriggerLogDto> req) {
        ResponseDto res = new ResponseDto();
        TriggerLogDto triggerLogDto = req.getData();
        if(null == triggerLogDto){
            throw new EventComponentException("event.triggerLog.saveOrUpdate.triggerLogDto.null");
        }
        triggerLogDto = triggerLogServiceImpl.saveTriggerLog(triggerLogDto) ;

        res.setMessage("保存联动日志成功！");
        res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        res.setData(triggerLogDto);
        return res;
    }
    
    /**
     * 根据事件日志Id获取联动日志
     *
     * @param eventIds 事件日志Id
     * @return 返回对应的联动日志信息
     */
    @RequestMapping(value = "/{eventIds}",method = RequestMethod.POST)
    @ApiOperation(value = "获取",notes = "获取")
    public ResponseDto getTriggerLogsByEventIds(@PathVariable String eventIds){
        ResponseDto res = new ResponseDto();
        if(StringUtils.isEmpty(eventIds)){
           throw new EventComponentException("event.triggerLog.saveOrUpdate.params.null") ;
        }

        String[] evnetIdArray = eventIds.split(",");
        List<TriggerLogDto> triggerLogDtos = triggerLogServiceImpl.getTriggerLogsByEventIds(Arrays.asList(evnetIdArray));
        res.setData(triggerLogDtos);
        res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        res.setMessage("查询事件联动日志成功");

        return res ;
    }
    
    /**
     * 分页查询联动日志信息
     *
     * @param requestDto 查询条件信息
     * @return 返回的查询结果
     */
    @RequestMapping(value = "/listTriggerLogs",method = RequestMethod.POST)
    @ApiOperation(value = "列表",notes = "列表")
    public ResponseDto getTriggerLogsByCondition(@RequestBody RequestDto<PageQueryDto> requestDto){
        ResponseDto res = new ResponseDto();
        PageQueryDto pageQuery = requestDto.getData();
        if(!ValidateParamUtils.isPageLegal(pageQuery)){
            throw new EventComponentException("event.triggerLog.page.valid");
        }
        res.setData( triggerLogServiceImpl.getTriggerLogPage(pageQuery));
        res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        res.setMessage("查询事件联动日志 成功");

        return res ;
    }

}
