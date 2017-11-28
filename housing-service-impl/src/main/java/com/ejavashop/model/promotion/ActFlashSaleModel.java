package com.ejavashop.model.promotion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.TimeUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.promotion.read.flash.ActFlashSaleProductReadDao;
import com.ejavashop.dao.promotion.read.flash.ActFlashSaleReadDao;
import com.ejavashop.dao.promotion.read.flash.ActFlashSaleStageReadDao;
import com.ejavashop.dao.promotion.write.flash.ActFlashSaleStageWriteDao;
import com.ejavashop.dao.promotion.write.flash.ActFlashSaleWriteDao;
import com.ejavashop.dao.promotion.write.flash.LogActFlashSaleStageWriteDao;
import com.ejavashop.dao.promotion.write.flash.LogActFlashSaleWriteDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.entity.promotion.flash.ActFlashSale;
import com.ejavashop.entity.promotion.flash.ActFlashSaleProduct;
import com.ejavashop.entity.promotion.flash.ActFlashSaleStage;
import com.ejavashop.entity.promotion.flash.LogActFlashSale;

@Component(value = "actFlashSaleModel")
public class ActFlashSaleModel {

    private static Logger                log = LogManager.getLogger(ActFlashSaleModel.class);

    @Resource
    private ActFlashSaleReadDao          actFlashSaleReadDao;
    @Resource
    private ActFlashSaleWriteDao         actFlashSaleWriteDao;
    @Resource
    private ActFlashSaleStageReadDao     actFlashSaleStageReadDao;
    @Resource
    private ActFlashSaleStageWriteDao    actFlashSaleStageWriteDao;
    @Resource
    private ActFlashSaleProductReadDao   actFlashSaleProductReadDao;
    @Resource
    private ProductReadDao               productReadDao;
    @Resource
    private LogActFlashSaleWriteDao      logActFlashSaleWriteDao;
    @Resource
    private LogActFlashSaleStageWriteDao logActFlashSaleStageWriteDao;
    @Resource
    private DataSourceTransactionManager promotionTransactionManager;

    /**
     * 根据id取得限时抢购活动
     * @param  actFlashSaleId
     * @return
     */
    public ActFlashSale getActFlashSaleById(Integer actFlashSaleId) {
        ActFlashSale actFlashSale = actFlashSaleReadDao.get(actFlashSaleId);
        List<ActFlashSaleStage> byActFlashSaleId = actFlashSaleStageReadDao
            .getByActFlashSaleId(actFlashSaleId);
        actFlashSale.setStageList(byActFlashSaleId);
        return actFlashSale;
    }

    /**
     * 根据id、商家ID取得限时抢购活动，封装活动阶段以及阶段的活动商品
     * @param actFlashSaleId
     * @param sellerId
     * @return
     */
    public ActFlashSale getActFlashSaleByIdAndSellerId(Integer actFlashSaleId, Integer sellerId) {
        ActFlashSale actFlashSale = actFlashSaleReadDao.get(actFlashSaleId);
        // 封装活动阶段
        List<ActFlashSaleStage> stageList = actFlashSaleStageReadDao
            .getByActFlashSaleId(actFlashSaleId);
        actFlashSale.setStageList(stageList);
        if (stageList != null && stageList.size() > 0) {
            for (ActFlashSaleStage stage : stageList) {
                List<ActFlashSaleProduct> productList = null;
                // 封装活动阶段的活动商品信息
                if (sellerId != null && sellerId > 0) {
                    productList = actFlashSaleProductReadDao.getByStageIdAndSellerId(stage.getId(),
                        sellerId);
                } else {
                    productList = actFlashSaleProductReadDao.getByStageId(stage.getId());
                }
                if (productList != null && productList.size() > 0) {
                    // 活动商品封装商品信息
                    for (ActFlashSaleProduct stageProduct : productList) {
                        stageProduct.setProduct(productReadDao.get(stageProduct.getProductId()));
                    }
                }
                stage.setProductList(productList);
            }
        }
        return actFlashSale;
    }

