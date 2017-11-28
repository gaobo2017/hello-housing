package com.ejavashop.dao.shop.write.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberCollectionSeller;

/**
 * 会员收藏商铺表
 * 
 * @Filename: MemberCollectionSellerWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberCollectionSellerWriteDao {

    /**
     * 根据店铺ID和用户ID获取店铺收藏
     * @param sellerId
     * @param memberId
     * @return
     */
    List<MemberCollectionSeller> getBySellerIdAndMId(@Param("sellerId") Integer sellerId,
                                                     @Param("memberId") Integer memberId);

    Integer save(MemberCollectionSeller memberCollectionSeller);

    Integer cancelCollectionSeller(@Param("sellerId") Integer sellerId,
                                   @Param("memberId") Integer memberId);

    MemberCollectionSeller get(java.lang.Integer id);

    Integer update(MemberCollectionSeller memberCollectionSeller);

    Integer updateNotNull(MemberCollectionSeller memberCollectionSeller);

    /**
     * 根据会员ID获取会员收藏商铺
     * @param memberId
     * @param start
     * @param size
     * @return
     */
    List<MemberCollectionSeller> getMemberCollectionSellers(@Param("memberId") Integer memberId,
                                                            @Param("start") Integer start,
                                                            @Param("size") Integer size);

    /**
     * 根据会员ID获取会员收藏商铺数量
     * @param memberId
     * @return
     */
    Integer getMemberCollectionSellersCount(@Param("memberId") Integer memberId);

    Integer queryCount(Map<String, Object> queryMap);

    List<MemberCollectionSeller> queryList(Map<String, Object> map);

}
