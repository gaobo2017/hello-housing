package com.ejavashop.model.house;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.TimeUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.write.house.HousingCostDetailWriteDao;
import com.ejavashop.dao.shop.write.house.HousingCostWriteDao;
import com.ejavashop.dao.shop.write.house.HousingIncomeWriteDao;
import com.ejavashop.dao.shop.write.house.HousingLeaseWriteDao;
import com.ejavashop.dao.shop.write.house.HousingReminderWriteDao;
import com.ejavashop.dao.shop.write.house.HousingResourcesWriteDao;
import com.ejavashop.dao.shop.write.house.HousingVacancyDaysWriteDao;
import com.ejavashop.entity.house.HousingCost;
import com.ejavashop.entity.house.HousingIncome;
import com.ejavashop.entity.house.HousingResources;

@Component(value = "houseManageModel")
public class HouseManageModel {
    @Resource
    private HousingCostWriteDao          housingCostWriteDao;

    @Resource
    private HousingCostDetailWriteDao    housingCostDetailWriteDao;

    @Resource
    private HousingResourcesWriteDao     housingResourcesWriteDao;

    @Resource
    private HousingIncomeWriteDao        housingIncomeWriteDao;

    @Resource
    private HousingLeaseWriteDao         housingLeaseWriteDao;

    @Resource
    private HousingReminderWriteDao      housingReminderWriteDao;

    @Resource
    private HousingVacancyDaysWriteDao   housingVacancyDaysWriteDao;

    @Resource
    private DataSourceTransactionManager transactionManager;

    public Integer getHousingResourcesCount(Map<String, String> queryMap) {
        return housingResourcesWriteDao.getHousingResourcesCount(queryMap);
    }

    public List<HousingResources> getHousingResourcesList(Map<String, String> queryMap,
                                                          Integer start, Integer size) {
        return housingResourcesWriteDao.getHousingResourcesList(queryMap, start, size);
    }

    /**
     * 根据id取得房源信息
     * @param  housingResourcesId
     * @return
     */
    public HousingResources getHousingResourcesById(Integer housingResourcesId) {
        return housingResourcesWriteDao.get(housingResourcesId);
    }

    /**
     * 新增房源表
     * @param  housingResources
     * @return
     */

    public Integer createHousingResources(HousingResources housingResources) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            int hrId = housingResourcesWriteDao.insertSelective(housingResources);

            //重新统计 成本总表数据
            //            List<HousingCostDetail> housingCostDetailSumlist = housingCostDetailWriteDao
            //                .getHousingCostDetailSum(housingCostDetail.getCostId());

            HousingCost housingCost = new HousingCost();

            long days = TimeUtil.getDaysBetweenDates(housingResources.getContractStartTime(),
                housingResources.getContractEndTime()) + 1;//加一天

            housingCost.setHouseId(housingResources.getId());// insert后会赋值，insert前是null
            //            housingCost.setOperationId(housingResources.getOperationId());
            //            housingCost.setOperationName(housingResources.getOperationName());

            // 计算日租成本， day=合同结束日期-合同开始日期    ，四舍五入，保留2位小数
            BigDecimal dayRentCost = housingResources.getPricesSum().divide(new BigDecimal(days), 2,
                BigDecimal.ROUND_HALF_UP);

            housingCost.setDayRentCost(dayRentCost);

            housingCost.setPricesSum(housingResources.getPricesSum());

            housingCost.setAllCostSum(housingResources.getPricesSum());

            boolean isCreateHousingCost = housingCostWriteDao.insertSelective(housingCost) > 0;

            //该房源总收入表  初始化数据
            HousingIncome housingIncome = new HousingIncome();
            housingIncome.setHouseId(housingResources.getId());

            boolean isCreateIncome = housingIncomeWriteDao.insertSelective(housingIncome) > 0;

            if (hrId <= 0 || !isCreateHousingCost || !isCreateIncome) {
                throw new BusinessException(" 添加房源失败！");
            }

            transactionManager.commit(status);

