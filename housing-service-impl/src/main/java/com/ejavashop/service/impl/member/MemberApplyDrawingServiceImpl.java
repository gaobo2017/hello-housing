package com.ejavashop.service.impl.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.MemberApplyDrawing;
import com.ejavashop.model.member.MemberApplyDrawingModel;
import com.ejavashop.service.member.IMemberApplyDrawingService;

@Service(value = "memberApplyDrawingService")
public class MemberApplyDrawingServiceImpl implements IMemberApplyDrawingService {
    private static Logger           log = LogManager.getLogger(MemberApplyDrawingServiceImpl.class);

    @Resource
    private MemberApplyDrawingModel memberApplyDrawingModel;

    @Override
    public ServiceResult<MemberApplyDrawing> getMemberApplyDrawing(Integer memberApplyDrawingId) {
        ServiceResult<MemberApplyDrawing> result = new ServiceResult<MemberApplyDrawing>();
        try {
            result.setResult(memberApplyDrawingModel.getMemberApplyDrawing(memberApplyDrawingId));
        } catch (Exception e) {
            log.error("根据id[" + memberApplyDrawingId + "]取得会员提款申请时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + memberApplyDrawingId + "]取得会员提款申请时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> saveMemberApplyDrawing(MemberApplyDrawing memberApplyDrawing) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberApplyDrawingModel.saveMemberApplyDrawing(memberApplyDrawing));
        } catch (Exception e) {
            log.error("保存会员提款申请时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存会员提款申请时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateMemberApplyDrawing(MemberApplyDrawing memberApplyDrawing) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberApplyDrawingModel.updateMemberApplyDrawing(memberApplyDrawing));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新会员提款申请时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新会员提款申请时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<MemberApplyDrawing>> getMemberApplyDrawings(Map<String, String> queryMap,
                                                                          PagerInfo pager) {
        ServiceResult<List<MemberApplyDrawing>> serviceResult = new ServiceResult<List<MemberApplyDrawing>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(memberApplyDrawingModel,
                "Property 'memberApplyDrawingModel' is required.");

            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(memberApplyDrawingModel.getMemberApplyDrawingsCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(memberApplyDrawingModel.getMemberApplyDrawings(queryMap, start,
                size));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberApplyDrawingService][getMemberApplyDrawings]查询会员退款申请信息发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> auditing(List<Integer> ids, Integer optId, String optName) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            Assert.notNull(memberApplyDrawingModel,
                "Property 'memberApplyDrawingModel' is required.");

            serviceResult.setResult(memberApplyDrawingModel.auditing(ids, optId, optName));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberApplyDrawingService][auditing]审核会员退款申请发生异常:", e);
        }
        return serviceResult;
    }
}