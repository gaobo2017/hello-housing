package com.ejavashop.dao.shop.write.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberApplyDrawing;

/**
 * 会员提款申请
 * 
 * @Filename: MemberApplyDrawingWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberApplyDrawingWriteDao {

    MemberApplyDrawing get(java.lang.Integer id);

    Integer save(MemberApplyDrawing memberApplyDrawing);

    Integer update(MemberApplyDrawing memberApplyDrawing);

    Integer updateNotNull(MemberApplyDrawing memberApplyDrawing);

    /**
     * 根据条件获取会员提款申请
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<MemberApplyDrawing> getMemberApplyDrawings(@Param("queryMap") Map<String, String> queryMap,
                                                    @Param("start") Integer start,
                                                    @Param("size") Integer size);

    /**
     * 根据条件获取会员提款申请
     * @param queryMap
     * @return
     */
    Integer getMemberApplyDrawingsCount(@Param("queryMap") Map<String, String> queryMap);

    /**
     * 批量审核退款申请
     * @param ids
     * @param state
     * @param optId
     * @return
     */
    Integer auditing(@Param("ids") List<Integer> ids, @Param("state") Integer state,
                     @Param("optId") Integer optId, @Param("optName") String optName);
}
