package com.ejavashop.model.seller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.seller.SellerRolesReadDao;
import com.ejavashop.dao.shop.write.seller.SellerRolesWriteDao;
import com.ejavashop.entity.seller.SellerRoles;

@Component(value = "sellerRolesModel")
public class SellerRolesModel {

    @Resource
    private SellerRolesWriteDao sellerRolesWriteDao;
    @Resource
    private SellerRolesReadDao  sellerRolesReadDao;

    /**
    * 根据id取得角色表
    * @param  sellerRolesId
    * @return
    */
    public SellerRoles getSellerRolesById(Integer sellerRolesId) {
        return sellerRolesReadDao.get(sellerRolesId);
    }

    /**
     * 保存角色表
     * @param  sellerRoles
     * @return
     */
    public Integer saveSellerRoles(SellerRoles sellerRoles) {
        return sellerRolesWriteDao.insert(sellerRoles);
    }

    /**
    * 更新角色表
    * @param  sellerRoles
    * @return
    */
    public Integer updateSellerRoles(SellerRoles sellerRoles) {
        return sellerRolesWriteDao.update(sellerRoles);
    }

    public Integer getSellerRolesCount(Map<String, String> queryMap) {
        return sellerRolesReadDao.getSellerRolesCount(queryMap);
    }

    public List<SellerRoles> getSellerRoles(Map<String, String> queryMap, Integer start,
                                            Integer size) {
        return sellerRolesReadDao.getSellerRoles(queryMap, start, size);
    }

    public Boolean deleteSellerRole(Integer id) {
        if (id == null)
            throw new BusinessException("删除角色表[" + id + "]时出错");
        return sellerRolesWriteDao.delete(id) > 0;
    }
}
