package com.ejavashop.model.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.product.ProductPictureReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.dao.shop.write.product.ProductAttrWriteDao;
import com.ejavashop.dao.shop.write.product.ProductBrandWriteDao;
import com.ejavashop.dao.shop.write.product.ProductCateWriteDao;
import com.ejavashop.dao.shop.write.product.ProductGoodsWriteDao;
import com.ejavashop.dao.shop.write.product.ProductNormWriteDao;
import com.ejavashop.dao.shop.write.product.ProductPictureWriteDao;
import com.ejavashop.dao.shop.write.product.ProductWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerCateWriteDao;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAttr;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.product.ProductNorm;
import com.ejavashop.entity.product.ProductNormAttr;
import com.ejavashop.entity.product.ProductNormAttrOpt;
import com.ejavashop.entity.product.ProductPicture;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.seller.SellerCate;
import com.ejavashop.util.FrontProductPictureUtil;

@Service(value = "productModel")
public class ProductModel {
    private static Logger log = LogManager.getLogger(ProductModel.class);

    @Resource
    private ProductWriteDao              productWriteDao;
    @Resource
    private ProductReadDao               productReadDao;
    @Resource
    private ProductPictureWriteDao       productPictureWriteDao;
    @Resource
    private ProductPictureReadDao        productPictureReadDao;
    @Resource
    private ProductAttrWriteDao          productAttrWriteDao;
    @Resource
    private ProductGoodsWriteDao         productGoodsWriteDao;
    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;
    @Resource
    private ProductBrandWriteDao         productBrandWriteDao;
    @Resource
    private ProductCateWriteDao          productCateWriteDao;
    @Resource
    private SellerCateWriteDao           sellerCateWriteDao;
    @Resource
    private ProductNormWriteDao          productNormWriteDao;
    @Resource
    private SellerReadDao                sellerReadDao;
    /**
     * 产品图片获取工具类
     */
    @Resource
    private FrontProductPictureUtil      frontProductPictureUtil;

    public List<Product> getByCateIdTop(Integer cateId, Integer limit) {
        return productReadDao.getByCateIdTop(cateId, limit);
    }

    /**
     * 获取size个商家推荐商品
     * @param sellerId 商家ID
     * @param size 获取条数
     * @return
     */
    public List<Product> getSellerRecommendProducts(Integer sellerId, Integer size) {
        List<Product> list = productReadDao.getSellerRecommendProducts(sellerId, size);
        this.setProductMiddleImg(list);
        return list;
    }

    /**
     * 获取size个商家新品
     * @param sellerId 商家ID
     * @param size 获取条数
     * @return
     */
    public List<Product> getSellerNewProducts(Integer sellerId, Integer size) {
        List<Product> list = productReadDao.getSellerNewProducts(sellerId, size);
        this.setProductMiddleImg(list);
        return list;
    }

    /**
     * 查询商家所有在售商品（商家列表页）
     * @param sellerId
     * @param sort 0:默认；1、销量从大到小；2、评价从多到少；3、价格从低到高；4、价格从高到低
     * @param sellerCateId
     * @param start
     * @param size
     * @return
     */
    public List<Product> getProductForSellerList(Integer sellerId, Integer sort,
                                                 Integer sellerCateId, Integer start,
                                                 Integer size) {
        // 排序支持0到4，其他一律按0处理
        if (sort == null || sort > 4) {
            sort = 0;
        }
        List<Product> products = productReadDao.getProductForSellerList(sellerId, sort,
            sellerCateId, start, size);
        this.setProductMiddleImg(products);
        return products;
    }

    public Integer getProductForSellerListCount(Integer sellerId, Integer sort,
                                                Integer sellerCateId) {
        // 排序支持0到4，其他一律按0处理
        if (sort == null || sort > 4) {
            sort = 0;
        }
        return productReadDao.getProductForSellerListCount(sellerId, sort, sellerCateId);
    }

    /**
     * 给商品设置中图路径
     * @param list
     */
    private void setProductMiddleImg(List<Product> list) {
        if (list != null && list.size() > 0) {
            for (Product product : list) {
                //中图路径
                String productLeadMiddle = frontProductPictureUtil
                    .getproductLeadMiddle(product.getId());
                product.setImagePath(productLeadMiddle);
            }
        }
    }

