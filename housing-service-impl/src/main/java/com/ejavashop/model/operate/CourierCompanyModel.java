package com.ejavashop.model.operate;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.operate.CourierCompanyReadDao;
import com.ejavashop.dao.shop.write.operate.CourierCompanyWriteDao;
import com.ejavashop.entity.operate.CourierCompany;

@Component(value = "courierCompanyModel")
public class CourierCompanyModel {

    @Resource
    private CourierCompanyWriteDao courierCompanyWriteDao;
    @Resource
    private CourierCompanyReadDao  courierCompanyReadDao;

    /**
    * 根据id取得快递公司
    * @param  courierCompanyId
    * @return
    */
    public CourierCompany getCourierCompanyById(Integer courierCompanyId) {
        return courierCompanyReadDao.get(courierCompanyId);
    }

    /**
     * 保存快递公司
     * @param  courierCompany
     * @return
     */
    public Integer saveCourierCompany(CourierCompany courierCompany) {
        return courierCompanyWriteDao.save(courierCompany);
    }

    /**
    * 更新快递公司
    * @param  courierCompany
    * @return
    */
    public Integer updateCourierCompany(CourierCompany courierCompany) {
        return courierCompanyWriteDao.update(courierCompany);
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return courierCompanyReadDao.getCount(queryMap);
    }

    public List<CourierCompany> page(Map<String, String> queryMap, Integer start, Integer size) {
        return courierCompanyReadDao.page(queryMap, start, size);
    }

    public List<CourierCompany> list() {
        List<CourierCompany> list = courierCompanyReadDao.list();
        return list;
    }

    public boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除快递公司[" + id + "]时出错");
        return courierCompanyWriteDao.del(id) > 0;
    }
}
