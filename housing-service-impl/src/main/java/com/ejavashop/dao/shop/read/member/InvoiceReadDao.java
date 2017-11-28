package com.ejavashop.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.Invoice;

@Repository
public interface InvoiceReadDao {

    Invoice get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<Invoice> queryList(Map<String, Object> map);

}
