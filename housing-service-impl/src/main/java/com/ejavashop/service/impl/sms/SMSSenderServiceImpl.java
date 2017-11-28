package com.ejavashop.service.impl.sms;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.model.sms.SMSSendModel;
import com.ejavashop.service.impl.order.AdminComplaintServiceImpl;
import com.ejavashop.service.sms.ISenderService;

/**
 * 短信发送服务
 *                       
 * @Filename: SMSSender.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihailong@javamalls.com
 *
 */
@Service(value = "senderService")
public class SMSSenderServiceImpl implements ISenderService {
    private static Logger log = LogManager.getLogger(AdminComplaintServiceImpl.class);
    @Resource(name = "SMSSendModel")
    private SMSSendModel  SMSSendModel;

    @Override
    public ServiceResult<Boolean> sendSMS(Map<String, Object> map) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            SMSSendModel.sendSMS(map);
            result.setResult(Boolean.TRUE);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setSuccess(false);
                result.setMessage(e.getMessage());
            } else {
                e.printStackTrace();
                log.error("短信发送出现未知异常：" + e);
                result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
                result.setMessage("短信发送出现未知异常");
            }
        }
        return result;
    }

    @Override
    public ServiceResult<Object> sendSMSWidthCallable(Map<String, Object> map) {
        ServiceResult<Object> result = new ServiceResult<Object>();
        try {
            result.setResult(SMSSendModel.sendSMSWidthCallable(map));
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setSuccess(false);
                result.setMessage(e.getMessage());
            } else {
                e.printStackTrace();
                log.error("短信发送出现未知异常：" + e);
                result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
                result.setMessage("短信发送出现未知异常");
            }
        }
        return result;
    }

    @Override
    public ServiceResult<Object> sendVerifyCode(String mobile) {
        ServiceResult<Object> result = new ServiceResult<Object>();
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("mobile", mobile);
            result.setResult(SMSSendModel.sendVerifyCode(param));
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setSuccess(false);
                result.setMessage(e.getMessage());
                log.debug(e.getMessage());
            } else {
                e.printStackTrace();
                log.error("短信发送出现未知异常：" + e);
                result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
                result.setMessage("短信发送出现未知异常");
            }
        }
        return result;
    }

}
