package com.ejavashop.model.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.write.member.MemberRuleWriteDao;
import com.ejavashop.entity.member.MemberRule;

@Component(value = "memberRuleModel")
public class MemberRuleModel {

    @Resource
    private MemberRuleWriteDao memberRuleWriteDao;

    /**
    * 根据id取得会员经验值和积分规则
    * @param  memberRuleId
    * @return
    */
    public MemberRule getMemberRule(Integer memberRuleId) {
        return memberRuleWriteDao.get(memberRuleId, null);
    }

    //    /**
    //     * 保存会员经验值和积分规则
    //     * @param  memberRule
    //     * @return
    //     */
    //    public Integer saveMemberRule(MemberRule memberRule) {
    //        return memberRuleWriteDao.save(memberRule);
    //    }

    /**
    * 更新会员经验值和积分规则
    * @param  memberRule
    * @return
    */
    public Integer updateMemberRule(MemberRule memberRule) {
        return memberRuleWriteDao.update(memberRule);
    }
}
