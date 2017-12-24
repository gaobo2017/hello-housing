package com.ejavashop.entity.house;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HousingLease implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -5048823834892861407L;

    private Integer           id;

    private Integer           houseId;

    private String            roomCodeNo;

    private Date              leaseStartTime;

    private Date              leaseEndTime;

    private BigDecimal        rent;

    private BigDecimal        allRent;

    private Integer           status;

    private Integer           payWay;

    private String            seller;

    private String            contract;

    private String            operationName;

    private Integer           operationId;

    private String            remark;

    private BigDecimal        dayRentCost;

    private BigDecimal        dayRentIncome;

    private BigDecimal        grossProfit;

    private Date              createTime;

    private Date              updateTime;

    private Date              finalLeaveTime;

    private BigDecimal        rentIncomeAgain;

    private BigDecimal        returnRentCost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigDecimal getRent() {
        return rent;
    }

    public String getRoomCodeNo() {
        return roomCodeNo;
    }

    public void setRoomCodeNo(String roomCodeNo) {
        this.roomCodeNo = roomCodeNo;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public BigDecimal getAllRent() {
        return allRent;
    }

    public void setAllRent(BigDecimal allRent) {
        this.allRent = allRent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayWay() {
        return payWay;
    }

    public void setPayWay(Integer payWay) {
        this.payWay = payWay;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller == null ? null : seller.trim();
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract == null ? null : contract.trim();
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

    public BigDecimal getDayRentCost() {
        return dayRentCost;
    }

    public void setDayRentCost(BigDecimal dayRentCost) {
        this.dayRentCost = dayRentCost;
    }

    public BigDecimal getDayRentIncome() {
        return dayRentIncome;
    }

    public void setDayRentIncome(BigDecimal dayRentIncome) {
        this.dayRentIncome = dayRentIncome;
    }

    public BigDecimal getGrossProfit() {
        return grossProfit;
    }

    public void setGrossProfit(BigDecimal grossProfit) {
        this.grossProfit = grossProfit;
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

    public Date getFinalLeaveTime() {
        return finalLeaveTime;
    }

    public void setFinalLeaveTime(Date finalLeaveTime) {
        this.finalLeaveTime = finalLeaveTime;
    }

    public BigDecimal getRentIncomeAgain() {
        return rentIncomeAgain;
    }

    public void setRentIncomeAgain(BigDecimal rentIncomeAgain) {
        this.rentIncomeAgain = rentIncomeAgain;
    }

    public BigDecimal getReturnRentCost() {
        return returnRentCost;
    }

    public void setReturnRentCost(BigDecimal returnRentCost) {
        this.returnRentCost = returnRentCost;
    }

}