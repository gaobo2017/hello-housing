package com.ejavashop.service.order;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.seller.SellerComplaint;
import com.ejavashop.vo.seller.SellerComplaintVO;

public interface IAdminComplaintService {

    /**
     * 根据id取得商家投诉管理
     * @param  sellerComplaintId
     * @return
     */
    ServiceResult<SellerComplaint> getSellerComplaintById(Integer sellerComplaintId);

    /**
     * 保存商家投诉管理
     * @param  sellerComplaint
     * @return
     */
    ServiceResult<Integer> saveSellerComplaint(SellerComplaint sellerComplaint, Member member);

    /**
    * 更新商家投诉管理
    * @param  sellerComplaint
    * @return
    */
    ServiceResult<Integer> updateSellerComplaint(SellerComplaint sellerComplaint);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SellerComplaintVO>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    ServiceResult<SellerComplaintVO> getById(Integer id);
}