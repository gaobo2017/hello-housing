package com.ejavashop.vo.product;

import java.util.List;

import com.ejavashop.entity.product.ProductCate;

public class FrontProductCateVO extends ProductCate {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long        serialVersionUID = 517865553096794672L;

    private List<FrontProductCateVO> childs;

    public List<FrontProductCateVO> getChilds() {
        return childs;
    }

    public void setChilds(List<FrontProductCateVO> childs) {
        this.childs = childs;
    }

}
