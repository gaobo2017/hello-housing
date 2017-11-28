package com.ejavashop.entity.seller;

import java.io.Serializable;

/**
 * 商家角色表
 * <p>Table: <strong>seller_roles</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>rolesName</td><td>{@link java.lang.String}</td><td>roles_name</td><td>varchar</td><td>角色名称</td></tr>
 *   <tr><td>roleCode</td><td>{@link java.lang.String}</td><td>role_code</td><td>varchar</td><td>角色code,唯一</td></tr>
 *   <tr><td>content</td><td>{@link java.lang.String}</td><td>content</td><td>varchar</td><td>角色描述</td></tr>
 *   <tr><td>userId</td><td>{@link java.lang.Integer}</td><td>user_id</td><td>int</td><td>创建人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>1、未删除2、删除</td></tr>
 * </table>
 *
 */
public class SellerRoles implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6832762749131978659L;

    /**角色状态:未删除*/
    public static final int   STATUS_1         = 1;
    /**角色状态:删除*/
    public static final int   STATUS_2         = 2;

    private java.lang.Integer id;
    private java.lang.Integer sellerId;
    private java.lang.String  rolesName;
    private java.lang.String  roleCode;
    private java.lang.String  content;
    private java.lang.Integer userId;
    private java.util.Date    createTime;
    private java.util.Date    updateTime;
    private java.lang.Integer status;

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
     * 获取角色名称
     */
    public java.lang.String getRolesName() {
        return this.rolesName;
    }

    /**
     * 设置角色名称
     */
    public void setRolesName(java.lang.String rolesName) {
        this.rolesName = rolesName;
    }

    /**
     * 获取角色code,唯一
     */
    public java.lang.String getRoleCode() {
        return this.roleCode;
    }

    /**
     * 设置角色code,唯一
     */
    public void setRoleCode(java.lang.String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * 获取角色描述
     */
    public java.lang.String getContent() {
        return this.content;
    }

    /**
     * 设置角色描述
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }

    /**
     * 获取创建人
     */
    public java.lang.Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置创建人
     */
    public void setUserId(java.lang.Integer userId) {
        this.userId = userId;
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

    /**
     * 获取1、未删除2、删除
     */
    public java.lang.Integer getStatus() {
        return this.status;
    }

    /**
     * 设置1、未删除2、删除
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }
}