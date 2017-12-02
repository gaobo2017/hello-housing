package com.ejavashop.entity.house;

import java.io.Serializable;
import java.util.Date;

public class HousingReminder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8826236925089199165L;

	private Integer id;

    private Integer leaseId;

    private Integer houseId;

    private Date leaseStartTime;

    private Date leaseEndTime;

    private Integer reminderyWay;

    private Integer reminderyStatus;

    private String operationName;

    private Integer operationId;

    private String remark;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(Integer leaseId) {
        this.leaseId = leaseId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Date getLeaseStartTime() {
        return leaseStartTime;
    }

    public void setLeaseStartTime(Date leaseStartTime) {
        this.leaseStartTime = leaseStartTime;
    }

    public Date getLeaseEndTime() {
        return leaseEndTime;
    }

    public void setLeaseEndTime(Date leaseEndTime) {
        this.leaseEndTime = leaseEndTime;
    }

    public Integer getReminderyWay() {
        return reminderyWay;
    }

    public void setReminderyWay(Integer reminderyWay) {
        this.reminderyWay = reminderyWay;
    }

    public Integer getReminderyStatus() {
        return reminderyStatus;
    }

    public void setReminderyStatus(Integer reminderyStatus) {
        this.reminderyStatus = reminderyStatus;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
}