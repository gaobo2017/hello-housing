package com.ejavashop.model.promotion;

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
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.promotion.read.flash.ActFlashSaleProductReadDao;
import com.ejavashop.dao.promotion.read.flash.ActFlashSaleStageReadDao;
import com.ejavashop.dao.promotion.write.flash.ActFlashSaleProductWriteDao;
import com.ejavashop.dao.promotion.write.flash.LogActFlashSaleProductWriteDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.flash.ActFlashSaleProduct;
import com.ejavashop.entity.promotion.flash.ActFlashSaleStage;
import com.ejavashop.entity.promotion.flash.LogActFlashSaleProduct;
import com.ejavashop.entity.seller.Seller;

@Component(value = "actFlashSaleProductModel")
public class ActFlashSaleProductModel {

    private static Logger                  log = LogManager
        .getLogger(ActFlashSaleProductModel.class);

    @Resource
    private ActFlashSaleProductReadDao     actFlashSaleProductReadDao;
    @Resource
    private ActFlashSaleProductWriteDao    actFlashSaleProductWriteDao;
    @Resource
    private ActFlashSaleStageReadDao       actFlashSaleStageReadDao;
    @Resource
    private LogActFlashSaleProductWriteDao logActFlashSaleProductWriteDao;
    @Resource
    private DataSourceTransactionManager   promotionTransactionManager;
    @Resource
    private ProductReadDao                 productReadDao;
    @Resource
    private SellerReadDao                  sellerReadDao;

    /**
     * 根据id取得限时抢购活动商品
     * @param  actFlashSaleProductId
     * @return
     */
    public ActFlashSaleProduct getActFlashSaleProductById(Integer actFlashSaleProductId) {
        return actFlashSaleProductReadDao.get(actFlashSaleProductId);
    }

