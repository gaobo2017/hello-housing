package com.ejavashop.service.impl.house;

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
import com.ejavashop.entity.house.HousingCostDetail;
import com.ejavashop.model.house.HouseCostModel;
import com.ejavashop.service.house.IHouseCostService;
import com.ejavashop.vo.house.HousingCostVO;

@Service(value = "houseCostService")
public class HouseCostServiceImpl implements IHouseCostService {

    private static Logger  log = LogManager.getLogger(HouseCostServiceImpl.class);

    @Resource
    private HouseCostModel houseCostModel;

    @Override
    public ServiceResult<List<HousingCostVO>> getHousingCostList(Map<String, String> queryMap,
                                                                 PagerInfo pager) {
        ServiceResult<List<HousingCostVO>> serviceResult = new ServiceResult<List<HousingCostVO>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(houseCostModel.getHousingCostCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<HousingCostVO> list = houseCostModel.getHousingCostList(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error(
                "[HouseCostService][getHousingCostList]根据条件分页查询房源成本信息时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[HouseCostService][getHousingCostList] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[HouseCostService][getHousingCostList] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<HousingCostVO>> getHousingCostDetailList(Map<String, String> queryMap,
                                                                       PagerInfo pager) {
        ServiceResult<List<HousingCostVO>> serviceResult = new ServiceResult<List<HousingCostVO>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(houseCostModel.getHousingCostDetailCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<HousingCostVO> list = houseCostModel.getHousingCostDetailList(queryMap, start,
                size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error(
                "[HouseCostService][getHousingCostList]根据条件分页查询房源成本信息时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[HouseCostService][getHousingCostList] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[HouseCostService][getHousingCostList] exception:", e);
        }
        return serviceResult;
    }
    //    @Override
    //    public ServiceResult<List<HousingResources>> getHousingResourcesList(Map<String, String> queryMap,
    //                                                                         PagerInfo pager) {
    //        ServiceResult<List<HousingResources>> serviceResult = new ServiceResult<List<HousingResources>>();
    //        try {
    //            Integer start = 0, size = 0;
    //            if (pager != null) {
    //                pager.setRowsCount(houseCostModel.getHousingResourcesCount(queryMap));
    //                start = pager.getStart();
    //                size = pager.getPageSize();
    //            }
    //
    //            List<HousingResources> list = houseCostModel.getHousingResourcesList(queryMap, start,
    //                size);
    //            serviceResult.setResult(list);
    //        } catch (BusinessException e) {
    //            serviceResult.setMessage(e.getMessage());
    //            serviceResult.setSuccess(Boolean.FALSE);
    //            log.error(
    //                "[HouseCostService][getHousingResourcesList]根据条件分页查询房源信息时出现异常：" + e.getMessage());
    //        } catch (Exception e) {
    //            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
    //                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
    //            log.error("[HouseCostService][getHousingResourcesList] param1:"
    //                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
    //            log.error("[HouseCostService][getHousingResourcesList] exception:", e);
    //        }
    //        return serviceResult;
    //    }

    //    @Override
    //    public ServiceResult<Boolean> deleteSellerApply(Integer id, Integer memberId) {
    //
    //        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
    //        try {
    //            serviceResult.setResult(sellerApplyModel.delete(id, memberId));
    //        } catch (BusinessException e) {
    //            serviceResult.setSuccess(false);
    //            serviceResult.setMessage(e.getMessage());
    //            log.error("[SellerApplyService][deleteSellerApply]删除商家申请时出现异常：" + e.getMessage());
    //        } catch (Exception e) {
    //            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
    //            log.error("[SellerApplyService][deleteSellerApply]删除商家申请时出现未知异常：", e);
    //        }
    //        return serviceResult;
    //    }
    //
    //    @Override
    //    public ServiceResult<HousingResources> getHousingResourcesById(Integer housingResourcesId) {
    //        ServiceResult<HousingResources> serviceResult = new ServiceResult<HousingResources>();
    //        try {
    //            serviceResult.setResult(houseCostModel.getHousingResourcesById(housingResourcesId));
    //        } catch (BusinessException e) {
    //            serviceResult.setSuccess(false);
    //            serviceResult.setMessage(e.getMessage());
    //            log.error("[HouseCostService][getHousingResourcesById]根据id[" + housingResourcesId
    //                      + "]取得房源信息时出现异常：" + e.getMessage());
    //        } catch (Exception e) {
    //            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
    //            log.error("[HouseCostService][getHousingResourcesById]根据id[" + housingResourcesId
    //                      + "]取得房源信息时出现未知异常：",
    //                e);
    //        }
    //        return serviceResult;
    //    }
    @Override
    public ServiceResult<Integer> createHousingCostDetailAndSummaryCost(HousingCostDetail housingCostDetail) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult
                .setResult(houseCostModel.createHousingCostDetailAndSummaryCost(housingCostDetail));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[HouseCostService][createHousingResources]平台新增房源信息时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[HouseCostService][createHousingResources]平台新增房源信息时出现未知异常：", e);
        }
        return serviceResult;
    }

  

    @Override
    public ServiceResult<HousingCostDetail> getHousingCostDetailById(Integer id) {
        ServiceResult<HousingCostDetail> serviceResult = new ServiceResult<HousingCostDetail>();
        try {
            serviceResult.setResult(houseCostModel.getHousingCostDetailById(id));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[HouseCostService][getHousingCostDetailById]根据id[" + id + "]取得成本明细时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[HouseCostService][getHousingCostDetailById]根据id[" + id + "]取得成本明细信息时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> updateHousingCostDetail(HousingCostDetail housingCostDetail) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(houseCostModel.updateHousingCostDetail(housingCostDetail));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[HouseCostService][updateHousingCostDetail]平台修改成本明细时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[HouseCostService][updateHousingCostDetail]平台修改成本明细时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> deleteHousingCostDetail(Integer housingCostDetail) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(houseCostModel.deleteHousingCostDetail(housingCostDetail));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[HouseCostService][deleteHousingResources]删除房源时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[HouseCostService][deleteHousingResources]删除房源时出现未知异常：", e);
        }
        return serviceResult;
    }

    //
    //    @Override
    //    public ServiceResult<SellerApply> getSellerApplyByUser(Integer memberId) {
    //        ServiceResult<SellerApply> serviceResult = new ServiceResult<SellerApply>();
    //        try {
    //            serviceResult.setResult(sellerApplyModel.getSellerApplyByUser(memberId));
    //        } catch (BusinessException e) {
    //            serviceResult.setSuccess(false);
    //            serviceResult.setMessage(e.getMessage());
    //            log.error(
    //                "[SellerApplyService][getSellerApplyByUser]根据用户ID获取其商家入驻申请时出现异常：" + e.getMessage());
    //        } catch (Exception e) {
    //            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
    //            log.error("[SellerApplyService][getSellerApplyByUser]根据用户ID获取其商家入驻申请时出现未知异常：", e);
    //        }
    //        return serviceResult;
    //    }
    //

    //    @Override
    //    public ServiceResult<Boolean> saveSellerApplyForAdmin(SellerApply sellerApply, String userName,
    //                                                          String sellerName, String ip) {
    //        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
    //        try {
    //            serviceResult.setResult(
    //                sellerApplyModel.saveSellerApplyForAdmin(sellerApply, userName, sellerName, ip));
    //        } catch (BusinessException e) {
    //            serviceResult.setSuccess(false);
    //            serviceResult.setMessage(e.getMessage());
    //            log.error(
    //                "[SellerApplyService][saveSellerApplyForAdmin]平台保存商家申请表时出现异常：" + e.getMessage());
    //        } catch (Exception e) {
    //            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
    //            log.error("[SellerApplyService][saveSellerApplyForAdmin]平台保存商家申请表时出现未知异常：", e);
    //        }
    //        return serviceResult;
    //    }
    //

    //
    //    @Override
    //    public ServiceResult<Boolean> saveSellerApplyForFront(SellerApply sellerApply, String userName,
    //                                                          String sellerName, Integer memberId) {
    //        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
    //        try {
    //            serviceResult.setResult(sellerApplyModel.saveSellerApplyForFront(sellerApply, userName,
    //                sellerName, memberId));
    //        } catch (BusinessException e) {
    //            serviceResult.setSuccess(false);
    //            serviceResult.setMessage(e.getMessage());
    //            log.error(
    //                "[SellerApplyService][saveSellerApplyForFront]用户端保存商家申请表时出现异常：" + e.getMessage());
    //        } catch (Exception e) {
    //            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
    //            log.error("[SellerApplyService][saveSellerApplyForFront]用户端保存商家申请表时出现未知异常：", e);
    //        }
    //        return serviceResult;
    //    }
    //
    //    @Override
    //    public ServiceResult<Boolean> updateSellerApplyForFront(SellerApply sellerApply,
    //                                                            String userName, String sellerName) {
    //        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
    //        try {
    //            serviceResult.setResult(
    //                sellerApplyModel.updateSellerApplyForFront(sellerApply, userName, sellerName));
    //        } catch (BusinessException e) {
    //            serviceResult.setSuccess(false);
    //            serviceResult.setMessage(e.getMessage());
    //            log.error(
    //                "[SellerApplyService][updateSellerApplyForFront]用户端修改商家申请表时出现异常：" + e.getMessage());
    //        } catch (Exception e) {
    //            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
    //            log.error("[SellerApplyService][updateSellerApplyForFront]用户端修改商家申请表时出现未知异常：", e);
    //        }
    //        return serviceResult;
    //    }
    //
    //    @Override
    //    public ServiceResult<List<SellerApply>> getSellerApplyByCompany(String company) {
    //        ServiceResult<List<SellerApply>> serviceResult = new ServiceResult<List<SellerApply>>();
    //        try {
    //            List<SellerApply> list = sellerApplyModel.getSellerApplyByCompany(company);
    //            serviceResult.setResult(list);
    //        } catch (BusinessException e) {
    //            serviceResult.setMessage(e.getMessage());
    //            serviceResult.setSuccess(Boolean.FALSE);
    //            log.error("[SellerApplyService][getSellerApplysByCompany]根据公司名称查询入驻申请时出现异常："
    //                      + e.getMessage());
    //        } catch (Exception e) {
    //            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
    //                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
    //            log.error("[SellerApplyService][getSellerApplysByCompany] param1:" + company);
    //            log.error("[SellerApplyService][getSellerApplysByCompany] 根据公司名称查询入驻申请时出现未知异常:", e);
    //        }
    //        return serviceResult;
    //    }

}
