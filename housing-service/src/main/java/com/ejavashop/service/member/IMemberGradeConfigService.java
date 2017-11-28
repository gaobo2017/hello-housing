package com.ejavashop.service.member;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.MemberGradeConfig;

/**
 * 会员等级配置表接口
 * 
 * @Filename: IMemberGradeConfigService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IMemberGradeConfigService {

    /**
     * 根据id取得会员等级配置表
     * @param  memberGradeConfigId
     * @return
     */
    ServiceResult<MemberGradeConfig> getMemberGradeConfig(Integer memberGradeConfigId);

    //    /**
    //     * 保存会员等级配置表
    //     * @param  memberGradeConfig
    //     * @return
    //     */
    //    ServiceResult<Integer> saveMemberGradeConfig(MemberGradeConfig memberGradeConfig);

    /**
    * 更新会员等级配置表
    * @param  memberGradeConfig
    * @return
    */
    ServiceResult<Integer> updateMemberGradeConfig(MemberGradeConfig memberGradeConfig);
}