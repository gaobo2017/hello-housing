package com.ejavashop.service.impl.seller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.seller.SellerComplaint;
import com.ejavashop.model.seller.SellerComplaintModel;
import com.ejavashop.service.seller.ISellerComplaintService;
import com.ejavashop.vo.seller.FrontSellerComplaintVO;

/**
 * 投诉管理
 *
 */
@Service(value = "sellerComplaintService")
public class SellerComplaintServiceImpl implements ISellerComplaintService {
    private static Logger        log = LogManager.getLogger(SellerComplaintServiceImpl.class);

    @Resource
    private SellerComplaintModel sellerComplaintModel;

    /**
    * 根据id取得商家投诉管理
    * @param  sellerComplaintId
    * @return
    */
    @Override
    public ServiceResult<SellerComplaint> getSellerComplaintById(Integer sellerComplaintId) {
        ServiceResult<SellerComplaint> result = new ServiceResult<SellerComplaint>();
        try {
            result.setResult(sellerComplaintModel.getSellerComplaintById(sellerComplaintId));
        } catch (Exception e) {
            log.error("根据id[" + sellerComplaintId + "]取得商家投诉管理时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + sellerComplaintId + "]取得商家投诉管理时出现未知异常");
        }
        return result;
    }

    /**
     * 保存商家投诉管理
     * @param sellerComplaint
     * @return
     */
    @Override
    public ServiceResult<SellerComplaint> saveSellerComplaint(Member member,
                                                              SellerComplaint sellerComplaint) {
        ServiceResult<SellerComplaint> serviceResult = new ServiceResult<SellerComplaint>();
        try {
            serviceResult
                .setResult(sellerComplaintModel.saveSellerComplaint(member, sellerComplaint));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[sellerComplaintService][saveSellerComplaint]保存申诉申请时发生异常:", e);
        }
        return serviceResult;
    }

    /**
    * 更新商家投诉管理
    * @param  sellerComplaint
    * @return
    */
    @Override
    public ServiceResult<Integer> updateSellerComplaint(SellerComplaint sellerComplaint) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerComplaintModel.updateSellerComplaint(sellerComplaint));
        } catch (Exception e) {
            log.error("更新商家投诉管理时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新商家投诉管理时出现未知异常");
        }
        return result;
    }

    /**
     * 根据登录用户获得申诉列表
     * @param pager
     * @return
     */
    @Override
    public ServiceResult<List<FrontSellerComplaintVO>> getSellerComplaintList(PagerInfo pager,
                                                                              Integer memberId) {
        ServiceResult<List<FrontSellerComplaintVO>> serviceResult = new ServiceResult<List<FrontSellerComplaintVO>>();
        try {
            serviceResult.setResult(sellerComplaintModel.getSellerComplaintList(pager, memberId));
            return serviceResult;
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[sellerComplaintService][getSellerComplaintList]取得用户申诉列表时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<FrontSellerComplaintVO>> getComplaintListWithPrdAndOp(PagerInfo pager,
                                                                                    Integer memberId) {
        ServiceResult<List<FrontSellerComplaintVO>> serviceResult = new ServiceResult<List<FrontSellerComplaintVO>>();
        try {
            serviceResult
                .setResult(sellerComplaintModel.getComplaintListWithPrdAndOp(pager, memberId));
            return serviceResult;
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[sellerComplaintService][getComplaintListWithPrdAndOp]取得用户申诉列表时发生异常:", e);
        }
        return serviceResult;
    }
}