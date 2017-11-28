package com.ejavashop.vo.seller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SellerComplaintVO implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4429694098991215507L;
    private Integer           id;                                      //id
    private Integer           userId;                                  //投诉人ID
    private String            userName;                                //投诉人账户
    private Integer           orderId;                                 //订单ID
    //订单号
    private String            orderSn;

    private Integer           orderProductId;                          //网单ID
    private String            orderProductName;                        //投诉商品
    private String            content;                                 //投诉内容
    private String            image;                                   //投诉图片
    private Date              complaintTime;                           //投诉时间
    private String            complaintTimeStr;
    private Integer           sellerId;                                //投诉商家
    //商家名称
    private String            sellerName;
    private Date              sellerComplaintTime;                     //商家申诉时间
    private String            sellerCompContent;                       //商家申诉内容
    private String            sellerCompImage;                         //商家申诉图片
    private Integer           state;                                   //1、投诉；2、处理；
    private Integer           optId;                                   //平台处理人ID
    private String            optContent;                              //平台处理结果
    private String            userContent;                             //客户反馈意见
    private Date              createTime;                              //创建时间
    private Date              optTime;                                 //处理时间

    private Integer           productBackId;                           //退货管理id，如没有置为0
    private Integer           productExchangeId;                       //换货管理id，如没有置为0

    /**
     * 来源
     */
    private String            source;
    /**
     * 退/换货问题描述
     */
    private String            question;

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
     * 获取投诉人ID
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置投诉人ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取投诉人账户
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置投诉人账户
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取订单ID
     */
    public Integer getOrderId() {
        return this.orderId;
    }

    /**
     * 设置订单ID
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取网单ID
     */
    public Integer getOrderProductId() {
        return this.orderProductId;
    }

    /**
     * 设置网单ID
     */
    public void setOrderProductId(Integer orderProductId) {
        this.orderProductId = orderProductId;
    }

    /**
     * 获取投诉内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置投诉内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取投诉图片
     */
    public String getImage() {
        return this.image;
    }

    /**
     * 设置投诉图片
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 获取投诉时间
     */
    public Date getComplaintTime() {
        return this.complaintTime;
    }

    /**
     * 设置投诉时间
     */
    public void setComplaintTime(Date complaintTime) {
        this.complaintTime = complaintTime;
    }

    /**
     * 获取投诉商家
     */
    public Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置投诉商家
     */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取商家申诉时间
     */
    public Date getSellerComplaintTime() {
        return this.sellerComplaintTime;
    }

    /**
     * 设置商家申诉时间
     */
    public void setSellerComplaintTime(Date sellerComplaintTime) {
        this.sellerComplaintTime = sellerComplaintTime;
    }

    /**
     * 获取商家申诉内容
     */
    public String getSellerCompContent() {
        return this.sellerCompContent;
    }

    /**
     * 设置商家申诉内容
     */
    public void setSellerCompContent(String sellerCompContent) {
        this.sellerCompContent = sellerCompContent;
    }

    /**
     * 获取商家申诉图片
     */
    public String getSellerCompImage() {
        return this.sellerCompImage;
    }

    /**
     * 设置商家申诉图片
     */
    public void setSellerCompImage(String sellerCompImage) {
        this.sellerCompImage = sellerCompImage;
    }

    /**
     * 获取1、投诉；2、处理；
     */
    public Integer getState() {
        return this.state;
    }

    /**
     * 设置1、投诉；2、处理；
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取平台处理人ID
     */
    public Integer getOptId() {
        return this.optId;
    }

    /**
     * 设置平台处理人ID
     */
    public void setOptId(Integer optId) {
        this.optId = optId;
    }

    /**
     * 获取平台处理结果
     */
    public String getOptContent() {
        return this.optContent;
    }

    /**
     * 设置平台处理结果
     */
    public void setOptContent(String optContent) {
        this.optContent = optContent;
    }

    /**
     * 获取客户反馈意见
     */
    public String getUserContent() {
        return this.userContent;
    }

    /**
     * 设置客户反馈意见
     */
    public void setUserContent(String userContent) {
        this.userContent = userContent;
    }

    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取处理时间
     */
    public Date getOptTime() {
        return this.optTime;
    }

    /**
     * 设置处理时间
     */
    public void setOptTime(Date optTime) {
        this.optTime = optTime;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getComplaintTimeStr() {
        if (this.complaintTime == null)
            return null;
        this.complaintTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(this.complaintTime);
        return this.complaintTimeStr;
    }

    public void setComplaintTimeStr(String complaintTimeStr) {
        this.complaintTimeStr = complaintTimeStr;
    }

    public String getOrderProductName() {
        return orderProductName;
    }

    public void setOrderProductName(String orderProductName) {
        this.orderProductName = orderProductName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getProductBackId() {
        return productBackId;
    }

    public void setProductBackId(Integer productBackId) {
        this.productBackId = productBackId;
    }

    public Integer getProductExchangeId() {
        return productExchangeId;
    }

    public void setProductExchangeId(Integer productExchangeId) {
        this.productExchangeId = productExchangeId;
    }

}