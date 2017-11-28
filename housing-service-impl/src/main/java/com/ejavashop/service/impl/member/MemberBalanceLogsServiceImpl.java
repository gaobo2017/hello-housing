package com.ejavashop.service.impl.member;

import java.util.HashMap;
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
import com.ejavashop.entity.member.MemberBalanceLogs;
import com.ejavashop.model.member.MemberBalanceLogsModel;
import com.ejavashop.service.member.IMemberBalanceLogsService;

@Service(value = "memberBalanceLogsService")
public class MemberBalanceLogsServiceImpl implements IMemberBalanceLogsService {
    private static Logger          log = LogManager.getLogger(MemberBalanceLogsServiceImpl.class);

    @Resource
    private MemberBalanceLogsModel memberBalanceLogsModel;

    @Override
    public ServiceResult<List<MemberBalanceLogs>> getMemberBalanceLogs(Integer memberId,
                                                                       PagerInfo pager) {
        ServiceResult<List<MemberBalanceLogs>> serviceResult = new ServiceResult<List<MemberBalanceLogs>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(memberBalanceLogsModel.getMemberBalanceLogsCount(memberId));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult
                .setResult(memberBalanceLogsModel.getMemberBalanceLogs(memberId, start, size));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[MemberService][getMemberBalanceLogs]根据会员ID获取会员账户余额变化日志发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][getMemberBalanceLogs]根据会员ID获取会员账户余额变化日志发生异常:", e);
        }
        return serviceResult;
    }

    /**
    * 根据id取得会员账户余额变化日志表
    * @param  memberBalanceLogsId
    * @return
    */
    @Override
    public ServiceResult<MemberBalanceLogs> getMemberBalanceLogsById(Integer memberBalanceLogsId) {
        ServiceResult<MemberBalanceLogs> result = new ServiceResult<MemberBalanceLogs>();
        try {
            result.setResult(memberBalanceLogsModel.getMemberBalanceLogsById(memberBalanceLogsId));
        } catch (Exception e) {
            log.error("根据id[" + memberBalanceLogsId + "]取得会员账户余额变化日志表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + memberBalanceLogsId + "]取得会员账户余额变化日志表时出现未知异常");
        }
        return result;
    }

    /**
     * 保存会员账户余额变化日志表
     * @param  memberBalanceLogs
     * @return
     */
    @Override
    public ServiceResult<Integer> saveMemberBalanceLogs(MemberBalanceLogs memberBalanceLogs) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberBalanceLogsModel.saveMemberBalanceLogs(memberBalanceLogs));
        } catch (Exception e) {
            log.error("保存会员账户余额变化日志表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存会员账户余额变化日志表时出现未知异常");
        }
        return result;
    }

    /**
    * 更新会员账户余额变化日志表
    * @param  memberBalanceLogs
    * @return
    */
    @Override
    public ServiceResult<Integer> updateMemberBalanceLogs(MemberBalanceLogs memberBalanceLogs) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberBalanceLogsModel.updateMemberBalanceLogs(memberBalanceLogs));
        } catch (Exception e) {
            log.error("更新会员账户余额变化日志表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新会员账户余额变化日志表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<MemberBalanceLogs>> page(Map<String, String> queryMap,
                                                       PagerInfo pager) {
        ServiceResult<List<MemberBalanceLogs>> serviceResult = new ServiceResult<List<MemberBalanceLogs>>();
        serviceResult.setPager(pager);
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(memberBalanceLogsModel.pageCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            param.put("start", start);
            param.put("size", size);
            List<MemberBalanceLogs> list = memberBalanceLogsModel.page(param);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[MemberBalanceLogsServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[MemberBalanceLogsServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> sr = new ServiceResult<Boolean>();
        try {
            sr.setResult(memberBalanceLogsModel.del(id));
        } catch (Exception e) {
            log.error("[MemberBalanceLogsServiceImpl][del] exception:" + e.getMessage());
            e.printStackTrace();
        }
        return sr;
    }
}