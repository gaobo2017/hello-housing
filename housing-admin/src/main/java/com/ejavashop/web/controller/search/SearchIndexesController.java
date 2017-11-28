package com.ejavashop.web.controller.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.search.SearchSetting;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.search.ISearchSettingService;
import com.ejavashop.web.controller.BaseController;

/**
 * 索引初始化
 *                       
 * @Filename: SearchIndexesController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/searchIndexes")
public class SearchIndexesController extends BaseController {

    @Resource
    private ISearchSettingService searchSettingService;

    @Resource
    private IProductService       productService;

    private static final String   solrUrl      = DomainUrlUtil.getEJS_SOLR_URL();
    private static final String   solrServer   = DomainUrlUtil.getEJS_SOLR_SERVER();

    /**
     * 每次处理索引的最大行数
     */
    private static final int      INDEX_NUMBER = 1000;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        return "admin/search/searchIndexes";
    }

    @RequestMapping(value = "operation", method = { RequestMethod.POST })
    public String doAdd(Map<String, Object> dataMap) throws Exception {
        ServiceResult<SearchSetting> serviceResult = searchSettingService
            .getSearchSettingById(ConstantsEJS.SEARCHSETTINGID);
        SearchSetting searchSetting = serviceResult.getResult();

        searchSetting.setIndexProductId(SearchSetting.INDEX_PRODUCT_ID_0);
        ServiceResult<Integer> serviceResultUpdate = searchSettingService
            .updateSearchSetting(searchSetting);
        if (!serviceResultUpdate.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResultUpdate.getCode())) {
                throw new RuntimeException(serviceResultUpdate.getMessage());
            } else {
                throw new BusinessException(serviceResultUpdate.getMessage());
            }
        }

        ServiceResult<Product> resultMax = productService.getProductByMax();
        if (!resultMax.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(resultMax.getCode())) {
                throw new RuntimeException(resultMax.getMessage());
            } else {
                throw new BusinessException(resultMax.getMessage());
            }
        }

        Product product = resultMax.getResult();

        deleteByQuery(product.getId().intValue());

        dataMap.put("message", "初始化成功");
        return "admin/search/searchIndexes";
    }

    public static SolrClient getSolrClient() {
        return new HttpSolrClient(solrUrl + "/" + solrServer);
    }

    /**
     * 删除索引
     */
    public static void deleteByQuery(int maxId) {
        SolrClient client = getSolrClient();

        int num = maxId / INDEX_NUMBER;
        try {
            List<String> list = null;
            for (int i = 0; i < num; i++) {
                list = new ArrayList<String>();
                for (int j = (i * INDEX_NUMBER + 1); j <= ((i + 1) * INDEX_NUMBER); j++) {
                    list.add(j + "");
                }

                client.deleteById(list);
                client.commit();
            }

            list = new ArrayList<String>();
            for (int i = (num * INDEX_NUMBER + 1); i <= maxId; i++) {
                list.add(i + "");
            }

            client.deleteById(list);
            client.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
