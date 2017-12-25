package com.ejavashop.vo.house;

import java.math.BigDecimal;

import com.ejavashop.entity.house.HousingLease;

public class HousingLeaseVO extends HousingLease {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 600975387901680920L;

    private String            houseName;

    private BigDecimal        allRentSum;

    private BigDecimal        grossProfitSum;

    private BigDecimal        rentIncomeAgainSum;

    private BigDecimal        returnRentCostSum;

    private BigDecimal        pureProfitSum;

    private BigDecimal        renovationCostSum;

    private BigDecimal        otherCostSum;

    private BigDecimal        pricesSum;

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
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

    public BigDecimal getPricesSum() {
        return pricesSum;
    }

    public void setPricesSum(BigDecimal pricesSum) {
        this.pricesSum = pricesSum;
    }

}