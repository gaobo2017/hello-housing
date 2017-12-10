package com.ejavashop.service.house;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.house.HousingResources;
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

    //    /**
    //     * 根据id取得房源
    //     * @param  housingResourcesId
    //     * @return
    //     */
    //    ServiceResult<HousingResources> getHousingResourcesById(Integer housingResourcesId);

    //    /**
    //     * 保存商家申请表
    //     * @param  sellerApply
    //     * @return
    //     */
    //    ServiceResult<Integer> saveSellerApply(SellerApply sellerApply);
    //
    /**
     * 修改
     * @param HousingResources
     * @return
     */
    ServiceResult<Integer> updateHousingResources(HousingResources housingResources);

    /**
     * 新增
     * @param HousingResources
     * @return
     */
    ServiceResult<Integer> createHousingResources(HousingResources housingResources);

    /**
     * delete
     * @param housingResourcesId
     * @return
     */
    ServiceResult<Boolean> deleteHousingResources(Integer housingResourcesId);

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
     * 根据条件分页查询房源总成本信息，PagerInfo传null取全部数据
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<HousingCostVO>> getHousingCostList(Map<String, String> queryMap,
                                                          PagerInfo pager);

}