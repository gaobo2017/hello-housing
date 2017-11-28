package com.ejavashop.service.analysis;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.analysis.BrowseLog;
import com.ejavashop.entity.analysis.BrowseLogMobile;
import com.ejavashop.entity.analysis.ProductLookLog;

public interface IAnalysisService {

    /**
     * 根据id取得browse_log对象
     * @param  browseLogId
     * @return
     */
    ServiceResult<BrowseLog> getBrowseLogById(Integer browseLogId);

    /**
     * 保存browse_log对象
     * @param  browseLog
     * @return
     */
    ServiceResult<Integer> saveBrowseLog(BrowseLog browseLog);

    /**
    * 更新browse_log对象
    * @param  browseLog
    * @return
    */
    ServiceResult<Integer> updateBrowseLog(BrowseLog browseLog);

    /**
     * 记录用户访问单品页日志
     * @param productLookLog
     * @return
     */
    ServiceResult<Integer> saveProductLookLog(ProductLookLog productLookLog);

    /**
     * 根据条件取得会员商品浏览记录
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ProductLookLog>> getProductLookLogs(Map<String, String> queryMap,
                                                           PagerInfo pager);

    /**
     * 记录移动端日志
     * @param browseLog
     * @return
     */
    ServiceResult<Integer> saveBrowseLogMobile(BrowseLogMobile browseLog);
}
