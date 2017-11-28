package com.ejavashop.dao.shop.write.seller;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerUser;

@Repository
public interface SellerUserWriteDao {

    SellerUser get(java.lang.Integer id);

    Integer insert(SellerUser sellerUser);

    Integer update(SellerUser sellerUser);

    //    Integer getCount(@Param("queryMap") Map<String, String> queryMap);
    //
    //    List<SystemAdmin> page(@Param("queryMap") Map<String, String> queryMap,
    //                           @Param("start") Integer start, @Param("size") Integer size);
    //
    //    SystemAdmin getByNamePwd(Map<String, Object> queryMap);

    Integer del(Integer id);
}