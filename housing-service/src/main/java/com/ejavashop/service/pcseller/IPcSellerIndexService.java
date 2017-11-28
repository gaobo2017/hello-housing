package com.ejavashop.service.pcseller;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.pcseller.PcSellerIndex;

public interface IPcSellerIndexService {

    /**
     * 根据id取得PC端商家首页信息
     * @param  pcSellerIndexId
     * @return
     */
    ServiceResult<PcSellerIndex> getPcSellerIndexById(Integer pcSellerIndexId);

    /**
     * 根据商家id取得PC端商家首页信息
     * @param  sellerId
     * @return
     */
    ServiceResult<PcSellerIndex> getPcSellerIndexBySellerId(Integer sellerId);

    /**
     * 保存PC端商家首页信息
     * @param  pcSellerIndex
     * @return
     */
    ServiceResult<Boolean> savePcSellerIndex(PcSellerIndex pcSellerIndex);

    /**
     * 更新PC端商家首页信息
     * @param pcSellerIndex
     * @return
     */
    ServiceResult<Boolean> updatePcSellerIndex(PcSellerIndex pcSellerIndex);

    /**
     * 删除PC端商家首页信息
     * @param  pcSellerIndex
     * @return
     */
    ServiceResult<Boolean> deletePcSellerIndex(Integer pcSellerIndexId);

    /**
     * 根据条件取得PC端商家首页信息
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PcSellerIndex>> getPcSellerIndexs(Map<String, String> queryMap,
                                                         PagerInfo pager);

}