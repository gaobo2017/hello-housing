package com.ejavashop.model.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import com.ejavashop.core.ComplaintSource;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.order.OrdersProductReadDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.dao.shop.write.member.MemberProductBackWriteDao;
import com.ejavashop.dao.shop.write.member.MemberProductExchangeWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerComplaintWriteDao;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberProductBack;
import com.ejavashop.entity.member.MemberProductExchange;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.seller.SellerComplaint;
import com.ejavashop.vo.seller.SellerComplaintVO;

@Component(value = "complaintModel")
public class ComplaintModel {

    @Resource
    private SellerComplaintWriteDao       sellerComplaintWriteDao;
    @Resource
    private OrdersWriteDao                ordersWriteDao;
    @Resource
    private OrdersProductReadDao          ordersProductReadDao;
    @Resource
    private MemberProductBackWriteDao     memberProductBackWriteDao;
    @Resource
    private MemberProductExchangeWriteDao memberProductExchangeWriteDao;
    @Resource
    private SellerReadDao                 sellerReadDao;

    /**
    * 根据id取得商家投诉管理
    * @param  sellerComplaintId
    * @return
    */
    public SellerComplaint getSellerComplaintById(Integer sellerComplaintId) {
        return sellerComplaintWriteDao.get(sellerComplaintId);
    }

    /**
     * 保存商家投诉管理
     * @param  sellerComplaint
     * @return
     */
    public Integer saveSellerComplaint(SellerComplaint sellerComplaint, Member member) {
        // 参数校验
        if (sellerComplaint == null) {
            throw new BusinessException("申诉申请不能为空，请重试！");
        } else if (sellerComplaint.getOrderProductId() == null
                   || sellerComplaint.getOrderProductId() == 0) {
            throw new BusinessException("申诉申请网单id不能为空，请重试！");
        } else if (sellerComplaint.getOrderId() == null || sellerComplaint.getOrderId() == 0) {
            throw new BusinessException("申诉申请订单id不能为空，请重试！");
        } else if ((sellerComplaint.getProductBackId() == null
                    || sellerComplaint.getProductBackId() == 0)
                   && (sellerComplaint.getProductExchangeId() == null
                       || sellerComplaint.getProductExchangeId() == 0)) {
            throw new BusinessException("申诉申请必须关联退换货申请中的一个，请重试！");
        } else if ((sellerComplaint.getProductBackId() != null
                    && sellerComplaint.getProductBackId() != 0)
                   && (sellerComplaint.getProductExchangeId() != null
                       && sellerComplaint.getProductExchangeId() != 0)) {
            throw new BusinessException("申诉申请只能关联退换货申请中的一个，请重试！");
        }

        //根据订单id取对应的订单信息 
        Orders order = ordersWriteDao.get(sellerComplaint.getOrderId());
        if (order == null) {
            throw new BusinessException("订单信息获取失败，请联系管理员！");
        } else {
            int orderState = order.getOrderState();
            if (orderState == Orders.ORDER_STATE_1 || orderState == Orders.ORDER_STATE_2
                || orderState == Orders.ORDER_STATE_3 || orderState == Orders.ORDER_STATE_4) {

                throw new BusinessException("订单此时所处状态不允许提交申诉申请！");
            }
        }

        //根据条件取申诉信息 判断是否已申请过申诉
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("userId", member.getId());
        queryMap.put("orderProductId", sellerComplaint.getOrderProductId());
        List<SellerComplaint> beanList = sellerComplaintWriteDao.queryList(queryMap);
        if (beanList.size() > 0) {
            throw new BusinessException("该产品已经提交过申诉了！");
        }

        sellerComplaint.setUserId(member.getId());
        sellerComplaint.setUserName(member.getName());
        sellerComplaint.setState(ConstantsEJS.SELLER_COMPLAINT_1);

        //1、保存信息
        dbConstrains(sellerComplaint);
        Integer count = sellerComplaintWriteDao.save(sellerComplaint);
        if (count == 0) {
            throw new BusinessException("产品申诉申请保存失败，请重试！");
        }

        return count;
    }

    /**
    * 更新商家投诉管理
    * @param  sellerComplaint
    * @return
    */
    public Integer updateSellerComplaint(SellerComplaint sellerComplaint) {
        if (sellerComplaint == null) {
            throw new BusinessException("更新投诉失败,投诉信息为空");
        }
        if (null == sellerComplaint.getId() || 0 == sellerComplaint.getId()) {
            throw new BusinessException("更新投诉失败,投诉信息id为空");
        }
        dbConstrains(sellerComplaint);
        return sellerComplaintWriteDao.update(sellerComplaint);
    }

