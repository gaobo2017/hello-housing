package com.ejavashop.entity.settlement;

import java.io.Serializable;

/**
 * 结算表
 * <p>Table: <strong>settlement</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>settleCycle</td><td>{@link java.lang.String}</td><td>settle_cycle</td><td>varchar</td><td>结算周期</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>sellerName</td><td>{@link java.lang.String}</td><td>seller_name</td><td>varchar</td><td>商家名称</td></tr>
 *   <tr><td>moneyProduct</td><td>{@link java.math.BigDecimal}</td><td>money_product</td><td>decimal</td><td>商品总金额，等于订单中所有的商品的单价乘以数量之和：orders.money_product总和</td></tr>
 *   <tr><td>moneyLogistics</td><td>{@link java.math.BigDecimal}</td><td>money_logistics</td><td>decimal</td><td>物流费用之和：orders.money_logistics总和</td></tr>
 *   <tr><td>moneyOrder</td><td>{@link java.math.BigDecimal}</td><td>money_order</td><td>decimal</td><td>实际应收金额总计：orders.money_order总和</td></tr>
 *   <tr><td>moneyPaidBalance</td><td>{@link java.math.BigDecimal}</td><td>money_paid_balance</td><td>decimal</td><td>余额账户支付总金额：money_paid_balance总和</td></tr>
 *   <tr><td>moneyPaidReality</td><td>{@link java.math.BigDecimal}</td><td>money_paid_reality</td><td>decimal</td><td>现金支付金额：money_paid_reality总和</td></tr>
 *   <tr><td>moneyCoupon</td><td>{@link java.math.BigDecimal}</td><td>money_coupon</td><td>decimal</td><td>优惠码优惠金额：money_coupon总和</td></tr>
 *   <tr><td>moneyActFull</td><td>{@link java.math.BigDecimal}</td><td>money_act_full</td><td>decimal</td><td>订单满减总金额：money_act_full总和</td></tr>
 *   <tr><td>moneyDiscount</td><td>{@link java.math.BigDecimal}</td><td>money_discount</td><td>decimal</td><td>优惠金额总额（满减、立减、优惠券等所有优惠额的和）：money_discount总和</td></tr>
 *   <tr><td>moneyIntegral</td><td>{@link java.math.BigDecimal}</td><td>money_integral</td><td>decimal</td><td>积分换算金额</td></tr>
 *   <tr><td>integral</td><td>{@link java.lang.Integer}</td><td>integral</td><td>int</td><td>使用积分数量</td></tr>
 *   <tr><td>moneyBack</td><td>{@link java.math.BigDecimal}</td><td>money_back</td><td>decimal</td><td>当月退款的总金额：退货表back_money总和</td></tr>
 *   <tr><td>moneyOther</td><td>{@link java.math.BigDecimal}</td><td>money_other</td><td>decimal</td><td>其他金额，应支付给商家的其他金额</td></tr>
 *   <tr><td>moneyOtherType</td><td>{@link java.lang.Integer}</td><td>money_other_type</td><td>tinyint</td><td>其他金额加减状态：1、支付增加；2、支付扣减</td></tr>
 *   <tr><td>moneyOtherReason</td><td>{@link java.lang.String}</td><td>money_other_reason</td><td>varchar</td><td>其他金额理由</td></tr>
 *   <tr><td>commision</td><td>{@link java.math.BigDecimal}</td><td>commision</td><td>decimal</td><td>佣金</td></tr>
 *   <tr><td>payable</td><td>{@link java.math.BigDecimal}</td><td>payable</td><td>decimal</td><td>应付金额：money_order-money_back-commision</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>结算状态：1、账单生成；2、平台审核通过；3、商家核对通过；4、商家核对质疑；5、对账完成；6、支付完成</td></tr>
 *   <tr><td>sellerDoubt</td><td>{@link java.lang.String}</td><td>seller_doubt</td><td>text</td><td>商家质疑</td></tr>
 *   <tr><td>platformExplain</td><td>{@link java.lang.String}</td><td>platform_explain</td><td>text</td><td>平台解释</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 *   <tr><td>updateUserId</td><td>{@link java.lang.Integer}</td><td>update_user_id</td><td>int</td><td>修改者ID</td></tr>
 *   <tr><td>updateUserName</td><td>{@link java.lang.String}</td><td>update_user_name</td><td>varchar</td><td>修改者名称</td></tr>
 * </table>
 *
 */
