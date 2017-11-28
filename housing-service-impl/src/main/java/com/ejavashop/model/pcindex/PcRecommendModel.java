package com.ejavashop.model.pcindex;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shopm.read.pcindex.PcRecommendReadDao;
import com.ejavashop.dao.shopm.write.pcindex.PcRecommendWriteDao;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.shopm.pcindex.PcRecommend;

@Component(value = "pcRecommendModel")
public class PcRecommendModel {

    @Resource
    private PcRecommendReadDao  pcRecommendReadDao;
    @Resource
    private PcRecommendWriteDao pcRecommendWriteDao;

    @Resource
    private ProductReadDao      productReadDao;

    /**
     * 根据id取得PC端推荐商品
     * @param  pcRecommendId
     * @return
     */
    public PcRecommend getPcRecommendById(Integer pcRecommendId) {
        PcRecommend pcRecommend = pcRecommendReadDao.get(pcRecommendId);
        Product product = productReadDao.get(pcRecommend.getProductId());
        pcRecommend.setProduct(product);
        return pcRecommend;
    }

    /**
     * 保存PC端推荐商品
     * @param  pcRecommend
     * @return
     */
    public boolean savePcRecommend(PcRecommend pcRecommend) {
        return pcRecommendWriteDao.insert(pcRecommend) > 0;
    }

    /**
     * 更新PC端推荐商品
     * @param pcRecommend
     * @return
     */
    public boolean updatePcRecommend(PcRecommend pcRecommend) {
        return pcRecommendWriteDao.update(pcRecommend) > 0;
    }

    /**
     * 删除PC端推荐商品
     * @param pcRecommend
     * @return
     */
    public boolean deletePcRecommend(Integer pcRecommendId) {
        return pcRecommendWriteDao.delete(pcRecommendId) > 0;
    }

    public Integer getPcRecommendsCount(Map<String, String> queryMap) {
        return pcRecommendReadDao.getPcRecommendsCount(queryMap);
    }

    public List<PcRecommend> getPcRecommends(Map<String, String> queryMap, Integer start,
                                             Integer size) {
        List<PcRecommend> list = pcRecommendReadDao.getPcRecommends(queryMap, start, size);
        if (list != null && list.size() > 0) {
            for (PcRecommend recommend : list) {
                Product product = productReadDao.get(recommend.getProductId());
                recommend.setProduct(product);
            }
        }
        return list;
    }

    /**
     * 取得PC端推荐商品（当前时间在展示时间范围内的推荐商品）<br>
     * <li>如果isPreview=true取使用和预使用状态的推荐商品
     * <li>如果isPreview=false取使用状态的推荐商品
     * 
     * @param recommendType 推荐类型：1首页热销商品2首页今日推荐
     * @param isPreview
     * @return
     */
    public List<PcRecommend> getPcRecommendForView(Integer recommendType, Boolean isPreview) {
        Integer isPreviewInt = 0;
        if (isPreview != null && isPreview) {
            isPreviewInt = 1;
        }
        List<PcRecommend> list = pcRecommendReadDao.getPcRecommendsForView(recommendType,
            isPreviewInt);
        if (list != null && list.size() > 0) {
            for (PcRecommend recommend : list) {
                Product product = productReadDao.get(recommend.getProductId());
                recommend.setProduct(product);
            }
        }
        return list;
    }
}