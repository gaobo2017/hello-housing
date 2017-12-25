package com.ejavashop.model.house;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.TimeUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.write.house.HousingCostWriteDao;
import com.ejavashop.dao.shop.write.house.HousingIncomeWriteDao;
import com.ejavashop.dao.shop.write.house.HousingLeaseWriteDao;
import com.ejavashop.dao.shop.write.house.HousingResourcesWriteDao;
import com.ejavashop.dao.shop.write.house.HousingVacancyDaysWriteDao;
import com.ejavashop.entity.house.HousingCost;
import com.ejavashop.entity.house.HousingIncome;
import com.ejavashop.entity.house.HousingLease;
import com.ejavashop.entity.house.HousingResources;
import com.ejavashop.entity.house.HousingVacancyDays;
import com.ejavashop.vo.house.HousingLeaseVO;

@Component(value = "houseLeaseModel")
public class HouseLeaseModel {

    @Resource
    private HousingLeaseWriteDao         housingLeaseWriteDao;

    @Resource
    private HousingIncomeWriteDao        housingIncomeWriteDao;

    @Resource
    private DataSourceTransactionManager transactionManager;

    @Resource
    private HousingResourcesWriteDao     housingResourcesWriteDao;

    @Resource
    private HousingVacancyDaysWriteDao   housingVacancyDaysWriteDao;

    @Resource
    private HousingCostWriteDao          housingCostWriteDao;

    /**
     * 根据id取得租赁息
     * @param  housingResourcesId
     * @return
     */
    public HousingLease getHousingLeaseById(Integer housingLeaseId) {
        return housingLeaseWriteDao.selectByPrimaryKey(housingLeaseId);
    }

    /**
     * 更新表
     * @param  housingCostDetail
     * @return
     */

    public Integer updateHousingLease(HousingLease housingLease) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            //=====================租赁数据====================================
            HousingCost housingCost = housingCostWriteDao
                .selectByHouseId(housingLease.getHouseId()); 
            //单记录
            //                housingLease.setStatus(1);//租期内
            //出租天数
            long days = TimeUtil.getDaysBetweenDates(housingLease.getLeaseStartTime(),
                housingLease.getLeaseEndTime()) + 1;
            // 计算日租收入， day=合同结束日期-合同开始日期    ，四舍五入，保留2位小数
            BigDecimal dayRentIncome = housingLease.getAllRent().divide(new BigDecimal(days), 2,
                BigDecimal.ROUND_HALF_UP);
            housingLease.setDayRentIncome(dayRentIncome);

            housingLease.setDayRentCost(housingCost.getDayRentCost());

            BigDecimal RentIncome = housingLease.getDayRentIncome().multiply(new BigDecimal(days))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal RentCost = housingLease.getDayRentCost().multiply(new BigDecimal(days))
                .setScale(2, BigDecimal.ROUND_HALF_UP);

            housingLease.setGrossProfit(RentIncome.subtract(RentCost));//毛利润  毛利润=  出租天数 * 平均每日房租收入 -  出租天数*平均每日房租成本

            boolean isUpdateHousingLease = housingLeaseWriteDao
                .updateByPrimaryKeySelective(housingLease) > 0;
            //=====================重新统计 income总表数据====================================
            //重新统计 income总表数据   all_rent_sum  gross_profit_sum  pure_profit_sum
            HousingLease housingLeaseSum = housingLeaseWriteDao
                .getHousingLeaseSum(housingLease.getHouseId());

            HousingIncome housingIncome = housingIncomeWriteDao
                .selectByHouseId(housingLease.getHouseId());

            housingIncome.setAllRentSum(housingLeaseSum.getAllRent()); //该房 全部租金  多次

            housingIncome.setGrossProfitSum(housingLeaseSum.getGrossProfit()); //毛利润 多次

