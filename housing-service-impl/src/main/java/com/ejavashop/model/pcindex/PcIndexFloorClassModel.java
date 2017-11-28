package com.ejavashop.model.pcindex;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shopm.read.pcindex.PcIndexFloorClassReadDao;
import com.ejavashop.dao.shopm.read.pcindex.PcIndexFloorReadDao;
import com.ejavashop.dao.shopm.write.pcindex.PcIndexFloorClassWriteDao;
import com.ejavashop.dao.shopm.write.pcindex.PcIndexFloorDataWriteDao;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloor;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorClass;

@Component(value = "pcIndexFloorClassModel")
public class PcIndexFloorClassModel {

    @Resource
    private PcIndexFloorClassReadDao  pcIndexFloorClassReadDao;
    @Resource
    private PcIndexFloorClassWriteDao pcIndexFloorClassWriteDao;
    @Resource
    private PcIndexFloorReadDao       pcIndexFloorReadDao;
    @Resource
    private PcIndexFloorDataWriteDao  pcIndexFloorDataWriteDao;

    /**
     * 根据id取得PC端首页楼层分类
     * @param  pcIndexFloorClassId
     * @return
     */
    public PcIndexFloorClass getPcIndexFloorClassById(Integer pcIndexFloorClassId) {
        return pcIndexFloorClassReadDao.get(pcIndexFloorClassId);
    }

    /**
     * 保存PC端首页楼层分类
     * @param  pcIndexFloorClass
     * @return
     */
    public boolean savePcIndexFloorClass(PcIndexFloorClass pcIndexFloorClass) {
        return pcIndexFloorClassWriteDao.insert(pcIndexFloorClass) > 0;
    }

    /**
     * 更新PC端首页楼层分类
     * @param pcIndexFloorClass
     * @return
     */
    public boolean updatePcIndexFloorClass(PcIndexFloorClass pcIndexFloorClass) {
        return pcIndexFloorClassWriteDao.update(pcIndexFloorClass) > 0;
    }

    /**
     * 删除PC端首页楼层分类
     * @param pcIndexFloorClass
     * @return
     */
    public boolean deletePcIndexFloorClass(Integer pcIndexFloorClassId) {
        // 删除楼层分类数据
        boolean resultData = pcIndexFloorDataWriteDao.deleteByFloorClassId(pcIndexFloorClassId) > 0;
        // 删除楼层
        boolean result = pcIndexFloorClassWriteDao.delete(pcIndexFloorClassId) > 0;

        return resultData && result;
    }

    public Integer getPcIndexFloorClasssCount(Map<String, String> queryMap) {
        return pcIndexFloorClassReadDao.getPcIndexFloorClasssCount(queryMap);
    }

    public List<PcIndexFloorClass> getPcIndexFloorClasss(Map<String, String> queryMap,
                                                         Integer start, Integer size) {
        List<PcIndexFloorClass> list = pcIndexFloorClassReadDao.getPcIndexFloorClasss(queryMap,
            start, size);
        if (list != null) {
            for (PcIndexFloorClass floorClass : list) {
                PcIndexFloor pcIndexFloor = pcIndexFloorReadDao.get(floorClass.getFloorId());
                floorClass.setPcIndexFloor(pcIndexFloor);
            }
        }
        return list;
    }

    /**
     * 根据楼层ID取得PC端首页楼层分类<br>
     * <li>如果isPreview=true取使用和预使用状态的楼层分类
     * <li>如果isPreview=false取使用状态的楼层分类
     * 
     * @param isPreview
     * @return
     */
    public List<PcIndexFloorClass> getPcIndexFloorClassForView(Integer floorId, Boolean isPreview) {
        Integer isPreviewInt = 0;
        if (isPreview != null && isPreview) {
            isPreviewInt = 1;
        }
        return pcIndexFloorClassReadDao.getPcIndexFloorClasssForView(floorId, isPreviewInt);
    }
}