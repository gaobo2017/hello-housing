package com.ejavashop.model.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.write.system.SystemRolesWriteDao;
import com.ejavashop.entity.system.SystemRoles;

@Component(value = "systemRolesModel")
public class SystemRolesModel {

    @Resource
    private SystemRolesWriteDao systemRolesWriteDao;

    /**
    * 根据id取得角色表
    * @param  systemRolesId
    * @return
    */
    public SystemRoles getSystemRolesById(Integer systemRolesId) {
        return systemRolesWriteDao.get(systemRolesId);
    }

    /**
     * 保存角色表
     * @param  systemRoles
     * @return
     */
    public Integer saveSystemRoles(SystemRoles systemRoles) {
        return systemRolesWriteDao.save(systemRoles);
    }

    /**
    * 更新角色表
    * @param  systemRoles
    * @return
    */
    public Integer updateSystemRoles(SystemRoles systemRoles) {
        return systemRolesWriteDao.update(systemRoles);
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return systemRolesWriteDao.getCount(queryMap);
    }

    public List<SystemRoles> page(Map<String, String> queryMap, Integer start, Integer size) {
        return systemRolesWriteDao.page(queryMap, start, size);
    }

    public Boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除角色表[" + id + "]时出错");
        return systemRolesWriteDao.del(id) > 0;
    }
}
