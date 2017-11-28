package com.ejavashop.service.pcindex;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.pcindex.PcRecommend;

public interface IPcRecommendService {

    /**
     * 根据id取得PC端推荐商品
     * @param  pcRecommendId
     * @return
     */
    ServiceResult<PcRecommend> getPcRecommendById(Integer pcRecommendId);

    /**
     * 保存PC端推荐商品
     * @param  pcRecommend
     * @return
     */
    ServiceResult<Boolean> savePcRecommend(PcRecommend pcRecommend);

    /**
     * 更新PC端推荐商品
     * @param pcRecommend
     * @return
     */
    ServiceResult<Boolean> updatePcRecommend(PcRecommend pcRecommend);

    /**
     * 删除PC端推荐商品
     * @param  pcRecommend
     * @return
     */
    ServiceResult<Boolean> deletePcRecommend(Integer pcRecommendId);

    /**
     * 根据条件取得PC端推荐商品
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PcRecommend>> getPcRecommends(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 取得PC端推荐商品（当前时间在展示时间范围内的推荐商品）<br>
     * <li>如果isPreview=true取使用和预使用状态的推荐商品
     * <li>如果isPreview=false取使用状态的推荐商品
     * 
     * @param recommendType 推荐类型：1首页热销商品2首页今日推荐
     * @param isPreview
     * @return
     */
    ServiceResult<List<PcRecommend>> getPcRecommendForView(Integer recommendType,
                                                           Boolean isPreview);

}