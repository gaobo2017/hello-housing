package com.ejavashop.model.pcindex;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shopm.read.pcindex.PcIndexFloorPatchReadDao;
import com.ejavashop.dao.shopm.read.pcindex.PcIndexFloorReadDao;
import com.ejavashop.dao.shopm.write.pcindex.PcIndexFloorPatchWriteDao;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloor;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorPatch;

@Component(value = "pcIndexFloorPatchModel")
public class PcIndexFloorPatchModel {

    @Resource
    private PcIndexFloorPatchReadDao  pcIndexFloorPatchReadDao;
    @Resource
    private PcIndexFloorPatchWriteDao pcIndexFloorPatchWriteDao;
    @Resource
    private PcIndexFloorReadDao       pcIndexFloorReadDao;

    /**
     * 根据id取得PC端首页楼层碎屑
     * @param  pcIndexFloorPatchId
     * @return
     */
    public PcIndexFloorPatch getPcIndexFloorPatchById(Integer pcIndexFloorPatchId) {
        return pcIndexFloorPatchReadDao.get(pcIndexFloorPatchId);
    }

    /**
     * 保存PC端首页楼层碎屑
     * @param  pcIndexFloorPatch
     * @return
     */
    public boolean savePcIndexFloorPatch(PcIndexFloorPatch pcIndexFloorPatch) {
        return pcIndexFloorPatchWriteDao.insert(pcIndexFloorPatch) > 0;
    }

    /**
     * 更新PC端首页楼层碎屑
     * @param pcIndexFloorPatch
     * @return
     */
    public boolean updatePcIndexFloorPatch(PcIndexFloorPatch pcIndexFloorPatch) {
        return pcIndexFloorPatchWriteDao.update(pcIndexFloorPatch) > 0;
    }

    /**
     * 删除PC端首页楼层碎屑
     * @param pcIndexFloorPatch
     * @return
     */
    public boolean deletePcIndexFloorPatch(Integer pcIndexFloorPatchId) {
        return pcIndexFloorPatchWriteDao.delete(pcIndexFloorPatchId) > 0;
    }

    public Integer getPcIndexFloorPatchsCount(Map<String, String> queryMap) {
        return pcIndexFloorPatchReadDao.getPcIndexFloorPatchsCount(queryMap);
    }

    public List<PcIndexFloorPatch> getPcIndexFloorPatchs(Map<String, String> queryMap,
                                                         Integer start, Integer size) {
        List<PcIndexFloorPatch> list = pcIndexFloorPatchReadDao.getPcIndexFloorPatchs(queryMap,
            start, size);
        if (list != null) {
            for (PcIndexFloorPatch floorClass : list) {
                PcIndexFloor pcIndexFloor = pcIndexFloorReadDao.get(floorClass.getFloorId());
                floorClass.setPcIndexFloor(pcIndexFloor);
            }
        }
        return list;
    }

    /**
     * 根据楼层ID取得PC端首页楼层碎屑<br>
     * <li>如果isPreview=true取使用和预使用状态的楼层碎屑
     * <li>如果isPreview=false取使用状态的楼层碎屑
     * 
     * @param isPreview
     * @return
     */
    public List<PcIndexFloorPatch> getPcIndexFloorPatchForView(Integer floorId, Boolean isPreview) {
        Integer isPreviewInt = 0;
        if (isPreview != null && isPreview) {
            isPreviewInt = 1;
        }
        return pcIndexFloorPatchReadDao.getPcIndexFloorPatchsForView(floorId, isPreviewInt);
    }
}