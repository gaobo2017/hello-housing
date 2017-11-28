package com.ejavashop.entity.promotion.coupon;

import java.io.Serializable;

/**
 * 优惠券用户表
 * <p>Table: <strong>coupon_user</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员id</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>所属商家id</td></tr>
 *   <tr><td>couponId</td><td>{@link java.lang.Integer}</td><td>coupon_id</td><td>int</td><td>优惠券id</td></tr>
 *   <tr><td>couponSn</td><td>{@link java.lang.String}</td><td>coupon_sn</td><td>varchar</td><td>序列号</td></tr>
 *   <tr><td>password</td><td>{@link java.lang.String}</td><td>password</td><td>varchar</td><td>密码</td></tr>
 *   <tr><td>canUse</td><td>{@link java.lang.Integer}</td><td>can_use</td><td>int</td><td>可使用次数（默认都是1次，不支持多次使用）</td></tr>
 *   <tr><td>receiveTime</td><td>{@link java.util.Date}</td><td>receive_time</td><td>datetime</td><td>领取时间</td></tr>
 *   <tr><td>orderId</td><td>{@link java.lang.Integer}</td><td>order_id</td><td>int</td><td>订单id(无订单赋0)</td></tr>
 *   <tr><td>useTime</td><td>{@link java.util.Date}</td><td>use_time</td><td>datetime</td><td>使用时间</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Integer}</td><td>create_user_id</td><td>int</td><td>createUserId</td></tr>
 *   <tr><td>createUserName</td><td>{@link java.lang.String}</td><td>create_user_name</td><td>varchar</td><td>createUserName</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateUserId</td><td>{@link java.lang.Integer}</td><td>update_user_id</td><td>int</td><td>updateUserId</td></tr>
 *   <tr><td>updateUserName</td><td>{@link java.lang.String}</td><td>update_user_name</td><td>varchar</td><td>updateUserName</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class CouponUser implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = -5595134894508191044L;

    private java.lang.Integer    id;
    private java.lang.Integer    memberId;
    private java.lang.Integer    sellerId;
    private java.lang.Integer    couponId;
    private java.lang.String     couponSn;
    private java.lang.String     password;
    private java.lang.Integer    canUse;
    private java.util.Date       receiveTime;
    private java.lang.Integer    orderId;
    private java.util.Date       useTime;
    private java.util.Date       useStartTime;
    private java.util.Date       useEndTime;
    private java.lang.Integer    createUserId;
    private java.lang.String     createUserName;
    private java.util.Date       createTime;
    private java.lang.Integer    updateUserId;
    private java.lang.String     updateUserName;
    private java.util.Date       updateTime;

    // 扩展属性
    private java.lang.String     memberName;
    private java.lang.String     sellerName;
    private java.lang.String     couponName;
    private java.lang.String     orderSn;
    private java.math.BigDecimal couponValue;                             //优惠券面值
    private java.math.BigDecimal minAmount;                               //适用的最低订单金额
    private boolean              timeout;                                 //true 过期
    private java.lang.Integer    channel;                                 // 优惠券使用渠道
    private boolean              isuse;

    public boolean isTimeout() {
        return timeout;
    }

    public void setTimeout(boolean timeout) {
        this.timeout = timeout;
    }

    public java.math.BigDecimal getCouponValue() {
        return couponValue;
    }

    public void setCouponValue(java.math.BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    public java.math.BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(java.math.BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public java.util.Date getUseStartTime() {
        return useStartTime;
    }

    public void setUseStartTime(java.util.Date useStartTime) {
        this.useStartTime = useStartTime;
    }

    public java.util.Date getUseEndTime() {
        return useEndTime;
    }

    public void setUseEndTime(java.util.Date useEndTime) {
        this.useEndTime = useEndTime;
    }

    public java.lang.String getMemberName() {
        return memberName;
    }

    public void setMemberName(java.lang.String memberName) {
        this.memberName = memberName;
    }

    public java.lang.String getSellerName() {
        return sellerName;
    }

    public void setSellerName(java.lang.String sellerName) {
        this.sellerName = sellerName;
    }

    public java.lang.String getCouponName() {
        return couponName;
    }

    public void setCouponName(java.lang.String couponName) {
        this.couponName = couponName;
    }

    public java.lang.String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(java.lang.String orderSn) {
        this.orderSn = orderSn;
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
     * 获取序列号
     */
    public java.lang.String getCouponSn() {
        return this.couponSn;
    }

    /**
     * 设置序列号
     */
    public void setCouponSn(java.lang.String couponSn) {
        this.couponSn = couponSn;
    }

    /**
     * 获取密码
     */
    public java.lang.String getPassword() {
        return this.password;
    }

    /**
     * 设置密码
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    /**
     * 获取可使用次数（默认都是1次，不支持多次使用）
     */
    public java.lang.Integer getCanUse() {
        return this.canUse;
    }

    /**
     * 设置可使用次数（默认都是1次，不支持多次使用）
     */
    public void setCanUse(java.lang.Integer canUse) {
        this.canUse = canUse;
    }

    /**
     * 获取领取时间
     */
    public java.util.Date getReceiveTime() {
        return this.receiveTime;
    }

    /**
     * 设置领取时间
     */
    public void setReceiveTime(java.util.Date receiveTime) {
        this.receiveTime = receiveTime;
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
     * 获取使用时间
     */
    public java.util.Date getUseTime() {
        return this.useTime;
    }

    /**
     * 设置使用时间
     */
    public void setUseTime(java.util.Date useTime) {
        this.useTime = useTime;
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

    /**
     * 获取updateUserId
     */
    public java.lang.Integer getUpdateUserId() {
        return this.updateUserId;
    }

    /**
     * 设置updateUserId
     */
    public void setUpdateUserId(java.lang.Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取updateUserName
     */
    public java.lang.String getUpdateUserName() {
        return this.updateUserName;
    }

    /**
     * 设置updateUserName
     */
    public void setUpdateUserName(java.lang.String updateUserName) {
        this.updateUserName = updateUserName;
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

    public java.lang.Integer getChannel() {
        return channel;
    }

    public void setChannel(java.lang.Integer channel) {
        this.channel = channel;
    }

    public boolean getIsuse() {
        return isuse;
    }

    public void setIsuse(boolean isuse) {
        this.isuse = isuse;
    }

}