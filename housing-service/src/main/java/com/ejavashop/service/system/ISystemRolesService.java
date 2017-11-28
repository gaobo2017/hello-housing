package com.ejavashop.service.system;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.system.SystemRoles;

/**
 * 平台角色管理
 *                       
 * @Filename: ISystemRolesService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISystemRolesService {

    /**
     * 根据id取得角色表
     * @param  systemRolesId
     * @return
     */
    ServiceResult<SystemRoles> getSystemRolesById(Integer systemRolesId);

    /**
     * 保存角色表
     * @param  systemRoles
     * @return
     */
    ServiceResult<Integer> saveSystemRoles(SystemRoles systemRoles);

    /**
    * 更新角色表
    * @param  systemRoles
    * @return
    */
    ServiceResult<Integer> updateSystemRoles(SystemRoles systemRoles);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SystemRoles>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);
}