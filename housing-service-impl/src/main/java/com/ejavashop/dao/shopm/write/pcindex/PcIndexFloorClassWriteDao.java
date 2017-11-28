package com.ejavashop.dao.shopm.write.pcindex;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcindex.PcIndexFloorClass;

@Repository
public interface PcIndexFloorClassWriteDao {

    PcIndexFloorClass get(java.lang.Integer id);

    Integer insert(PcIndexFloorClass pcIndexFloorClass);

    Integer update(PcIndexFloorClass pcIndexFloorClass);

    Integer delete(Integer id);
}