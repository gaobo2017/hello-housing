package com.ejavashop.entity.member;

import java.io.Serializable;

/**
 * 会员等级升级日志表
 * <p>Table: <strong>member_grade_up_logs</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>memberName</td><td>{@link java.lang.String}</td><td>member_name</td><td>varchar</td><td>会员名称</td></tr>
 *   <tr><td>beforeExper</td><td>{@link java.lang.Integer}</td><td>before_exper</td><td>int</td><td>升级之前的经验值</td></tr>
 *   <tr><td>afterExper</td><td>{@link java.lang.Integer}</td><td>after_exper</td><td>int</td><td>升级之后的经验值</td></tr>
 *   <tr><td>beforeGrade</td><td>{@link java.lang.Integer}</td><td>before_grade</td><td>int</td><td>升级之前的等级</td></tr>
 *   <tr><td>afterGrade</td><td>{@link java.lang.Integer}</td><td>after_grade</td><td>int</td><td>升级之后的等级</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 * </table>
 *
 */
public class MemberGradeUpLogs implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1234026996138253879L;

    private java.lang.Integer id;
    private java.lang.Integer memberId;
    private java.lang.String  memberName;
    private java.lang.Integer beforeExper;
    private java.lang.Integer afterExper;
    private java.lang.Integer beforeGrade;
    private java.lang.Integer afterGrade;
    private java.util.Date    createTime;

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
     * 获取升级之前的经验值
     */
    public java.lang.Integer getBeforeExper() {
        return this.beforeExper;
    }

    /**
     * 设置升级之前的经验值
     */
    public void setBeforeExper(java.lang.Integer beforeExper) {
        this.beforeExper = beforeExper;
    }

    /**
     * 获取升级之后的经验值
     */
    public java.lang.Integer getAfterExper() {
        return this.afterExper;
    }

    /**
     * 设置升级之后的经验值
     */
    public void setAfterExper(java.lang.Integer afterExper) {
        this.afterExper = afterExper;
    }

    /**
     * 获取升级之前的等级
     */
    public java.lang.Integer getBeforeGrade() {
        return this.beforeGrade;
    }

    /**
     * 设置升级之前的等级
     */
    public void setBeforeGrade(java.lang.Integer beforeGrade) {
        this.beforeGrade = beforeGrade;
    }

    /**
     * 获取升级之后的等级
     */
    public java.lang.Integer getAfterGrade() {
        return this.afterGrade;
    }

    /**
     * 设置升级之后的等级
     */
    public void setAfterGrade(java.lang.Integer afterGrade) {
        this.afterGrade = afterGrade;
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
}