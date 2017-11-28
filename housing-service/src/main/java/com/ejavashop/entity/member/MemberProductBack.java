package com.ejavashop.entity.member;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.coupon.CouponUser;

/**
 * 用户退货
 * <p>Table: <strong>member_product_back</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>所属商家</td></tr>
 *   <tr><td>orderId</td><td>{@link java.lang.Integer}</td><td>order_id</td><td>int</td><td>订单ID</td></tr>
 *   <tr><td>orderProductId</td><td>{@link java.lang.Integer}</td><td>order_product_id</td><td>int</td><td>网单ID</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>商品ID</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>用户ID</td></tr>
 *   <tr><td>memberName</td><td>{@link java.lang.String}</td><td>member_name</td><td>varchar</td><td>用户Name</td></tr>
 *   <tr><td>provinceId</td><td>{@link java.lang.Integer}</td><td>province_id</td><td>int</td><td>Province</td></tr>
 *   <tr><td>cityId</td><td>{@link java.lang.Integer}</td><td>city_id</td><td>int</td><td>city</td></tr>
 *   <tr><td>areaId</td><td>{@link java.lang.Integer}</td><td>area_id</td><td>int</td><td>area</td></tr>
 *   <tr><td>addressAll</td><td>{@link java.lang.String}</td><td>address_all</td><td>varchar</td><td>省市区组合</td></tr>
 *   <tr><td>addressInfo</td><td>{@link java.lang.String}</td><td>address_info</td><td>varchar</td><td>详细地址</td></tr>
 *   <tr><td>phone</td><td>{@link java.lang.String}</td><td>phone</td><td>varchar</td><td>退货人手机</td></tr>
 *   <tr><td>returnName</td><td>{@link java.lang.String}</td><td>return_name</td><td>varchar</td><td>联系人姓名</td></tr>
 *   <tr><td>zipCode</td><td>{@link java.lang.String}</td><td>zip_code</td><td>varchar</td><td>邮编</td></tr>
 *   <tr><td>question</td><td>{@link java.lang.String}</td><td>question</td><td>varchar</td><td>问题描述</td></tr>
 *   <tr><td>image</td><td>{@link java.lang.String}</td><td>image</td><td>varchar</td><td>图片</td></tr>
 *   <tr><td>stateReturn</td><td>{@link java.lang.Integer}</td><td>state_return</td><td>tinyint</td><td>退货状态：1、未处理；2、审核通过待收货；3、已经收货；4、不予处理</td></tr>
 *   <tr><td>stateMoney</td><td>{@link java.lang.Integer}</td><td>state_money</td><td>tinyint</td><td>退款状态：1、未退款；2、退款到账户；3、退款到银行</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 *   <tr><td>optId</td><td>{@link java.lang.Integer}</td><td>opt_id</td><td>int</td><td>处理人</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>varchar</td><td>备注</td></tr>
 * </table>
 *
 */
