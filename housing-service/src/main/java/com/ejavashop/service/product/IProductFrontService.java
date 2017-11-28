package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.Product;

/**
 * 商城所有关于商品的查询
 *                       
 * @Filename: IProductFrontService.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public interface IProductFrontService {

    /**
     * 根据商品分类查询商品
     * @param cateId 商品分类ID
     * @param mapCondition 传递的参数
     * @param stack 传出参数map集合
     */
    void getProducts(Integer cateId, Map<String, Object> mapCondition, Map<String, Object> stack);

    /**
     * 根据分类查询列表页推荐的头部信息
     * @param cateId 分类Id
     * @return
     */
    ServiceResult<List<Product>> getProductListByCateIdTop(int cateId);

    /**
     * 根据分类查询列表页推荐的左边商品
     * @param cateId 分类Id
     * @return
     */
    ServiceResult<List<Product>> getProductListByCateIdLeft(int cateId);

    /**
     * 查询二级分类下三级分类的商品，默认取5个
     * @param cateId 分类Id
     * @return
     */
    ServiceResult<List<Product>> getProductListByCateId2(int cateId);

    /**
     * 搜索页面推荐商品信息头部
     * @return
     */
    ServiceResult<List<Product>> getProductSearchByTop();

    /**
     * 搜索页面推荐商品信息左部
     * @return
     */
    ServiceResult<List<Product>> getProductSearchByLeft();

    /**
     * 根据商家商品分类查询商家列表页 商品信息
     * @param start 
     * @param size
     * @param cateId 商家分类
     * @param sellerId 商家ID
     * @param sort 排序  0:默认；1、价格从低到高；2、价格从高到底；3、销量从高到底；4、销量从低到高；5、上架时间新旧；6、上架时间旧新
     * @return
     */
    ServiceResult<List<Product>> getProductListBySellerCateId(int start, int size, int cateId,
                                                              int sellerId, int sort);

    /**
     * 根据商家商品分类统计商家商品
     * @param cateId 商家分类
     * @param sellerId 商家ID
     * @return
     */
    ServiceResult<Integer> getProductListBySellerCateIdCount(int cateId, int sellerId);

}