    /**
     * 获取size个推荐商品
     * @return
     */
    public List<Product> getRecommendProducts(Integer size) {
        List<Product> list = productReadDao.getRecommendProducts(size);
        this.setProductMiddleImg(list);
        return list;
    }

    public Boolean saveProduct(Product product, List<ProductPicture> productPictureList,
                               List<ProductAttr> productAttrList) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {

            //1.check

            //2.save product
            this.dbConstrainsProduct(product);
            productWriteDao.insert(product);
            //3.save productPicture
            if (null != productPictureList && productPictureList.size() > 0) {
                for (ProductPicture picture : productPictureList) {
                    picture.setProductId(product.getId());
                    productPictureWriteDao.insert(picture);
                }
            }
            //4.save productAttr
            if (null != productAttrList && productAttrList.size() > 0) {
                for (ProductAttr attr : productAttrList) {
                    attr.setProductId(product.getId());
                    productAttrWriteDao.insert(attr);
                }
            }
            //5.save productGood
            if (null != product.getGoodsList() && product.getGoodsList().size() > 0) {
                for (ProductGoods goods : product.getGoodsList()) {
                    //goods.setNormName();颜色:黄色,尺码:XL normName:normAttrName,normName:normAttrName,
                    String normName = "";
                    String normAttrId = goods.getNormAttrId();
                    if (!StringUtil.isEmpty(normAttrId)) {
                        String[] normAttrIds = normAttrId.split(",");
                        for (String attrId : normAttrIds) {
                            ProductNormAttr normAttr = productNormWriteDao
                                .getNormAttrById(Integer.valueOf(attrId));
                            if (null != normAttr && normAttr.getProductNormId() != null) {
                                ProductNorm norm = productNormWriteDao
                                    .getNormById(normAttr.getProductNormId());
                                if (null != norm && !StringUtil.isEmpty(norm.getName())) {
                                    normName += norm.getName() + "," + normAttr.getName() + ";";
                                }
                            }
                        }
                        normName = normName.substring(0, normName.length() - 1);
                        goods.setNormName(normName);
                        goods.setProductId(product.getId());
                        this.dbConstrainsProductGood(goods);
                        productGoodsWriteDao.insert(goods);
                    }
                }
            } else {
                //默认货品
                ProductGoods goods = new ProductGoods();
                goods.setProductId(product.getId());
                goods.setMallPcPrice(product.getMallPcPrice());
                goods.setMallMobilePrice(product.getMalMobilePrice());
                goods.setProductStock(product.getProductStock());
                goods.setProductStockWarning(5);
                goods.setActualSales(0);
                goods.setSku(product.getSku());
                goods.setImages("");
                this.dbConstrainsProductGood(goods);
                productGoodsWriteDao.insert(goods);
            }
            transactionManager.commit(status);

            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("ProductServiceImpl saveProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl saveProduct exception:", e);
            throw e;
        }
    }

    public Boolean updateProduct(Product product, List<ProductPicture> productPictureList,
                                 List<ProductAttr> productAttrList) {
        //如果商品被删除,取消推荐
        if (product != null && null != product.getState()
            && product.getState() == Product.STATE_5) {
            product.setIsTop(ConstantsEJS.PRODUCT_IS_TOP_1);
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (null == product)
                throw new BusinessException("更新商品失败，商品信息为空");
            if (null == product.getId() || 0 == product.getId())
                throw new BusinessException("更新商品失败，商品id为空");

            /**更新商品**/
            productWriteDao.update(product);

            /**更新商品图片**/
            if (null != productPictureList && productPictureList.size() > 0) {
                productPictureWriteDao.delByProductId(product.getId());
                for (ProductPicture picture : productPictureList) {
                    picture.setProductId(product.getId());
                    productPictureWriteDao.insert(picture);
                }
            }

            /**更新商品**/
            if (null != productAttrList && productAttrList.size() > 0) {
                productAttrWriteDao.delByProductId(product.getId());
                for (ProductAttr attr : productAttrList) {
                    attr.setProductId(product.getId());
                    productAttrWriteDao.insert(attr);
                }
            }

            /**更新货品**/
            if (null != product.getGoodsList() && product.getGoodsList().size() > 0) {
                for (ProductGoods goods : product.getGoodsList()) {
                    //goods.setNormName();颜色:黄色,尺码:XL normName:normAttrName,normName:normAttrName,
                    String normName = "";
                    String normAttrId = goods.getNormAttrId();
                    if (!StringUtil.isEmpty(normAttrId)) {
                        String[] normAttrIds = normAttrId.split(",");
                        for (String attrId : normAttrIds) {
                            ProductNormAttr normAttr = productNormWriteDao
                                .getNormAttrById(Integer.valueOf(attrId));
                            if (null != normAttr && normAttr.getProductNormId() != null) {
                                ProductNorm norm = productNormWriteDao
                                    .getNormById(normAttr.getProductNormId());
                                if (null != norm && !StringUtil.isEmpty(norm.getName())) {
                                    normName += norm.getName() + "," + normAttr.getName() + ";";
                                }
                            }
                        }
                        normName = normName.substring(0, normName.length() - 1);
                        goods.setNormName(normName);
                        goods.setProductId(product.getId());
                        this.dbConstrainsProductGood(goods);
                        ProductGoods dbGood = productGoodsWriteDao
                            .getByProductIdAndAttrId(product.getId(), goods.getNormAttrId());
                        if (null != dbGood) {
                            goods.setId(dbGood.getId());
                            productGoodsWriteDao.update(goods);
                        } else {
                            productGoodsWriteDao.insert(goods);
                        }
                    }
                }
            } else {
                // 默认货品
                ProductGoods goods = productGoodsWriteDao.getByProductId(product.getId());
                goods.setProductId(product.getId());
                goods.setMallPcPrice(product.getMallPcPrice());
                goods.setMallMobilePrice(product.getMalMobilePrice());
                goods.setProductStock(product.getProductStock());
                goods.setProductStockWarning(5);
                // goods.setActualSales(0);
                goods.setSku(product.getSku());
                goods.setImages("");
                this.dbConstrainsProductGood(goods);
                productGoodsWriteDao.update(goods);
            }
            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("ProductServiceImpl updateProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl updateProduct exception:", e);
            throw e;
        }
    }

    public Boolean delProduct(Integer productId) {
        if (null == productId || 0 == productId)
            throw new BusinessException("根据id删除商品表失败，id为空");
        Product product = productWriteDao.get(productId);
        if (product.getState().intValue() == Product.STATE_6) {
            throw new BusinessException("已经上架的商品不能删除");
        }
        return productWriteDao.updateState(productId, Product.STATE_5) > 0;
    }

    public Product getProductById(Integer productId) {
        if (null == productId || 0 == productId)
            throw new BusinessException("根据id获取商品表失败，id为空");

        Product product = productWriteDao.get(productId);
        if (product.getIsNorm() != 2) {
            ProductGoods goods = productGoodsWriteDao.getByProductId(productId);
            product.setSku(goods.getSku());
        }
        return product;
    }

    public List<Product> pageProduct(Map<String, String> queryMap,
                                     PagerInfo pager) throws Exception {
        List<Product> list = new ArrayList<Product>();
        try {
            Integer start = 0, size = 0;
            String state = queryMap.get("q_state");
            if (!StringUtil.isEmpty(state) && state.indexOf(",") != -1) {
                if (pager != null) {
                    pager.setRowsCount(productWriteDao.count1(queryMap));
                    start = pager.getStart();
                    size = pager.getPageSize();
                }
                list = productWriteDao.page1(queryMap, start, size);
            } else {
                if (pager != null) {
                    pager.setRowsCount(productWriteDao.count(queryMap));
                    start = pager.getStart();
                    size = pager.getPageSize();
                }
                list = productWriteDao.page(queryMap, start, size);
            }

            for (Product pro : list) {
                ProductCate pcate = productCateWriteDao.get(pro.getProductCateId());
                pro.setProductCateName(pcate == null ? null : pcate.getName());
                pro.setProductBrandName(
                    productBrandWriteDao.getById(pro.getProductBrandId()).getName());
                SellerCate cate = sellerCateWriteDao.get(pro.getSellerCateId());
                pro.setSellerCateName(cate == null ? null : cate.getName());

                Seller seller = sellerReadDao.get(pro.getSellerId());
                if (null != seller && !StringUtil.isEmpty(seller.getSellerName())) {
                    pro.setSeller(seller.getSellerName());
                }
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    public List<Product> getProductsBySellerId(Integer sellerid) {
        if (sellerid == null)
            throw new BusinessException("没有商家");
        return productWriteDao.getProductsBySellerId(sellerid);
    }

    public boolean saveOrupdate(Product product, List<ProductPicture> productPictureList,
                                List<ProductAttr> productAttrList,
                                List<ProductGoods> productGoodsList,
                                List<ProductNormAttrOpt> optlist) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {

            if (product.getId() == null || product.getId() == 0) {
                productWriteDao.insert(product);
            } else {
                //更新商品前删除之前所有关联
                deleteProRelation(product.getId());
                productWriteDao.update(product);
            }
            //3.save productPicture
            if (null != productPictureList && productPictureList.size() > 0) {
                for (ProductPicture picture : productPictureList) {
                    picture.setProductId(product.getId());
                    productPictureWriteDao.insert(picture);
                }
            }
            //4.save productAttr
            if (null != productAttrList && productAttrList.size() > 0) {
                for (ProductAttr attr : productAttrList) {
                    attr.setProductId(product.getId());
                    productAttrWriteDao.insert(attr);
                }
            }
            //5.save productGoods
            if (null != productGoodsList && productGoodsList.size() > 0) {
                for (ProductGoods goods : productGoodsList) {
                    goods.setProductId(product.getId());
                    productGoodsWriteDao.insert(goods);
                }
            }
            //6.save productNormAttrOpt
            if (null != optlist && optlist.size() > 0) {
                for (ProductNormAttrOpt opt : optlist) {
                    opt.setProductId(product.getId());
                    productNormWriteDao.insertNormAttrOpt(opt);
                }
            }
            transactionManager.commit(status);
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            log.error("ProductServiceImpl saveProduct exception:", e);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("ProductServiceImpl saveProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl saveProduct exception:", e);
            throw e;
        }
        return true;
    }

    /**
     * 删除商品关联信息
     * @param proid
     */
    private void deleteProRelation(Integer proid) throws Exception {
        Assert.notNull(proid);

        //1.delete productPicture
        productPictureWriteDao.delByProductId(proid);

        //2.delete productAttr
        productAttrWriteDao.delByProductId(proid);

        //3.delete productGoods
        productGoodsWriteDao.deleteByProductId(proid);

        //4.delete productNormAttrOpt
        productNormWriteDao.deleteProductNormOptByProductId(proid);

    }

    public Integer updateByIds(Map<String, Object> param, List<Integer> ids) {
        return productWriteDao.updateByIds(param, ids);
    }

    /**
     * 根据商品ID修改商品状态
     * @param id
     * @param state
     * @return
     */
    public boolean updateProductState(Integer id, Integer state) {
        return productWriteDao.updateState(id, state) > 0;
    }

    /**
     * 根据商品ID修改商品推荐状态
     * @param id
     * @param isTop
     * @return
     */
    public boolean updateProductRecommend(Integer id, Integer isTop) {
        return productWriteDao.updateRecommend(id, isTop) > 0;
    }

    /**
     * 以商品id字符串获取商品
     * @param ids
     * @return
     */
    public List<Product> getProductsByIds(List<Integer> ids) {
        return productReadDao.getProductsByIds(ids);
    }

    private void dbConstrainsProduct(Product product) {
        product.setName1(StringUtil.dbSafeString(product.getName1(), false, 200));
        product.setName2(StringUtil.dbSafeString(product.getName2(), false, 200));
        product.setKeyword(StringUtil.dbSafeString(product.getKeyword(), false, 200));
        product.setNormIds(StringUtil.dbSafeString(product.getNormIds(), false, 255));
        product.setNormName(StringUtil.dbSafeString(product.getNormName(), false, 255));
        product.setMasterImg(StringUtil.dbSafeString(product.getMasterImg(), false, 255));

    }

    private void dbConstrainsProductGood(ProductGoods productGoods) {
        productGoods
            .setNormAttrId(StringUtil.dbSafeString(productGoods.getNormAttrId(), false, 255));
        productGoods.setNormName(StringUtil.dbSafeString(productGoods.getNormName(), false, 255));
        productGoods.setSku(StringUtil.dbSafeString(productGoods.getSku(), false, 50));
        productGoods.setImages(StringUtil.dbSafeString(productGoods.getImages(), false, 255));
    }

    /**
     * 查询最大的商品
     * @return
     */
    public Product getProductByMax() {
        return productReadDao.getProductByMax();
    }

    public Integer updateProduct(Product pro) {
        return productWriteDao.update(pro);
    }
}
