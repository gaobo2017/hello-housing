package com.ejavashop.service.system;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.system.Code;

/**
 * 字典服务类
 *                       
 * @Filename: CodeService.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public interface ICodeService {

    /**
     * 获取字典数据。
     * @param queryMap
     * @param pager
     * @return ServiceResult<List<Code>>
     */
    ServiceResult<List<Code>> getCodes(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 根据条件获取单个字典数据。
     * @param codeDiv
     * @param codeCd
     * @return ServiceResult<Code>
     */
    ServiceResult<Code> getCode(String codeDiv, String codeCd);

    /**
     * 新增字典数据。
     * @param code
     * @return ServiceResult<Integer>
     */
    ServiceResult<Integer> createCode(Code code);

    /**
     * 修改字典数据。
     * @param code
     * @return ServiceResult<Boolean>
     */
    ServiceResult<Boolean> updateCode(Code code);
}
