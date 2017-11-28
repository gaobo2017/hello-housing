package com.ejavashop.model.pcseller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shopm.read.pcseller.PcSellerIndexReadDao;
import com.ejavashop.dao.shopm.write.pcseller.PcSellerIndexWriteDao;
import com.ejavashop.entity.shopm.pcseller.PcSellerIndex;

@Component(value = "pcSellerIndexModel")
public class PcSellerIndexModel {

    @Resource
    private PcSellerIndexReadDao  pcSellerIndexReadDao;
    @Resource
    private PcSellerIndexWriteDao pcSellerIndexWriteDao;

    /**
     * 根据id取得PC端商家首页信息
     * @param  pcSellerIndexId
     * @return
     */
    public PcSellerIndex getPcSellerIndexById(Integer pcSellerIndexId) {
        return pcSellerIndexReadDao.get(pcSellerIndexId);
    }

    /**
     * 根据商家id取得PC端商家首页信息
     * @param  sellerId
     * @return
     */
    public PcSellerIndex getPcSellerIndexBySellerId(Integer sellerId) {
        return pcSellerIndexReadDao.getBySellerId(sellerId);
    }

    /**
     * 保存PC端商家首页信息
     * @param  pcSellerIndex
     * @return
     */
    public boolean savePcSellerIndex(PcSellerIndex pcSellerIndex) {
        return pcSellerIndexWriteDao.insert(pcSellerIndex) > 0;
    }

    /**
     * 更新PC端商家首页信息
     * @param pcSellerIndex
     * @return
     */
    public boolean updatePcSellerIndex(PcSellerIndex pcSellerIndex) {
        return pcSellerIndexWriteDao.update(pcSellerIndex) > 0;
    }

    /**
     * 删除PC端商家首页信息
     * @param pcSellerIndex
     * @return
     */
    public boolean deletePcSellerIndex(Integer pcSellerIndexId) {
        return pcSellerIndexWriteDao.delete(pcSellerIndexId) > 0;
    }

    public Integer getPcSellerIndexsCount(Map<String, String> queryMap) {
        return pcSellerIndexReadDao.getPcSellerIndexsCount(queryMap);
    }

    public List<PcSellerIndex> getPcSellerIndexs(Map<String, String> queryMap, Integer start,
                                                 Integer size) {
        return pcSellerIndexReadDao.getPcSellerIndexs(queryMap, start, size);
    }

}