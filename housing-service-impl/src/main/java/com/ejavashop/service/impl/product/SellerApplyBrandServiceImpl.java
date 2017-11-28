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
import com.ejavashop.entity.seller.SellerApplyBrand;
import com.ejavashop.model.product.SellerApplyBrandModel;
import com.ejavashop.service.product.ISellerApplyBrandService;

/**
 */
@Service
public class SellerApplyBrandServiceImpl implements ISellerApplyBrandService {
    private static Logger         log = LogManager.getLogger(SellerApplyBrandServiceImpl.class);
    @Resource
    private SellerApplyBrandModel sellerApplyBrandModel;

    @Override
    public ServiceResult<Boolean> save(SellerApplyBrand brand) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerApplyBrandModel.save(brand));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerApplyBrandServiceImpl][save] param:" + JSON.toJSONString(brand));
            log.error("[SellerApplyBrandServiceImpl][save] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<SellerApplyBrand> getById(Integer id) {
        ServiceResult<SellerApplyBrand> serviceResult = new ServiceResult<SellerApplyBrand>();
        try {
            serviceResult.setResult(sellerApplyBrandModel.getById(id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerApplyBrandServiceImpl][save] param:" + id);
            log.error("[SellerApplyBrandServiceImpl][getById] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SellerApplyBrand>> page(Map<String, String> queryMap,
                                                      PagerInfo pager) {
        ServiceResult<List<SellerApplyBrand>> serviceResult = new ServiceResult<List<SellerApplyBrand>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sellerApplyBrandModel.pageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<SellerApplyBrand> list = sellerApplyBrandModel.page(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerApplyBrandServiceImpl][save] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SellerApplyBrandServiceImpl][page] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SellerApplyBrand>> todoList(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        ServiceResult<List<SellerApplyBrand>> serviceResult = new ServiceResult<List<SellerApplyBrand>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sellerApplyBrandModel.todoListCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<SellerApplyBrand> list = sellerApplyBrandModel.todoList(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerApplyBrandServiceImpl][todoList] param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[SellerApplyBrandServiceImpl][todoList] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> update(SellerApplyBrand brand) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerApplyBrandModel.update(brand));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerApplyBrandServiceImpl][save] param:" + JSON.toJSONString(brand));
            log.error("[SellerApplyBrandServiceImpl][audit] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> commit(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerApplyBrandModel.commit(id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerApplyBrandServiceImpl][save] param:" + id);
            log.error("[SellerApplyBrandServiceImpl][del] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerApplyBrandModel.del(id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerApplyBrandServiceImpl][save] param:" + id);
            log.error("[SellerApplyBrandServiceImpl][del] exception:", e);
        }
        return serviceResult;
    }

}
