package com.ejavashop.entity.product;

import java.io.Serializable;

/**
 * 商品咨询管理
 * <p>Table: <strong>product_ask</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>sellerId</td><td>{@link Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>productId</td><td>{@link Integer}</td><td>product_id</td><td>int</td><td>商品ID</td></tr>
 *   <tr><td>userId</td><td>{@link Integer}</td><td>user_id</td><td>int</td><td>咨询人ID</td></tr>
 *   <tr><td>userName</td><td>{@link String}</td><td>user_name</td><td>varchar</td><td>资质人账号</td></tr>
 *   <tr><td>askContent</td><td>{@link String}</td><td>ask_content</td><td>varchar</td><td>咨询内容</td></tr>
 *   <tr><td>replyId</td><td>{@link Integer}</td><td>reply_id</td><td>int</td><td>回复人ID</td></tr>
 *   <tr><td>replyName</td><td>{@link String}</td><td>reply_name</td><td>varchar</td><td>回复人账号</td></tr>
 *   <tr><td>replyContent</td><td>{@link String}</td><td>reply_content</td><td>varchar</td><td>回复内容</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>replyTime</td><td>{@link java.util.Date}</td><td>reply_time</td><td>datetime</td><td>回复时间</td></tr>
 *   <tr><td>state</td><td>{@link Integer}</td><td>state</td><td>tinyint</td><td>1、咨询；2、已经回答；3、前台显示；4、删除</td></tr>
 * </table>
 *
 */
public class ProductAsk implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8745829143169258906L;

    /** 商品咨询状态：1、咨询 */
    public static final int   STATE_1          = 1;

    /** 商品咨询状态：2、已经回答 */
    public static final int   STATE_2          = 2;

    /** 商品咨询状态：3、前台显示 */
    public static final int   STATE_3          = 3;

    /** 商品咨询状态：4、删除 */
    public static final int   STATE_4          = 4;

    private Integer           id;
    private Integer           sellerId;
    private Integer           productId;
    private Integer           userId;
    private String            userName;
    private String            askContent;
    private Integer           replyId;
    private String            replyName;
    private String            replyContent;
    private java.util.Date    createTime;
    private java.util.Date    replyTime;
    private Integer           state;

    // --------额外属性（entity对应表结构之外的属性） start------------------------------
    private String            productName;                             //产品名称
    private String            productLeadLittle;                       //产品主图 小图
    private String            sellerName;                              //商家名称
    // --------额外属性（entity对应表结构之外的属性） end--------------------------------

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
     * 获取商品ID
     */
    public Integer getProductId() {
        return this.productId;
    }

    /**
     * 设置商品ID
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取咨询人ID
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置咨询人ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取资质人账号
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置资质人账号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取咨询内容
     */
    public String getAskContent() {
        return this.askContent;
    }

    /**
     * 设置咨询内容
     */
    public void setAskContent(String askContent) {
        this.askContent = askContent;
    }

    /**
     * 获取回复人ID
     */
    public Integer getReplyId() {
        return this.replyId;
    }

    /**
     * 设置回复人ID
     */
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    /**
     * 获取回复人账号
     */
    public String getReplyName() {
        return this.replyName;
    }

    /**
     * 设置回复人账号
     */
    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    /**
     * 获取回复内容
     */
    public String getReplyContent() {
        return this.replyContent;
    }

    /**
     * 设置回复内容
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
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
     * 获取回复时间
     */
    public java.util.Date getReplyTime() {
        return this.replyTime;
    }

    /**
     * 设置回复时间
     */
    public void setReplyTime(java.util.Date replyTime) {
        this.replyTime = replyTime;
    }

    /**
     * 获取1、咨询；2、已经回答；3、前台显示；4、删除
     */
    public Integer getState() {
        return this.state;
    }

    /**
     * 设置1、咨询；2、已经回答；3、前台显示；4、删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductLeadLittle() {
        return productLeadLittle;
    }

    public void setProductLeadLittle(String productLeadLittle) {
        this.productLeadLittle = productLeadLittle;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

}