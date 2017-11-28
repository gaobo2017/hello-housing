package com.ejavashop.model.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.write.product.ProductBrandWriteDao;
import com.ejavashop.dao.shop.write.product.ProductTypeWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerApplyBrandWriteDao;
import com.ejavashop.dao.shop.write.system.SystemAdminWriteDao;
import com.ejavashop.entity.product.ProductBrand;
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.seller.SellerApplyBrand;
import com.ejavashop.entity.system.SystemAdmin;

/**
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
@Component(value = "productBrandModel")
public class ProductBrandModel {

    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;

    @Resource
    private ProductBrandWriteDao         brandWriteDao;
    @Resource
    private SystemAdminWriteDao          systemAdminWriteDao;

    @Resource
    private SellerApplyBrandWriteDao     sellerApplyBrandWriteDao;

    @Resource
    private ProductTypeWriteDao          productTypeWriteDao;

    public Boolean save(ProductBrand brand) {
        //1. business check
        if (null == brand)
            throw new BusinessException("保存商品品牌失败，品牌信息为空");
        if (StringUtil.isEmpty(brand.getName()))
            throw new BusinessException("保存商品品牌失败，品牌名称为空");
        Integer hasName = brandWriteDao.getByName(brand.getName());
        if (hasName > 0)
            throw new BusinessException("保存商品品牌失败，品牌名称已经处在");
        //2. dbConstrains
        this.dbConstrains(brand);
        //3. dbOper
        return brandWriteDao.save(brand) > 0;
    }

    public ProductBrand getById(Integer id) {
        if (null == id || 0 == id)
            throw new BusinessException("无效的id");
        return brandWriteDao.getById(id);
    }

    public List<ProductBrand> getByIds(String ids) {
        if (StringUtil.isEmpty(ids))
            throw new BusinessException("无效的ids");
        return brandWriteDao.getByIds(ids);
    }

    public Boolean update(ProductBrand brand) {
        if (null == brand)
            throw new BusinessException("更新品牌信息失败，品牌信息为空");
        if (null == brand.getId() || 0 == brand.getId())
            throw new BusinessException("更新品牌信息失败，品牌id为空");
        brand.setState(2);

        ProductBrand brandOld = brandWriteDao.getById(brand.getId());
        if (!brandOld.getName().equals(brand.getName())) {
            Integer hasName = brandWriteDao.getByName(brand.getName());
            if (hasName > 0)
                throw new BusinessException("保存商品品牌失败，品牌名称已经处在");
        }
        return brandWriteDao.update(brand) > 0;
    }

    public List<ProductBrand> page(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(brandWriteDao.count(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }

        List<ProductBrand> list = brandWriteDao.page(queryMap, start, size);
        //构造createUser updateUser名字，如果列表页面不显示，以下代码可以去掉
        if (null != list && list.size() > 0) {
            for (ProductBrand brand : list) {
                SystemAdmin admin = systemAdminWriteDao.get(brand.getCreateId());
                if (null != admin && !StringUtil.isEmpty(admin.getName())) {
                    brand.setCreateUser(admin.getName());
                }
                if (null != admin && brand.getCreateId() == brand.getUpdateId()) {
                    brand.setUpdateUser(admin.getName());
                } else {
                    admin = systemAdminWriteDao.get(brand.getUpdateId());
                    if (null != admin && !StringUtil.isEmpty(admin.getName())) {
                        brand.setUpdateUser(admin.getName());
                    }
                }
            }
        }
        return list;
    }

    public List<ProductBrand> getBrandByTypeId(Integer typeId) {
        if (null == typeId)
            throw new BusinessException("根据类型id获取品牌列表失败，类型id为空");
        ProductType type = productTypeWriteDao.get(typeId);
        if (null == type || StringUtil.isEmpty(type.getProductBrandIds())) {
            throw new BusinessException("根据类型id获取品牌列表失败，类型为空");
        }
        List<ProductBrand> list = brandWriteDao.getByIds(type.getProductBrandIds());

        //构造createUser updateUser名字，如果列表页面不显示，以下代码可以去掉
        if (null != list && list.size() > 0) {
            for (ProductBrand brand : list) {
                SystemAdmin admin = systemAdminWriteDao.get(brand.getCreateId());
                if (null != admin && !StringUtil.isEmpty(admin.getName())) {
                    brand.setCreateUser(admin.getName());
                }
                if (null != admin && brand.getCreateId() == brand.getUpdateId()) {
                    brand.setUpdateUser(admin.getName());
                } else {
                    admin = systemAdminWriteDao.get(brand.getUpdateId());
                    if (null != admin && !StringUtil.isEmpty(admin.getName())) {
                        brand.setUpdateUser(admin.getName());
                    }
                }
            }
        }
        return list;
    }

    /**
     * 品牌审核
     * @param id
     * @param state
     * @param userId
     * @return
     */
    public Boolean audit(Integer id, Integer state, Integer userId) {
        boolean result = false;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (null == id || 0 == id)
                throw new BusinessException("无效的id");
            if (!SellerApplyBrand.Status.chkStatus(state))
                throw new BusinessException("无效的审核状态");
            SellerApplyBrand brand = new SellerApplyBrand();
            brand.setId(id);
            brand.setState(state);
            if (sellerApplyBrandWriteDao.update(brand) > 0) {
                //审核成功，拷贝到平台品牌中
                if (SellerApplyBrand.Status.SUCCESS.getValue() == state) {
                    brand = sellerApplyBrandWriteDao.getById(id);
                    ProductBrand productBrand = new ProductBrand();
                    productBrand.setState(state);
                    productBrand.setName(brand.getName());
                    productBrand.setImage(brand.getImage());
                    productBrand.setNameFirst(brand.getNameFirst());
                    productBrand.setLookMethod(0);
                    productBrand.setTop(1);
                    productBrand.setSort(0);
                    productBrand.setCreateId(userId);
                    productBrand.setUpdateId(userId);
                    brandWriteDao.save(productBrand);
                }
                result = true;
            }
            transactionManager.commit(status);
            return result;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    public Boolean del(Integer id) {
        if (null == id || 0 == id)
            throw new BusinessException("删除品牌失败,无效的id");
        List<ProductType> list = productTypeWriteDao.getByBrandId(id);
        if (null != list && list.size() > 0) {
            for (ProductType type : list) {
                String productBrandIds = type.getProductBrandIds();
                if (!StringUtil.isEmpty(productBrandIds)) {
                    String[] split = productBrandIds.split(",");
                    for (String str : split) {
                        if (String.valueOf(id).equals(str))
                            throw new BusinessException("删除品牌失败,该品牌已经和商品类型关联.");
                    }
                }
            }
        }
        ProductBrand brand = new ProductBrand();
        brand.setId(id);
        brand.setState(ProductBrand.Status.DEL.getValue());
        return brandWriteDao.update(brand) > 0;
    }

    private void dbConstrains(ProductBrand brand) {
        brand.setImage(StringUtil.dbSafeString(brand.getImage(), false, 200));
        brand.setName(StringUtil.dbSafeString(brand.getName(), false, 50));
        brand.setNameFirst(StringUtil.dbSafeString(brand.getNameFirst(), false, 200));
    }

    /**
     * 取出所有可用的品牌
     * @return
     */
    public List<ProductBrand> listNoPage() {
        return brandWriteDao.listNoPage();
    }
}
