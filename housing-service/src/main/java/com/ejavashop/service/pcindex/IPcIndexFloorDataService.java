package com.ejavashop.service.pcindex;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorData;

public interface IPcIndexFloorDataService {

    /**
     * 根据id取得PC端首页楼层分类数据
     * @param  pcIndexFloorDataId
     * @return
     */
    ServiceResult<PcIndexFloorData> getPcIndexFloorDataById(Integer pcIndexFloorDataId);

    /**
     * 保存PC端首页楼层分类数据
     * @param  pcIndexFloorData
     * @return
     */
    ServiceResult<Boolean> savePcIndexFloorData(PcIndexFloorData pcIndexFloorData);

    /**
     * 更新PC端首页楼层分类数据
     * @param pcIndexFloorData
     * @return
     */
    ServiceResult<Boolean> updatePcIndexFloorData(PcIndexFloorData pcIndexFloorData);

    /**
     * 删除PC端首页楼层分类数据
     * @param  pcIndexFloorData
     * @return
     */
    ServiceResult<Boolean> deletePcIndexFloorData(Integer pcIndexFloorDataId);

    /**
     * 根据条件取得PC端首页楼层分类数据
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PcIndexFloorData>> getPcIndexFloorDatas(Map<String, String> queryMap,
                                                               PagerInfo pager);

    /**
     * 根据楼层分类ID取得PC端首页楼层分类数据<br>
     * 
     * @param floorClassId
     * @return
     */
    ServiceResult<List<PcIndexFloorData>> getPcIndexFloorDataForView(Integer floorClassId);

}