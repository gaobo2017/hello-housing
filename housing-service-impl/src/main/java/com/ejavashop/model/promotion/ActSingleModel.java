package com.ejavashop.model.promotion;

import java.util.Date;
import java.util.HashMap;
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
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.promotion.read.single.ActSingleReadDao;
import com.ejavashop.dao.promotion.write.single.ActSingleWriteDao;
import com.ejavashop.dao.promotion.write.single.LogActSingleWriteDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.entity.promotion.single.ActSingle;
import com.ejavashop.entity.promotion.single.LogActSingle;
import com.ejavashop.entity.seller.Seller;

@Component(value = "actSingleModel")
public class ActSingleModel {

    private static Logger                log = LogManager.getLogger(ActSingleModel.class);

    @Resource
    private ActSingleReadDao             actSingleReadDao;
    @Resource
    private ActSingleWriteDao            actSingleWriteDao;
    @Resource
    private LogActSingleWriteDao         logActSingleWriteDao;
    @Resource
    private DataSourceTransactionManager promotionTransactionManager;
    @Resource
    private SellerReadDao                sellerReadDao;

    /**
     * 根据id取得单品立减活动
     * @param  actSingleId
     * @return
     */
    public ActSingle getActSingleById(Integer actSingleId) {
        return actSingleReadDao.get(actSingleId);
    }

    /**
     * 保存单品立减活动
     * @param  actSingle
     * @return
     */
    public boolean saveActSingle(ActSingle actSingle) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {
            Integer row = actSingleWriteDao.insert(actSingle);

            LogActSingle logActSingle = new LogActSingle();
            logActSingle.setOptType(ConstantsEJS.DATA_OPT_TYPE_C);
            logActSingle.setOptUserId(actSingle.getUpdateUserId());
            logActSingle.setOptUserName(actSingle.getUpdateUserName());
            logActSingle.setOptTime(new Date());
            logActSingle.setActSingleId(actSingle.getId());
            logActSingleWriteDao.insert(logActSingle);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("保存单品立减活动时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 更新单品立减活动
     * @param actSingle
     * @return
     */
    public boolean updateActSingle(ActSingle actSingle) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {

            ActSingle actSingleDb = actSingleReadDao.get(actSingle.getId());
            if (actSingleDb.getStatus().intValue() != ActSingle.STATUS_1
                && actSingleDb.getStatus().intValue() != ActSingle.STATUS_4) {
                throw new BusinessException("只能修改新建或审核失败的活动。");
            }
            if (actSingle.getSellerId() == null
                || actSingleDb.getSellerId().intValue() != actSingle.getSellerId().intValue()) {
                throw new BusinessException("只能修改自己店铺的活动。");
            }

            Integer row = actSingleWriteDao.update(actSingle);

            LogActSingle logActSingle = new LogActSingle();
            logActSingle.setOptType(ConstantsEJS.DATA_OPT_TYPE_U);
            logActSingle.setOptUserId(actSingle.getUpdateUserId());
            logActSingle.setOptUserName(actSingle.getUpdateUserName());
            logActSingle.setOptTime(new Date());
            logActSingle.setActSingleId(actSingle.getId());
            logActSingleWriteDao.insert(logActSingle);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("修改单品立减活动时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 更新单品立减活动状态
     * @param actSingle
     * @return
     */
    public boolean updateActSingleStatus(ActSingle actSingle) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {
            Integer row = actSingleWriteDao.updateStatus(actSingle);

            LogActSingle logActSingle = new LogActSingle();
            logActSingle.setOptType(ConstantsEJS.DATA_OPT_TYPE_U);
            // 如果是审核的情况设定修改人为审核人
            if (actSingle.getStatus().intValue() == ActSingle.STATUS_3
                || actSingle.getStatus().intValue() == ActSingle.STATUS_4) {
                logActSingle.setOptUserId(actSingle.getAuditUserId());
                logActSingle.setOptUserName(actSingle.getAuditUserName());
            } else {
                logActSingle.setOptUserId(actSingle.getUpdateUserId());
                logActSingle.setOptUserName(actSingle.getUpdateUserName());
            }
            logActSingle.setOptTime(new Date());
            logActSingle.setActSingleId(actSingle.getId());
            logActSingleWriteDao.insert(logActSingle);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("修改单品立减活动时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 删除单品立减活动
     * @param actSingle
     * @return
     */
    public boolean deleteActSingle(Integer actSingleId, Integer userId, String userName) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {

            ActSingle actSingle = actSingleReadDao.get(actSingleId);
            if (actSingle.getStatus().intValue() != ActSingle.STATUS_1
                && actSingle.getStatus().intValue() != ActSingle.STATUS_4) {
                throw new BusinessException("只能删除新建或审核失败的活动。");
            }

            LogActSingle logActSingle = new LogActSingle();
            logActSingle.setOptType(ConstantsEJS.DATA_OPT_TYPE_D);
            logActSingle.setOptUserId(userId);
            logActSingle.setOptUserName(userName);
            logActSingle.setOptTime(new Date());
            logActSingle.setActSingleId(actSingleId);
            logActSingleWriteDao.insert(logActSingle);

            Integer row = actSingleWriteDao.delete(actSingleId);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("删除满减活动时出现未知异常：" + e);
            throw e;
        }
    }

    public Integer getActSinglesCount(Map<String, String> queryMap) {
        return actSingleReadDao.getActSinglesCount(queryMap);
    }

    public List<ActSingle> getActSingles(Map<String, String> queryMap, Integer start,
                                         Integer size) {
        List<ActSingle> actSingles = actSingleReadDao.getActSingles(queryMap, start, size);

        if (actSingles != null && actSingles.size() > 0) {
            // 保存取到的商家信息，避免多次读取同一条数据，增加效率
            Map<Integer, Seller> map = new HashMap<Integer, Seller>();
            for (ActSingle actSingle : actSingles) {
                if (map.get(actSingle.getSellerId()) != null) {
                    actSingle.setSellerName(map.get(actSingle.getSellerId()).getSellerName());
                } else {
                    Seller seller = sellerReadDao.get(actSingle.getSellerId());
                    if (seller != null) {
                        map.put(seller.getId(), seller);
                        actSingle.setSellerName(seller.getSellerName());
                    }
                }
            }
        }
        return actSingles;
    }

    /**
     * 根据商家ID、渠道、商品ID取得单品立减活动（当前时间有效的活动，如果有多个，只取最新的一个）
     * 
     * @param sellerId
     * @param channel
     * @param productId
     * @return
     */
    public ActSingle getEffectiveActSingle(Integer sellerId, Integer channel, Integer productId) {
        // 把商品ID前后加,传入sql
        String productIdStr = "";
        if (productId != null) {
            productIdStr = "," + productId + ",";
        }
        return actSingleReadDao.getEffectiveActSingle(sellerId, channel, productIdStr);
    }
}