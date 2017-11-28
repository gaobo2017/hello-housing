package com.ejavashop.model.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.seller.SellerCateReadDao;
import com.ejavashop.dao.shop.write.product.ProductWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerCateWriteDao;
import com.ejavashop.entity.seller.SellerCate;

@Component(value = "sellerCateModel")
public class SellerCateModel {

    @Resource
    private SellerCateWriteDao sellerCateWriteDao;

    @Resource
    private SellerCateReadDao  sellerCateReadDao;

    @Resource
    private ProductWriteDao    productWriteDao;

    public boolean saveSellerCate(SellerCate sellerCate) {
        return sellerCateWriteDao.insert(sellerCate) > 0;
    }

    public boolean updateSellerCate(SellerCate sellerCate) {
        return sellerCateWriteDao.update(sellerCate) > 0;
    }

    public boolean delSellerCate(Integer sellerCateId) {
        //1. business check
        if (null == sellerCateId || 0 == sellerCateId)
            throw new BusinessException("根据id删除商家分类失败，id为空");

        int sellerCount = sellerCateWriteDao.countByPid(sellerCateId);
        if (sellerCount > 0) {
            throw new BusinessException("删除商品分类失败，该分类下还有商品");
        }
        int count = productWriteDao.countBySellerCateId(sellerCateId);
        if (count > 0) {
            throw new BusinessException("删除商品分类失败，该分类下还有商品");
        }

        SellerCate sellerCate = sellerCateWriteDao.get(sellerCateId);
        sellerCate.setStatus(3);
        //2. dbOper
        return sellerCateWriteDao.update(sellerCate) > 0;
    }

    public SellerCate getSellerCateById(Integer sellerCateId) {
        if (null == sellerCateId || 0 == sellerCateId)
            throw new BusinessException("根据id获取商家分类失败，id为空");

        return sellerCateWriteDao.get(sellerCateId);
    }

    public Integer pageSellerCateCount(Map<String, String> queryMap) {
        return sellerCateWriteDao.count(queryMap);
    }

    public List<SellerCate> pageSellerCate(Map<String, String> queryMap, Integer start, Integer size) {
        return sellerCateWriteDao.page(queryMap, start, size);
    }

    public List<SellerCate> getByPid(Integer pid, Integer sellerId) {
        if (null == pid)
            throw new BusinessException("根据pid获取商家分类失败，上级商品分类id为空");
        if (null == sellerId)
            throw new BusinessException("根据pid获取商家分类失败，商家id为空");
        return sellerCateWriteDao.getByPid(pid, sellerId);
    }

    /**
     * 获得所有商家分类（显示状态）
     * @param sellerId
     * @return
     */
    public List<SellerCate> getOnuseSellerCate(Integer sellerId) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        //状态为1：显示
        queryMap.put("q_status", ConstantsEJS.SELLER_QQ_STATE_1);//显示
        queryMap.put("q_sellerId", sellerId);
        //1、取第一级分类
        queryMap.put("q_pid", "0");
        List<SellerCate> beanList = sellerCateReadDao.queryList(queryMap);

        for (SellerCate bean : beanList) {
            //取第二级 
            queryMap.put("q_pid", bean.getId());
            List<SellerCate> cateList2 = sellerCateReadDao.queryList(queryMap);
            for (SellerCate temp : cateList2) {
                //取第三级，最多三级
                queryMap.put("q_pid", temp.getId());
                List<SellerCate> cateList3 = sellerCateReadDao.queryList(queryMap);
                temp.setChilds(cateList3);
            }
            bean.setChilds(cateList2);
        }
        return beanList;
    }
}
