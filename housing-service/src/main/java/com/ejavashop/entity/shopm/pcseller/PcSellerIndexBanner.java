package com.ejavashop.entity.shopm.pcseller;

import java.io.Serializable;

/**
 * PC端商家首页轮播图
 * <p>Table: <strong>pc_seller_index_banner</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>title</td><td>{@link java.lang.String}</td><td>title</td><td>varchar</td><td>标题</td></tr>
 *   <tr><td>linkUrl</td><td>{@link java.lang.String}</td><td>link_url</td><td>varchar</td><td>链接地址</td></tr>
 *   <tr><td>image</td><td>{@link java.lang.String}</td><td>image</td><td>varchar</td><td>图片</td></tr>
 *   <tr><td>orderNo</td><td>{@link java.lang.Integer}</td><td>order_no</td><td>int</td><td>排序</td></tr>
 *   <tr><td>startTime</td><td>{@link java.util.Date}</td><td>start_time</td><td>datetime</td><td>展示开始时间</td></tr>
 *   <tr><td>endTime</td><td>{@link java.util.Date}</td><td>end_time</td><td>datetime</td><td>展示结束时间</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>状态1使用2预使用3不使用</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Integer}</td><td>create_user_id</td><td>int</td><td>createUserId</td></tr>
 *   <tr><td>createUserName</td><td>{@link java.lang.String}</td><td>create_user_name</td><td>varchar</td><td>createUserName</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateUserId</td><td>{@link java.lang.Integer}</td><td>update_user_id</td><td>int</td><td>updateUserId</td></tr>
 *   <tr><td>updateUserName</td><td>{@link java.lang.String}</td><td>update_user_name</td><td>varchar</td><td>updateUserName</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class PcSellerIndexBanner implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -7010080557264993581L;

    /** 状态：1使用 */
    public static final int   STATUS_1         = 1;
    /** 状态：2预使用 */
    public static final int   STATUS_2         = 2;
    /** 状态：3不使用 */
    public static final int   STATUS_3         = 3;

    private java.lang.Integer id;
    private java.lang.Integer sellerId;
    private java.lang.String  title;
    private java.lang.String  linkUrl;
    private java.lang.String  image;
    private java.lang.Integer orderNo;
    private java.util.Date    startTime;
    private java.util.Date    endTime;
    private java.lang.Integer status;
    private java.lang.Integer createUserId;
    private java.lang.String  createUserName;
    private java.util.Date    createTime;
    private java.lang.Integer updateUserId;
    private java.lang.String  updateUserName;
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
     * 获取商家ID
     */
    public java.lang.Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置商家ID
     */
    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取标题
     */
    public java.lang.String getTitle() {
        return this.title;
    }

    /**
     * 设置标题
     */
    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    /**
     * 获取链接地址
     */
    public java.lang.String getLinkUrl() {
        return this.linkUrl;
    }

    /**
     * 设置链接地址
     */
    public void setLinkUrl(java.lang.String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /**
     * 获取图片
     */
    public java.lang.String getImage() {
        return this.image;
    }

    /**
     * 设置图片
     */
    public void setImage(java.lang.String image) {
        this.image = image;
    }

    /**
     * 获取排序
     */
    public java.lang.Integer getOrderNo() {
        return this.orderNo;
    }

    /**
     * 设置排序
     */
    public void setOrderNo(java.lang.Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取展示开始时间
     */
    public java.util.Date getStartTime() {
        return this.startTime;
    }

    /**
     * 设置展示开始时间
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取展示结束时间
     */
    public java.util.Date getEndTime() {
        return this.endTime;
    }

    /**
     * 设置展示结束时间
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取状态1使用2预使用3不使用
     */
    public java.lang.Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态1使用2预使用3不使用
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    /**
     * 获取createUserId
     */
    public java.lang.Integer getCreateUserId() {
        return this.createUserId;
    }

    /**
     * 设置createUserId
     */
    public void setCreateUserId(java.lang.Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取createUserName
     */
    public java.lang.String getCreateUserName() {
        return this.createUserName;
    }

    /**
     * 设置createUserName
     */
    public void setCreateUserName(java.lang.String createUserName) {
        this.createUserName = createUserName;
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
     * 获取updateUserId
     */
    public java.lang.Integer getUpdateUserId() {
        return this.updateUserId;
    }

    /**
     * 设置updateUserId
     */
    public void setUpdateUserId(java.lang.Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取updateUserName
     */
    public java.lang.String getUpdateUserName() {
        return this.updateUserName;
    }

    /**
     * 设置updateUserName
     */
    public void setUpdateUserName(java.lang.String updateUserName) {
        this.updateUserName = updateUserName;
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