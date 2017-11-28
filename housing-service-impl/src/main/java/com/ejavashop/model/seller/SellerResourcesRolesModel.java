package com.ejavashop.model.seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.seller.SellerResourcesRolesReadDao;
import com.ejavashop.dao.shop.write.seller.SellerResourcesRolesWriteDao;
import com.ejavashop.entity.seller.SellerResourcesRoles;
import com.ejavashop.entity.system.SystemResources;

@Component(value = "sellerResourcesRolesModel")
public class SellerResourcesRolesModel {

    @Resource
    private SellerResourcesRolesWriteDao sellerResourcesRolesWriteDao;
    @Resource
    private SellerResourcesRolesReadDao  sellerResourcesRolesReadDao;
    @Resource
    private DataSourceTransactionManager transactionManager;

    /**
    * 根据id取得角色资源对应表
    * @param  sellerResourcesRolesId
    * @return
    */
    public SellerResourcesRoles getSellerResourcesRolesById(Integer sellerResourcesRolesId) {
        return sellerResourcesRolesReadDao.get(sellerResourcesRolesId);
    }

    /**
     * 保存角色资源对应表
     * @param  sellerResourcesRoles
     * @return
     */
    public Integer saveSellerResourcesRoles(SellerResourcesRoles sellerResourcesRoles) {
        return sellerResourcesRolesWriteDao.insert(sellerResourcesRoles);
    }

    /**
    * 更新角色资源对应表
    * @param  sellerResourcesRoles
    * @return
    */
    public Integer updateSellerResourcesRoles(SellerResourcesRoles sellerResourcesRoles) {
        return sellerResourcesRolesWriteDao.update(sellerResourcesRoles);
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return sellerResourcesRolesReadDao.getCount(queryMap);
    }

    public List<SellerResourcesRoles> page(Map<String, String> queryMap) {
        return sellerResourcesRolesReadDao.page(queryMap);
    }

    public boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除角色资源对应表[" + id + "]时出错");
        return sellerResourcesRolesWriteDao.del(id) > 0;
    }

    public boolean save(String roleId, String[] resArr) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //删除此角色之前的资源关联
            sellerResourcesRolesWriteDao.delByRole(roleId);

            //保存选中的资源
            for (String resId : resArr) {
                SellerResourcesRoles srr = new SellerResourcesRoles();
                srr.setResourcesId(Integer.valueOf(resId));
                srr.setSellerRolesId(Integer.valueOf(roleId));
                sellerResourcesRolesWriteDao.insert(srr);
            }

            transactionManager.commit(status);
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(status);
            throw e;
        }
        return true;
    }

    public List<SystemResources> getResourceByRoleId(Integer roleId, Integer scope) {
        if (roleId == null)
            throw new BusinessException("未指定角色");
        return sellerResourcesRolesReadDao.getResourceByRoleId(roleId, scope);
    }

    /**
     * <b>公用方法</b><br>
     * 此资源下所有有权限的子资源
     * @param pid
     * @param roleId
     * @param scope 资源使用范围
     * @return
     * @throws BusinessException
     */
    public List<SystemResources> getResourceByPid(Integer pid, Integer roleId,
                                                  Integer scope) throws BusinessException {
        if (pid == null)
            throw new BusinessException("未指定父资源");
        if (roleId == null)
            throw new BusinessException("未指定角色");

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pid", pid);
        map.put("roleId", roleId);
        map.put("scope", scope);
        return sellerResourcesRolesReadDao.getResourceByPid(map);
    }
}
