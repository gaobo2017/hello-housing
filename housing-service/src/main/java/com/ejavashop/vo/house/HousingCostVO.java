package com.ejavashop.vo.house;

import java.math.BigDecimal;
import java.util.Date;

import com.ejavashop.entity.house.HousingCost;

public class HousingCostVO extends HousingCost {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6539783751425364594L;

    private Integer           costId;                                 //成本主表id

    private Integer           costType;                               //成本类型

    private BigDecimal        money;                                  //发生金额

    private String            roomCodeNo;                             //房间编号

    private String            houseName;

    private Date              gainTime;

    private Integer           status;

    private Integer           isSold;

    private Date              contractStartTime;

    private Date              contractEndTime;

    private BigDecimal        monthlyRent;

    private BigDecimal        pricesSum;

    private Date              lastSoldTime;

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
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

    public String getRoomCodeNo() {
        return roomCodeNo;
    }

    public void setRoomCodeNo(String roomCodeNo) {
        this.roomCodeNo = roomCodeNo;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public Date getGainTime() {
        return gainTime;
    }

    public void setGainTime(Date gainTime) {
        this.gainTime = gainTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsSold() {
        return isSold;
    }

    public void setIsSold(Integer isSold) {
        this.isSold = isSold;
    }

    public Date getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public BigDecimal getPricesSum() {
        return pricesSum;
    }

    public void setPricesSum(BigDecimal pricesSum) {
        this.pricesSum = pricesSum;
    }

    public Date getLastSoldTime() {
        return lastSoldTime;
    }

    public void setLastSoldTime(Date lastSoldTime) {
        this.lastSoldTime = lastSoldTime;
    }

}