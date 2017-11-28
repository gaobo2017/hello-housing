package com.ejavashop.service.seller;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.seller.SellerRoles;

/**
 * 商家角色
 *                       
 * @Filename: ISellerRolesService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISellerRolesService {

    /**
     * 根据id取得角色表
     * @param  sellerRolesId
     * @return
     */
    ServiceResult<SellerRoles> getSellerRolesById(Integer sellerRolesId);

    /**
     * 保存角色表
     * @param  sellerRoles
     * @return
     */
    ServiceResult<Integer> saveSellerRoles(SellerRoles sellerRoles);

    /**
    * 更新角色表
    * @param  sellerRoles
    * @return
    */
    ServiceResult<Integer> updateSellerRoles(SellerRoles sellerRoles);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SellerRoles>> getSellerRoles(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> deleteSellerRole(Integer id);
}