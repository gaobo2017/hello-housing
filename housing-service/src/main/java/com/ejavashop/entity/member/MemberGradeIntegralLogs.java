package com.ejavashop.entity.member;

import java.io.Serializable;

/**
 * 会员经验积分日志表
 * <p>Table: <strong>member_grade_integral_logs</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>memberName</td><td>{@link java.lang.String}</td><td>member_name</td><td>varchar</td><td>会员名称</td></tr>
 *   <tr><td>value</td><td>{@link java.lang.Integer}</td><td>value</td><td>int</td><td>经验值或积分</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>optType</td><td>{@link java.lang.Integer}</td><td>opt_type</td><td>tinyint</td><td>具体操作1、会员注册；2、会员登录；3、商品购买；4、商品评论；5、系统添加；6、系统减少</td></tr>
 *   <tr><td>optDes</td><td>{@link java.lang.String}</td><td>opt_des</td><td>varchar</td><td>操作描述，订单记录订单号，商品评论记录商品ID</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.Integer}</td><td>type</td><td>tinyint</td><td>1、经验值；2、积分</td></tr>
 * </table>
 *
 */
public class MemberGradeIntegralLogs implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -812942743552587624L;

    private java.lang.Integer id;
    private java.lang.Integer memberId;
    private java.lang.String  memberName;
    private java.lang.Integer value;
    private java.util.Date    createTime;
    private java.lang.Integer optType;
    private java.lang.String  optDes;
    private java.lang.Integer type;

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
     * 获取经验值或积分
     */
    public java.lang.Integer getValue() {
        return this.value;
    }

    /**
     * 设置经验值或积分
     */
    public void setValue(java.lang.Integer value) {
        this.value = value;
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
     * 获取具体操作1、会员注册；2、会员登录；3、商品购买；4、商品评论；5、系统添加；6、系统减少
     */
    public java.lang.Integer getOptType() {
        return this.optType;
    }

    /**
     * 设置具体操作1、会员注册；2、会员登录；3、商品购买；4、商品评论；5、系统添加；6、系统减少
     */
    public void setOptType(java.lang.Integer optType) {
        this.optType = optType;
    }

    /**
     * 获取操作描述，订单记录订单号，商品评论记录商品ID
     */
    public java.lang.String getOptDes() {
        return this.optDes;
    }

    /**
     * 设置操作描述，订单记录订单号，商品评论记录商品ID
     */
    public void setOptDes(java.lang.String optDes) {
        this.optDes = optDes;
    }

    /**
     * 获取1、经验值；2、积分
     */
    public java.lang.Integer getType() {
        return this.type;
    }

    /**
     * 设置1、经验值；2、积分
     */
    public void setType(java.lang.Integer type) {
        this.type = type;
    }
}