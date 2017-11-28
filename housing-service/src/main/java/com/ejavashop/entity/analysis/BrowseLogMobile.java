package com.ejavashop.entity.analysis;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>browse_log</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>siteCookie</td><td>{@link java.lang.String}</td><td>site_cookie</td><td>varchar</td><td>cookie埋点</td></tr>
 *   <tr><td>sessionId</td><td>{@link java.lang.String}</td><td>session_id</td><td>varchar</td><td>session标识</td></tr>
 *   <tr><td>userAgent</td><td>{@link java.lang.String}</td><td>user_agent</td><td>text</td><td>客户端详细信息</td></tr>
 *   <tr><td>ipAddress</td><td>{@link java.lang.String}</td><td>ip_address</td><td>varchar</td><td>IP地址</td></tr>
 *   <tr><td>accessedPage</td><td>{@link java.lang.String}</td><td>accessed_page</td><td>varchar</td><td>访问的URL</td></tr>
 *   <tr><td>urlReferer</td><td>{@link java.lang.String}</td><td>url_referer</td><td>varchar</td><td>前一个URL</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>访问时间</td></tr>
 *   <tr><td>browseName</td><td>{@link java.lang.String}</td><td>browse_name</td><td>varchar</td><td>浏览器名称</td></tr>
 *   <tr><td>browserVersion</td><td>{@link java.lang.String}</td><td>browser_version</td><td>varchar</td><td>浏览器版本</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>用户ID，用户没有登录ID为0</td></tr>
 *   <tr><td>ebi</td><td>{@link java.lang.String}</td><td>ebi</td><td>varchar</td><td>ebi</td></tr>
 * </table>
 *
 */
public class BrowseLogMobile implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.String siteCookie;
 	private java.lang.String sessionId;
 	private java.lang.String userAgent;
 	private java.lang.String ipAddress;
 	private java.lang.String accessedPage;
 	private java.lang.String urlReferer;
 	private java.util.Date createTime;
 	private java.lang.String browseName;
 	private java.lang.String browserVersion;
 	private java.lang.Integer memberId;
 	private java.lang.String ebi;
 	
 		
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
     * 获取session标识
     */
	public java.lang.String getSessionId(){
		return this.sessionId;
	}
 		
	/**
     * 设置session标识
     */
	public void setSessionId(java.lang.String sessionId){
		this.sessionId = sessionId;
	}
 		
	/**
     * 获取客户端详细信息
     */
	public java.lang.String getUserAgent(){
		return this.userAgent;
	}
 		
	/**
     * 设置客户端详细信息
     */
	public void setUserAgent(java.lang.String userAgent){
		this.userAgent = userAgent;
	}
 		
	/**
     * 获取IP地址
     */
	public java.lang.String getIpAddress(){
		return this.ipAddress;
	}
 		
	/**
     * 设置IP地址
     */
	public void setIpAddress(java.lang.String ipAddress){
		this.ipAddress = ipAddress;
	}
 		
	/**
     * 获取访问的URL
     */
	public java.lang.String getAccessedPage(){
		return this.accessedPage;
	}
 		
	/**
     * 设置访问的URL
     */
	public void setAccessedPage(java.lang.String accessedPage){
		this.accessedPage = accessedPage;
	}
 		
	/**
     * 获取前一个URL
     */
	public java.lang.String getUrlReferer(){
		return this.urlReferer;
	}
 		
	/**
     * 设置前一个URL
     */
	public void setUrlReferer(java.lang.String urlReferer){
		this.urlReferer = urlReferer;
	}
 		
	/**
     * 获取访问时间
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置访问时间
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 		
	/**
     * 获取浏览器名称
     */
	public java.lang.String getBrowseName(){
		return this.browseName;
	}
 		
	/**
     * 设置浏览器名称
     */
	public void setBrowseName(java.lang.String browseName){
		this.browseName = browseName;
	}
 		
	/**
     * 获取浏览器版本
     */
	public java.lang.String getBrowserVersion(){
		return this.browserVersion;
	}
 		
	/**
     * 设置浏览器版本
     */
	public void setBrowserVersion(java.lang.String browserVersion){
		this.browserVersion = browserVersion;
	}
 		
	/**
     * 获取用户ID，用户没有登录ID为0
     */
	public java.lang.Integer getMemberId(){
		return this.memberId;
	}
 		
	/**
     * 设置用户ID，用户没有登录ID为0
     */
	public void setMemberId(java.lang.Integer memberId){
		this.memberId = memberId;
	}
 		
	/**
     * 获取ebi
     */
	public java.lang.String getEbi(){
		return this.ebi;
	}
 		
	/**
     * 设置ebi
     */
	public void setEbi(java.lang.String ebi){
		this.ebi = ebi;
	}
 }