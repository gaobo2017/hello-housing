package com.ejavashop.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerUser;

@Repository
public interface SellerUserReadDao {

    SellerUser get(java.lang.Integer id);

    Integer getCount(@Param("queryMap") Map<String, String> queryMap);

    List<SellerUser> page(@Param("queryMap") Map<String, String> queryMap,
                          @Param("start") Integer start, @Param("size") Integer size);

    SellerUser getByNamePwd(Map<String, Object> queryMap);

    /**
     * 根据用户名取商家用户
     * @param name
     * @return
     */
    List<SellerUser> getByName(@Param("name") String name);
}