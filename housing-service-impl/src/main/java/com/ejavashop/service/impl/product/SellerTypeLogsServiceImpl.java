package com.ejavashop.service.impl.product;

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
import com.ejavashop.entity.seller.SellerTypeLogs;
import com.ejavashop.model.product.SellerTypeLogsModel;
import com.ejavashop.service.product.ISellerTypeLogsService;

@Service(value = "sellerTypeLogsService")
public class SellerTypeLogsServiceImpl implements ISellerTypeLogsService {
    private static Logger log = LogManager.getLogger(SellerTypeLogsServiceImpl.class);

    @Resource
    private SellerTypeLogsModel sellerTypeLogsModel;

    @Override
    public ServiceResult<Boolean> saveSellerTypeLogs(SellerTypeLogs sellerTypeLogs) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerTypeLogsModel.saveSellerTypeLogs(sellerTypeLogs));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("SellerTypeLogsServiceImpl saveSellerTypeLogs param:"
                      + JSON.toJSONString(sellerTypeLogs));
            log.error("SellerTypeLogsServiceImpl saveSellerTypeLogs exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateSellerTypeLogs(SellerTypeLogs sellerTypeLogs) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {

            serviceResult.setResult(sellerTypeLogsModel.updateSellerTypeLogs(sellerTypeLogs));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("SellerTypeLogsServiceImpl updateSellerTypeLogs param:"
                      + JSON.toJSONString(sellerTypeLogs));
            log.error("SellerTypeLogsServiceImpl updateSellerTypeLogs exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> delSellerTypeLogs(Integer sellerTypeLogsId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerTypeLogsModel.delSellerTypeLogs(sellerTypeLogsId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "SellerTypeLogsServiceImpl delSellerTypeLogs sellerTypeLogsId:" + sellerTypeLogsId);
            log.error("SellerTypeLogsServiceImpl delSellerTypeLogs exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<SellerTypeLogs> getSellerTypeLogsById(Integer sellerTypeLogsId) {
        ServiceResult<SellerTypeLogs> serviceResult = new ServiceResult<SellerTypeLogs>();
        try {
            serviceResult.setResult(sellerTypeLogsModel.getSellerTypeLogsById(sellerTypeLogsId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("SellerTypeLogsServiceImpl getSellerTypeLogsById id:" + sellerTypeLogsId);
            log.error("SellerTypeLogsServiceImpl getSellerTypeLogsById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SellerTypeLogs>> getSellerTypeLogsByCateId(Integer cateId) {
        ServiceResult<List<SellerTypeLogs>> serviceResult = new ServiceResult<List<SellerTypeLogs>>();
        try {
            serviceResult.setResult(sellerTypeLogsModel.getSellerTypeLogsByCateId(cateId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("SellerTypeLogsServiceImpl getSellerTypeLogsByCateId cateId:" + cateId);
            log.error("SellerTypeLogsServiceImpl getSellerTypeLogsByCateId exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SellerTypeLogs>> pageSellerTypeLogs(Map<String, String> queryMap,
                                                                  PagerInfo pager) {
        ServiceResult<List<SellerTypeLogs>> serviceResult = new ServiceResult<List<SellerTypeLogs>>();
        try {
            serviceResult.setResult(sellerTypeLogsModel.pageSellerTypeLogs(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("SellerTypeLogsServiceImpl pageSellerTypeLogs queryMap:"
                      + JSON.toJSONString(queryMap) + " pager:" + JSON.toJSONString(pager));
            log.error("SellerTypeLogsServiceImpl pageSellerTypeLogs exception:", e);
        }
        return serviceResult;
    }

}