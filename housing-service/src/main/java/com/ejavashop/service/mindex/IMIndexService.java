package com.ejavashop.service.mindex;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.MIndexBanner;
import com.ejavashop.entity.shopm.MIndexFloor;
import com.ejavashop.entity.shopm.MIndexFloorData;

public interface IMIndexService {

    /**
     * 根据id取得移动端首页轮播图
     * @param  mIndexBannerId
     * @return
     */
    ServiceResult<MIndexBanner> getMIndexBannerById(Integer mIndexBannerId);

    /**
     * 保存移动端首页轮播图
     * @param  mIndexBanner
     * @return
     */
    ServiceResult<Boolean> saveMIndexBanner(MIndexBanner mIndexBanner);

    /**
     * 更新移动端首页轮播图
     * @param mIndexBanner
     * @return
     */
    ServiceResult<Boolean> updateMIndexBanner(MIndexBanner mIndexBanner);

    /**
     * 删除移动端首页轮播图
     * @param  mIndexBanner
     * @return
     */
    ServiceResult<Boolean> deleteMIndexBanner(Integer mIndexBannerId);

    /**
     * 根据条件取得移动端首页轮播图
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<MIndexBanner>> getMIndexBanners(Map<String, String> queryMap,
                                                       PagerInfo pager);

    /**
     * 取得移动端首页轮播图<br>
     * <li>如果isPreview=true取所有轮播图
     * <li>如果isPreview=false取使用状态且当前时间在展示时间范围内的轮播图
     * 
     * @param isPreview
     * @return
     */
    ServiceResult<List<MIndexBanner>> getMIndexBannerForView(Boolean isPreview);

    /**
     * 根据id取得移动端首页楼层表
     * @param  mIndexFloorId
     * @return
     */
    ServiceResult<MIndexFloor> getMIndexFloorById(Integer mIndexFloorId);

    /**
     * 保存移动端首页楼层表
     * @param  mIndexFloor
     * @return
     */
    ServiceResult<Boolean> saveMIndexFloor(MIndexFloor mIndexFloor);

    /**
     * 更新移动端首页楼层表
     * @param mIndexFloor
     * @return
     */
    ServiceResult<Boolean> updateMIndexFloor(MIndexFloor mIndexFloor);

    /**
     * 删除移动端首页楼层表
     * @param mIndexFloor
     * @return
     */
    ServiceResult<Boolean> deleteMIndexFloor(Integer mIndexFloorId);

    /**
     * 根据条件取得移动端首页楼层表
     * @param  mIndexFloorId
     * @return
     */
    ServiceResult<List<MIndexFloor>> getMIndexFloors(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 取得移动端首页楼层表<br>
     * <li>如果isPreview=true取所有楼层
     * <li>如果isPreview=false只取显示状态的楼层
     * <li>封装楼层对应的数据（MIndexFloorData）
     * @param isPreview
     * @return
     */
    ServiceResult<List<MIndexFloor>> getMIndexFloorsWithData(Boolean isPreview);

    /**
     * 根据id取得首页楼层数据表
     * @param  mIndexFloorDataId
     * @return
     */
    ServiceResult<MIndexFloorData> getMIndexFloorDataById(Integer mIndexFloorDataId);

    /**
     * 保存首页楼层数据表
     * @param  mIndexFloorData
     * @return
     */
    ServiceResult<Boolean> saveMIndexFloorData(MIndexFloorData mIndexFloorData);

    /**
     * 更新首页楼层数据表
     * @param mIndexFloorData
     * @return
     */
    ServiceResult<Boolean> updateMIndexFloorData(MIndexFloorData mIndexFloorData);

    /**
     * 删除首页楼层数据表
     * @param  mIndexFloorData
     * @return
     */
    ServiceResult<Boolean> deleteMIndexFloorData(Integer mIndexFloorDataId);

    /**
     * 根据楼层ID取得首页楼层数据表
     * @param mIndexFloorId
     * @return
     */
    ServiceResult<List<MIndexFloorData>> getMIndexFloorDatasByFloorId(Integer mIndexFloorId);

    /**
     * 根据条件取得首页楼层数据表
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<MIndexFloorData>> getMIndexFloorDatas(Map<String, String> queryMap,
                                                             PagerInfo pager);

}