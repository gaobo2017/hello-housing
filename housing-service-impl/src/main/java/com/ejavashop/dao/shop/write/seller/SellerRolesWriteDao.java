package com.ejavashop.dao.shop.write.seller;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerRoles;

@Repository
public interface SellerRolesWriteDao {

    SellerRoles get(java.lang.Integer id);

    Integer insert(SellerRoles sellerRoles);

    Integer update(SellerRoles sellerRoles);

    Integer delete(java.lang.Integer id);
}