package com.ejavashop.dao.shop.write.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.Member;

/**
 * 会员表
 * 
 * @Filename: MemberWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberWriteDao {

    Member get(java.lang.Integer id);

    /**
     * 根据条件获取用户信息
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<Member> getMembers(@Param("queryMap") Map<String, String> queryMap,
                            @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 根据条件获取用户数量
     * @param queryMap
     * @return
     */
    Integer getMembersCount(@Param("queryMap") Map<String, String> queryMap);

    /**
     * 更新用户经验值及等级</br>
     * 经验值采用grade_value=grade_value+变更值的方式
     * @param member
     * @return
     */
    Integer updateGrade(Member member);

    /**
     * 更新用户积分</br>
     * 经验值采用integral=integral+变更值的方式
     * @param member
     * @return
     */
    Integer updateIntegral(Member member);

    /**
     * 更新用户余额</br>
     * 余额采用balance=balance+变更值的方式
     * @param member
     * @return
     */
    Integer updateBalance(Member member);

    /**
     * 更新用户经验值、积分值、等级</br>
     * 采用【字段】=【字段】+【变更值】的方式
     * @param member
     * @return
     */
    Integer updateValue(Member member);

    Integer save(Member member);

    Integer update(Member member);

    Integer updateNotNull(Member member);

    /**
     * 根据用户名和密码获取用户
     * @param name
     * @param password
     * @return
     */
    List<Member> getByNameAndPwd(@Param("name") String name, @Param("password") String password);

    /**
     * 根据会员name获取会员
     * @param name
     * @return
     */
    List<Member> getByName(@Param("name") String name);
}
