package com.ejavashop.service.impl.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.system.Code;
import com.ejavashop.model.system.CodeModel;
import com.ejavashop.service.system.ICodeService;

/**
 * 字典服务类
 *                       
 * @Filename: CodeServiceImpl.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Service(value = "codeService")
public class CodeServiceImpl implements ICodeService {

    private static Logger log = LogManager.getLogger(CodeServiceImpl.class);

    @Resource
    private CodeModel codeModel;

    @Override
    public ServiceResult<List<Code>> getCodes(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<Code>> serviceResult = new ServiceResult<List<Code>>();
        serviceResult.setPager(pager);
        try {
            serviceResult.setResult(codeModel.getCodes(queryMap, pager));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[CodeService][getCodes]查询数据字典发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 根据条件获取单个字典数据。
     * @param codeDiv
     * @param codeCd
     * @return ServiceResult<Code>
     */
    @Override
    public ServiceResult<Code> getCode(String codeDiv, String codeCd) {
        ServiceResult<Code> serviceResult = new ServiceResult<Code>();
        try {
            serviceResult.setResult(codeModel.getCode(codeDiv, codeCd));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[CodeService][getCode]查询数据字典发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 新增字典数据。
     * @param code
     * @return ServiceResult<Integer>
     */
    @Override
    public ServiceResult<Integer> createCode(Code code) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(codeModel.createCode(code));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[CodeService][createCode]创建数据字典发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 修改字典数据。
     * @param code
     * @return ServiceResult<Boolean>
     */
    @Override
    public ServiceResult<Boolean> updateCode(Code code) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(codeModel.updateCode(code));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[CodeService][updateCode]更新数据字典发生异常:", e);
        }
        return serviceResult;
    }
}
