package com.ejavashop.dao.shop.write.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.system.Code;

/**
 * 用于获取商城数据字典的数据访问接口。 
 *                       
 * @Filename: CodeDao.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Repository
public interface CodeWriteDao {
    /**
     * 获取字典数据。
     * @param queryMap
     * @param start
     * @param size
     * @return List<Code>
     */
    public List<Code> getCodes(Map<String, String> queryMap, @Param("start") Integer start,
                               @Param("size") Integer size);

    /**
     * 获取数据个数。
     * @param queryMap
     * @return Integer
     */
    public Integer getCodesCount(Map<String, String> queryMap);

    /**
     * 获取字典数据。
     * @param queryMap
     * @return List<Code>
     */
    public List<Code> getCodes(Map<String, ?> queryMap);

    /**
     * 根据条件获取单个字典数据。
     * @param codeDiv
     * @param codeCd
     * @return Code
     */
    public Code getCode(@Param("codeDiv") String codeDiv, @Param("codeCd") String codeCd);

    /**
     * 新增字典数据。
     * @param code
     * @return int
     */
    public int createCode(Code code);

    /**
     * 修改字典数据。
     * @param code
     * @return int
     */
    public int updateCode(Code code);
}
