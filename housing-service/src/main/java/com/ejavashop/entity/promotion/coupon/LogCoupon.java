package com.ejavashop.entity.promotion.coupon;

import java.io.Serializable;

/**
 * 优惠券日志表
 * <p>Table: <strong>log_coupon</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>optType</td><td>{@link java.lang.String}</td><td>opt_type</td><td>varchar</td><td>操作类型，C、新建，U、修改，D、删除</td></tr>
 *   <tr><td>optUserId</td><td>{@link java.lang.Integer}</td><td>opt_user_id</td><td>int</td><td>操作人ID</td></tr>
 *   <tr><td>optUserName</td><td>{@link java.lang.String}</td><td>opt_user_name</td><td>varchar</td><td>操作人名称</td></tr>
 *   <tr><td>optTime</td><td>{@link java.util.Date}</td><td>opt_time</td><td>timestamp</td><td>操作时间</td></tr>
 *   <tr><td>couponId</td><td>{@link java.lang.Integer}</td><td>coupon_id</td><td>int</td><td>优惠券ID</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>couponName</td><td>{@link java.lang.String}</td><td>coupon_name</td><td>varchar</td><td>优惠券名称</td></tr>
 *   <tr><td>prefix</td><td>{@link java.lang.String}</td><td>prefix</td><td>varchar</td><td>优惠券前缀</td></tr>
 *   <tr><td>couponValue</td><td>{@link java.math.BigDecimal}</td><td>coupon_value</td><td>decimal</td><td>优惠券面值</td></tr>
 *   <tr><td>minAmount</td><td>{@link java.math.BigDecimal}</td><td>min_amount</td><td>decimal</td><td>适用的最低订单金额</td></tr>
 *   <tr><td>sendStartTime</td><td>{@link java.util.Date}</td><td>send_start_time</td><td>datetime</td><td>发放开始时间</td></tr>
 *   <tr><td>sendEndTime</td><td>{@link java.util.Date}</td><td>send_end_time</td><td>datetime</td><td>发放结束时间</td></tr>
 *   <tr><td>useStartTime</td><td>{@link java.util.Date}</td><td>use_start_time</td><td>datetime</td><td>使用起始时间</td></tr>
 *   <tr><td>useEndTime</td><td>{@link java.util.Date}</td><td>use_end_time</td><td>datetime</td><td>使用截止时间</td></tr>
 *   <tr><td>personLimitNum</td><td>{@link java.lang.Integer}</td><td>person_limit_num</td><td>int</td><td>每个会员限制领取的张数，0为不限</td></tr>
 *   <tr><td>totalLimitNum</td><td>{@link java.lang.Integer}</td><td>total_limit_num</td><td>int</td><td>总共允许发放的总张数</td></tr>
 *   <tr><td>receivedNum</td><td>{@link java.lang.Integer}</td><td>received_num</td><td>int</td><td>已发放的张数</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.Integer}</td><td>type</td><td>tinyint</td><td>优惠券类型1、在线领取；2、线下发放</td></tr>
 *   <tr><td>channel</td><td>{@link java.lang.Integer}</td><td>channel</td><td>tinyint</td><td>活动应用渠道1、通用；2、PC；3、Mobile</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>1、新建；2、提交审核；3、审核通过；4、审核失败；5、上架；6、下架</td></tr>
 *   <tr><td>auditOpinion</td><td>{@link java.lang.String}</td><td>audit_opinion</td><td>text</td><td>审核意见</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>varchar</td><td>优惠券描述</td></tr>
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
public class LogCoupon implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = -8264946551006684217L;

    private java.lang.Integer    id;
    private java.lang.String     optType;
    private java.lang.Integer    optUserId;
    private java.lang.String     optUserName;
    private java.util.Date       optTime;
    private java.lang.Integer    couponId;
    private java.lang.Integer    sellerId;
    private java.lang.String     couponName;
    private java.lang.String     prefix;
    private java.math.BigDecimal couponValue;
    private java.math.BigDecimal minAmount;
    private java.util.Date       sendStartTime;
    private java.util.Date       sendEndTime;
    private java.util.Date       useStartTime;
    private java.util.Date       useEndTime;
    private java.lang.Integer    personLimitNum;
    private java.lang.Integer    totalLimitNum;
    private java.lang.Integer    receivedNum;
    private java.lang.Integer    type;
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
     * 获取优惠券ID
     */
    public java.lang.Integer getCouponId() {
        return this.couponId;
    }

    /**
     * 设置优惠券ID
     */
    public void setCouponId(java.lang.Integer couponId) {
        this.couponId = couponId;
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
     * 获取优惠券名称
     */
    public java.lang.String getCouponName() {
        return this.couponName;
    }

    /**
     * 设置优惠券名称
     */
    public void setCouponName(java.lang.String couponName) {
        this.couponName = couponName;
    }

    /**
     * 获取优惠券前缀
     */
    public java.lang.String getPrefix() {
        return this.prefix;
    }

    /**
     * 设置优惠券前缀
     */
    public void setPrefix(java.lang.String prefix) {
        this.prefix = prefix;
    }

    /**
     * 获取优惠券面值
     */
    public java.math.BigDecimal getCouponValue() {
        return this.couponValue;
    }

    /**
     * 设置优惠券面值
     */
    public void setCouponValue(java.math.BigDecimal couponValue) {
        this.couponValue = couponValue;
    }

    /**
     * 获取适用的最低订单金额
     */
    public java.math.BigDecimal getMinAmount() {
        return this.minAmount;
    }

    /**
     * 设置适用的最低订单金额
     */
    public void setMinAmount(java.math.BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    /**
     * 获取发放开始时间
     */
    public java.util.Date getSendStartTime() {
        return this.sendStartTime;
    }

    /**
     * 设置发放开始时间
     */
    public void setSendStartTime(java.util.Date sendStartTime) {
        this.sendStartTime = sendStartTime;
    }

    /**
     * 获取发放结束时间
     */
    public java.util.Date getSendEndTime() {
        return this.sendEndTime;
    }

    /**
     * 设置发放结束时间
     */
    public void setSendEndTime(java.util.Date sendEndTime) {
        this.sendEndTime = sendEndTime;
    }

    /**
     * 获取使用起始时间
     */
    public java.util.Date getUseStartTime() {
        return this.useStartTime;
    }

    /**
     * 设置使用起始时间
     */
    public void setUseStartTime(java.util.Date useStartTime) {
        this.useStartTime = useStartTime;
    }

    /**
     * 获取使用截止时间
     */
    public java.util.Date getUseEndTime() {
        return this.useEndTime;
    }

    /**
     * 设置使用截止时间
     */
    public void setUseEndTime(java.util.Date useEndTime) {
        this.useEndTime = useEndTime;
    }

    /**
     * 获取每个会员限制领取的张数，0为不限
     */
    public java.lang.Integer getPersonLimitNum() {
        return this.personLimitNum;
    }

    /**
     * 设置每个会员限制领取的张数，0为不限
     */
    public void setPersonLimitNum(java.lang.Integer personLimitNum) {
        this.personLimitNum = personLimitNum;
    }

    /**
     * 获取总共允许发放的总张数
     */
    public java.lang.Integer getTotalLimitNum() {
        return this.totalLimitNum;
    }

    /**
     * 设置总共允许发放的总张数
     */
    public void setTotalLimitNum(java.lang.Integer totalLimitNum) {
        this.totalLimitNum = totalLimitNum;
    }

    /**
     * 获取已发放的张数
     */
    public java.lang.Integer getReceivedNum() {
        return this.receivedNum;
    }

    /**
     * 设置已发放的张数
     */
    public void setReceivedNum(java.lang.Integer receivedNum) {
        this.receivedNum = receivedNum;
    }

    /**
     * 获取优惠券类型1、在线领取；2、线下发放
     */
    public java.lang.Integer getType() {
        return this.type;
    }

    /**
     * 设置优惠券类型1、在线领取；2、线下发放
     */
    public void setType(java.lang.Integer type) {
        this.type = type;
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
     * 获取优惠券描述
     */
    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * 设置优惠券描述
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