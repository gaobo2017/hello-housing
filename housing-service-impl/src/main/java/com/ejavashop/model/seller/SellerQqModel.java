package com.ejavashop.model.seller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.write.seller.SellerQqWriteDao;
import com.ejavashop.entity.seller.SellerQq;

@Component(value = "sellerQqModel")
public class SellerQqModel {

    @Resource
    private SellerQqWriteDao sellerQqWriteDao;

    /**
     * 根据店铺ID获取店铺正在使用的QQ
     * @param sellerId
     * @return
     */
    public List<SellerQq> getInusedSellerQqBySId(Integer sellerId) {
        return sellerQqWriteDao.getInusedSellerQqBySId(sellerId);
    }

    /**
    * 根据id取得商家客服QQ
    * @param  sellerQqId
    * @return
    */
    public SellerQq getById(Integer sellerQqId) {
        return sellerQqWriteDao.get(sellerQqId);
    }

    /**
     * 保存商家客服QQ
     * @param  sellerQq
     * @return
     */
    public Integer save(SellerQq sellerQq) {
        return sellerQqWriteDao.save(sellerQq);
    }

    /**
    * 更新商家客服QQ
    * @param  sellerQq
    * @return
    */
    public Integer update(SellerQq sellerQq) {
        return sellerQqWriteDao.update(sellerQq);
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return sellerQqWriteDao.getCount(queryMap);
    }

    public List<SellerQq> page(Map<String, Object> queryMap) {
        return sellerQqWriteDao.page(queryMap);
    }

    public List<SellerQq> list() {
        List<SellerQq> list = sellerQqWriteDao.list();
        return list;
    }

    public boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除[" + id + "]时出错");
        return sellerQqWriteDao.del(id) > 0;
    }
}
