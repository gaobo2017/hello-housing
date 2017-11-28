package com.ejavashop.entity.seller;

import java.io.Serializable;

/**
 * 商家用户登录日志
 * <p>Table: <strong>seller_user_login_log</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>userId</td><td>{@link java.lang.Integer}</td><td>user_id</td><td>int</td><td>userId</td></tr>
 *   <tr><td>userName</td><td>{@link java.lang.String}</td><td>user_name</td><td>varchar</td><td>userName</td></tr>
 *   <tr><td>loginIp</td><td>{@link java.lang.String}</td><td>login_ip</td><td>varchar</td><td>loginIp</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 * </table>
 *
 */
public class SellerUserLoginLog implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6497416159676388601L;

    private java.lang.Integer id;
    private java.lang.Integer userId;
    private java.lang.String  userName;
    private java.lang.String  loginIp;
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
     * 获取userId
     */
    public java.lang.Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置userId
     */
    public void setUserId(java.lang.Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取userName
     */
    public java.lang.String getUserName() {
        return this.userName;
    }

    /**
     * 设置userName
     */
    public void setUserName(java.lang.String userName) {
        this.userName = userName;
    }

    /**
     * 获取loginIp
     */
    public java.lang.String getLoginIp() {
        return this.loginIp;
    }

    /**
     * 设置loginIp
     */
    public void setLoginIp(java.lang.String loginIp) {
        this.loginIp = loginIp;
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
}