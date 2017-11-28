package com.ejavashop.entity.member;

import java.io.Serializable;

/**
 * 会员收藏商品表
 * <p>Table: <strong>member_collection_product</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>用户ID</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>商品ID</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>收藏时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>删除时间</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>1、正常显示；2、删除</td></tr>
 * </table>
 *
 */
public class MemberCollectionProduct implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4054266399814558989L;
    private java.lang.Integer id;
    private java.lang.Integer memberId;
    private java.lang.Integer productId;
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
     * 获取用户ID
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置用户ID
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取商品ID
     */
    public java.lang.Integer getProductId() {
        return this.productId;
    }

    /**
     * 设置商品ID
     */
    public void setProductId(java.lang.Integer productId) {
        this.productId = productId;
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
     * 获取1、正常显示；2、删除
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置1、正常显示；2、删除
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }
}