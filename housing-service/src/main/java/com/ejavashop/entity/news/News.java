package com.ejavashop.entity.news;

import java.io.Serializable;

/**
 * 新闻资讯
 * <p>Table: <strong>news</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>typeId</td><td>{@link java.lang.Integer}</td><td>type_id</td><td>int</td><td>typeId</td></tr>
 *   <tr><td>typePath</td><td>{@link java.lang.String}</td><td>type_path</td><td>varchar</td><td>typePath</td></tr>
 *   <tr><td>title</td><td>{@link java.lang.String}</td><td>title</td><td>varchar</td><td>新闻标题</td></tr>
 *   <tr><td>content</td><td>{@link java.lang.String}</td><td>content</td><td>text</td><td>content</td></tr>
 *   <tr><td>author</td><td>{@link java.lang.String}</td><td>author</td><td>varchar</td><td>作者</td></tr>
 *   <tr><td>source</td><td>{@link java.lang.String}</td><td>source</td><td>varchar</td><td>来源</td></tr>
 *   <tr><td>isOut</td><td>{@link java.lang.Integer}</td><td>is_out</td><td>tinyint</td><td>是否是外部链接0、不是；1、是</td></tr>
 *   <tr><td>outUrl</td><td>{@link java.lang.String}</td><td>out_url</td><td>varchar</td><td>外部链接的URL</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>0、不显示；1、显示</td></tr>
 *   <tr><td>sort</td><td>{@link java.lang.Integer}</td><td>sort</td><td>int</td><td>排序</td></tr>
 *   <tr><td>isRecommend</td><td>{@link java.lang.Integer}</td><td>is_recommend</td><td>tinyint</td><td>是否推荐文章0、不是推荐文章；1、推荐文章</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>createId</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class News implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
    private java.lang.Integer typeId;
    private java.lang.String  typePath;
    private java.lang.String  title;
    private java.lang.String  content;
    private java.lang.String  author;
    private java.lang.String  source;
    private java.lang.Integer isOut;
    private java.lang.String  outUrl;
    private java.lang.Integer status;
    private java.lang.Integer sort;
    private java.lang.Integer isRecommend;
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
     * 获取typeId
     */
    public java.lang.Integer getTypeId() {
        return this.typeId;
    }

    /**
     * 设置typeId
     */
    public void setTypeId(java.lang.Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取typePath
     */
    public java.lang.String getTypePath() {
        return this.typePath;
    }

    /**
     * 设置typePath
     */
    public void setTypePath(java.lang.String typePath) {
        this.typePath = typePath;
    }

    /**
     * 获取新闻标题
     */
    public java.lang.String getTitle() {
        return this.title;
    }

    /**
     * 设置新闻标题
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    /**
     * 获取content
     */
    public java.lang.String getContent() {
        return this.content;
    }

    /**
     * 设置content
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }

    /**
     * 获取作者
     */
    public java.lang.String getAuthor() {
        return this.author;
    }

    /**
     * 设置作者
     */
    public void setAuthor(java.lang.String author) {
        this.author = author;
    }

    /**
     * 获取来源
     */
    public java.lang.String getSource() {
        return this.source;
    }

    /**
     * 设置来源
     */
    public void setSource(java.lang.String source) {
        this.source = source;
    }

    /**
     * 获取是否是外部链接0、不是；1、是
     */
    public java.lang.Integer getIsOut() {
        return this.isOut;
    }

    /**
     * 设置是否是外部链接0、不是；1、是
     */
    public void setIsOut(java.lang.Integer isOut) {
        this.isOut = isOut;
    }

    /**
     * 获取外部链接的URL
     */
    public java.lang.String getOutUrl() {
        return this.outUrl;
    }

    /**
     * 设置外部链接的URL
     */
    public void setOutUrl(java.lang.String outUrl) {
        this.outUrl = outUrl;
    }

    /**
     * 获取0、不显示；1、显示
     */
    public java.lang.Integer getStatus() {
        return this.status;
    }

    /**
     * 设置0、不显示；1、显示
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    /**
     * 获取排序
     */
    public java.lang.Integer getSort() {
        return this.sort;
    }

    /**
     * 设置排序
     */
    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取是否推荐文章0、不是推荐文章；1、推荐文章
     */
    public java.lang.Integer getIsRecommend() {
        return this.isRecommend;
    }

    /**
     * 设置是否推荐文章0、不是推荐文章；1、推荐文章
     */
    public void setIsRecommend(java.lang.Integer isRecommend) {
        this.isRecommend = isRecommend;
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