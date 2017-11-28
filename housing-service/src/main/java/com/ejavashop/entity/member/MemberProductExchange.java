package com.ejavashop.entity.member;

import java.io.Serializable;

import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.product.Product;

/**
 * 用户换货
 * <p>Table: <strong>member_product_exchange</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>seller</td><td>{@link java.lang.Integer}</td><td>seller</td><td>int</td><td>所属商家</td></tr>
 *   <tr><td>orderId</td><td>{@link java.lang.Integer}</td><td>order_id</td><td>int</td><td>订单ID</td></tr>
 *   <tr><td>orderProductId</td><td>{@link java.lang.Integer}</td><td>order_product_id</td><td>int</td><td>网单ID</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>商品ID</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>用户ID</td></tr>
 *   <tr><td>memberName</td><td>{@link java.lang.String}</td><td>member_name</td><td>varchar</td><td>用户Name</td></tr>
 *   <tr><td>provinceId2</td><td>{@link java.lang.Integer}</td><td>province_id2</td><td>int</td><td>Province</td></tr>
 *   <tr><td>cityId2</td><td>{@link java.lang.Integer}</td><td>city_id2</td><td>int</td><td>city</td></tr>
 *   <tr><td>areaId2</td><td>{@link java.lang.Integer}</td><td>area_id2</td><td>int</td><td>area</td></tr>
 *   <tr><td>addressAll2</td><td>{@link java.lang.String}</td><td>address_all2</td><td>varchar</td><td>省市区组合</td></tr>
 *   <tr><td>addressInfo2</td><td>{@link java.lang.String}</td><td>address_info2</td><td>varchar</td><td>详细地址</td></tr>
 *   <tr><td>phone2</td><td>{@link java.lang.String}</td><td>phone2</td><td>varchar</td><td>phone2</td></tr>
 *   <tr><td>changeName2</td><td>{@link java.lang.String}</td><td>change_name2</td><td>varchar</td><td>换货收货人</td></tr>
 *   <tr><td>provinceId</td><td>{@link java.lang.Integer}</td><td>province_id</td><td>int</td><td>Province</td></tr>
 *   <tr><td>cityId</td><td>{@link java.lang.Integer}</td><td>city_id</td><td>int</td><td>city</td></tr>
 *   <tr><td>areaId</td><td>{@link java.lang.Integer}</td><td>area_id</td><td>int</td><td>area</td></tr>
 *   <tr><td>addressAll</td><td>{@link java.lang.String}</td><td>address_all</td><td>varchar</td><td>省市区组合</td></tr>
 *   <tr><td>addressInfo</td><td>{@link java.lang.String}</td><td>address_info</td><td>varchar</td><td>详细地址</td></tr>
 *   <tr><td>changeName</td><td>{@link java.lang.String}</td><td>change_name</td><td>char</td><td>收货人地址</td></tr>
 *   <tr><td>phone</td><td>{@link java.lang.String}</td><td>phone</td><td>varchar</td><td>phone</td></tr>
 *   <tr><td>email</td><td>{@link java.lang.String}</td><td>email</td><td>varchar</td><td>email</td></tr>
 *   <tr><td>zipCode</td><td>{@link java.lang.String}</td><td>zip_code</td><td>varchar</td><td>邮编</td></tr>
 *   <tr><td>question</td><td>{@link java.lang.String}</td><td>question</td><td>varchar</td><td>问题描述</td></tr>
 *   <tr><td>image</td><td>{@link java.lang.String}</td><td>image</td><td>varchar</td><td>图片</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>联系人姓名</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>换货状态：1、未处理；2、审核通过待收货；3、已经收货；4、发货处理完成；5、不予处理原件退还；6、不处理</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 *   <tr><td>optId</td><td>{@link java.lang.Integer}</td><td>opt_id</td><td>int</td><td>处理人</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>varchar</td><td>备注</td></tr>
 *   <tr><td>addressResult</td><td>{@link java.lang.String}</td><td>address_result</td><td>varchar</td><td>退货地址</td></tr>
 * </table>
 *
 */
