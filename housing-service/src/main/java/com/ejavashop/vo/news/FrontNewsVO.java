package com.ejavashop.vo.news;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 新闻资讯
 *                       
 * @Filename: FrontNewsVO.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public class FrontNewsVO implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private Integer           id;
    private Integer           typeId;
    private String            typePath;
    private String            title;
    private String            content;
    private String            author;
    private String            source;
    private Integer           isOut;
    private String            outUrl;
    private Integer           status;
    private Integer           sort;
    private Integer           isRecommend;
    private Integer           createId;
    private Date              createTime;
    private String            createTimeStr;
    private Date              updateTime;

    private Integer           preId;
    private String            preTitle;
    private String            preTime;
    private String            preURL;

    private Integer           nextId;
    private String            nextTitle;
    private String            nextTime;
    private String            nextURL;

    /**
     * 分类名称
     */
    private String            typeName;

    /**
     * 获取id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取typeId
     */
    public Integer getTypeId() {
        return this.typeId;
    }

    /**
     * 设置typeId
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取typePath
     */
    public String getTypePath() {
        return this.typePath;
    }

    /**
     * 设置typePath
     */
    public void setTypePath(String typePath) {
        this.typePath = typePath;
    }

    /**
     * 获取新闻标题
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * 设置新闻标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取content
     */
    public String getContent() {
        return this.content.replaceAll("\n", "<br/>").replaceAll("\r", "&nbsp;&nbsp;")
            .replaceAll("\"", "\'");
    }

    /**
     * 设置content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取作者
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * 设置作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取来源
     */
    public String getSource() {
        return this.source;
    }

    /**
     * 设置来源
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取是否是外部链接0、不是；1、是
     */
    public Integer getIsOut() {
        return this.isOut;
    }

    /**
     * 设置是否是外部链接0、不是；1、是
     */
    public void setIsOut(Integer isOut) {
        this.isOut = isOut;
    }

    /**
     * 获取外部链接的URL
     */
    public String getOutUrl() {
        return this.outUrl;
    }

    /**
     * 设置外部链接的URL
     */
    public void setOutUrl(String outUrl) {
        this.outUrl = outUrl;
    }

    /**
     * 获取0、不显示；1、显示
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置0、不显示；1、显示
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取排序
     */
    public Integer getSort() {
        return this.sort;
    }

    /**
     * 设置排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取是否推荐文章0、不是推荐文章；1、推荐文章
     */
    public Integer getIsRecommend() {
        return this.isRecommend;
    }

    /**
     * 设置是否推荐文章0、不是推荐文章；1、推荐文章
     */
    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * 获取createId
     */
    public Integer getCreateId() {
        return this.createId;
    }

    /**
     * 设置createId
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * 获取createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取updateTime
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getPreId() {
        return preId;
    }

    public void setPreId(Integer preId) {
        this.preId = preId;
    }

    public String getPreTitle() {
        return preTitle;
    }

    public void setPreTitle(String preTitle) {
        this.preTitle = preTitle;
    }

    public String getPreTime() {
        return preTime;
    }

    public void setPreTime(String preTime) {
        this.preTime = preTime;
    }

    public Integer getNextId() {
        return nextId;
    }

    public void setNextId(Integer nextId) {
        this.nextId = nextId;
    }

    public String getNextTitle() {
        return nextTitle;
    }

    public void setNextTitle(String nextTitle) {
        this.nextTitle = nextTitle;
    }

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }

    public String getPreURL() {
        return preURL;
    }

    public void setPreURL(String preURL) {
        this.preURL = preURL;
    }

    public String getNextURL() {
        return nextURL;
    }

    public void setNextURL(String nextURL) {
        this.nextURL = nextURL;
    }

    public String getCreateTimeStr() {
        if (null != this.createTime)
            this.createTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(this.createTime);
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

}