package com.ejavashop.service.pcseller;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.pcseller.PcSellerRecommendData;

public interface IPcSellerRecommendDataService {

    /**
     * 根据id取得PC端商家推荐数据
     * @param  pcSellerRecommendDataId
     * @return
     */
    ServiceResult<PcSellerRecommendData> getPcSellerRecommendDataById(Integer pcSellerRecommendDataId);

    /**
     * 保存PC端商家推荐数据
     * @param  pcSellerRecommendData
     * @return
     */
    ServiceResult<Boolean> savePcSellerRecommendData(PcSellerRecommendData pcSellerRecommendData);

    /**
     * 更新PC端商家推荐数据
     * @param pcSellerRecommendData
     * @return
     */
    ServiceResult<Boolean> updatePcSellerRecommendData(PcSellerRecommendData pcSellerRecommendData);

    /**
     * 删除PC端商家推荐数据
     * @param  pcSellerRecommendData
     * @return
     */
    ServiceResult<Boolean> deletePcSellerRecommendData(Integer pcSellerRecommendDataId);

    /**
     * 根据条件取得PC端商家推荐数据
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PcSellerRecommendData>> getPcSellerRecommendDatas(Map<String, String> queryMap,
                                                                         PagerInfo pager);

    /**
     * 根据楼层分类ID取得PC端商家推荐数据<br>
     * 
     * @param recommendId
     * @return
     */
    ServiceResult<List<PcSellerRecommendData>> getPcSellerRecommendDataForView(Integer recommendId);

}