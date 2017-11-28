package com.ejavashop.vo.product;

import java.io.Serializable;
import java.util.List;

import com.ejavashop.entity.promotion.coupon.Coupon;
import com.ejavashop.entity.promotion.flash.ActFlashSale;
import com.ejavashop.entity.promotion.flash.ActFlashSaleProduct;
import com.ejavashop.entity.promotion.flash.ActFlashSaleStage;
import com.ejavashop.entity.promotion.full.ActFull;
import com.ejavashop.entity.promotion.single.ActSingle;

/**
 * 用于商品单品页异步加载活动信息
 * 
 * @Filename: ProductActVO.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public class ProductActVO implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long   serialVersionUID = -2350521208155705102L;

    /** 活动阶段:1、抢购结束，2、正在进行，3、即将开始 */
    public static final int     STAGE_TYPE_1     = 1;
    /** 活动阶段:1、抢购结束，2、正在进行，3、即将开始 */
    public static final int     STAGE_TYPE_2     = 2;
    /** 活动阶段:1、抢购结束，2、正在进行，3、即将开始 */
    public static final int     STAGE_TYPE_3     = 3;

    // 普通商品展示需要信息
    private ActSingle           actSingle;                               // 单品立减活动
    private ActFull             actFull;                                 // 订单满减活动
    private List<Coupon>        couponList;                              // 优惠券list

    // 限时抢购展示需要信息
    private ActFlashSale        actFlashSale;                            // 限时抢购活动
    private ActFlashSaleStage   actFlashSaleStage;                       // 限时抢购活动阶段
    private ActFlashSaleProduct actFlashSaleProduct;                     // 限时抢购活动商品
    private Integer             stageType;                               // 活动阶段:1、抢购结束，2、正在进行，3、即将开始

    public ActSingle getActSingle() {
        return actSingle;
    }

    public void setActSingle(ActSingle actSingle) {
        this.actSingle = actSingle;
    }

    public ActFull getActFull() {
        return actFull;
    }

    public void setActFull(ActFull actFull) {
        this.actFull = actFull;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public ActFlashSale getActFlashSale() {
        return actFlashSale;
    }

    public void setActFlashSale(ActFlashSale actFlashSale) {
        this.actFlashSale = actFlashSale;
    }

    public ActFlashSaleStage getActFlashSaleStage() {
        return actFlashSaleStage;
    }

    public void setActFlashSaleStage(ActFlashSaleStage actFlashSaleStage) {
        this.actFlashSaleStage = actFlashSaleStage;
    }

    public ActFlashSaleProduct getActFlashSaleProduct() {
        return actFlashSaleProduct;
    }

    public void setActFlashSaleProduct(ActFlashSaleProduct actFlashSaleProduct) {
        this.actFlashSaleProduct = actFlashSaleProduct;
    }

    public Integer getStageType() {
        return stageType;
    }

    public void setStageType(Integer stageType) {
        this.stageType = stageType;
    }

}
