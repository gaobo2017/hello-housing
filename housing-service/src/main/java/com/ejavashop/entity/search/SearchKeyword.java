package com.ejavashop.entity.search;

import java.io.Serializable;

/**
 * 
 * <p>Table: <strong>search_keyword</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>keyword</td><td>{@link java.lang.String}</td><td>keyword</td><td>varchar</td><td>关键字</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>添加时间</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>createId</td></tr>
 *   <tr><td>createName</td><td>{@link java.lang.String}</td><td>create_name</td><td>varchar</td><td>createName</td></tr>
 * </table>
 *
 */
public class SearchKeyword implements Serializable {

    private java.lang.Integer id;
    private java.lang.String  keyword;
    private java.util.Date    createTime;
    private java.lang.Integer createId;
    private java.lang.String  createName;
    private java.util.Date    updateTime;

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
     * 获取关键字
     */
    public java.lang.String getKeyword() {
        return this.keyword;
    }

    /**
     * 设置关键字
     */
    public void setKeyword(java.lang.String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取添加时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置添加时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取createId
     */
    public java.lang.Integer getCreateId() {
        return this.createId;
    }

    /**
     * 设置createId
     */
    public void setCreateId(java.lang.Integer createId) {
        this.createId = createId;
    }

    /**
     * 获取createName
     */
    public java.lang.String getCreateName() {
        return this.createName;
    }

    /**
     * 设置createName
     */
    public void setCreateName(java.lang.String createName) {
        this.createName = createName;
    }

    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

}