package com.ejavashop.service.mseller;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.MSellerIndexBanner;
import com.ejavashop.entity.shopm.MSellerIndexFloor;
import com.ejavashop.entity.shopm.MSellerIndexFloorData;

/**
 * 商家首页service
 *                       
 * @Filename: IMSellerIndexService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IMSellerIndexService {

    /**
     * 根据id取得移动端首页轮播图
     * @param  mSellerIndexBannerId
     * @return
     */
    ServiceResult<MSellerIndexBanner> getMSellerIndexBannerById(Integer mSellerIndexBannerId);

    /**
     * 保存移动端首页轮播图
     * @param  mSellerIndexBanner
     * @return
     */
    ServiceResult<Boolean> saveMSellerIndexBanner(MSellerIndexBanner mSellerIndexBanner);

    /**
     * 更新移动端首页轮播图
     * @param mSellerIndexBanner
     * @return
     */
    ServiceResult<Boolean> updateMSellerIndexBanner(MSellerIndexBanner mSellerIndexBanner);

    /**
     * 删除移动端首页轮播图
     * @param  mSellerIndexBanner
     * @return
     */
    ServiceResult<Boolean> deleteMSellerIndexBanner(Integer mSellerIndexBannerId);

    /**
     * 根据条件取得移动端首页轮播图
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<MSellerIndexBanner>> getMSellerIndexBanners(Map<String, String> queryMap,
                                                                   PagerInfo pager);

    /**
     * 取得移动端首页轮播图<br>
     * <li>如果isPreview=true取所有轮播图
     * <li>如果isPreview=false取使用状态且当前时间在展示时间范围内的轮播图
     * 
     * @param sellerId
     * @param isPreview
     * @return
     */
    ServiceResult<List<MSellerIndexBanner>> getMSellerIndexBannerForView(Integer sellerId,
                                                                         Boolean isPreview);

    /**
     * 根据id取得移动端首页楼层表
     * @param  mSellerIndexFloorId
     * @return
     */
    ServiceResult<MSellerIndexFloor> getMSellerIndexFloorById(Integer mSellerIndexFloorId);

    /**
     * 保存移动端首页楼层表
     * @param  mSellerIndexFloor
     * @return
     */
    ServiceResult<Boolean> saveMSellerIndexFloor(MSellerIndexFloor mSellerIndexFloor);

    /**
     * 更新移动端首页楼层表
     * @param mSellerIndexFloor
     * @return
     */
    ServiceResult<Boolean> updateMSellerIndexFloor(MSellerIndexFloor mSellerIndexFloor);

    /**
     * 删除移动端首页楼层表
     * @param mSellerIndexFloor
     * @return
     */
    ServiceResult<Boolean> deleteMSellerIndexFloor(Integer mSellerIndexFloorId);

    /**
     * 根据条件取得移动端首页楼层表
     * @param  mSellerIndexFloorId
     * @return
     */
    ServiceResult<List<MSellerIndexFloor>> getMSellerIndexFloors(Map<String, String> queryMap,
                                                                 PagerInfo pager);

    /**
     * 取得移动端首页楼层表<br>
     * <li>如果isPreview=true取所有楼层
     * <li>如果isPreview=false只取显示状态的楼层
     * <li>封装楼层对应的数据（MSellerIndexFloorData）
     * 
     * @param sellerId
     * @param isPreview
     * @return
     */
    ServiceResult<List<MSellerIndexFloor>> getMSellerIndexFloorsWithData(Integer sellerId,
                                                                         Boolean isPreview);

    /**
     * 根据id取得首页楼层数据表
     * @param  mSellerIndexFloorDataId
     * @return
     */
    ServiceResult<MSellerIndexFloorData> getMSellerIndexFloorDataById(Integer mSellerIndexFloorDataId);

    /**
     * 保存首页楼层数据表
     * @param  mSellerIndexFloorData
     * @return
     */
    ServiceResult<Boolean> saveMSellerIndexFloorData(MSellerIndexFloorData mSellerIndexFloorData);

    /**
     * 更新首页楼层数据表
     * @param mSellerIndexFloorData
     * @return
     */
    ServiceResult<Boolean> updateMSellerIndexFloorData(MSellerIndexFloorData mSellerIndexFloorData);

    /**
     * 删除首页楼层数据表
     * @param  mSellerIndexFloorData
     * @return
     */
    ServiceResult<Boolean> deleteMSellerIndexFloorData(Integer mSellerIndexFloorDataId);

    /**
     * 根据楼层ID取得首页楼层数据表
     * @param mSellerIndexFloorId
     * @return
     */
    ServiceResult<List<MSellerIndexFloorData>> getMSellerIndexFloorDatasByFloorId(Integer mSellerIndexFloorId);

    /**
     * 根据条件取得首页楼层数据表
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<MSellerIndexFloorData>> getMSellerIndexFloorDatas(Map<String, String> queryMap,
                                                                         PagerInfo pager);

}