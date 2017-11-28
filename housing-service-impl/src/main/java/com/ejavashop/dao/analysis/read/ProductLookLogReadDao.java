package com.ejavashop.dao.analysis.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.analysis.ProductLookLog;

@Repository
public interface ProductLookLogReadDao {

    ProductLookLog get(java.lang.Integer id);

    /**
     * 根据条件获取用户商品浏览信息
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<ProductLookLog> getProductLookLogs(@Param("queryMap") Map<String, String> queryMap,
                                            @Param("start") Integer start,
                                            @Param("size") Integer size);

    /**
     * 根据条件获取用户商品浏览数量
     * @param queryMap
     * @return
     */
    Integer getProductLookLogsCount(@Param("queryMap") Map<String, String> queryMap);
}