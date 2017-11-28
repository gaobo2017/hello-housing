package com.ejavashop.model.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.product.ProductAskReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.write.product.ProductAskWriteDao;
import com.ejavashop.dao.shop.write.product.ProductWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerWriteDao;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAsk;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.util.FrontProductPictureUtil;

/**
 * 商品咨询管理model
 * 
 * @Filename: ProductAskModel.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Component(value = "productAskModel")
public class ProductAskModel {

    @Resource
    private ProductAskWriteDao      productAskWriteDao;
    @Resource
    private ProductAskReadDao       productAskReadDao;

    @Resource
    private ProductWriteDao         productWriteDao;
    @Resource
    private ProductReadDao          productReadDao;

    @Resource
    private SellerWriteDao          sellerWriteDao;

    @Resource
    private FrontProductPictureUtil frontProductPictureUtil;

    public ProductAsk getProductAskById(Integer productAskId) {
        Assert.notNull(productAskWriteDao, "Property 'productAskWriteDao' is required.");
        ProductAsk productAsk = productAskWriteDao.get(productAskId);

        Product product = productWriteDao.get(productAsk.getProductId());
        Seller seller = sellerWriteDao.get(productAsk.getSellerId());
        //拼装返回对象
        productAsk.setProductName(product.getName1());
        productAsk.setSellerName(seller.getSellerName());

        return productAsk;
    }

    public Boolean updateProductAsk(ProductAsk productAsk) {
        Assert.notNull(productAskWriteDao, "Property 'productAskWriteDao' is required.");
        // 参数校验
        if (productAsk == null) {
            throw new BusinessException("商品咨询不能为空，请重试！");
        } else if (productAsk.getId() == null) {
            throw new BusinessException("商品咨询id不能为空，请重试！");
        }
        Integer count = productAskWriteDao.update(productAsk);
        if (count == 0) {
            throw new BusinessException("会员商品咨询更新失败，请重试！");
        }
        return true;
    }

    public Integer getProductAsksCount(Map<String, String> queryMap) {
        Assert.notNull(productAskWriteDao, "Property 'productAskWriteDao' is required.");
        return productAskWriteDao.getProductAsksCount(queryMap);
    }

    public List<ProductAsk> getProductAsks(Map<String, String> queryMap, Integer start,
                                           Integer size) throws Exception {
        Assert.notNull(productAskWriteDao, "Property 'productAskWriteDao' is required.");
        return productAskWriteDao.getProductAsks(queryMap, start, size);
    }

    public List<ProductAsk> getProductAsksWithInfo(Map<String, String> queryMap, Integer start,
                                                   Integer size) throws Exception {
        Assert.notNull(productAskWriteDao, "Property 'productAskWriteDao' is required.");

        List<ProductAsk> list = productAskWriteDao.getProductAsks(queryMap, start, size);
        for (ProductAsk bean : list) {
            Product product = productWriteDao.get(bean.getProductId());
            Seller seller = sellerWriteDao.get(bean.getSellerId());
            //拼装返回对象
            bean.setProductName(product.getName1());
            bean.setSellerName(seller.getSellerName());

            //获得产品对应的小图 
            String productLeadLittle = frontProductPictureUtil
                .getproductLeadLittle(bean.getProductId());
            bean.setProductLeadLittle(productLeadLittle);
        }
        return list;
    }

    /**
     * 保存商品咨询管理
     * @param  productAsk
     * @return
     */
    public ProductAsk saveProductAsk(ProductAsk productAsk, Member member) {
        // 参数校验
        if (productAsk == null) {
            throw new BusinessException("商品咨询不能为空，请重试！");
        } else if (productAsk.getProductId() == null) {
            throw new BusinessException("商品咨询中产品id不能为空，请重试！");
        }

        //根据条件取咨询
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_userId", String.valueOf(member.getId()));
        queryMap.put("q_productId", String.valueOf(productAsk.getProductId()));
        List<ProductAsk> beanList = productAskWriteDao.getProductAsks(queryMap, 0, 0);
        if (beanList.size() > 0) {
            throw new BusinessException("该产品已经咨询过了，我们会尽快处理！");
        }

        productAsk.setUserId(member.getId());
        productAsk.setUserName(member.getName());
        productAsk.setState(ProductAsk.STATE_1);
        //1、保存信息
        Integer count = productAskWriteDao.insert(productAsk);
        if (count == 0) {
            throw new BusinessException("商品咨询保存失败，请重试！");
        }
        return productAsk;
    }

    /**
     * 根据产品及用户id取得商品咨询
     * @param productId
     * @param request
     * @return
     */
    public ProductAsk getProductAskByProductId(String productId, Integer memberId) {

        if (StringUtil.isEmpty(productId)) {
            throw new BusinessException("产品id为空，请重试！");
        }

        //根据条件取咨询
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("userId", memberId);
        queryMap.put("productId", productId);
        List<ProductAsk> beanList = productAskReadDao.queryList(queryMap);
        ProductAsk bean = new ProductAsk();
        if (beanList.size() > 0) {
            throw new BusinessException("该产品已经咨询过了，我们会尽快处理！");
        } else {
            //获得对应商家的产品信息
            Product product = productReadDao.get(Integer.parseInt(productId));
            //获得产品对应的小图 
            String productLeadLittle = frontProductPictureUtil
                .getproductLeadLittle(Integer.parseInt(productId));

            bean.setProductName(product.getName1());
            bean.setProductLeadLittle(productLeadLittle);

        }

        return bean;
    }
}
