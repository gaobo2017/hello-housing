package com.ejavashop.service.member;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ejavashop.service.product.IProductFrontService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class ProductServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource(name = "productFrontService")
    IProductFrontService productFrontService;

    @Test
    public void testGetProducts() {
        Integer cateId = 1;
        Map<String, Object> stack = new HashMap<String, Object>();
        //存放参数
        Map<String, Object> mapCondition = new HashMap<String, Object>();
        mapCondition.put("page", 1);
        mapCondition.put("sort", 1);
        //        mapCondition.put("filterAll", "159_0");
        //        mapCondition.put("filterAll", "160_1");
        mapCondition.put("filterAll", "159_0-160_1");

        productFrontService.getProducts(cateId, mapCondition, stack);
    }

}
