package com.ejavashop.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberCollectionProduct;

/**
 * 会员收藏商品
 *                       
 * @Filename: MemberCollectionProductReadDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberCollectionProductReadDao {

    MemberCollectionProduct get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<MemberCollectionProduct> queryList(Map<String, Object> map);

}
