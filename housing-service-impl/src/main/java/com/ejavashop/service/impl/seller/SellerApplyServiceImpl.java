package com.ejavashop.service.impl.seller;

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
import com.ejavashop.entity.seller.SellerApply;
import com.ejavashop.model.seller.SellerApplyModel;
import com.ejavashop.service.seller.ISellerApplyService;

@Service(value = "sellerApplyService")
public class SellerApplyServiceImpl implements ISellerApplyService {

    private static Logger    log = LogManager.getLogger(SellerApplyServiceImpl.class);

    @Resource
    private SellerApplyModel sellerApplyModel;

    @Override
    public ServiceResult<Boolean> updateSellerApply(SellerApply sellerApply) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerApplyModel.updateSellerApply(sellerApply));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SellerApplyService][updateSellerApply]修改商家申请时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[SellerApplyService][updateSellerApply] param:" + JSON.toJSONString(sellerApply));
            log.error("[SellerApplyService][updateSellerApply]修改商家申请时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SellerApply>> getSellerApplys(Map<String, String> queryMap,
                                                            PagerInfo pager) {
        ServiceResult<List<SellerApply>> serviceResult = new ServiceResult<List<SellerApply>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sellerApplyModel.getSellerApplysCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<SellerApply> list = sellerApplyModel.getSellerApplys(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error("[SellerApplyService][getSellerApplys]根据条件分页查询商家申请时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerApplyService][getSellerApplys] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SellerApplyService][getSellerApplys] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> deleteSellerApply(Integer id, Integer memberId) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerApplyModel.delete(id, memberId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SellerApplyService][deleteSellerApply]删除商家申请时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][deleteSellerApply]删除商家申请时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<SellerApply> getSellerApplyById(Integer sellerApplyId) {
        ServiceResult<SellerApply> serviceResult = new ServiceResult<SellerApply>();
        try {
            serviceResult.setResult(sellerApplyModel.getSellerApplyById(sellerApplyId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SellerApplyService][getSellerApplyById]根据id[" + sellerApplyId
                      + "]取得商家申请表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][getSellerApplyById]根据id[" + sellerApplyId
                      + "]取得商家申请表时出现未知异常：",
                e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> saveSellerApply(SellerApply sellerApply) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(sellerApplyModel.saveSellerApply(sellerApply));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SellerApplyService][saveSellerApply]保存商家申请表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][saveSellerApply]保存商家申请表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<SellerApply> getSellerApplyByUser(Integer memberId) {
        ServiceResult<SellerApply> serviceResult = new ServiceResult<SellerApply>();
        try {
            serviceResult.setResult(sellerApplyModel.getSellerApplyByUser(memberId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[SellerApplyService][getSellerApplyByUser]根据用户ID获取其商家入驻申请时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][getSellerApplyByUser]根据用户ID获取其商家入驻申请时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> auditSellerApply(Integer sellerApplyId, Integer state,
                                                   Integer optUserId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult
                .setResult(sellerApplyModel.auditSellerApply(sellerApplyId, state, optUserId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SellerApplyService][auditSellerApply]审核商家入驻申请时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][auditSellerApply]审核商家入驻申请时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> saveSellerApplyForAdmin(SellerApply sellerApply, String userName,
                                                          String sellerName, String ip) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(
                sellerApplyModel.saveSellerApplyForAdmin(sellerApply, userName, sellerName, ip));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[SellerApplyService][saveSellerApplyForAdmin]平台保存商家申请表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][saveSellerApplyForAdmin]平台保存商家申请表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateSellerApplyForAdmin(SellerApply sellerApply,
                                                            String userName, String sellerName) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(
                sellerApplyModel.updateSellerApplyForAdmin(sellerApply, userName, sellerName));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[SellerApplyService][updateSellerApplyForAdmin]平台修改商家申请表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][updateSellerApplyForAdmin]平台修改商家申请表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> saveSellerApplyForFront(SellerApply sellerApply, String userName,
                                                          String sellerName, Integer memberId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerApplyModel.saveSellerApplyForFront(sellerApply, userName,
                sellerName, memberId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[SellerApplyService][saveSellerApplyForFront]用户端保存商家申请表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][saveSellerApplyForFront]用户端保存商家申请表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateSellerApplyForFront(SellerApply sellerApply,
                                                            String userName, String sellerName) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(
                sellerApplyModel.updateSellerApplyForFront(sellerApply, userName, sellerName));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[SellerApplyService][updateSellerApplyForFront]用户端修改商家申请表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][updateSellerApplyForFront]用户端修改商家申请表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SellerApply>> getSellerApplyByCompany(String company) {
        ServiceResult<List<SellerApply>> serviceResult = new ServiceResult<List<SellerApply>>();
        try {
            List<SellerApply> list = sellerApplyModel.getSellerApplyByCompany(company);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error("[SellerApplyService][getSellerApplysByCompany]根据公司名称查询入驻申请时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerApplyService][getSellerApplysByCompany] param1:" + company);
            log.error("[SellerApplyService][getSellerApplysByCompany] 根据公司名称查询入驻申请时出现未知异常:", e);
        }
        return serviceResult;
    }
}