public class MemberProductBack implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 2408716797440619962L;

    /** 用户退货表  state_return   退货状态：1、未处理；*/
    public final static int   STATE_RETURN_1   = 1;
    /** 用户退货表  state_return   退货状态：2、审核通过待收货；*/
    public final static int   STATE_RETURN_2   = 2;
    /** 用户退货表  state_return   退货状态：3、已经收货；*/
    public final static int   STATE_RETURN_3   = 3;
    /** 用户退货表  state_return   退货状态：4、不予处理；*/
    public final static int   STATE_RETURN_4   = 4;

    /** 用户退货表  state_money   退款状态：1、未退款；*/
    public final static int   STATE_MONEY_1    = 1;
    /** 用户退货表  state_money   退款状态：2、退款到账户；*/
    public final static int   STATE_MONEY_2    = 2;
    /** 用户退货表  state_money   退款状态：3、退款到银行*/
    public final static int   STATE_MONEY_3    = 3;

    private java.lang.Integer id;                                     //id
    private java.lang.Integer sellerId;                               //所属商家
    private java.lang.Integer orderId;                                //订单ID
    private java.lang.Integer orderProductId;                         //网单ID
    private java.lang.Integer productId;                              //商品ID
    private java.lang.Integer memberId;                               //用户ID
    private java.lang.String  memberName;                             //用户Name
    private java.lang.Integer provinceId;                             //Province
    private java.lang.Integer cityId;                                 //city
    private java.lang.Integer areaId;                                 //area
    private java.lang.String  addressAll;                             //省市区组合
    private java.lang.String  addressInfo;                            //详细地址
    private java.lang.String  phone;                                  //退货人手机
    private java.lang.String  returnName;                             //联系人姓名
    private java.lang.String  zipCode;                                //邮编
    private java.lang.String  question;                               //问题描述
    private java.lang.String  image;                                  //图片
    private java.lang.Integer stateReturn;                            //退货状态：1、未处理；2、审核通过待收货；3、已经收货；4、不予处理
    private java.lang.Integer stateMoney;                             //退款状态：1、未退款；2、退款到账户；3、退款到银行
    private BigDecimal        backMoney;                              //退款金额
    private java.lang.Integer backIntegral;                           //退回积分
    private BigDecimal        backIntegralMoney;                      //退回积分金额（退回积分转换成金额）
    private java.lang.Integer backCouponUserId;                       //退回优惠券ID，0表示没有
    private java.util.Date    backMoneyTime;                          //退款时间
    private java.util.Date    createTime;                             //createTime
    private java.util.Date    updateTime;                             //updateTime
    private java.lang.Integer optId;                                  //处理人
    private java.lang.String  remark;                                 //备注

    // --------额外属性（entity对应表结构之外的属性） start------------------------------
    private String            orderSn;
    private String            productName;

    private Product           product;                                // 商品
    private OrdersProduct     ordersProduct;                          // 网单
    private CouponUser        couponUser;                             // 退回优惠券
    // --------额外属性（entity对应表结构之外的属性） end------------------------------

    public String getBackCouponSn() {
        if (couponUser != null) {
            return couponUser.getCouponSn();
        }
        return "";
    }

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
     * 获取所属商家
     */
    public java.lang.Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置所属商家
     */
    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取订单ID
     */
    public java.lang.Integer getOrderId() {
        return this.orderId;
    }

    /**
     * 设置订单ID
     */
    public void setOrderId(java.lang.Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取网单ID
     */
    public java.lang.Integer getOrderProductId() {
        return this.orderProductId;
    }

    /**
     * 设置网单ID
     */
    public void setOrderProductId(java.lang.Integer orderProductId) {
        this.orderProductId = orderProductId;
    }

    /**
     * 获取商品ID
     */
    public java.lang.Integer getProductId() {
        return this.productId;
    }

    /**
     * 设置商品ID
     */
    public void setProductId(java.lang.Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取用户ID
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置用户ID
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取用户Name
     */
    public java.lang.String getMemberName() {
        return this.memberName;
    }

    /**
     * 设置用户Name
     */
    public void setMemberName(java.lang.String memberName) {
        this.memberName = memberName;
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
     * 获取退货人手机
     */
    public java.lang.String getPhone() {
        return this.phone;
    }

    /**
     * 设置退货人手机
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }

    /**
     * 获取联系人姓名
     */
    public java.lang.String getReturnName() {
        return this.returnName;
    }

    /**
     * 设置联系人姓名
     */
    public void setReturnName(java.lang.String returnName) {
        this.returnName = returnName;
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
     * 获取问题描述
     */
    public java.lang.String getQuestion() {
        return this.question;
    }

    /**
     * 设置问题描述
     */
    public void setQuestion(java.lang.String question) {
        this.question = question;
    }

    /**
     * 获取图片
     */
    public java.lang.String getImage() {
        return this.image;
    }

    /**
     * 设置图片
     */
    public void setImage(java.lang.String image) {
        this.image = image;
    }

    /**
     * 获取退货状态：1、未处理；2、审核通过待收货；3、已经收货；4、不予处理
     */
    public java.lang.Integer getStateReturn() {
        return this.stateReturn;
    }

    /**
     * 设置退货状态：1、未处理；2、审核通过待收货；3、已经收货；4、不予处理
     */
    public void setStateReturn(java.lang.Integer stateReturn) {
        this.stateReturn = stateReturn;
    }

    /**
     * 获取退款状态：1、未退款；2、退款到账户；3、退款到银行
     */
    public java.lang.Integer getStateMoney() {
        return this.stateMoney;
    }

    /**
     * 设置退款状态：1、未退款；2、退款到账户；3、退款到银行
     */
    public void setStateMoney(java.lang.Integer stateMoney) {
        this.stateMoney = stateMoney;
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
     * 获取处理人
     */
    public java.lang.Integer getOptId() {
        return this.optId;
    }

    /**
     * 设置处理人
     */
    public void setOptId(java.lang.Integer optId) {
        this.optId = optId;
    }

    /**
     * 获取备注
     */
    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public java.util.Date getBackMoneyTime() {
        return backMoneyTime;
    }

    public void setBackMoneyTime(java.util.Date backMoneyTime) {
        this.backMoneyTime = backMoneyTime;
    }

    public BigDecimal getBackMoney() {
        return backMoney;
    }

    public void setBackMoney(BigDecimal backMoney) {
        this.backMoney = backMoney;
    }

    public java.lang.Integer getBackIntegral() {
        return backIntegral;
    }

    public void setBackIntegral(java.lang.Integer backIntegral) {
        this.backIntegral = backIntegral;
    }

    public BigDecimal getBackIntegralMoney() {
        return backIntegralMoney;
    }

    public void setBackIntegralMoney(BigDecimal backIntegralMoney) {
        this.backIntegralMoney = backIntegralMoney;
    }

    public java.lang.Integer getBackCouponUserId() {
        return backCouponUserId;
    }

    public void setBackCouponUserId(java.lang.Integer backCouponUserId) {
        this.backCouponUserId = backCouponUserId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrdersProduct getOrdersProduct() {
        return ordersProduct;
    }

    public void setOrdersProduct(OrdersProduct ordersProduct) {
        this.ordersProduct = ordersProduct;
    }

    public CouponUser getCouponUser() {
        return couponUser;
    }

    public void setCouponUser(CouponUser couponUser) {
        this.couponUser = couponUser;
    }

}