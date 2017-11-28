package com.ejavashop.model.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.product.ProductPictureReadDao;
import com.ejavashop.entity.product.ProductPicture;

@Component(value = "frontProductPictureModel")
public class FrontProductPictureModel {

    @Resource
    private ProductPictureReadDao productPictureReadDao;

    /**
    * 根据id取得商品对应图片表
    * @param  productPictureId
    * @return
    */
    public ProductPicture getProductPictureById(Integer productPictureId) {
        return productPictureReadDao.get(productPictureId);
    }

    /**
     * 根据id取得商品对应图片表
     * @param  productId
     * @return
     */
    public List<ProductPicture> getByProductIds(Integer productId) {
        return productPictureReadDao.getByProductId(productId);
    }

    /**
     * 根据商品ID获取商品的主图
     * @param productId
     * @return
     */
    public ProductPicture getproductLead(Integer productId) {
        return productPictureReadDao.getproductLead(productId);
    }

}
