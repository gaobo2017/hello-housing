package com.ejavashop.vo.product;

import java.util.List;

import com.ejavashop.entity.product.ProductTypeAttr;

/**
 * 商品分类属性
 *                       
 * @Filename: ProductTypeAttrVO.java
 * @Version: 1.0
 *
 */
public class ProductTypeAttrVO extends ProductTypeAttr {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7638315549640131068L;
    private String            productTypeName;

    private List<String>      values;

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    private int    isChoice      = 0; //是否选择
    private int    isChoiceIndex = -1; //选择索引
    private String choiceAll;         //选中的URL中得拼装
    private String choiceName;        //选中的文字

    public int getIsChoice() {
        return isChoice;
    }

    public void setIsChoice(int isChoice) {
        this.isChoice = isChoice;
    }

    public int getIsChoiceIndex() {
        return isChoiceIndex;
    }

    public void setIsChoiceIndex(int isChoiceIndex) {
        this.isChoiceIndex = isChoiceIndex;
    }

    public String getChoiceAll() {
        return choiceAll;
    }

    public void setChoiceAll(String choiceAll) {
        this.choiceAll = choiceAll;
    }

    public String getChoiceName() {
        if (isChoiceIndex != -1 && values.size() > isChoiceIndex) {
            return values.get(isChoiceIndex);
        }
        return choiceName;
    }

    public void setChoiceName(String choiceName) {
        this.choiceName = choiceName;
    }

}