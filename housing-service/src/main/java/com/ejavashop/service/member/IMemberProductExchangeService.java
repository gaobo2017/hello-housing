package com.ejavashop.service.member;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberProductExchange;

public interface IMemberProductExchangeService {

    /**
     * 根据id取得用户换货
     * @param  memberProductExchangeId
     * @return
     */
    ServiceResult<MemberProductExchange> getMemberProductExchangeById(Integer memberProductExchangeId);

    /**
     * 根据登录用户取得用户换货列表 分页
     * @param pager
     * @param request
     * @return
     */
    ServiceResult<List<MemberProductExchange>> getMemberProductExchangeList(Map<String, Object> queryMap,
                                                                            PagerInfo pager,
                                                                            Integer memberId);

    /**
     * 保存用户换货
     * @param memberProductExchange
     * @param request
     * @return
     */
    ServiceResult<Boolean> saveMemberProductExchange(MemberProductExchange memberProductExchange,
                                                     Member member);

    /**
    * 更新用户换货
    * @param  memberProductExchange
    * @return
    */
    ServiceResult<Boolean> updateMemberProductExchange(MemberProductExchange memberProductExchange);

    /**
    * 根据条件分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<MemberProductExchange>> getMemberProductExchanges(Map<String, String> queryMap,
                                                                         PagerInfo pager);

    /**
     * 根据登录用户取得用户换货列表(封装商品对象和网单对象)
     * @param pager
     * @param memberId
     * @return
     */
    ServiceResult<List<MemberProductExchange>> getExchangeListWithPrdAndOp(PagerInfo pager,
                                                                           Integer memberId);

}