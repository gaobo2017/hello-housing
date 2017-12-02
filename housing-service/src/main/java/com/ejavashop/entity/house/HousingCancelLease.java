package com.ejavashop.entity.house;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HousingCancelLease implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2346048758546155611L;

	private Integer id;

    private Integer houseId;

    private Integer leaseId;

    private Date leaseStartTime;

    private Date leaseEndTime;

    private BigDecimal rentIncomeAgain;

    private BigDecimal returnRentCost;

    private String seller;

    private String contract;

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

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(Integer leaseId) {
        this.leaseId = leaseId;
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