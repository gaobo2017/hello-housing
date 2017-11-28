package com.ejavashop.model.pcseller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shopm.read.pcseller.PcSellerRecommendDataReadDao;
import com.ejavashop.dao.shopm.read.pcseller.PcSellerRecommendReadDao;
import com.ejavashop.dao.shopm.write.pcseller.PcSellerRecommendDataWriteDao;
import com.ejavashop.dao.shopm.write.pcseller.PcSellerRecommendWriteDao;
import com.ejavashop.entity.shopm.pcseller.PcSellerRecommend;
import com.ejavashop.entity.shopm.pcseller.PcSellerRecommendData;

@Component(value = "pcSellerRecommendModel")
public class PcSellerRecommendModel {

    @Resource
    private PcSellerRecommendReadDao      pcSellerRecommendReadDao;
    @Resource
    private PcSellerRecommendWriteDao     pcSellerRecommendWriteDao;
    @Resource
    private PcSellerRecommendDataReadDao  pcSellerRecommendDataReadDao;
    @Resource
    private PcSellerRecommendDataWriteDao pcSellerRecommendDataWriteDao;
    @Resource
    private ProductReadDao                productReadDao;

    /**
     * 根据id取得PC端商家推荐类型
     * @param  pcSellerRecommendId
     * @return
     */
    public PcSellerRecommend getPcSellerRecommendById(Integer pcSellerRecommendId) {
        return pcSellerRecommendReadDao.get(pcSellerRecommendId);
    }

    /**
     * 保存PC端商家推荐类型
     * @param  pcSellerRecommend
     * @return
     */
    public boolean savePcSellerRecommend(PcSellerRecommend pcSellerRecommend) {
        return pcSellerRecommendWriteDao.insert(pcSellerRecommend) > 0;
    }

    /**
     * 更新PC端商家推荐类型
     * @param pcSellerRecommend
     * @return
     */
    public boolean updatePcSellerRecommend(PcSellerRecommend pcSellerRecommend) {
        return pcSellerRecommendWriteDao.update(pcSellerRecommend) > 0;
    }

    /**
     * 删除PC端商家推荐类型
     * @param pcSellerRecommend
     * @return
     */
    public boolean deletePcSellerRecommend(Integer pcSellerRecommendId) {
        // 删除数据
        boolean resultData = pcSellerRecommendDataWriteDao
            .deleteByRecommendId(pcSellerRecommendId) > 0;

        // 删除推荐类型
        boolean result = pcSellerRecommendWriteDao.delete(pcSellerRecommendId) > 0;

        return resultData && result;
    }

    public Integer getPcSellerRecommendsCount(Map<String, String> queryMap) {
        return pcSellerRecommendReadDao.getPcSellerRecommendsCount(queryMap);
    }

    public List<PcSellerRecommend> getPcSellerRecommends(Map<String, String> queryMap,
                                                         Integer start, Integer size) {
        return pcSellerRecommendReadDao.getPcSellerRecommends(queryMap, start, size);
    }

    /**
     * 取得PC端商家推荐类型<br>
     * <li>如果isPreview=true取使用和预使用状态的推荐类型
     * <li>如果isPreview=false取使用状态的推荐类型
     * 
     * @param sellerId
     * @param isPreview
     * @return
     */
    public List<PcSellerRecommend> getPcSellerRecommendForView(Integer sellerId,
                                                               Boolean isPreview) {
        Integer isPreviewInt = 0;
        if (isPreview != null && isPreview) {
            isPreviewInt = 1;
        }

        List<PcSellerRecommend> list = pcSellerRecommendReadDao
            .getPcSellerRecommendsForView(sellerId, isPreviewInt);
        if (list != null && list.size() > 0) {
            for (PcSellerRecommend recommend : list) {
                List<PcSellerRecommendData> dataList = pcSellerRecommendDataReadDao
                    .getPcSellerRecommendDatasForView(recommend.getId());
                if (dataList != null && dataList.size() > 0) {
                    for (PcSellerRecommendData data : dataList) {
                        if (data.getDataType().intValue() == PcSellerRecommendData.DATA_TYPE_1) {
                            data.setProduct(productReadDao.get(data.getRefId()));
                        }
                    }
                }
                recommend.setDataList(dataList);
            }
        }

        return list;
    }
}