            return 1;
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
            throw e;
        }

    }

    /**
    * 更新表
    * @param  housingResources
    * @return
    */

    public Integer updateHousingResources(HousingResources housingResources) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            int hrId = housingResourcesWriteDao.updateByPrimaryKeySelective(housingResources);

            HousingCost housingCost = housingCostWriteDao.selectByHouseId(housingResources.getId());

            long days = TimeUtil.getDaysBetweenDates(housingResources.getContractStartTime(),
                housingResources.getContractEndTime()) + 1;//加一天

            // 计算日租成本， day=合同结束日期-合同开始日期    ，四舍五入，保留2位小数
            BigDecimal dayRentCost = housingResources.getPricesSum().divide(new BigDecimal(days), 2,
                BigDecimal.ROUND_HALF_UP);

            housingCost.setDayRentCost(dayRentCost);

            housingCost.setPricesSum(housingResources.getPricesSum());

            housingCost.setAllCostSum(housingResources.getPricesSum()
                .add(housingCost.getRenovationCostSum()).add(housingCost.getOtherCostSum()));

            boolean isUpdateHousingCost = housingCostWriteDao.updateByPrimaryKey(housingCost) > 0;

            if (hrId <= 0 || !isUpdateHousingCost) {
                throw new BusinessException(" 添加房源失败！");
            }

            transactionManager.commit(status);

            return 1;
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
            throw e;
        }

    }

    /**
     *  1.删除房源数据           2.删除成本主表数据 
     *  3.删除成本详情数据  4.删除收入主表数据 
     *  5.删除租赁数据          6。删除提醒表数据  7.删除空置期数据  
     * @param  housingResources
     * @return
     */

    public Boolean deleteHousingResources(Integer housingResourcesId) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            boolean isDeleteResources = housingResourcesWriteDao
                .deleteByPrimaryKey(housingResourcesId) > 0;

            //删除成本主表  
            HousingCost housingCost = housingCostWriteDao.selectByHouseId(housingResourcesId);
            boolean isDeleteHousingCost = housingCostWriteDao
                .deleteByPrimaryKey(housingCost.getId()) > 0;

            //删除成本详情数据 
            housingCostDetailWriteDao.deleteByHouseId(housingResourcesId);

            //4.删除收入主表数据 
            HousingIncome housingIncome = housingIncomeWriteDao.selectByHouseId(housingResourcesId);

            boolean isDeleteIncome = housingIncomeWriteDao
                .deleteByPrimaryKey(housingIncome.getId()) > 0;

            //5.删除租赁数据          
            housingLeaseWriteDao.deleteByHouseId(housingResourcesId);

            //6。删除提醒表数据  
            housingReminderWriteDao.deleteByHouseId(housingResourcesId);

            //7.删除空置期数据  
            housingVacancyDaysWriteDao.deleteByHouseId(housingResourcesId);

            if (!isDeleteResources) {
                throw new BusinessException(" 删除房源失败！isDeleteResources=false");
            }

            if (!isDeleteHousingCost) {
                throw new BusinessException(" 删除房源失败！isDeleteHousingCost=false");
            }

            if (!isDeleteIncome) {
                throw new BusinessException(" 删除房源失败！isDeleteIncome=false");
            }

            transactionManager.commit(status);

            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
    //    public boolean delete(Integer id, Integer memberId) throws Exception {
    //        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    //        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    //        TransactionStatus status = transactionManager.getTransaction(def);
    //
    //        try {
    //            // 数据库中的申请
    //            SellerApply applyDb = sellerApplyWriteDao.get(id);
    //            if (applyDb.getState().intValue() != SellerApply.STATE_1_SEND
    //                && applyDb.getState().intValue() != SellerApply.STATE_4_FAIL) {
    //                throw new BusinessException("只能删除提交申请和审核失败状态的商家申请！");
    //            }
    //
    //            // 删除商家入驻申请
    //            boolean applyDel = sellerApplyWriteDao.delete(id) > 0;
    //            if (!applyDel) {
    //                throw new BusinessException("删除商家入驻申请时失败！");
    //            }
    //
    //            Seller sellerDb = sellerWriteDao.getSellerByMemberId(memberId);
    //            if (sellerDb.getAuditStatus().intValue() != Seller.AUDIT_STATE_1_SEND) {
    //                throw new BusinessException("只能删除提交申请状态的商家申请！");
    //            }
    //
    //            //删除商家账号
    //            boolean sellerDel = sellerWriteDao.deleteByMemberId(memberId) > 0;
    //            if (!sellerDel) {
    //                throw new BusinessException("删除商家入驻申请时失败（删除关联账号时）！");
    //            }
    //
    //            transactionManager.commit(status);
    //
    //            return applyDel && sellerDel;
    //        } catch (Exception e) {
    //            transactionManager.rollback(status);
    //            throw e;
    //        }
    //    }
    //

}
