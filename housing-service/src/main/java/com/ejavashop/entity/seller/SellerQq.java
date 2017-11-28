package com.ejavashop.entity.seller;

import java.io.Serializable;

/**
 * 商家客服QQ
 * <p>Table: <strong>seller_qq</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>sellerId</td><td>{@link Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>qq</td><td>{@link String}</td><td>qq</td><td>varchar</td><td>qq</td></tr>
 *   <tr><td>createId</td><td>{@link Integer}</td><td>create_id</td><td>int</td><td>创建人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>state</td><td>{@link Integer}</td><td>state</td><td>tinyint</td><td>1、显示；2、不显示；3、删除</td></tr>
 * </table>
 *
 */
public class SellerQq implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5580272333137841794L;
    private Integer           id;
    private Integer           sellerId;
    private String            name;
    private String            qq;
    private Integer           createId;
    private java.util.Date    createTime;
    private Integer           state;

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
     * 获取商家ID
     */
    public Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置商家ID
     */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取qq
     */
    public String getQq() {
        return this.qq;
    }

    /**
     * 设置qq
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取创建人
     */
    public Integer getCreateId() {
        return this.createId;
    }

    /**
     * 设置创建人
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
     * 获取创建时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取1、显示；2、不显示；3、删除
     */
    public Integer getState() {
        return this.state;
    }

    /**
     * 设置1、显示；2、不显示；3、删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获得姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     */
    public void setName(String name) {
        this.name = name;
    }

}