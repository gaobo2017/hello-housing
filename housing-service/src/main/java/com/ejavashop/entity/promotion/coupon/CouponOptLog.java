package com.ejavashop.entity.promotion.coupon;

import java.io.Serializable;

/**
 * 优惠券操作日志
 * <p>Table: <strong>coupon_opt_log</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>couponUserId</td><td>{@link java.lang.Integer}</td><td>coupon_user_id</td><td>int</td><td>优惠券用户id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员id</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>所属商家id</td></tr>
 *   <tr><td>couponId</td><td>{@link java.lang.Integer}</td><td>coupon_id</td><td>int</td><td>优惠券id</td></tr>
 *   <tr><td>optType</td><td>{@link java.lang.Integer}</td><td>opt_type</td><td>tinyint</td><td>1、领取；2、下单消费；3、订单取消返回；4、商品退货返回</td></tr>
 *   <tr><td>orderId</td><td>{@link java.lang.Integer}</td><td>order_id</td><td>int</td><td>订单id(无订单赋0)</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Integer}</td><td>create_user_id</td><td>int</td><td>createUserId</td></tr>
 *   <tr><td>createUserName</td><td>{@link java.lang.String}</td><td>create_user_name</td><td>varchar</td><td>createUserName</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>timestamp</td><td>createTime</td></tr>
 * </table>
 *
 */
public class CouponOptLog implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1721840172803340887L;

    /** 操作类型1、领取；2、下单消费；3、订单取消返回；4、商品退货返回 */
    public static final int   OPT_TYPE_1       = 1;
    /** 操作类型1、领取；2、下单消费；3、订单取消返回；4、商品退货返回 */
    public static final int   OPT_TYPE_2       = 2;
    /** 操作类型1、领取；2、下单消费；3、订单取消返回；4、商品退货返回 */
    public static final int   OPT_TYPE_3       = 3;
    /** 操作类型1、领取；2、下单消费；3、订单取消返回；4、商品退货返回 */
    public static final int   OPT_TYPE_4       = 4;

    private java.lang.Integer id;
    private java.lang.Integer couponUserId;
    private java.lang.Integer memberId;
    private java.lang.Integer sellerId;
    private java.lang.Integer couponId;
    private java.lang.Integer optType;
    private java.lang.Integer orderId;
    private java.lang.Integer createUserId;
    private java.lang.String  createUserName;
    private java.util.Date    createTime;

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
     * 获取优惠券用户id
     */
    public java.lang.Integer getCouponUserId() {
        return this.couponUserId;
    }

    /**
     * 设置优惠券用户id
     */
    public void setCouponUserId(java.lang.Integer couponUserId) {
        this.couponUserId = couponUserId;
    }

    /**
     * 获取会员id
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置会员id
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取所属商家id
     */
    public java.lang.Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置所属商家id
     */
    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取优惠券id
     */
    public java.lang.Integer getCouponId() {
        return this.couponId;
    }

    /**
     * 设置优惠券id
     */
    public void setCouponId(java.lang.Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取1、领取；2、下单消费；3、订单取消返回；4、商品退货返回
     */
    public java.lang.Integer getOptType() {
        return this.optType;
    }

    /**
     * 设置1、领取；2、下单消费；3、订单取消返回；4、商品退货返回
     */
    public void setOptType(java.lang.Integer optType) {
        this.optType = optType;
    }

    /**
     * 获取订单id(无订单赋0)
     */
    public java.lang.Integer getOrderId() {
        return this.orderId;
    }

    /**
     * 设置订单id(无订单赋0)
     */
    public void setOrderId(java.lang.Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取createUserId
     */
    public java.lang.Integer getCreateUserId() {
        return this.createUserId;
    }

    /**
     * 设置createUserId
     */
    public void setCreateUserId(java.lang.Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取createUserName
     */
    public java.lang.String getCreateUserName() {
        return this.createUserName;
    }

    /**
     * 设置createUserName
     */
    public void setCreateUserName(java.lang.String createUserName) {
        this.createUserName = createUserName;
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
}