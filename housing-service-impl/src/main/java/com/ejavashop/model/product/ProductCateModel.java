package com.ejavashop.model.product;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.product.ProductCateReadDao;
import com.ejavashop.dao.shop.write.product.ProductCateWriteDao;
import com.ejavashop.dao.shop.write.product.ProductTypeWriteDao;
import com.ejavashop.dao.shop.write.product.ProductWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerManageCateWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerTypeLogsWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerWriteDao;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.seller.SellerManageCate;
import com.ejavashop.entity.seller.SellerTypeLogs;
import com.ejavashop.vo.product.FrontProductCateVO;
import com.ejavashop.vo.product.ProductCateVO;

import net.sf.cglib.beans.BeanCopier;

@Component(value = "productCateModel")
public class ProductCateModel {

    @Resource
    private ProductCateWriteDao          productCateWriteDao;
    @Resource
    private SellerManageCateWriteDao     sellerManageCateWriteDao;
    @Resource
    private SellerWriteDao               sellerWriteDao;
    @Resource
    private SellerTypeLogsWriteDao       sellerTypeLogsWriteDao;
    @Resource
    private ProductCateReadDao           productCateReadDao;
    @Resource
    private ProductTypeWriteDao          productTypeWriteDao;
    @Resource
    private ProductWriteDao              productWriteDao;

    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;

