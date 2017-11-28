package com.ejavashop.service.member;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.MemberApplyDrawing;

/**
 * 会员提款申请接口
 * 
 * @Filename: IMemberApplyDrawingService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IMemberApplyDrawingService {

    /**
     * 根据id取得会员提款申请
     * @param  memberApplyDrawingId
     * @return
     */
    ServiceResult<MemberApplyDrawing> getMemberApplyDrawing(Integer memberApplyDrawingId);

    /**
     * 根据条件取得会员提款申请
     * @param  queryMap
     * @param  pager
     * @return
     */
    ServiceResult<List<MemberApplyDrawing>> getMemberApplyDrawings(Map<String, String> queryMap,
                                                                   PagerInfo pager);

    /**
     * 保存会员提款申请
     * @param  memberApplyDrawing
     * @return
     */
    ServiceResult<Integer> saveMemberApplyDrawing(MemberApplyDrawing memberApplyDrawing);

    /**
    * 更新会员提款申请
    * @param  memberApplyDrawing
    * @return
    */
    ServiceResult<Integer> updateMemberApplyDrawing(MemberApplyDrawing memberApplyDrawing);

    /**
     * 会员提款申请批量审核
     * @param ids 申请ID
     * @param optId 操作人ID
     * @param optName 操作人名称
     * @return
     */
    ServiceResult<Integer> auditing(List<Integer> ids, Integer optId, String optName);

}