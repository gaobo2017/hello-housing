package com.ejavashop.entity.product;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品选定的规格属性
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
public class ProductNormAttrOpt implements Serializable {

    private static final long serialVersionUID = -2211679112782022769L;
    private Integer           id;
    private Integer           productNormId;                           //规格ID
    private Integer           productId;                               //商品ID
    private Integer           sellerId;                                //商家ID
    private Integer           typeAttr;                                //2、自定义属性
    private Integer           type;                                    //1、文字；2、图片
    private String            name;                                    //属性名称
    private String            image;                                   //图片
    private Integer           createId;                                //创建人
    private Date              createTime;                              //创建时间
    private Integer           attrId;                                  //属性id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductNormId() {
        return productNormId;
    }

    public void setProductNormId(Integer productNormId) {
        this.productNormId = productNormId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getTypeAttr() {
        return typeAttr;
    }

    public void setTypeAttr(Integer typeAttr) {
        this.typeAttr = typeAttr;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        ProductNormAttrOpt other = (ProductNormAttrOpt) obj;
        if (this.id.intValue() == other.getId().intValue())
            return true;
        return true;
    }

}
