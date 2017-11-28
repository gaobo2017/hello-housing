package com.ejavashop.entity.member;

import java.io.Serializable;

/**
 * 会员登录日志
 * <p>Table: <strong>member_login_logs</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>memberId</td></tr>
 *   <tr><td>memberName</td><td>{@link java.lang.String}</td><td>member_name</td><td>varchar</td><td>memberName</td></tr>
 *   <tr><td>loginIp</td><td>{@link java.lang.String}</td><td>login_ip</td><td>varchar</td><td>登录IP</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>登录时间</td></tr>
 *   <tr><td>source</td><td>{@link java.lang.Integer}</td><td>source</td><td>tinyint</td><td>1、pc；2、android；3、ios；4、Html5；5、微信商城</td></tr>
 * </table>
 *
 */
public class MemberLoginLogs implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4284778723549383112L;

    private java.lang.Integer id;
    private java.lang.Integer memberId;
    private java.lang.String  memberName;
    private java.lang.String  loginIp;
    private java.util.Date    createTime;
    private java.lang.Integer source;

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
     * 获取memberId
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置memberId
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取memberName
     */
    public java.lang.String getMemberName() {
        return this.memberName;
    }

    /**
     * 设置memberName
     */
    public void setMemberName(java.lang.String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取登录IP
     */
    public java.lang.String getLoginIp() {
        return this.loginIp;
    }

    /**
     * 设置登录IP
     */
    public void setLoginIp(java.lang.String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取登录时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置登录时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取1、pc；2、android；3、ios；4、Html5；5、微信商城
     */
    public java.lang.Integer getSource() {
        return this.source;
    }

    /**
     * 设置1、pc；2、android；3、ios；4、Html5；5、微信商城
     */
    public void setSource(java.lang.Integer source) {
        this.source = source;
    }
}