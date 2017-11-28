package com.ejavashop.dao.shop.write.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerCate;

@Repository
public interface SellerCateWriteDao {

    Integer insert(SellerCate sellerCate);

    Integer update(SellerCate sellerCate);

    Integer del(Integer id);

    SellerCate get(Integer id);

    Integer count(@Param("param1") Map<String, String> queryMap);

    List<SellerCate> page(@Param("param1") Map<String, String> queryMap,
                          @Param("start") Integer start, @Param("size") Integer size);

    List<SellerCate> getByPid(@Param("pid") Integer pid, @Param("sellerId") Integer sellerId);

    /**
     * 统计分类下面有没有子分类
     * @param sellerCateId
     * @return
     */
    int countByPid(Integer sellerCateId);
}
