package com.ejavashop.entity.product;

import java.io.Serializable;

/**
* 货品表
* <p>Table: <strong>product_goods</strong>
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
	*   <tr><td>normAttrId</td><td>{@link String}</td><td>norm_attr_id</td><td>varchar</td><td>规格属性值ID，用逗号分隔</td></tr>
    *
    <tr>
        <td>normName</td>
        <td>{@link String}</td>
        <td>norm_name</td>
        <td>varchar</td>
        <td>规格值，用逗号分隔</td>
    </tr>
    *
    <tr>
        <td>mallPcPrice</td>
        <td>{@link java.math.BigDecimal}</td>
        <td>mall_pc_price</td>
        <td>decimal</td>
        <td>PC价格</td>
    </tr>
    *
    <tr>
        <td>mallMobilePrice</td>
        <td>{@link java.math.BigDecimal}</td>
        <td>mall_mobile_price</td>
        <td>decimal</td>
        <td>Mobile价格</td>
    </tr>
    *
    <tr>
        <td>productStock</td>
        <td>{@link Integer}</td>
        <td>product_stock</td>
        <td>int</td>
        <td>库存</td>
    </tr>
    *
    <tr>
        <td>productStockWarning</td>
        <td>{@link Integer}</td>
        <td>product_stock_warning</td>
        <td>int</td>
        <td>库存预警值</td>
    </tr>
    *
    <tr>
        <td>actualSales</td>
        <td>{@link Integer}</td>
        <td>actual_sales</td>
        <td>int</td>
        <td>所有规格销量相加等于商品总销量</td>
    </tr>
    *
    <tr>
        <td>sku</td>
        <td>{@link String}</td>
        <td>sku</td>
        <td>varchar</td>
        <td>sku</td>
    </tr>
    *
    <tr>
        <td>images</td>
        <td>{@link String}</td>
        <td>images</td>
        <td>varchar</td>
        <td>规格图片（规格图片，用逗号隔开，和规格值对应，如果没有图片，那为空）</td>
    </tr>
    *
</table>
*
*/
public class ProductGoods implements Serializable {

    /**
    *Comment for <code>serialVersionUID</code>
    */
    private static final long    serialVersionUID = -264584636750741280L;

    private Integer              id;

    private Integer              productId;                              //商品ID

    private String               normAttrId;                             //规格属性值ID，用逗号分隔

    private String               normName;                               //规格值，用逗号分隔

    private java.math.BigDecimal mallPcPrice;                            //PC价格

    private java.math.BigDecimal mallMobilePrice;                        //Mobile价格

    private Integer              productStock;                           //库存

    private Integer              productStockWarning;                    //库存预警值

    private Integer              actualSales;                            //所有规格销量相加等于商品总销量

    private String               sku;

    private String               images;                                 //规格图片（规格图片，用逗号隔开，和规格值对应，如果没有图片，那为空）

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
     * 获取规格属性值ID，用逗号分隔
     */
    public String getNormAttrId() {
        return this.normAttrId;
    }

    /**
     * 设置规格属性值ID，用逗号分隔
     */
    public void setNormAttrId(String normAttrId) {
        this.normAttrId = normAttrId;
    }

    /**
     * 获取规格值，用逗号分隔
     */
    public String getNormName() {
        return this.normName;
    }

    /**
    * 设置规格值，用逗号分隔
    */
    public void setNormName(String normName) {
        this.normName = normName;
    }

    /**
    * 获取PC价格
    */
    public java.math.BigDecimal getMallPcPrice() {
        return this.mallPcPrice;
    }

    /**
    * 设置PC价格
    */
    public void setMallPcPrice(java.math.BigDecimal mallPcPrice) {
        this.mallPcPrice = mallPcPrice;
    }

    /**
    * 获取Mobile价格
    */
    public java.math.BigDecimal getMallMobilePrice() {
        return this.mallMobilePrice;
    }

    /**
    * 设置Mobile价格
    */
    public void setMallMobilePrice(java.math.BigDecimal mallMobilePrice) {
        this.mallMobilePrice = mallMobilePrice;
    }

    /**
    * 获取库存
    */
    public Integer getProductStock() {
        return this.productStock;
    }

    /**
    * 设置库存
    */
    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    /**
    * 获取库存预警值
    */
    public Integer getProductStockWarning() {
        return this.productStockWarning;
    }

    /**
    * 设置库存预警值
    */
    public void setProductStockWarning(Integer productStockWarning) {
        this.productStockWarning = productStockWarning;
    }

    /**
    * 获取所有规格销量相加等于商品总销量
    */
    public Integer getActualSales() {
        return this.actualSales;
    }

    /**
    * 设置所有规格销量相加等于商品总销量
    */
    public void setActualSales(Integer actualSales) {
        this.actualSales = actualSales;
    }

    /**
    * 获取sku
    */
    public String getSku() {
        return this.sku;
    }

    /**
    * 设置sku
    */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
    * 获取规格图片（规格图片，用逗号隔开，和规格值对应，如果没有图片，那为空）
    */
    public String getImages() {
        return this.images;
    }

    /**
    * 设置规格图片（规格图片，用逗号隔开，和规格值对应，如果没有图片，那为空）
    */
    public void setImages(String images) {
        this.images = images;
    }
}