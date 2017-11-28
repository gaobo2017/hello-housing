package com.ejavashop.dao.shop.write.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberProductBack;

@Repository
public interface MemberProductBackWriteDao {

    MemberProductBack get(java.lang.Integer id);

    Integer save(MemberProductBack memberProductBack);

    Integer update(MemberProductBack memberProductBack);

    Integer getCount(@Param("param1") Map<String, String> queryMap);

    List<MemberProductBack> page(@Param("param1") Map<String, String> queryMap,
                                 @Param("start") Integer start, @Param("size") Integer size);

    List<MemberProductBack> list();

    Integer del(Integer id);

    Integer queryCount(Map<String, Object> map);

    List<MemberProductBack> queryList(Map<String, Object> map);
}
