package com.ejavashop.service.impl.member;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.MemberGradeConfig;
import com.ejavashop.model.member.MemberGradeConfigModel;
import com.ejavashop.service.member.IMemberGradeConfigService;

@Service(value = "memberGradeConfigService")
public class MemberGradeConfigServiceImpl implements IMemberGradeConfigService {
    private static Logger          log = LogManager.getLogger(MemberGradeConfigServiceImpl.class);

    @Resource
    private MemberGradeConfigModel memberGradeConfigModel;

    @Override
    public ServiceResult<MemberGradeConfig> getMemberGradeConfig(Integer memberGradeConfigId) {
        ServiceResult<MemberGradeConfig> result = new ServiceResult<MemberGradeConfig>();
        try {
            result.setResult(memberGradeConfigModel.getMemberGradeConfig(memberGradeConfigId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberGradeConfigService][getMemberGradeConfig]查询会员等级配置表时发生异常:", e);
        }
        return result;
    }

    //    /**
    //     * 保存会员等级配置表
    //     * @param  memberGradeConfig
    //     * @return
    //     */
    //    @Override
    //    public ServiceResult<Integer> saveMemberGradeConfig(MemberGradeConfig memberGradeConfig) {
    //        ServiceResult<Integer> result = new ServiceResult<Integer>();
    //        try {
    //            result.setResult(memberGradeConfigModel.save(memberGradeConfig));
    //        } catch (Exception e) {
    //            log.error("保存会员等级配置表时出现未知异常：" + e);
    //            result.setSuccess(false);
    //            result.setMessage("保存会员等级配置表时出现未知异常");
    //        }
    //        return result;
    //    }

    @Override
    public ServiceResult<Integer> updateMemberGradeConfig(MemberGradeConfig memberGradeConfig) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberGradeConfigModel.updateMemberGradeConfig(memberGradeConfig));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberGradeConfigService][updateMemberGradeConfig]更新会员等级配置表时发生异常:", e);
        }
        return result;
    }
}