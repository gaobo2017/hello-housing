package com.ejavashop.entity.member;

import java.io.Serializable;

/**
 * 收货地址
 * <p>Table: <strong>member_address</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>memberId</td></tr>
 *   <tr><td>memberName</td><td>{@link java.lang.String}</td><td>member_name</td><td>varchar</td><td>收货人</td></tr>
 *   <tr><td>provinceId</td><td>{@link java.lang.Integer}</td><td>province_id</td><td>int</td><td>省ID</td></tr>
 *   <tr><td>cityId</td><td>{@link java.lang.Integer}</td><td>city_id</td><td>int</td><td>市ID</td></tr>
 *   <tr><td>areaId</td><td>{@link java.lang.Integer}</td><td>area_id</td><td>int</td><td>地区ID</td></tr>
 *   <tr><td>addAll</td><td>{@link java.lang.String}</td><td>add_all</td><td>varchar</td><td>省市区组合</td></tr>
 *   <tr><td>addressInfo</td><td>{@link java.lang.String}</td><td>address_info</td><td>varchar</td><td>详细地址</td></tr>
 *   <tr><td>mobile</td><td>{@link java.lang.String}</td><td>mobile</td><td>varchar</td><td>电话</td></tr>
 *   <tr><td>phone</td><td>{@link java.lang.String}</td><td>phone</td><td>varchar</td><td>手机</td></tr>
 *   <tr><td>email</td><td>{@link java.lang.String}</td><td>email</td><td>varchar</td><td>邮箱</td></tr>
 *   <tr><td>zipCode</td><td>{@link java.lang.String}</td><td>zip_code</td><td>varchar</td><td>邮编</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>1、默认；2、不是默认</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class MemberAddress implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5375495908433112267L;

    /** 收货地址表state，状态：1、默认 */
    public final static int   STATE_1          = 1;
    /** 收货地址表state，状态：2、非默认 */
    public final static int   STATE_2          = 2;

    private java.lang.Integer id;
    private java.lang.Integer memberId;
    private java.lang.String  memberName;
    private java.lang.Integer provinceId;
    private java.lang.Integer cityId;
    private java.lang.Integer areaId;
    private java.lang.String  addAll;
    private java.lang.String  addressInfo;
    private java.lang.String  mobile;
    private java.lang.String  phone;
    private java.lang.String  email;
    private java.lang.String  zipCode;
    private java.lang.Integer state;
    private java.util.Date    createTime;
    private java.util.Date    updateTime;

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
     * 获取收货人
     */
    public java.lang.String getmemberName() {
        return this.memberName;
    }

    /**
     * 设置收货人
     */
    public void setMemberName(java.lang.String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取省ID
     */
    public java.lang.Integer getProvinceId() {
        return this.provinceId;
    }

    /**
     * 设置省ID
     */
    public void setProvinceId(java.lang.Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取市ID
     */
    public java.lang.Integer getCityId() {
        return this.cityId;
    }

    /**
     * 设置市ID
     */
    public void setCityId(java.lang.Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取地区ID
     */
    public java.lang.Integer getAreaId() {
        return this.areaId;
    }

    /**
     * 设置地区ID
     */
    public void setAreaId(java.lang.Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取省市区组合
     */
    public java.lang.String getAddAll() {
        return this.addAll;
    }

    /**
     * 设置省市区组合
     */
    public void setAddAll(java.lang.String addAll) {
        this.addAll = addAll;
    }

    /**
     * 获取详细地址
     */
    public java.lang.String getAddressInfo() {
        return this.addressInfo;
    }

    /**
     * 设置详细地址
     */
    public void setAddressInfo(java.lang.String addressInfo) {
        this.addressInfo = addressInfo;
    }

    /**
     * 获取电话
     */
    public java.lang.String getMobile() {
        return this.mobile;
    }

    /**
     * 设置电话
     */
    public void setMobile(java.lang.String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取手机
     */
    public java.lang.String getPhone() {
        return this.phone;
    }

    /**
     * 设置手机
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }

    /**
     * 获取邮箱
     */
    public java.lang.String getEmail() {
        return this.email;
    }

    /**
     * 设置邮箱
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    /**
     * 获取邮编
     */
    public java.lang.String getZipCode() {
        return this.zipCode;
    }

    /**
     * 设置邮编
     */
    public void setZipCode(java.lang.String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * 获取1、默认；2、不是默认
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置1、默认；2、不是默认
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
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