package com.ejavashop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.search.SearchKeyword;
import com.ejavashop.service.search.ISearchSettingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class SearchServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource(name = "searchSettingService")
    ISearchSettingService searchSettingService;

    @Test
    public void testGetSearchKeywords() {
        Map<String, String> queryMap = new HashMap<>();
        PagerInfo pager = new PagerInfo();
        pager.setPageSize(10);

        ServiceResult<List<SearchKeyword>> serviceResult = searchSettingService.getSearchKeywords(
            queryMap, pager);
        List<SearchKeyword> searchKeywords = serviceResult.getResult();
        for (SearchKeyword searchKeyword : searchKeywords) {
            System.out.println(searchKeyword.getKeyword());
        }

    }
}
