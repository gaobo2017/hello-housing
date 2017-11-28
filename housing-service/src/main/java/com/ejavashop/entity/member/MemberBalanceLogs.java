package com.ejavashop.entity.member;

import java.io.Serializable;

/**
 * 会员账户余额变化日志表
 * <p>Table: <strong>member_balance_logs</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>memberName</td><td>{@link java.lang.String}</td><td>member_name</td><td>varchar</td><td>会员名称</td></tr>
 *   <tr><td>moneyBefore</td><td>{@link java.math.BigDecimal}</td><td>money_before</td><td>decimal</td><td>变化之前的余额</td></tr>
 *   <tr><td>moneyAfter</td><td>{@link java.math.BigDecimal}</td><td>money_after</td><td>decimal</td><td>变化之后的余额</td></tr>
 *   <tr><td>money</td><td>{@link java.math.BigDecimal}</td><td>money</td><td>decimal</td><td>变化金额</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>1、充值；2、退款；3、消费；4、提款；5、系统添加；6、系统减少</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>varchar</td><td>操作备注</td></tr>
 *   <tr><td>optId</td><td>{@link java.lang.Integer}</td><td>opt_id</td><td>int</td><td>操作人ID</td></tr>
 *   <tr><td>optName</td><td>{@link java.lang.String}</td><td>opt_name</td><td>varchar</td><td>操作人名称</td></tr>
 * </table>
 *
 */
public class MemberBalanceLogs implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = -7763258009998262842L;

    /** 会员账户余额变化日志表state，操作类型：1、充值 */
    public final static int      STATE_1          = 1;
    /** 会员账户余额变化日志表state，操作类型：2、退款 */
    public final static int      STATE_2          = 2;
    /** 会员账户余额变化日志表state，操作类型：3、消费 */
    public final static int      STATE_3          = 3;
    /** 会员账户余额变化日志表state，操作类型：4、提款 */
    public final static int      STATE_4          = 4;
    /** 会员账户余额变化日志表state，操作类型：5、系统添加  */
    public final static int      STATE_5          = 5;
    /** 会员账户余额变化日志表state，操作类型：6、系统减少 */
    public final static int      STATE_6          = 6;

    private java.lang.Integer    id;
    private java.lang.Integer    memberId;
    private java.lang.String     memberName;
    private java.math.BigDecimal moneyBefore;
    private java.math.BigDecimal moneyAfter;
    private java.math.BigDecimal money;
    private java.util.Date       createTime;
    private java.lang.Integer    state;
    private java.lang.String     remark;
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
     * 获取变化之前的余额
     */
    public java.math.BigDecimal getMoneyBefore() {
        return this.moneyBefore;
    }

    /**
     * 设置变化之前的余额
     */
    public void setMoneyBefore(java.math.BigDecimal moneyBefore) {
        this.moneyBefore = moneyBefore;
    }

    /**
     * 获取变化之后的余额
     */
    public java.math.BigDecimal getMoneyAfter() {
        return this.moneyAfter;
    }

    /**
     * 设置变化之后的余额
     */
    public void setMoneyAfter(java.math.BigDecimal moneyAfter) {
        this.moneyAfter = moneyAfter;
    }

    /**
     * 获取变化金额
     */
    public java.math.BigDecimal getMoney() {
        return money;
    }

    /**
     * 设置变化金额
     */
    public void setMoney(java.math.BigDecimal money) {
        this.money = money;
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
     * 获取1、充值；2、退款；3、消费；4、提款；5、系统添加；6、系统减少
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置1、充值；2、退款；3、消费；4、提款；5、系统添加；6、系统减少
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取操作备注
     */
    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * 设置操作备注
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    /**
     * 获取操作人ID
     */
    public java.lang.Integer getOptId() {
        return optId;
    }

    /**
     * 设置操作人ID
     */
    public void setOptId(java.lang.Integer optId) {
        this.optId = optId;
    }

    /**
     * 获取操作人名称
     */
    public java.lang.String getOptName() {
        return optName;
    }

    /**
     * 设置操作人名称
     */
    public void setOptName(java.lang.String optName) {
        this.optName = optName;
    }

}