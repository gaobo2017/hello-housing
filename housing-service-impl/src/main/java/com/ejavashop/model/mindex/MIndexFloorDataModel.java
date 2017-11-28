package com.ejavashop.model.mindex;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shopm.read.mindex.MIndexFloorDataReadDao;
import com.ejavashop.dao.shopm.read.mindex.MIndexFloorReadDao;
import com.ejavashop.dao.shopm.write.mindex.MIndexFloorDataWriteDao;
import com.ejavashop.entity.shopm.MIndexFloorData;

@Component(value = "mIndexFloorDataModel")
public class MIndexFloorDataModel {

    @Resource
    private MIndexFloorDataReadDao  mIndexFloorDataReadDao;
    @Resource
    private MIndexFloorDataWriteDao mIndexFloorDataWriteDao;
    @Resource
    private ProductReadDao          productReadDao;
    @Resource
    private MIndexFloorReadDao      mIndexFloorReadDao;

    /**
     * 根据id取得首页楼层数据表
     * @param  mIndexFloorDataId
     * @return
     */
    public MIndexFloorData getMIndexFloorDataById(Integer mIndexFloorDataId) {
        return mIndexFloorDataReadDao.get(mIndexFloorDataId);
    }

    /**
     * 保存首页楼层数据表
     * @param  mIndexFloorData
     * @return
     */
    public boolean saveMIndexFloorData(MIndexFloorData mIndexFloorData) {
        return mIndexFloorDataWriteDao.insert(mIndexFloorData) > 0;
    }

    /**
    * 更新首页楼层数据表
    * @param  mIndexFloorData
    * @return
    */
    public boolean updateMIndexFloorData(MIndexFloorData mIndexFloorData) {
        return mIndexFloorDataWriteDao.update(mIndexFloorData) > 0;
    }

    /**
     * 删除首页楼层数据表
     * @param mIndexFloor
     * @return
     */
    public boolean deleteMIndexFloorData(Integer mIndexFloorDataId) {
        return mIndexFloorDataWriteDao.delete(mIndexFloorDataId) > 0;
    }

    public Integer getMIndexFloorDatasCount(Map<String, String> queryMap) {
        return mIndexFloorDataReadDao.getMIndexFloorDatasCount(queryMap);
    }

    public List<MIndexFloorData> getMIndexFloorDatas(Map<String, String> queryMap, Integer start,
                                                     Integer size) {
        List<MIndexFloorData> floorDatas = mIndexFloorDataReadDao.getMIndexFloorDatas(queryMap,
            start, size);
        if (floorDatas != null && floorDatas.size() > 0) {
            for (MIndexFloorData data : floorDatas) {
                data.setmIndexFloor(mIndexFloorReadDao.get(data.getIndexFloorId()));
                if (data.getDataType() == MIndexFloorData.DATA_TYPE_1) {
                    data.setProduct(productReadDao.get(data.getRefId()));
                }
            }
        }
        return floorDatas;
    }

    /**
     * 根据楼层ID取得首页楼层数据表
     * @param mIndexFloorId
     * @return
     */
    public List<MIndexFloorData> getMIndexFloorDatasByFloorId(Integer mIndexFloorId) {
        List<MIndexFloorData> dataList = mIndexFloorDataReadDao
            .getMIndexFloorDatasByFloorId(mIndexFloorId);
        if (dataList != null && dataList.size() > 0) {
            for (MIndexFloorData data : dataList) {
                if (data.getDataType() != null
                    && data.getDataType().equals(MIndexFloorData.DATA_TYPE_1)) {
                    data.setProduct(productReadDao.get(data.getRefId()));
                }
            }
        }

        return dataList;
    }
}