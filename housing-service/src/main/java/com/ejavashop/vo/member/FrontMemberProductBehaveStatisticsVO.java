package com.ejavashop.vo.member;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 某产品会员行为统计实体，统计某一个产品的咨询数，评价数，好评数，中评数，差评数，提款申请数，订单数
 *                       
 * @Filename: FrontMemberProductBehaveStatisticsVO.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public class FrontMemberProductBehaveStatisticsVO implements Serializable {
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2940033744746442137L;
    private Integer           productAskCount;                        //咨询数
    private Integer           productCommentsAllCount;                //评价总数
    private Integer           productCommentsHighCount;               //好评数
    private Integer           productCommentsMiddleCount;             //中评数
    private Integer           productCommentsLowCount;                //差评数

    private BigDecimal        productCommentsHighProportion;          //好评占百分比 
    private BigDecimal        productCommentsMiddleProportion;        //中评占百分比 
    private BigDecimal        productCommentsLowProportion;           //差评占百分比 
    private boolean           collectedProduct = false;               //收藏过该商品
    private boolean           collectedShop    = false;               //收藏过该商铺

    public Integer getProductAskCount() {
        return productAskCount;
    }

    public void setProductAskCount(Integer productAskCount) {
        this.productAskCount = productAskCount;
    }

    public Integer getProductCommentsAllCount() {
        return productCommentsAllCount;
    }

    public void setProductCommentsAllCount(Integer productCommentsAllCount) {
        this.productCommentsAllCount = productCommentsAllCount;
    }

    public Integer getProductCommentsHighCount() {
        return productCommentsHighCount;
    }

    public void setProductCommentsHighCount(Integer productCommentsHighCount) {
        this.productCommentsHighCount = productCommentsHighCount;
    }

    public Integer getProductCommentsMiddleCount() {
        return productCommentsMiddleCount;
    }

    public void setProductCommentsMiddleCount(Integer productCommentsMiddleCount) {
        this.productCommentsMiddleCount = productCommentsMiddleCount;
    }

    public Integer getProductCommentsLowCount() {
        return productCommentsLowCount;
    }

    public BigDecimal getProductCommentsHighProportion() {
        return productCommentsHighProportion;
    }

    public void setProductCommentsHighProportion(BigDecimal productCommentsHighProportion) {
        this.productCommentsHighProportion = productCommentsHighProportion;
    }

    public BigDecimal getProductCommentsMiddleProportion() {
        return productCommentsMiddleProportion;
    }

    public void setProductCommentsMiddleProportion(BigDecimal productCommentsMiddleProportion) {
        this.productCommentsMiddleProportion = productCommentsMiddleProportion;
    }

    public BigDecimal getProductCommentsLowProportion() {
        return productCommentsLowProportion;
    }

    public void setProductCommentsLowProportion(BigDecimal productCommentsLowProportion) {
        this.productCommentsLowProportion = productCommentsLowProportion;
    }

    public void setProductCommentsLowCount(Integer productCommentsLowCount) {
        this.productCommentsLowCount = productCommentsLowCount;
    }

    public boolean isCollectedProduct() {
        return collectedProduct;
    }

    public void setCollectedProduct(boolean collectedProduct) {
        this.collectedProduct = collectedProduct;
    }

    public boolean isCollectedShop() {
        return collectedShop;
    }

    public void setCollectedShop(boolean collectedShop) {
        this.collectedShop = collectedShop;
    }

}