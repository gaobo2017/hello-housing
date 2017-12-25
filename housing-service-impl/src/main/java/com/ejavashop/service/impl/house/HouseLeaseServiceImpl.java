package com.ejavashop.service.impl.house;

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
import com.ejavashop.entity.house.HousingLease;
import com.ejavashop.model.house.HouseCostModel;
import com.ejavashop.model.house.HouseLeaseModel;
import com.ejavashop.service.house.IHouseLeaseService;
import com.ejavashop.vo.house.HousingCostVO;
import com.ejavashop.vo.house.HousingLeaseVO;

@Service(value = "houseLeaseService")
public class HouseLeaseServiceImpl implements IHouseLeaseService {

    private static Logger   log = LogManager.getLogger(HouseLeaseServiceImpl.class);

    @Resource
    private HouseCostModel  houseCostModel;

    @Resource
    private HouseLeaseModel houseLeaseModel;

    @Override
    public ServiceResult<List<HousingLeaseVO>> getHousingLeaseList(Map<String, String> queryMap,
                                                                   PagerInfo pager) {
        ServiceResult<List<HousingLeaseVO>> serviceResult = new ServiceResult<List<HousingLeaseVO>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(houseLeaseModel.getHousingLeaseCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<HousingLeaseVO> list = houseLeaseModel.getHousingLeaseList(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error(
                "[HousingLeaseService][getHousingLeaseList]根据条件分页查询租赁时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[HousingLeaseService][getHousingLeaseList] param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[HousingLeaseService][getHousingLeaseList] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<HousingCostVO>> getHousingCostList(Map<String, String> queryMap,
                                                                 PagerInfo pager) {
        ServiceResult<List<HousingCostVO>> serviceResult = new ServiceResult<List<HousingCostVO>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(houseCostModel.getHousingCostCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<HousingCostVO> list = houseCostModel.getHousingCostList(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error(
                "[HouseCostService][getHousingCostList]根据条件分页查询房源成本信息时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[HouseCostService][getHousingCostList] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[HouseCostService][getHousingCostList] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<HousingLease> getHousingLeaseById(Integer id) {
        ServiceResult<HousingLease> serviceResult = new ServiceResult<HousingLease>();
        try {
            serviceResult.setResult(houseLeaseModel.getHousingLeaseById(id));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[HouseCostService][getHousingCostDetailById]根据id[" + id + "]取得成本明细时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[HouseCostService][getHousingLeaseById]根据id[" + id + "]取得成本明细信息时出现未知异常：", e);
        }
        return serviceResult;
    }
    
    
    @Override
    public ServiceResult<Integer> updateHousingLease(HousingLease housingLease) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(houseLeaseModel.updateHousingLease(housingLease));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[houseLeaseModel][updateHousingLease]平台修改租赁记录时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[houseLeaseModel][updateHousingLease]平台修改租赁记录时出现未知异常：", e);
        }
        return serviceResult;
    }
    
    
    @Override
    public ServiceResult<Integer> cancelLeaseHousingLease(HousingLease housingLease) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(houseLeaseModel.cancelLeaseHousingLease(housingLease));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[houseLeaseModel][cancelLeaseHousingLease]平台退租租赁记录时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[houseLeaseModel][cancelLeaseHousingLease]平台修改租赁记录时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> createHousingLease(HousingLease housingLease) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<Integer> createHousingLeaseAndSummaryIncome(HousingLease housingLease) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult
                .setResult(houseLeaseModel.createHousingLeaseAndSummaryIncome(housingLease));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[houseLeaseModel][createHousingLeaseAndSummaryIncome]平台新增租赁记录时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[houseLeaseModel][createHousingLeaseAndSummaryIncome]平台新增租赁记录时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> deleteHousingLease(Integer housingLeaseId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(houseLeaseModel.deleteHousingLease(housingLeaseId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[HouseCostService][deleteHousingResources]删除房源时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[HouseCostService][deleteHousingResources]删除房源时出现未知异常：", e);
        }
        return serviceResult;
    }

}
