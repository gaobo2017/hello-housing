package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.seller.SellerApplyBrand;

/**
 * 商家品牌管理
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
public interface ISellerBrandService {

    /**
     * 保存品牌信息
     * 用途：商家中心添加品牌信息
     * @param brand
     * @return
     */
    ServiceResult<Boolean> save(SellerApplyBrand brand);

    /**
     * 根据id查询品牌信息
     * @param id
     * @return
     */
    ServiceResult<SellerApplyBrand> getById(Integer id);

    /**
     * 分页查询品牌信息
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<SellerApplyBrand>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 分页查询待提交品牌信息
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<SellerApplyBrand>> todoList(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 修改品牌信息
     * @param brand
     * @return
     */
    ServiceResult<Boolean> update(SellerApplyBrand brand);

    /**
     * 提交到平台审核
     * @param id
     * @return
     */
    ServiceResult<Boolean> commit(Integer id);

    /**
     * 删除品牌信息，逻辑删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);
}
