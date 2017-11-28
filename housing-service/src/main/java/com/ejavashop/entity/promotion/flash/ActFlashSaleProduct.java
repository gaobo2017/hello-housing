package com.ejavashop.entity.promotion.flash;

import java.io.Serializable;

import com.ejavashop.entity.product.Product;

/**
 * 限时抢购活动商品表
 * <p>Table: <strong>act_flash_sale_product</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>actFlashSaleId</td><td>{@link java.lang.Integer}</td><td>act_flash_sale_id</td><td>int</td><td>限时抢购活动id</td></tr>
 *   <tr><td>actFlashSaleStageId</td><td>{@link java.lang.Integer}</td><td>act_flash_sale_stage_id</td><td>int</td><td>限时抢购活动阶段id</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>商品ID</td></tr>
 *   <tr><td>price</td><td>{@link java.math.BigDecimal}</td><td>price</td><td>decimal</td><td>活动价格</td></tr>
 *   <tr><td>stock</td><td>{@link java.lang.Integer}</td><td>stock</td><td>int</td><td>库存量</td></tr>
 *   <tr><td>actualSales</td><td>{@link java.lang.Integer}</td><td>actual_sales</td><td>int</td><td>已售出数量</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>1、提交审核；2、审核通过；3、审核失败</td></tr>
 *   <tr><td>auditOpinion</td><td>{@link java.lang.String}</td><td>audit_opinion</td><td>text</td><td>审核意见</td></tr>
 *   <tr><td>auditUserId</td><td>{@link java.lang.Integer}</td><td>audit_user_id</td><td>int</td><td>审核人ID</td></tr>
 *   <tr><td>auditUserName</td><td>{@link java.lang.String}</td><td>audit_user_name</td><td>varchar</td><td>审核人名称</td></tr>
 *   <tr><td>auditTime</td><td>{@link java.util.Date}</td><td>audit_time</td><td>datetime</td><td>审核时间</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Integer}</td><td>create_user_id</td><td>int</td><td>createUserId</td></tr>
 *   <tr><td>createUserName</td><td>{@link java.lang.String}</td><td>create_user_name</td><td>varchar</td><td>createUserName</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateUserId</td><td>{@link java.lang.Integer}</td><td>update_user_id</td><td>int</td><td>updateUserId</td></tr>
 *   <tr><td>updateUserName</td><td>{@link java.lang.String}</td><td>update_user_name</td><td>varchar</td><td>updateUserName</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class ActFlashSaleProduct implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = -8991160916946830919L;

    /** 活动状态1、提交审核；2、审核通过；3、审核失败 */
    public static final int      STATUS_1         = 1;
    /** 活动状态1、提交审核；2、审核通过；3、审核失败 */
    public static final int      STATUS_2         = 2;
    /** 活动状态1、提交审核；2、审核通过；3、审核失败 */
    public static final int      STATUS_3         = 3;

    private java.lang.Integer    id;
    private java.lang.Integer    actFlashSaleId;
    private java.lang.Integer    actFlashSaleStageId;
    private java.lang.Integer    sellerId;
    private java.lang.Integer    productId;
    private java.math.BigDecimal price;
    private java.lang.Integer    stock;
    private java.lang.Integer    actualSales;
    private java.lang.Integer    status;
    private java.lang.String     auditOpinion;
    private java.lang.Integer    auditUserId;
    private java.lang.String     auditUserName;
    private java.util.Date       auditTime;
    private java.lang.Integer    createUserId;
    private java.lang.String     createUserName;
    private java.util.Date       createTime;
    private java.lang.Integer    updateUserId;
    private java.lang.String     updateUserName;
    private java.util.Date       updateTime;
    private Integer              sort;                                    //排序号

    //-----------------------数据对象 bg-----------------------------//
    private Product              product;
    //商家
    private String               sellerName;

    //-----------------------数据对象 ed-----------------------------//

    /**
     * 商品名称
     */
    public String getProductName() {
        return this.product == null ? "" : this.product.getName1();
    }

    /**
     * 商品原价
     * @return
     */
    public String getMallPcPrice() {
        return this.product == null ? "" : this.getProduct().getMallPcPrice().toString();
    }

    /**
     * 此商品活动阶段
     * @return
     */
    public String getActTime() {
        return this.product == null ? "" : this.getProduct().getActTime();
    }

    //-----------------------数据对象 ed-----------------------------//

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
     * 获取限时抢购活动id
     */
    public java.lang.Integer getActFlashSaleId() {
        return this.actFlashSaleId;
    }

    /**
     * 设置限时抢购活动id
     */
    public void setActFlashSaleId(java.lang.Integer actFlashSaleId) {
        this.actFlashSaleId = actFlashSaleId;
    }

    /**
     * 获取限时抢购活动阶段id
     */
    public java.lang.Integer getActFlashSaleStageId() {
        return this.actFlashSaleStageId;
    }

    /**
     * 设置限时抢购活动阶段id
     */
    public void setActFlashSaleStageId(java.lang.Integer actFlashSaleStageId) {
        this.actFlashSaleStageId = actFlashSaleStageId;
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
     * 获取活动价格
     */
    public java.math.BigDecimal getPrice() {
        return this.price;
    }

    /**
     * 设置活动价格
     */
    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取库存量
     */
    public java.lang.Integer getStock() {
        return this.stock;
    }

    /**
     * 设置库存量
     */
    public void setStock(java.lang.Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取已售出数量
     */
    public java.lang.Integer getActualSales() {
        return this.actualSales;
    }

    /**
     * 设置已售出数量
     */
    public void setActualSales(java.lang.Integer actualSales) {
        this.actualSales = actualSales;
    }

    /**
     * 获取1、提交审核；2、审核通过；3、审核失败
     */
    public java.lang.Integer getStatus() {
        return this.status;
    }

    /**
     * 设置1、提交审核；2、审核通过；3、审核失败
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    /**
     * 获取审核意见
     */
    public java.lang.String getAuditOpinion() {
        return this.auditOpinion;
    }

    /**
     * 设置审核意见
     */
    public void setAuditOpinion(java.lang.String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    /**
     * 获取审核人ID
     */
    public java.lang.Integer getAuditUserId() {
        return this.auditUserId;
    }

    /**
     * 设置审核人ID
     */
    public void setAuditUserId(java.lang.Integer auditUserId) {
        this.auditUserId = auditUserId;
    }

    /**
     * 获取审核人名称
     */
    public java.lang.String getAuditUserName() {
        return this.auditUserName;
    }

    /**
     * 设置审核人名称
     */
    public void setAuditUserName(java.lang.String auditUserName) {
        this.auditUserName = auditUserName;
    }

    /**
     * 获取审核时间
     */
    public java.util.Date getAuditTime() {
        return this.auditTime;
    }

    /**
     * 设置审核时间
     */
    public void setAuditTime(java.util.Date auditTime) {
        this.auditTime = auditTime;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

}