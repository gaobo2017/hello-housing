package com.ejavashop.dao.shop.read.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberAddress;

/**
 * 收货地址
 *                       
 * @Filename: MemberAddressReadDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberAddressReadDao {

    MemberAddress get(java.lang.Integer id);

    /**
     * 根据会员ID获取会员地址
     * @param memberId
     * @param start
     * @param size
     * @return
     */
    List<MemberAddress> getMemberAddresses(@Param("memberId") Integer memberId,
                                           @Param("start") Integer start,
                                           @Param("size") Integer size);

    /**
     * 根据会员ID获取会员地址数量
     * @param memberId
     * @return
     */
    Integer getMemberAddressesCount(@Param("memberId") Integer memberId);
}
