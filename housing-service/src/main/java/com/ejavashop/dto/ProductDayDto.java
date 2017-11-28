package com.ejavashop.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品每日统计
 *                       
 * @Filename: ProductDayDto.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public class ProductDayDto implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5547196797172780119L;

    private Integer           productId;
    private String            productName;
    private Integer           sellerId;
    private String            orderDay;
    private BigDecimal        moneyAmount;
    private Integer           number;

    private String            sellerName;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(String orderDay) {
        this.orderDay = orderDay;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

}
