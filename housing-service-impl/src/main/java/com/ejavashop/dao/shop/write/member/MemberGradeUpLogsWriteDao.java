package com.ejavashop.dao.shop.write.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberGradeUpLogs;

/**
 * 会员等级升级日志表
 * 
 * @Filename: MemberGradeUpLogsWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberGradeUpLogsWriteDao {

    MemberGradeUpLogs get(java.lang.Integer id);

    Integer save(MemberGradeUpLogs memberGradeUpLogs);

    Integer update(MemberGradeUpLogs memberGradeUpLogs);

    Integer updateNotNull(MemberGradeUpLogs memberGradeUpLogs);

    /**
     * 根据会员ID获取会员等级升级日志
     * @param memberId
     * @param start
     * @param size
     * @return
     */
    List<MemberGradeUpLogs> getMemberGradeUpLogs(@Param("memberId") Integer memberId,
                                                 @Param("start") Integer start,
                                                 @Param("size") Integer size);

    /**
     * 根据会员ID获取会员等级升级日志数量
     * @param memberId
     * @return
     */
    Integer getMemberGradeUpLogsCount(@Param("memberId") Integer memberId);

}
