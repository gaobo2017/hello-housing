package com.ejavashop.entity.promotion.full;

import java.io.Serializable;

/**
 * 满减活动表
 * <p>Table: <strong>act_full</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>actFullName</td><td>{@link java.lang.String}</td><td>act_full_name</td><td>varchar</td><td>满减活动名称</td></tr>
 *   <tr><td>firstFull</td><td>{@link java.math.BigDecimal}</td><td>first_full</td><td>decimal</td><td>第一档满额</td></tr>
 *   <tr><td>firstDiscount</td><td>{@link java.math.BigDecimal}</td><td>first_discount</td><td>decimal</td><td>第一档减免额</td></tr>
 *   <tr><td>secondFull</td><td>{@link java.math.BigDecimal}</td><td>second_full</td><td>decimal</td><td>第二档满额</td></tr>
 *   <tr><td>secondDiscount</td><td>{@link java.math.BigDecimal}</td><td>second_discount</td><td>decimal</td><td>第二档减免额</td></tr>
 *   <tr><td>thirdFull</td><td>{@link java.math.BigDecimal}</td><td>third_full</td><td>decimal</td><td>第三档满额</td></tr>
 *   <tr><td>thirdDiscount</td><td>{@link java.math.BigDecimal}</td><td>third_discount</td><td>decimal</td><td>第三档减免额</td></tr>
 *   <tr><td>startTime</td><td>{@link java.util.Date}</td><td>start_time</td><td>datetime</td><td>开始时间</td></tr>
 *   <tr><td>endTime</td><td>{@link java.util.Date}</td><td>end_time</td><td>datetime</td><td>结束时间</td></tr>
 *   <tr><td>channel</td><td>{@link java.lang.Integer}</td><td>channel</td><td>tinyint</td><td>活动应用渠道1、通用；2、PC；3、Mobile</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>1、新建；2、提交审核；3、审核通过；4、审核失败；5、上架；6、下架</td></tr>
 *   <tr><td>auditOpinion</td><td>{@link java.lang.String}</td><td>audit_opinion</td><td>text</td><td>审核意见</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>varchar</td><td>活动描述</td></tr>
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
public class ActFull implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = 7460284007874093677L;

    /** 活动状态1、新建；2、提交审核；3、审核通过；4、审核失败；5、上架；6、下架 */
    public static final int      STATUS_1         = 1;
    /** 活动状态1、新建；2、提交审核；3、审核通过；4、审核失败；5、上架；6、下架 */
    public static final int      STATUS_2         = 2;
    /** 活动状态1、新建；2、提交审核；3、审核通过；4、审核失败；5、上架；6、下架 */
    public static final int      STATUS_3         = 3;
    /** 活动状态1、新建；2、提交审核；3、审核通过；4、审核失败；5、上架；6、下架 */
    public static final int      STATUS_4         = 4;
    /** 活动状态1、新建；2、提交审核；3、审核通过；4、审核失败；5、上架；6、下架 */
    public static final int      STATUS_5         = 5;
    /** 活动状态1、新建；2、提交审核；3、审核通过；4、审核失败；5、上架；6、下架 */
    public static final int      STATUS_6         = 6;

    private java.lang.Integer    id;
    private java.lang.Integer    sellerId;
    private java.lang.String     actFullName;
    private java.math.BigDecimal firstFull;
    private java.math.BigDecimal firstDiscount;
    private java.math.BigDecimal secondFull;
    private java.math.BigDecimal secondDiscount;
    private java.math.BigDecimal thirdFull;
    private java.math.BigDecimal thirdDiscount;
    private java.util.Date       startTime;
    private java.util.Date       endTime;
    private java.lang.Integer    channel;
    private java.lang.Integer    status;
    private java.lang.String     auditOpinion;
    private java.lang.String     remark;
    private java.lang.Integer    auditUserId;
    private java.lang.String     auditUserName;
    private java.util.Date       auditTime;
    private java.lang.Integer    createUserId;
    private java.lang.String     createUserName;
    private java.util.Date       createTime;
    private java.lang.Integer    updateUserId;
    private java.lang.String     updateUserName;
    private java.util.Date       updateTime;

    private String               sellerName;                             // 店铺名称

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

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
     * 获取满减活动名称
     */
    public java.lang.String getActFullName() {
        return this.actFullName;
    }

    /**
     * 设置满减活动名称
     */
    public void setActFullName(java.lang.String actFullName) {
        this.actFullName = actFullName;
    }

    /**
     * 获取第一档满额
     */
    public java.math.BigDecimal getFirstFull() {
        return this.firstFull;
    }

    /**
     * 设置第一档满额
     */
    public void setFirstFull(java.math.BigDecimal firstFull) {
        this.firstFull = firstFull;
    }

    /**
     * 获取第一档减免额
     */
    public java.math.BigDecimal getFirstDiscount() {
        return this.firstDiscount;
    }

    /**
     * 设置第一档减免额
     */
    public void setFirstDiscount(java.math.BigDecimal firstDiscount) {
        this.firstDiscount = firstDiscount;
    }

    /**
     * 获取第二档满额
     */
    public java.math.BigDecimal getSecondFull() {
        return this.secondFull;
    }

    /**
     * 设置第二档满额
     */
    public void setSecondFull(java.math.BigDecimal secondFull) {
        this.secondFull = secondFull;
    }

    /**
     * 获取第二档减免额
     */
    public java.math.BigDecimal getSecondDiscount() {
        return this.secondDiscount;
    }

    /**
     * 设置第二档减免额
     */
    public void setSecondDiscount(java.math.BigDecimal secondDiscount) {
        this.secondDiscount = secondDiscount;
    }

    /**
     * 获取第三档满额
     */
    public java.math.BigDecimal getThirdFull() {
        return this.thirdFull;
    }

    /**
     * 设置第三档满额
     */
    public void setThirdFull(java.math.BigDecimal thirdFull) {
        this.thirdFull = thirdFull;
    }

    /**
     * 获取第三档减免额
     */
    public java.math.BigDecimal getThirdDiscount() {
        return this.thirdDiscount;
    }

    /**
     * 设置第三档减免额
     */
    public void setThirdDiscount(java.math.BigDecimal thirdDiscount) {
        this.thirdDiscount = thirdDiscount;
    }

    /**
     * 获取开始时间
     */
    public java.util.Date getStartTime() {
        return this.startTime;
    }

    /**
     * 设置开始时间
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     */
    public java.util.Date getEndTime() {
        return this.endTime;
    }

    /**
     * 设置结束时间
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取活动应用渠道1、通用；2、PC；3、Mobile
     */
    public java.lang.Integer getChannel() {
        return this.channel;
    }

    /**
     * 设置活动应用渠道1、通用；2、PC；3、Mobile
     */
    public void setChannel(java.lang.Integer channel) {
        this.channel = channel;
    }

    /**
     * 获取1、新建；2、提交审核；3、审核通过；4、审核失败；5、上架；6、下架
     */
    public java.lang.Integer getStatus() {
        return this.status;
    }

    /**
     * 设置1、新建；2、提交审核；3、审核通过；4、审核失败；5、上架；6、下架
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
     * 获取活动描述
     */
    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * 设置活动描述
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
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
}