            housingIncome.setPureProfitSum(
                housingIncome.getAllRentSum().subtract(housingCost.getAllCostSum())
                    .add(housingIncome
                        .getRentIncomeAgainSum().subtract(housingIncome.getReturnRentCostSum()))); //存利润  该房纯利润= 房租总和 - 总成本   +退租补缴费用收入 - 返还房租总额

            boolean isUpdatehousingIncome = housingIncomeWriteDao
                .updateByPrimaryKeySelective(housingIncome) > 0;
            //=========================================================

            //===================修改空置期表 ======================================

            HousingResources housingResources = housingResourcesWriteDao
                .get(housingLease.getHouseId());

            HousingVacancyDays housingVacancyDays = housingVacancyDaysWriteDao
                .selectByLeaseId(housingLease.getId());
            housingVacancyDays.setHouseId(housingLease.getHouseId());
            //            housingVacancyDays.setLeaseId(housingLease.getId());

            if (housingResources.getLastSoldTime() == null) {//有问题 应该用 该房的上一次租赁  是否有记录，来判断
                housingVacancyDays.setVacancyStartTime(housingResources.getContractStartTime());//房源有效期内，出租事件触发。 出租合同开始日期-该房源上次最近租房合同结束日期+1（如果0次出租，从收房合同开始日期计算） 
            } else {
                housingVacancyDays.setVacancyStartTime(
                    TimeUtil.getBeforeOrAfterDay(housingResources.getLastSoldTime(), 1));//房源有效期内，出租事件触发。 出租合同开始日期-该房源上次最近租房合同结束日期+1（如果0次出租，从收房合同开始日期计算）
            }

            housingVacancyDays.setVacancyEndTime(housingLease.getLeaseStartTime());
            long vacanDay = TimeUtil.getDaysBetweenDates(housingVacancyDays.getVacancyStartTime(),
                housingVacancyDays.getVacancyEndTime());

            housingVacancyDays.setVacancyDay(Integer.valueOf(((int) vacanDay)));
            boolean isUpdatehousingVacancyDays = housingVacancyDaysWriteDao
                .updateByPrimaryKey(housingVacancyDays) > 0;

            //=========================================================
            //===================修改 cost表  空置期天数 ======================================
            //房子出租事件, 空置期累加到 空置期总天数,  vacancy_day清零.?????????已经清零重新计算 拿租赁记录计算。统计。

            housingCost.setVacancyDays(housingCost.getVacancyDays() + housingCost.getVacancyDay());
            housingCost.setVacancyDay(0);

            boolean isUpdateHousingCost = housingCostWriteDao.updateByPrimaryKey(housingCost) > 0;

            //=========================================================
            //===================修改房源 出租状态======================================

            housingResources.setIsSold(1);//出租
            housingResources.setLastSoldTime(housingLease.getLeaseEndTime());//该房源最近租房合同结束日期

            boolean isUpdateHousingResources = housingResourcesWriteDao
                .updateByPrimaryKeySelective(housingResources) > 0;
            //=========================================================

            if (!isUpdateHousingLease) {
                throw new BusinessException(" 修改租赁明细失败！ isUpdateHousingLease=false");
            } else if (!isUpdatehousingIncome) {
                throw new BusinessException(" 修改租赁明细失败！isUpdatehousingIncome=false");
            } else if (!isUpdateHousingResources) {
                throw new BusinessException(" 修改租赁明细失败！isUpdateHousingResources=false");
            } else if (!isUpdateHousingCost) {
                throw new BusinessException(" 修改租赁明细失败！isUpdateHousingCost=false");
            } else if (!isUpdatehousingVacancyDays) {
                throw new BusinessException(" 修改租赁明细失败！isUpdatehousingVacancyDays=false");
            }

            return 1;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
    
    
    /**
     * 退租事件
     * @param  housingCostDetail
     * @return
     */

    public Integer cancelLeaseHousingLease(HousingLease housingLease) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            //=====================租赁数据====================================

            //单记录
            //                housingLease.setStatus(1);//租期内
            housingLease.setStatus(3);// 3提前到期  2正常到期  1租期内
            
