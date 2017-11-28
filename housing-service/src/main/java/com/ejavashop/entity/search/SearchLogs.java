package com.ejavashop.entity.search;

import java.io.Serializable;
/**
 * 搜索历史记录表
 * <p>Table: <strong>search_logs</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>keyword</td><td>{@link java.lang.String}</td><td>keyword</td><td>varchar</td><td>搜索词</td></tr>
 *   <tr><td>ip</td><td>{@link java.lang.String}</td><td>ip</td><td>varchar</td><td>IP地址</td></tr>
 *   <tr><td>siteCookie</td><td>{@link java.lang.String}</td><td>site_cookie</td><td>varchar</td><td>cookie埋点</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>用户不登录存0</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 * </table>
 *
 */
public class SearchLogs implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.String keyword;
 	private java.lang.String ip;
 	private java.lang.String siteCookie;
 	private java.lang.Integer memberId;
 	private java.util.Date createTime;
 	
 		
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
     * 获取搜索词
     */
	public java.lang.String getKeyword(){
		return this.keyword;
	}
 		
	/**
     * 设置搜索词
     */
	public void setKeyword(java.lang.String keyword){
		this.keyword = keyword;
	}
 		
	/**
     * 获取IP地址
     */
	public java.lang.String getIp(){
		return this.ip;
	}
 		
	/**
     * 设置IP地址
     */
	public void setIp(java.lang.String ip){
		this.ip = ip;
	}
 		
	/**
     * 获取cookie埋点
     */
	public java.lang.String getSiteCookie(){
		return this.siteCookie;
	}
 		
	/**
     * 设置cookie埋点
     */
	public void setSiteCookie(java.lang.String siteCookie){
		this.siteCookie = siteCookie;
	}
 		
	/**
     * 获取用户不登录存0
     */
	public java.lang.Integer getMemberId(){
		return this.memberId;
	}
 		
	/**
     * 设置用户不登录存0
     */
	public void setMemberId(java.lang.Integer memberId){
		this.memberId = memberId;
	}
 		
	/**
     * 获取createTime
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置createTime
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 }