package com.ejavashop.dao.shop.write.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberLoginLogs;

/**
 * 会员登录日志
 * 
 * @Filename: MemberLoginLogsWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberLoginLogsWriteDao {

    MemberLoginLogs get(java.lang.Integer id);

    Integer save(MemberLoginLogs memberLoginLogs);

    Integer update(MemberLoginLogs memberLoginLogs);

    Integer updateNotNull(MemberLoginLogs memberLoginLogs);

    /**
     * 根据会员ID获取会员登录日志
     * @param memberId
     * @param start
     * @param size
     * @return
     */
    List<MemberLoginLogs> getMemberLoginLogs(@Param("memberId") Integer memberId,
                                             @Param("start") Integer start,
                                             @Param("size") Integer size);

    /**
     * 根据会员ID获取会员登录日志数量
     * @param memberId
     * @return
     */
    Integer getMemberLoginLogsCount(@Param("memberId") Integer memberId);

    /**
     * 根据会员ID获取会员某个时间段的登录日志
     * @param memberId
     * @param startTime
     * @param endTime
     * @return
     */
    List<MemberLoginLogs> getByMemberIdAndTime(@Param("memberId") Integer memberId,
                                               @Param("startTime") String startTime,
                                               @Param("endTime") String endTime);
}
