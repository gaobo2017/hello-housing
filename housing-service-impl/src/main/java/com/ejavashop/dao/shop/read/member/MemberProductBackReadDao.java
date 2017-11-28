package com.ejavashop.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberProductBack;

/**
 * 退货申请
 *                       
 * @Filename: MemberProductBackReadDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberProductBackReadDao {

    MemberProductBack get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<MemberProductBack> queryList(Map<String, Object> map);

    /**
     * 根据店铺ID获取在时间段内已退过款得退货申请（退款状态是2和3）
     * @param sellerId 商家ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<MemberProductBack> getSettleBacks(@Param("sellerId") Integer sellerId,
                                           @Param("startTime") String startTime,
                                           @Param("endTime") String endTime,
                                           @Param("start") Integer start,
                                           @Param("size") Integer size);

    Integer getSettleBacksCount(@Param("sellerId") Integer sellerId,
                                @Param("startTime") String startTime,
                                @Param("endTime") String endTime);
}
