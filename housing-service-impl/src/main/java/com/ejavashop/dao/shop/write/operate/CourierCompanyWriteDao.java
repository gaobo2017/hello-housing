package com.ejavashop.dao.shop.write.operate;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.operate.CourierCompany;

@Repository
public interface CourierCompanyWriteDao {

    //    CourierCompany get(Integer id);

    Integer save(CourierCompany courierCompany);

    Integer update(CourierCompany courierCompany);

    //    Integer getCount(@Param("param1") Map<String, String> queryMap);
    //
    //    List<CourierCompany> page(@Param("param1") Map<String, String> queryMap,
    //                              @Param("start") Integer start, @Param("size") Integer size);
    //
    //    List<CourierCompany> list();

    Integer del(Integer id);

}
