package com.ejavashop.service.system;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.system.SystemResources;
import com.ejavashop.entity.system.SystemResourcesRoles;

/**
 * 平台角色管理
 *                       
 * @Filename: ISystemResourcesRolesService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISystemResourcesRolesService {

    /**
     * 根据id取得角色资源对应表
     * @param  systemResourcesRolesId
     * @return
     */
    ServiceResult<SystemResourcesRoles> getSystemResourcesRolesById(Integer systemResourcesRolesId);

    /**
     * 保存角色资源对应表
     * @param  systemResourcesRoles
     * @return
     */
    ServiceResult<Integer> saveSystemResourcesRoles(SystemResourcesRoles systemResourcesRoles);

    /**
    * 更新角色资源对应表
    * @param  systemResourcesRoles
    * @return
    */
    ServiceResult<Integer> updateSystemResourcesRoles(SystemResourcesRoles systemResourcesRoles);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SystemResourcesRoles>> page(Map<String, String> queryMap, PagerInfo pager);

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