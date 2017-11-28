package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.seller.SellerManageCate;

public interface ISellerManageCateService {
    /**
    * 保存商家可以经营商品分类表
    * @param  sellerManageCate
    * @return
    */
    ServiceResult<Boolean> saveSellerManageCate(SellerManageCate sellerManageCate);

    /**
    * 更新商家可以经营商品分类表
    * @param  sellerManageCate
    * @return
    */
    ServiceResult<Boolean> updateSellerManageCate(SellerManageCate sellerManageCate);

    /**
    * 删除商家可以经营商品分类表
    * @param  id
    * @return
    */
    ServiceResult<Boolean> delSellerManageCate(Integer id);

    /**
     * 平台审核
     * @param map
     * key: id、optId
     * @return
     */
    ServiceResult<Boolean> auditing(Map<String, String> map);

    /**
     * 平台停用
     * @param map
     * key: id、stopId、stopReason
     * @return
     */
    ServiceResult<Boolean> stop(Map<String, String> map);

    /**
     * 提交平台审核
     * @param id
     * @return
     */
    ServiceResult<Boolean> commit(Integer id);

    /**
    * 根据id取得商家可以经营商品分类表
    * @param sellerManageCateId
    * @return
    */
    ServiceResult<SellerManageCate> getSellerManageCateById(Integer sellerManageCateId);

    /**
    * 分页
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SellerManageCate>> pageSellerManageCate(Map<String, String> queryMap,
                                                               PagerInfo pager);
}