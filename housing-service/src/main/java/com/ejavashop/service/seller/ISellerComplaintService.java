package com.ejavashop.service.seller;

import java.util.List;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.seller.SellerComplaint;
import com.ejavashop.vo.seller.FrontSellerComplaintVO;

public interface ISellerComplaintService {

    /**
     * 根据id取得商家投诉管理
     * @param  sellerComplaintId
     * @return
     */
    ServiceResult<SellerComplaint> getSellerComplaintById(Integer sellerComplaintId);

    /**
     * 保存商家投诉管理
     * @param request
     * @param sellerComplaint
     * @return
     */

    ServiceResult<SellerComplaint> saveSellerComplaint(Member member,
                                                       SellerComplaint sellerComplaint);

    /**
     * 根据登录用户获得申诉列表
     * @param pager
     * @param memberId
     * @return
     */
    ServiceResult<List<FrontSellerComplaintVO>> getSellerComplaintList(PagerInfo pager,
                                                                       Integer memberId);

    /**
    * 更新商家投诉管理
    * @param  sellerComplaint
    * @return
    */
    ServiceResult<Integer> updateSellerComplaint(SellerComplaint sellerComplaint);

    /**
     * 根据登录用户获得申诉列表(封装商品对象和网单对象)
     * @param pager
     * @param memberId
     * @return
     */
    ServiceResult<List<FrontSellerComplaintVO>> getComplaintListWithPrdAndOp(PagerInfo pager,
                                                                             Integer memberId);
    //    /**
    //    * 分页查询
    //    * @param queryMap
    //    * @param pager
    //    * @return
    //    */
    //    ServiceResult<List<SellerComplaintVO>> page(Map<String, String> queryMap, PagerInfo pager);
    //
    //    /**
    //     * 删除
    //     * @param id
    //     * @return
    //     */
    //    ServiceResult<Boolean> del(Integer id);
    //
    //    ServiceResult<SellerComplaintVO> getById(Integer id);
}