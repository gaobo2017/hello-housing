package com.ejavashop.dao.shop.write.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberAddress;

/**
 * 收货地址
 * 
 * @Filename: MemberAddressWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberAddressWriteDao {

    MemberAddress get(java.lang.Integer id);

    Integer save(MemberAddress memberAddress);

    Integer update(MemberAddress memberAddress);

    Integer updateNotNull(MemberAddress memberAddress);

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

    /**
     * 更新用户其他地址为非默认字段
     * @param id 默认地址ID
     * @param memberId 用户ID
     * @return
     */
    Integer updateNotDefByMId(@Param("id") Integer id, @Param("memberId") Integer memberId);

    /**
     * 根据ID删除地址
     * @param id 地址id
     * @return
     */
    Integer delete(@Param("id") Integer id);

    /**
     * 根据ID和会员ID删除地址
     * @param id
     * @param memberId
     * @return
     */
    Integer deleteByIdAndMId(@Param("id") Integer id, @Param("memberId") Integer memberId);
}
