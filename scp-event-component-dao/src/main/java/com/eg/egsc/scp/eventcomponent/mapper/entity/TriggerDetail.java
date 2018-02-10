package com.eg.egsc.scp.eventcomponent.mapper.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class TriggerDetail implements Serializable {
    /**
     * ,主键UUID
     */
    private String uuid;

    /**
     * 联动规则UUID
     */
    private String triggerId;

    /**
     * 联动方法
     */
    private String triggerType;

    /**
     * 联动参数
     */
    private Object triggerParams;

    /**
     * 下一个联动方的联动规则接收队列
     */
    private String to;

    /**
     * 联动顺序
     */
    private Integer order;

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

    private static final long serialVersionUID = 1L;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTriggerId() {
        return triggerId;
    }

    public void setTriggerId(String triggerId) {
        this.triggerId = triggerId;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public Object getTriggerParams() {
        return triggerParams;
    }

    public void setTriggerParams(Object triggerParams) {
        this.triggerParams = triggerParams;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
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
        TriggerDetail other = (TriggerDetail) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getTriggerId() == null ? other.getTriggerId() == null : this.getTriggerId().equals(other.getTriggerId()))
            && (this.getTriggerType() == null ? other.getTriggerType() == null : this.getTriggerType().equals(other.getTriggerType()))
            && (this.getTriggerParams() == null ? other.getTriggerParams() == null : this.getTriggerParams().equals(other.getTriggerParams()))
            && (this.getTo() == null ? other.getTo() == null : this.getTo().equals(other.getTo()))
            && (this.getOrder() == null ? other.getOrder() == null : this.getOrder().equals(other.getOrder()))
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
        result = prime * result + ((getTriggerId() == null) ? 0 : getTriggerId().hashCode());
        result = prime * result + ((getTriggerType() == null) ? 0 : getTriggerType().hashCode());
        result = prime * result + ((getTriggerParams() == null) ? 0 : getTriggerParams().hashCode());
        result = prime * result + ((getTo() == null) ? 0 : getTo().hashCode());
        result = prime * result + ((getOrder() == null) ? 0 : getOrder().hashCode());
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
        sb.append(", triggerId=").append(triggerId);
        sb.append(", triggerType=").append(triggerType);
        sb.append(", triggerParams=").append(triggerParams);
        sb.append(", to=").append(to);
        sb.append(", order=").append(order);
        sb.append(", courtUuid=").append(courtUuid);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}