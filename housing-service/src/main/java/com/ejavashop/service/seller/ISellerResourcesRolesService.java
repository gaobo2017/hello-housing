package com.ejavashop.service.seller;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.seller.SellerResourcesRoles;
import com.ejavashop.entity.system.SystemResources;

/**
 * 商家角色权限
 *                       
 * @Filename: ISellerResourcesRolesService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISellerResourcesRolesService {

    /**
     * 根据id取得角色资源对应表
     * @param  sellerResourcesRolesId
     * @return
     */
    ServiceResult<SellerResourcesRoles> getSellerResourcesRolesById(Integer sellerResourcesRolesId);

    /**
     * 保存角色资源对应表
     * @param  sellerResourcesRoles
     * @return
     */
    ServiceResult<Integer> saveSellerResourcesRoles(SellerResourcesRoles sellerResourcesRoles);

    /**
    * 更新角色资源对应表
    * @param  sellerResourcesRoles
    * @return
    */
    ServiceResult<Integer> updateSellerResourcesRoles(SellerResourcesRoles sellerResourcesRoles);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SellerResourcesRoles>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    /**
     * 保存角色资源关系
     * @param roleId
     * @param resArr
     * @return
     */
    ServiceResult<Boolean> save(String roleId, String[] resArr);

    /**
     * 获取该角色下的所有资源列表
     * @param roleId
     * @return
     */
    ServiceResult<List<SystemResources>> getResourceByRoleId(Integer roleId, Integer scope);

    /**
     * 根据父资源ID、角色ID、使用范围获取资源
     * @param pid
     * @param roleId
     * @param scope 资源使用范围，1商家、2平台
     * @return
     */
    List<SystemResources> getResourceByPid(Integer pid, Integer roleId, Integer scope);
}