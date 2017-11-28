package com.ejavashop.dao.shopm.write.mindex;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.MIndexFloor;

@Repository
public interface MIndexFloorWriteDao {

    MIndexFloor get(java.lang.Integer id);

    Integer insert(MIndexFloor mIndexFloor);

    Integer update(MIndexFloor mIndexFloor);

    Integer delete(java.lang.Integer id);
}