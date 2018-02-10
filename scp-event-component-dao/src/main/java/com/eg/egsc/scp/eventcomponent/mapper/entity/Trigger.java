package com.eg.egsc.scp.eventcomponent.mapper.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Trigger implements Serializable {
    private static final long serialVersionUID = 8313368161858446145L;
    /**
     * ,主键UUID
     */
    private String uuid;

    /**
     * 事件类型
     */
    private Integer eventType;

    /**
     * 事件源编号
     */
    private String eventSourceCode;

    /**
     * 时序性，true : 是, false : 否
     */
    private Boolean isSequential;

    /**
     * 创建者编号
     */
    private String creator;

    /**
     * 小区UUID
     */
    private String courtUuid;

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

    /**
     * 创建时间
     */
    private Date createTime;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public String getEventSourceCode() {
        return eventSourceCode;
    }

    public void setEventSourceCode(String eventSourceCode) {
        this.eventSourceCode = eventSourceCode;
    }

    public Boolean getIsSequential() {
        return isSequential;
    }

    public void setIsSequential(Boolean isSequential) {
        this.isSequential = isSequential;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCourtUuid() {
        return courtUuid;
    }

    public void setCourtUuid(String courtUuid) {
        this.courtUuid = courtUuid;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        Trigger other = (Trigger) that;
        return (this.getUuid() == null ? other.getUuid() == null : this.getUuid().equals(other.getUuid()))
            && (this.getEventType() == null ? other.getEventType() == null : this.getEventType().equals(other.getEventType()))
            && (this.getEventSourceCode() == null ? other.getEventSourceCode() == null : this.getEventSourceCode().equals(other.getEventSourceCode()))
            && (this.getIsSequential() == null ? other.getIsSequential() == null : this.getIsSequential().equals(other.getIsSequential()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCourtUuid() == null ? other.getCourtUuid() == null : this.getCourtUuid().equals(other.getCourtUuid()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getUpdateUser() == null ? other.getUpdateUser() == null : this.getUpdateUser().equals(other.getUpdateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUuid() == null) ? 0 : getUuid().hashCode());
        result = prime * result + ((getEventType() == null) ? 0 : getEventType().hashCode());
        result = prime * result + ((getEventSourceCode() == null) ? 0 : getEventSourceCode().hashCode());
        result = prime * result + ((getIsSequential() == null) ? 0 : getIsSequential().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCourtUuid() == null) ? 0 : getCourtUuid().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getUpdateUser() == null) ? 0 : getUpdateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", uuid=").append(uuid);
        sb.append(", eventType=").append(eventType);
        sb.append(", eventSourceCode=").append(eventSourceCode);
        sb.append(", isSequential=").append(isSequential);
        sb.append(", creator=").append(creator);
        sb.append(", courtUuid=").append(courtUuid);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", updateUser=").append(updateUser);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}