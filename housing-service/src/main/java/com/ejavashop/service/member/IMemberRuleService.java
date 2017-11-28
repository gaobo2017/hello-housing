package com.ejavashop.service.member;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.MemberRule;

/**
 * 会员经验值和积分规则接口
 * 
 * @Filename: IMemberRuleService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IMemberRuleService {

    /**
     * 根据id取得会员经验值和积分规则
     * @param  memberRuleId
     * @return
     */
    ServiceResult<MemberRule> getMemberRule(Integer memberRuleId);

    //    /**
    //     * 保存会员经验值和积分规则
    //     * @param  memberRule
    //     * @return
    //     */
    //    ServiceResult<Integer> saveMemberRule(MemberRule memberRule);

    /**
    * 更新会员经验值和积分规则
    * @param  memberRule
    * @return
    */
    ServiceResult<Integer> updateMemberRule(MemberRule memberRule);
}