    public Integer pageCount(Map<String, Object> queryMap) {
        return sellerComplaintWriteDao.getCount(queryMap);
    }

    public List<SellerComplaintVO> page(Map<String, Object> queryMap) throws Exception {
        List<SellerComplaint> list = sellerComplaintWriteDao.page(queryMap);

        List<SellerComplaintVO> volist = new ArrayList<SellerComplaintVO>();
        for (SellerComplaint sc : list) {
            SellerComplaintVO vo = new SellerComplaintVO();
            PropertyUtils.copyProperties(vo, sc);

            //订单号
            vo.setOrderSn(ordersWriteDao.get(vo.getOrderId()).getOrderSn());
            //商家名称
            vo.setSellerName(sellerReadDao.get(vo.getSellerId()).getSellerName());

            //来源及问题描述 
            vo.setSource(vo.getProductBackId() != null && vo.getProductBackId() != 0
                ? ComplaintSource.PRODUCT_BACK.getValue()
                : (vo.getProductExchangeId() != null && vo.getProductExchangeId() != 0
                    ? ComplaintSource.PRODUCT_EXCHANGE.getValue()
                    : ComplaintSource.UNKNOWN.getValue()));
            if (ComplaintSource.PRODUCT_BACK.getValue().equals(vo.getSource())) {
                MemberProductBack back = memberProductBackWriteDao.get(vo.getProductBackId());
                if (back != null)
                    vo.setQuestion(back.getQuestion());
                else
                    vo.setSource(ComplaintSource.UNKNOWN.getValue());
            } else if (ComplaintSource.PRODUCT_EXCHANGE.getValue().equals(vo.getSource())) {
                MemberProductExchange ex = memberProductExchangeWriteDao
                    .get(vo.getProductExchangeId());
                if (ex != null)
                    vo.setQuestion(ex.getQuestion());
                else
                    vo.setSource(ComplaintSource.UNKNOWN.getValue());
            }

            volist.add(vo);
        }

        return volist;
    }

    public boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除商家投诉管理[" + id + "]时出错");
        return sellerComplaintWriteDao.del(id) > 0;
    }

    public SellerComplaintVO getById(Integer id) throws Exception {
        SellerComplaint sr = getSellerComplaintById(id);
        SellerComplaintVO vo = new SellerComplaintVO();
        PropertyUtils.copyProperties(vo, sr);

        //订单号
        vo.setOrderSn(ordersWriteDao.get(vo.getOrderId()).getOrderSn());
        //被设计商品名称
        vo.setOrderProductName(ordersProductReadDao.get(vo.getOrderProductId()).getProductName());
        //商家账号
        vo.setSellerName(sellerReadDao.get(vo.getSellerId()).getSellerName());

        //来源及问题描述 
        vo.setSource(vo.getProductBackId() != null && vo.getProductBackId() != 0 ? "[用户退货]"
            : (vo.getProductExchangeId() != null && vo.getProductExchangeId() != 0 ? "[用户换货]"
                : "未知"));
        if ("[用户退货]".equals(vo.getSource())) {
            MemberProductBack back = memberProductBackWriteDao.get(vo.getProductBackId());
            if (back != null)
                vo.setQuestion(back.getQuestion());
            else
                vo.setSource("未知");
        } else if ("[用户换货]".equals(vo.getSource())) {
            MemberProductExchange ex = memberProductExchangeWriteDao.get(vo.getProductExchangeId());
            if (ex != null)
                vo.setQuestion(ex.getQuestion());
            else
                vo.setSource("未知");
        }

        return vo;
    }

    private void dbConstrains(SellerComplaint sellerComplaint) {
        sellerComplaint.setImage(StringUtil.dbSafeString(sellerComplaint.getImage(), false, 255));
        sellerComplaint
            .setContent(StringUtil.dbSafeString(sellerComplaint.getContent(), false, 1000));
        sellerComplaint
            .setOptContent(StringUtil.dbSafeString(sellerComplaint.getOptContent(), false, 255));
        sellerComplaint.setSellerCompContent(
            StringUtil.dbSafeString(sellerComplaint.getSellerCompContent(), false, 1000));
        sellerComplaint.setSellerCompImage(
            StringUtil.dbSafeString(sellerComplaint.getSellerCompImage(), false, 255));
        sellerComplaint
            .setUserContent(StringUtil.dbSafeString(sellerComplaint.getUserContent(), false, 255));
        sellerComplaint
            .setUserName(StringUtil.dbSafeString(sellerComplaint.getUserName(), false, 50));

    }
}
