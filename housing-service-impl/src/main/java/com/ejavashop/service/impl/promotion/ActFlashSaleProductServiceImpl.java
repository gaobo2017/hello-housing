package com.ejavashop.service.impl.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.flash.ActFlashSaleProduct;
import com.ejavashop.model.promotion.ActFlashSaleProductModel;
import com.ejavashop.service.promotion.IActFlashSaleProductService;

@Service(value = "actFlashSaleProductService")
public class ActFlashSaleProductServiceImpl implements IActFlashSaleProductService {

    private static Logger            log = LogManager
                                             .getLogger(ActFlashSaleProductServiceImpl.class);

    @Resource
    private ActFlashSaleProductModel actFlashSaleProductModel;

    /**
    * 根据id取得限时抢购活动商品表
    * @param  actFlashSaleProductId
    * @return
    */
    @Override
    public ServiceResult<ActFlashSaleProduct> getActFlashSaleProductById(Integer actFlashSaleProductId) {
        ServiceResult<ActFlashSaleProduct> result = new ServiceResult<ActFlashSaleProduct>();
        try {
            result.setResult(actFlashSaleProductModel
                .getActFlashSaleProductById(actFlashSaleProductId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleProductService][getActFlashSaleProductById]根据id["
                      + actFlashSaleProductId + "]取得限时抢购活动商品表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActFlashSaleProductService][getActFlashSaleProductById]根据id["
                      + actFlashSaleProductId + "]取得限时抢购活动商品表时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存限时抢购活动商品表
     * @param  actFlashSaleProduct
     * @return
     */
    @Override
    public ServiceResult<Boolean> saveActFlashSaleProduct(ActFlashSaleProduct actFlashSaleProduct) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFlashSaleProductModel.saveActFlashSaleProduct(actFlashSaleProduct));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleProductService][saveActFlashSaleProduct]保存限时抢购活动商品表时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActFlashSaleProductService][saveActFlashSaleProduct]保存限时抢购活动商品表时出现未知异常：",
                e);
        }
        return result;
    }

    /**
    * 更新限时抢购活动商品表
    * @param  actFlashSaleProduct
    * @return
    */
    @Override
    public ServiceResult<Boolean> updateActFlashSaleProduct(ActFlashSaleProduct actFlashSaleProduct) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFlashSaleProductModel
                .updateActFlashSaleProduct(actFlashSaleProduct));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleProductService][updateActFlashSaleProduct]更新限时抢购活动商品表时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IActFlashSaleProductService][updateActFlashSaleProduct]更新限时抢购活动商品表时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateActFlashSaleProductStatus(ActFlashSaleProduct actFlashSaleProduct) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFlashSaleProductModel
                .updateActFlashSaleProductStatus(actFlashSaleProduct));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleProductService][updateActFlashSaleProductStatus]更新限时抢购活动商品状态时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IActFlashSaleProductService][updateActFlashSaleProductStatus]更新限时抢购活动商品状态时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteActFlashSaleProduct(Integer actFlashSaleProductId,
                                                            Integer userId, String userName) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFlashSaleProductModel.deleteActFlashSaleProduct(
                actFlashSaleProductId, userId, userName));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleProductService][deleteActFlashSaleProduct]删除限时抢购活动商品时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActFlashSaleProductService][deleteActFlashSaleProduct]删除限时抢购活动商品时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteActFlashSaleProductForSeller(Integer actFlashSaleStageId,
                                                                     Integer sellerId,
                                                                     Integer productId,
                                                                     Integer userId, String userName) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFlashSaleProductModel.deleteActFlashSaleProductForSeller(
                actFlashSaleStageId, sellerId, productId, userId, userName));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleProductService][deleteActFlashSaleProductForSeller]删除限时抢购活动商品时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IActFlashSaleProductService][deleteActFlashSaleProductForSeller]删除限时抢购活动商品时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ActFlashSaleProduct>> getActFlashSaleProductsByStageId(Integer actFlashSaleStageId) {
        ServiceResult<List<ActFlashSaleProduct>> result = new ServiceResult<List<ActFlashSaleProduct>>();
        try {
            result.setResult(actFlashSaleProductModel
                .getActFlashSaleProductsByStageId(actFlashSaleStageId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleProductService][getActFlashSaleProductsByStageId]根据阶段ID获取活动商品时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IActFlashSaleProductService][getActFlashSaleProductsByStageId]根据阶段ID获取活动商品时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ActFlashSaleProduct>> getActFlashSaleProductsByStageIdAndSellerId(Integer actFlashSaleStageId,
                                                                                                Integer sellerId) {
        ServiceResult<List<ActFlashSaleProduct>> result = new ServiceResult<List<ActFlashSaleProduct>>();
        try {
            result.setResult(actFlashSaleProductModel.getActFlashSaleProductsByStageIdAndSellerId(
                actFlashSaleStageId, sellerId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleProductService][getActFlashSaleProductsByStageIdAndSellerId]根据阶段ID和商家ID获取活动商品时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IActFlashSaleProductService][getActFlashSaleProductsByStageIdAndSellerId]根据阶段ID和商家ID获取活动商品时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ActFlashSaleProduct>> getActFlashSaleProduct(Map<String, Object> param) {
        ServiceResult<List<ActFlashSaleProduct>> result = new ServiceResult<List<ActFlashSaleProduct>>();
        try {
            result.setResult(actFlashSaleProductModel.getActFlashSaleProduct(param));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleProductService][getActFlashSaleProduct]获取活动商品时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActFlashSaleProductService][getActFlashSaleProduct] 获取活动商品时出现未知异常：", e);
        }
        return result;
    }
}