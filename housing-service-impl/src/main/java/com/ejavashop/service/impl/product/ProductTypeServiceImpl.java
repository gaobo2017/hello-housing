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
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.model.product.ProductTypeModel;
import com.ejavashop.service.product.IProductTypeService;

@Service(value = "productTypeService")
public class ProductTypeServiceImpl implements IProductTypeService {
    private static Logger log = LogManager.getLogger(ProductTypeServiceImpl.class);

    @Resource
    private ProductTypeModel productTypeModel;

    @Override
    public ServiceResult<Boolean> saveProductType(ProductType productType) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productTypeModel.saveProductType(productType));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "ProductTypeServiceImpl saveProductType param:" + JSON.toJSONString(productType));
            log.error("ProductTypeServiceImpl saveProductType exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> saveProductType(Map<String, Object> map) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productTypeModel.saveProductType(map));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductTypeServiceImpl saveProductType param:" + JSON.toJSONString(map));
            log.error("ProductTypeServiceImpl saveProductType exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProductType(ProductType productType) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productTypeModel.updateProductType(productType));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "ProductTypeServiceImpl updateProductType param:" + JSON.toJSONString(productType));
            log.error("ProductTypeServiceImpl updateProductType exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProductType(Map<String, Object> map) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productTypeModel.updateProductType(map));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductTypeServiceImpl updateProductType param:" + JSON.toJSONString(map));
            log.error("ProductTypeServiceImpl updateProductType exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> delProductType(Integer productTypeId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productTypeModel.delProductType(productTypeId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductTypeServiceImpl delProductType id:" + productTypeId);
            log.error("ProductTypeServiceImpl delProductType exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ProductType> getProductTypeById(Integer productTypeId) {
        ServiceResult<ProductType> serviceResult = new ServiceResult<ProductType>();
        try {

            serviceResult.setResult(productTypeModel.getProductTypeById(productTypeId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductTypeServiceImpl getProductTypeById id:" + productTypeId);
            log.error("ProductTypeServiceImpl getProductTypeById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductTypeAttr>> getAttrByTypeId(Integer productTypeId) {
        ServiceResult<List<ProductTypeAttr>> serviceResult = new ServiceResult<List<ProductTypeAttr>>();
        try {
            serviceResult.setResult(productTypeModel.getAttrByTypeId(productTypeId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductTypeServiceImpl getProductTypeById id:" + productTypeId);
            log.error("ProductTypeServiceImpl getProductTypeById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductType>> pageProductType(Map<String, String> queryMap,
                                                            PagerInfo pager) {
        ServiceResult<List<ProductType>> serviceResult = new ServiceResult<List<ProductType>>();
        try {
            serviceResult.setResult(productTypeModel.pageProductType(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductTypeServiceImpl pageProductType queryMap:"
                      + JSON.toJSONString(queryMap) + " pager:" + JSON.toJSONString(pager));
            log.error("ProductTypeServiceImpl pageProductType exception:", e);
        }
        return serviceResult;
    }
}
