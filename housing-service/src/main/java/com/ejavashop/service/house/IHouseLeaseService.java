package com.ejavashop.service.house;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.house.HousingLease;
import com.ejavashop.vo.house.HousingCostVO;
import com.ejavashop.vo.house.HousingLeaseVO;

/**
 * 房源出租管理
 *                       
 * @Filename: IHouseLeaseService.java
 * @Version: 1.0
 * @Author: gao
 * @Email: 
 *
 */
public interface IHouseLeaseService {

    /**
     * 根据id取得成本明细
     * @param  housingCostDetailId
     * @return
     */
    ServiceResult<HousingLease> getHousingLeaseById(Integer id);

    /**
     * 修改
     * @param HousingLease
     * @return
     */
    ServiceResult<Integer> updateHousingLease(HousingLease housingLease);
   
    /**
     * 退租
     * @param HousingLease
     * @return
     */
    ServiceResult<Integer> cancelLeaseHousingLease(HousingLease housingLease);
    /**
     * 新增
     * @param HousingLease
     * @return
     */
    ServiceResult<Integer> createHousingLease(HousingLease housingLease);

    /**
     * 新增
     * @param HousingLease
     * @return
     */
    ServiceResult<Integer> createHousingLeaseAndSummaryIncome(HousingLease housingLease);

    //    /**
    //     * 修改
    //     * @param HousingLease
    //     * @return
    //     */
    //    ServiceResult<Integer> updateHousingLease(HousingLease housingCostDetail);

    /**
     * delete
     * @param HousingLease
     * @return
     */
    ServiceResult<Boolean> deleteHousingLease(Integer housingLease);

    //
    //    /**
    //     * 删除该商家申请,同时删除该商家账号
    //     * @param id
    //     * @return
    //     */
    //    ServiceResult<Boolean> deleteSellerApply(Integer id, Integer memberId);

    //    /**
    //     * 根据条件分页查询房源信息，PagerInfo传null取全部数据
    //     * @param queryMap
    //     * @param pager
    //     * @return
    //     */
    //    ServiceResult<List<HousingResources>> getHousingResourcesList(Map<String, String> queryMap,
    //                                                                  PagerInfo pager);
    /**
     * 根据条件分页查询租房记录，PagerInfo传null取全部数据
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<HousingLeaseVO>> getHousingLeaseList(Map<String, String> queryMap,
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