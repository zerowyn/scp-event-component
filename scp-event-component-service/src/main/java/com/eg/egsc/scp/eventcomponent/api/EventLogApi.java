/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.api;


import com.eg.egsc.framework.client.dto.RequestDto;
import com.eg.egsc.framework.client.dto.ResponseDto;
import com.eg.egsc.framework.paging.PageInfo;
import com.eg.egsc.framework.service.base.api.BaseApiController;
import com.eg.egsc.scp.eventcomponent.common.CommonConstant;
import com.eg.egsc.scp.eventcomponent.common.exception.EventComponentException;
import com.eg.egsc.scp.eventcomponent.dto.EventLogDto;
import com.eg.egsc.scp.eventcomponent.dto.PageQueryDto;
import com.eg.egsc.scp.eventcomponent.service.EventLogService;
import com.eg.egsc.scp.eventcomponent.utils.ValidateParamUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 事件日志的API接口类
 *
 * @author xulei
 * @since 2017-12-08
 */
@RestController
@RequestMapping(value = "/api/eventlog")
@Api(description = "事件日志")
public class EventLogApi extends BaseApiController {
    @Autowired
    private EventLogService eventLogServiceImpl;

    /**
     * 分页查询，根据查询条件查询事件日志
     *
     * @param req 查询事件日志的查询条件
     * @return 返回事件日志的查询结果
     */
    @RequestMapping(value = "/getEventLog", method = RequestMethod.POST)
    @ApiOperation(value = "列表",notes = "列表")
    public ResponseDto selectLog(@RequestBody RequestDto<PageQueryDto> req) {
        PageQueryDto reqDto= req.getData();
        ResponseDto res = new ResponseDto();
        if(!ValidateParamUtils.isPageLegal(reqDto)){
            throw new EventComponentException("event.eventLog.page.valid");
        }
        PageInfo<EventLogDto> result = eventLogServiceImpl.selectByExampleWithRowbounds(reqDto);
        res.setData(result);
        res.setMessage("数据查询成功");
        res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        return res;
    }

    /**
     * 事件日志新增数据
     *
     * @param req 要保存的事件日志
     * @return 返回保存结果
     */
    @RequestMapping(value = "/insertEventLog", method = RequestMethod.POST)
    @ApiOperation(value = "新增",notes = "新增")
    public ResponseDto saveLog(@RequestBody @Valid RequestDto<EventLogDto> req) {
        ResponseDto res = new ResponseDto();
        EventLogDto reqDto = req.getData();

        eventLogServiceImpl.insert(reqDto);
        res.setMessage("保存成功");
        res.setCode(CommonConstant.RESPONSE_CODE_SUCCESS);
        return res;
    }

}
