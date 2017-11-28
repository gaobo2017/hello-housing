package com.ejavashop.service.impl.member;

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
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberProductBack;
import com.ejavashop.model.member.MemberProductBackModel;
import com.ejavashop.service.member.IMemberProductBackService;

/**
 * 会员退货管理
 *
 */
@Service(value = "memberProductBackService")
public class MemberProductBackServiceImpl implements IMemberProductBackService {
    private static Logger          log = LogManager.getLogger(MemberProductBackServiceImpl.class);

    @Resource
    private MemberProductBackModel memberProductBackModel;

    @Override
    public ServiceResult<MemberProductBack> getMemberProductBackById(Integer memberProductBackId) {
        ServiceResult<MemberProductBack> serviceResult = new ServiceResult<MemberProductBack>();
        try {
            serviceResult
                .setResult(memberProductBackModel.getMemberProductBackById(memberProductBackId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[memberProductBackService][getMemberProductBackById]根据id["
                      + memberProductBackId + "]取得用户退货时出现未知异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberProductBackService][getMemberProductBackById]根据id["
                      + memberProductBackId + "]取得用户退货时出现未知异常:",
                e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateMemberProductBack(MemberProductBack memberProductBack) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult
                .setResult(memberProductBackModel.updateMemberProductBack(memberProductBack));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberProductBackService][updateMemberProductBack]修改产品退货申请时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> saveMemberProductBack(MemberProductBack memberProductBack,
                                                        Member member) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult
                .setResult(memberProductBackModel.saveMemberProductBack(memberProductBack, member));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberProductBackService][saveMemberProductBack]保存产品退货申请时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MemberProductBack>> getMemberProductBackList(PagerInfo pager,
                                                                           Integer memberId) {
        ServiceResult<List<MemberProductBack>> serviceResult = new ServiceResult<List<MemberProductBack>>();
        try {
            serviceResult
                .setResult(memberProductBackModel.getMemberProductBackList(pager, memberId));
            return serviceResult;
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberProductBackService][getMemberProductList]取得用户退货列表时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 判断是否可以发起退换货申请
     * @param orderId
     * @param orderProductId
     * @param request
     * @return
     */
    @Override
    public ServiceResult<Boolean> canApplyProductBackOrExchange(Integer orderId,
                                                                Integer orderProductId,
                                                                Integer memberId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(memberProductBackModel.canApplyProductBackOrExchange(orderId,
                orderProductId, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberProductBackService][saveMemberProductBack]保存产品退货申请时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MemberProductBack>> page(Map<String, String> queryMap,
                                                       PagerInfo pager) {
        ServiceResult<List<MemberProductBack>> serviceResult = new ServiceResult<List<MemberProductBack>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(memberProductBackModel.pageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<MemberProductBack> list = memberProductBackModel.page(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[MemberProductBackServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[MemberProductBackServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MemberProductBack>> list() {

        ServiceResult<List<MemberProductBack>> serviceResult = new ServiceResult<List<MemberProductBack>>();
        try {
            List<MemberProductBack> list = memberProductBackModel.list();
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[MemberProductBackServiceImpl][list] exception:", e);
        }
        return serviceResult;

    }

    @Override
    public ServiceResult<Boolean> backMoney(Integer memberProductBackId, Integer optId,
                                            String optName) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult
                .setResult(memberProductBackModel.backMoney(memberProductBackId, optId, optName));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[memberProductBackService][backMoney]用户退货申请退款时出现未知异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberProductBackService][backMoney]用户退货申请退款时出现未知异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MemberProductBack>> getSettleBacks(Integer sellerId, String startTime,
                                                                 String endTime, PagerInfo pager) {

        ServiceResult<List<MemberProductBack>> serviceResult = new ServiceResult<List<MemberProductBack>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(
                    memberProductBackModel.getSettleBacksCount(sellerId, startTime, endTime));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<MemberProductBack> list = memberProductBackModel.getSettleBacks(sellerId,
                startTime, endTime, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[MemberProductBackServiceImpl][getSettleBacks] exception:", e);
        }
        return serviceResult;

    }

    @Override
    public ServiceResult<List<MemberProductBack>> getBackListWithPrdAndOp(PagerInfo pager,
                                                                          Integer memberId) {
        ServiceResult<List<MemberProductBack>> serviceResult = new ServiceResult<List<MemberProductBack>>();
        try {
            serviceResult
                .setResult(memberProductBackModel.getBackListWithPrdAndOp(pager, memberId));
            return serviceResult;
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberProductBackService][getBackListWithPrdAndOp]取得用户退货列表时发生异常:", e);
        }
        return serviceResult;
    }

}