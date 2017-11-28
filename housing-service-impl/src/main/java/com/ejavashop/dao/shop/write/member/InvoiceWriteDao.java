package com.ejavashop.dao.shop.write.member;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.Invoice;

@Repository
public interface InvoiceWriteDao {

    Invoice get(java.lang.Integer id);

    Integer save(Invoice invoice);

    Integer update(Invoice invoice);

    Integer delete(Integer id);

    /**
     * 根据创建人获取发票信息，只取state为1（显示）的记录
     * @param memberId
     * @return
     */
    List<Invoice> getByMId(Integer memberId);

}
