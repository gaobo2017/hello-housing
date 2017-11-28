package com.ejavashop.service.pcseller;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.pcseller.PcSellerRecommend;

public interface IPcSellerRecommendService {

    /**
     * 根据id取得PC端商家推荐类型
     * @param  pcSellerRecommendId
     * @return
     */
    ServiceResult<PcSellerRecommend> getPcSellerRecommendById(Integer pcSellerRecommendId);

    /**
     * 保存PC端商家推荐类型
     * @param  pcSellerRecommend
     * @return
     */
    ServiceResult<Boolean> savePcSellerRecommend(PcSellerRecommend pcSellerRecommend);

    /**
     * 更新PC端商家推荐类型
     * @param pcSellerRecommend
     * @return
     */
    ServiceResult<Boolean> updatePcSellerRecommend(PcSellerRecommend pcSellerRecommend);

    /**
     * 删除PC端商家推荐类型
     * @param  pcSellerRecommend
     * @return
     */
    ServiceResult<Boolean> deletePcSellerRecommend(Integer pcSellerRecommendId);

    /**
     * 根据条件取得PC端商家推荐类型
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PcSellerRecommend>> getPcSellerRecommends(Map<String, String> queryMap,
                                                                 PagerInfo pager);

    /**
     * 取得PC端商家推荐类型<br>
     * <li>如果isPreview=true取使用和预使用状态的推荐类型
     * <li>如果isPreview=false取使用状态的推荐类型
     * 
     * @param sellerId
     * @param isPreview
     * @return
     */
    ServiceResult<List<PcSellerRecommend>> getPcSellerRecommendForView(Integer sellerId,
                                                                       Boolean isPreview);

}