public class MemberProductExchange implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1434128168892641179L;
    private java.lang.Integer id;                                     // id
    private java.lang.Integer seller;                                 // 所属商家
    private java.lang.Integer orderId;                                // 订单ID
    private java.lang.Integer orderProductId;                         // 网单ID
    private java.lang.Integer productId;                              // 商品ID
    private java.lang.Integer memberId;                               // 用户ID
    private java.lang.String  memberName;                             // 用户Name
    private java.lang.Integer provinceId2;                            // Province
    private java.lang.Integer cityId2;                                // city
    private java.lang.Integer areaId2;                                // area
    private java.lang.String  addressAll2;                            // 省市区组合
    private java.lang.String  addressInfo2;                           // 详细地址
    private java.lang.String  phone2;                                 // phone2
    private java.lang.String  changeName2;                            // 换货收货人
    private java.lang.Integer provinceId;                             // Province
    private java.lang.Integer cityId;                                 // city
    private java.lang.Integer areaId;                                 // area
    private java.lang.String  addressAll;                             // 省市区组合
    private java.lang.String  addressInfo;                            // 详细地址
    private java.lang.String  changeName;                             // 收货人地址
    private java.lang.String  phone;                                  // phone
    private java.lang.String  email;                                  // email
    private java.lang.String  zipCode;                                // 邮编
    private java.lang.String  question;                               // 问题描述
    private java.lang.String  image;                                  // 图片
    private java.lang.String  name;                                   // 联系人姓名
    private java.lang.Integer state;                                  //换货状态：1、未处理；2、审核通过待收货；3、已经收货；4、发货处理完成；5、不予处理原件退还；6、不处理
    private java.util.Date    createTime;                             // createTime
    private java.util.Date    updateTime;                             // updateTime
    private java.lang.Integer optId;                                  // 处理人
    private java.lang.String  remark;                                 // 备注
    private java.lang.String  addressResult;                          // 退货地址

    // --------额外属性（entity对应表结构之外的属性） start------------------------------
    private String            orderSn;                                // 订单编号
    private String            productName;                            // 商品名称

    private Product           product;                                // 商品
    private OrdersProduct     ordersProduct;                          // 网单
    // --------额外属性（entity对应表结构之外的属性） end--------------------------------

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
     * 获取所属商家
     */
    public java.lang.Integer getSeller() {
        return this.seller;
    }

    /**
     * 设置所属商家
     */
    public void setSeller(java.lang.Integer seller) {
        this.seller = seller;
    }

    /**
     * 获取订单ID
     */
    public java.lang.Integer getOrderId() {
        return this.orderId;
    }

    /**
     * 设置订单ID
     */
    public void setOrderId(java.lang.Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取网单ID
     */
    public java.lang.Integer getOrderProductId() {
        return this.orderProductId;
    }

    /**
     * 设置网单ID
     */
    public void setOrderProductId(java.lang.Integer orderProductId) {
        this.orderProductId = orderProductId;
    }

    /**
     * 获取商品ID
     */
    public java.lang.Integer getProductId() {
        return this.productId;
    }

    /**
     * 设置商品ID
     */
    public void setProductId(java.lang.Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取用户ID
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置用户ID
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取用户Name
     */
    public java.lang.String getMemberName() {
        return this.memberName;
    }

    /**
     * 设置用户Name
     */
    public void setMemberName(java.lang.String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取Province
     */
    public java.lang.Integer getProvinceId2() {
        return this.provinceId2;
    }

    /**
     * 设置Province
     */
    public void setProvinceId2(java.lang.Integer provinceId2) {
        this.provinceId2 = provinceId2;
    }

    /**
     * 获取city
     */
    public java.lang.Integer getCityId2() {
        return this.cityId2;
    }

    /**
     * 设置city
     */
    public void setCityId2(java.lang.Integer cityId2) {
        this.cityId2 = cityId2;
    }

    /**
     * 获取area
     */
    public java.lang.Integer getAreaId2() {
        return this.areaId2;
    }

    /**
     * 设置area
     */
    public void setAreaId2(java.lang.Integer areaId2) {
        this.areaId2 = areaId2;
    }

    /**
     * 获取省市区组合
     */
    public java.lang.String getAddressAll2() {
        return this.addressAll2;
    }

    /**
     * 设置省市区组合
     */
    public void setAddressAll2(java.lang.String addressAll2) {
        this.addressAll2 = addressAll2;
    }

    /**
     * 获取详细地址
     */
    public java.lang.String getAddressInfo2() {
        return this.addressInfo2;
    }

    /**
     * 设置详细地址
     */
    public void setAddressInfo2(java.lang.String addressInfo2) {
        this.addressInfo2 = addressInfo2;
    }

    /**
     * 获取phone2
     */
    public java.lang.String getPhone2() {
        return this.phone2;
    }

    /**
     * 设置phone2
     */
    public void setPhone2(java.lang.String phone2) {
        this.phone2 = phone2;
    }

    /**
     * 获取换货收货人
     */
    public java.lang.String getChangeName2() {
        return this.changeName2;
    }

    /**
     * 设置换货收货人
     */
    public void setChangeName2(java.lang.String changeName2) {
        this.changeName2 = changeName2;
    }

    /**
     * 获取Province
     */
    public java.lang.Integer getProvinceId() {
        return this.provinceId;
    }

    /**
     * 设置Province
     */
    public void setProvinceId(java.lang.Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取city
     */
    public java.lang.Integer getCityId() {
        return this.cityId;
    }

    /**
     * 设置city
     */
    public void setCityId(java.lang.Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取area
     */
    public java.lang.Integer getAreaId() {
        return this.areaId;
    }

    /**
     * 设置area
     */
    public void setAreaId(java.lang.Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * 获取省市区组合
     */
    public java.lang.String getAddressAll() {
        return this.addressAll;
    }

    /**
     * 设置省市区组合
     */
    public void setAddressAll(java.lang.String addressAll) {
        this.addressAll = addressAll;
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
     * 获取收货人地址
     */
    public java.lang.String getChangeName() {
        return this.changeName;
    }

    /**
     * 设置收货人地址
     */
    public void setChangeName(java.lang.String changeName) {
        this.changeName = changeName;
    }

    /**
     * 获取phone
     */
    public java.lang.String getPhone() {
        return this.phone;
    }

    /**
     * 设置phone
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }

    /**
     * 获取email
     */
    public java.lang.String getEmail() {
        return this.email;
    }

    /**
     * 设置email
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
     * 获取问题描述
     */
    public java.lang.String getQuestion() {
        return this.question;
    }

    /**
     * 设置问题描述
     */
    public void setQuestion(java.lang.String question) {
        this.question = question;
    }

    /**
     * 获取图片
     */
    public java.lang.String getImage() {
        return this.image;
    }

    /**
     * 设置图片
     */
    public void setImage(java.lang.String image) {
        this.image = image;
    }

    /**
     * 获取联系人姓名
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 设置联系人姓名
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * 获取换货状态：1、未处理；2、审核通过待收货；3、已经收货；4、发货处理完成；5、不予处理原件退还；6、不处理
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置换货状态：1、未处理；2、审核通过待收货；3、已经收货；4、发货处理完成；5、不予处理原件退还；6、不处理
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

    /**
     * 获取处理人
     */
    public java.lang.Integer getOptId() {
        return this.optId;
    }

    /**
     * 设置处理人
     */
    public void setOptId(java.lang.Integer optId) {
        this.optId = optId;
    }

    /**
     * 获取备注
     */
    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * 设置备注
     */
    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    /**
     * 获取退货地址
     */
    public java.lang.String getAddressResult() {
        return this.addressResult;
    }

    /**
     * 设置退货地址
     */
    public void setAddressResult(java.lang.String addressResult) {
        this.addressResult = addressResult;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public OrdersProduct getOrdersProduct() {
        return ordersProduct;
    }

    public void setOrdersProduct(OrdersProduct ordersProduct) {
        this.ordersProduct = ordersProduct;
    }

}