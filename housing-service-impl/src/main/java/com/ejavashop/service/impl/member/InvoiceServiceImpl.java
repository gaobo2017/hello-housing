package com.ejavashop.service.impl.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.Invoice;
import com.ejavashop.model.member.InvoiceModel;
import com.ejavashop.service.member.IInvoiceService;

@Service(value = "invoiceService")
public class InvoiceServiceImpl implements IInvoiceService {
    private static Logger log = LogManager.getLogger(InvoiceServiceImpl.class);

    @Resource
    private InvoiceModel  invoiceModel;

    @Override
    public ServiceResult<List<Invoice>> getInvoiceByMId(Integer memberId) {
        ServiceResult<List<Invoice>> serviceResult = new ServiceResult<List<Invoice>>();
        try {
            serviceResult.setResult(invoiceModel.getInvoiceByMId(memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[InvoiceService][getInvoiceByMId]根据用户ID获取发票信息表时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[InvoiceService][getInvoiceByMId]根据用户ID获取发票信息表时发生异常:", e);
        }
        return serviceResult;
    }

    /**
    * 根据id取得发票信息表
    * @param  invoiceId
    * @return
    */
    @Override
    public ServiceResult<Invoice> getInvoiceById(Integer invoiceId) {
        ServiceResult<Invoice> result = new ServiceResult<Invoice>();
        try {
            result.setResult(invoiceModel.getInvoiceById(invoiceId));
        } catch (Exception e) {
            log.error("根据id[" + invoiceId + "]取得发票信息表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + invoiceId + "]取得发票信息表时出现未知异常");
        }
        return result;
    }

    /**
     * 保存发票信息表
     * @param invoice
     * @param memberId
     * @return
     */
    @Override
    public ServiceResult<Integer> saveInvoice(Invoice invoice, Integer memberId) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();

        Assert.notNull(invoiceModel, "Property 'frontInvoiceModel' is required.");
        try {
            serviceResult.setResult(invoiceModel.saveInvoice(invoice, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[InvoiceService][saveInvoice]保存发票表时发生异常:", e);
        }
        return serviceResult;
    }

    /**
    * 更新发票信息表
    * @param  invoice
    * @return
    */
    @Override
    public ServiceResult<Integer> updateInvoice(Invoice invoice) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(invoiceModel.updateInvoice(invoice));
        } catch (Exception e) {
            log.error("更新发票信息表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新发票信息表时出现未知异常");
        }
        return result;
    }

    /**
     * 删除发票
     * @param invoiceId
     * @param request
     * @return
     */
    @Override
    public ServiceResult<Boolean> delInvoice(Integer invoiceId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();

        Assert.notNull(invoiceModel, "Property 'frontInvoiceModel' is required.");
        try {
            serviceResult.setResult(invoiceModel.delInvoice(invoiceId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[InvoiceService][delInvoice]删除发票发生异常:", e);
        }
        return serviceResult;
    }
}