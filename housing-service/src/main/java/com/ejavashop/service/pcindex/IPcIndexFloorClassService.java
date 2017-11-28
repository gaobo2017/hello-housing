package com.ejavashop.service.pcindex;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorClass;

public interface IPcIndexFloorClassService {

    /**
     * 根据id取得PC端首页楼层分类
     * @param  pcIndexFloorClassId
     * @return
     */
    ServiceResult<PcIndexFloorClass> getPcIndexFloorClassById(Integer pcIndexFloorClassId);

    /**
     * 保存PC端首页楼层分类
     * @param  pcIndexFloorClass
     * @return
     */
    ServiceResult<Boolean> savePcIndexFloorClass(PcIndexFloorClass pcIndexFloorClass);

    /**
     * 更新PC端首页楼层分类
     * @param pcIndexFloorClass
     * @return
     */
    ServiceResult<Boolean> updatePcIndexFloorClass(PcIndexFloorClass pcIndexFloorClass);

    /**
     * 删除PC端首页楼层分类
     * @param  pcIndexFloorClass
     * @return
     */
    ServiceResult<Boolean> deletePcIndexFloorClass(Integer pcIndexFloorClassId);

    /**
     * 根据条件取得PC端首页楼层分类
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PcIndexFloorClass>> getPcIndexFloorClasss(Map<String, String> queryMap,
                                                                 PagerInfo pager);

    /**
     * 根据楼层ID取得PC端首页楼层分类<br>
     * <li>如果isPreview=true取使用和预使用状态的楼层分类
     * <li>如果isPreview=false取使用状态的楼层分类
     * 
     * @param floorId
     * @param isPreview
     * @return
     */
    ServiceResult<List<PcIndexFloorClass>> getPcIndexFloorClassForView(Integer floorId,
                                                                       Boolean isPreview);

}