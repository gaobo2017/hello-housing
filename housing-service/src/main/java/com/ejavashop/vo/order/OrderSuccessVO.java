package com.ejavashop.vo.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.ejavashop.entity.order.Orders;

public class OrderSuccessVO implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -968936602944172666L;

    private List<Orders> ordersList;      // 子订单列表
    private String       relationOrderSn; // 所有关联订单编号（拆分后订单的orderSn连接字符串），用英文逗号（,）连接
    private BigDecimal   payAmount;       // 订单总金额  商品总额+总运费-账户支付金额-优惠金额-积分抵用金额
    private Boolean      isBanlancePay;   // 是否使用余额支付
    private String       balancePwd;      // 支付密码
    private String       paymentCode;     // 支付代码
    private String       paymentName;     // 支付名称
    private String       paySessionstr;   // 支付随机码，跳到支付页面时用到，防二次提交
    private Boolean      isPaid;          // 是否已支付（主要是在用余额支付的情况，余额足够支付所有订单的金额，下单直接扣除余额付款成功）
    private Boolean      goJumpPayfor;    // 是否跳转到支付页面

    private BigDecimal payOrderAllsVO;     //订单需要支付总金额
    private BigDecimal banlancePayMoneyVO; //使用余额支付的金额
    private int        banlancePayVO;      //1、未使用余额，2、使用余额支付够付款，3、余额不够付款
    private String     productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 1、未使用余额
     */
    public static final int BANLANCEPAYVO_1 = 1;

    /**
     * 2、使用余额支付够付款
     */
    public static final int BANLANCEPAYVO_2 = 2;

    /**
     * 3、余额不够付款
     */
    public static final int BANLANCEPAYVO_3 = 3;

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public String getRelationOrderSn() {
        return relationOrderSn;
    }

    public void setRelationOrderSn(String relationOrderSn) {
        this.relationOrderSn = relationOrderSn;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Boolean getIsBanlancePay() {
        return isBanlancePay;
    }

    public void setIsBanlancePay(Boolean isBanlancePay) {
        this.isBanlancePay = isBanlancePay;
    }

    public String getBalancePwd() {
        return balancePwd;
    }

    public void setBalancePwd(String balancePwd) {
        this.balancePwd = balancePwd;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaySessionstr() {
        return paySessionstr;
    }

    public void setPaySessionstr(String paySessionstr) {
        this.paySessionstr = paySessionstr;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Boolean getGoJumpPayfor() {
        return goJumpPayfor;
    }

    public void setGoJumpPayfor(Boolean goJumpPayfor) {
        this.goJumpPayfor = goJumpPayfor;
    }

    public BigDecimal getPayOrderAllsVO() {
        return payOrderAllsVO;
    }

    public void setPayOrderAllsVO(BigDecimal payOrderAllsVO) {
        this.payOrderAllsVO = payOrderAllsVO;
    }

    public BigDecimal getBanlancePayMoneyVO() {
        return banlancePayMoneyVO;
    }

    public void setBanlancePayMoneyVO(BigDecimal banlancePayMoneyVO) {
        this.banlancePayMoneyVO = banlancePayMoneyVO;
    }

    /**
     * 1、未使用余额，2、使用余额支付够付款，3、余额不够付款
     * @return
     */
    public int getBanlancePayVO() {
        return banlancePayVO;
    }

    /**
     * 1、未使用余额，2、使用余额支付够付款，3、余额不够付款
     * @param banlancePayVO
     */
    public void setBanlancePayVO(int banlancePayVO) {
        this.banlancePayVO = banlancePayVO;
    }

}
