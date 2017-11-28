package com.ejavashop.vo.order;

import java.io.Serializable;
import java.util.Map;

public class OrderCommitVO implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long           serialVersionUID = 4453809759137522726L;

    private Integer                     memberId;                               // 用户ID
    private Boolean                     isBalancePay;                           // 是否使用余额支付
    // private Boolean        balance;                                // 使用金额（暂时不用，默认使用所有余额）
    private String                      balancePwd;                             // 支付密码
    private Integer                     addressId;                              // 收货地址ID
    private Integer                     invoiceStatus;                          // 发票状态：0、不要发票；1、单位；2个人
    private String                      invoiceTitle;                           // 发票抬头
    private String                      invoiceType;                            // 发票类型（内容），明细、办公用品等
    private String                      paymentCode;                            // 支付方式code   ONLINE: 在线支付（实际支付的时候再修改）；OFFLINE:货到付款
    private String                      paymentName;                            // 支付方式名称
    private String                      ip;                                     // 下单用户的IP地址
    private String                      remark;                                 // 订单备注
    private Integer                     source;                                 // 订单来源：1、pc；2、H5；3、Android；4、IOS
    private Integer                     integral;                               // 订单使用的积分数

    private Map<Integer, OrderCouponVO> sellerCouponMap;                        // 使用商家优惠券信息，key：商家ID，value：优惠券信息

    // 限时抢购、团购提交订单需要字段
    private Integer                     productId;                              // 商品ID
    private Integer                     productGoodsId;                         // 货品ID
    private Integer                     sellerId;                               // 商家ID
    private Integer                     number;                                 // 购买数量

    // 团购活动ID
    private Integer                     actGroupId;                             // 团购ID

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public java.lang.Integer getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(java.lang.Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public java.lang.String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(java.lang.String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Boolean getIsBalancePay() {
        return isBalancePay;
    }

    public void setIsBalancePay(Boolean isBalancePay) {
        this.isBalancePay = isBalancePay;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getBalancePwd() {
        return balancePwd;
    }

    public void setBalancePwd(String balancePwd) {
        this.balancePwd = balancePwd;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Map<Integer, OrderCouponVO> getSellerCouponMap() {
        return sellerCouponMap;
    }

    public void setSellerCouponMap(Map<Integer, OrderCouponVO> sellerCouponMap) {
        this.sellerCouponMap = sellerCouponMap;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductGoodsId() {
        return productGoodsId;
    }

    public void setProductGoodsId(Integer productGoodsId) {
        this.productGoodsId = productGoodsId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getActGroupId() {
        return actGroupId;
    }

    public void setActGroupId(Integer actGroupId) {
        this.actGroupId = actGroupId;
    }

}
