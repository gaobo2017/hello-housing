package com.ejavashop.model.product;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.write.product.ProductCateWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerManageCateWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerWriteDao;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.seller.SellerManageCate;

@Component(value = "sellerManageCateModel")
public class SellerManageCateModel {

    @Resource
    private SellerManageCateWriteDao sellerManageCateWriteDao;
    @Resource
    private ProductCateWriteDao      productCateWriteDao;
    @Resource
    private SellerWriteDao           sellerWriteDao;

    public boolean saveSellerManageCate(SellerManageCate sellerManageCate) {
        ProductCate cate = productCateWriteDao.get(sellerManageCate.getProductCateId());
        if (null == cate || StringUtil.isEmpty(cate.getName()))
            throw new BusinessException("提交分类失败，平台商品分类无效");
        SellerManageCate manageCate = sellerManageCateWriteDao
            .getCateBySellerId(sellerManageCate.getProductCateId(), sellerManageCate.getSeller());//TODO 后续需要对状态检查，根据业务
        if (null != manageCate && manageCate.getState() == 1)
            throw new BusinessException("提交分类失败，该商品分类已经申请，请耐心等待审核。");
        if (null != manageCate && manageCate.getState() == 2)
            throw new BusinessException("提交分类失败，该商品分类已经申请通过，无需重复申请。");
        if (null != manageCate && manageCate.getState() == 3)
            throw new BusinessException("提交分类失败，该商品分类已经审核失败。");
        sellerManageCate.setProductCateName(cate.getName());
        return sellerManageCateWriteDao.insert(sellerManageCate) > 0;
    }

    public boolean updateSellerManageCate(SellerManageCate sellerManageCate) {
        return sellerManageCateWriteDao.update(sellerManageCate) > 0;
    }

    public boolean delSellerManageCate(Integer sellerManageCateId) {
        //1. business check
        if (null == sellerManageCateId || 0 == sellerManageCateId)
            throw new BusinessException("根据id删除商家可以经营商品分类表失败，id为空");
        //2. dbOper
        return sellerManageCateWriteDao.del(sellerManageCateId) > 0;
    }

    public boolean auditing(Map<String, String> map) {
        //1. business check
        if (map == null || StringUtil.isEmpty(map.get("id")))
            throw new BusinessException("提交审核失败，id为空");
        if (StringUtil.isEmpty(map.get("optId")))
            throw new BusinessException("提交审核失败，操作人为空");
        if (StringUtil.isEmpty(map.get("state")))
            throw new BusinessException("提交审核失败，审核状态为空");

        SellerManageCate manager = sellerManageCateWriteDao.get(Integer.valueOf(map.get("id")));
        if (null == manager)
            throw new BusinessException("提交审核失败，该内容不存在");
        if (SellerManageCate.Status.COMMIT.getValue() != manager.getState())
            throw new BusinessException("审核失败，只有“提交审核”的才可以审核");
        manager.setState(Integer.valueOf(map.get("state")));
        manager.setOptId(Integer.valueOf(map.get("optId")));
        manager.setOptTime(new Date());

        //2. dbOper
        return sellerManageCateWriteDao.update(manager) > 0;
    }

    public boolean stop(Map<String, String> map) {
        //1. business check
        if (null == map || StringUtil.isEmpty(map.get("id")))
            throw new BusinessException("停用失败，id为空");
        if (StringUtil.isEmpty(map.get("stopId")))
            throw new BusinessException("停用失败，停用人为空");
        if (StringUtil.isEmpty(map.get("stopReason")))
            throw new BusinessException("停用失败，停用原因为空");
        SellerManageCate manager = sellerManageCateWriteDao.get(Integer.valueOf(map.get("id")));
        manager.setState(SellerManageCate.Status.DEL.getValue());
        manager.setStopId(Integer.valueOf(map.get("stopId")));
        manager.setStopReason(map.get("stopReason"));
        manager.setStopTime(new Date());
        //2. dbOper
        return sellerManageCateWriteDao.update(manager) > 0;
    }

    public boolean commit(Integer id) {
        //1. business check
        if (null == id || 0 == id)
            throw new BusinessException("审核失败，id为空");
        SellerManageCate manager = sellerManageCateWriteDao.get(id);
        if (null == manager)
            throw new BusinessException("提交审核失败，该内容不存在");
        if (SellerManageCate.Status.DEFAULT.getValue() != manager.getState())
            throw new BusinessException("提交审核失败，该分类不可以提交审核");

        manager.setState(SellerManageCate.Status.COMMIT.getValue());
        //2. dbOper
        return sellerManageCateWriteDao.update(manager) > 0;
    }

    public SellerManageCate getSellerManageCateById(Integer sellerManageCateId) {
        if (null == sellerManageCateId || 0 == sellerManageCateId)
            throw new BusinessException("根据id获取商家可以经营商品分类表失败，id为空");
        return sellerManageCateWriteDao.get(sellerManageCateId);
    }

    public Integer pageSellerManageCateCount(Map<String, String> queryMap) {
        return sellerManageCateWriteDao.count(queryMap);
    }

    public List<SellerManageCate> pageSellerManageCate(Map<String, String> queryMap, Integer start,
                                                       Integer size) {
        List<SellerManageCate> list = sellerManageCateWriteDao.page(queryMap, start, size);
        if (null != list && list.size() > 0) {
            for (SellerManageCate cate : list) {
                Seller seller = sellerWriteDao.get(cate.getCreateId());
                if (null != seller && !StringUtil.isEmpty(seller.getName()))
                    cate.setCreateUser(seller.getName());
            }
        }
        return list;
    }

}
