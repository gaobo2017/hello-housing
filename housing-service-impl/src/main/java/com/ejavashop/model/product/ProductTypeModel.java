package com.ejavashop.model.product;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.ejavashop.dao.shop.write.product.ProductCateWriteDao;
import com.ejavashop.dao.shop.write.product.ProductNormWriteDao;
import com.ejavashop.dao.shop.write.product.ProductTypeAttrWriteDao;
import com.ejavashop.dao.shop.write.product.ProductTypeWriteDao;
import com.ejavashop.dao.shop.write.system.SystemAdminWriteDao;
import com.ejavashop.entity.product.ProductBrand;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.product.ProductNorm;
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.entity.system.SystemAdmin;

@Component(value = "productTypeModel")
public class ProductTypeModel {

    @Resource
    private ProductTypeWriteDao          productTypeWriteDao;
    @Resource
    private ProductTypeAttrWriteDao      productTypeAttrWriteDao;
    @Resource
    private SystemAdminWriteDao          systemAdminWriteDao;
    @Resource
    private ProductBrandWriteDao         productBrandWriteDao;
    @Resource
    private ProductNormWriteDao          productNormWriteDao;
    @Resource
    private ProductCateWriteDao          productCateWriteDao;

    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;

    public Boolean saveProductType(ProductType productType) {
        return productTypeWriteDao.insert(productType) > 0;
    }

    public Boolean saveProductType(Map<String, Object> map) throws Exception {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //1.business check
            ProductType type = (ProductType) map.get("type");
            if (null == map || null == type) {
                throw new BusinessException("保存商品类型失败，商品类型为空");
            }
            if (StringUtil.isEmpty(type.getName())) {
                throw new BusinessException("保存商品类型失败，商品类型名称为空");
            }
            if (StringUtil.isEmpty(type.getProductBrandIds())) {
                throw new BusinessException("保存商品类型失败，请选择关联品牌");
            }
            //if (StringUtil.isEmpty(type.getProductNormIds())) {
            //    throw new BusinessException("保存商品类型失败，请选择关联规格");
            //}

            //2.insert type
            dbConstrainsType(type);
            productTypeWriteDao.insert(type);
            //3.insert attr
            ProductTypeAttr attrs[] = (ProductTypeAttr[]) map.get("attr");
            if (null != attrs && attrs.length > 0) {
                for (int i = 0; i < attrs.length; i++) {
                    attrs[i].setProductTypeId(type.getId());
                    dbConstrainsAttr(attrs[i]);
                    productTypeAttrWriteDao.insert(attrs[i]);
                }
            }

            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    public Boolean updateProductType(ProductType productType) {
        return productTypeWriteDao.update(productType) > 0;
    }

    public Boolean updateProductType(Map<String, Object> map) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //1.business check
            ProductType type = (ProductType) map.get("type");
            if (null == map || null == type) {
                throw new BusinessException("更新商品类型失败，商品类型为空");
            }
            if (null == type.getId() || 0 == type.getId()) {
                throw new BusinessException("更新商品类型失败，商品类型id为空");
            }

            //2.update type
            dbConstrainsType(type);
            productTypeWriteDao.update(type);

            //存放要删除的分类属性
            Set<Integer> productTypeAttrIdDel = new HashSet<Integer>();
            //传过来的分类属性ID
            Set<Integer> productTypeAttrIds = new HashSet<Integer>();
            ProductTypeAttr attrs[] = (ProductTypeAttr[]) map.get("attr");
            if (null != attrs && attrs.length > 0) {
                for (int i = 0; i < attrs.length; i++) {
                    if (attrs[i].getId() != null || attrs[i].getId().intValue() == 0) {
                        productTypeAttrIds.add(attrs[i].getId());
                    }
                }
            }

            List<ProductTypeAttr> productTypeAttrs = productTypeAttrWriteDao
                .getByTypeId(type.getId());
            for (ProductTypeAttr productTypeAttr : productTypeAttrs) {
                int number = 0;
                for (Integer integer : productTypeAttrIds) {
                    if (integer.intValue() != productTypeAttr.getId().intValue()) {
                        number++;
                    }
                }
                if (number == productTypeAttrIds.size()) {
                    productTypeAttrIdDel.add(productTypeAttr.getId());
                }
            }

            for (Integer integer : productTypeAttrIdDel) {
                productTypeAttrWriteDao.del(integer);
            }

            //判断插入还是更新
            if (null != attrs && attrs.length > 0) {
                for (int i = 0; i < attrs.length; i++) {
                    attrs[i].setProductTypeId(type.getId());
                    dbConstrainsAttr(attrs[i]);
                    if (attrs[i].getId().intValue() == 0 || attrs[i].getId() == null) {
                        productTypeAttrWriteDao.insert(attrs[i]);
                    } else {
                        productTypeAttrWriteDao.update(attrs[i]);
                    }
                }
            }

            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
        return false;
    }

