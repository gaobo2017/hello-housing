package com.ejavashop.service.operate;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.operate.CourierCompany;

public interface ICourierCompanyService {

    /**
     * 根据id取得快递公司
     * @param  courierCompanyId
     * @return
     */
    ServiceResult<CourierCompany> getCourierCompanyById(Integer courierCompanyId);

    /**
     * 保存快递公司
     * @param  courierCompany
     * @return
     */
    ServiceResult<Integer> saveCourierCompany(CourierCompany courierCompany);

    /**
    * 更新快递公司
    * @param  courierCompany
    * @return
    */
    ServiceResult<Integer> updateCourierCompany(CourierCompany courierCompany);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<CourierCompany>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 所有CourierCompany列表
     * @return
     */
    List<CourierCompany> list();

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);
}