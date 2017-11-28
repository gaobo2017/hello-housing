package com.ejavashop.entity.order;

import java.io.Serializable;
/**
 * 订单支付记录表
 * <p>Table: <strong>order_pay_log</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>ordersId</td><td>{@link java.lang.Integer}</td><td>orders_id</td><td>int</td><td>ordersId</td></tr>
 *   <tr><td>ordersSn</td><td>{@link java.lang.String}</td><td>orders_sn</td><td>varchar</td><td>ordersSn</td></tr>
 *   <tr><td>payStatus</td><td>{@link java.lang.String}</td><td>pay_status</td><td>varchar</td><td>支付方式</td></tr>
 *   <tr><td>payContent</td><td>{@link java.lang.String}</td><td>pay_content</td><td>text</td><td>支付返回的代码</td></tr>
 *   <tr><td>payMoney</td><td>{@link java.math.BigDecimal}</td><td>pay_money</td><td>decimal</td><td>支付金额</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>memberId</td></tr>
 *   <tr><td>memberName</td><td>{@link java.lang.String}</td><td>member_name</td><td>varchar</td><td>memberName</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>支付时间</td></tr>
 * </table>
 *
 */
public class OrderPayLog implements Serializable {
 
 	private java.lang.Integer id; //id
 	private java.lang.Integer ordersId; //ordersId
 	private java.lang.String ordersSn; //ordersSn
 	private java.lang.String payStatus; //支付方式
 	private java.lang.String payContent; //支付返回的代码
 	private java.math.BigDecimal payMoney; //支付金额
 	private java.lang.Integer memberId; //memberId
 	private java.lang.String memberName; //memberName
 	private java.util.Date createTime; //支付时间
 	
 		
 		
	/**
     * 获取id
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置id
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
 		
	/**
     * 获取ordersId
     */
	public java.lang.Integer getOrdersId(){
		return this.ordersId;
	}
 		
	/**
     * 设置ordersId
     */
	public void setOrdersId(java.lang.Integer ordersId){
		this.ordersId = ordersId;
	}
 		
 		
	/**
     * 获取ordersSn
     */
	public java.lang.String getOrdersSn(){
		return this.ordersSn;
	}
 		
	/**
     * 设置ordersSn
     */
	public void setOrdersSn(java.lang.String ordersSn){
		this.ordersSn = ordersSn;
	}
 		
 		
	/**
     * 获取支付方式
     */
	public java.lang.String getPayStatus(){
		return this.payStatus;
	}
 		
	/**
     * 设置支付方式
     */
	public void setPayStatus(java.lang.String payStatus){
		this.payStatus = payStatus;
	}
 		
 		
	/**
     * 获取支付返回的代码
     */
	public java.lang.String getPayContent(){
		return this.payContent;
	}
 		
	/**
     * 设置支付返回的代码
     */
	public void setPayContent(java.lang.String payContent){
		this.payContent = payContent;
	}
 		
 		
	/**
     * 获取支付金额
     */
	public java.math.BigDecimal getPayMoney(){
		return this.payMoney;
	}
 		
	/**
     * 设置支付金额
     */
	public void setPayMoney(java.math.BigDecimal payMoney){
		this.payMoney = payMoney;
	}
 		
 		
	/**
     * 获取memberId
     */
	public java.lang.Integer getMemberId(){
		return this.memberId;
	}
 		
	/**
     * 设置memberId
     */
	public void setMemberId(java.lang.Integer memberId){
		this.memberId = memberId;
	}
 		
 		
	/**
     * 获取memberName
     */
	public java.lang.String getMemberName(){
		return this.memberName;
	}
 		
	/**
     * 设置memberName
     */
	public void setMemberName(java.lang.String memberName){
		this.memberName = memberName;
	}
 		
 		
	/**
     * 获取支付时间
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置支付时间
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 }