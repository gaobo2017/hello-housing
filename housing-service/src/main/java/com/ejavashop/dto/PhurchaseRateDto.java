package com.ejavashop.dto;

import java.util.List;

public class PhurchaseRateDto {
    /**
     * 订单数
     */
    private List<Object> orderNumList;
    /**
     * pv量
     */
    private List<Object> pvList;
    /**
     * 购买率
     */
    private List<Object> rateList;

    public List<Object> getOrderNumList() {
        return orderNumList;
    }

    public void setOrderNumList(List<Object> orderNumList) {
        this.orderNumList = orderNumList;
    }

    public List<Object> getPvList() {
        return pvList;
    }

    public void setPvList(List<Object> pvList) {
        this.pvList = pvList;
    }

    public List<Object> getRateList() {
        return rateList;
    }

    public void setRateList(List<Object> rateList) {
        this.rateList = rateList;
    }

}
