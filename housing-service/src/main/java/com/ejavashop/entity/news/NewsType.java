package com.ejavashop.entity.news;

import java.io.Serializable;
import java.util.List;

/**
 * 文章分类
 * <p>Table: <strong>news_type</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>pid</td><td>{@link java.lang.Integer}</td><td>pid</td><td>int</td><td>pid</td></tr>
 *   <tr><td>parentPath</td><td>{@link java.lang.String}</td><td>parent_path</td><td>varchar</td><td>parentPath</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>name</td></tr>
 *   <tr><td>sort</td><td>{@link java.lang.Integer}</td><td>sort</td><td>int</td><td>序号越小，越靠前</td></tr>
 *   <tr><td>image</td><td>{@link java.lang.String}</td><td>image</td><td>varchar</td><td>分类图片</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 * </table>
 *
 */
public class NewsType implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
    private java.lang.Integer pid;
    private java.lang.String  parentPath;
    private java.lang.String  name;
    private java.lang.Integer sort;
    private java.lang.String  image;
    private java.util.Date    createTime;

    /**
     * 该分类下的新闻
     */
    private List<News>        news;

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
     * 获取pid
     */
    public java.lang.Integer getPid() {
        return this.pid;
    }

    /**
     * 设置pid
     */
    public void setPid(java.lang.Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取parentPath
     */
    public java.lang.String getParentPath() {
        return this.parentPath;
    }

    /**
     * 设置parentPath
     */
    public void setParentPath(java.lang.String parentPath) {
        this.parentPath = parentPath;
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
     * 获取序号越小，越靠前
     */
    public java.lang.Integer getSort() {
        return this.sort;
    }

    /**
     * 设置序号越小，越靠前
     */
    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取分类图片
     */
    public java.lang.String getImage() {
        return this.image;
    }

    /**
     * 设置分类图片
     */
    public void setImage(java.lang.String image) {
        this.image = image;
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

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

}