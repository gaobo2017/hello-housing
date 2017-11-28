package com.ejavashop.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品销量dto
 *                       
 * @Filename: ProductSaleDto.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public class ProductSaleDto {
    /**
     * 订单状态
     */
    private Integer    orderState;
    /**
     * 订单金额
     */
    private BigDecimal moneyOrder;
    /**
     * 商品名称
     */
    private String     productName;
    /**
     * 货品规格
     */
    private String     specInfo;
    /**
     * 是否启用规格 1-否 2-启用
     */
    private Integer    isNorm;
    /**
     * 付款时间 
     */
    private Date       payTime;

    //按不现的维度统计的数据
    private List<Object> data;

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public BigDecimal getMoneyOrder() {
        return moneyOrder;
    }

    public void setMoneyOrder(BigDecimal moneyOrder) {
        this.moneyOrder = moneyOrder;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSpecInfo() {
        return specInfo;
    }

    public void setSpecInfo(String specInfo) {
        this.specInfo = specInfo;
    }

    public Integer getIsNorm() {
        return isNorm;
    }

    public void setIsNorm(Integer isNorm) {
        this.isNorm = isNorm;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

}
