package com.ejavashop.vo.product;

import java.util.List;

import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductCate;

/**
 * 二级列表页，商品和分类的VO对象
 *                       
 * @Filename: ProductListCate2.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public class ProductListCate2 {
    private ProductCate   productCate;
    private List<Product> products;

    public ProductCate getProductCate() {
        return productCate;
    }

    public void setProductCate(ProductCate productCate) {
        this.productCate = productCate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
