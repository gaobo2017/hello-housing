package com.ejavashop.service.pcindex;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloor;

public interface IPcIndexFloorService {

    /**
     * 根据id取得PC端首页楼层
     * @param  pcIndexFloorId
     * @return
     */
    ServiceResult<PcIndexFloor> getPcIndexFloorById(Integer pcIndexFloorId);

    /**
     * 保存PC端首页楼层
     * @param  pcIndexFloor
     * @return
     */
    ServiceResult<Boolean> savePcIndexFloor(PcIndexFloor pcIndexFloor);

    /**
     * 更新PC端首页楼层
     * @param pcIndexFloor
     * @return
     */
    ServiceResult<Boolean> updatePcIndexFloor(PcIndexFloor pcIndexFloor);

    /**
     * 删除PC端首页楼层
     * @param  pcIndexFloor
     * @return
     */
    ServiceResult<Boolean> deletePcIndexFloor(Integer pcIndexFloorId);

    /**
     * 根据条件取得PC端首页楼层
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PcIndexFloor>> getPcIndexFloors(Map<String, String> queryMap,
                                                       PagerInfo pager);

    /**
     * 取得PC端首页楼层<br>
     * <li>如果isPreview=true取使用和预使用状态的楼层
     * <li>如果isPreview=false取使用状态的楼层
     * 
     * @param isPreview
     * @return
     */
    ServiceResult<List<PcIndexFloor>> getPcIndexFloorForView(Boolean isPreview);

}