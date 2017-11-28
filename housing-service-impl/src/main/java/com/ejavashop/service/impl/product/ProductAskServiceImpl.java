package com.ejavashop.service.impl.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.product.ProductAsk;
import com.ejavashop.model.product.ProductAskModel;
import com.ejavashop.service.product.IProductAskService;

/**
 * 商品咨询管理实现
 * 
 * @Filename: ProductAskServiceImpl.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Service(value = "productAskService")
public class ProductAskServiceImpl implements IProductAskService {
    private static Logger   log = LogManager.getLogger(ProductAskServiceImpl.class);

    @Resource
    private ProductAskModel productAskModel;

    @Override
    public ServiceResult<ProductAsk> getProductAskById(Integer productAskId) {
        Assert.notNull(productAskModel, "Property 'productAskModel' is required.");
        ServiceResult<ProductAsk> result = new ServiceResult<ProductAsk>();
        try {
            result.setResult(productAskModel.getProductAskById(productAskId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ProductAskService][getProductAskById]根据id[" + productAskId
                      + "]取得商品咨询管理时出现异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[ProductAskService][getProductAskById]根据id[" + productAskId + "]取得商品咨询管理时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateProductAsk(ProductAsk productAsk) {
        Assert.notNull(productAskModel, "Property 'productAskModel' is required.");
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(productAskModel.updateProductAsk(productAsk));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ProductAskService][updateProductAsk]更新商品咨询管理时出现异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductAskService][updateProductAsk]更新商品咨询时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ProductAsk>> getProductAsksWithInfo(Map<String, String> queryMap,
                                                                 PagerInfo pager) {
        Assert.notNull(productAskModel, "Property 'productAskModel' is required.");
        ServiceResult<List<ProductAsk>> serviceResult = new ServiceResult<List<ProductAsk>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(productAskModel.getProductAsksCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<ProductAsk> list = productAskModel.getProductAsksWithInfo(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[ProductAskService][getProductAsksWithInfo]根据查询条件取所有的咨询时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[productAskService][getProductAsksWithInfo]根据查询条件取所有的咨询时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductAsk>> getProductAsks(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        Assert.notNull(productAskModel, "Property 'productAskModel' is required.");
        ServiceResult<List<ProductAsk>> serviceResult = new ServiceResult<List<ProductAsk>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(productAskModel.getProductAsksCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<ProductAsk> list = productAskModel.getProductAsks(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ProductAskService][getProductAsks]根据查询条件取所有的咨询时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[productAskService][getProductAsks]根据查询条件取所有的咨询时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ProductAsk> saveProductAsk(ProductAsk productAsk, Member member) {
        ServiceResult<ProductAsk> serviceResult = new ServiceResult<ProductAsk>();
        try {
            serviceResult.setResult(productAskModel.saveProductAsk(productAsk, member));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[productAskService][saveProductAsk]保存商品咨询表时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ProductAsk> getProductAskByProductId(String productId, Integer memberId) {
        ServiceResult<ProductAsk> serviceResult = new ServiceResult<ProductAsk>();
        try {
            serviceResult.setResult(productAskModel.getProductAskByProductId(productId, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[productAskService][getProductAskByProductId]获取产品咨询时发生异常:", e);
        }
        return serviceResult;
    }
}