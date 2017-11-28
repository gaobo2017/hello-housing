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
import com.ejavashop.entity.member.MemberProductExchange;
import com.ejavashop.model.member.MemberProductExchangeModel;
import com.ejavashop.service.member.IMemberProductExchangeService;

/**
 * 会员换货管理                       
 *
 */
@Service(value = "memberProductExchangeService")
public class MemberProductExchangeServiceImpl implements IMemberProductExchangeService {
    private static Logger              log = LogManager
        .getLogger(MemberProductExchangeServiceImpl.class);

    @Resource
    private MemberProductExchangeModel memberProductExchangeModel;

    /**
    * 根据id取得用户换货
    * @param  memberProductExchangeId
    * @return
    */
    @Override
    public ServiceResult<MemberProductExchange> getMemberProductExchangeById(Integer memberProductExchangeId) {
        ServiceResult<MemberProductExchange> result = new ServiceResult<MemberProductExchange>();
        try {
            result.setResult(
                memberProductExchangeModel.getMemberProductExchangeById(memberProductExchangeId));
        } catch (Exception e) {
            log.error("根据id[" + memberProductExchangeId + "]取得用户换货时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + memberProductExchangeId + "]取得用户换货时出现未知异常");
        }
        return result;
    }

    /**
     * 保存用户换货
     * @param  memberProductExchange
     * @return
     */
    @Override
    public ServiceResult<Boolean> saveMemberProductExchange(MemberProductExchange memberProductExchange,
                                                            Member member) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(memberProductExchangeModel
                .saveMemberProductExchange(memberProductExchange, member));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberProductExchangeService][saveMemberProductExchange]保存产品换货申请时发生异常:", e);
        }
        return serviceResult;
    }

    /**
    * 更新用户换货
    * @param  memberProductExchange
    * @return
    */
    @Override
    public ServiceResult<Boolean> updateMemberProductExchange(MemberProductExchange memberProductExchange) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(
                memberProductExchangeModel.updateMemberProductExchange(memberProductExchange));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberProductExchangeService][updateMemberProductExchange]更新用户换货申请时发生异常:",
                e);
        }
        return serviceResult;
    }

    /**
     * 根据登录用户取得用户换货列表 分页
     * @param pager
     * @param request
     * @return
     */
    @Override
    public ServiceResult<List<MemberProductExchange>> getMemberProductExchangeList(Map<String, Object> queryMap,
                                                                                   PagerInfo pager,
                                                                                   Integer memberId) {
        ServiceResult<List<MemberProductExchange>> serviceResult = new ServiceResult<List<MemberProductExchange>>();
        try {
            serviceResult.setResult(
                memberProductExchangeModel.getMemberProductExchangeList(queryMap, pager, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberProductExchangeService][getMemberProductExchangeList]取得用户换货列表时发生异常:",
                e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MemberProductExchange>> getMemberProductExchanges(Map<String, String> queryMap,
                                                                                PagerInfo pager) {
        ServiceResult<List<MemberProductExchange>> serviceResult = new ServiceResult<List<MemberProductExchange>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(memberProductExchangeModel.pageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<MemberProductExchange> list = memberProductExchangeModel.page(queryMap, start,
                size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[MemberProductBackServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[MemberProductBackServiceImpl][page] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MemberProductExchange>> getExchangeListWithPrdAndOp(PagerInfo pager,
                                                                                  Integer memberId) {
        ServiceResult<List<MemberProductExchange>> serviceResult = new ServiceResult<List<MemberProductExchange>>();
        try {
            serviceResult
                .setResult(memberProductExchangeModel.getExchangeListWithPrdAndOp(pager, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberProductExchangeService][getExchangeListWithPrdAndOp]取得用户换货列表时发生异常:",
                e);
        }
        return serviceResult;
    }
}