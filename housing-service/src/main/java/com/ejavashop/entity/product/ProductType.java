package com.ejavashop.entity.product;

import java.io.Serializable;

/**
* 商品类型表
* <p>Table: <strong>product_type</strong>
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
        <td>name</td>
        <td>{@link String}</td>
        <td>name</td>
        <td>varchar</td>
        <td>名称</td>
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
        <td>productNormIds</td>
        <td>{@link String}</td>
        <td>product_norm_ids</td>
        <td>varchar</td>
        <td>关联规格ID集合</td>
    </tr>
    *
    <tr>
        <td>productBrandIds</td>
        <td>{@link String}</td>
        <td>product_brand_ids</td>
        <td>varchar</td>
        <td>关联品牌ID集合</td>
    </tr>
    *
    <tr>
        <td>createId</td>
        <td>{@link Integer}</td>
        <td>create_id</td>
        <td>int</td>
        <td>创建人</td>
    </tr>
    *
    <tr>
        <td>createTime</td>
        <td>{@link java.util.Date}</td>
        <td>create_time</td>
        <td>datetime</td>
        <td>创建时间</td>
    </tr>
    *
</table>
*
*/
public class ProductType implements Serializable {

    /**
    *Comment for <code>serialVersionUID</code>
    */
    private static final long serialVersionUID = -264584636750741280L;

    private Integer id;

    private String name; //名称

    private Integer sort; //排序

    private String productNormIds; //关联规格ID集合

    private String productBrandIds; //关联品牌ID集合

    private Integer createId; //创建人

    private java.util.Date createTime; //创建时间

    private String createUser; //创建人

    private String normNames;  //规格名称
    private String brandNames; //品牌名称

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
    * 获取名称
    */
    public String getName() {
        return this.name;
    }

    /**
    * 设置名称
    */
    public void setName(String name) {
        this.name = name;
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
    * 获取关联规格ID集合
    */
    public String getProductNormIds() {
        return this.productNormIds;
    }

    /**
    * 设置关联规格ID集合
    */
    public void setProductNormIds(String productNormIds) {
        this.productNormIds = productNormIds;
    }

    /**
    * 获取关联品牌ID集合
    */
    public String getProductBrandIds() {
        return this.productBrandIds;
    }

    /**
    * 设置关联品牌ID集合
    */
    public void setProductBrandIds(String productBrandIds) {
        this.productBrandIds = productBrandIds;
    }

    /**
    * 获取创建人
    */
    public Integer getCreateId() {
        return this.createId;
    }

    /**
    * 设置创建人
    */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
    * 获取创建时间
    */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
    * 设置创建时间
    */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getNormNames() {
        return normNames;
    }

    public void setNormNames(String normNames) {
        this.normNames = normNames;
    }

    public String getBrandNames() {
        return brandNames;
    }

    public void setBrandNames(String brandNames) {
        this.brandNames = brandNames;
    }
}