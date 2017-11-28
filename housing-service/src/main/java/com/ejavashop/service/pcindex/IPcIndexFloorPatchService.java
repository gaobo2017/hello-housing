package com.ejavashop.service.pcindex;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorPatch;

public interface IPcIndexFloorPatchService {

    /**
     * 根据id取得PC端首页楼层碎屑
     * @param  pcIndexFloorPatchId
     * @return
     */
    ServiceResult<PcIndexFloorPatch> getPcIndexFloorPatchById(Integer pcIndexFloorPatchId);

    /**
     * 保存PC端首页楼层碎屑
     * @param  pcIndexFloorPatch
     * @return
     */
    ServiceResult<Boolean> savePcIndexFloorPatch(PcIndexFloorPatch pcIndexFloorPatch);

    /**
     * 更新PC端首页楼层碎屑
     * @param pcIndexFloorPatch
     * @return
     */
    ServiceResult<Boolean> updatePcIndexFloorPatch(PcIndexFloorPatch pcIndexFloorPatch);

    /**
     * 删除PC端首页楼层碎屑
     * @param  pcIndexFloorPatch
     * @return
     */
    ServiceResult<Boolean> deletePcIndexFloorPatch(Integer pcIndexFloorPatchId);

    /**
     * 根据条件取得PC端首页楼层碎屑
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PcIndexFloorPatch>> getPcIndexFloorPatchs(Map<String, String> queryMap,
                                                                 PagerInfo pager);

    /**
     * 根据楼层ID取得PC端首页楼层碎屑<br>
     * <li>如果isPreview=true取使用和预使用状态的楼层碎屑
     * <li>如果isPreview=false取使用状态的楼层碎屑
     * 
     * @param floorId
     * @param isPreview
     * @return
     */
    ServiceResult<List<PcIndexFloorPatch>> getPcIndexFloorPatchForView(Integer floorId,
                                                                       Boolean isPreview);

}