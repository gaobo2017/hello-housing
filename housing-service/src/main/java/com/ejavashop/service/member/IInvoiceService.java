package com.ejavashop.service.member;

import java.util.List;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.Invoice;

public interface IInvoiceService {

    /**
     * 根据用户ID获取发票信息表，只取状态为1显示的记录（state=1）
     * @param memberId
     * @return
     */
    ServiceResult<List<Invoice>> getInvoiceByMId(Integer memberId);

    /**
     * 根据id取得发票信息表
     * @param  invoiceId
     * @return
     */
    ServiceResult<Invoice> getInvoiceById(Integer invoiceId);

    /**
     * 保存发票信息表
     * @param invoice
     * @param memberId
     * @return
     */
    ServiceResult<Integer> saveInvoice(Invoice invoice, Integer memberId);

    /**
    * 更新发票信息表
    * @param  invoice
    * @return
    */
    ServiceResult<Integer> updateInvoice(Invoice invoice);

    /**
     * 删除发票
     * @param invoiceId
     * @return
     */
    ServiceResult<Boolean> delInvoice(Integer invoiceId);
}