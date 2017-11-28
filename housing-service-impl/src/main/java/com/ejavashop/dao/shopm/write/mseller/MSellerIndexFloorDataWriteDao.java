package com.ejavashop.dao.shopm.write.mseller;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.MSellerIndexFloorData;

@Repository
public interface MSellerIndexFloorDataWriteDao {

    MSellerIndexFloorData get(java.lang.Integer id);

    Integer insert(MSellerIndexFloorData mIndexFloorData);

    Integer update(MSellerIndexFloorData mIndexFloorData);

    Integer delete(java.lang.Integer id);

    Integer deleteByFloorId(java.lang.Integer mIndexFloorId);
}