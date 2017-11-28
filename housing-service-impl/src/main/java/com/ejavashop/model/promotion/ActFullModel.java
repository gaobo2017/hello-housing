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
import com.ejavashop.dao.promotion.read.full.ActFullReadDao;
import com.ejavashop.dao.promotion.write.full.ActFullWriteDao;
import com.ejavashop.dao.promotion.write.full.LogActFullWriteDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.entity.promotion.full.ActFull;
import com.ejavashop.entity.promotion.full.LogActFull;
import com.ejavashop.entity.seller.Seller;

@Component(value = "actFullModel")
public class ActFullModel {
    private static Logger                log = LogManager.getLogger(ActFullModel.class);

    @Resource
    private ActFullReadDao               actFullReadDao;
    @Resource
    private ActFullWriteDao              actFullWriteDao;
    @Resource
    private LogActFullWriteDao           logActFullWriteDao;
    @Resource
    private DataSourceTransactionManager promotionTransactionManager;
    @Resource
    private SellerReadDao                sellerReadDao;

    /**
     * 根据id取得满减活动
     * @param  actFullId
     * @return
     */
    public ActFull getActFullById(Integer actFullId) {
        return actFullReadDao.get(actFullId);
    }

    /**
     * 保存满减活动
     * @param  actFull
     * @return
     */
    public boolean saveActFull(ActFull actFull) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {
            Integer row = actFullWriteDao.insert(actFull);

            LogActFull logActFull = new LogActFull();
            logActFull.setOptType(ConstantsEJS.DATA_OPT_TYPE_C);
            logActFull.setOptUserId(actFull.getUpdateUserId());
            logActFull.setOptUserName(actFull.getUpdateUserName());
            logActFull.setOptTime(new Date());
            logActFull.setActFullId(actFull.getId());
            logActFullWriteDao.insert(logActFull);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("保存满减活动时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 更新满减活动
     * @param actFull
     * @return
     */
    public boolean updateActFull(ActFull actFull) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {

            ActFull actFullDb = actFullReadDao.get(actFull.getId());
            if (actFullDb.getStatus().intValue() != ActFull.STATUS_1
                && actFullDb.getStatus().intValue() != ActFull.STATUS_4) {
                throw new BusinessException("只能修改新建或审核失败的活动。");
            }
            if (actFull.getSellerId() == null
                || actFullDb.getSellerId().intValue() != actFull.getSellerId().intValue()) {
                throw new BusinessException("只能修改自己店铺的活动。");
            }

            Integer row = actFullWriteDao.update(actFull);

            LogActFull logActFull = new LogActFull();
            logActFull.setOptType(ConstantsEJS.DATA_OPT_TYPE_U);
            logActFull.setOptUserId(actFull.getUpdateUserId());
            logActFull.setOptUserName(actFull.getUpdateUserName());
            logActFull.setOptTime(new Date());
            logActFull.setActFullId(actFull.getId());
            logActFullWriteDao.insert(logActFull);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("修改满减活动时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 更新满减活动状态
     * @param actFull
     * @return
     */
    public boolean updateActFullStatus(ActFull actFull) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {
            Integer row = actFullWriteDao.updateStatus(actFull);

            LogActFull logActFull = new LogActFull();
            logActFull.setOptType(ConstantsEJS.DATA_OPT_TYPE_U);
            // 如果是审核的情况设定修改人为审核人
            if (actFull.getStatus().intValue() == ActFull.STATUS_3
                || actFull.getStatus().intValue() == ActFull.STATUS_4) {
                logActFull.setOptUserId(actFull.getAuditUserId());
                logActFull.setOptUserName(actFull.getAuditUserName());
            } else {
                logActFull.setOptUserId(actFull.getUpdateUserId());
                logActFull.setOptUserName(actFull.getUpdateUserName());
            }
            logActFull.setOptTime(new Date());
            logActFull.setActFullId(actFull.getId());
            logActFullWriteDao.insert(logActFull);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("修改满减活动时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 删除满减活动
     * @param actFull
     * @return
     */
    public boolean deleteActFull(Integer actFullId, Integer userId, String userName) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {

            ActFull actFull = actFullReadDao.get(actFullId);
            if (actFull.getStatus().intValue() != ActFull.STATUS_1
                && actFull.getStatus().intValue() != ActFull.STATUS_4) {
                throw new BusinessException("只能删除新建或审核失败的活动。");
            }

            LogActFull logActFull = new LogActFull();
            logActFull.setOptType(ConstantsEJS.DATA_OPT_TYPE_D);
            logActFull.setOptUserId(userId);
            logActFull.setOptUserName(userName);
            logActFull.setOptTime(new Date());
            logActFull.setActFullId(actFullId);
            logActFullWriteDao.insert(logActFull);

            Integer row = actFullWriteDao.delete(actFullId);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("删除满减活动时出现未知异常：" + e);
            throw e;
        }
    }

    public Integer getActFullsCount(Map<String, String> queryMap) {
        return actFullReadDao.getActFullsCount(queryMap);
    }

    public List<ActFull> getActFulls(Map<String, String> queryMap, Integer start, Integer size) {
        List<ActFull> actFulls = actFullReadDao.getActFulls(queryMap, start, size);
        if (actFulls != null && actFulls.size() > 0) {
            // 保存取到的商家信息，避免多次读取同一条数据，增加效率
            Map<Integer, Seller> map = new HashMap<Integer, Seller>();
            for (ActFull actFull : actFulls) {
                if (map.get(actFull.getSellerId()) != null) {
                    actFull.setSellerName(map.get(actFull.getSellerId()).getSellerName());
                } else {
                    Seller seller = sellerReadDao.get(actFull.getSellerId());
                    if (seller != null) {
                        map.put(seller.getId(), seller);
                        actFull.setSellerName(seller.getSellerName());
                    }
                }
            }
        }
        return actFulls;
    }

    /**
     * 根据商家ID取得满减活动（当前时间有效的活动，如果有多个，只取最新的一个）
     * 
     * @param sellerId
     * @param channel 渠道
     * @return
     */
    public ActFull getEffectiveActFull(Integer sellerId, Integer channel) {
        return actFullReadDao.getEffectiveActFull(sellerId, channel);
    }
}