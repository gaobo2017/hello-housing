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
import com.ejavashop.entity.product.ProductPicture;
import com.ejavashop.model.product.ProductPictureModel;
import com.ejavashop.service.product.IProductPictureService;

@Service(value = "productPictureService")
public class ProductPictureServiceImpl implements IProductPictureService {
    private static Logger log = LogManager.getLogger(ProductPictureServiceImpl.class);

    @Resource
    private ProductPictureModel productPictureModel;

    @Override
    public ServiceResult<Boolean> saveProductPicture(ProductPicture productPicture) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productPictureModel.saveProductPicture(productPicture));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductPictureServiceImpl saveProductPicture param:"
                      + JSON.toJSONString(productPicture));
            log.error("ProductPictureServiceImpl saveProductPicture exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProductPicture(ProductPicture productPicture) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productPictureModel.updateProductPicture(productPicture));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductPictureServiceImpl updateProductPicture param:"
                      + JSON.toJSONString(productPicture));
            log.error("ProductPictureServiceImpl updateProductPicture exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> delProductPicture(Integer productPictureId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productPictureModel.delProductPicture(productPictureId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "ProductPictureServiceImpl delProductPicture productPictureId:" + productPictureId);
            log.error("ProductPictureServiceImpl delProductPicture exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ProductPicture> getProductPictureById(Integer productPictureId) {
        ServiceResult<ProductPicture> serviceResult = new ServiceResult<ProductPicture>();
        try {
            serviceResult.setResult(productPictureModel.getProductPictureById(productPictureId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductPictureServiceImpl getProductPictureById id:" + productPictureId);
            log.error("ProductPictureServiceImpl getProductPictureById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductPicture>> pageProductPicture(Map<String, String> queryMap,
                                                                  PagerInfo pager) {
        ServiceResult<List<ProductPicture>> serviceResult = new ServiceResult<List<ProductPicture>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(productPictureModel.pageProductPictureCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<ProductPicture> list = productPictureModel.pageProductPicture(queryMap, start,
                size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductPictureServiceImpl pageProductPicture queryMap:"
                      + JSON.toJSONString(queryMap) + " pager:" + JSON.toJSONString(pager));
            log.error("ProductPictureServiceImpl pageProductPicture exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductPicture>> getProductPictureByProductId(Integer productId) {
        ServiceResult<List<ProductPicture>> serviceResult = new ServiceResult<List<ProductPicture>>();
        try {
            serviceResult.setResult(productPictureModel.getProductPictureByProductId(productId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "ProductPictureServiceImpl getProductPictureByProductId productId:" + productId);
            log.error("ProductPictureServiceImpl pageProductPicture exception:", e);
        }
        return serviceResult;
    }

}