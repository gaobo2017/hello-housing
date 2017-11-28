package com.ejavashop.entity.product;

import java.io.Serializable;

/**
* 商品对应图片表
* <p>Table: <strong>product_picture</strong>
    * <p>
<table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
    *
    <tr style="background-color:#ddd;Text-align:Left;">
        *
        <th nowrap>属性名</th>
        <th nowrap>属性类型</th>
        <th nowrap>字段名</th>
        <th nowrap>字段类型</th>
        <th nowrap>说明</th>
        *
    </tr>
    *
    <tr>
        <td>id</td>
        <td>{@link Integer}</td>
        <td>id</td>
        <td>int</td>
        <td>id</td>
    </tr>
    *
    <tr>
        <td>productId</td>
        <td>{@link Integer}</td>
        <td>product_id</td>
        <td>int</td>
        <td>商品ID</td>
    </tr>
    *
    <tr>
        <td>imagePath</td>
        <td>{@link String}</td>
        <td>image_path</td>
        <td>varchar</td>
        <td>图片路径</td>
    </tr>
    *
    <tr>
        <td>sort</td>
        <td>{@link Integer}</td>
        <td>sort</td>
        <td>int</td>
        <td>排序</td>
    </tr>
    *
    <tr>
        <td>createId</td>
        <td>{@link Integer}</td>
        <td>create_id</td>
        <td>int</td>
        <td>上传人</td>
    </tr>
    *
    <tr>
        <td>createTime</td>
        <td>{@link java.util.Date}</td>
        <td>create_time</td>
        <td>datetime</td>
        <td>上传时间</td>
    </tr>
    *
    <tr>
        <td>sellerId</td>
        <td>{@link Integer}</td>
        <td>seller_id</td>
        <td>int</td>
        <td>商家ID</td>
    </tr>
    *
    <tr>
        <td>state</td>
        <td>{@link Integer}</td>
        <td>state</td>
        <td>tinyint</td>
        <td>1、启用；2、不启用</td>
    </tr>
    *
    <tr>
        <td>productLead</td>
        <td>{@link Integer}</td>
        <td>product_lead</td>
        <td>tinyint</td>
        <td>商品主图1、主图；2、非主图，主图只能有一张</td>
    </tr>
    *
</table>
*
*/
public class ProductPicture implements Serializable {

    /**
    *Comment for <code>serialVersionUID</code>
    */
    private static final long serialVersionUID = -264584636750741280L;

    private Integer id;

    private Integer productId; //商品ID

    private String imagePath; //图片路径

    private Integer sort; //排序

    private Integer createId; //上传人

    private java.util.Date createTime; //上传时间

    private Integer sellerId; //商家ID

    private Integer state; //1、启用；2、不启用

    private Integer productLead; //商品主图1、主图；2、非主图，主图只能有一张

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
    * 获取商品ID
    */
    public Integer getProductId() {
        return this.productId;
    }

    /**
    * 设置商品ID
    */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
    * 获取图片路径
    */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
    * 设置图片路径
    */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
    * 获取排序
    */
    public Integer getSort() {
        return this.sort;
    }

    /**
    * 设置排序
    */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
    * 获取上传人
    */
    public Integer getCreateId() {
        return this.createId;
    }

    /**
    * 设置上传人
    */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
    * 获取上传时间
    */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
    * 设置上传时间
    */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
    * 获取商家ID
    */
    public Integer getSellerId() {
        return this.sellerId;
    }

    /**
    * 设置商家ID
    */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
    * 获取1、启用；2、不启用
    */
    public Integer getState() {
        return this.state;
    }

    /**
    * 设置1、启用；2、不启用
    */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
    * 获取商品主图1、主图；2、非主图，主图只能有一张
    */
    public Integer getProductLead() {
        return this.productLead;
    }

    /**
    * 设置商品主图1、主图；2、非主图，主图只能有一张
    */
    public void setProductLead(Integer productLead) {
        this.productLead = productLead;
    }
}