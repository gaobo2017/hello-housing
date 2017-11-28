package com.ejavashop.dao.shop.write.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.system.Regions;

@Repository
public interface RegionsWriteDao {

    Regions get(Integer id);

    List<Regions> getProvince();

    List<Regions> getChildrenArea(Map<String, Object> map);

}
