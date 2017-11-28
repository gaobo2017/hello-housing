package com.ejavashop.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerComplaint;

/**
 * 投诉申请及审核
 *                       
 * @Filename: SellerComplaintReadDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface SellerComplaintReadDao {

    SellerComplaint get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<SellerComplaint> queryList(Map<String, Object> map);

}
