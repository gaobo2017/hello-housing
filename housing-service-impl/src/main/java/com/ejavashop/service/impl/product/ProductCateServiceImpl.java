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
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.seller.SellerManageCate;
import com.ejavashop.model.product.ProductCateModel;
import com.ejavashop.service.product.IProductCateService;
import com.ejavashop.vo.product.FrontProductCateVO;
import com.ejavashop.vo.product.ProductCateVO;

@Service(value = "productCateService")
public class ProductCateServiceImpl implements IProductCateService {
    private static Logger    log = LogManager.getLogger(ProductCateServiceImpl.class);

    @Resource
    private ProductCateModel productCateModel;

    @Override
    public ServiceResult<Boolean> saveProductCate(ProductCate productCate) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productCateModel.saveProductCate(productCate));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "ProductCateServiceImpl saveProductCate param:" + JSON.toJSONString(productCate));
            log.error("ProductCateServiceImpl saveProductCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProductCate(ProductCate productCate) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productCateModel.updateProductCate(productCate));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "ProductCateServiceImpl updateProductCate param:" + JSON.toJSONString(productCate));
            log.error("ProductCateServiceImpl updateProductCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> delProductCate(Integer productCateId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productCateModel.delProductCate(productCateId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl delProductCate productCateId:" + productCateId);
            log.error("ProductCateServiceImpl delProductCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ProductCate> getProductCateById(Integer productCateId) {
        ServiceResult<ProductCate> serviceResult = new ServiceResult<ProductCate>();
        try {
            serviceResult.setResult(productCateModel.getProductCateById(productCateId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl getProductCateById id:" + productCateId);
            log.error("ProductCateServiceImpl getProductCateById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductCateVO>> pageProductCate(Map<String, String> queryMap,
                                                              PagerInfo pager) {
        ServiceResult<List<ProductCateVO>> serviceResult = new ServiceResult<List<ProductCateVO>>();
        try {
            serviceResult.setResult(productCateModel.pageProductCate(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl pageProductCate queryMap:"
                      + JSON.toJSONString(queryMap) + " pager:" + JSON.toJSONString(pager));
            log.error("ProductCateServiceImpl pageProductCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductCate>> getByPid(Integer pid) {
        ServiceResult<List<ProductCate>> serviceResult = new ServiceResult<List<ProductCate>>();
        try {
            serviceResult.setResult(productCateModel.getByPid(pid));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl pageProductCate pid:" + pid);
            log.error("ProductCateServiceImpl pageProductCate exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductCate>> getCateBySellerId(Integer sellerId) {
        ServiceResult<List<ProductCate>> serviceResult = new ServiceResult<List<ProductCate>>();
        try {
            serviceResult.setResult(productCateModel.getCateBySellerId(sellerId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl getCateBySellerId sellerId:" + sellerId);
            log.error("ProductCateServiceImpl getCateBySellerId exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductCate>> getCateBySellerId(Integer sellerId, Integer pid) {
        ServiceResult<List<ProductCate>> serviceResult = new ServiceResult<List<ProductCate>>();

        try {
            serviceResult.setResult(productCateModel.getCateBySellerId(sellerId, pid));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl getCateBySellerId sellerId:" + sellerId);
            log.error("ProductCateServiceImpl getCateBySellerId exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<String> getCatePathStrById(Integer productCateId) {
        ServiceResult<String> serviceResult = new ServiceResult<String>();
        try {
            serviceResult.setResult(productCateModel.getCatePathStrById(productCateId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl getCatePathStrById productCateId:" + productCateId);
            log.error("ProductCateServiceImpl getCatePathStrById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public SellerManageCate getSellerManageCate(Integer id) {
        return productCateModel.getSellerManageCate(id);
    }

    @Override
    public Boolean updateSellerManageCate(SellerManageCate cate) {
        return productCateModel.updateSellerManageCate(cate);
    }

    @Override
    public ServiceResult<List<ProductCateVO>> getByPidAndSeller(Integer pid, Integer seller) {
        ServiceResult<List<ProductCateVO>> serviceResult = new ServiceResult<List<ProductCateVO>>();
        try {

            serviceResult.setResult(productCateModel.getByPidAndSeller(pid, seller));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductCateServiceImpl pageProductCate pid:" + pid);
            log.error("ProductCateServiceImpl pageProductCate exception:", e);
        }
        return serviceResult;
    }

    /**
     * 取得所有显示状态的商品分类
     * @param  map
     * @return
     */
    @Override
    public ServiceResult<List<FrontProductCateVO>> getProductCateList(Map<String, Object> queryMap) {
        ServiceResult<List<FrontProductCateVO>> serviceResult = new ServiceResult<List<FrontProductCateVO>>();
        try {
            serviceResult.setResult(productCateModel.getProductCateList(queryMap));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductCateService][getProductCateList]获取商品分类列表时发生异常:", e);
        }
        return serviceResult;
    }
}