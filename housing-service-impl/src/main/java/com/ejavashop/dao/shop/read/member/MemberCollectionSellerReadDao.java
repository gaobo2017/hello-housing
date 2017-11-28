package com.ejavashop.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberCollectionSeller;

/**
 * 收藏店铺
 *                       
 * @Filename: MemberCollectionSellerReadDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberCollectionSellerReadDao {

    MemberCollectionSeller get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<MemberCollectionSeller> queryList(Map<String, Object> map);

    Integer getCountBySellerId(Integer sellerId);
}
