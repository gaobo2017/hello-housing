package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.seller.SellerManageCate;
import com.ejavashop.vo.product.FrontProductCateVO;
import com.ejavashop.vo.product.ProductCateVO;

public interface IProductCateService {
    /**
    * 保存商品分类
    * @param  productCate
    * @return
    */
    ServiceResult<Boolean> saveProductCate(ProductCate productCate);

    /**
    * 更新商品分类
    * @param  productCate
    * @return
    */
    ServiceResult<Boolean> updateProductCate(ProductCate productCate);

    /**
    * 删除商品分类
    * @param  id
    * @return
    */
    ServiceResult<Boolean> delProductCate(Integer id);

    /**
    * 根据id取得商品分类
    * @param productCateId
    * @return
    */
    ServiceResult<ProductCate> getProductCateById(Integer productCateId);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<ProductCateVO>> pageProductCate(Map<String, String> queryMap,
                                                       PagerInfo pager);

    /**
     * 根据pid查询商品分类信息
     * @param pid
     * @return
     */
    ServiceResult<List<ProductCate>> getByPid(Integer pid);

    /**
     * 商家发布商品时根据商家id获取商家一级分类信息
     * @param sellerId
     * @return
     */
    ServiceResult<List<ProductCate>> getCateBySellerId(Integer sellerId);

    /**
     * 商家发布商品时根据商家id和上级分类id获取商家下级分类信息
     * @param sellerId
     * @param pid
     * @return
     */
    ServiceResult<List<ProductCate>> getCateBySellerId(Integer sellerId, Integer pid);

    /**
     * 根据商品分类id，获取分类路径名称 例如：数码办公 >电脑整机 >平板电脑
     * @param productCateId
     * @return
     */
    ServiceResult<String> getCatePathStrById(Integer productCateId);

    /**
     * 将该商家申请置为审核通过
     * @param id
     */
    SellerManageCate getSellerManageCate(Integer id);

    Boolean updateSellerManageCate(SellerManageCate cate);

    //
    /**
     * 根据pid查询商品分类信息
     * @param pid
     * @param seller
     * @return
     */
    ServiceResult<List<ProductCateVO>> getByPidAndSeller(Integer pid, Integer seller);

    /**
     * 取得所有显示状态的商品分类
     * @param  map
     * @return
     */
    ServiceResult<List<FrontProductCateVO>> getProductCateList(Map<String, Object> queryMap);
}