            boolean isUpdateHousingLease = housingLeaseWriteDao
                .updateByPrimaryKeySelective(housingLease) > 0;
                
                
            //=====================重新统计 income总表数据====================================
            //重新统计 income总表数据   RentIncomeAgainSum  ReturnRentCostSum  PureProfitSum
                HousingCost housingCost = housingCostWriteDao
                        .selectByHouseId(housingLease.getHouseId()); 

            HousingIncome housingIncome = housingIncomeWriteDao
                .selectByHouseId(housingLease.getHouseId());


            housingIncome.setRentIncomeAgainSum(housingIncome.getRentIncomeAgainSum().add(housingLease.getRentIncomeAgain()));//累加
            housingIncome.setReturnRentCostSum(housingIncome.getReturnRentCostSum().add(housingLease.getReturnRentCost()));//累加
            housingIncome.setPureProfitSum(
                housingIncome.getAllRentSum().subtract(housingCost.getAllCostSum())
                    .add(housingIncome.getRentIncomeAgainSum().subtract(housingIncome.getReturnRentCostSum()))); //存利润  该房纯利润= 房租总和 - 总成本   +退租补缴费用收入 - 返还房租总额

            boolean isUpdatehousingIncome = housingIncomeWriteDao
                .updateByPrimaryKeySelective(housingIncome) > 0;
            //=========================================================

            //===================修改房源 出租状态======================================
            HousingResources housingResources = housingResourcesWriteDao
                    .get(housingLease.getHouseId());
            housingResources.setIsSold(0);//1出租  0 未租
            housingResources.setLastSoldTime(housingLease.getFinalLeaveTime());//该房源最近租房实际结束日期

            boolean isUpdateHousingResources = housingResourcesWriteDao
                .updateByPrimaryKeySelective(housingResources) > 0;
            //=========================================================

            if (!isUpdateHousingLease) {
                throw new BusinessException(" 修改租赁明细失败！ isUpdateHousingLease=false");
            } else if (!isUpdatehousingIncome) {
                throw new BusinessException(" 修改租赁明细失败！isUpdatehousingIncome=false");
            } else if (!isUpdateHousingResources) {
                throw new BusinessException(" 修改租赁明细失败！isUpdateHousingResources=false");
            } 

            return 1;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
    
    public Integer getHousingLeaseCount(Map<String, String> queryMap) {
        return housingLeaseWriteDao.getHousingLeaseCount(queryMap);
    }

    /**
     * 新增房源表
     * @param  housingLease
     * @return
     */

    public Integer createHousingLease(HousingLease housingLease) {
        return housingLeaseWriteDao.insertSelective(housingLease);
    }

    /**
     * 新增租赁明细表
     * @param  housingCostDetail
     * @return
     */

    public Integer createHousingLeaseAndSummaryIncome(HousingLease housingLease) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            //=====================租赁数据====================================
            HousingCost housingCost = housingCostWriteDao
                .selectByHouseId(housingLease.getHouseId());
            //单记录
            housingLease.setStatus(1);//租期内
            //出租天数
            long days = TimeUtil.getDaysBetweenDates(housingLease.getLeaseStartTime(),
                housingLease.getLeaseEndTime()) + 1;
            // 计算日租收入， day=合同结束日期-合同开始日期    ，四舍五入，保留2位小数
            BigDecimal dayRentIncome = housingLease.getAllRent().divide(new BigDecimal(days), 2,
                BigDecimal.ROUND_HALF_UP);
            housingLease.setDayRentIncome(dayRentIncome);

            housingLease.setDayRentCost(housingCost.getDayRentCost());

            BigDecimal RentIncome = housingLease.getDayRentIncome().multiply(new BigDecimal(days))
                .setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal RentCost = housingLease.getDayRentCost().multiply(new BigDecimal(days))
                .setScale(2, BigDecimal.ROUND_HALF_UP);

