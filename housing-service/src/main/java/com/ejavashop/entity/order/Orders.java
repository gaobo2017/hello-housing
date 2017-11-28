package com.ejavashop.entity.order;

import java.io.Serializable;
import java.util.List;

/**
 * 订单
 * <p>Table: <strong>orders</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>orderSn</td><td>{@link java.lang.String}</td><td>order_sn</td><td>varchar</td><td>订单号</td></tr>
 *   <tr><td>relationOrderSn</td><td>{@link java.lang.String}</td><td>relation_order_sn</td><td>varchar</td><td>关联订单编号</td></tr>
 *   <tr><td>orderType</td><td>{@link java.lang.Integer}</td><td>order_type</td><td>Integer</td><td>订单类型：1、普通订单，2、限时抢购订单</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>memberName</td><td>{@link java.lang.String}</td><td>member_name</td><td>varchar</td><td>会员name</td></tr>
 *   <tr><td>orderState</td><td>{@link java.lang.Integer}</td><td>order_state</td><td>tinyint</td><td>订单状态：1、未付款的订单；2、待确认的订单；3、待发货的订单；4、已发货的订单；5、已完成的订单；6、取消的订单</td></tr>
 *   <tr><td>payTime</td><td>{@link java.util.Date}</td><td>pay_time</td><td>datetime</td><td>在线付款时间</td></tr>
 *   <tr><td>paymentStatus</td><td>{@link java.lang.Integer}</td><td>payment_status</td><td>tinyint</td><td>付款状态：0 买家未付款 1 买家已付款</td></tr>
 *   <tr><td>invoiceStatus</td><td>{@link java.lang.Integer}</td><td>invoice_status</td><td>tinyint</td><td>发票状态0、不要发票；1、单位；2个人</td></tr>
 *   <tr><td>invoiceTitle</td><td>{@link java.lang.String}</td><td>invoice_title</td><td>varchar</td><td>发票抬头</td></tr>
 *   <tr><td>invoiceType</td><td>{@link java.lang.String}</td><td>invoice_type</td><td>varchar</td><td>发票类型</td></tr>
 *   <tr><td>moneyProduct</td><td>{@link java.math.BigDecimal}</td><td>money_product</td><td>decimal</td><td>商品金额，等于订单中所有的商品的单价乘以数量之和</td></tr>
 *   <tr><td>moneyLogistics</td><td>{@link java.math.BigDecimal}</td><td>money_logistics</td><td>decimal</td><td>物流费用</td></tr>
 *   <tr><td>moneyOrder</td><td>{@link java.math.BigDecimal}</td><td>money_order</td><td>decimal</td><td>订单总金额，等于商品总金额＋运费-优惠金额总额</td></tr>
 *   <tr><td>moneyPaidBalance</td><td>{@link java.math.BigDecimal}</td><td>money_paid_balance</td><td>decimal</td><td>余额账户支付总金额</td></tr>
 *   <tr><td>moneyPaidReality</td><td>{@link java.math.BigDecimal}</td><td>money_paid_reality</td><td>decimal</td><td>现金支付金额</td></tr>
 *   <tr><td>moneyCoupon</td><td>{@link java.math.BigDecimal}</td><td>money_coupon</td><td>decimal</td><td>优惠券优惠金额</td></tr>
 *   <tr><td>moneyActFull</td><td>{@link java.math.BigDecimal}</td><td>money_act_full</td><td>decimal</td><td>订单满减金额</td></tr>
 *   <tr><td>moneyDiscount</td><td>{@link java.math.BigDecimal}</td><td>money_discount</td><td>decimal</td><td>优惠金额总额（满减、立减、优惠券等所有优惠额的和）</td></tr>
 *   <tr><td>moneyBack</td><td>{@link java.math.BigDecimal}</td><td>money_back</td><td>decimal</td><td>退款的金额，订单没有退款则为0</td></tr>
 *   <tr><td>moneyIntegral</td><td>{@link java.math.BigDecimal}</td><td>money_integral</td><td>decimal</td><td>积分换算金额</td></tr>
 *   <tr><td>integral</td><td>{@link java.lang.Integer}</td><td>integral</td><td>int</td><td>订单使用的积分数量</td></tr>
 *   <tr><td>couponUserId</td><td>{@link java.lang.Integer}</td><td>coupon_user_id</td><td>int</td><td>优惠码ID</td></tr>
 *   <tr><td>actFullId</td><td>{@link java.lang.Integer}</td><td>act_full_id</td><td>int</td><td>订单满减活动ID</td></tr>
 *   <tr><td>ip</td><td>{@link java.lang.String}</td><td>ip</td><td>varchar</td><td>ip</td></tr>
 *   <tr><td>paymentName</td><td>{@link java.lang.String}</td><td>payment_name</td><td>varchar</td><td>支付方式名称</td></tr>
 *   <tr><td>paymentCode</td><td>{@link java.lang.String}</td><td>payment_code</td><td>varchar</td><td>支付方式code</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>收货人</td></tr>
 *   <tr><td>provinceId</td><td>{@link java.lang.Integer}</td><td>province_id</td><td>int</td><td>Province</td></tr>
 *   <tr><td>cityId</td><td>{@link java.lang.Integer}</td><td>city_id</td><td>int</td><td>city</td></tr>
 *   <tr><td>areaId</td><td>{@link java.lang.Integer}</td><td>area_id</td><td>int</td><td>area</td></tr>
 *   <tr><td>addressAll</td><td>{@link java.lang.String}</td><td>address_all</td><td>varchar</td><td>省市区组合</td></tr>
 *   <tr><td>addressInfo</td><td>{@link java.lang.String}</td><td>address_info</td><td>varchar</td><td>详细地址</td></tr>
 *   <tr><td>mobile</td><td>{@link java.lang.String}</td><td>mobile</td><td>varchar</td><td>mobile</td></tr>
 *   <tr><td>email</td><td>{@link java.lang.String}</td><td>email</td><td>varchar</td><td>email</td></tr>
 *   <tr><td>zipCode</td><td>{@link java.lang.String}</td><td>zip_code</td><td>varchar</td><td>邮编</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>varchar</td><td>订单备注</td></tr>
 *   <tr><td>deliverTime</td><td>{@link java.util.Date}</td><td>deliver_time</td><td>datetime</td><td>发货时间</td></tr>
 *   <tr><td>finishTime</td><td>{@link java.util.Date}</td><td>finish_time</td><td>datetime</td><td>订单完成时间</td></tr>
 *   <tr><td>tradeSn</td><td>{@link java.lang.String}</td><td>trade_sn</td><td>varchar</td><td>在线支付交易流水号</td></tr>
 *   <tr><td>source</td><td>{@link java.lang.Integer}</td><td>source</td><td>tinyint</td><td>会员来源1、pc；2、H5；3、Android；4、IOS</td></tr>
 *   <tr><td>logisticsId</td><td>{@link java.lang.Integer}</td><td>logistics_id</td><td>int</td><td>物流公司ID</td></tr>
 *   <tr><td>logisticsName</td><td>{@link java.lang.String}</td><td>logistics_name</td><td>varchar</td><td>物流公司</td></tr>
 *   <tr><td>logisticsNumber</td><td>{@link java.lang.String}</td><td>logistics_number</td><td>varchar</td><td>发票快递单号</td></tr>
 *   <tr><td>isCodconfim</td><td>{@link java.lang.Integer}</td><td>is_codconfim</td><td>tinyint</td><td>是否货到付款订单0、不是；1、是</td></tr>
 *   <tr><td>codconfirmId</td><td>{@link java.lang.Integer}</td><td>codconfirm_id</td><td>int</td><td>货到付款确认人</td></tr>
 *   <tr><td>codconfirmName</td><td>{@link java.lang.String}</td><td>codconfirm_name</td><td>varchar</td><td>货到付款确认Name</td></tr>
 *   <tr><td>codconfirmTime</td><td>{@link java.util.Date}</td><td>codconfirm_time</td><td>datetime</td><td>货到付款确认时间</td></tr>
 *   <tr><td>codconfirmRemark</td><td>{@link java.lang.String}</td><td>codconfirm_remark</td><td>varchar</td><td>货到付款确认备注</td></tr>
 *   <tr><td>codconfirmState</td><td>{@link java.lang.Integer}</td><td>codconfirm_state</td><td>tinyint</td><td>货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class Orders implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID     = 416597734859947122L;

    /** 发货 */
    public static final int      DELIVER              = 1;
    /** 取消 */
    public static final int      CANCEL               = 2;
    /** 更新金额 */
    public static final int      UPDATE_AMOUNT        = 3;
    /** 确认收款 */
    public static final int      SUBMIT_PAY           = 4;
    /** 确认 */
    public static final int      CONFIRM              = 5;

    /** 订单表  order_state  订单状态：1、未付款的订单；*/
    public final static int      ORDER_STATE_1        = 1;
    /** 订单表  order_state  订单状态：2、待确认的订单；*/
    public final static int      ORDER_STATE_2        = 2;
    /** 订单表  order_state  订单状态：3、待发货的订单；*/
    public final static int      ORDER_STATE_3        = 3;
    /** 订单表  order_state  订单状态：4、已发货的订单；*/
    public final static int      ORDER_STATE_4        = 4;
    /** 订单表  order_state  订单状态：5、已完成的订单；*/
    public final static int      ORDER_STATE_5        = 5;
    /** 订单表  order_state  订单状态：6、取消*/
    public final static int      ORDER_STATE_6        = 6;

    /** 订单表  order_type  订单类型：1、普通订单，2、限时抢购订单，3、团购订单 */
    public final static int      ORDER_TYPE_1         = 1;
    /** 订单表  order_type  订单类型：1、普通订单，2、限时抢购订单，3、团购订单 */
    public final static int      ORDER_TYPE_2         = 2;
    /** 订单表  order_type  订单类型：1、普通订单，2、限时抢购订单，3、团购订单 */
    public final static int      ORDER_TYPE_3         = 3;

    /** 订单表  invoice_status  发票状态：0、不要发票；*/
    public final static int      INVOICE_STATUS_0     = 0;
    /** 订单表  invoice_status  发票状态：1、单位；*/
    public final static int      INVOICE_STATUS_1     = 1;
    /** 订单表  invoice_status  发票状态：2、个人*/
    public final static int      INVOICE_STATUS_2     = 2;

    /** 付款code：OFFLINE，货到付款 */
    public final static String   PAYMENT_CODE_OFFLINE = "OFFLINE";
    /** 付款code：ONLINE，在线支付 */
    public final static String   PAYMENT_CODE_ONLINE  = "ONLINE";
    /** 付款code：BALANCE，余额支付 */
    public final static String   PAYMENT_CODE_BALANCE = "BALANCE";
    /** 付款code：ALIPAY，支付宝支付 */
    public final static String   PAYMENT_CODE_ALIPAY  = "ALIPAY";

    /** 是否货到付款订单：0、不是 */
    public final static int      IS_CODCONFIM_0       = 0;
    /** 是否货到付款订单：1、是 */
    public final static int      IS_CODCONFIM_1       = 1;

    /** 货到付款状态：0、非货到付款 */
    public final static int      CODCONFIRM_STATE_0   = 0;
    /** 货到付款状态：1、待确认 */
    public final static int      CODCONFIRM_STATE_1   = 1;
    /** 货到付款状态：2、确认收款 */
    public final static int      CODCONFIRM_STATE_2   = 2;
    /** 货到付款状态：3、订单取消 */
    public final static int      CODCONFIRM_STATE_3   = 3;

    /** 订单表  payment_status  付款状态：0、买家未付款*/
    public final static int      PAYMENT_STATUS_0     = 0;
    /** 订单表  payment_status  付款状态：1、买家已付款*/
    public final static int      PAYMENT_STATUS_1     = 1;

    private java.lang.Integer    id;
    private java.lang.String     orderSn;
    private java.lang.String     relationOrderSn;
    private java.lang.Integer    orderType;
    private java.lang.Integer    sellerId;
    private java.lang.Integer    memberId;
    private java.lang.String     memberName;
    private java.lang.Integer    orderState;
    private java.util.Date       payTime;
    private java.lang.Integer    paymentStatus;
    private java.lang.Integer    invoiceStatus;
    private java.lang.String     invoiceTitle;
    private java.lang.String     invoiceType;
    private java.math.BigDecimal moneyProduct;
    private java.math.BigDecimal moneyLogistics;
    private java.math.BigDecimal moneyOrder;
    private java.math.BigDecimal moneyPaidBalance;
    private java.math.BigDecimal moneyPaidReality;
    private java.math.BigDecimal moneyCoupon;
    private java.math.BigDecimal moneyActFull;
    private java.math.BigDecimal moneyDiscount;
    private java.math.BigDecimal moneyBack;
    private java.math.BigDecimal moneyIntegral;
    private java.lang.Integer    integral;
    private java.lang.Integer    couponUserId;
    private java.lang.Integer    actFullId;
    private java.lang.String     ip;
    private java.lang.String     paymentName;
    private java.lang.String     paymentCode;
    private java.lang.String     name;
    private java.lang.Integer    provinceId;
    private java.lang.Integer    cityId;
    private java.lang.Integer    areaId;
    private java.lang.String     addressAll;
    private java.lang.String     addressInfo;
    private java.lang.String     mobile;
    private java.lang.String     email;
    private java.lang.String     zipCode;
    private java.lang.String     remark;
    private java.util.Date       deliverTime;
    private java.util.Date       finishTime;
    private java.lang.String     tradeSn;
    private java.lang.Integer    source;
    private java.lang.Integer    logisticsId;
    private java.lang.String     logisticsName;
    private java.lang.String     logisticsNumber;
    private java.lang.Integer    isCodconfim;
    private java.lang.Integer    codconfirmId;
    private java.lang.String     codconfirmName;
    private java.util.Date       codconfirmTime;
    private java.lang.String     codconfirmRemark;
    private java.lang.Integer    codconfirmState;
    private java.util.Date       createTime;
    private java.util.Date       updateTime;

    // --------额外属性（entity对应表结构之外的属性） start------------------------------
    private String               sellerName;                                // 商家名称
    private List<OrdersProduct>  orderProductList;                          // 网单list
    // --------额外属性（entity对应表结构之外的属性） end--------------------------------

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
     * 获取订单号
     */
    public java.lang.String getOrderSn() {
        return this.orderSn;
    }

    /**
     * 设置订单号
     */
    public void setOrderSn(java.lang.String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取关联订单编号
     */
    public java.lang.String getRelationOrderSn() {
        return this.relationOrderSn;
    }

    /**
     * 设置关联订单编号
     */
    public void setRelationOrderSn(java.lang.String relationOrderSn) {
        this.relationOrderSn = relationOrderSn;
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
     * 获取会员ID
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置会员ID
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取会员name
     */
    public java.lang.String getMemberName() {
        return this.memberName;
    }

    /**
     * 设置会员name
     */
    public void setMemberName(java.lang.String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取订单状态：1、未付款的订单；2、待确认的订单；3、待发货的订单；4、已发货的订单；5、已完成的订单；6、取消的订单
     */
    public java.lang.Integer getOrderState() {
        return this.orderState;
    }

    /**
     * 设置订单状态：1、未付款的订单；2、待确认的订单；3、待发货的订单；4、已发货的订单；5、已完成的订单；6、取消的订单
     */
    public void setOrderState(java.lang.Integer orderState) {
        this.orderState = orderState;
    }

    /**
     * 获取在线付款时间
     */
    public java.util.Date getPayTime() {
        return this.payTime;
    }

    /**
     * 设置在线付款时间
     */
    public void setPayTime(java.util.Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取付款状态：0 买家未付款 1 买家已付款
     */
    public java.lang.Integer getPaymentStatus() {
        return this.paymentStatus;
    }

    /**
     * 设置付款状态：0 买家未付款 1 买家已付款
     */
    public void setPaymentStatus(java.lang.Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    /**
     * 获取发票状态0、不要发票；1、单位；2个人
     */
    public java.lang.Integer getInvoiceStatus() {
        return this.invoiceStatus;
    }

    /**
     * 设置发票状态0、不要发票；1、单位；2个人
     */
    public void setInvoiceStatus(java.lang.Integer invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    /**
     * 获取发票抬头
     */
    public java.lang.String getInvoiceTitle() {
        return this.invoiceTitle;
    }

    /**
     * 设置发票抬头
     */
    public void setInvoiceTitle(java.lang.String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    /**
     * 获取发票类型
     */
    public java.lang.String getInvoiceType() {
        return this.invoiceType;
    }

    /**
     * 设置发票类型
     */
    public void setInvoiceType(java.lang.String invoiceType) {
        this.invoiceType = invoiceType;
    }

    /**
     * 获取商品金额，等于订单中所有的商品的单价乘以数量之和
     */
    public java.math.BigDecimal getMoneyProduct() {
        return this.moneyProduct;
    }

    /**
     * 设置商品金额，等于订单中所有的商品的单价乘以数量之和
     */
    public void setMoneyProduct(java.math.BigDecimal moneyProduct) {
        this.moneyProduct = moneyProduct;
    }

    /**
     * 获取物流费用
     */
    public java.math.BigDecimal getMoneyLogistics() {
        return this.moneyLogistics;
    }

    /**
     * 设置物流费用
     */
    public void setMoneyLogistics(java.math.BigDecimal moneyLogistics) {
        this.moneyLogistics = moneyLogistics;
    }

    /**
     * 获取订单总金额，等于商品总金额＋运费-优惠金额总额
     */
    public java.math.BigDecimal getMoneyOrder() {
        return this.moneyOrder;
    }

    /**
     * 设置订单总金额，等于商品总金额＋运费-优惠金额总额
     */
    public void setMoneyOrder(java.math.BigDecimal moneyOrder) {
        this.moneyOrder = moneyOrder;
    }

    /**
     * 获取余额账户支付总金额
     */
    public java.math.BigDecimal getMoneyPaidBalance() {
        return this.moneyPaidBalance;
    }

    /**
     * 设置余额账户支付总金额
     */
    public void setMoneyPaidBalance(java.math.BigDecimal moneyPaidBalance) {
        this.moneyPaidBalance = moneyPaidBalance;
    }

    /**
     * 获取现金支付金额
     */
    public java.math.BigDecimal getMoneyPaidReality() {
        return this.moneyPaidReality;
    }

    /**
     * 设置现金支付金额
     */
    public void setMoneyPaidReality(java.math.BigDecimal moneyPaidReality) {
        this.moneyPaidReality = moneyPaidReality;
    }

    /**
     * 获取优惠券优惠金额
     */
    public java.math.BigDecimal getMoneyCoupon() {
        return this.moneyCoupon;
    }

    /**
     * 设置优惠券优惠金额
     */
    public void setMoneyCoupon(java.math.BigDecimal moneyCoupon) {
        this.moneyCoupon = moneyCoupon;
    }

    /**
     * 获取下单立减金额
     */
    public java.math.BigDecimal getMoneyActFull() {
        return this.moneyActFull;
    }

    /**
     * 设置下单立减金额
     */
    public void setMoneyActFull(java.math.BigDecimal moneyActFull) {
        this.moneyActFull = moneyActFull;
    }

    /**
     * 获取优惠金额总额（满减、立减、优惠券和）
     */
    public java.math.BigDecimal getMoneyDiscount() {
        return this.moneyDiscount;
    }

    /**
     * 设置优惠金额总额（满减、立减、优惠券和）
     */
    public void setMoneyDiscount(java.math.BigDecimal moneyDiscount) {
        this.moneyDiscount = moneyDiscount;
    }

    /**
     * 获取退款的金额，订单没有退款则为0
     */
    public java.math.BigDecimal getMoneyBack() {
        return this.moneyBack;
    }

    /**
     * 设置退款的金额，订单没有退款则为0
     */
    public void setMoneyBack(java.math.BigDecimal moneyBack) {
        this.moneyBack = moneyBack;
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

    public java.lang.Integer getIntegral() {
        return integral;
    }

    public void setIntegral(java.lang.Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取优惠码ID
     */
    public java.lang.Integer getCouponUserId() {
        return this.couponUserId;
    }

    /**
     * 设置优惠码ID
     */
    public void setCouponUserId(java.lang.Integer couponUserId) {
        this.couponUserId = couponUserId;
    }

    /**
     * 获取订单满减活动ID
     */
    public java.lang.Integer getActFullId() {
        return this.actFullId;
    }

    /**
     * 设置订单满减活动ID
     */
    public void setActFullId(java.lang.Integer actFullId) {
        this.actFullId = actFullId;
    }

    /**
     * 获取ip
     */
    public java.lang.String getIp() {
        return this.ip;
    }

    /**
     * 设置ip
     */
    public void setIp(java.lang.String ip) {
        this.ip = ip;
    }

    /**
     * 获取支付方式名称
     */
    public java.lang.String getPaymentName() {
        return this.paymentName;
    }

    /**
     * 设置支付方式名称
     */
    public void setPaymentName(java.lang.String paymentName) {
        this.paymentName = paymentName;
    }

    /**
     * 获取支付方式code
     */
    public java.lang.String getPaymentCode() {
        return this.paymentCode;
    }

    /**
     * 设置支付方式code
     */
    public void setPaymentCode(java.lang.String paymentCode) {
        this.paymentCode = paymentCode;
    }

    /**
     * 获取收货人
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 设置收货人
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * 获取Province
     */
    public java.lang.Integer getProvinceId() {
        return this.provinceId;
    }

    /**
     * 设置Province
     */
    public void setProvinceId(java.lang.Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取city
     */
    public java.lang.Integer getCityId() {
        return this.cityId;
    }

    /**
     * 设置city
     */
    public void setCityId(java.lang.Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取area
     */
    public java.lang.Integer getAreaId() {
        return this.areaId;
    }

    /**
     * 设置area
     */
    public void setAreaId(java.lang.Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取省市区组合
     */
    public java.lang.String getAddressAll() {
        return this.addressAll;
    }

    /**
     * 设置省市区组合
     */
    public void setAddressAll(java.lang.String addressAll) {
        this.addressAll = addressAll;
    }

    /**
     * 获取详细地址
     */
    public java.lang.String getAddressInfo() {
        return this.addressInfo;
    }

    /**
     * 设置详细地址
     */
    public void setAddressInfo(java.lang.String addressInfo) {
        this.addressInfo = addressInfo;
    }

    /**
     * 获取mobile
     */
    public java.lang.String getMobile() {
        return this.mobile;
    }

    /**
     * 设置mobile
     */
    public void setMobile(java.lang.String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取email
     */
    public java.lang.String getEmail() {
        return this.email;
    }

    /**
     * 设置email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    /**
     * 获取邮编
     */
    public java.lang.String getZipCode() {
        return this.zipCode;
    }

    /**
     * 设置邮编
     */
    public void setZipCode(java.lang.String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 获取订单备注
     */
    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * 设置订单备注
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    /**
     * 获取订单完成时间
     */
    public java.util.Date getFinishTime() {
        return this.finishTime;
    }

    /**
     * 设置订单完成时间
     */
    public void setFinishTime(java.util.Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 获取在线支付交易流水号
     */
    public java.lang.String getTradeSn() {
        return this.tradeSn;
    }

    /**
     * 设置在线支付交易流水号
     */
    public void setTradeSn(java.lang.String tradeSn) {
        this.tradeSn = tradeSn;
    }

    /**
     * 获取会员来源1、pc；2、H5；3、Android；4、IOS
     */
    public java.lang.Integer getSource() {
        return this.source;
    }

    /**
     * 设置会员来源1、pc；2、H5；3、Android；4、IOS
     */
    public void setSource(java.lang.Integer source) {
        this.source = source;
    }

    /**
     * 获取物流公司ID
     */
    public java.lang.Integer getLogisticsId() {
        return this.logisticsId;
    }

    /**
     * 设置物流公司ID
     */
    public void setLogisticsId(java.lang.Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    /**
     * 获取物流公司
     */
    public java.lang.String getLogisticsName() {
        return this.logisticsName;
    }

    /**
     * 设置物流公司
     */
    public void setLogisticsName(java.lang.String logisticsName) {
        this.logisticsName = logisticsName;
    }

    /**
     * 获取发票快递单号
     */
    public java.lang.String getLogisticsNumber() {
        return this.logisticsNumber;
    }

    /**
     * 设置发票快递单号
     */
    public void setLogisticsNumber(java.lang.String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    /**
     * 获取是否货到付款订单0、不是；1、是
     */
    public java.lang.Integer getIsCodconfim() {
        return this.isCodconfim;
    }

    /**
     * 设置是否货到付款订单0、不是；1、是
     */
    public void setIsCodconfim(java.lang.Integer isCodconfim) {
        this.isCodconfim = isCodconfim;
    }

    /**
     * 获取货到付款确认人
     */
    public java.lang.Integer getCodconfirmId() {
        return this.codconfirmId;
    }

    /**
     * 设置货到付款确认人
     */
    public void setCodconfirmId(java.lang.Integer codconfirmId) {
        this.codconfirmId = codconfirmId;
    }

    /**
     * 获取货到付款确认Name
     */
    public java.lang.String getCodconfirmName() {
        return this.codconfirmName;
    }

    /**
     * 设置货到付款确认Name
     */
    public void setCodconfirmName(java.lang.String codconfirmName) {
        this.codconfirmName = codconfirmName;
    }

    /**
     * 获取货到付款确认时间
     */
    public java.util.Date getCodconfirmTime() {
        return this.codconfirmTime;
    }

    /**
     * 设置货到付款确认时间
     */
    public void setCodconfirmTime(java.util.Date codconfirmTime) {
        this.codconfirmTime = codconfirmTime;
    }

    /**
     * 获取货到付款确认备注
     */
    public java.lang.String getCodconfirmRemark() {
        return this.codconfirmRemark;
    }

    /**
     * 设置货到付款确认备注
     */
    public void setCodconfirmRemark(java.lang.String codconfirmRemark) {
        this.codconfirmRemark = codconfirmRemark;
    }

    /**
     * 获取货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消
     */
    public java.lang.Integer getCodconfirmState() {
        return this.codconfirmState;
    }

    /**
     * 设置货到付款状态 0、非货到付款；1、待确认；2、确认通过可以发货；3、订单取消
     */
    public void setCodconfirmState(java.lang.Integer codconfirmState) {
        this.codconfirmState = codconfirmState;
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
     * 订单类型：1、普通订单，2、限时抢购订单
     */
    public java.lang.Integer getOrderType() {
        return orderType;
    }

    /**
     * 订单类型：1、普通订单，2、限时抢购订单
     */
    public void setOrderType(java.lang.Integer orderType) {
        this.orderType = orderType;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public List<OrdersProduct> getOrderProductList() {
        return orderProductList;
    }

    public void setOrderProductList(List<OrdersProduct> orderProductList) {
        this.orderProductList = orderProductList;
    }

    public java.util.Date getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(java.util.Date deliverTime) {
        this.deliverTime = deliverTime;
    }

}