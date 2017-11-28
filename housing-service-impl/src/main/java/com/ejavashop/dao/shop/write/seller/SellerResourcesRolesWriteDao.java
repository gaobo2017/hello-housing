package com.ejavashop.dao.shop.write.seller;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerResourcesRoles;

@Repository
public interface SellerResourcesRolesWriteDao {

    SellerResourcesRoles get(java.lang.Integer id);

    Integer insert(SellerResourcesRoles sellerResourcesRoles);

    Integer update(SellerResourcesRoles sellerResourcesRoles);

    //    Integer getCount(Map<String, String> queryMap);
    //
    //    List<SellerResourcesRoles> page(Map<String, String> queryMap);

    Integer del(Integer id);

    //    /**
    //     * 此角色下的资源
    //     * @param roleId
    //     * @return
    //     */
    //    List<SystemResources> getResourceByRoleId(@Param("roleId") Integer roleId,
    //                                              @Param("scope") Integer scope);
    //
    //    /**
    //     * 此资源下的有权限的子资源
    //     * @param queryMap
    //     * @return
    //     */
    //    List<SystemResources> getResourceByPid(Map<String, Object> queryMap);

    /**
     * 删除该角色下的资源关联
     * @param roleId
     * @return
     */
    Integer delByRole(String roleId);
}