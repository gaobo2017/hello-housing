package com.ejavashop.entity.house;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HousingIncome implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8010947957294183267L;

	private Integer id;

    private Integer houseId;

    private BigDecimal allRentSum;

    private BigDecimal grossProfitSum;

    private BigDecimal rentIncomeAgainSum;

    private BigDecimal returnRentCostSum;

    private BigDecimal pureProfitSum;

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

    public BigDecimal getAllRentSum() {
        return allRentSum;
    }

    public void setAllRentSum(BigDecimal allRentSum) {
        this.allRentSum = allRentSum;
    }

    public BigDecimal getGrossProfitSum() {
        return grossProfitSum;
    }

    public void setGrossProfitSum(BigDecimal grossProfitSum) {
        this.grossProfitSum = grossProfitSum;
    }

    public BigDecimal getRentIncomeAgainSum() {
        return rentIncomeAgainSum;
    }

    public void setRentIncomeAgainSum(BigDecimal rentIncomeAgainSum) {
        this.rentIncomeAgainSum = rentIncomeAgainSum;
    }

    public BigDecimal getReturnRentCostSum() {
        return returnRentCostSum;
    }

    public void setReturnRentCostSum(BigDecimal returnRentCostSum) {
        this.returnRentCostSum = returnRentCostSum;
    }

    public BigDecimal getPureProfitSum() {
        return pureProfitSum;
    }

    public void setPureProfitSum(BigDecimal pureProfitSum) {
        this.pureProfitSum = pureProfitSum;
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