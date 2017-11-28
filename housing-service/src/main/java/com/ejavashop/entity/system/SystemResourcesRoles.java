package com.ejavashop.entity.system;

import java.io.Serializable;

/**
 * 角色资源对应表
 * <p>Table: <strong>system_resources_roles</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>resourcesId</td><td>{@link java.lang.Integer}</td><td>resources_id</td><td>int</td><td>resourcesId</td></tr>
 *   <tr><td>rolesId</td><td>{@link java.lang.Integer}</td><td>roles_id</td><td>int</td><td>rolesId</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 * </table>
 *
 */
public class SystemResourcesRoles implements Serializable {

    private java.lang.Integer id;          //id
    private java.lang.Integer resourcesId; //resourcesId
    private java.lang.Integer rolesId;     //rolesId
    private java.util.Date    createTime;  //createTime

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
     * 获取resourcesId
     */
    public java.lang.Integer getResourcesId() {
        return this.resourcesId;
    }

    /**
     * 设置resourcesId
     */
    public void setResourcesId(java.lang.Integer resourcesId) {
        this.resourcesId = resourcesId;
    }

    /**
     * 获取rolesId
     */
    public java.lang.Integer getRolesId() {
        return this.rolesId;
    }

    /**
     * 设置rolesId
     */
    public void setRolesId(java.lang.Integer rolesId) {
        this.rolesId = rolesId;
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