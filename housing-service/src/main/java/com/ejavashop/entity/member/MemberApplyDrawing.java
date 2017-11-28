package com.ejavashop.entity.member;

import java.io.Serializable;

/**
 * 会员提款申请
 * <p>Table: <strong>member_apply_drawing</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>memberName</td><td>{@link java.lang.String}</td><td>member_name</td><td>varchar</td><td>会员名称</td></tr>
 *   <tr><td>code</td><td>{@link java.lang.String}</td><td>code</td><td>varchar</td><td>提现编号</td></tr>
 *   <tr><td>money</td><td>{@link java.math.BigDecimal}</td><td>money</td><td>decimal</td><td>提现金额</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>申请时间</td></tr>
 *   <tr><td>auditingTime</td><td>{@link java.util.Date}</td><td>auditing_time</td><td>datetime</td><td>审核时间</td></tr>
 *   <tr><td>handleTime</td><td>{@link java.util.Date}</td><td>handle_time</td><td>datetime</td><td>处理时间</td></tr>
 *   <tr><td>bank</td><td>{@link java.lang.String}</td><td>bank</td><td>varchar</td><td>收款银行</td></tr>
 *   <tr><td>bankCode</td><td>{@link java.lang.String}</td><td>bank_code</td><td>varchar</td><td>收款账号</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>1、提交申请；2、申请通过；3、已打款；4、处理失败</td></tr>
 *   <tr><td>failReason</td><td>{@link java.lang.String}</td><td>fail_reason</td><td>varchar</td><td>失败原因</td></tr>
 *   <tr><td>optId</td><td>{@link java.lang.Integer}</td><td>opt_id</td><td>int</td><td>处理人ID</td></tr>
 *   <tr><td>optName</td><td>{@link java.lang.String}</td><td>opt_name</td><td>varchar</td><td>处理人名称</td></tr>
 * </table>
 *
 */
public class MemberApplyDrawing implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = -7553659489254277249L;

    private java.lang.Integer    id;
    private java.lang.Integer    memberId;
    private java.lang.String     memberName;
    private java.lang.String     code;
    private java.math.BigDecimal money;
    private java.util.Date       createTime;
    private java.util.Date       auditingTime;
    private java.util.Date       handleTime;
    private java.lang.String     bank;
    private java.lang.String     bankCode;
    private java.lang.Integer    state;
    private java.lang.String     failReason;
    private java.lang.Integer    optId;
    private java.lang.String     optName;

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
     * 获取会员ID
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置会员ID
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取会员名称
     */
    public java.lang.String getMemberName() {
        return this.memberName;
    }

    /**
     * 设置会员名称
     */
    public void setMemberName(java.lang.String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取提现编号
     */
    public java.lang.String getCode() {
        return this.code;
    }

    /**
     * 设置提现编号
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }

    /**
     * 获取提现金额
     */
    public java.math.BigDecimal getMoney() {
        return this.money;
    }

    /**
     * 设置提现金额
     */
    public void setMoney(java.math.BigDecimal money) {
        this.money = money;
    }

    /**
     * 获取申请时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置申请时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取审核时间
     */
    public java.util.Date getAuditingTime() {
        return auditingTime;
    }

    /**
     * 设置审核时间
     */
    public void setAuditingTime(java.util.Date auditingTime) {
        this.auditingTime = auditingTime;
    }

    /**
     * 获取处理时间
     */
    public java.util.Date getHandleTime() {
        return handleTime;
    }

    /**
     * 设置处理时间
     */
    public void setHandleTime(java.util.Date handleTime) {
        this.handleTime = handleTime;
    }

    /**
     * 获取收款银行
     */
    public java.lang.String getBank() {
        return this.bank;
    }

    /**
     * 设置收款银行
     */
    public void setBank(java.lang.String bank) {
        this.bank = bank;
    }

    /**
     * 获取收款账号
     */
    public java.lang.String getBankCode() {
        return this.bankCode;
    }

    /**
     * 设置收款账号
     */
    public void setBankCode(java.lang.String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * 获取1、提交申请；2、申请通过；3、已打款；4、处理失败
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置1、提交申请；2、申请通过；3、已打款；4、处理失败
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取失败原因
     */
    public java.lang.String getFailReason() {
        return this.failReason;
    }

    /**
     * 设置失败原因
     */
    public void setFailReason(java.lang.String failReason) {
        this.failReason = failReason;
    }

    /**
     * 获取处理人ID
     */
    public java.lang.Integer getOptId() {
        return this.optId;
    }

    /**
     * 设置处理人ID
     */
    public void setOptId(java.lang.Integer optId) {
        this.optId = optId;
    }

    /**
     * 获取处理人名称
     */
    public java.lang.String getOptName() {
        return optName;
    }

    /**
     * 设置处理人名称
     */
    public void setOptName(java.lang.String optName) {
        this.optName = optName;
    }

}