/**
 * Copyright 2017-2025 Evergrande Group.
 */
package com.eg.egsc.scp.eventcomponent.dto;

import com.eg.egsc.framework.client.dto.BaseBusinessDto;

import java.util.Date;

/**
 * @Description
 * @Author shiweisen
 * @Date 2018/1/10 16:10
 */
public class EventBusinessDto extends BaseBusinessDto {

    private String uuid;//表记录
    private String courtUuid;//小区UUID
    private Date createTime;//创建时间
    private Date updateTime;//更新时间
    private String createUser;//创建人
    private String updateUser;//修订人

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCourtUuid() {
        return courtUuid;
    }

    public void setCourtUuid(String courtUuid) {
        this.courtUuid = courtUuid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
