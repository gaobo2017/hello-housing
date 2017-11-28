package com.ejavashop.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerResourcesRoles;
import com.ejavashop.entity.system.SystemResources;

@Repository
public interface SellerResourcesRolesReadDao {

    SellerResourcesRoles get(java.lang.Integer id);

    Integer getCount(Map<String, String> queryMap);

    List<SellerResourcesRoles> page(Map<String, String> queryMap);

    /**
     * 此角色下的资源
     * @param roleId
     * @return
     */
    List<SystemResources> getResourceByRoleId(@Param("roleId") Integer roleId,
                                              @Param("scope") Integer scope);

    /**
     * 此资源下的有权限的子资源
     * @param queryMap
     * @return
     */
    List<SystemResources> getResourceByPid(Map<String, Object> queryMap);

}