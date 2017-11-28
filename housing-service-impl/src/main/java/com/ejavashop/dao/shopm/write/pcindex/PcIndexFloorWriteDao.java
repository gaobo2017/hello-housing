package com.ejavashop.dao.shopm.write.pcindex;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcindex.PcIndexFloor;

@Repository
public interface PcIndexFloorWriteDao {

    PcIndexFloor get(java.lang.Integer id);

    Integer insert(PcIndexFloor pcIndexFloor);

    Integer update(PcIndexFloor pcIndexFloor);

    Integer delete(Integer id);
}