package com.ejavashop.entity.member;

import java.io.Serializable;

/**
 * 会员经验值和积分规则
 * <p>Table: <strong>member_rule</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>register</td><td>{@link java.lang.Integer}</td><td>register</td><td>int</td><td>会员注册</td></tr>
 *   <tr><td>login</td><td>{@link java.lang.Integer}</td><td>login</td><td>int</td><td>会员每天登陆</td></tr>
 *   <tr><td>orderEvaluate</td><td>{@link java.lang.Integer}</td><td>order_evaluate</td><td>int</td><td>订单商品评论</td></tr>
 *   <tr><td>orderBuy</td><td>{@link java.lang.Integer}</td><td>order_buy</td><td>int</td><td>购物积分</td></tr>
 *   <tr><td>orderMax</td><td>{@link java.lang.Integer}</td><td>order_max</td><td>int</td><td>购物积分上限</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>1、开始；2、关闭</td></tr>
 * </table>
 *
 */
public class MemberRule implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8035771258380806601L;

    private java.lang.Integer id;
    private java.lang.Integer register;
    private java.lang.Integer login;
    private java.lang.Integer orderEvaluate;
    private java.lang.Integer orderBuy;
    private java.lang.Integer orderMax;
    private java.lang.Integer state;

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
     * 获取会员注册
     */
    public java.lang.Integer getRegister() {
        return this.register;
    }

    /**
     * 设置会员注册
     */
    public void setRegister(java.lang.Integer register) {
        this.register = register;
    }

    /**
     * 获取会员每天登陆
     */
    public java.lang.Integer getLogin() {
        return this.login;
    }

    /**
     * 设置会员每天登陆
     */
    public void setLogin(java.lang.Integer login) {
        this.login = login;
    }

    /**
     * 获取订单商品评论
     */
    public java.lang.Integer getOrderEvaluate() {
        return this.orderEvaluate;
    }

    /**
     * 设置订单商品评论
     */
    public void setOrderEvaluate(java.lang.Integer orderEvaluate) {
        this.orderEvaluate = orderEvaluate;
    }

    /**
     * 获取购物积分
     */
    public java.lang.Integer getOrderBuy() {
        return this.orderBuy;
    }

    /**
     * 设置购物积分
     */
    public void setOrderBuy(java.lang.Integer orderBuy) {
        this.orderBuy = orderBuy;
    }

    /**
     * 获取购物积分上限
     */
    public java.lang.Integer getOrderMax() {
        return this.orderMax;
    }

    /**
     * 设置购物积分上限
     */
    public void setOrderMax(java.lang.Integer orderMax) {
        this.orderMax = orderMax;
    }

    /**
     * 获取1、开始；2、关闭
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置1、开始；2、关闭
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }
}