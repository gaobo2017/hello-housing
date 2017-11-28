package com.ejavashop.entity.promotion.full;

import java.io.Serializable;

/**
 * 满减活动参加日志表
 * <p>Table: <strong>act_full_log</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>actFullId</td><td>{@link java.lang.Integer}</td><td>act_full_id</td><td>int</td><td>满减活动id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员id</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>所属商家id</td></tr>
 *   <tr><td>orderId</td><td>{@link java.lang.Integer}</td><td>order_id</td><td>int</td><td>订单id</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Integer}</td><td>create_user_id</td><td>int</td><td>createUserId</td></tr>
 *   <tr><td>createUserName</td><td>{@link java.lang.String}</td><td>create_user_name</td><td>varchar</td><td>createUserName</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>timestamp</td><td>createTime</td></tr>
 * </table>
 *
 */
public class ActFullLog implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1033026647427372646L;

    private java.lang.Integer id;
    private java.lang.Integer actFullId;
    private java.lang.Integer memberId;
    private java.lang.Integer sellerId;
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
     * 获取满减活动id
     */
    public java.lang.Integer getActFullId() {
        return this.actFullId;
    }

    /**
     * 设置满减活动id
     */
    public void setActFullId(java.lang.Integer actFullId) {
        this.actFullId = actFullId;
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
     * 获取订单id
     */
    public java.lang.Integer getOrderId() {
        return this.orderId;
    }

    /**
     * 设置订单id
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