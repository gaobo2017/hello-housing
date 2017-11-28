package com.ejavashop.entity.analysis;

import java.io.Serializable;

import com.ejavashop.entity.product.Product;

/**
 * 
 * <p>Table: <strong>product_look_log</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>siteCookie</td><td>{@link java.lang.String}</td><td>site_cookie</td><td>varchar</td><td>cookie埋点</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>用户ID，没有登录ID为0</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>商品ID</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>访问时间</td></tr>
 * </table>
 *
 */
public class ProductLookLog implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4947997181374727517L;

    private java.lang.Integer id;
    private java.lang.String  siteCookie;
    private java.lang.Integer memberId;
    private java.lang.Integer productId;
    private java.lang.String  createTime;

    private Product           product;

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
     * 获取cookie埋点
     */
    public java.lang.String getSiteCookie() {
        return this.siteCookie;
    }

    /**
     * 设置cookie埋点
     */
    public void setSiteCookie(java.lang.String siteCookie) {
        this.siteCookie = siteCookie;
    }

    /**
     * 获取用户ID，没有登录ID为0
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置用户ID，没有登录ID为0
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
     * 获取访问时间
     */
    public java.lang.String getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置访问时间
     */
    public void setCreateTime(java.lang.String createTime) {
        this.createTime = createTime;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}