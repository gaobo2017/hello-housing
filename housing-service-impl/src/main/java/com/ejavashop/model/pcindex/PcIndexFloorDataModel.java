package com.ejavashop.model.pcindex;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shopm.read.pcindex.PcIndexFloorClassReadDao;
import com.ejavashop.dao.shopm.read.pcindex.PcIndexFloorDataReadDao;
import com.ejavashop.dao.shopm.write.pcindex.PcIndexFloorDataWriteDao;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorClass;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorData;

@Component(value = "pcIndexFloorDataModel")
public class PcIndexFloorDataModel {

    @Resource
    private PcIndexFloorDataReadDao  pcIndexFloorDataReadDao;
    @Resource
    private PcIndexFloorDataWriteDao pcIndexFloorDataWriteDao;
    @Resource
    private PcIndexFloorClassReadDao pcIndexFloorClassReadDao;
    @Resource
    private ProductReadDao           productReadDao;

    /**
     * 根据id取得PC端首页楼层分类数据
     * @param  pcIndexFloorDataId
     * @return
     */
    public PcIndexFloorData getPcIndexFloorDataById(Integer pcIndexFloorDataId) {
        PcIndexFloorData pcIndexFloorData = pcIndexFloorDataReadDao.get(pcIndexFloorDataId);
        if (pcIndexFloorData.getDataType().intValue() == PcIndexFloorData.DATA_TYPE_1) {
            Product product = productReadDao.get(pcIndexFloorData.getRefId());
            pcIndexFloorData.setProduct(product);
        }
        return pcIndexFloorData;
    }

    /**
     * 保存PC端首页楼层分类数据
     * @param  pcIndexFloorData
     * @return
     */
    public boolean savePcIndexFloorData(PcIndexFloorData pcIndexFloorData) {
        return pcIndexFloorDataWriteDao.insert(pcIndexFloorData) > 0;
    }

    /**
     * 更新PC端首页楼层分类数据
     * @param pcIndexFloorData
     * @return
     */
    public boolean updatePcIndexFloorData(PcIndexFloorData pcIndexFloorData) {
        return pcIndexFloorDataWriteDao.update(pcIndexFloorData) > 0;
    }

    /**
     * 删除PC端首页楼层分类数据
     * @param pcIndexFloorData
     * @return
     */
    public boolean deletePcIndexFloorData(Integer pcIndexFloorDataId) {
        return pcIndexFloorDataWriteDao.delete(pcIndexFloorDataId) > 0;
    }

    public Integer getPcIndexFloorDatasCount(Map<String, String> queryMap) {
        return pcIndexFloorDataReadDao.getPcIndexFloorDatasCount(queryMap);
    }

    public List<PcIndexFloorData> getPcIndexFloorDatas(Map<String, String> queryMap, Integer start,
                                                       Integer size) {
        List<PcIndexFloorData> list = pcIndexFloorDataReadDao.getPcIndexFloorDatas(queryMap, start,
            size);
        if (list != null) {
            for (PcIndexFloorData floorClass : list) {
                PcIndexFloorClass pcIndexFloorClass = pcIndexFloorClassReadDao
                    .get(floorClass.getFloorClassId());
                floorClass.setPcIndexFloorClass(pcIndexFloorClass);

                if (floorClass.getDataType().intValue() == PcIndexFloorData.DATA_TYPE_1) {
                    Product product = productReadDao.get(floorClass.getRefId());
                    floorClass.setProduct(product);
                }
            }
        }
        return list;
    }

    /**
     * 根据楼层分类ID取得PC端首页楼层分类数据<br>
     * 
     * @param floorClassId
     * @return
     */
    public List<PcIndexFloorData> getPcIndexFloorDataForView(Integer floorClassId) {
        List<PcIndexFloorData> list = pcIndexFloorDataReadDao
            .getPcIndexFloorDatasForView(floorClassId);

        if (list != null) {
            for (PcIndexFloorData floorClass : list) {
                if (floorClass.getDataType().intValue() == PcIndexFloorData.DATA_TYPE_1) {
                    Product product = productReadDao.get(floorClass.getRefId());
                    floorClass.setProduct(product);
                }
            }
        }

        return list;
    }
}