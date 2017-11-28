package com.ejavashop.model.product;

import java.util.ArrayList;
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
import com.ejavashop.dao.shop.write.product.ProductNormWriteDao;
import com.ejavashop.dao.shop.write.product.ProductTypeWriteDao;
import com.ejavashop.dao.shop.write.product.ProductWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerWriteDao;
import com.ejavashop.dao.shop.write.system.SystemAdminWriteDao;
import com.ejavashop.entity.product.ProductNorm;
import com.ejavashop.entity.product.ProductNormAttr;
import com.ejavashop.entity.product.ProductNormAttrOpt;
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.system.SystemAdmin;

/**
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
@Component(value = "productNormModel")
public class ProductNormModel {
    @Resource
    private ProductNormWriteDao          productNormWriteDao;
    @Resource
    private SellerWriteDao               sellerWriteDao;
    @Resource
    private ProductWriteDao              productWriteDao;
    @Resource
    private SystemAdminWriteDao          systemAdminWriteDao;
    @Resource
    private ProductTypeWriteDao          productTypeWriteDao;

    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;

    public Boolean saveNorm(ProductNorm norm) {
        //1. business check
        if (null == norm)
            throw new BusinessException("保存商品规格失败，商品规格为空");
        if (!ProductNorm.Status.chkStatus(norm.getState()))
            throw new BusinessException("保存商品规格失败，商品规格状态无效");
        if (StringUtil.isEmpty(norm.getName()))
            throw new BusinessException("保存商品规格失败，商品规格名称位空");

        //2. dbConstrains
        this.dbConstrainsNorm(norm);

        //3. dbOper
        return productNormWriteDao.insertNorm(norm) > 0;
    }

    public Boolean saveNorm(Map<String, Object> map) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //1. business check
            if (null == map || null == map.get("norm") || null == map.get("attr"))
                throw new BusinessException("保存商品规格失败，商品规格为空");

            ProductNorm norm = (ProductNorm) map.get("norm");
            ProductNormAttr attrs[] = (ProductNormAttr[]) map.get("attr");

            if (!ProductNorm.Status.chkStatus(norm.getState()))
                throw new BusinessException("保存商品规格失败，商品规格状态无效");
            if (StringUtil.isEmpty(norm.getName()))
                throw new BusinessException("保存商品规格失败，商品规格名称位空");

            //2. dbConstrains
            this.dbConstrainsNorm(norm);

            //3. dbOper
            productNormWriteDao.insertNorm(norm);
            for (ProductNormAttr attr : attrs) {
                attr.setProductNormId(norm.getId());
                this.dbConstrainsAttr(attr);
                //3. dbOper
                productNormWriteDao.insertNormAttr(attr);
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

    public ProductNorm getNormById(Integer id) {
        if (null == id || 0 == id)
            throw new BusinessException("获取商品规格失败，无效的id");
        ProductNorm productNorm = productNormWriteDao.getNormById(id);
        if (null != productNorm && null != productNorm.getId()) {
            List<ProductNormAttr> attrs = productNormWriteDao.getAttrByNormId(productNorm.getId());
            productNorm.setAttrList(attrs);
        }
        return productNorm;
    }

    public List<ProductNorm> getNormByIds(String ids) {
        if (StringUtil.isEmpty(ids))
            throw new BusinessException("获取商品规格失败，无效的ids");
        return productNormWriteDao.getNormByIds(ids);
    }

    public List<ProductNorm> pageNorm(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(productNormWriteDao.countNorm(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        List<ProductNorm> list = productNormWriteDao.getNormByPage(queryMap, start, size);
        //构造createUser updateUser名字，如果列表页面不显示，以下代码可以去掉
        if (null != list && list.size() > 0) {
            for (ProductNorm norm : list) {
                SystemAdmin admin = systemAdminWriteDao.get(norm.getCreateId());
                if (null != admin && !StringUtil.isEmpty(admin.getName())) {
                    norm.setCreateUser(admin.getName());
                }
                if (null != admin && norm.getCreateId() == norm.getUpdateId()) {
                    norm.setUpdateUser(admin.getName());
                } else {
                    admin = systemAdminWriteDao.get(norm.getUpdateId());
                    if (null != admin && !StringUtil.isEmpty(admin.getName())) {
                        norm.setCreateUser(admin.getName());
                    }
                }
            }
        }
        return list;
    }

    public Boolean updateNorm(ProductNorm norm) {
        //1. business check
        if (null == norm)
            throw new BusinessException("更新商品规格失败，商品规格为空");
        if (null == norm.getId() || 0 == norm.getId())
            throw new BusinessException("更新商品规格失败，商品规格id为空");
        //2. dbConstrains
        this.dbConstrainsNorm(norm);
        //3. dbOper
        return productNormWriteDao.updateNorm(norm) > 0;
    }

    public Boolean updateNorm(Map<String, Object> map) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //1. business check
            if (null == map)
                throw new BusinessException("更新商品规格失败，商品规格基本信息为空");
            if (null == map.get("norm") || null == map.get("attr"))
                throw new BusinessException("更新商品规格失败，商品规格值为空");
            ProductNorm norm = (ProductNorm) map.get("norm");
            if (null == norm.getId() || 0 == norm.getId())
                throw new BusinessException("更新商品规格失败，商品规格id为空");

            //商品类型验证:如果该规格已经在商品类型中使用,则不能编辑
            //List<ProductType> list = productTypeWriteDao.getByNormId(norm.getId());
            //if (null != list && list.size() > 0)
            //    throw new BusinessException("该商品规格已经在商品类型中使用,不可进行编辑");
            //2. dbConstrains
            this.dbConstrainsNorm(norm);

            List<ProductNormAttr> attrOlds = productNormWriteDao.getAttrByNormId(norm.getId());

            //3. dbOper
            //3.1 del attr
            //productNormWriteDao.delAttrByNormId(norm.getId());
            //3.2 update norm
            productNormWriteDao.updateNorm(norm);

            List<Integer> attrNewIds = new ArrayList<Integer>();
            //3.3 insert attr
            ProductNormAttr[] attrs = (ProductNormAttr[]) map.get("attr");
            if (null != attrs && attrs.length > 0) {
                for (int i = 0; i < attrs.length; i++) {
                    Integer attrId = attrs[i].getId();
                    if (null != attrId) {
                        ProductNormAttr attr = productNormWriteDao.getNormAttrById(attrId);
                        if (null != attr) {
                            //update
                            productNormWriteDao.updateNormAttr(attrs[i]);
                            attrNewIds.add(attrId);
                        }
                    } else {
                        //insert
                        attrs[i].setProductNormId(norm.getId());
                        this.dbConstrainsAttr(attrs[i]);
                        productNormWriteDao.insertNormAttr(attrs[i]);
                    }
                }
            }

            List<Integer> attrDelIds = new ArrayList<Integer>();//需要删除的规格属性
            if (attrNewIds.size() != attrOlds.size()) { //删除已经删除的规格属性
                for (ProductNormAttr productNormAttr : attrOlds) {
                    Integer attrId = productNormAttr.getId();
                    int count = 0;
                    for (Integer attrNewId : attrNewIds) {
                        if (attrId.intValue() != attrNewId.intValue()) {
                            count++;
                        }
                    }

                    if (count == attrNewIds.size()) {
                        attrDelIds.add(attrId);
                    }
                }
            }

            for (Integer id : attrDelIds) {
                productNormWriteDao.delAttr(id);
            }

            //4. commit
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

    /**
     * 根据规格id查询商品类型,检查规格是否被占用
     * @param id
     * @return
     */
    public List<ProductType> chkHasUsed(Integer id) {
        List<ProductType> list = productTypeWriteDao.getByNormId(id);
        //if (null != list && list.size() > 0)
        //    throw new BusinessException("该商品规格已经在商品类型中使用,不可进行编辑");
        return list;
    }

    public Boolean delNorm(Integer id) {
        //1. business check
        if (null == id)
            throw new BusinessException("删除商品规格失败，商品规格id为空");
        List<ProductType> list = productTypeWriteDao.getByNormId(id);
        if (null != list && list.size() > 0) {
            for (ProductType type : list) {
                String productNormIds = type.getProductNormIds();
                if (!StringUtil.isEmpty(productNormIds)) {
                    String[] split = productNormIds.split(",");
                    for (String str : split) {
                        if (String.valueOf(id).equals(str))
                            throw new BusinessException("删除商品规格失败，该商品规格已经和商品类型关联.");
                    }
                }
            }
        }
        //2. dbOper
        ProductNorm norm = productNormWriteDao.getNormById(id);
        norm.setState(ProductNorm.Status.DEL.getValue());
        return productNormWriteDao.updateNorm(norm) > 0;
    }

    public Boolean saveNormAttr(ProductNormAttr attr) {
        //1. business check
        if (null == attr)
            throw new BusinessException("保存商品规格属性失败，商品规格属性为空");
        if (StringUtil.isEmpty(attr.getName()))
            throw new BusinessException("保存商品规格属性失败，商品规格属性名称为空");
        if (null == attr.getProductNormId() || 0 == attr.getProductNormId())
            throw new BusinessException("保存商品规格属性失败，商品规格id为空");

        ProductNorm norm = productNormWriteDao.getNormById(attr.getProductNormId());
        if (null == norm)
            throw new BusinessException("保存商品规格属性失败，所选商品规格为空");

        //2. dbConstrains
        this.dbConstrainsAttr(attr);
        //3. dbOper
        return productNormWriteDao.insertNormAttr(attr) > 0;
    }

    public ProductNormAttr getNormAttrById(Integer id) {
        if (null == id || 0 == id)
            throw new BusinessException("获取商品规格属性失败，无效的id");
        return productNormWriteDao.getNormAttrById(id);
    }

    public List<ProductNormAttr> getAttrByNormId(Integer id) {
        if (null == id || 0 == id)
            throw new BusinessException("获取商品规格属性失败，无效的商品规格id");
        return productNormWriteDao.getAttrByNormId(id);
    }

    public List<ProductNormAttr> getAttrByNormIds(String ids) {
        if (StringUtil.isEmpty(ids))
            throw new BusinessException("获取商品规格属性失败，无效的商品规格id");
        return productNormWriteDao.getAttrByNormIds(ids);
    }

    public List<ProductNormAttr> pageNormAttr(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(productNormWriteDao.countNormAttr(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        return productNormWriteDao.getNormAttrByPage(queryMap, start, size);
    }

    public Boolean updateNormAttr(ProductNormAttr attr) {
        //1. business check
        if (null == attr)
            throw new BusinessException("更新商品规格属性失败，商品规格属性为空");
        if (null == attr.getId() || 0 == attr.getId())
            throw new BusinessException("更新商品规格属性失败，商品规格属性id为空");

        //2. dbConstrains
        this.dbConstrainsAttr(attr);
        //3. dbOper
        return productNormWriteDao.updateNormAttr(attr) > 0;
    }

    public Boolean delNormAttr(Integer id) {
        //1. business check
        if (null == id)
            throw new BusinessException("删除商品规格属性失败，商品规格属性id为空");

        //2. dbOper
        return productNormWriteDao.delAttr(id) > 0;
    }

    public Boolean saveNormAttrOpt(ProductNormAttrOpt opt) {
        //1. business check
        if (null == opt)
            throw new BusinessException("保存商品选定的规格属性失败，商品选定的规格属性为空");
        if (null == opt.getProductNormId() || 0 == opt.getProductNormId())
            throw new BusinessException("保存商品选定的规格属性失败，商品规格属性id为空");
        if (null == opt.getProductId() || 0 == opt.getProductId())
            throw new BusinessException("保存商品选定的规格属性失败，商品id为空");
        if (null == opt.getSellerId() || 0 == opt.getSellerId())
            throw new BusinessException("保存商品选定的规格属性失败，商家id为空");
        if (null == productWriteDao.get(opt.getProductId()))
            throw new BusinessException("保存商品选定的规格属性失败，商品为空");
        if (null == sellerWriteDao.get(opt.getSellerId()))
            throw new BusinessException("保存商品选定的规格属性失败，商家为空");

        //2. dbConstrains
        this.dbConstrainsOpt(opt);
        //3. dbOper
        return productNormWriteDao.insertNormAttrOpt(opt) > 0;
    }

    public Boolean updateNormAttrOpt(ProductNormAttrOpt opt) {
        //1. business check
        if (null == opt)
            throw new BusinessException("更新商品选定的规格属性失败，商品选定的规格属性为空");
        if (null == opt.getId() || 0 == opt.getId())
            throw new BusinessException("更新商品选定的规格属性失败，商品选定的规格属性id为空");
        //2. dbConstrains
        this.dbConstrainsOpt(opt);
        //3. dbOper
        return productNormWriteDao.updateNormAttrOpt(opt) > 0;
    }

    public Boolean delNormAttrOpt(Integer id) {
        //1. business check
        if (null == id)
            throw new BusinessException("删除商品选定的规格属性失败，id为空");
        //2. dbOper
        return productNormWriteDao.delOpt(id) > 0;
    }

    public List<ProductNormAttrOpt> pageNormAttrOpt(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(productNormWriteDao.countNormAttrOpt(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        return productNormWriteDao.getNormAttrOptByPage(queryMap, start, size);
    }

    public ProductNormAttrOpt getNormAttrOptById(Integer id) {
        if (null == id || 0 == id)
            throw new BusinessException("获取商品规格属性失败，无效的id");
        return productNormWriteDao.getNormAttrOptById(id);
    }

    private void dbConstrainsNorm(ProductNorm norm) {
        norm.setName(StringUtil.dbSafeString(norm.getName(), false, 50));
    }

    private void dbConstrainsAttr(ProductNormAttr attr) {
        attr.setName(StringUtil.dbSafeString(attr.getName(), false, 50));
        attr.setImage(StringUtil.dbSafeString(attr.getImage(), false, 200));
    }

    private void dbConstrainsOpt(ProductNormAttrOpt opt) {
        opt.setName(StringUtil.dbSafeString(opt.getName(), false, 50));
        opt.setImage(StringUtil.dbSafeString(opt.getImage(), false, 200));
    }

    /**
     * 查询所有的规格
     * @return
     */
    public List<ProductNorm> listNoPage() {
        return productNormWriteDao.listNoPage();
    }
}
