package com.ejavashop.service.impl.member;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.MemberRule;
import com.ejavashop.model.member.MemberRuleModel;
import com.ejavashop.service.member.IMemberRuleService;

@Service(value = "memberRuleService")
public class MemberRuleServiceImpl implements IMemberRuleService {
    private static Logger   log = LogManager.getLogger(MemberRuleServiceImpl.class);

    @Resource
    private MemberRuleModel memberRuleModel;

    @Override
    public ServiceResult<MemberRule> getMemberRule(Integer memberRuleId) {
        ServiceResult<MemberRule> result = new ServiceResult<MemberRule>();
        try {
            result.setResult(memberRuleModel.getMemberRule(memberRuleId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberRuleService][getMemberRule]查询会员经验值和积分规则发生异常:", e);
        }
        return result;
    }

    //    /**
    //     * 保存会员经验值和积分规则
    //     * @param  memberRule
    //     * @return
    //     */
    //    @Override
    //    public ServiceResult<Integer> saveMemberRule(MemberRule memberRule) {
    //        ServiceResult<Integer> result = new ServiceResult<Integer>();
    //        try {
    //            result.setResult(memberRuleModel.save(memberRule));
    //        } catch (Exception e) {
    //            log.error("保存会员经验值和积分规则时出现未知异常：" + e);
    //            result.setSuccess(false);
    //            result.setMessage("保存会员经验值和积分规则时出现未知异常");
    //        }
    //        return result;
    //    }

    @Override
    public ServiceResult<Integer> updateMemberRule(MemberRule memberRule) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberRuleModel.updateMemberRule(memberRule));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberRuleService][updateMemberRule]更新会员经验值和积分规则发生异常:", e);
        }
        return result;
    }
}