            housingLease.setGrossProfit(RentIncome.subtract(RentCost));//毛利润  毛利润=  出租天数 * 平均每日房租收入 -  出租天数*平均每日房租成本

            boolean isCreateHousingLease = housingLeaseWriteDao.insertSelective(housingLease) > 0;

            //=====================重新统计 income总表数据====================================
            //重新统计 income总表数据   all_rent_sum  gross_profit_sum  pure_profit_sum
            HousingLease housingLeaseSum = housingLeaseWriteDao
                .getHousingLeaseSum(housingLease.getHouseId());

            HousingIncome housingIncome = housingIncomeWriteDao
                .selectByHouseId(housingLease.getHouseId());

            housingIncome.setAllRentSum(housingLeaseSum.getAllRent()); //该房 全部租金  多次

            housingIncome.setGrossProfitSum(housingLeaseSum.getGrossProfit()); //毛利润 多次
            
            housingIncome.setPureProfitSum(
                housingIncome.getAllRentSum().subtract(housingCost.getAllCostSum())
                    .add(housingIncome
                        .getRentIncomeAgainSum().subtract(housingIncome.getReturnRentCostSum()))); //存利润  该房纯利润= 房租总和 - 总成本  +退租补缴费用收入 - 返还房租总额

            boolean isUpdatehousingIncome = housingIncomeWriteDao
                .updateByPrimaryKeySelective(housingIncome) > 0;
            //=========================================================

            //===================修改空置期表 ======================================

            HousingResources housingResources = housingResourcesWriteDao
                .get(housingLease.getHouseId());

            HousingVacancyDays housingVacancyDays = new HousingVacancyDays();
            housingVacancyDays.setHouseId(housingLease.getHouseId());
            housingVacancyDays.setLeaseId(housingLease.getId());

            if (housingResources.getLastSoldTime() == null) {
                housingVacancyDays.setVacancyStartTime(housingResources.getContractStartTime());//房源有效期内，出租事件触发。 出租合同开始日期-该房源上次最近租房合同结束日期+1（如果0次出租，从收房合同开始日期计算） 
            } else {
                housingVacancyDays.setVacancyStartTime(
                    TimeUtil.getBeforeOrAfterDay(housingResources.getLastSoldTime(), 1));//房源有效期内，出租事件触发。 出租合同开始日期-该房源上次最近租房合同结束日期+1（如果0次出租，从收房合同开始日期计算）
            }

            housingVacancyDays.setVacancyEndTime(housingLease.getLeaseStartTime());
            long vacanDay = TimeUtil.getDaysBetweenDates(housingVacancyDays.getVacancyStartTime(),
                housingVacancyDays.getVacancyEndTime());

            housingVacancyDays.setVacancyDay(Integer.valueOf(((int) vacanDay)));
            boolean isCreatehousingVacancyDays = housingVacancyDaysWriteDao
                .insertSelective(housingVacancyDays) > 0;

            //=========================================================
            //===================修改 cost表  空置期天数 ======================================
            //房子出租事件, 空置期累加到 空置期总天数,  vacancy_day清零.

            housingCost.setVacancyDays(housingCost.getVacancyDays() + housingCost.getVacancyDay());
            housingCost.setVacancyDay(0);

            boolean isUpdateHousingCost = housingCostWriteDao.updateByPrimaryKey(housingCost) > 0;

            //=========================================================
            //===================修改房源 出租状态======================================

            housingResources.setIsSold(1);//出租
            housingResources.setLastSoldTime(housingLease.getLeaseEndTime());//该房源最近租房合同结束日期

            boolean isUpdateHousingResources = housingResourcesWriteDao
                .updateByPrimaryKeySelective(housingResources) > 0;
            //=========================================================

