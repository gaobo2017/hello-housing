package com.ejavashop.model.analysis;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.analysis.read.BrowseLogMobileReadDao;
import com.ejavashop.dao.analysis.read.BrowseLogReadDao;
import com.ejavashop.dao.analysis.read.ProductLookLogReadDao;
import com.ejavashop.dao.analysis.write.BrowseLogMobileWriteDao;
import com.ejavashop.dao.analysis.write.BrowseLogWriteDao;
import com.ejavashop.dao.analysis.write.ProductLookLogWriteDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.entity.analysis.BrowseLog;
import com.ejavashop.entity.analysis.BrowseLogMobile;
import com.ejavashop.entity.analysis.ProductLookLog;

@Component(value = "analysisModel")
public class AnalysisModel {

    @Resource
    private BrowseLogWriteDao       browseLogWriteDao;

    @Resource
    private BrowseLogReadDao        browseLogReadDao;

    @Resource
    private BrowseLogMobileWriteDao browseLogMobileWriteDao;

    @Resource
    private BrowseLogMobileReadDao  browseLogMobileReadDao;

    @Resource
    private ProductLookLogReadDao   productLookLogReadDao;

    @Resource
    private ProductLookLogWriteDao  productLookLogWriteDao;

    @Resource
    private ProductReadDao          productReadDao;

    /**
     * 根据id取得browse_log对象
     * @param  browseLogId
     * @return
     */
    public BrowseLog getBrowseLogById(Integer browseLogId) {
        return browseLogReadDao.get(browseLogId);
    }

    /**
     * 保存browse_log对象
     * @param  browseLog
     * @return
     */
    public Integer saveBrowseLog(BrowseLog browseLog) {
        this.dbConstrains(browseLog);
        return browseLogWriteDao.insert(browseLog);
    }

    /**
    * 更新browse_log对象
    * @param  browseLog
    * @return
    */
    public Integer updateBrowseLog(BrowseLog browseLog) {
        this.dbConstrains(browseLog);
        return browseLogWriteDao.update(browseLog);
    }

    /**
     * 保存用户浏览商品日志
     * @param productLookLog
     * @return
     */
    public Integer saveProductLookLog(ProductLookLog productLookLog) {
        //判断今天是否有浏览商品，有不做处理，没有插入数据
        int countSiteCookie = productLookLogWriteDao.countBySiteCookieAndProductId(
            productLookLog.getSiteCookie(), productLookLog.getProductId());
        if (countSiteCookie == 0) {
            productLookLogWriteDao.insert(productLookLog);
        }
        //假如用户登录，判断是否有未更新的用户ID，有更新用户ID，没有不做处理
        if (productLookLog.getMemberId().intValue() != 0) {
            int countMember = productLookLogWriteDao.countBySiteCookie(productLookLog
                .getSiteCookie());
            if (countMember > 0) {
                productLookLogWriteDao.updateByMemberId(productLookLog.getSiteCookie(),
                    productLookLog.getMemberId());
            }
        }
        return 0;
    }

    public Integer getProductLookLogsCount(Map<String, String> queryMap) {
        return productLookLogReadDao.getProductLookLogsCount(queryMap);
    }

    public List<ProductLookLog> getProductLookLogs(Map<String, String> queryMap, Integer start,
                                                   Integer size) {
        List<ProductLookLog> lookLogList = productLookLogReadDao.getProductLookLogs(queryMap,
            start, size);
        if (lookLogList != null && lookLogList.size() > 0) {
            for (ProductLookLog lookLog : lookLogList) {
                lookLog.setProduct(productReadDao.get(lookLog.getProductId()));
            }
        }
        return lookLogList;
    }

    private void dbConstrains(BrowseLog browseLog) {
        browseLog.setSiteCookie(StringUtil.dbSafeString(browseLog.getSiteCookie(), false, 50));
        browseLog.setSessionId(StringUtil.dbSafeString(browseLog.getSessionId(), false, 50));
        browseLog.setUserAgent(StringUtil.dbSafeString(browseLog.getUserAgent(), false, 65535));
        browseLog.setIpAddress(StringUtil.dbSafeString(browseLog.getIpAddress(), false, 50));
        browseLog.setAccessedPage(StringUtil.dbSafeString(browseLog.getAccessedPage(), false, 200));
        browseLog.setUrlReferer(StringUtil.dbSafeString(browseLog.getUrlReferer(), false, 200));
        browseLog.setBrowseName(StringUtil.dbSafeString(browseLog.getBrowseName(), false, 200));
        browseLog.setBrowserVersion(StringUtil.dbSafeString(browseLog.getBrowserVersion(), false,
            200));
        browseLog.setEbi(StringUtil.dbSafeString(browseLog.getEbi(), true, 200));
    }

    private void dbConstrainsMobile(BrowseLogMobile browseLog) {
        browseLog.setSiteCookie(StringUtil.dbSafeString(browseLog.getSiteCookie(), false, 50));
        browseLog.setSessionId(StringUtil.dbSafeString(browseLog.getSessionId(), false, 50));
        browseLog.setUserAgent(StringUtil.dbSafeString(browseLog.getUserAgent(), false, 65535));
        browseLog.setIpAddress(StringUtil.dbSafeString(browseLog.getIpAddress(), false, 50));
        browseLog.setAccessedPage(StringUtil.dbSafeString(browseLog.getAccessedPage(), false, 200));
        browseLog.setUrlReferer(StringUtil.dbSafeString(browseLog.getUrlReferer(), false, 200));
        browseLog.setBrowseName(StringUtil.dbSafeString(browseLog.getBrowseName(), false, 200));
        browseLog.setBrowserVersion(StringUtil.dbSafeString(browseLog.getBrowserVersion(), false,
            200));
        browseLog.setEbi(StringUtil.dbSafeString(browseLog.getEbi(), true, 200));
    }

    /**
     * 保存移动端的记录
     * @param browseLog
     * @return
     */
    public Integer saveBrowseLogMobile(BrowseLogMobile browseLog) {
        this.dbConstrainsMobile(browseLog);
        return browseLogMobileWriteDao.insert(browseLog);
    }

}