    /**
     * 保存限时抢购活动商品，如果商品存在执行更新操作
     * @param  actFlashSaleProduct
     * @return
     */
    public boolean saveActFlashSaleProduct(ActFlashSaleProduct actFlashSaleProduct) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {
            // 先查询数据库，如果存在则更新，不存在则新增
            ActFlashSaleProduct stageProduct = actFlashSaleProductWriteDao.getForSeller(
                actFlashSaleProduct.getActFlashSaleStageId(), actFlashSaleProduct.getSellerId(),
                actFlashSaleProduct.getProductId());

            Integer row = 0;

            LogActFlashSaleProduct logActFlashSaleProduct = new LogActFlashSaleProduct();

            // 如果stageProduct是空，则是新增
            if (stageProduct == null) {
                row = actFlashSaleProductWriteDao.insert(actFlashSaleProduct);
                logActFlashSaleProduct.setOptType(ConstantsEJS.DATA_OPT_TYPE_C);
                logActFlashSaleProduct.setActFlashSaleProductId(actFlashSaleProduct.getId());
            } else {
                stageProduct.setPrice(actFlashSaleProduct.getPrice());
                stageProduct.setStock(actFlashSaleProduct.getStock());
                stageProduct.setUpdateUserId(actFlashSaleProduct.getUpdateUserId());
                stageProduct.setUpdateUserName(actFlashSaleProduct.getUpdateUserName());
                stageProduct.setUpdateTime(new Date());
                row = actFlashSaleProductWriteDao.update(stageProduct);

                logActFlashSaleProduct.setOptType(ConstantsEJS.DATA_OPT_TYPE_U);
                logActFlashSaleProduct.setActFlashSaleProductId(stageProduct.getId());
            }

            logActFlashSaleProduct.setOptUserId(actFlashSaleProduct.getUpdateUserId());
            logActFlashSaleProduct.setOptUserName(actFlashSaleProduct.getUpdateUserName());
            logActFlashSaleProduct.setOptTime(new Date());
            logActFlashSaleProductWriteDao.insert(logActFlashSaleProduct);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("保存限时抢购活动商品时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 更新限时抢购活动商品
     * @param actFlashSaleProduct
     * @return
     */
    public boolean updateActFlashSaleProduct(ActFlashSaleProduct actFlashSaleProduct) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {

            ActFlashSaleProduct actFlashSaleProductDb = actFlashSaleProductReadDao
                .get(actFlashSaleProduct.getId());
            //            if (actFlashSaleProductDb.getStatus().intValue() != ActFlashSaleProduct.STATUS_1
            //                && actFlashSaleProductDb.getStatus().intValue() != ActFlashSaleProduct.STATUS_3) {
            //                throw new BusinessException("只能修改提交审核状态或审核失败的活动商品。");
            //            }
            if (actFlashSaleProduct.getSellerId() == null || actFlashSaleProductDb.getSellerId()
                .intValue() != actFlashSaleProduct.getSellerId().intValue()) {
                throw new BusinessException("只能修改自己店铺的活动商品。");
            }

            Integer row = actFlashSaleProductWriteDao.update(actFlashSaleProduct);

            LogActFlashSaleProduct logActFlashSaleProduct = new LogActFlashSaleProduct();
            logActFlashSaleProduct.setOptType(ConstantsEJS.DATA_OPT_TYPE_U);
            logActFlashSaleProduct.setOptUserId(actFlashSaleProduct.getUpdateUserId());
            logActFlashSaleProduct.setOptUserName(actFlashSaleProduct.getUpdateUserName());
            logActFlashSaleProduct.setOptTime(new Date());
            logActFlashSaleProduct.setActFlashSaleProductId(actFlashSaleProduct.getId());
            logActFlashSaleProductWriteDao.insert(logActFlashSaleProduct);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            e.printStackTrace();
            promotionTransactionManager.rollback(status);
            log.error("修改限时抢购活动商品时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 更新限时抢购活动商品状态
     * @param actFlashSaleProduct
     * @return
     */
    public boolean updateActFlashSaleProductStatus(ActFlashSaleProduct actFlashSaleProduct) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {
            Integer row = actFlashSaleProductWriteDao.updateStatus(actFlashSaleProduct);

            LogActFlashSaleProduct logActFlashSaleProduct = new LogActFlashSaleProduct();
            logActFlashSaleProduct.setOptType(ConstantsEJS.DATA_OPT_TYPE_U);
            // 如果是审核的情况设定修改人为审核人
            if (actFlashSaleProduct.getStatus().intValue() == ActFlashSaleProduct.STATUS_2
                || actFlashSaleProduct.getStatus().intValue() == ActFlashSaleProduct.STATUS_3) {
                logActFlashSaleProduct.setOptUserId(actFlashSaleProduct.getAuditUserId());
                logActFlashSaleProduct.setOptUserName(actFlashSaleProduct.getAuditUserName());
            } else {
                logActFlashSaleProduct.setOptUserId(actFlashSaleProduct.getUpdateUserId());
                logActFlashSaleProduct.setOptUserName(actFlashSaleProduct.getUpdateUserName());
            }
            logActFlashSaleProduct.setOptTime(new Date());
            logActFlashSaleProduct.setActFlashSaleProductId(actFlashSaleProduct.getId());
            logActFlashSaleProductWriteDao.insert(logActFlashSaleProduct);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("修改限时抢购活动商品时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 删除限时抢购活动商品
     * @param actFlashSaleProduct
     * @return
     */
    public boolean deleteActFlashSaleProduct(Integer actFlashSaleProductId, Integer userId,
                                             String userName) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {

            ActFlashSaleProduct actFlashSaleProduct = actFlashSaleProductReadDao
                .get(actFlashSaleProductId);
            if (actFlashSaleProduct.getStatus().intValue() == ActFlashSaleProduct.STATUS_2) {
                throw new BusinessException("只能删除提交审核或审核失败的活动。");
            }

            LogActFlashSaleProduct logActFlashSaleProduct = new LogActFlashSaleProduct();
            logActFlashSaleProduct.setOptType(ConstantsEJS.DATA_OPT_TYPE_D);
            logActFlashSaleProduct.setOptUserId(userId);
            logActFlashSaleProduct.setOptUserName(userName);
            logActFlashSaleProduct.setOptTime(new Date());
            logActFlashSaleProduct.setActFlashSaleProductId(actFlashSaleProductId);
            logActFlashSaleProductWriteDao.insert(logActFlashSaleProduct);

            Integer row = actFlashSaleProductWriteDao.delete(actFlashSaleProductId);

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("删除限时抢购活动商品时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 根据阶段ID、商家ID、商品ID删除限时抢购活动商品
     * @param actFlashSaleProduct
     * @return
     */
    public boolean deleteActFlashSaleProductForSeller(Integer actFlashSaleStageId, Integer sellerId,
                                                      Integer productId, Integer userId,
                                                      String userName) {
        // 根据阶段ID、商家ID、商品ID获取活动商品信息，如果为空则直接返回，不为空则执行删除操作
        ActFlashSaleProduct actFlashSaleProduct = actFlashSaleProductWriteDao
            .getForSeller(actFlashSaleStageId, sellerId, productId);
        if (actFlashSaleProduct == null) {
            return true;
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        try {

            if (actFlashSaleProduct.getStatus().intValue() == ActFlashSaleProduct.STATUS_2) {
                throw new BusinessException("只能删除提交审核或审核失败的活动。");
            }

            LogActFlashSaleProduct logActFlashSaleProduct = new LogActFlashSaleProduct();
            logActFlashSaleProduct.setOptType(ConstantsEJS.DATA_OPT_TYPE_D);
            logActFlashSaleProduct.setOptUserId(userId);
            logActFlashSaleProduct.setOptUserName(userName);
            logActFlashSaleProduct.setOptTime(new Date());
            logActFlashSaleProduct.setActFlashSaleProductId(actFlashSaleProduct.getId());
            logActFlashSaleProductWriteDao.insert(logActFlashSaleProduct);

            Integer row = actFlashSaleProductWriteDao.delete(actFlashSaleProduct.getId());

            promotionTransactionManager.commit(status);
            return row > 0;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            log.error("删除限时抢购活动商品时出现未知异常：" + e);
            throw e;
        }
    }

    /**
     * 根据阶段ID获取活动商品
     * @param actFlashSaleStageId
     * @return
     */
    public List<ActFlashSaleProduct> getActFlashSaleProductsByStageId(Integer actFlashSaleStageId) {
        List<ActFlashSaleProduct> actFlashSaleProducts = actFlashSaleProductReadDao
            .getEffectiveByStageId(actFlashSaleStageId);
        return actFlashSaleProducts;
    }

    /**
     * 根据阶段ID和商家ID获取活动商品
     * @param actFlashSaleStageId
     * @param sellerId
     * @return
     */
    public List<ActFlashSaleProduct> getActFlashSaleProductsByStageIdAndSellerId(Integer actFlashSaleStageId,
                                                                                 Integer sellerId) {
        List<ActFlashSaleProduct> actFlashSaleProducts = actFlashSaleProductReadDao
            .getByStageIdAndSellerId(actFlashSaleStageId, sellerId);
        return actFlashSaleProducts;
    }

    /**
     * 以商品id、卖家id、活动阶段和活动id查询活动商品
     * @param param
     * @return
     */
    public List<ActFlashSaleProduct> getActFlashSaleProduct(Map<String, Object> param) {
        List<ActFlashSaleProduct> actFlashSaleProducts = actFlashSaleProductReadDao
            .getActFlashSaleProduct(param);

        for (ActFlashSaleProduct actPros : actFlashSaleProducts) {
            //活动商品
            Product pro = productReadDao.get(actPros.getProductId());

            //活动阶段,每个商品在当前活动阶段只能添加一个,其与当前活动阶段是一对一的
            ActFlashSaleStage stage = actFlashSaleStageReadDao
                .get(actPros.getActFlashSaleStageId());

            pro.setActTime(stage.getStartTime() + "~" + stage.getEndTime());
            actPros.setProduct(pro);

            //商家
            Seller seller = sellerReadDao.get(actPros.getSellerId());
            actPros.setSellerName(seller.getName());
        }

        return actFlashSaleProducts;
    }
}