    /**
     * 保存限时抢购活动
     * @param  actFlashSale
     * @return
     */
    public boolean saveActFlashSale(ActFlashSale actFlashSale) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {
            Integer row = actFlashSaleWriteDao.insert(actFlashSale);

            for (ActFlashSaleStage stage : actFlashSale.getStageList()) {
                stage.setActFlashSaleId(actFlashSale.getId());
            }

            actFlashSaleStageWriteDao.batchInsert(actFlashSale.getStageList());

            LogActFlashSale logActFlashSale = new LogActFlashSale();
            logActFlashSale.setOptType(ConstantsEJS.DATA_OPT_TYPE_C);
            logActFlashSale.setOptUserId(actFlashSale.getUpdateUserId());
            logActFlashSale.setOptUserName(actFlashSale.getUpdateUserName());
            logActFlashSale.setOptTime(new Date());
            logActFlashSale.setActFlashSaleId(actFlashSale.getId());
            logActFlashSaleWriteDao.insert(logActFlashSale);

            logActFlashSaleStageWriteDao.insert(actFlashSale.getId());

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("保存限时抢购活动时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 更新限时抢购活动
     * @param actFlashSale
     * @return
     */
    public boolean updateActFlashSale(ActFlashSale actFlashSale) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {

            ActFlashSale actFlashSaleDb = actFlashSaleReadDao.get(actFlashSale.getId());
            if (actFlashSaleDb.getStatus().intValue() != ActFlashSale.STATUS_1) {
                throw new BusinessException("只能修改新建状态的活动。");
            }

            Integer row = actFlashSaleWriteDao.update(actFlashSale);

            for (ActFlashSaleStage stage : actFlashSale.getStageList()) {
                stage.setActFlashSaleId(actFlashSale.getId());
            }

            // 先删除阶段
            actFlashSaleStageWriteDao.deleteByActFlashSaleId(actFlashSale.getId());
            // 再添加阶段
            actFlashSaleStageWriteDao.batchInsert(actFlashSale.getStageList());

            LogActFlashSale logActFlashSale = new LogActFlashSale();
            logActFlashSale.setOptType(ConstantsEJS.DATA_OPT_TYPE_U);
            logActFlashSale.setOptUserId(actFlashSale.getUpdateUserId());
            logActFlashSale.setOptUserName(actFlashSale.getUpdateUserName());
            logActFlashSale.setOptTime(new Date());
            logActFlashSale.setActFlashSaleId(actFlashSale.getId());
            logActFlashSaleWriteDao.insert(logActFlashSale);

            logActFlashSaleStageWriteDao.insert(actFlashSale.getId());

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("修改限时抢购活动时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 更新限时抢购活动状态
     * @param actFlashSale
     * @return
     */
    public boolean updateActFlashSaleStatus(ActFlashSale actFlashSale) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {
            //            ActFlashSale actFlashSaleOld = actFlashSaleReadDao.get(actFlashSale.getId());
            if (actFlashSale.getStatus().intValue() == ActFlashSale.STATUS_5) {
                int count = actFlashSaleReadDao.countByActDateAndStatus(actFlashSale.getActDate(),
                    ActFlashSale.STATUS_5);
                if (count > 0) {
                    throw new BusinessException("一天只能上架一个活动，已经有上架的活动，不能进行上架操作。");
                }
            }
            Integer row = actFlashSaleWriteDao.updateStatus(actFlashSale);

            LogActFlashSale logActFlashSale = new LogActFlashSale();
            logActFlashSale.setOptType(ConstantsEJS.DATA_OPT_TYPE_U);
            logActFlashSale.setOptUserId(actFlashSale.getUpdateUserId());
            logActFlashSale.setOptUserName(actFlashSale.getUpdateUserName());
            logActFlashSale.setOptTime(new Date());
            logActFlashSale.setActFlashSaleId(actFlashSale.getId());
            logActFlashSaleWriteDao.insert(logActFlashSale);

            logActFlashSaleStageWriteDao.insert(actFlashSale.getId());

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("修改限时抢购活动时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 删除限时抢购活动
     * @param actFlashSale
     * @return
     */
    public boolean deleteActFlashSale(Integer actFlashSaleId, Integer userId, String userName) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {

            ActFlashSale actFlashSale = actFlashSaleReadDao.get(actFlashSaleId);
            if (actFlashSale.getStatus().intValue() != ActFlashSale.STATUS_1) {
                throw new BusinessException("只能删除新建状态的活动。");
            }

            LogActFlashSale logActFlashSale = new LogActFlashSale();
            logActFlashSale.setOptType(ConstantsEJS.DATA_OPT_TYPE_D);
            logActFlashSale.setOptUserId(userId);
            logActFlashSale.setOptUserName(userName);
            logActFlashSale.setOptTime(new Date());
            logActFlashSale.setActFlashSaleId(actFlashSaleId);
            logActFlashSaleWriteDao.insert(logActFlashSale);
            logActFlashSaleStageWriteDao.insert(actFlashSaleId);

            Integer row = actFlashSaleWriteDao.delete(actFlashSaleId);
            actFlashSaleStageWriteDao.deleteByActFlashSaleId(actFlashSaleId);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("删除限时抢购活动时出现未知异常：" + e);
            throw e;
        }
    }

    public Integer getActFlashSalesCount(Map<String, String> queryMap) {
        return actFlashSaleReadDao.getActFlashSalesCount(queryMap);
    }

    public List<ActFlashSale> getActFlashSales(Map<String, String> queryMap, Integer start,
                                               Integer size) {
        List<ActFlashSale> actFlashSales = actFlashSaleReadDao.getActFlashSales(queryMap, start,
            size);
        return actFlashSales;
    }

    /**
     * 根据活动日期、渠道取得限时抢购活动（上架的活动，如果有多个，只取最新的一个）
     * @param actDate 日期的时分秒都设定成0，如2016-05-14 00:00:00
     * @param channel
     * @return
     */
    public ActFlashSale getEffectiveActFlashSale(Date actDate, Integer channel) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(actDate);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        String actDateStr = TimeUtil.getDateTimeString(calendar.getTime());
        ActFlashSale actFlashSale = actFlashSaleReadDao.getEffectiveActFlashSale(actDateStr,
            channel);

        if (actFlashSale != null) {
            List<ActFlashSaleStage> byActFlashSaleId = actFlashSaleStageReadDao
                .getByActFlashSaleId(actFlashSale.getId());
            actFlashSale.setStageList(byActFlashSaleId);
        }
        return actFlashSale;

    }

    /**
     * 根据商品ID、渠道取得当前时间点的该商品参加的限时抢购活动、阶段、活动商品信息（上架的活动，如果有多个，只取最新的一个）
     * @param productId
     * @param channel
     * @return
     */
    public ActFlashSale getCurrEffectiveActFlashSale(Integer productId, Integer channel) {
        Calendar calendar = Calendar.getInstance();
        Integer currHour = calendar.get(Calendar.HOUR_OF_DAY);
        String actDate = TimeUtil.getToday() + " 00:00:00";
        // 取当天有效的活动
        ActFlashSale actFlashSale = actFlashSaleReadDao.getEffectiveActFlashSale(actDate, channel);
        // 取当前有效活动的时间段信息
        if (actFlashSale != null) {
            // 如果有活动取阶段
            ActFlashSaleStage stageByTime = actFlashSaleStageReadDao
                .getStageByTime(actFlashSale.getId(), currHour);
            if (stageByTime != null) {
                // 如果阶段不空，继续取活动商品
                ActFlashSaleProduct actFlashSaleProduct = actFlashSaleProductReadDao
                    .getByStageIdAndProductId(stageByTime.getId(), productId);
                if (actFlashSaleProduct != null) {
                    // 如果商品不为空，则返回活动信息
                    List<ActFlashSaleStage> stageList = new ArrayList<ActFlashSaleStage>(1);
                    stageList.add(stageByTime);
                    actFlashSale.setStageList(stageList);
                    List<ActFlashSaleProduct> productList = new ArrayList<ActFlashSaleProduct>(1);
                    productList.add(actFlashSaleProduct);
                    stageByTime.setProductList(productList);
                    return actFlashSale;
                } else {
                    // 如果为空，返回null
                    return null;
                }
            } else {
                // 如果阶段为空，直接返回null
                return null;
            }
        } else {
            // 如果没有活动，直接返回null
            return null;
        }
    }

    /**
     * 根据商品ID、渠道取得当天该商品参加的限时抢购活动、阶段、活动商品信息（上架的活动，如果有多个，只取最新的一个）
     * @param productId
     * @param channel
     * @return
     */
    public ActFlashSale getTodayEffectiveActFlashSale(Integer productId, Integer channel) {
        String actDate = TimeUtil.getToday() + " 00:00:00";
        // 取当天有效的活动
        ActFlashSale actFlashSale = actFlashSaleReadDao.getEffectiveActFlashSale(actDate, channel);
        // 取当前有效活动的时间段信息
        if (actFlashSale != null) {
            // 如果有活动，根据商品ID和活动ID获取活动商品
            List<ActFlashSaleProduct> actProList = actFlashSaleProductReadDao
                .getByActFlashSaleIdAndProductId(actFlashSale.getId(), productId);
            // 如果活动商品为空，则返回null
            if (actProList != null && actProList.size() > 0) {
                List<ActFlashSaleStage> stageList = new ArrayList<ActFlashSaleStage>();
                // 循环商品，取阶段
                for (ActFlashSaleProduct actProduct : actProList) {
                    // 获取商品对应的阶段信息，并把商品信息存入阶段对象
                    ActFlashSaleStage actFlashSaleStage = actFlashSaleStageReadDao
                        .get(actProduct.getActFlashSaleStageId());
                    if (actFlashSaleStage != null) {
                        List<ActFlashSaleProduct> stageProList = new ArrayList<ActFlashSaleProduct>();
                        stageProList.add(actProduct);
                        actFlashSaleStage.setProductList(stageProList);
                        stageList.add(actFlashSaleStage);
                    }
                }

                if (stageList.size() > 0) {
                    actFlashSale.setStageList(stageList);
                } else {
                    // 根据活动没有取到活动阶段信息，则返回null
                    return null;
                }
            } else {
                return null;
            }
        } else {
            // 如果没有活动，直接返回null
            return null;
        }

        return actFlashSale;
    }

    //    /**
    //     * 取得指定日期的促销活动
    //     * @param date
    //     * @return
    //     */
    //    public ActFlashSale getByActDate(Date actDate) {
    //        ActFlashSale actFlashSale = actFlashSaleReadDao
    //            .getByActDate(actDate, ActFlashSale.STATUS_5);
    //        if (actFlashSale != null) {
    //            List<ActFlashSaleStage> byActFlashSaleId = actFlashSaleStageReadDao
    //                .getByActFlashSaleId(actFlashSale.getId());
    //            actFlashSale.setStageList(byActFlashSaleId);
    //        }
    //        return actFlashSale;
    //    }

    public List<ActFlashSaleStage> getActFlashSaleStage(Integer actFlashSaleId) {
        return actFlashSaleReadDao.getActFlashSaleStage(actFlashSaleId);
    }

}