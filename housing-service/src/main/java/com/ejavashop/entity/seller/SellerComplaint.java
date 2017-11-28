package com.ejavashop.entity.seller;

import java.io.Serializable;

/**
 * 商家投诉管理
 * <p>Table: <strong>seller_complaint</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>userId</td><td>{@link java.lang.Integer}</td><td>user_id</td><td>int</td><td>投诉人ID</td></tr>
 *   <tr><td>userName</td><td>{@link java.lang.String}</td><td>user_name</td><td>varchar</td><td>投诉人账户</td></tr>
 *   <tr><td>orderId</td><td>{@link java.lang.Integer}</td><td>order_id</td><td>int</td><td>订单ID</td></tr>
 *   <tr><td>orderProductId</td><td>{@link java.lang.Integer}</td><td>order_product_id</td><td>int</td><td>网单ID</td></tr>
 *   <tr><td>productBackId</td><td>{@link java.lang.Integer}</td><td>product_back_id</td><td>int</td><td>退货管理id，如没有置为0</td></tr>
 *   <tr><td>productExchangeId</td><td>{@link java.lang.Integer}</td><td>product_exchange_id</td><td>int</td><td>换货管理id，如没有置为0</td></tr>
 *   <tr><td>content</td><td>{@link java.lang.String}</td><td>content</td><td>text</td><td>投诉内容</td></tr>
 *   <tr><td>image</td><td>{@link java.lang.String}</td><td>image</td><td>varchar</td><td>投诉图片</td></tr>
 *   <tr><td>complaintTime</td><td>{@link java.util.Date}</td><td>complaint_time</td><td>datetime</td><td>投诉时间</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>投诉商家</td></tr>
 *   <tr><td>sellerComplaintTime</td><td>{@link java.util.Date}</td><td>seller_complaint_time</td><td>datetime</td><td>商家申诉时间</td></tr>
 *   <tr><td>sellerCompContent</td><td>{@link java.lang.String}</td><td>seller_comp_content</td><td>text</td><td>商家申诉内容</td></tr>
 *   <tr><td>sellerCompImage</td><td>{@link java.lang.String}</td><td>seller_comp_image</td><td>varchar</td><td>商家申诉图片</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>状态：1、买家投诉待审核；2、买家投诉不通过；3、买家投诉通过；4、卖家申诉待审核；5、卖家申诉不通过；6、卖家申诉通过；</td></tr>
 *   <tr><td>optId</td><td>{@link java.lang.Integer}</td><td>opt_id</td><td>int</td><td>平台处理人ID</td></tr>
 *   <tr><td>optContent</td><td>{@link java.lang.String}</td><td>opt_content</td><td>varchar</td><td>平台处理结果</td></tr>
 *   <tr><td>userContent</td><td>{@link java.lang.String}</td><td>user_content</td><td>varchar</td><td>客户反馈意见</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>optTime</td><td>{@link java.util.Date}</td><td>opt_time</td><td>datetime</td><td>处理时间</td></tr>
 * </table>
 *
 */
