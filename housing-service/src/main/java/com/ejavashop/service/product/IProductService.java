package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAttr;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.product.ProductNormAttrOpt;
import com.ejavashop.entity.product.ProductPicture;

/**
 * 商品服务
 *                       
 * @Filename: IProductService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IProductService {

    /**
     * 获取cateId分类下的推荐商品
     * @param cateId
     * @return
     */
    ServiceResult<List<Product>> getByCateIdTop(Integer cateId, Integer limit);

    /**
     * 获取size个商家推荐商品
     * @param sellerId 商家ID
     * @param size 获取条数
     * @return
     */
    ServiceResult<List<Product>> getSellerRecommendProducts(Integer sellerId, Integer size);

    /**
     * 获取size个商家新品
     * @param sellerId 商家ID
     * @param size 获取条数
     * @return
     */
    ServiceResult<List<Product>> getSellerNewProducts(Integer sellerId, Integer size);

    /**
     * 查询商家所有在售商品（商家列表页）
     * @param sellerId
     * @param sort 0:默认；1、销量从大到小；2、评价从多到少；3、价格从低到高；4、价格从高到低
     * @param sellerCateId
     * @param pager
     * @return
     */
    ServiceResult<List<Product>> getProductForSellerList(Integer sellerId, Integer sort,
                                                         Integer sellerCateId, PagerInfo pager);

    /**
     * 获取size个推荐商品
     * @param size
     * @return
     */
    ServiceResult<List<Product>> getRecommendProducts(Integer size);

    /**
     * 保存商品信息
     * @param product 商品
     * @param productPictureList 商品图片
     * @param productAttrList 商品属性
     * @return
     */
    ServiceResult<Boolean> saveProduct(Product product, List<ProductPicture> productPictureList,
                                       List<ProductAttr> productAttrList);

    /**
    * 更新商品表
    * @param  product
    * @return
    */
    ServiceResult<Boolean> updateProduct(Product product, List<ProductPicture> productPictureList,
                                         List<ProductAttr> productAttrList);

    /**
    * 删除商品表
    * @param  id
    * @return
    */
    ServiceResult<Boolean> delProduct(Integer id);

    /**
    * 根据id取得商品表
    * @param productId
    * @return
    */
    ServiceResult<Product> getProductById(Integer productId);

    /**
    * 分页
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<Product>> pageProduct(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 以商家id获取所有商品
     * @param integer
     * @return
     */
    ServiceResult<List<Product>> getProductsBySellerId(Integer integer);

    /**
     * 保存或修改商品
     * @param product
     * @param productPictureList
     * @param productAttrList
     * @param productGoodsList
     * @param optlist
     * @return
     */
    ServiceResult<Boolean> saveOrupdate(Product product, List<ProductPicture> productPictureList,
                                        List<ProductAttr> productAttrList,
                                        List<ProductGoods> productGoodsList,
                                        List<ProductNormAttrOpt> optlist);

    ServiceResult<Integer> updateByIds(Map<String, Object> map, List<Integer> string2IntegerList);

    /**
     * 根据商品ID修改状态
     * @param id
     * @param state
     * @return
     */
    ServiceResult<Boolean> updateProductState(Integer id, Integer state);

    /**
     * 根据商品ID修改推荐状态
     * @param id
     * @param isTop
     * @return
     */
    ServiceResult<Boolean> updateProductRecommend(Integer id, Integer isTop);

    /**
     * 以商品id字符串获取商品
     * @param ids
     * @return
     */
    ServiceResult<List<Product>> getProductsByIds(List<Integer> ids);

    /**
     * 查询最大的商品
     * @return
     */
    ServiceResult<Product> getProductByMax();

    /**
     * 更新单个商品
     * @param product
     * @return
     */
    ServiceResult<Boolean> updateProduct(Product product);
}