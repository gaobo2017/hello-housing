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
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.model.product.ProductTypeAttrModel;
import com.ejavashop.service.product.IProductTypeAttrService;
import com.ejavashop.vo.product.ProductTypeAttrVO;

@Service(value = "productTypeAttrService")
public class ProductTypeAttrServiceImpl implements IProductTypeAttrService {
    private static Logger log = LogManager.getLogger(ProductTypeAttrServiceImpl.class);

    @Resource
    private ProductTypeAttrModel productTypeAttrModel;

    @Override
    public ServiceResult<Boolean> saveProductTypeAttr(ProductTypeAttr productTypeAttr) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productTypeAttrModel.saveProductTypeAttr(productTypeAttr));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductTypeAttrServiceImpl saveProductTypeAttr param:"
                      + JSON.toJSONString(productTypeAttr));
            log.error("ProductTypeAttrServiceImpl saveProductTypeAttr exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProductTypeAttr(ProductTypeAttr productTypeAttr) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productTypeAttrModel.updateProductTypeAttr(productTypeAttr));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductTypeAttrServiceImpl updateProductTypeAttr param:"
                      + JSON.toJSONString(productTypeAttr));
            log.error("ProductTypeAttrServiceImpl updateProductTypeAttr exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> delProductTypeAttr(Integer productTypeAttrId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productTypeAttrModel.delProductTypeAttr(productTypeAttrId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductTypeAttrServiceImpl delProductTypeAttr productTypeAttrId:"
                      + productTypeAttrId);
            log.error("ProductTypeAttrServiceImpl delProductTypeAttr exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ProductTypeAttr> getProductTypeAttrById(Integer productTypeAttrId) {
        ServiceResult<ProductTypeAttr> serviceResult = new ServiceResult<ProductTypeAttr>();
        try {
            serviceResult.setResult(productTypeAttrModel.getProductTypeAttrById(productTypeAttrId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductTypeAttrServiceImpl getProductTypeAttrById id:" + productTypeAttrId);
            log.error("ProductTypeAttrServiceImpl getProductTypeAttrById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductTypeAttr>> getProductTypeAttrByTypeId(Integer productTypeId) {
        ServiceResult<List<ProductTypeAttr>> serviceResult = new ServiceResult<List<ProductTypeAttr>>();
        try {
            serviceResult.setResult(productTypeAttrModel.getProductTypeAttrByTypeId(productTypeId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "ProductTypeAttrServiceImpl getProductTypeAttrById productTypeId:" + productTypeId);
            log.error("ProductTypeAttrServiceImpl getProductTypeAttrById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductTypeAttrVO>> pageProductTypeAttr(Map<String, String> queryMap,
                                                                      PagerInfo pager) {
        ServiceResult<List<ProductTypeAttrVO>> serviceResult = new ServiceResult<List<ProductTypeAttrVO>>();
        try {
            serviceResult.setResult(productTypeAttrModel.pageProductTypeAttr(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductTypeAttrServiceImpl pageProductTypeAttr queryMap:"
                      + JSON.toJSONString(queryMap) + " pager:" + JSON.toJSONString(pager));
            log.error("ProductTypeAttrServiceImpl pageProductTypeAttr exception:", e);
        }
        return serviceResult;
    }
}
