package com.ejavashop.service.seller;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.seller.SellerQq;

/**
 * 商家QQ管理
 *                       
 * @Filename: ISellerQqService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISellerQqService {

    /**
     * 根据店铺ID获取店铺正在使用的QQ
     * @param sellerId
     * @return
     */
    ServiceResult<List<SellerQq>> getInusedSellerQqBySId(Integer sellerId);

    /**
     * 根据id取得商家客服QQ
     * @param  sellerQqId
     * @return
     */
    ServiceResult<SellerQq> getById(Integer sellerQqId);

    /**
     * 保存商家客服QQ
     * @param  sellerQq
     * @return
     */
    ServiceResult<Integer> save(SellerQq sellerQq);

    /**
    * 更新商家客服QQ
    * @param  sellerQq
    * @return
    */
    ServiceResult<Integer> update(SellerQq sellerQq);

    /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<SellerQq>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 客服qq列表
     * @return
     */
    List<SellerQq> list();

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);
}