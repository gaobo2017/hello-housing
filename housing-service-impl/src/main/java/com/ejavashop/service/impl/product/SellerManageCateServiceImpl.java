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
import com.ejavashop.entity.seller.SellerManageCate;
import com.ejavashop.model.product.SellerManageCateModel;
import com.ejavashop.service.product.ISellerManageCateService;

@Service
public class SellerManageCateServiceImpl implements ISellerManageCateService {
    private static Logger log = LogManager.getLogger(SellerManageCateServiceImpl.class);

    @Resource
    private SellerManageCateModel sellerManageCateModel;

    @Override
    public ServiceResult<Boolean> saveSellerManageCate(SellerManageCate sellerManageCate) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerManageCateModel.saveSellerManageCate(sellerManageCate));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("SellerManageCateServiceImpl saveSellerManageCate param:"
                      + JSON.toJSONString(sellerManageCate));
            log.error("SellerManageCateServiceImpl saveSellerManageCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateSellerManageCate(SellerManageCate sellerManageCate) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerManageCateModel.updateSellerManageCate(sellerManageCate));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("SellerManageCateServiceImpl updateSellerManageCate param:"
                      + JSON.toJSONString(sellerManageCate));
            log.error("SellerManageCateServiceImpl updateSellerManageCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> delSellerManageCate(Integer sellerManageCateId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerManageCateModel.delSellerManageCate(sellerManageCateId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("SellerManageCateServiceImpl delSellerManageCate sellerManageCateId:"
                      + sellerManageCateId);
            log.error("SellerManageCateServiceImpl delSellerManageCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> auditing(Map<String, String> map) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerManageCateModel.auditing(map));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("SellerManageCateServiceImpl auditing map:" + JSON.toJSONString(map));
            log.error("SellerManageCateServiceImpl auditing exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> stop(Map<String, String> map) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerManageCateModel.stop(map));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("SellerManageCateServiceImpl commit map:" + JSON.toJSONString(map));
            log.error("SellerManageCateServiceImpl commit exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> commit(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerManageCateModel.commit(id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("SellerManageCateServiceImpl commit id:" + id);
            log.error("SellerManageCateServiceImpl commit exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<SellerManageCate> getSellerManageCateById(Integer sellerManageCateId) {
        ServiceResult<SellerManageCate> serviceResult = new ServiceResult<SellerManageCate>();
        try {
            serviceResult
                .setResult(sellerManageCateModel.getSellerManageCateById(sellerManageCateId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "SellerManageCateServiceImpl getSellerManageCateById id:" + sellerManageCateId);
            log.error("SellerManageCateServiceImpl getSellerManageCateById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SellerManageCate>> pageSellerManageCate(Map<String, String> queryMap,
                                                                      PagerInfo pager) {
        ServiceResult<List<SellerManageCate>> serviceResult = new ServiceResult<List<SellerManageCate>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sellerManageCateModel.pageSellerManageCateCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<SellerManageCate> list = sellerManageCateModel.pageSellerManageCate(queryMap,
                start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("SellerManageCateServiceImpl pageSellerManageCate queryMap:"
                      + JSON.toJSONString(queryMap) + " pager:" + JSON.toJSONString(pager));
            log.error("SellerManageCateServiceImpl pageSellerManageCate exception:", e);
        }
        return serviceResult;
    }

}