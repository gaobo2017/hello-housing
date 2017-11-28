package com.ejavashop.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerManageCate;

@Repository
public interface SellerManageCateReadDao {

    SellerManageCate get(Integer id);

    Integer chkCate(@Param("cateId") Integer cateId, @Param("seller") Integer seller);

    Integer count(@Param("param1") Map<String, String> queryMap);

    List<SellerManageCate> page(@Param("param1") Map<String, String> queryMap,
                                @Param("start") Integer start, @Param("size") Integer size);
}