            if (!isCreateHousingLease) {
                throw new BusinessException(" 添加租赁明细失败！ isCreateHousingLease=false");
            } else if (!isUpdatehousingIncome) {
                throw new BusinessException(" 添加租赁明细失败！isUpdatehousingIncome=false");
            } else if (!isUpdateHousingResources) {
                throw new BusinessException(" 添加租赁明细失败！isUpdateHousingResources=false");
            } else if (!isUpdateHousingCost) {
                throw new BusinessException(" 添加租赁明细失败！isUpdateHousingCost=false");
            } else if (!isCreatehousingVacancyDays) {
                throw new BusinessException(" 添加租赁明细失败！isCreatehousingVacancyDays=false");
            }

            transactionManager.commit(status);

            return 1;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

    }

    /**
     * 删除成本明细
     * @param  housingResources
     * @return
     */

    public Boolean deleteHousingLease(Integer housingLeaseId) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            //            HousingLease housingLease = housingLeaseWriteDao.selectByPrimaryKey(housingLeaseId);
            //            boolean isDeteleHousingCostDetail = housingLeaseWriteDao
            //                .deleteByPrimaryKey(housingLeaseId) > 0;
            //
            //            //重新统计 成本总表数据
            //            List<HousingLease> housingCostDetailSumlist = housingLeaseWriteDao
            //                .getHousingLeaseSum(housingLease.getHouseId());
            //
            //            HousingCost housingCost = housingCostWriteDao
            //                .selectByPrimaryKey(housingLease.getCostId());
            //
            //            for (HousingCostDetail CostDetailSum : housingCostDetailSumlist) {
            //
            //                if (HousingCostDetail.COST_TYPE_1 == CostDetailSum.getCostType().intValue()) {
            //                    housingCost.setRenovationCostSum(CostDetailSum.getMoney());// 装修费统计
            //                } else {
            //                    housingCost.setOtherCostSum(CostDetailSum.getMoney()); //其他费用统计
            //                }
            //            }
            //
            //            //总成本=装修成本总额+其他成本总额+房源总价
            //            housingCost.setAllCostSum(housingCost.getRenovationCostSum()
            //                .add(housingCost.getOtherCostSum()).add(housingCost.getPricesSum()));
            //
            //            boolean isCreateHousingCost = housingCostWriteDao
            //                .updateByPrimaryKeySelective(housingCost) > 0;
            //
            //            if (!isDeteleHousingCostDetail || !isCreateHousingCost) {
            //                throw new BusinessException(" 删除成本明细失败！");
            //            }
            //
            //            transactionManager.commit(status);

            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

    }

    public List<HousingLeaseVO> getHousingLeaseList(Map<String, String> queryMap, Integer start,
                                                    Integer size) throws IllegalAccessException,
                                                                  InvocationTargetException,
                                                                  NoSuchMethodException {

        List<HousingLeaseVO> volist = new ArrayList<HousingLeaseVO>();

        List<HousingLease> list = housingLeaseWriteDao.getHousingLeaseList(queryMap, start, size);

        for (HousingLease hlease : list) {

            HousingResources hr = housingResourcesWriteDao.get(hlease.getHouseId());

            HousingLeaseVO vo = new HousingLeaseVO();

            PropertyUtils.copyProperties(vo, hr);

            PropertyUtils.copyProperties(vo, hlease);

            volist.add(vo);
        }
        return volist;
    }

    public List<HousingLeaseVO> getHousingIncomeList(Map<String, String> queryMap, Integer start,
                                                     Integer size) throws IllegalAccessException,
                                                                   InvocationTargetException,
                                                                   NoSuchMethodException {

        List<HousingLeaseVO> volist = new ArrayList<HousingLeaseVO>();

        //        List<HousingIncome> list = housingIncomeWriteDao.getHousingIncomeList(queryMap, start,
        //            size);
        //
        //        for (HousingIncome cost : list) {
        //
        //            HousingResources hr = housingResourcesWriteDao.get(cost.getHouseId());
        //
        //            HousingLeaseVO vo = new HousingLeaseVO();
        //
        //            PropertyUtils.copyProperties(vo, hr);
        //
        //            PropertyUtils.copyProperties(vo, cost);
        //
        //            volist.add(vo);
        //        }
        return volist;
    }

}
