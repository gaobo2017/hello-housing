package com.ejavashop.dao.shop.read.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberBalanceLogs;

/**
 * MemberBalanceLogs
 * 
 * @Filename: MemberBalanceLogsWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberBalanceLogsReadDao {

    MemberBalanceLogs get(java.lang.Integer id);

    /**
     * 根据会员ID获取会员账户余额变化日志
     * @param memberId
     * @param start
     * @param size
     * @return
     */
    List<MemberBalanceLogs> getMemberBalanceLogs(@Param("memberId") Integer memberId,
                                                 @Param("start") Integer start,
                                                 @Param("size") Integer size);

    /**
     * 根据会员ID获取会员账户余额变化日志数量
     * @param memberId
     * @return
     */
    Integer getMemberBalanceLogsCount(@Param("memberId") Integer memberId);
}
