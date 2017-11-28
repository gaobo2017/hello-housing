package com.ejavashop.model.pcseller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shopm.read.pcseller.PcSellerRecommendDataReadDao;
import com.ejavashop.dao.shopm.read.pcseller.PcSellerRecommendReadDao;
import com.ejavashop.dao.shopm.write.pcseller.PcSellerRecommendDataWriteDao;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.shopm.pcseller.PcSellerRecommend;
import com.ejavashop.entity.shopm.pcseller.PcSellerRecommendData;

@Component(value = "pcSellerRecommendDataModel")
public class PcSellerRecommendDataModel {

    @Resource
    private PcSellerRecommendDataReadDao  pcSellerRecommendDataReadDao;
    @Resource
    private PcSellerRecommendDataWriteDao pcSellerRecommendDataWriteDao;
    @Resource
    private PcSellerRecommendReadDao      pcSellerRecommendReadDao;
    @Resource
    private ProductReadDao                productReadDao;

    /**
     * 根据id取得PC端商家推荐数据
     * @param  pcSellerRecommendDataId
     * @return
     */
    public PcSellerRecommendData getPcSellerRecommendDataById(Integer pcSellerRecommendDataId) {
        PcSellerRecommendData pcSellerRecommendData = pcSellerRecommendDataReadDao
            .get(pcSellerRecommendDataId);
        if (pcSellerRecommendData.getDataType().intValue() == PcSellerRecommendData.DATA_TYPE_1) {
            Product product = productReadDao.get(pcSellerRecommendData.getRefId());
            pcSellerRecommendData.setProduct(product);
        }
        return pcSellerRecommendData;
    }

    /**
     * 保存PC端商家推荐数据
     * @param  pcSellerRecommendData
     * @return
     */
    public boolean savePcSellerRecommendData(PcSellerRecommendData pcSellerRecommendData) {
        return pcSellerRecommendDataWriteDao.insert(pcSellerRecommendData) > 0;
    }

    /**
     * 更新PC端商家推荐数据
     * @param pcSellerRecommendData
     * @return
     */
    public boolean updatePcSellerRecommendData(PcSellerRecommendData pcSellerRecommendData) {
        return pcSellerRecommendDataWriteDao.update(pcSellerRecommendData) > 0;
    }

    /**
     * 删除PC端商家推荐数据
     * @param pcSellerRecommendData
     * @return
     */
    public boolean deletePcSellerRecommendData(Integer pcSellerRecommendDataId) {
        return pcSellerRecommendDataWriteDao.delete(pcSellerRecommendDataId) > 0;
    }

    public Integer getPcSellerRecommendDatasCount(Map<String, String> queryMap) {
        return pcSellerRecommendDataReadDao.getPcSellerRecommendDatasCount(queryMap);
    }

    public List<PcSellerRecommendData> getPcSellerRecommendDatas(Map<String, String> queryMap,
                                                                 Integer start, Integer size) {
        List<PcSellerRecommendData> list = pcSellerRecommendDataReadDao
            .getPcSellerRecommendDatas(queryMap, start, size);
        if (list != null) {
            for (PcSellerRecommendData recommendData : list) {
                PcSellerRecommend recommend = pcSellerRecommendReadDao
                    .get(recommendData.getRecommendId());
                recommendData.setPcSellerRecommend(recommend);

                if (recommendData.getDataType().intValue() == PcSellerRecommendData.DATA_TYPE_1) {
                    Product product = productReadDao.get(recommendData.getRefId());
                    recommendData.setProduct(product);
                }
            }
        }
        return list;
    }

    /**
     * 根据楼层分类ID取得PC端商家推荐数据<br>
     * 
     * @param floorClassId
     * @return
     */
    public List<PcSellerRecommendData> getPcSellerRecommendDataForView(Integer recommendId) {
        List<PcSellerRecommendData> list = pcSellerRecommendDataReadDao
            .getPcSellerRecommendDatasForView(recommendId);

        if (list != null) {
            for (PcSellerRecommendData floorClass : list) {
                if (floorClass.getDataType().intValue() == PcSellerRecommendData.DATA_TYPE_1) {
                    Product product = productReadDao.get(floorClass.getRefId());
                    floorClass.setProduct(product);
                }
            }
        }

        return list;
    }
}