    public Boolean delProductType(Integer productTypeId) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //1. business check
            if (null == productTypeId || 0 == productTypeId)
                throw new BusinessException("根据id删除商品类型表失败，id为空");
            ProductCate cate = productCateWriteDao.getByTypeId(productTypeId);
            if (null != cate)
                throw new BusinessException("删除商品类型失败,该类型已经有商品分类在使用");
            //2. dbOper
            productTypeWriteDao.del(productTypeId);
            productTypeAttrWriteDao.delByTypeId(productTypeId);
            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    public ProductType getProductTypeById(Integer productTypeId) {
        if (null == productTypeId || 0 == productTypeId)
            throw new BusinessException("根据id获取商品类型表失败，id为空");

        return productTypeWriteDao.get(productTypeId);
    }

    public List<ProductTypeAttr> getAttrByTypeId(Integer productTypeId) {
        if (null == productTypeId || 0 == productTypeId)
            throw new BusinessException("根据商品类型id获取商品类型属性失败，商品类型id为空");

        return productTypeAttrWriteDao.getByTypeId(productTypeId);
    }

    public List<ProductType> pageProductType(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(productTypeWriteDao.count(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        List<ProductType> list = productTypeWriteDao.page(queryMap, start, size);
        //构造createUser updateUser名字，如果列表页面不显示，以下代码可以去掉
        if (null != list && list.size() > 0) {
            for (ProductType type : list) {
                //createUser
                SystemAdmin admin = systemAdminWriteDao.get(type.getCreateId());
                if (null != admin && !StringUtil.isEmpty(admin.getName())) {
                    type.setCreateUser(admin.getName());
                }

                //normName
                String normIds = type.getProductNormIds();
                if (!StringUtil.isEmpty(normIds)) {
                    String[] split = normIds.split(",");
                    String normName = "";
                    for (int i = 0; i < split.length; i++) {
                        if (!StringUtil.isEmpty(split[i])) {
                            ProductNorm norm = productNormWriteDao
                                .getNormById(Integer.valueOf(split[i]));
                            if (null != norm && !StringUtil.isEmpty(norm.getName()))
                                normName += norm.getName() + ",";
                        }
                    }
                    if (!StringUtil.isEmpty(normName) && normName.indexOf(",") != -1) {
                        normName = normName.substring(0, normName.length() - 1);
                        type.setNormNames(normName);
                    }
                }

                //brandName
                String brandIds = type.getProductBrandIds();
                if (!StringUtil.isEmpty(brandIds)) {
                    String[] split = brandIds.split(",");
                    String brandName = "";
                    for (int i = 0; i < split.length; i++) {
                        ProductBrand brand = productBrandWriteDao
                            .getById(Integer.valueOf(split[i]));
                        if (null != brand && !StringUtil.isEmpty(brand.getName()))
                            brandName += brand.getName() + ",";
                    }
                    if (!StringUtil.isEmpty(brandName) && brandName.indexOf(",") != -1) {
                        brandName = brandName.substring(0, brandName.length() - 1);
                        type.setBrandNames(brandName);
                    }
                }
            }
        }
        return list;
    }

    private void dbConstrainsType(ProductType type) {
        type.setName(StringUtil.dbSafeString(type.getName(), false, 50));
        type.setProductNormIds(StringUtil.dbSafeString(type.getProductNormIds(), false, 255));
        type.setProductBrandIds(StringUtil.dbSafeString(type.getProductBrandIds(), false, 255));
    }

    private void dbConstrainsAttr(ProductTypeAttr attr) {
        attr.setName(StringUtil.dbSafeString(attr.getName(), false, 255));
        attr.setValue(StringUtil.dbSafeString(attr.getValue(), false, 255));
    }

}
