package com.ejavashop.entity.house;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HousingCost implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1299882729919626209L;

	private Integer id;

    private Integer houseId;

    private BigDecimal renovationCostSum;

    private BigDecimal otherCostSum;

    private BigDecimal dayRentCost;

    private Integer vacancyDays;

    private Integer vacancyDay;

    private BigDecimal vacancyFeeSumt;

    private BigDecimal pricesSum;

    private BigDecimal allCostSum;

    private Date lastSoldTime;

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

    public BigDecimal getRenovationCostSum() {
        return renovationCostSum;
    }

    public void setRenovationCostSum(BigDecimal renovationCostSum) {
        this.renovationCostSum = renovationCostSum;
    }

    public BigDecimal getOtherCostSum() {
        return otherCostSum;
    }

    public void setOtherCostSum(BigDecimal otherCostSum) {
        this.otherCostSum = otherCostSum;
    }

    public BigDecimal getDayRentCost() {
        return dayRentCost;
    }

    public void setDayRentCost(BigDecimal dayRentCost) {
        this.dayRentCost = dayRentCost;
    }

    public Integer getVacancyDays() {
        return vacancyDays;
    }

    public void setVacancyDays(Integer vacancyDays) {
        this.vacancyDays = vacancyDays;
    }

    public Integer getVacancyDay() {
        return vacancyDay;
    }

    public void setVacancyDay(Integer vacancyDay) {
        this.vacancyDay = vacancyDay;
    }

    public BigDecimal getVacancyFeeSumt() {
        return vacancyFeeSumt;
    }

    public void setVacancyFeeSumt(BigDecimal vacancyFeeSumt) {
        this.vacancyFeeSumt = vacancyFeeSumt;
    }

    public BigDecimal getPricesSum() {
        return pricesSum;
    }

    public void setPricesSum(BigDecimal pricesSum) {
        this.pricesSum = pricesSum;
    }

    public BigDecimal getAllCostSum() {
        return allCostSum;
    }

    public void setAllCostSum(BigDecimal allCostSum) {
        this.allCostSum = allCostSum;
    }

    public Date getLastSoldTime() {
        return lastSoldTime;
    }

    public void setLastSoldTime(Date lastSoldTime) {
        this.lastSoldTime = lastSoldTime;
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