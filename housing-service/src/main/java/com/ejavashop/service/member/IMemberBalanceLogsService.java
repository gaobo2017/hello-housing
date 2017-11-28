package com.ejavashop.service.member;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.MemberBalanceLogs;

public interface IMemberBalanceLogsService {

    /**
     * 根据会员ID获取会员账户余额变化日志
     * @param memberId 会员ID
     * @param pager 分页信息
     * @return
     */
    ServiceResult<List<MemberBalanceLogs>> getMemberBalanceLogs(Integer memberId, PagerInfo pager);

    /**
     * 更新会员账户余额变化日志表
     * @param memberBalanceLogs
     * @return
     */
    ServiceResult<Integer> updateMemberBalanceLogs(MemberBalanceLogs memberBalanceLogs);

    /**
     * 根据条件分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<MemberBalanceLogs>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 根据id取得会员账户余额变化日志表
     * @param  memberBalanceLogsId
     * @return
     */
    ServiceResult<MemberBalanceLogs> getMemberBalanceLogsById(Integer memberBalanceLogsId);

    /**
     * 保存会员账户余额变化日志表
     * @param  memberBalanceLogs
     * @return
     */
    ServiceResult<Integer> saveMemberBalanceLogs(MemberBalanceLogs memberBalanceLogs);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);
}