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
import com.ejavashop.entity.product.ProductBrand;
import com.ejavashop.model.product.ProductBrandModel;
import com.ejavashop.service.product.IProductBrandService;

/**
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
@Service
public class ProductBrandServiceImpl implements IProductBrandService {
    private static Logger     log = LogManager.getLogger(ProductBrandServiceImpl.class);

    @Resource
    private ProductBrandModel productBrandModel;

    @Override
    public ServiceResult<Boolean> save(ProductBrand brand) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productBrandModel.save(brand));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param:" + JSON.toJSONString(brand));
            log.error("[BrandServiceImpl][save] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ProductBrand> getById(Integer id) {
        ServiceResult<ProductBrand> serviceResult = new ServiceResult<ProductBrand>();
        try {
            serviceResult.setResult(productBrandModel.getById(id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param:" + id);
            log.error("[BrandServiceImpl][getById] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductBrand>> getByIds(String ids) {
        ServiceResult<List<ProductBrand>> serviceResult = new ServiceResult<List<ProductBrand>>();
        try {
            serviceResult.setResult(productBrandModel.getByIds(ids));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param:" + ids);
            log.error("[BrandServiceImpl][getById] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> update(ProductBrand brand) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productBrandModel.update(brand));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param:" + JSON.toJSONString(brand));
            log.error("[BrandServiceImpl][audit] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductBrand>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<ProductBrand>> serviceResult = new ServiceResult<List<ProductBrand>>();
        try {
            serviceResult.setResult(productBrandModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[BrandServiceImpl][page] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ProductBrand>> getBrandByTypeId(Integer typeId) {
        ServiceResult<List<ProductBrand>> serviceResult = new ServiceResult<List<ProductBrand>>();
        try {
            serviceResult.setResult(productBrandModel.getBrandByTypeId(typeId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][getBrandByTypeId] typeId:" + typeId);
            log.error("[BrandServiceImpl][getBrandByTypeId] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> audit(Integer id, Integer state, Integer userId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productBrandModel.audit(id, state, userId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param:" + id);
            log.error("[BrandServiceImpl][audit] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productBrandModel.del(id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param:" + id);
            log.error("[BrandServiceImpl][del] exception:", e);
        }
        return serviceResult;
    }

    /**
     * 取出所有可用的品牌
     * @return
     * @see com.ejavashop.service.product.IProductBrandService#listNoPage()
     */
    @Override
    public ServiceResult<List<ProductBrand>> listNoPage() {
        ServiceResult<List<ProductBrand>> serviceResult = new ServiceResult<List<ProductBrand>>();
        try {
            serviceResult.setResult(productBrandModel.listNoPage());
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][getBrandByTypeId] exception:", e);
        }
        return serviceResult;
    }
}
