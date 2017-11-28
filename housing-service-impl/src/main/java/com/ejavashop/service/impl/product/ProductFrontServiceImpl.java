package com.ejavashop.service.impl.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.Product;
import com.ejavashop.model.product.ProductFrontModel;
import com.ejavashop.service.product.IProductFrontService;

@Service(value = "productFrontService")
public class ProductFrontServiceImpl implements IProductFrontService {
    private static Logger     log = LogManager.getLogger(ProductFrontServiceImpl.class);

    @Resource
    private ProductFrontModel productFrontModel;

    /**
     * 根据分类列表页查询商品
     * @param cateId
     * @param mapCondition
     * @param stack
     */
    @Override
    public void getProducts(Integer cateId, Map<String, Object> mapCondition,
                            Map<String, Object> stack) {
        try {
            productFrontModel.getProducts(cateId, mapCondition, stack);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ProductFrontService][getProducts]根据商品分类查询商品时发生异常:", e);
        }
    }

    /**
     * 根据分类查询列表页推荐的头部信息
     * @param cateId
     * @return
     * @see com.ejavashop.service.product.IProductFrontService#getProductListByCateIdTop(int)
     */
    @Override
    public ServiceResult<List<Product>> getProductListByCateIdTop(int cateId) {
        ServiceResult<List<Product>> result = new ServiceResult<List<Product>>();
        try {
            result.setResult(productFrontModel.getProductListByCateIdTop(cateId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[ProductFrontService][getProductListByCateIdTop]根据商户id取得列表页头部推荐商品 及最新商品时发生异常:", e);
        }
        return result;
    }

    /**
     * 根据分类查询列表页推荐的左边信息
     * @param cateId
     * @return
     * @see com.ejavashop.service.product.IProductFrontService#getProductListByCateIdLeft(int)
     */
    @Override
    public ServiceResult<List<Product>> getProductListByCateIdLeft(int cateId) {
        ServiceResult<List<Product>> result = new ServiceResult<List<Product>>();
        try {
            result.setResult(productFrontModel.getProductListByCateIdLeft(cateId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[ProductFrontService][getProductListByCateIdTop]根据商户id取得列表页左侧推荐商品 及最新商品时发生异常:", e);
        }
        return result;
    }

    /**
     * 查询二级分类下三级分类的商品
     * @param cateId
     * @return
     * @see com.ejavashop.service.product.IProductFrontService#getProductListByCateId2(int)
     */
    @Override
    public ServiceResult<List<Product>> getProductListByCateId2(int cateId) {
        ServiceResult<List<Product>> result = new ServiceResult<List<Product>>();
        try {
            result.setResult(productFrontModel.getProductListByCateId2(cateId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductFrontService][getProductListByCateId2]查询二级分类下三级分类的商品发生异常:", e);
        }
        return result;
    }

    /**
     * 搜索页面推荐商品信息头部
     * @return
     * @see com.ejavashop.service.product.IProductFrontService#getProductSearchByTop()
     */
    @Override
    public ServiceResult<List<Product>> getProductSearchByTop() {
        ServiceResult<List<Product>> result = new ServiceResult<List<Product>>();
        try {
            result.setResult(productFrontModel.getProductSearchByTop());
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductFrontService][getProductSearchByTop]查询搜索页面推荐商品信息头部发生异常:", e);
        }
        return result;
    }

    /**
     * 搜索页面推荐商品信息左部
     * @return
     * @see com.ejavashop.service.product.IProductFrontService#getProductSearchByLeft()
     */
    @Override
    public ServiceResult<List<Product>> getProductSearchByLeft() {
        ServiceResult<List<Product>> result = new ServiceResult<List<Product>>();
        try {
            result.setResult(productFrontModel.getProductSearchByLeft());
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductFrontService][getProductSearchByLeft]查询搜搜索页面推荐商品信息左部发生异常:", e);
        }
        return result;
    }

    /**
     * 查询商家列表页 商品信息
     * @param start 
     * @param size
     * @param cateId 商家分类
     * @param sellerId 商家ID
     * @param sort 排序
    * @return
    * @see com.ejavashop.service.product.IProductFrontService#getProductListBySellerCateId(com.ejavashop.core.PaginationUtil, java.lang.Integer, java.lang.Integer)
    */
    @Override
    public ServiceResult<List<Product>> getProductListBySellerCateId(int start, int size,
                                                                     int cateId, int sellerId,
                                                                     int sort) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
            List<Product> returnList = productFrontModel.getProductListBySellerCateId(start, size,
                cateId, sellerId, sort);
            serviceResult.setResult(returnList);
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[ProductFrontService][getProductListBySellerCateId]查询商家列表页 商品信息时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductFrontService][getProductListBySellerCateId]查询商家列表页 商品信息时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 根据商家商品分类统计商家商品
     * @param cateId 商家分类
     * @param sellerId 商家ID
     * @return
     * @see com.ejavashop.service.product.IProductFrontService#getProductListBySellerCateIdCount(int, int)
     */
    @Override
    public ServiceResult<Integer> getProductListBySellerCateIdCount(int cateId, int sellerId) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            Integer count = productFrontModel.getProductListBySellerCateIdCount(cateId, sellerId);
            serviceResult.setResult(count);
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[ProductFrontService][getProductListBySellerCateIdCount]根据商家商品分类统计商家商品时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[ProductFrontService][getProductListBySellerCateIdCount]根据商家商品分类统计商家商品时发生异常:", e);
        }
        return serviceResult;
    }

}
