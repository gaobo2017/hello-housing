package com.ejavashop.dao.shop.write.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberProductExchange;

@Repository
public interface MemberProductExchangeWriteDao {

    MemberProductExchange get(Integer id);

    Integer save(MemberProductExchange ex);

    Integer update(MemberProductExchange ex);

    Integer getCount(@Param("param1") Map<String, String> queryMap);

    List<MemberProductExchange> page(@Param("param1") Map<String, String> queryMap,
                                     @Param("start") Integer start, @Param("size") Integer size);

    List<MemberProductExchange> list();

    Integer queryCount(Map<String, Object> map);

    List<MemberProductExchange> queryList(Map<String, Object> map);
}