public class SellerComplaint implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6052862865720372233L;
    private java.lang.Integer id;                                      //id
    private java.lang.Integer userId;                                  //投诉人ID
    private java.lang.String  userName;                                //投诉人账户
    private java.lang.Integer orderId;                                 //订单ID
    private java.lang.Integer orderProductId;                          //网单ID
    private java.lang.Integer productBackId;                           //退货管理id，如没有置为0
    private java.lang.Integer productExchangeId;                       //换货管理id，如没有置为0
    private java.lang.String  content;                                 //投诉内容
    private java.lang.String  image;                                   //投诉图片
    private java.util.Date    complaintTime;                           //投诉时间
    private java.lang.Integer sellerId;                                //投诉商家
    private java.util.Date    sellerComplaintTime;                     //商家申诉时间
    private java.lang.String  sellerCompContent;                       //商家申诉内容
    private java.lang.String  sellerCompImage;                         //商家申诉图片
    private java.lang.Integer state;                                   //状态：1、买家投诉待审核；2、买家投诉不通过；3、买家投诉通过；4、卖家申诉待审核；5、卖家申诉不通过；6、卖家申诉通过；
    private java.lang.Integer optId;                                   //平台处理人ID
    private java.lang.String  optContent;                              //平台处理结果
    private java.lang.String  userContent;                             //客户反馈意见
    private java.util.Date    createTime;                              //创建时间
    private java.util.Date    optTime;                                 //处理时间

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
     * 获取投诉人ID
     */
    public java.lang.Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置投诉人ID
     */
    public void setUserId(java.lang.Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取投诉人账户
     */
    public java.lang.String getUserName() {
        return this.userName;
    }

    /**
     * 设置投诉人账户
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    /**
     * 获取订单ID
     */
    public java.lang.Integer getOrderId() {
        return this.orderId;
    }

    /**
     * 设置订单ID
     */
    public void setOrderId(java.lang.Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取网单ID
     */
    public java.lang.Integer getOrderProductId() {
        return this.orderProductId;
    }

    /**
     * 设置网单ID
     */
    public void setOrderProductId(java.lang.Integer orderProductId) {
        this.orderProductId = orderProductId;
    }

    /**
     * 获取退货管理id，如没有置为0
     */
    public java.lang.Integer getProductBackId() {
        return this.productBackId;
    }

    /**
     * 设置退货管理id，如没有置为0
     */
    public void setProductBackId(java.lang.Integer productBackId) {
        this.productBackId = productBackId;
    }

    /**
     * 获取换货管理id，如没有置为0
     */
    public java.lang.Integer getProductExchangeId() {
        return this.productExchangeId;
    }

    /**
     * 设置换货管理id，如没有置为0
     */
    public void setProductExchangeId(java.lang.Integer productExchangeId) {
        this.productExchangeId = productExchangeId;
    }

    /**
     * 获取投诉内容
     */
    public java.lang.String getContent() {
        return this.content;
    }

    /**
     * 设置投诉内容
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }

    /**
     * 获取投诉图片
     */
    public java.lang.String getImage() {
        return this.image;
    }

    /**
     * 设置投诉图片
     */
    public void setImage(java.lang.String image) {
        this.image = image;
    }

    /**
     * 获取投诉时间
     */
    public java.util.Date getComplaintTime() {
        return this.complaintTime;
    }

    /**
     * 设置投诉时间
     */
    public void setComplaintTime(java.util.Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    /**
     * 获取投诉商家
     */
    public java.lang.Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置投诉商家
     */
    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取商家申诉时间
     */
    public java.util.Date getSellerComplaintTime() {
        return this.sellerComplaintTime;
    }

    /**
     * 设置商家申诉时间
     */
    public void setSellerComplaintTime(java.util.Date sellerComplaintTime) {
        this.sellerComplaintTime = sellerComplaintTime;
    }

    /**
     * 获取商家申诉内容
     */
    public java.lang.String getSellerCompContent() {
        return this.sellerCompContent;
    }

    /**
     * 设置商家申诉内容
     */
    public void setSellerCompContent(java.lang.String sellerCompContent) {
        this.sellerCompContent = sellerCompContent;
    }

    /**
     * 获取商家申诉图片
     */
    public java.lang.String getSellerCompImage() {
        return this.sellerCompImage;
    }

    /**
     * 设置商家申诉图片
     */
    public void setSellerCompImage(java.lang.String sellerCompImage) {
        this.sellerCompImage = sellerCompImage;
    }

    /**
     * 获取状态：1、买家投诉待审核；2、买家投诉不通过；3、买家投诉通过；4、卖家申诉待审核；5、卖家申诉不通过；6、卖家申诉通过；
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置状态：1、买家投诉待审核；2、买家投诉不通过；3、买家投诉通过；4、卖家申诉待审核；5、卖家申诉不通过；6、卖家申诉通过；
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取平台处理人ID
     */
    public java.lang.Integer getOptId() {
        return this.optId;
    }

    /**
     * 设置平台处理人ID
     */
    public void setOptId(java.lang.Integer optId) {
        this.optId = optId;
    }

    /**
     * 获取平台处理结果
     */
    public java.lang.String getOptContent() {
        return this.optContent;
    }

    /**
     * 设置平台处理结果
     */
    public void setOptContent(java.lang.String optContent) {
        this.optContent = optContent;
    }

    /**
     * 获取客户反馈意见
     */
    public java.lang.String getUserContent() {
        return this.userContent;
    }

    /**
     * 设置客户反馈意见
     */
    public void setUserContent(java.lang.String userContent) {
        this.userContent = userContent;
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
     * 获取处理时间
     */
    public java.util.Date getOptTime() {
        return this.optTime;
    }

    /**
     * 设置处理时间
     */
    public void setOptTime(java.util.Date optTime) {
        this.optTime = optTime;
    }
}