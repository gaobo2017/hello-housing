package com.ejavashop.entity.news;

import java.io.Serializable;

/**
 * 合作伙伴
 * <p>Table: <strong>news_partner</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>name</td></tr>
 *   <tr><td>image</td><td>{@link java.lang.String}</td><td>image</td><td>varchar</td><td>图片标识</td></tr>
 *   <tr><td>url</td><td>{@link java.lang.String}</td><td>url</td><td>varchar</td><td>链接</td></tr>
 *   <tr><td>sort</td><td>{@link java.lang.Integer}</td><td>sort</td><td>int</td><td>数字越小，越靠前</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>0、不可见；1、可见</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>createId</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class NewsPartner implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4069271985300634113L;
    private java.lang.Integer id;
    private java.lang.String  name;
    private java.lang.String  image;
    private java.lang.String  url;
    private java.lang.Integer sort;
    private java.lang.Integer status;
    private java.lang.Integer createId;
    private java.util.Date    createTime;
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
     * 获取name
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 设置name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * 获取图片标识
     */
    public java.lang.String getImage() {
        return this.image;
    }

    /**
     * 设置图片标识
     */
    public void setImage(java.lang.String image) {
        this.image = image;
    }

    /**
     * 获取链接
     */
    public java.lang.String getUrl() {
        return this.url;
    }

    /**
     * 设置链接
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }

    /**
     * 获取数字越小，越靠前
     */
    public java.lang.Integer getSort() {
        return this.sort;
    }

    /**
     * 设置数字越小，越靠前
     */
    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取0、不可见；1、可见
     */
    public java.lang.Integer getStatus() {
        return this.status;
    }

    /**
     * 设置0、不可见；1、可见
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
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
}