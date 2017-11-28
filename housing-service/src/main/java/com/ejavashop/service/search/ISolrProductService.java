package com.ejavashop.service.search;

import com.ejavashop.core.ServiceResult;

/**
 * solr相关的操作
 *                       
 * @Filename: ISolrProductService.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public interface ISolrProductService {

    /**
     * 创建索引
     */
    ServiceResult<Boolean> jobCreateIndexesSolr(String solrUrl, String solrServer);

    /**
     * 更新敏感词的索引值
     * @param id
     * @return
     */
    ServiceResult<Boolean> jobUpdateSearchRecordIndex(String solrUrl, String solrServer);
}
