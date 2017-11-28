package com.ejavashop.service.impl.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.seller.SellerComplaint;
import com.ejavashop.model.order.ComplaintModel;
import com.ejavashop.service.order.IAdminComplaintService;
import com.ejavashop.vo.seller.SellerComplaintVO;

@Service(value = "adminComplaintServiceImpl")
public class AdminComplaintServiceImpl implements IAdminComplaintService {
    private static Logger  log = LogManager.getLogger(AdminComplaintServiceImpl.class);

    @Resource(name = "complaintModel")
    private ComplaintModel complaintModel;

    /**
    * 根据id取得商家投诉管理
    * @param  sellerComplaintId
    * @return
    */
    @Override
    public ServiceResult<SellerComplaint> getSellerComplaintById(Integer sellerComplaintId) {
        ServiceResult<SellerComplaint> result = new ServiceResult<SellerComplaint>();
        try {
            result.setResult(complaintModel.getSellerComplaintById(sellerComplaintId));
        } catch (Exception e) {
            log.error("根据id[" + sellerComplaintId + "]取得商家投诉管理时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + sellerComplaintId + "]取得商家投诉管理时出现未知异常");
        }
        return result;
    }

    /**
     * 保存商家投诉管理
     * @param  sellerComplaint
     * @return
     */
    @Override
    public ServiceResult<Integer> saveSellerComplaint(SellerComplaint sellerComplaint,
                                                      Member member) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(complaintModel.saveSellerComplaint(sellerComplaint, member));
        } catch (Exception e) {
            log.error("保存商家投诉管理时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存商家投诉管理时出现未知异常");
        }
        return result;
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
            result.setResult(complaintModel.updateSellerComplaint(sellerComplaint));
        } catch (Exception e) {
            log.error("更新商家投诉管理时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新商家投诉管理时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SellerComplaintVO>> page(Map<String, String> queryMap,
                                                       PagerInfo pager) {
        ServiceResult<List<SellerComplaintVO>> serviceResult = new ServiceResult<List<SellerComplaintVO>>();
        serviceResult.setPager(pager);
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(complaintModel.pageCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            param.put("start", start);
            param.put("size", size);
            List<SellerComplaintVO> list = complaintModel.page(param);

            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerComplaintServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SellerComplaintServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> sr = new ServiceResult<Boolean>();
        try {
            sr.setResult(complaintModel.del(id));
        } catch (Exception e) {
            log.error("[SellerComplaintServiceImpl][del] exception:" + e.getMessage());
            e.printStackTrace();
            sr.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
        }
        return sr;
    }

    @Override
    public ServiceResult<SellerComplaintVO> getById(Integer id) {
        ServiceResult<SellerComplaintVO> res = new ServiceResult<SellerComplaintVO>();
        try {

            res.setResult(complaintModel.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
            res.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerComplaintServiceImpl][getById] exception:", e);
        }
        return res;
    }
}