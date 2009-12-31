package com.ejavashop.entity.house;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HousingCostDetail implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1506851343261830419L;

    private Integer           id;

    private Integer           costId;

    private String            roomCodeNo;

    private Integer           houseId;

    private Integer           costType;

    private BigDecimal        money;

    private String            operationName;

    private Integer           operationId;

    private String            remark;

    private Date              createTime;

    private Date              updateTime;

    public Integer getId() {
        return id;
    }

    public String getRoomCodeNo() {
        return roomCodeNo;
    }

    public void setRoomCodeNo(String roomCodeNo) {
        this.roomCodeNo = roomCodeNo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getCostType() {
        return costType;
    }

    public void setCostType(Integer costType) {
        this.costType = costType;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
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