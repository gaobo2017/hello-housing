package com.ejavashop.dao.shop.write.member;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberRule;

/**
 * 会员经验值和积分规则
 * 
 * @Filename: MemberRuleWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberRuleWriteDao {

    /**
     * 根据ID及状态获取规则
     * <li>当state传入null时根据ID取数据
     * <li>当state不为null时根据ID及状态取数据
     * 
     * @param id
     * @return
     */
    MemberRule get(@Param("id") java.lang.Integer id, @Param("state") java.lang.Integer state);

    // Integer save(MemberRule memberRule);

    /**
     * 更新规则
     * @param memberRule
     * @return
     */
    Integer update(MemberRule memberRule);

    //Integer updateNotNull(MemberRule memberRule);

}
