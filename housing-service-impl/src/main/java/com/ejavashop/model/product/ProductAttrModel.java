package com.ejavashop.model.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.write.product.ProductAttrWriteDao;
import com.ejavashop.entity.product.ProductAttr;

@Component
public class ProductAttrModel {
    @Resource
    private ProductAttrWriteDao productAttrWriteDao;

    public Boolean saveProductAttr(ProductAttr productAttr) {
        return productAttrWriteDao.insert(productAttr) > 0;
    }

    public Boolean updateProductAttr(ProductAttr productAttr) {
        return productAttrWriteDao.update(productAttr) > 0;
    }

    public Boolean delProductAttr(Integer productAttrId) {
        //1. business check
        if (null == productAttrId || 0 == productAttrId)
            throw new BusinessException("根据id删除商品对应属性表失败，id为空");

        //2. dbOper
        return productAttrWriteDao.del(productAttrId) > 0;
    }

    public ProductAttr getProductAttrById(Integer productAttrId) {
        if (null == productAttrId || 0 == productAttrId)
            throw new BusinessException("根据id获取商品对应属性表失败，id为空");
        return productAttrWriteDao.get(productAttrId);
    }

    public List<ProductAttr> getByProductId(Integer productId) {
        return productAttrWriteDao.getByProductId(productId);
    }

    public List<ProductAttr> pageProductAttr(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(productAttrWriteDao.count(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        return productAttrWriteDao.page(queryMap, start, size);
    }
}
