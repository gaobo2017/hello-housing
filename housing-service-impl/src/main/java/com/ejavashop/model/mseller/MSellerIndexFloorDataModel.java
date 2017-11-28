package com.ejavashop.model.mseller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shopm.read.mseller.MSellerIndexFloorDataReadDao;
import com.ejavashop.dao.shopm.read.mseller.MSellerIndexFloorReadDao;
import com.ejavashop.dao.shopm.write.mseller.MSellerIndexFloorDataWriteDao;
import com.ejavashop.entity.shopm.MSellerIndexFloorData;

@Component(value = "mSellerIndexFloorDataModel")
public class MSellerIndexFloorDataModel {

    @Resource
    private MSellerIndexFloorDataReadDao  mSellerIndexFloorDataReadDao;
    @Resource
    private MSellerIndexFloorDataWriteDao mSellerIndexFloorDataWriteDao;
    @Resource
    private ProductReadDao                productReadDao;
    @Resource
    private MSellerIndexFloorReadDao      mSellerIndexFloorReadDao;

    /**
     * 根据id取得首页楼层数据表
     * @param  mSellerIndexFloorDataId
     * @return
     */
    public MSellerIndexFloorData getMSellerIndexFloorDataById(Integer mSellerIndexFloorDataId) {
        return mSellerIndexFloorDataReadDao.get(mSellerIndexFloorDataId);
    }

    /**
     * 保存首页楼层数据表
     * @param  mSellerIndexFloorData
     * @return
     */
    public boolean saveMSellerIndexFloorData(MSellerIndexFloorData mSellerIndexFloorData) {
        return mSellerIndexFloorDataWriteDao.insert(mSellerIndexFloorData) > 0;
    }

    /**
    * 更新首页楼层数据表
    * @param  mSellerIndexFloorData
    * @return
    */
    public boolean updateMSellerIndexFloorData(MSellerIndexFloorData mSellerIndexFloorData) {
        return mSellerIndexFloorDataWriteDao.update(mSellerIndexFloorData) > 0;
    }

    /**
     * 删除首页楼层数据表
     * @param mSellerIndexFloor
     * @return
     */
    public boolean deleteMSellerIndexFloorData(Integer mSellerIndexFloorDataId) {
        return mSellerIndexFloorDataWriteDao.delete(mSellerIndexFloorDataId) > 0;
    }

    public Integer getMSellerIndexFloorDatasCount(Map<String, String> queryMap) {
        return mSellerIndexFloorDataReadDao.getMSellerIndexFloorDatasCount(queryMap);
    }

    public List<MSellerIndexFloorData> getMSellerIndexFloorDatas(Map<String, String> queryMap,
                                                                 Integer start, Integer size) {
        List<MSellerIndexFloorData> floorDatas = mSellerIndexFloorDataReadDao
            .getMSellerIndexFloorDatas(queryMap, start, size);
        if (floorDatas != null && floorDatas.size() > 0) {
            for (MSellerIndexFloorData data : floorDatas) {
                data.setmSellerIndexFloor(mSellerIndexFloorReadDao.get(data.getIndexFloorId()));
                if (data.getDataType() == MSellerIndexFloorData.DATA_TYPE_1) {
                    data.setProduct(productReadDao.get(data.getRefId()));
                }
            }
        }
        return floorDatas;
    }

    /**
     * 根据楼层ID取得首页楼层数据表
     * @param mSellerIndexFloorId
     * @return
     */
    public List<MSellerIndexFloorData> getMSellerIndexFloorDatasByFloorId(Integer mSellerIndexFloorId) {
        List<MSellerIndexFloorData> dataList = mSellerIndexFloorDataReadDao
            .getMSellerIndexFloorDatasByFloorId(mSellerIndexFloorId);
        if (dataList != null && dataList.size() > 0) {
            for (MSellerIndexFloorData data : dataList) {
                if (data.getDataType() != null
                    && data.getDataType().equals(MSellerIndexFloorData.DATA_TYPE_1)) {
                    data.setProduct(productReadDao.get(data.getRefId()));
                }
            }
        }

        return dataList;
    }
}