package com.ejavashop.service.impl.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.ProductAttr;
import com.ejavashop.model.product.ProductAttrModel;
import com.ejavashop.service.product.IProductAttrService;

@Component
public class ProductAttrServiceImpl implements IProductAttrService {
    private static Logger log = LogManager.getLogger(ProductAttrServiceImpl.class);

    @Resource
    private ProductAttrModel productAttrModel;

    @Override
    public ServiceResult<Boolean> saveProductAttr(ProductAttr productAttr) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productAttrModel.saveProductAttr(productAttr));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "ProductAttrServiceImpl saveProductAttr param:" + JSON.toJSONString(productAttr));
            log.error("ProductAttrServiceImpl saveProductAttr exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProductAttr(ProductAttr productAttr) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productAttrModel.updateProductAttr(productAttr));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "ProductAttrServiceImpl updateProductAttr param:" + JSON.toJSONString(productAttr));
            log.error("ProductAttrServiceImpl updateProductAttr exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> delProductAttr(Integer productAttrId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productAttrModel.delProductAttr(productAttrId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductAttrServiceImpl delProductAttr productAttrId:" + productAttrId);
            log.error("ProductAttrServiceImpl delProductAttr exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ProductAttr> getProductAttrById(Integer productAttrId) {
        ServiceResult<ProductAttr> serviceResult = new ServiceResult<ProductAttr>();
        try {
            serviceResult.setResult(productAttrModel.getProductAttrById(productAttrId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductAttrServiceImpl getProductAttrById id:" + productAttrId);
            log.error("ProductAttrServiceImpl getProductAttrById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductAttr>> getProductAttrByProductId(Integer productId) {
        ServiceResult<List<ProductAttr>> serviceResult = new ServiceResult<List<ProductAttr>>();
        try {
            if (null == productId || 0 == productId)
                throw new BusinessException("根据商品id获取商品商品属性失败，商品id为空");
            serviceResult.setResult(productAttrModel.getByProductId(productId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductAttrServiceImpl getProductAttrByProductId productId:" + productId);
            log.error("ProductAttrServiceImpl getProductAttrByProductId exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductAttr>> pageProductAttr(Map<String, String> queryMap,
                                                            PagerInfo pager) {
        ServiceResult<List<ProductAttr>> serviceResult = new ServiceResult<List<ProductAttr>>();
        try {
            serviceResult.setResult(productAttrModel.pageProductAttr(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductAttrServiceImpl pageProductAttr queryMap:"
                      + JSON.toJSONString(queryMap) + " pager:" + JSON.toJSONString(pager));
            log.error("ProductAttrServiceImpl pageProductAttr exception:", e);
        }
        return serviceResult;
    }

}