package com.ejavashop.dao.shop.write.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberCollectionProduct;

/**
 * 会员收藏商品表
 * 
 * @Filename: MemberCollectionProductWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberCollectionProductWriteDao {

    MemberCollectionProduct get(java.lang.Integer id);

    Integer save(MemberCollectionProduct memberCollectionProduct);

    Integer update(MemberCollectionProduct memberCollectionProduct);

    Integer updateNotNull(MemberCollectionProduct memberCollectionProduct);

    /**
     * 根据会员ID获取会员收藏商品
     * @param memberId
     * @param start
     * @param size
     * @return
     */
    List<MemberCollectionProduct> getMemberCollectionProducts(@Param("memberId") Integer memberId,
                                                              @Param("start") Integer start,
                                                              @Param("size") Integer size);

    /**
     * 根据会员ID获取会员收藏商品数量
     * @param memberId
     * @return
     */
    Integer getMemberCollectionProductsCount(@Param("memberId") Integer memberId);

    Integer queryCount(Map<String, Object> queryMap);

    List<MemberCollectionProduct> queryList(Map<String, Object> map);

}
