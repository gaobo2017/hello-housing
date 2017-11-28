package com.ejavashop.entity.seller;

import java.io.Serializable;

/**
 * 商家用户表
 * <p>Table: <strong>seller_user</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>name</td></tr>
 *   <tr><td>password</td><td>{@link java.lang.String}</td><td>password</td><td>varchar</td><td>password</td></tr>
 *   <tr><td>code</td><td>{@link java.lang.String}</td><td>code</td><td>varchar</td><td>员工号</td></tr>
 *   <tr><td>realName</td><td>{@link java.lang.String}</td><td>real_name</td><td>varchar</td><td>真实姓名</td></tr>
 *   <tr><td>phone</td><td>{@link java.lang.String}</td><td>phone</td><td>varchar</td><td>联系电话</td></tr>
 *   <tr><td>job</td><td>{@link java.lang.String}</td><td>job</td><td>varchar</td><td>职务</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>所属商家</td></tr>
 *   <tr><td>roleId</td><td>{@link java.lang.Integer}</td><td>role_id</td><td>int</td><td>商家角色ID</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>1、使用；2、删除</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>创建人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateId</td><td>{@link java.lang.Integer}</td><td>update_id</td><td>int</td><td>修改人</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>更新时间（最后一次登陆时间）</td></tr>
 * </table>
 *
 */
public class SellerUser implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 816972633607285541L;

    /**管理员状态:正常*/
    public static final int   STATE_NORM       = 1;
    /**管理员状态:冻结*/
    public static final int   STATE_FREEZE     = 2;
    /**管理员状态:删除*/
    public static final int   STATE_DEL        = 3;

    private java.lang.Integer id;
    private java.lang.String  name;
    private java.lang.String  password;
    private java.lang.String  code;
    private java.lang.String  realName;
    private java.lang.String  phone;
    private java.lang.String  job;
    private java.lang.Integer sellerId;
    private java.lang.Integer roleId;
    private java.lang.Integer state;
    private java.lang.Integer createId;
    private java.util.Date    createTime;
    private java.lang.Integer updateId;
    private java.util.Date    updateTime;

    private String            roleName;
    private Seller            seller;

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
     * 获取name
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 设置name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * 获取password
     */
    public java.lang.String getPassword() {
        return this.password;
    }

    /**
     * 设置password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    /**
     * 获取员工号
     */
    public java.lang.String getCode() {
        return this.code;
    }

    /**
     * 设置员工号
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }

    /**
     * 获取真实姓名
     */
    public java.lang.String getRealName() {
        return this.realName;
    }

    /**
     * 设置真实姓名
     */
    public void setRealName(java.lang.String realName) {
        this.realName = realName;
    }

    /**
     * 获取联系电话
     */
    public java.lang.String getPhone() {
        return this.phone;
    }

    /**
     * 设置联系电话
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }

    /**
     * 获取职务
     */
    public java.lang.String getJob() {
        return this.job;
    }

    /**
     * 设置职务
     */
    public void setJob(java.lang.String job) {
        this.job = job;
    }

    /**
     * 获取所属商家
     */
    public java.lang.Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置所属商家
     */
    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取商家角色ID
     */
    public java.lang.Integer getRoleId() {
        return this.roleId;
    }

    /**
     * 设置商家角色ID
     */
    public void setRoleId(java.lang.Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取1、使用；2、删除
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置1、使用；2、删除
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取创建人
     */
    public java.lang.Integer getCreateId() {
        return this.createId;
    }

    /**
     * 设置创建人
     */
    public void setCreateId(java.lang.Integer createId) {
        this.createId = createId;
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
     * 获取修改人
     */
    public java.lang.Integer getUpdateId() {
        return this.updateId;
    }

    /**
     * 设置修改人
     */
    public void setUpdateId(java.lang.Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * 获取更新时间（最后一次登陆时间）
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置更新时间（最后一次登陆时间）
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}