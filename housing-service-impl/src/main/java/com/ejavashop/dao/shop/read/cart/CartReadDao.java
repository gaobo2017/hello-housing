package com.ejavashop.dao.shop.read.cart;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.cart.Cart;

@Repository
public interface CartReadDao {

    // 移到write dao
    // List<Cart> getByMemberId(Integer memberId);

    Cart get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<Cart> queryList(Map<String, Object> map);

    /**
     * 根据用户ID获取用户购物车数量
     * @param memberId
     * @return
     */
    Integer getCartNumberByMId(Integer memberId);
}
