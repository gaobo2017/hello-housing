package com.ejavashop.vo.member;

import com.ejavashop.entity.member.MemberCollectionProduct;

/**
 * 会员收藏商品表
 *
 */
public class FrontMemberCollectionProductVO extends MemberCollectionProduct {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = -2162966049957695231L;
    private Integer              sellerId;                                //商家ID
    private String               productName;                             //产品名称
    private java.math.BigDecimal mallPcPrice;                             //产品 商城价格
    private java.math.BigDecimal mallMobilePrice;                         //产品 商城移动端价格
    private Integer              commentsNumber;                          //产品评价总数
    private String               productLeadLittle;                       //产品主图 小图

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductLeadLittle() {
        return productLeadLittle;
    }

    public void setProductLeadLittle(String productLeadLittle) {
        this.productLeadLittle = productLeadLittle;
    }

    public java.math.BigDecimal getMallPcPrice() {
        return mallPcPrice;
    }

    public void setMallPcPrice(java.math.BigDecimal mallPcPrice) {
        this.mallPcPrice = mallPcPrice;
    }

    public java.math.BigDecimal getMallMobilePrice() {
        return mallMobilePrice;
    }

    public void setMallMobilePrice(java.math.BigDecimal mallMobilePrice) {
        this.mallMobilePrice = mallMobilePrice;
    }

    public Integer getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(Integer commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

}