package com.ejavashop.entity.system;

import java.io.Serializable;

/**
 * 系统管理员表
 * <p>Table: <strong>system_admin</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link String}</td><td>name</td><td>varchar</td><td>用户名</td></tr>
 *   <tr><td>password</td><td>{@link String}</td><td>password</td><td>varchar</td><td>密码</td></tr>
 *   <tr><td>roleId</td><td>{@link Integer}</td><td>role_id</td><td>int</td><td>角色id</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>createUser</td><td>{@link Integer}</td><td>create_user</td><td>int</td><td>createUser</td></tr>
 *   <tr><td>tel</td><td>{@link String}</td><td>tel</td><td>varchar</td><td>电话</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>tinyint</td><td>状态 1-正常 2-冻结 3-删除</td></tr>
 * </table>
 *
 */
public class SystemAdmin implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -1365464805345367359L;

    private Integer           id;                                      //id
    private String            name;                                    //用户名
    private String            password;                                //密码
    private Integer           roleId;                                  //角色id
    private java.util.Date    createTime;                              //createTime
    private Integer           createUser;                              //createUser
    private String            tel;                                     //电话
    private Integer           status;                                  //状态 1-正常 2-冻结 3-删除

    private String            roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取角色id
     */
    public Integer getRoleId() {
        return this.roleId;
    }

    /**
     * 设置角色id
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
     * 获取createUser
     */
    public Integer getCreateUser() {
        return this.createUser;
    }

    /**
     * 设置createUser
     */
    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取电话
     */
    public String getTel() {
        return this.tel;
    }

    /**
     * 设置电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 获取状态 1-正常 2-冻结 3-删除
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态 1-正常 2-冻结 3-删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}