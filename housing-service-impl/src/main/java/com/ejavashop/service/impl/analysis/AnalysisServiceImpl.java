package com.ejavashop.service.impl.analysis;

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
import com.ejavashop.entity.analysis.BrowseLog;
import com.ejavashop.entity.analysis.BrowseLogMobile;
import com.ejavashop.entity.analysis.ProductLookLog;
import com.ejavashop.model.analysis.AnalysisModel;
import com.ejavashop.service.analysis.IAnalysisService;

@Service(value = "analysisService")
public class AnalysisServiceImpl implements IAnalysisService {

    private static Logger log = LogManager.getLogger(AnalysisServiceImpl.class);

    @Resource
    private AnalysisModel analysisModel;

    /**
     * 根据id取得browse_log对象
     * @param  browseLogId
     * @return
     * @see com.ejavashop.analysis.service.BrowseLogService#getBrowseLogById(java.lang.Integer)
     */
    @Override
    public ServiceResult<BrowseLog> getBrowseLogById(Integer browseLogId) {
        ServiceResult<BrowseLog> result = new ServiceResult<BrowseLog>();
        try {
            result.setResult(analysisModel.getBrowseLogById(browseLogId));
        } catch (Exception e) {
            log.error("[BrowseLogServiceImpl][getBrowseLogById]根据id[" + browseLogId
                      + "]取得browse_log对象时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[BrowseLogServiceImpl][getBrowseLogById]根据id[" + browseLogId
                              + "]取得browse_log对象时出现未知异常");
        }
        return result;
    }

    /**
     * 保存browse_log对象
     * @param  browseLog
     * @return
     * @see com.ejavashop.analysis.service.BrowseLogService#saveBrowseLog(BrowseLog)
     */
    @Override
    public ServiceResult<Integer> saveBrowseLog(BrowseLog browseLog) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(analysisModel.saveBrowseLog(browseLog));
        } catch (Exception e) {
            log.error("[BrowseLogServiceImpl][saveBrowseLog]保存browse_log对象时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[BrowseLogServiceImpl][saveBrowseLog]保存browse_log对象时出现未知异常");
        }
        return result;
    }

    /**
    * 更新browse_log对象
    * @param  browseLog
    * @return
    * @see com.ejavashop.analysis.service.BrowseLogService#updateBrowseLog(BrowseLog)
    */
    @Override
    public ServiceResult<Integer> updateBrowseLog(BrowseLog browseLog) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(analysisModel.updateBrowseLog(browseLog));
        } catch (Exception e) {
            log.error("[BrowseLogServiceImpl][updateBrowseLog]更新browse_log对象时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[BrowseLogServiceImpl][updateBrowseLog]更新browse_log对象时出现未知异常");
        }
        return result;
    }

    /**
     * 记录用户访问单品页日志
     * @param productLookLog
     * @return
     * @see com.ejavashop.service.analysis.IAnalysisService#saveProductLookLog(com.ejavashop.entity.analysis.ProductLookLog)
     */
    @Override
    public ServiceResult<Integer> saveProductLookLog(ProductLookLog productLookLog) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(analysisModel.saveProductLookLog(productLookLog));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[BrowseLogServiceImpl][saveProductLookLog]记录用户访问单品页日志时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("[BrowseLogServiceImpl][saveProductLookLog]记录用户访问单品页日志时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<ProductLookLog>> getProductLookLogs(Map<String, String> queryMap,
                                                                  PagerInfo pager) {
        ServiceResult<List<ProductLookLog>> serviceResult = new ServiceResult<List<ProductLookLog>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(analysisModel.getProductLookLogsCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(analysisModel.getProductLookLogs(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[BrowseLogServiceImpl][getProductLookLogs]查询会员商品浏览表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[BrowseLogServiceImpl][getProductLookLogs]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[BrowseLogServiceImpl][getProductLookLogs]查询会员商品浏览发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 记录移动端日志
     * @param browseLog
     * @return
     * @see com.ejavashop.service.analysis.IAnalysisService#saveBrowseLogMobile(com.ejavashop.entity.analysis.BrowseLogMobile)
     */
    @Override
    public ServiceResult<Integer> saveBrowseLogMobile(BrowseLogMobile browseLog) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(analysisModel.saveBrowseLogMobile(browseLog));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[BrowseLogServiceImpl][saveBrowseLogMobile]保存BrowseLogMobile对象时出现未知异常：" + e);
            result.setSuccess(false);
            result
                .setMessage("[BrowseLogServiceImpl][saveBrowseLogMobile]保存BrowseLogMobile对象时出现未知异常");
        }
        return result;
    }
}
