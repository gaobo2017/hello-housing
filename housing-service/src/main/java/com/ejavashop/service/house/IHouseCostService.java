package com.ejavashop.service.house;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.house.HousingCostDetail;
import com.ejavashop.vo.house.HousingCostVO;

/**
 * 房源成本管理
 *                       
 * @Filename: IHouseCostService.java
 * @Version: 1.0
 * @Author: gao
 * @Email: 
 *
 */
public interface IHouseCostService {

    /**
     * 根据id取得成本明细
     * @param  housingCostDetailId
     * @return
     */
    ServiceResult<HousingCostDetail> getHousingCostDetailById(Integer id);

    /**
     * 新增
     * @param HousingCostDetail
     * @return
     */
    ServiceResult<Integer> createHousingCostDetailAndSummaryCost(HousingCostDetail housingCostDetail);

    /**
     * 修改
     * @param HousingCostDetail
     * @return
     */
    ServiceResult<Integer> updateHousingCostDetail(HousingCostDetail housingCostDetail);

    /**
     * delete
     * @param housingCostDetail
     * @return
     */
    ServiceResult<Boolean> deleteHousingCostDetail(Integer housingCostDetail);

    /**
     * 定时任务 自动完成 <br>
     * <li>计算最近一次空置期天数：   对未出租房源 每天进行统计，修改空置期天数
     * @return
     */
    ServiceResult<Boolean> jobSystemVacancyDay();

    //    /**
    //     * 根据条件分页查询房源信息，PagerInfo传null取全部数据
    //     * @param queryMap
    //     * @param pager
    //     * @return
    //     */
    //    ServiceResult<List<HousingResources>> getHousingResourcesList(Map<String, String> queryMap,
    //                                                                  PagerInfo pager);
    /**
     * 根据条件分页查询房源总成本信息，PagerInfo传null取全部数据
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<HousingCostVO>> getHousingCostDetailList(Map<String, String> queryMap,
                                                                PagerInfo pager);

    /**
     * 根据条件分页查询房源总成本信息，PagerInfo传null取全部数据
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<HousingCostVO>> getHousingCostList(Map<String, String> queryMap,
                                                          PagerInfo pager);

}