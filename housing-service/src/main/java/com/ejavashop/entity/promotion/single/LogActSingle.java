package com.ejavashop.entity.promotion.single;

import java.io.Serializable;

/**
 * 单品立减活动日志表
 * <p>Table: <strong>log_act_single</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>optType</td><td>{@link java.lang.String}</td><td>opt_type</td><td>varchar</td><td>操作类型，C、新建，U、修改，D、删除</td></tr>
 *   <tr><td>optUserId</td><td>{@link java.lang.Integer}</td><td>opt_user_id</td><td>int</td><td>操作人ID</td></tr>
 *   <tr><td>optUserName</td><td>{@link java.lang.String}</td><td>opt_user_name</td><td>varchar</td><td>操作人名称</td></tr>
 *   <tr><td>optTime</td><td>{@link java.util.Date}</td><td>opt_time</td><td>timestamp</td><td>操作时间</td></tr>
 *   <tr><td>actSingleId</td><td>{@link java.lang.Integer}</td><td>act_single_id</td><td>int</td><td>单品立减活动ID</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>actSingleName</td><td>{@link java.lang.String}</td><td>act_single_name</td><td>varchar</td><td>单品立减活动名称</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.Integer}</td><td>type</td><td>tinyint</td><td>活动类型1、减免金额；2、折扣</td></tr>
 *   <tr><td>discount</td><td>{@link java.math.BigDecimal}</td><td>discount</td><td>decimal</td><td>类型为减免金额时为金额，折扣类型时为折扣（如0.90为打九折）</td></tr>
 *   <tr><td>productIds</td><td>{@link java.lang.String}</td><td>product_ids</td><td>mediumtext</td><td>商品id列表，逗号隔开，前后有逗号，如果同一个商品有多个单品立减活动取有效活动中最新的一个</td></tr>
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
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class LogActSingle implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = -8122646857211914863L;

    private java.lang.Integer    id;
    private java.lang.String     optType;
    private java.lang.Integer    optUserId;
    private java.lang.String     optUserName;
    private java.util.Date       optTime;
    private java.lang.Integer    actSingleId;
    private java.lang.Integer    sellerId;
    private java.lang.String     actSingleName;
    private java.lang.Integer    type;
    private java.math.BigDecimal discount;
    private java.lang.String     productIds;
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
     * 获取单品立减活动ID
     */
    public java.lang.Integer getActSingleId() {
        return this.actSingleId;
    }

    /**
     * 设置单品立减活动ID
     */
    public void setActSingleId(java.lang.Integer actSingleId) {
        this.actSingleId = actSingleId;
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
     * 获取单品立减活动名称
     */
    public java.lang.String getActSingleName() {
        return this.actSingleName;
    }

    /**
     * 设置单品立减活动名称
     */
    public void setActSingleName(java.lang.String actSingleName) {
        this.actSingleName = actSingleName;
    }

    /**
     * 获取活动类型1、减免金额；2、折扣
     */
    public java.lang.Integer getType() {
        return this.type;
    }

    /**
     * 设置活动类型1、减免金额；2、折扣
     */
    public void setType(java.lang.Integer type) {
        this.type = type;
    }

    /**
     * 获取类型为减免金额时为金额，折扣类型时为折扣（如0.90为打九折）
     */
    public java.math.BigDecimal getDiscount() {
        return this.discount;
    }

    /**
     * 设置类型为减免金额时为金额，折扣类型时为折扣（如0.90为打九折）
     */
    public void setDiscount(java.math.BigDecimal discount) {
        this.discount = discount;
    }

    /**
     * 获取商品id列表，逗号隔开，前后有逗号，如果同一个商品有多个单品立减活动取有效活动中最新的一个
     */
    public java.lang.String getProductIds() {
        return this.productIds;
    }

    /**
     * 设置商品id列表，逗号隔开，前后有逗号，如果同一个商品有多个单品立减活动取有效活动中最新的一个
     */
    public void setProductIds(java.lang.String productIds) {
        this.productIds = productIds;
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