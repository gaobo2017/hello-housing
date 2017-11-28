package com.ejavashop.dao.shop.write.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerApply;

@Repository
public interface SellerApplyWriteDao {

    /**
     * 根据条件查询count
     * @param queryMap
     * @return
     */
    Integer getSellerApplysCount(@Param("queryMap") Map<String, String> queryMap);

    /**
     * 分页查询
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<SellerApply> getSellerApplys(@Param("queryMap") Map<String, String> queryMap,
                                      @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 更新（只更新对象中不为null的字段）
     * @param sellerApply
     * @return
     */
    Integer update(SellerApply sellerApply);

    SellerApply get(Integer id);

    Integer delete(Integer id);

    /**
     * 根据商家ID查询商家申请
     * @param userId
     * @return
     */
    SellerApply getSellerApplyByUserId(Integer userId);

    Integer insert(SellerApply sellerApply);

    List<SellerApply> getSellerApplyByCompany(@Param("company") String company);
}
