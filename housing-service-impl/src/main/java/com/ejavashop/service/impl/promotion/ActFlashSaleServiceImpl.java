package com.ejavashop.service.impl.promotion;

import java.util.Date;
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
import com.ejavashop.entity.promotion.flash.ActFlashSale;
import com.ejavashop.entity.promotion.flash.ActFlashSaleStage;
import com.ejavashop.model.promotion.ActFlashSaleModel;
import com.ejavashop.service.promotion.IActFlashSaleService;

@Service(value = "actFlashSaleService")
public class ActFlashSaleServiceImpl implements IActFlashSaleService {
    private static Logger     log = LogManager.getLogger(ActFlashSaleServiceImpl.class);

    @Resource
    private ActFlashSaleModel actFlashSaleModel;

    /**
    * 根据id取得限时抢购活动表
    * @param  actFlashSaleId
    * @return
    */
    @Override
    public ServiceResult<ActFlashSale> getActFlashSaleById(Integer actFlashSaleId) {
        ServiceResult<ActFlashSale> result = new ServiceResult<ActFlashSale>();
        try {
            result.setResult(actFlashSaleModel.getActFlashSaleById(actFlashSaleId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleService][getActFlashSaleById]根据id[" + actFlashSaleId
                      + "]取得限时抢购活动表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActFlashSaleService][getActFlashSaleById]根据id[" + actFlashSaleId
                      + "]取得限时抢购活动表时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<ActFlashSale> getActFlashSaleByIdAndSellerId(Integer actFlashSaleId,
                                                                      Integer sellerId) {
        ServiceResult<ActFlashSale> result = new ServiceResult<ActFlashSale>();
        try {
            result.setResult(
                actFlashSaleModel.getActFlashSaleByIdAndSellerId(actFlashSaleId, sellerId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleService][getActFlashSaleByIdAndSellerId]根据id[" + actFlashSaleId
                      + "]取得限时抢购活动表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActFlashSaleService][getActFlashSaleByIdAndSellerId]根据id[" + actFlashSaleId
                      + "]取得限时抢购活动表时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 保存限时抢购活动表
     * @param  actFlashSale
     * @return
     */
    @Override
    public ServiceResult<Boolean> saveActFlashSale(ActFlashSale actFlashSale) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFlashSaleModel.saveActFlashSale(actFlashSale));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleService][saveActFlashSale]保存限时抢购活动表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActFlashSaleService][saveActFlashSale]保存限时抢购活动表时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 更新限时抢购活动表
     * @param  actFlashSale
     * @return
     */
    @Override
    public ServiceResult<Boolean> updateActFlashSale(ActFlashSale actFlashSale) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFlashSaleModel.updateActFlashSale(actFlashSale));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IActFlashSaleService][updateActFlashSale]更新限时抢购活动表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActFlashSaleService][updateActFlashSale]更新限时抢购活动表时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateActFlashSaleStatus(ActFlashSale actFlashSale) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actFlashSaleModel.updateActFlashSaleStatus(actFlashSale));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleService][updateActFlashSaleStatus]更新限时抢购活动状态时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActFlashSaleService][updateActFlashSaleStatus]更新限时抢购活动状态时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteActFlashSale(Integer actFlashSaleId, Integer userId,
                                                     String userName) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result
                .setResult(actFlashSaleModel.deleteActFlashSale(actFlashSaleId, userId, userName));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IActFlashSaleService][deleteActFlashSale]删除限时抢购活动时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActFlashSaleService][deleteActFlashSale]删除限时抢购活动时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ActFlashSale>> getActFlashSales(Map<String, String> queryMap,
                                                              PagerInfo pager) {
        ServiceResult<List<ActFlashSale>> serviceResult = new ServiceResult<List<ActFlashSale>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(actFlashSaleModel.getActFlashSalesCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(actFlashSaleModel.getActFlashSales(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[IActFlashSaleService][getActFlashSales]根据条件取得限时抢购活动时出现异常：" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActFlashSaleService][getActFlashSales]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[IActFlashSaleService][getActFlashSales]根据条件取得限时抢购活动时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ActFlashSale> getEffectiveActFlashSale(Date actDate, Integer channel) {
        ServiceResult<ActFlashSale> result = new ServiceResult<ActFlashSale>();
        try {
            result.setResult(actFlashSaleModel.getEffectiveActFlashSale(actDate, channel));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleService][getEffectiveActFlashSale]根据活动日期、渠道取得限时抢购活动时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActFlashSaleService][getEffectiveActFlashSale]根据活动日期、渠道取得限时抢购活动时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<ActFlashSale> getCurrEffectiveActFlashSale(Integer productId,
                                                                    Integer channel) {
        ServiceResult<ActFlashSale> result = new ServiceResult<ActFlashSale>();
        try {
            result.setResult(actFlashSaleModel.getCurrEffectiveActFlashSale(productId, channel));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IActFlashSaleService][getCurrEffectiveActFlashSale]根据商品ID、渠道取得当前时间点的该商品参加的限时抢购活动、阶段、活动商品信息时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IActFlashSaleService][getCurrEffectiveActFlashSale]根据商品ID、渠道取得当前时间点的该商品参加的限时抢购活动、阶段、活动商品信息时出现未知异常：",
                e);
        }
        return result;
    }

    //    /**
    //     * 取得指定日期的促销活动
    //     * @param actDate
    //     * @return
    //     */
    //    @Override
    //    public ServiceResult<ActFlashSale> getByActDate(Date actDate) {
    //        ServiceResult<ActFlashSale> result = new ServiceResult<ActFlashSale>();
    //        try {
    //            result.setResult(actFlashSaleModel.getByActDate(actDate));
    //        } catch (BusinessException e) {
    //            result.setSuccess(false);
    //            result.setMessage(e.getMessage());
    //            log.error("[IActFlashSaleService][getByActDate]取得指定日期的促销活动时出现未知异常：" + e.getMessage());
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
    //                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
    //            log.error("[IActFlashSaleService][getByActDate]取得指定日期的促销活动时出现未知异常：", e);
    //        }
    //        return result;
    //    }

    @Override
    public ServiceResult<List<ActFlashSaleStage>> getActFlashSaleStage(Integer actFlashSaleId) {
        ServiceResult<List<ActFlashSaleStage>> result = new ServiceResult<List<ActFlashSaleStage>>();
        try {
            result.setResult(actFlashSaleModel.getActFlashSaleStage(actFlashSaleId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IActFlashSaleService][ActFlashSaleStage]取得活动阶段时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActFlashSaleService][ActFlashSaleStage]取得活动阶段时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<ActFlashSale> getTodayEffectiveActFlashSale(Integer productId,
                                                                     Integer channel) {
        ServiceResult<ActFlashSale> result = new ServiceResult<ActFlashSale>();
        try {
            result.setResult(actFlashSaleModel.getTodayEffectiveActFlashSale(productId, channel));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IActFlashSaleService][getTodayEffectiveActFlashSale]根据商品ID、渠道取得当天该商品参加的限时抢购活动、阶段、活动商品信息时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IActFlashSaleService][getTodayEffectiveActFlashSale]根据商品ID、渠道取得当天该商品参加的限时抢购活动、阶段、活动商品信息时出现未知异常：",
                e);
        }
        return result;
    }

}