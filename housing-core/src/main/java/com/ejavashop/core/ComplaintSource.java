package com.ejavashop.core;

/**
 * 投诉来源
 *                       
 * @Filename: ComplaintSource.java
 * @Version: 1.0
 * @Author: 陈万海
 *
 */
public enum ComplaintSource {

    PRODUCT_BACK("[用户退货]"), PRODUCT_EXCHANGE("[用户换货]"), UNKNOWN("未知");

    private String value;

    private ComplaintSource(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
