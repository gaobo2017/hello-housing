package com.ejavashop.entity.promotion.flash;

import java.io.Serializable;

/**
 * 限时抢购活动日志表
 * <p>Table: <strong>log_act_flash_sale</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>optType</td><td>{@link java.lang.String}</td><td>opt_type</td><td>varchar</td><td>操作类型，C、新建，U、修改，D、删除</td></tr>
 *   <tr><td>optUserId</td><td>{@link java.lang.Integer}</td><td>opt_user_id</td><td>int</td><td>操作人ID</td></tr>
 *   <tr><td>optUserName</td><td>{@link java.lang.String}</td><td>opt_user_name</td><td>varchar</td><td>操作人名称</td></tr>
 *   <tr><td>optTime</td><td>{@link java.util.Date}</td><td>opt_time</td><td>timestamp</td><td>操作时间</td></tr>
 *   <tr><td>actFlashSaleId</td><td>{@link java.lang.Integer}</td><td>act_flash_sale_id</td><td>int</td><td>actFlashSaleId</td></tr>
 *   <tr><td>actFlashSaleName</td><td>{@link java.lang.String}</td><td>act_flash_sale_name</td><td>varchar</td><td>活动名称</td></tr>
 *   <tr><td>actDate</td><td>{@link java.util.Date}</td><td>act_date</td><td>datetime</td><td>活动日期</td></tr>
 *   <tr><td>channel</td><td>{@link java.lang.Integer}</td><td>channel</td><td>tinyint</td><td>活动应用渠道1、通用；2、PC；3、Mobile</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>1、新建；2、商品征集；3、征集结束；4、作废；5、上架；6、下架</td></tr>
 *   <tr><td>auditOpinion</td><td>{@link java.lang.String}</td><td>audit_opinion</td><td>text</td><td>审核意见</td></tr>
 *   <tr><td>auditRule</td><td>{@link java.lang.String}</td><td>audit_rule</td><td>text</td><td>申请规则，用于给商家申请时的须知</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>varchar</td><td>活动描述</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Integer}</td><td>create_user_id</td><td>int</td><td>createUserId</td></tr>
 *   <tr><td>createUserName</td><td>{@link java.lang.String}</td><td>create_user_name</td><td>varchar</td><td>createUserName</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateUserId</td><td>{@link java.lang.Integer}</td><td>update_user_id</td><td>int</td><td>updateUserId</td></tr>
 *   <tr><td>updateUserName</td><td>{@link java.lang.String}</td><td>update_user_name</td><td>varchar</td><td>updateUserName</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class LogActFlashSale implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4907709191906467166L;

    private java.lang.Integer id;
    private java.lang.String  optType;
    private java.lang.Integer optUserId;
    private java.lang.String  optUserName;
    private java.util.Date    optTime;
    private java.lang.Integer actFlashSaleId;
    private java.lang.String  actFlashSaleName;
    private java.util.Date    actDate;
    private java.lang.Integer channel;
    private java.lang.Integer status;
    private java.lang.String  auditOpinion;
    private java.lang.String  auditRule;
    private java.lang.String  remark;
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
     * 获取操作类型，C、新建，U、修改，D、删除
     */
    public java.lang.String getOptType() {
        return this.optType;
    }

    /**
     * 设置操作类型，C、新建，U、修改，D、删除
     */
    public void setOptType(java.lang.String optType) {
        this.optType = optType;
    }

    /**
     * 获取操作人ID
     */
    public java.lang.Integer getOptUserId() {
        return this.optUserId;
    }

    /**
     * 设置操作人ID
     */
    public void setOptUserId(java.lang.Integer optUserId) {
        this.optUserId = optUserId;
    }

    /**
     * 获取操作人名称
     */
    public java.lang.String getOptUserName() {
        return this.optUserName;
    }

    /**
     * 设置操作人名称
     */
    public void setOptUserName(java.lang.String optUserName) {
        this.optUserName = optUserName;
    }

    /**
     * 获取操作时间
     */
    public java.util.Date getOptTime() {
        return this.optTime;
    }

    /**
     * 设置操作时间
     */
    public void setOptTime(java.util.Date optTime) {
        this.optTime = optTime;
    }

    /**
     * 获取actFlashSaleId
     */
    public java.lang.Integer getActFlashSaleId() {
        return this.actFlashSaleId;
    }

    /**
     * 设置actFlashSaleId
     */
    public void setActFlashSaleId(java.lang.Integer actFlashSaleId) {
        this.actFlashSaleId = actFlashSaleId;
    }

    /**
     * 获取活动名称
     */
    public java.lang.String getActFlashSaleName() {
        return this.actFlashSaleName;
    }

    /**
     * 设置活动名称
     */
    public void setActFlashSaleName(java.lang.String actFlashSaleName) {
        this.actFlashSaleName = actFlashSaleName;
    }

    /**
     * 获取活动日期
     */
    public java.util.Date getActDate() {
        return this.actDate;
    }

    /**
     * 设置活动日期
     */
    public void setActDate(java.util.Date actDate) {
        this.actDate = actDate;
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
     * 获取1、新建；2、商品征集；3、征集结束；4、作废；5、上架；6、下架
     */
    public java.lang.Integer getStatus() {
        return this.status;
    }

    /**
     * 设置1、新建；2、商品征集；3、征集结束；4、作废；5、上架；6、下架
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
     * 获取申请规则，用于给商家申请时的须知
     */
    public java.lang.String getAuditRule() {
        return this.auditRule;
    }

    /**
     * 设置申请规则，用于给商家申请时的须知
     */
    public void setAuditRule(java.lang.String auditRule) {
        this.auditRule = auditRule;
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