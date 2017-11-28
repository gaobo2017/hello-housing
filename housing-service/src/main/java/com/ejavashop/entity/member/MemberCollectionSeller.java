package com.ejavashop.entity.member;

import java.io.Serializable;

/**
 * 会员收藏商铺表
 * <p>Table: <strong>member_collection_seller</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商铺ID</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>收藏时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>删除时间</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>1、显示；2、删除</td></tr>
 * </table>
 *
 */
public class MemberCollectionSeller implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -2105935603666088622L;

    /** 会员收藏商铺表 state 状态：1、显示   */
    public final static int   STATE_1          = 1;
    /** 会员收藏商铺表 state 状态：2、删除   */
    public final static int   STATE_2          = 2;

    private java.lang.Integer id;
    private java.lang.Integer memberId;
    private java.lang.Integer sellerId;
    private java.util.Date    createTime;
    private java.util.Date    updateTime;
    private java.lang.Integer state;

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
     * 获取会员ID
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置会员ID
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取商铺ID
     */
    public java.lang.Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置商铺ID
     */
    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取收藏时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置收藏时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取删除时间
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置删除时间
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取1、显示；2、删除
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置1、显示；2、删除
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }
}