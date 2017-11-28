package com.ejavashop.job;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.service.cart.ICartService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.search.ISolrProductService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.service.settlement.ISettlementService;

public class AdminJob {
    private static final Logger log = LogManager.getLogger(AdminJob.class);
    @Resource
    private ISettlementService  settlementService;
    @Resource
    private IOrdersService      ordersService;
    @Resource
    private ICartService        cartService;
    @Resource
    private ISellerService      sellerService;

    @Resource
    private ISolrProductService solrProductService;

    /**
     * 商家结算账单生成定时任务<br>
     * <li>查询所有商家，每个商家每个结算周期生成一条结算账单
     * <li>计算周期内商家所有的订单金额合计
     * <li>计算所有订单下网单的佣金
     * <li>计算周期内发生的退货退款（当前周期结算的订单如果在下个结算周期才退款，退款结算在下个周期计算）
     * <li>每个商家一个事务，某个商家结算时发生错误不影响其他结算
     */
    public void jobSettlement() {
        ServiceResult<Boolean> jobResult = settlementService.jobSettlement();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobSettlement] 商家结算账单生成时失败："
                      + jobResult.getMessage());
        }
    }

    /**
     * 系统自动完成订单<br>
     * <li>对已发货状态的订单发货时间超过15个自然日的订单进行自动完成处理
     */
    public void jobSystemFinishOrder() {
        ServiceResult<Boolean> jobResult = ordersService.jobSystemFinishOrder();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobSystemFinishOrder] 系统自动完成订单时失败："
                      + jobResult.getMessage());
        }
    }

    /**
     * 系统定时任务清除7天之前添加的购物车数据
     */
    public void jobClearCart() {
        ServiceResult<Boolean> jobResult = cartService.jobClearCart();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobClearCart] 系统定时任务清除7天之前添加的购物车数据时失败："
                      + jobResult.getMessage());
        }
    }

    /**
     * 系统定时更新solr索引
     */
    public void jobSearchSolr() {
        String solrUrl = DomainUrlUtil.getEJS_SOLR_URL();
        String solrServer = DomainUrlUtil.getEJS_SOLR_SERVER();
        ServiceResult<Boolean> jobResult = solrProductService.jobCreateIndexesSolr(solrUrl,
            solrServer);
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobSearchSolr] 系统定时任务定时生成Solr索引失败："
                      + jobResult.getMessage());
        }
    }

    /**
     * 更新敏感词的索引值
     */
    public void jobUpdateSearchRecordIndex() {
        String solrUrl = DomainUrlUtil.getEJS_SOLR_URL();
        String solrServer = DomainUrlUtil.getEJS_SOLR_SERVER();
        ServiceResult<Boolean> jobResult = solrProductService.jobUpdateSearchRecordIndex(solrUrl,
            solrServer);
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobUpdateSearchRecordIndex] 系统定时任务定时更新敏感词的索引值："
                      + jobResult.getMessage());
        }
    }

    /**
     * 定时任务设定商家的评分，用户评论各项求平均值设置为商家各项的综合评分
     * @return
     */
    public void jobSetSellerScore() {
        ServiceResult<Boolean> jobResult = sellerService.jobSetSellerScore();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobSetSellerScore] 定时任务设定商家的评分时失败："
                      + jobResult.getMessage());
        }
    }

    /**
     * 系统自动取消24小时没有付款订单
     * @return
     */
    public void jobSystemCancelOrder() {
        ServiceResult<Boolean> jobResult = ordersService.jobSystemCancelOrder();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobSystemCancelOrder] 定时任务系统自动取消24小时没有付款订单时失败："
                      + jobResult.getMessage());
        }
    }

    /**
     * 定时任务设定商家各项统计数据
     * @return
     */
    public void jobSellerStatistics() {
        ServiceResult<Boolean> jobResult = sellerService.jobSellerStatistics();
        if (!jobResult.getSuccess() || jobResult.getResult() == null || !jobResult.getResult()) {
            log.error("[ejavashop-admin][AdminJob][jobSellerStatistics] 定时任务设定商家各项统计数据时失败："
                      + jobResult.getMessage());
        }
    }
}
