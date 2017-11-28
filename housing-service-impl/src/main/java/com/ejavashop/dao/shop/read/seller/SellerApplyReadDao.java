package com.ejavashop.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerApply;

@Repository
public interface SellerApplyReadDao {

    SellerApply get(java.lang.Integer id);

    /**
     * 以用户获取其商家申请
     * @param userid
     * @return
     */
    SellerApply getSellerApplyByUser(Integer userid);

    List<SellerApply> getSellerApplyByCondition(Map<String, Object> map);

}
