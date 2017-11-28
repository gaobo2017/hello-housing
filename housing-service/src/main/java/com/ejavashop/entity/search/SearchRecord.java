package com.ejavashop.entity.search;

import java.io.Serializable;
/**
 * 模糊搜索匹配表
 * <p>Table: <strong>search_record</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>keyword</td><td>{@link java.lang.String}</td><td>keyword</td><td>varchar</td><td>关键词</td></tr>
 *   <tr><td>keywordPinyin</td><td>{@link java.lang.String}</td><td>keyword_pinyin</td><td>varchar</td><td>关键词拼音</td></tr>
 *   <tr><td>keywordIndex</td><td>{@link java.lang.Integer}</td><td>keyword_index</td><td>int</td><td>关键词索引数量</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>创建人ID</td></tr>
 *   <tr><td>createName</td><td>{@link java.lang.String}</td><td>create_name</td><td>varchar</td><td>创建人Name</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class SearchRecord implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.String keyword;
 	private java.lang.String keywordPinyin;
 	private java.lang.Integer keywordIndex;
 	private java.lang.Integer createId;
 	private java.lang.String createName;
 	private java.util.Date createTime;
 	private java.util.Date updateTime;
 	
 		
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
     * 获取关键词
     */
	public java.lang.String getKeyword(){
		return this.keyword;
	}
 		
	/**
     * 设置关键词
     */
	public void setKeyword(java.lang.String keyword){
		this.keyword = keyword;
	}
 		
	/**
     * 获取关键词拼音
     */
	public java.lang.String getKeywordPinyin(){
		return this.keywordPinyin;
	}
 		
	/**
     * 设置关键词拼音
     */
	public void setKeywordPinyin(java.lang.String keywordPinyin){
		this.keywordPinyin = keywordPinyin;
	}
 		
	/**
     * 获取关键词索引数量
     */
	public java.lang.Integer getKeywordIndex(){
		return this.keywordIndex;
	}
 		
	/**
     * 设置关键词索引数量
     */
	public void setKeywordIndex(java.lang.Integer keywordIndex){
		this.keywordIndex = keywordIndex;
	}
 		
	/**
     * 获取创建人ID
     */
	public java.lang.Integer getCreateId(){
		return this.createId;
	}
 		
	/**
     * 设置创建人ID
     */
	public void setCreateId(java.lang.Integer createId){
		this.createId = createId;
	}
 		
	/**
     * 获取创建人Name
     */
	public java.lang.String getCreateName(){
		return this.createName;
	}
 		
	/**
     * 设置创建人Name
     */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
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
 		
	/**
     * 获取updateTime
     */
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
 		
	/**
     * 设置updateTime
     */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
 }