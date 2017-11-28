package com.ejavashop.dao.shopm.write.mindex;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.MIndexFloorData;

@Repository
public interface MIndexFloorDataWriteDao {

    MIndexFloorData get(java.lang.Integer id);

    Integer insert(MIndexFloorData mIndexFloorData);

    Integer update(MIndexFloorData mIndexFloorData);

    Integer delete(java.lang.Integer id);

    Integer deleteByFloorId(java.lang.Integer mIndexFloorId);
}