    public Boolean saveProductCate(ProductCate productCate) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            productCateWriteDao.insert(productCate);
            //记录日志
            SellerTypeLogs logs = new SellerTypeLogs();
            logs.setCreateId(productCate.getCreateId());
            logs.setCreateName(productCate.getCreater());
            logs.setProductCateId(productCate.getId());
            StringBuffer content = new StringBuffer();
            content.append("保存商品分类，佣金:" + productCate.getScaling());
            logs.setContent(content.toString());
            sellerTypeLogsWriteDao.insert(logs);
            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
        return false;
    }

    public Boolean updateProductCate(ProductCate productCate) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            productCateWriteDao.update(productCate);
            //记录日志
            SellerTypeLogs logs = new SellerTypeLogs();
            logs.setProductCateId(productCate.getId());
            logs.setCreateId(productCate.getUpdateId());
            logs.setCreateName(productCate.getUpdater());
            StringBuffer content = new StringBuffer();
            content.append("修改商品分类，佣金:" + productCate.getScaling());
            logs.setContent(content.toString());
            sellerTypeLogsWriteDao.insert(logs);
            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
        return false;
    }

    public Boolean delProductCate(Integer productCateId) {
        //1. business check
        if (null == productCateId || 0 == productCateId)
            throw new BusinessException("删除商品分类失败，id为空");
        List<ProductCate> list = productCateWriteDao.getByPid(productCateId);
        if (null != list && list.size() > 0) {
            throw new BusinessException("删除商品分类失败，该分类下还有分类");
        }
        int count = productWriteDao.countByCateId(productCateId);
        if (count > 0) {
            throw new BusinessException("删除商品分类失败，该分类下还有商品");
        }
        ProductCate productCate = productCateWriteDao.get(productCateId);
        productCate.setStatus(3);
        return productCateWriteDao.update(productCate) > 0;
    }

    public ProductCate getProductCateById(Integer productCateId) {
        if (null == productCateId)
            throw new BusinessException("根据id获取商品分类失败，id为空");

        return productCateWriteDao.get(productCateId);
    }

    public List<ProductCateVO> pageProductCate(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(productCateWriteDao.count(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        Map<String, Object> map = new HashMap<String, Object>(queryMap);
        map.put("start", start);
        map.put("size", size);

        List<ProductCateVO> volist = productCateWriteDao.page(map);
        for (ProductCateVO vo : volist) {
            Seller seller = sellerWriteDao.get(Integer.valueOf(vo.getSeller()));
            vo.setSeller(seller.getName());
            vo.setSellerName(seller.getSellerName());
            vo.setPathName(assemblePath(vo));
        }

        return volist;
    }

    /**
     * 组装分类路径
     * @param vo
     * @return
     */
    private String assemblePath(ProductCateVO vo) {
        String[] data = vo.getPath().split("/");
        switch (data.length) {
            case 0:
                return vo.getName();
            case 2:
                return productCateWriteDao.get(Integer.valueOf(data[1])).getName() + "/"
                       + vo.getName();
            case 3:
                ProductCate parent = productCateWriteDao.get(Integer.valueOf(data[2]));
                ProductCate root = productCateWriteDao.get(Integer.valueOf(data[1]));
                return root.getName() + "/" + parent.getName() + "/" + vo.getName();
            default:
                break;
        }
        return null;
    }

    public List<ProductCate> getByPid(Integer pid) {
        if (null == pid)
            throw new BusinessException("根据父id获取商品分类失败，父id为空");
        List<ProductCate> list = productCateWriteDao.getByPid(pid);
        for (ProductCate cate : list) {
            ProductType productType = productTypeWriteDao.get(cate.getProductTypeId());
            if (null != productType && !StringUtil.isEmpty(productType.getName())) {
                cate.setTypeName(productType.getName());
            }
        }
        return list;
    }

    public List<ProductCate> getCateBySellerId(Integer sellerId) {
        if (null == sellerId)
            throw new BusinessException("根据商家id获取商品分类失败，商家分类id为空");
        /**根据商家id查询出该商家申请成功的所有的商品分类信息*/
        List<ProductCate> cateList = productCateWriteDao.getBySellerId(sellerId);
        if (null != cateList && cateList.size() > 0) {
            Set<String> cateIdSet = new HashSet<String>();

            /**根据path构造一级分类id*/
            for (ProductCate cate : cateList) {
                String path = cate.getPath();
                String[] cateIds = path.split("/");
                if (null != cateIds && cateIds.length > 2) {
                    String bigCateStr = cateIds[1];//一级分类信息
                    cateIdSet.add(bigCateStr);
                }
            }
            List<ProductCate> list = new ArrayList<ProductCate>(cateIdSet.size());

            /**根据一级分类id查询分类信息，放到list中*/
            if (null != cateIdSet && cateIdSet.size() > 0) {
                for (String cateId : cateIdSet) {
                    ProductCate cate = productCateWriteDao.get(Integer.valueOf(cateId));
                    if (null != cate) {
                        list.add(cate);
                    }
                }
            }

            /**设置返回*/
            return list;
        } else {
            return new ArrayList<ProductCate>(0);
        }
    }

    public List<ProductCate> getCateBySellerId(Integer sellerId, Integer pid) {
        if (null == sellerId)
            throw new BusinessException("根据商家id和商品分类id获取商品分类信息失败，商家id为空");
        if (null == pid)
            throw new BusinessException("根据商家id和商品分类id获取商品分类信息失败，商品分类id为空");
        /**根据商家id查询出该商家申请成功的所有的商品分类信息*/
        List<ProductCate> cateList = productCateWriteDao.getBySellerId(sellerId);
        if (null != cateList && cateList.size() > 0) {
            Set<String> cateIdSet = new HashSet<String>();
            List<ProductCate> list = new ArrayList<ProductCate>(cateIdSet.size());
            /**根据path构造pid下一级分类id*/
            for (ProductCate cate : cateList) {
                String path = cate.getPath();//path规则:一级分类 / ;二级分类 /1 ;三级分类 /1/2
                String[] split = path.split("/");
                if (null != split && split.length == 3
                    && Integer.valueOf(split[1]).intValue() == pid) {
                    String[] cateIds = path.split("/");
                    if (cateIds != null && cateIds.length > 2) {
                        String secondCateId = cateIds[2];//二级分类id
                        cateIdSet.add(secondCateId);
                    }
                } else if (null != split && split.length == 3
                           && Integer.valueOf(split[2]).intValue() == pid) {
                    list.add(cate);//三级分类
                }
            }

            /**根据二级分类id查询分类信息，放到list中*/
            if (null != cateIdSet && cateIdSet.size() > 0) {
                for (String cateId : cateIdSet) {
                    ProductCate cate = productCateWriteDao.get(Integer.valueOf(cateId));
                    if (null != cate) {
                        list.add(cate);
                    }
                }
            }
            Collections.reverse(list);
            /**设置返回*/
            return list;
        } else {
            return new ArrayList<ProductCate>(0);
        }
    }

    public String getCatePathStrById(Integer productCateId) {
        if (null == productCateId)
            throw new BusinessException("获取商品分类路径失败，商品分类id为空");
        ProductCate productCate = productCateWriteDao.get(productCateId);
        if (null == productCate)
            throw new BusinessException("获取商品分类路径失败，该商品分类不存在");
        String pathStr = "";
        String path = productCate.getPath();
        String[] split = path.split("/");
        for (int i = 0; i < split.length; i++) {
            if (StringUtil.isEmpty(split[i]))
                continue;
            ProductCate cate = productCateWriteDao.get(Integer.valueOf(split[i]));
            if (null != cate)
                pathStr += cate.getName() + " > ";
        }

        if (!StringUtil.isEmpty(pathStr)) {
            pathStr += productCate.getName();
        }
        return pathStr;
    }

    public SellerManageCate getSellerManageCate(Integer id) {
        return sellerManageCateWriteDao.get(id);
    }

    public Boolean updateSellerManageCate(SellerManageCate cate) {
        return sellerManageCateWriteDao.update(cate) > 0;
    }

    public List<ProductCateVO> getByPidAndSeller(Integer pid,
                                                 Integer seller) throws IllegalAccessException,
                                                                 InvocationTargetException,
                                                                 NoSuchMethodException {
        if (null == pid)
            throw new BusinessException("根据父id获取商品分类失败，父id为空");

        List<ProductCate> list = productCateWriteDao.getByPid(pid);
        List<ProductCateVO> volist = new ArrayList<ProductCateVO>();

        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("q_seller", seller.toString());
        map1.put("q_state", "2");
        List<SellerManageCate> sellerCatelist = sellerManageCateWriteDao.page(map1, -1, -1);

        for (ProductCate cate : list) {
            ProductCateVO vo = new ProductCateVO();
            PropertyUtils.copyProperties(vo, cate);

            for (SellerManageCate sc : sellerCatelist) {
                if (sc.getProductCateId() == vo.getId()) {
                    vo.setChecked("true");
                }
            }

            volist.add(vo);
        }

        return volist;
    }

    /**
     * 取得所有显示状态的商品分类
     * @param  queryMap
     * @return
     */
    public List<FrontProductCateVO> getProductCateList(Map<String, Object> queryMap) {
        List<FrontProductCateVO> returnList = new ArrayList<FrontProductCateVO>();

        //状态为1：显示
        queryMap.put("status", ConstantsEJS.PRODUCT_CATE_STATUS_1);

        //1、取第一级分类
        queryMap.put("pid", "0");
        List<ProductCate> beanList = productCateReadDao.queryList(queryMap);
        //取第二级分类
        for (ProductCate bean : beanList) {
            FrontProductCateVO vo = new FrontProductCateVO();
            final BeanCopier copier = BeanCopier.create(ProductCate.class, FrontProductCateVO.class,
                false);
            copier.copy(bean, vo, null);

            //取第二级 
            queryMap.put("pid", bean.getId());
            List<ProductCate> beanListLevel2 = productCateReadDao.queryList(queryMap);
            List<FrontProductCateVO> cateList2 = new ArrayList<FrontProductCateVO>();
            for (ProductCate temp : beanListLevel2) {
                FrontProductCateVO vo2 = new FrontProductCateVO();
                copier.copy(temp, vo2, null);

                //取第三级
                queryMap.put("pid", temp.getId());
                List<ProductCate> beanListLevel3 = productCateReadDao.queryList(queryMap);
                List<FrontProductCateVO> cateList3 = new ArrayList<FrontProductCateVO>();
                for (ProductCate cate : beanListLevel3) {
                    FrontProductCateVO vo3 = new FrontProductCateVO();
                    copier.copy(cate, vo3, null);
                    cateList3.add(vo3);
                }

                vo2.setChilds(cateList3);
                cateList2.add(vo2);
            }

            vo.setChilds(cateList2);
            returnList.add(vo);
        }

        return returnList;
    }
}
