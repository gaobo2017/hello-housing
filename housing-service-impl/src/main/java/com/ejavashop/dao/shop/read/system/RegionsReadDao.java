package com.ejavashop.dao.shop.read.system;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.system.Regions;

@Repository
public interface RegionsReadDao {

    Regions get(java.lang.Integer id);

    List<Regions> getProvince();

    List<Regions> getChildrenArea(Map<String, Object> map);

    List<Regions> getByParentId(Integer parentId);

}
