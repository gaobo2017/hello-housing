package com.ejavashop.entity.search;

import java.io.Serializable;

/**
 * 
 * <p>Table: <strong>search_setting</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>keyword</td><td>{@link java.lang.String}</td><td>keyword</td><td>varchar</td><td>搜索框下关键字设置</td></tr>
 *   <tr><td>keywordFilter</td><td>{@link java.lang.Integer}</td><td>keyword_filter</td><td>tinyint</td><td>关键字过滤1、不过滤；2、过滤</td></tr>
 *   <tr><td>indexProductId</td><td>{@link java.lang.Integer}</td><td>index_product_id</td><td>int</td><td>索引处理到最大得商品ID，0为没有处理</td></tr>
 *   <tr><td>indexProductTime</td><td>{@link java.util.Date}</td><td>index_product_time</td><td>datetime</td><td>上次索引处理的时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>更新时间</td></tr>
 * </table>
 *
 */
public class SearchSetting implements Serializable {

    /**
     * 系统默认为0
     */
    public final static int   INDEX_PRODUCT_ID_0 = 0;

    /**
     * 已经创建索引为1
     */
    public final static int   INDEX_PRODUCT_ID_1 = 1;

    /**
     * 敏感词 不过滤 1
     */
    public final static int   KEYWORD_FILTER_1   = 1;

    /**
     * 敏感词 过滤 2
     */
    public final static int   KEYWORD_FILTER_2   = 2;

    private java.lang.Integer id;
    private java.lang.String  keyword;
    private java.lang.Integer keywordFilter;
    private java.lang.Integer indexProductId;
    private java.util.Date    indexProductTime;
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
     * 获取搜索框下关键字设置
     */
    public java.lang.String getKeyword() {
        return this.keyword;
    }

    /**
     * 设置搜索框下关键字设置
     */
    public void setKeyword(java.lang.String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取关键字过滤1、不过滤；2、过滤
     */
    public java.lang.Integer getKeywordFilter() {
        return this.keywordFilter;
    }

    /**
     * 设置关键字过滤1、不过滤；2、过滤
     */
    public void setKeywordFilter(java.lang.Integer keywordFilter) {
        this.keywordFilter = keywordFilter;
    }

    /**
     * 获取索引处理到最大得商品ID，0为没有处理
     */
    public java.lang.Integer getIndexProductId() {
        return this.indexProductId;
    }

    /**
     * 设置索引处理到最大得商品ID，0为没有处理
     */
    public void setIndexProductId(java.lang.Integer indexProductId) {
        this.indexProductId = indexProductId;
    }

    /**
     * 获取上次索引处理的时间
     */
    public java.util.Date getIndexProductTime() {
        return this.indexProductTime;
    }

    /**
     * 设置上次索引处理的时间
     */
    public void setIndexProductTime(java.util.Date indexProductTime) {
        this.indexProductTime = indexProductTime;
    }

    /**
     * 获取更新时间
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置更新时间
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }
}