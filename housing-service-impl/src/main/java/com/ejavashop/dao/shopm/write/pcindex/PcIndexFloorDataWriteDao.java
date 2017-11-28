package com.ejavashop.dao.shopm.write.pcindex;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcindex.PcIndexFloorData;

@Repository
public interface PcIndexFloorDataWriteDao {

    PcIndexFloorData get(java.lang.Integer id);

    Integer insert(PcIndexFloorData pcIndexFloorData);

    Integer update(PcIndexFloorData pcIndexFloorData);

    Integer delete(Integer id);

    Integer deleteByFloorClassId(Integer floorClassId);
}