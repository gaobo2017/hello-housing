package com.ejavashop.dao.shopm.write.mseller;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.MSellerIndexFloor;

@Repository
public interface MSellerIndexFloorWriteDao {

    MSellerIndexFloor get(java.lang.Integer id);

    Integer insert(MSellerIndexFloor mIndexFloor);

    Integer update(MSellerIndexFloor mIndexFloor);

    Integer delete(java.lang.Integer id);
}