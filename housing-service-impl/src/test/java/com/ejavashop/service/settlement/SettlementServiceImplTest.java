package com.ejavashop.service.settlement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ejavashop.core.ServiceResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:testApplicationContext.xml")
public class SettlementServiceImplTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource(name = "settlementService")
    ISettlementService settlementService;

    @Override
    public void setDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    @Test
    public void testJobSettlement() {
        ServiceResult<Boolean> jobSettlement = settlementService.jobSettlement();
        System.out.println(jobSettlement);
    }

}