public class Settlement implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID   = 5735828246105869447L;

    /** 结算状态：1、账单生成  */
    public static final int      STATUS_1           = 1;
    /** 结算状态：2、平台审核通过成  */
    public static final int      STATUS_2           = 2;
    /** 结算状态：3、商家核对通过  */
    public static final int      STATUS_3           = 3;
    /** 结算状态：4、商家核对质疑  */
    public static final int      STATUS_4           = 4;
    /** 结算状态：5、对账完成  */
    public static final int      STATUS_5           = 5;
    /** 结算状态：6、支付完成  */
    public static final int      STATUS_6           = 6;

    /** 其他金额加减状态：1、支付增加  */
    public static final int      MONEY_OTHER_TYPE_1 = 1;
    /** 其他金额加减状态：2、支付扣减  */
    public static final int      MONEY_OTHER_TYPE_2 = 2;

    private java.lang.Integer    id;
    private java.lang.String     settleCycle;
    private java.lang.Integer    sellerId;
    private java.lang.String     sellerName;
    private java.math.BigDecimal moneyProduct;
    private java.math.BigDecimal moneyLogistics;
    private java.math.BigDecimal moneyOrder;
    private java.math.BigDecimal moneyPaidBalance;
    private java.math.BigDecimal moneyPaidReality;
    private java.math.BigDecimal moneyCoupon;
    private java.math.BigDecimal moneyActFull;
    private java.math.BigDecimal moneyDiscount;
    private java.math.BigDecimal moneyIntegral;
    private java.lang.Integer    integral;
    private java.math.BigDecimal moneyBack;
    private java.math.BigDecimal moneyIntegralBack;
    private java.math.BigDecimal moneyOther;
    private java.lang.Integer    moneyOtherType;
    private java.lang.String     moneyOtherReason;
    private java.math.BigDecimal commision;
    private java.math.BigDecimal payable;
    private java.lang.Integer    status;
    private java.lang.String     sellerDoubt;
    private java.lang.String     platformExplain;
    private java.util.Date       createTime;
    private java.util.Date       updateTime;
    private java.lang.Integer    updateUserId;
    private java.lang.String     updateUserName;

    /**
     * 获取id
     */
    public java.lang.Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    /**
     * 获取结算周期
     */
    public java.lang.String getSettleCycle() {
        return this.settleCycle;
    }

    /**
     * 设置结算周期
     */
    public void setSettleCycle(java.lang.String settleCycle) {
        this.settleCycle = settleCycle;
    }

    /**
     * 获取商家ID
     */
    public java.lang.Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置商家ID
     */
    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取商家名称
     */
    public java.lang.String getSellerName() {
        return this.sellerName;
    }

    /**
     * 设置商家名称
     */
    public void setSellerName(java.lang.String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * 获取商品总金额，等于订单中所有的商品的单价乘以数量之和：orders.money_product总和
     */
    public java.math.BigDecimal getMoneyProduct() {
        return this.moneyProduct;
    }

    /**
     * 设置商品总金额，等于订单中所有的商品的单价乘以数量之和：orders.money_product总和
     */
    public void setMoneyProduct(java.math.BigDecimal moneyProduct) {
        this.moneyProduct = moneyProduct;
    }

    /**
     * 获取物流费用之和：orders.money_logistics总和
     */
    public java.math.BigDecimal getMoneyLogistics() {
        return this.moneyLogistics;
    }

    /**
     * 设置物流费用之和：orders.money_logistics总和
     */
    public void setMoneyLogistics(java.math.BigDecimal moneyLogistics) {
        this.moneyLogistics = moneyLogistics;
    }

    /**
     * 获取实际应收金额总计：orders.money_order总和
     */
    public java.math.BigDecimal getMoneyOrder() {
        return this.moneyOrder;
    }

    /**
     * 设置实际应收金额总计：orders.money_order总和
     */
    public void setMoneyOrder(java.math.BigDecimal moneyOrder) {
        this.moneyOrder = moneyOrder;
    }

    /**
     * 获取余额账户支付总金额：money_paid_balance总和
     */
    public java.math.BigDecimal getMoneyPaidBalance() {
        return this.moneyPaidBalance;
    }

    /**
     * 设置余额账户支付总金额：money_paid_balance总和
     */
    public void setMoneyPaidBalance(java.math.BigDecimal moneyPaidBalance) {
        this.moneyPaidBalance = moneyPaidBalance;
    }

    /**
     * 获取现金支付金额：money_paid_reality总和
     */
    public java.math.BigDecimal getMoneyPaidReality() {
        return this.moneyPaidReality;
    }

    /**
     * 设置现金支付金额：money_paid_reality总和
     */
    public void setMoneyPaidReality(java.math.BigDecimal moneyPaidReality) {
        this.moneyPaidReality = moneyPaidReality;
    }

    /**
     * 获取优惠码优惠金额：money_coupon总和
     */
    public java.math.BigDecimal getMoneyCoupon() {
        return this.moneyCoupon;
    }

    /**
     * 设置优惠码优惠金额：money_coupon总和
     */
    public void setMoneyCoupon(java.math.BigDecimal moneyCoupon) {
        this.moneyCoupon = moneyCoupon;
    }

    /**
     * 获取订单满减总金额：money_act_full总和
     */
    public java.math.BigDecimal getMoneyActFull() {
        return this.moneyActFull;
    }

    /**
     * 设置订单满减总金额：money_act_full总和
     */
    public void setMoneyActFull(java.math.BigDecimal moneyActFull) {
        this.moneyActFull = moneyActFull;
    }

    /**
     * 获取优惠金额总额（满减、立减、优惠券等所有优惠额的和）：money_discount总和
     */
    public java.math.BigDecimal getMoneyDiscount() {
        return this.moneyDiscount;
    }

    /**
     * 设置优惠金额总额（满减、立减、优惠券等所有优惠额的和）：money_discount总和
     */
    public void setMoneyDiscount(java.math.BigDecimal moneyDiscount) {
        this.moneyDiscount = moneyDiscount;
    }

    /**
     * 获取积分换算金额
     */
    public java.math.BigDecimal getMoneyIntegral() {
        return this.moneyIntegral;
    }

    /**
     * 设置积分换算金额
     */
    public void setMoneyIntegral(java.math.BigDecimal moneyIntegral) {
        this.moneyIntegral = moneyIntegral;
    }

    /**
     * 获取使用积分数量
     */
    public java.lang.Integer getIntegral() {
        return this.integral;
    }

    /**
     * 设置使用积分数量
     */
    public void setIntegral(java.lang.Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取当月退款的总金额：退货表back_money总和
     */
    public java.math.BigDecimal getMoneyBack() {
        return this.moneyBack;
    }

    /**
     * 设置当月退款的总金额：退货表back_money总和
     */
    public void setMoneyBack(java.math.BigDecimal moneyBack) {
        this.moneyBack = moneyBack;
    }

    /**
     * 获取当月退回积分金额的总金额：退货表back_integral_money总和
     * @return
     */
    public java.math.BigDecimal getMoneyIntegralBack() {
        return moneyIntegralBack;
    }

    /**
     * 设置当月退回积分金额的总金额：退货表back_integral_money总和
     * @param moneyIntegralBack
     */
    public void setMoneyIntegralBack(java.math.BigDecimal moneyIntegralBack) {
        this.moneyIntegralBack = moneyIntegralBack;
    }

    /**
     * 获取其他金额，应支付给商家的其他金额
     */
    public java.math.BigDecimal getMoneyOther() {
        return this.moneyOther;
    }

    /**
     * 设置其他金额，应支付给商家的其他金额
     */
    public void setMoneyOther(java.math.BigDecimal moneyOther) {
        this.moneyOther = moneyOther;
    }

    /**
     * 获取其他金额加减状态：1、支付增加；2、支付扣减
     */
    public java.lang.Integer getMoneyOtherType() {
        return this.moneyOtherType;
    }

    /**
     * 设置其他金额加减状态：1、支付增加；2、支付扣减
     */
    public void setMoneyOtherType(java.lang.Integer moneyOtherType) {
        this.moneyOtherType = moneyOtherType;
    }

    /**
     * 获取其他金额理由
     */
    public java.lang.String getMoneyOtherReason() {
        return this.moneyOtherReason;
    }

    /**
     * 设置其他金额理由
     */
    public void setMoneyOtherReason(java.lang.String moneyOtherReason) {
        this.moneyOtherReason = moneyOtherReason;
    }

    /**
     * 获取佣金
     */
    public java.math.BigDecimal getCommision() {
        return this.commision;
    }

    /**
     * 设置佣金
     */
    public void setCommision(java.math.BigDecimal commision) {
        this.commision = commision;
    }

    /**
     * 获取应付金额：money_order-money_back-commision
     */
    public java.math.BigDecimal getPayable() {
        return this.payable;
    }

    /**
     * 设置应付金额：money_order-money_back-commision
     */
    public void setPayable(java.math.BigDecimal payable) {
        this.payable = payable;
    }

    /**
     * 获取结算状态：1、账单生成；2、平台审核通过；3、商家核对通过；4、商家核对质疑；5、对账完成；6、支付完成
     */
    public java.lang.Integer getStatus() {
        return this.status;
    }

    /**
     * 设置结算状态：1、账单生成；2、平台审核通过；3、商家核对通过；4、商家核对质疑；5、对账完成；6、支付完成
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    /**
     * 获取商家质疑
     */
    public java.lang.String getSellerDoubt() {
        return this.sellerDoubt;
    }

    /**
     * 设置商家质疑
     */
    public void setSellerDoubt(java.lang.String sellerDoubt) {
        this.sellerDoubt = sellerDoubt;
    }

    /**
     * 获取平台解释
     */
    public java.lang.String getPlatformExplain() {
        return this.platformExplain;
    }

    /**
     * 设置平台解释
     */
    public void setPlatformExplain(java.lang.String platformExplain) {
        this.platformExplain = platformExplain;
    }

    /**
     * 获取createTime
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置createTime
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取updateTime
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置updateTime
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取修改者ID
     */
    public java.lang.Integer getUpdateUserId() {
        return this.updateUserId;
    }

    /**
     * 设置修改者ID
     */
    public void setUpdateUserId(java.lang.Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取修改者名称
     */
    public java.lang.String getUpdateUserName() {
        return this.updateUserName;
    }

    /**
     * 设置修改者名称
     */
    public void setUpdateUserName(java.lang.String updateUserName) {
        this.updateUserName = updateUserName;
    }

}