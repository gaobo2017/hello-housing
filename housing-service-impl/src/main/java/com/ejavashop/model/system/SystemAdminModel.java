package com.ejavashop.model.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.system.SystemAdminReadDao;
import com.ejavashop.dao.shop.write.system.SystemAdminWriteDao;
import com.ejavashop.dao.shop.write.system.SystemRolesWriteDao;
import com.ejavashop.entity.system.SystemAdmin;

@Component(value = "systemAdminModel")
public class SystemAdminModel {

    @Resource
    private SystemAdminReadDao  systemAdminReadDao;
    @Resource
    private SystemAdminWriteDao systemAdminWriteDao;
    @Resource
    private SystemRolesWriteDao systemRolesWriteDao;

    /**
    * 根据id取得系统管理员表
    * @param  systemAdminId
    * @return
    */
    public SystemAdmin getSystemAdminById(Integer systemAdminId) {
        return systemAdminWriteDao.get(systemAdminId);
    }

    /**
     * 保存系统管理员表
     * @param  systemAdmin
     * @return
     */
    public Integer saveSystemAdmin(SystemAdmin systemAdmin) {
        return systemAdminWriteDao.save(systemAdmin);
    }

    /**
    * 更新系统管理员表
    * @param  systemAdmin
    * @return
    */

    public Integer updateSystemAdmin(SystemAdmin systemAdmin) {
        return systemAdminWriteDao.update(systemAdmin);
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return systemAdminWriteDao.getCount(queryMap);
    }

    public List<SystemAdmin> page(Map<String, String> queryMap, Integer start, Integer size) {
        List<SystemAdmin> list = systemAdminWriteDao.page(queryMap, start, size);

        for (SystemAdmin admin : list) {
            admin.setRoleName(systemRolesWriteDao.get(admin.getRoleId()).getRolesName());
        }

        return list;
    }

    public Boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除系统管理员表[" + id + "]时出错");
        return this.systemAdminWriteDao.del(id) > 0;
    }

    public SystemAdmin getSystemAdminByNamePwd(String name, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("password", password);
        return systemAdminWriteDao.getByNamePwd(map);
    }

    /**
     * 根据用户名取用户
     * @param name
     * @return
     */
    public List<SystemAdmin> getSystemAdminByName(String name) {
        return systemAdminReadDao.getByName(name);
    }

}
