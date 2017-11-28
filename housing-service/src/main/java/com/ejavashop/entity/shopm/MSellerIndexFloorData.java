package com.ejavashop.entity.shopm;

import java.io.Serializable;

import com.ejavashop.entity.product.Product;

/**
 * 首页楼层数据表
 * <p>Table: <strong>m_index_floor_data</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>indexFloorId</td><td>{@link java.lang.Integer}</td><td>index_floor_id</td><td>int</td><td>所属楼层ID</td></tr>
 *   <tr><td>dataType</td><td>{@link java.lang.Integer}</td><td>data_type</td><td>tinyint</td><td>数据类型：1商品2图片链接</td></tr>
 *   <tr><td>refId</td><td>{@link java.lang.Integer}</td><td>ref_id</td><td>int</td><td>数据ID（data_type为1表示商品ID）</td></tr>
 *   <tr><td>image</td><td>{@link java.lang.String}</td><td>image</td><td>varchar</td><td>图片地址</td></tr>
 *   <tr><td>linkUrl</td><td>{@link java.lang.String}</td><td>link_url</td><td>varchar</td><td>图片链接地址</td></tr>
 *   <tr><td>orderNo</td><td>{@link java.lang.Integer}</td><td>order_no</td><td>int</td><td>排序号</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>varchar</td><td>数据说明</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Integer}</td><td>create_user_id</td><td>int</td><td>createUserId</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateUserId</td><td>{@link java.lang.Integer}</td><td>update_user_id</td><td>int</td><td>updateUserId</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class MSellerIndexFloorData implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 8983501286781089867L;

    /** 数据类型：1商品 */
    public static final int   DATA_TYPE_1      = 1;
    /** 数据类型：2图片链接 */
    public static final int   DATA_TYPE_2      = 2;

    private java.lang.Integer id;
    private java.lang.Integer sellerId;
    private java.lang.Integer indexFloorId;
    private java.lang.Integer dataType;
    private java.lang.Integer refId;
    private java.lang.String  title;
    private java.lang.String  image;
    private java.lang.String  linkUrl;
    private java.lang.Integer orderNo;
    private java.lang.String  remark;
    private java.lang.Integer createUserId;
    private java.lang.String  createUserName;
    private java.util.Date    createTime;
    private java.lang.Integer updateUserId;
    private java.lang.String  updateUserName;
    private java.util.Date    updateTime;

    // --------额外属性（entity对应表结构之外的属性） start------------------------------

    private MSellerIndexFloor mSellerIndexFloor;                      // 楼层
    private Product           product;                                // 商品

    // --------额外属性（entity对应表结构之外的属性） end--------------------------------

    public String getMSellerIndexFloorName() {
        if (mSellerIndexFloor != null) {
            return mSellerIndexFloor.getName();
        }
        return "";
    }

    public String getProductName() {
        if (product != null) {
            return product.getName1();
        }
        return "";
    }

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
     * 获取所属楼层ID
     */
    public java.lang.Integer getIndexFloorId() {
        return this.indexFloorId;
    }

    /**
     * 设置所属楼层ID
     */
    public void setIndexFloorId(java.lang.Integer indexFloorId) {
        this.indexFloorId = indexFloorId;
    }

    /**
     * 获取数据类型：1商品2图片链接
     */
    public java.lang.Integer getDataType() {
        return this.dataType;
    }

    /**
     * 设置数据类型：1商品2图片链接
     */
    public void setDataType(java.lang.Integer dataType) {
        this.dataType = dataType;
    }

    /**
     * 获取数据ID（data_type为1表示商品ID）
     */
    public java.lang.Integer getRefId() {
        return this.refId;
    }

    /**
     * 设置数据ID（data_type为1表示商品ID）
     */
    public void setRefId(java.lang.Integer refId) {
        this.refId = refId;
    }

    /**
     * 获取图片地址
     */
    public java.lang.String getImage() {
        return this.image;
    }

    /**
     * 设置图片地址
     */
    public void setImage(java.lang.String image) {
        this.image = image;
    }

    /**
     * 获取图片链接地址
     */
    public java.lang.String getLinkUrl() {
        return this.linkUrl;
    }

    /**
     * 设置图片链接地址
     */
    public void setLinkUrl(java.lang.String linkUrl) {
        this.linkUrl = linkUrl;
    }

    /**
     * 获取排序号
     */
    public java.lang.Integer getOrderNo() {
        return this.orderNo;
    }

    /**
     * 设置排序号
     */
    public void setOrderNo(java.lang.Integer orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取数据说明
     */
    public java.lang.String getRemark() {
        return this.remark;
    }

    /**
     * 设置数据说明
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

    public java.lang.String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(java.lang.String createUserName) {
        this.createUserName = createUserName;
    }

    public java.lang.String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(java.lang.String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public MSellerIndexFloor getmSellerIndexFloor() {
        return mSellerIndexFloor;
    }

    public void setmSellerIndexFloor(MSellerIndexFloor mSellerIndexFloor) {
        this.mSellerIndexFloor = mSellerIndexFloor;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public void setTitle(java.lang.String title) {
        this.title = title;
    }

    public java.lang.Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }
}