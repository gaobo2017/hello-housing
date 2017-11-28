package com.ejavashop.model.mseller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shopm.read.mseller.MSellerIndexFloorDataReadDao;
import com.ejavashop.dao.shopm.read.mseller.MSellerIndexFloorReadDao;
import com.ejavashop.dao.shopm.write.mseller.MSellerIndexFloorDataWriteDao;
import com.ejavashop.dao.shopm.write.mseller.MSellerIndexFloorWriteDao;
import com.ejavashop.entity.shopm.MSellerIndexFloor;
import com.ejavashop.entity.shopm.MSellerIndexFloorData;

@Component(value = "mSellerIndexFloorModel")
public class MSellerIndexFloorModel {

    @Resource
    private MSellerIndexFloorReadDao      mSellerIndexFloorReadDao;
    @Resource
    private MSellerIndexFloorWriteDao     mSellerIndexFloorWriteDao;
    @Resource
    private MSellerIndexFloorDataReadDao  mSellerIndexFloorDataReadDao;
    @Resource
    private MSellerIndexFloorDataWriteDao mSellerIndexFloorDataWriteDao;
    @Resource
    private ProductReadDao                productReadDao;
    @Resource
    private DataSourceTransactionManager  mobileTransactionManager;

    /**
     * 根据id取得移动端首页楼层表
     * @param  mSellerIndexFloorId
     * @return
     */
    public MSellerIndexFloor getMSellerIndexFloorById(Integer mSellerIndexFloorId) {
        return mSellerIndexFloorReadDao.get(mSellerIndexFloorId);
    }

    /**
     * 保存移动端首页楼层表
     * @param  mSellerIndexFloor
     * @return
     */
    public boolean saveMSellerIndexFloor(MSellerIndexFloor mSellerIndexFloor) {
        return mSellerIndexFloorWriteDao.insert(mSellerIndexFloor) > 0;
    }

    /**
     * 更新移动端首页楼层表
     * @param mSellerIndexFloor
     * @return
     */
    public boolean updateMSellerIndexFloor(MSellerIndexFloor mSellerIndexFloor) {
        return mSellerIndexFloorWriteDao.update(mSellerIndexFloor) > 0;
    }

    /**
     * 删除移动端首页楼层表
     * @param mSellerIndexFloor
     * @return
     */
    public boolean deleteMSellerIndexFloor(Integer mSellerIndexFloorId) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = mobileTransactionManager.getTransaction(def);
        try {
            // 删除楼层
            Integer delete = mSellerIndexFloorWriteDao.delete(mSellerIndexFloorId);
            // 删除楼层数据
            mSellerIndexFloorDataWriteDao.deleteByFloorId(mSellerIndexFloorId);
            mobileTransactionManager.commit(status);
            return delete > 0;
        } catch (Exception e) {
            mobileTransactionManager.rollback(status);
            throw e;
        }
    }

    public Integer getMSellerIndexFloorsCount(Map<String, String> queryMap) {
        return mSellerIndexFloorReadDao.getMSellerIndexFloorsCount(queryMap);
    }

    public List<MSellerIndexFloor> getMSellerIndexFloors(Map<String, String> queryMap,
                                                         Integer start, Integer size) {
        return mSellerIndexFloorReadDao.getMSellerIndexFloors(queryMap, start, size);
    }

    /**
     * 取得移动端首页楼层表<br>
     * <li>如果isPreview=true取所有楼层
     * <li>如果isPreview=false只取显示状态的楼层
     * <li>封装楼层对应的数据（MSellerIndexFloorData）
     * 
     * @param sellerId
     * @param isPreview
     * @return
     */
    public List<MSellerIndexFloor> getMSellerIndexFloorsWithData(Integer sellerId,
                                                                 Boolean isPreview) {
        String status = "";
        if (!isPreview) {
            status = MSellerIndexFloor.STATUS_1 + "";
        }
        List<MSellerIndexFloor> list = mSellerIndexFloorReadDao
            .getMSellerIndexFloorsForView(sellerId, status);

        for (MSellerIndexFloor floor : list) {
            List<MSellerIndexFloorData> dataList = mSellerIndexFloorDataReadDao
                .getMSellerIndexFloorDatasByFloorId(floor.getId());
            if (dataList != null && dataList.size() > 0) {
                for (MSellerIndexFloorData data : dataList) {
                    if (data.getDataType() != null
                        && data.getDataType().equals(MSellerIndexFloorData.DATA_TYPE_1)) {
                        data.setProduct(productReadDao.get(data.getRefId()));
                    }
                }
            }
            floor.setDatas(dataList);
        }

        return list;
    }
}