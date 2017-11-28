package com.ejavashop.service.impl.pcseller;

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
import com.ejavashop.entity.shopm.pcseller.PcSellerRecommendData;
import com.ejavashop.model.pcseller.PcSellerRecommendDataModel;
import com.ejavashop.service.pcseller.IPcSellerRecommendDataService;

@Service(value = "pcSellerRecommendDataService")
public class PcSellerRecommendDataServiceImpl implements IPcSellerRecommendDataService {
    private static Logger              log = LogManager
        .getLogger(PcSellerRecommendDataServiceImpl.class);

    @Resource
    private PcSellerRecommendDataModel pcSellerRecommendDataModel;

    @Override
    public ServiceResult<PcSellerRecommendData> getPcSellerRecommendDataById(Integer pcSellerRecommendDataId) {
        ServiceResult<PcSellerRecommendData> result = new ServiceResult<PcSellerRecommendData>();
        try {
            result.setResult(
                pcSellerRecommendDataModel.getPcSellerRecommendDataById(pcSellerRecommendDataId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerRecommendDataService][getPcSellerRecommendDataById]根据id["
                      + pcSellerRecommendDataId + "]取得PC端商家推荐数据时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerRecommendDataService][getPcSellerRecommendDataById]根据id["
                      + pcSellerRecommendDataId + "]取得PC端商家推荐数据时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> savePcSellerRecommendData(PcSellerRecommendData pcSellerRecommendData) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(
                pcSellerRecommendDataModel.savePcSellerRecommendData(pcSellerRecommendData));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IPcSellerRecommendDataService][savePcSellerRecommendData]保存PC端商家推荐数据时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerRecommendDataService][savePcSellerRecommendData]保存PC端商家推荐数据时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updatePcSellerRecommendData(PcSellerRecommendData pcSellerRecommendData) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(
                pcSellerRecommendDataModel.updatePcSellerRecommendData(pcSellerRecommendData));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcSellerRecommendDataService][updatePcSellerRecommendData]更新PC端商家推荐数据时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[IPcSellerRecommendDataService][updatePcSellerRecommendData]更新PC端商家推荐数据时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deletePcSellerRecommendData(Integer pcSellerRecommendDataId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(
                pcSellerRecommendDataModel.deletePcSellerRecommendData(pcSellerRecommendDataId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcSellerRecommendDataService][deletePcSellerRecommendData]删除PC端商家推荐数据时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[IPcSellerRecommendDataService][deletePcSellerRecommendData]删除PC端商家推荐数据时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PcSellerRecommendData>> getPcSellerRecommendDatas(Map<String, String> queryMap,
                                                                                PagerInfo pager) {
        ServiceResult<List<PcSellerRecommendData>> serviceResult = new ServiceResult<List<PcSellerRecommendData>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(
                    pcSellerRecommendDataModel.getPcSellerRecommendDatasCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(
                pcSellerRecommendDataModel.getPcSellerRecommendDatas(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[IPcSellerRecommendDataService][getPcSellerRecommendDatas]根据条件取得PC端商家推荐数据时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPcSellerRecommendDataService][getPcSellerRecommendDatas]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error(
                "[IPcSellerRecommendDataService][getPcSellerRecommendDatas]根据条件取得PC端商家推荐数据时发生异常:",
                e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PcSellerRecommendData>> getPcSellerRecommendDataForView(Integer recommendId) {
        ServiceResult<List<PcSellerRecommendData>> result = new ServiceResult<List<PcSellerRecommendData>>();
        try {
            result
                .setResult(pcSellerRecommendDataModel.getPcSellerRecommendDataForView(recommendId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[IPcSellerRecommendDataService][getPcSellerRecommendDataForView]取得PC端商家推荐数据时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[IPcSellerRecommendDataService][getPcSellerRecommendDataForView]取得PC端商家推荐数据时发生异常:",
                e);
        }
        return result;
    }

}