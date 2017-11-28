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
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAttr;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.product.ProductNormAttrOpt;
import com.ejavashop.entity.product.ProductPicture;
import com.ejavashop.model.product.ProductModel;
import com.ejavashop.service.product.IProductService;

@Service(value = "productService")
public class ProductServiceImpl implements IProductService {
    private static Logger log = LogManager.getLogger(ProductServiceImpl.class);

    @Resource
    private ProductModel  productModel;

    @Override
    public ServiceResult<List<Product>> getByCateIdTop(Integer cateId, Integer limit) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
            serviceResult.setResult(productModel.getByCateIdTop(cateId, limit));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl getByCateIdTop cateId:" + cateId);
            log.error("ProductServiceImpl getByCateIdTop exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getSellerRecommendProducts(Integer sellerId, Integer size) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
            serviceResult.setResult(productModel.getSellerRecommendProducts(sellerId, size));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ProductService][getSellerRecommendProducts]获取商家推荐商品时出现异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getSellerNewProducts(Integer sellerId, Integer size) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
            serviceResult.setResult(productModel.getSellerNewProducts(sellerId, size));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ProductService][getSellerNewProducts]获取商家新品时出现异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getProductForSellerList(Integer sellerId, Integer sort,
                                                                Integer sellerCateId,
                                                                PagerInfo pager) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(productModel.getProductForSellerListCount(sellerId, sort,
                    sellerCateId));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(productModel.getProductForSellerList(sellerId, sort,
                sellerCateId, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ProductService][getProductForSellerList]查询店铺商品时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductService][getProductForSellerList]查询店铺商品时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getRecommendProducts(Integer size) {
        ServiceResult<List<Product>> result = new ServiceResult<List<Product>>();
        try {
            result.setResult(productModel.getRecommendProducts(size));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[ProductService][getIndexRecProduct]获取首页推荐商品时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductService][getIndexRecProduct]获取首页推荐商品时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> saveProduct(Product product,
                                              List<ProductPicture> productPictureList,
                                              List<ProductAttr> productAttrList) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productModel.saveProduct(product, productPictureList,
                productAttrList));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl saveProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl saveProduct exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProduct(Product product,
                                                List<ProductPicture> productPictureList,
                                                List<ProductAttr> productAttrList) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productModel.updateProduct(product, productPictureList,
                productAttrList));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl updateProduct exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> delProduct(Integer productId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productModel.delProduct(productId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl delProduct productId:" + productId);
            log.error("ProductServiceImpl delProduct exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Product> getProductById(Integer productId) {
        ServiceResult<Product> serviceResult = new ServiceResult<Product>();
        try {
            serviceResult.setResult(productModel.getProductById(productId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl getProductById id:" + productId);
            log.error("ProductServiceImpl getProductById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> pageProduct(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
            serviceResult.setResult(productModel.pageProduct(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl pageProduct queryMap:" + JSON.toJSONString(queryMap)
                      + " pager:" + JSON.toJSONString(pager));
            log.error("ProductServiceImpl pageProduct exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getProductsBySellerId(Integer sellerid) {
        ServiceResult<List<Product>> sr = new ServiceResult<List<Product>>();
        try {
            sr.setResult(productModel.getProductsBySellerId(sellerid));
        } catch (BusinessException e) {
            sr.setMessage(e.getMessage());
            sr.setSuccess(false);
        }
        return sr;
    }

    @Override
    public ServiceResult<Boolean> saveOrupdate(Product product,
                                               List<ProductPicture> productPictureList,
                                               List<ProductAttr> productAttrList,
                                               List<ProductGoods> productGoodsList,
                                               List<ProductNormAttrOpt> optlist) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {

            serviceResult.setResult(productModel.saveOrupdate(product, productPictureList,
                productAttrList, productGoodsList, optlist));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl saveProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl saveProduct exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> updateByIds(Map<String, Object> param, List<Integer> ids) {

        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(productModel.updateByIds(param, ids));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateByIds param1:" + JSON.toJSONString(param)
                      + System.lineSeparator() + "param2:" + JSON.toJSONString(ids));
            log.error("ProductServiceImpl updateByIds exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProductState(Integer productId, Integer state) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productModel.updateProductState(productId, state));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateProductState productId:" + productId);
            log.error("ProductServiceImpl updateProductState exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProductRecommend(Integer productId, Integer isTop) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productModel.updateProductRecommend(productId, isTop));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateProductRecommend productId:" + productId);
            log.error("ProductServiceImpl updateProductRecommend exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getProductsByIds(List<Integer> ids) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
            serviceResult.setResult(productModel.getProductsByIds(ids));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ProductServiceImpl][getProductsByIds] exception:", e);
        }
        return serviceResult;
    }

    /**
     * 查询最大的商品
     * @return
     * @see com.ejavashop.service.product.IProductService#getProductByMax()
     */
    @Override
    public ServiceResult<Product> getProductByMax() {
        ServiceResult<Product> serviceResult = new ServiceResult<Product>();
        try {
            serviceResult.setResult(productModel.getProductByMax());
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl getProductByMax");
            log.error("ProductServiceImpl getProductByMax exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProduct(Product product) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productModel.updateProduct(product) > 0);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateProduct exception:", e);
        }
        return serviceResult;
    }
}