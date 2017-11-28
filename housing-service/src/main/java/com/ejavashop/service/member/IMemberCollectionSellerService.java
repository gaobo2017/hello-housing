package com.ejavashop.service.member;

import java.util.List;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.MemberCollectionSeller;
import com.ejavashop.vo.member.FrontMemberCollectionSellerVO;

/**
 * 用户收藏店铺
 *                       
 * @Filename: IMemberCollectionSellerService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IMemberCollectionSellerService {

    /**
     * 会员收藏商铺
     * @param sellerId
     * @param memberId
     * @return 如果会员已经收藏过该店铺，返回true，收藏成功返回true
     */
    ServiceResult<Boolean> collectionSeller(Integer sellerId, Integer memberId);

    /**
     * 取消收藏商铺
     * @param sellerId
     * @param memberId
     * @return
     */
    ServiceResult<Boolean> cancelCollectionSeller(Integer sellerId, Integer memberId);

    /**
     * 根据id取得会员收藏商铺表
     * @param  memberCollectionSellerId
     * @return
     */
    ServiceResult<MemberCollectionSeller> getMemberCollectionSellerById(Integer memberCollectionSellerId);

    /**
     * 根据会员id取得会员收藏商铺表
     * @param request
     * @param pager
     * @return
     */
    ServiceResult<List<FrontMemberCollectionSellerVO>> getCollectionSellerByMemberid(Integer memberId,
                                                                                     PagerInfo pager);

    /**
     * 根据用户ID和店铺ID获取收藏信息
     * @param sellerId
     * @param memberId
     * @return
     */
    ServiceResult<List<MemberCollectionSeller>> getBySellerIdAndMId(Integer sellerId,
                                                                    Integer memberId);
}