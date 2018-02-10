/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.dto;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;

import java.util.Date;

/**
 * Created by xulei on 2017/12/11.
 * 接受删除参数的对象
 */
public class DeleteEventDto extends BaseBusinessDto {
    private Date startTime;//事件开始时间
    private Date endTime;//事件结束时间

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
