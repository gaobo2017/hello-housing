package com.ejavashop.entity.promotion.group;

import java.io.Serializable;

/**
 * 团购分类
 * <p>Table: <strong>act_group_type</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>团购分类名称</td></tr>
 *   <tr><td>sort</td><td>{@link java.lang.Integer}</td><td>sort</td><td>tinyint</td><td>权重</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>分类状态：0、不显示；1、显示</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>创建人ID</td></tr>
 *   <tr><td>createName</td><td>{@link java.lang.String}</td><td>create_name</td><td>varchar</td><td>创建人</td></tr>
 *   <tr><td>updateId</td><td>{@link java.lang.Integer}</td><td>update_id</td><td>int</td><td>更新人ID</td></tr>
 *   <tr><td>updateName</td><td>{@link java.lang.String}</td><td>update_name</td><td>varchar</td><td>更新人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class ActGroupType implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID     = 9040130008983347372L;
    private java.lang.Integer id;
    private java.lang.String  name;
    private java.lang.Integer sort;
    private java.lang.Integer state;
    private java.lang.Integer createId;
    private java.lang.String  createName;
    private java.lang.Integer updateId;
    private java.lang.String  updateName;
    private java.util.Date    createTime;
    private java.util.Date    updateTime;

    /**
     * 停用 0
     */
    public static final int   ACTGROUPTYPE_STATE_0 = 0;

    /**
     * 启用 1
     */
    public static final int   ACTGROUPTYPE_STATE_1 = 1;

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
     * 获取团购分类名称
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 设置团购分类名称
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * 获取权重
     */
    public java.lang.Integer getSort() {
        return this.sort;
    }

    /**
     * 设置权重
     */
    public void setSort(java.lang.Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取分类状态：0、不显示；1、显示
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置分类状态：0、不显示；1、显示
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取创建人ID
     */
    public java.lang.Integer getCreateId() {
        return this.createId;
    }

    /**
     * 设置创建人ID
     */
    public void setCreateId(java.lang.Integer createId) {
        this.createId = createId;
    }

    /**
     * 获取创建人
     */
    public java.lang.String getCreateName() {
        return this.createName;
    }

    /**
     * 设置创建人
     */
    public void setCreateName(java.lang.String createName) {
        this.createName = createName;
    }

    /**
     * 获取更新人ID
     */
    public java.lang.Integer getUpdateId() {
        return this.updateId;
    }

    /**
     * 设置更新人ID
     */
    public void setUpdateId(java.lang.Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * 获取更新人
     */
    public java.lang.String getUpdateName() {
        return this.updateName;
    }

    /**
     * 设置更新人
     */
    public void setUpdateName(java.lang.String updateName) {
        this.updateName = updateName;
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
}