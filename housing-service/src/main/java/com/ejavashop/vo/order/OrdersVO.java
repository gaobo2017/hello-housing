package com.ejavashop.vo.order;

import com.ejavashop.entity.order.Orders;

public class OrdersVO extends Orders {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6132883783258672974L;
    private String            createTimeStr;
    private String            sellerName;

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}