package com.ejavashop.entity.seller;

import java.io.Serializable;

/**
 * 商家角色资源对应表
 * <p>Table: <strong>seller_resources_roles</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>resourcesId</td><td>{@link java.lang.Integer}</td><td>resources_id</td><td>int</td><td>resourcesId</td></tr>
 *   <tr><td>sellerRolesId</td><td>{@link java.lang.Integer}</td><td>seller_roles_id</td><td>int</td><td>sellerRolesId</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 * </table>
 *
 */
public class SellerResourcesRoles implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 192316953734998730L;

    private java.lang.Integer id;
    private java.lang.Integer resourcesId;
    private java.lang.Integer sellerRolesId;
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
     * 获取sellerRolesId
     */
    public java.lang.Integer getSellerRolesId() {
        return this.sellerRolesId;
    }

    /**
     * 设置sellerRolesId
     */
    public void setSellerRolesId(java.lang.Integer sellerRolesId) {
        this.sellerRolesId = sellerRolesId;
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