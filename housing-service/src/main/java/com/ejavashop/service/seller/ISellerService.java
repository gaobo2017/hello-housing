package com.ejavashop.service.seller;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.seller.Seller;

/**
 * 商家基本信息service
 *                       
 * @Filename: ISellerService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISellerService {

    /**
     * 根据id取得商家表
     * @param  sellerId
     * @return
     */
    ServiceResult<Seller> getSellerById(Integer sellerId);

    /**
     * 根据用户id获取商家
     * @param userid
     * @return
     */
    ServiceResult<Seller> getSellerByMemberId(Integer memberId);

    /**
     * 保存商家表
     * @param  seller
     * @return
     */
    ServiceResult<Integer> saveSeller(Seller seller);

    /**
    * 更新商家表
    * @param  seller
    * @return
    */
    ServiceResult<Integer> updateSeller(Seller seller);

    /**
     * 根据条件分页查询商家，PagerInfo传null返回全部数据
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<Seller>> getSellers(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 冻结商家，修改商家状态，修改商家下所有商品状态
     * @param sellerId
     * @return
     */
    ServiceResult<Boolean> freezeSeller(Integer sellerId);

    /**
     * 解冻商家，修改商家状态，修改商家下所有商品状态（TODO 暂时修改为7下架）
     * @param sellerId
     * @return
     */
    ServiceResult<Boolean> unFreezeSeller(Integer sellerId);

    /**
     * 根据商家的用户ID，获取商家所在的地址（省市级）
     * @param memberId
     * @return
     */
    ServiceResult<String> getSellerLocationByMId(Integer memberId);

    /**
     * 定时任务设定商家的评分，用户评论各项求平均值设置为商家各项的综合评分
     * @return
     */
    ServiceResult<Boolean> jobSetSellerScore();

    /**
     * 定时任务设定商家各项统计数据
     * @return
     */
    ServiceResult<Boolean> jobSellerStatistics();

    /**
     * 根据名称获取商家
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<Seller>> getSellerByName(String name);

    /**
     * 根据店铺名称获取商家
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<Seller>> getSellerBySellerName(String sellerName);
}