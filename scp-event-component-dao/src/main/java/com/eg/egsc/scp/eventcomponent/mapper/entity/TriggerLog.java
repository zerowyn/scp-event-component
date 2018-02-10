package com.eg.egsc.scp.eventcomponent.mapper.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class TriggerLog  {
    /**
     * 表记录,主键UUID
     */
    private String uuid;

    /**
     * 联动日志编号,UUID
     */
    private String triggerLogId;

    /**
     * 联动方式
     */
    private String triggerType;

    /**
     * 联动结果
     */
    private Object triggerResult;

    /**
     *  联动顺序，从1开始
     */
    private Short order;

    /**
     * 联动时间
     */
    private Date triggerTime;

    /**
     * 联动方
     */
    private String creator;

    /**
     * 重试次数
     */
    private Integer retryCount;

    /**
     * 关联事件日志编号
     */
    private String eventLogId;

    /**
     * 小区UUID
     */
    private String courtUuid;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修订人
     */
    private String updateUser;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTriggerLogId() {
        return triggerLogId;
    }

    public void setTriggerLogId(String triggerLogId) {
        this.triggerLogId = triggerLogId;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public Object getTriggerResult() {
        return triggerResult;
    }

    public void setTriggerResult(Object triggerResult) {
        this.triggerResult = triggerResult;
    }

    public Short getOrder() {
        return order;
    }

    public void setOrder(Short order) {
        this.order = order;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public String getEventLogId() {
        return eventLogId;
    }

    public void setEventLogId(String eventLogId) {
        this.eventLogId = eventLogId;
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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TriggerLog other = (TriggerLog) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getTriggerLogId() == null ? other.getTriggerLogId() == null : this.getTriggerLogId().equals(other.getTriggerLogId()))
            && (this.getTriggerType() == null ? other.getTriggerType() == null : this.getTriggerType().equals(other.getTriggerType()))
            && (this.getTriggerResult() == null ? other.getTriggerResult() == null : this.getTriggerResult().equals(other.getTriggerResult()))
            && (this.getOrder() == null ? other.getOrder() == null : this.getOrder().equals(other.getOrder()))
            && (this.getTriggerTime() == null ? other.getTriggerTime() == null : this.getTriggerTime().equals(other.getTriggerTime()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getRetryCount() == null ? other.getRetryCount() == null : this.getRetryCount().equals(other.getRetryCount()))
            && (this.getEventLogId() == null ? other.getEventLogId() == null : this.getEventLogId().equals(other.getEventLogId()))
            && (this.getCourtUuid() == null ? other.getCourtUuid() == null : this.getCourtUuid().equals(other.getCourtUuid()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getTriggerLogId() == null) ? 0 : getTriggerLogId().hashCode());
        result = prime * result + ((getTriggerType() == null) ? 0 : getTriggerType().hashCode());
        result = prime * result + ((getTriggerResult() == null) ? 0 : getTriggerResult().hashCode());
        result = prime * result + ((getOrder() == null) ? 0 : getOrder().hashCode());
        result = prime * result + ((getTriggerTime() == null) ? 0 : getTriggerTime().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getRetryCount() == null) ? 0 : getRetryCount().hashCode());
        result = prime * result + ((getEventLogId() == null) ? 0 : getEventLogId().hashCode());
        result = prime * result + ((getCourtUuid() == null) ? 0 : getCourtUuid().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", triggerLogId=").append(triggerLogId);
        sb.append(", triggerType=").append(triggerType);
        sb.append(", triggerResult=").append(triggerResult);
        sb.append(", order=").append(order);
        sb.append(", triggerTime=").append(triggerTime);
        sb.append(", creator=").append(creator);
        sb.append(", retryCount=").append(retryCount);
        sb.append(", eventLogId=").append(eventLogId);
        sb.append(", courtUuid=").append(courtUuid);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append("]");
        return sb.toString();
    }
}