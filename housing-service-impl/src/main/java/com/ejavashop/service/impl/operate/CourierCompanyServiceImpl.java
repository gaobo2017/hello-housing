package com.ejavashop.service.impl.operate;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.operate.CourierCompany;
import com.ejavashop.model.operate.CourierCompanyModel;
import com.ejavashop.service.operate.ICourierCompanyService;

@Service(value = "courierCompanyService")
public class CourierCompanyServiceImpl implements ICourierCompanyService {
    private static Logger       log = LogManager.getLogger(CourierCompanyServiceImpl.class);

    @Resource
    private CourierCompanyModel courierCompanyModel;

    /**
    * 根据id取得快递公司
    * @param  courierCompanyId
    * @return
    */
    @Override
    public ServiceResult<CourierCompany> getCourierCompanyById(Integer courierCompanyId) {
        ServiceResult<CourierCompany> result = new ServiceResult<CourierCompany>();
        try {
            result.setResult(courierCompanyModel.getCourierCompanyById(courierCompanyId));
        } catch (Exception e) {
            log.error("根据id[" + courierCompanyId + "]取得快递公司时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + courierCompanyId + "]取得快递公司时出现未知异常");
        }
        return result;
    }

    /**
     * 保存快递公司
     * @param  courierCompany
     * @return
     */
    @Override
    public ServiceResult<Integer> saveCourierCompany(CourierCompany courierCompany) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(courierCompanyModel.saveCourierCompany(courierCompany));
        } catch (Exception e) {
            log.error("保存快递公司时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存快递公司时出现未知异常");
        }
        return result;
    }

    /**
    * 更新快递公司
    * @param  courierCompany
    * @return
    */
    @Override
    public ServiceResult<Integer> updateCourierCompany(CourierCompany courierCompany) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(courierCompanyModel.updateCourierCompany(courierCompany));
        } catch (Exception e) {
            log.error("更新快递公司时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新快递公司时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<CourierCompany>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<CourierCompany>> serviceResult = new ServiceResult<List<CourierCompany>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(courierCompanyModel.pageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<CourierCompany> list = courierCompanyModel.page(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[CourierCompanyServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[CourierCompanyServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public List<CourierCompany> list() {
        List<CourierCompany> list = courierCompanyModel.list();
        return list;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> sr = new ServiceResult<Boolean>();
        try {
            sr.setResult(courierCompanyModel.del(id));
        } catch (Exception e) {
            log.error("[CourierCompanyServiceImpl][del] exception:" + e.getMessage());
            e.printStackTrace();
        }
        return sr;
    }
}