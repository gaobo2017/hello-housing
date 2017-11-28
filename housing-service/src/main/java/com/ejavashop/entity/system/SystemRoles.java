package com.ejavashop.entity.system;

import java.io.Serializable;
import java.util.Date;

/**
 * 角色表
 * <p>Table: <strong>system_roles</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>rolesName</td><td>{@link String}</td><td>roles_name</td><td>varchar</td><td>角色名称</td></tr>
 *   <tr><td>content</td><td>{@link String}</td><td>content</td><td>varchar</td><td>角色描述</td></tr>
 *   <tr><td>userId</td><td>{@link Integer}</td><td>user_id</td><td>int</td><td>userId</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>tinyint</td><td>1、未删除2、删除</td></tr>
 * </table>
 *
 */
public class SystemRoles implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8117295580077675461L;
    private Integer           id;                                      //id
    private String            rolesName;                               //角色名称
    private String            content;                                 //角色描述
    private Integer           userId;                                  //userId
    private Date              createTime;                              //createTime
    private Date              updateTime;                              //updateTime
    private Integer           status;                                  //1、未删除2、删除
    private String            roleCode;

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
     * 获取角色名称
     */
    public String getRolesName() {
        return this.rolesName;
    }

    /**
     * 设置角色名称
     */
    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    /**
     * 获取角色描述
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置角色描述
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取userId
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取updateTime
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取1、未删除2、删除
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置1、未删除2、删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
}