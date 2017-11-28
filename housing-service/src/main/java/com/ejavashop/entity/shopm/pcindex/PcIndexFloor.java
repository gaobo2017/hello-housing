package com.ejavashop.entity.shopm.pcindex;

import java.io.Serializable;
import java.util.List;

/**
 * PC端首页楼层表
 * <p>Table: <strong>pc_index_floor</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>楼层名称</td></tr>
 *   <tr><td>orderNo</td><td>{@link java.lang.Integer}</td><td>order_no</td><td>int</td><td>楼层排序号</td></tr>
 *   <tr><td>masterImage</td><td>{@link java.lang.String}</td><td>master_image</td><td>varchar</td><td>楼层主图片</td></tr>
 *   <tr><td>masterLinkUrl</td><td>{@link java.lang.String}</td><td>master_link_url</td><td>varchar</td><td>楼层主图片链接地址</td></tr>
 *   <tr><td>advImage</td><td>{@link java.lang.String}</td><td>adv_image</td><td>varchar</td><td>楼层上端广告图片</td></tr>
 *   <tr><td>advLinkUrl</td><td>{@link java.lang.String}</td><td>adv_link_url</td><td>varchar</td><td>楼层上端广告图片链接地址</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>状态1使用2预使用3不使用</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>varchar</td><td>楼层备注</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Integer}</td><td>create_user_id</td><td>int</td><td>createUserId</td></tr>
 *   <tr><td>createUserName</td><td>{@link java.lang.String}</td><td>create_user_name</td><td>varchar</td><td>createUserName</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateUserId</td><td>{@link java.lang.Integer}</td><td>update_user_id</td><td>int</td><td>updateUserId</td></tr>
 *   <tr><td>updateUserName</td><td>{@link java.lang.String}</td><td>update_user_name</td><td>varchar</td><td>updateUserName</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class PcIndexFloor implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long       serialVersionUID = -6302403008705929754L;

    /** 状态：1使用 */
    public static final int         STATUS_1         = 1;
    /** 状态：2预使用 */
    public static final int         STATUS_2         = 2;
    /** 状态：3不使用 */
    public static final int         STATUS_3         = 3;

    private java.lang.Integer       id;
    private java.lang.String        name;
    private java.lang.Integer       orderNo;
    private java.lang.String        masterImage;
    private java.lang.String        masterLinkUrl;
    private java.lang.String        advImage;
    private java.lang.String        advLinkUrl;
    private java.lang.Integer       status;
    private java.lang.String        remark;
    private java.lang.Integer       createUserId;
    private java.lang.String        createUserName;
    private java.util.Date          createTime;
    private java.lang.Integer       updateUserId;
    private java.lang.String        updateUserName;
    private java.util.Date          updateTime;

    private List<PcIndexFloorClass> classList;
    private List<PcIndexFloorPatch> patchList;

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
     * 获取楼层名称
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 设置楼层名称
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * 获取楼层排序号
     */
    public java.lang.Integer getOrderNo() {
        return this.orderNo;
    }

    /**
     * 设置楼层排序号
     */
    public void setOrderNo(java.lang.Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取楼层主图片
     */
    public java.lang.String getMasterImage() {
        return this.masterImage;
    }

    /**
     * 设置楼层主图片
     */
    public void setMasterImage(java.lang.String masterImage) {
        this.masterImage = masterImage;
    }

    /**
     * 获取楼层主图片链接地址
     */
    public java.lang.String getMasterLinkUrl() {
        return this.masterLinkUrl;
    }

    /**
     * 设置楼层主图片链接地址
     */
    public void setMasterLinkUrl(java.lang.String masterLinkUrl) {
        this.masterLinkUrl = masterLinkUrl;
    }

    /**
     * 获取楼层上端广告图片
     */
    public java.lang.String getAdvImage() {
        return this.advImage;
    }

    /**
     * 设置楼层上端广告图片
     */
    public void setAdvImage(java.lang.String advImage) {
        this.advImage = advImage;
    }

    /**
     * 获取楼层上端广告图片链接地址
     */
    public java.lang.String getAdvLinkUrl() {
        return this.advLinkUrl;
    }

    /**
     * 设置楼层上端广告图片链接地址
     */
    public void setAdvLinkUrl(java.lang.String advLinkUrl) {
        this.advLinkUrl = advLinkUrl;
    }

    /**
     * 获取状态1使用2预使用3不使用
     */
    public java.lang.Integer getStatus() {
        return this.status;
    }

    /**
     * 设置状态1使用2预使用3不使用
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    /**
     * 获取楼层备注
     */
    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * 设置楼层备注
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    /**
     * 获取createUserId
     */
    public java.lang.Integer getCreateUserId() {
        return this.createUserId;
    }

    /**
     * 设置createUserId
     */
    public void setCreateUserId(java.lang.Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取createUserName
     */
    public java.lang.String getCreateUserName() {
        return this.createUserName;
    }

    /**
     * 设置createUserName
     */
    public void setCreateUserName(java.lang.String createUserName) {
        this.createUserName = createUserName;
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
     * 获取updateUserId
     */
    public java.lang.Integer getUpdateUserId() {
        return this.updateUserId;
    }

    /**
     * 设置updateUserId
     */
    public void setUpdateUserId(java.lang.Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    /**
     * 获取updateUserName
     */
    public java.lang.String getUpdateUserName() {
        return this.updateUserName;
    }

    /**
     * 设置updateUserName
     */
    public void setUpdateUserName(java.lang.String updateUserName) {
        this.updateUserName = updateUserName;
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

    public List<PcIndexFloorClass> getClassList() {
        return classList;
    }

    public void setClassList(List<PcIndexFloorClass> classList) {
        this.classList = classList;
    }

    public List<PcIndexFloorPatch> getPatchList() {
        return patchList;
    }

    public void setPatchList(List<PcIndexFloorPatch> patchList) {
        this.patchList = patchList;
    }
}