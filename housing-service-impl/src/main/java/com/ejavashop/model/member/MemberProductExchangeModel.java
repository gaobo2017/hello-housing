package com.ejavashop.model.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.member.MemberProductExchangeReadDao;
import com.ejavashop.dao.shop.read.order.OrdersProductReadDao;
import com.ejavashop.dao.shop.read.order.OrdersReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.write.member.MemberProductExchangeWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersProductWriteDao;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberProductExchange;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.product.Product;

/**
 * 会员换货管理                       
 *
 */
@Component(value = "memberProductExchangeModel")
public class MemberProductExchangeModel {
    private static Logger                 log = LogManager
        .getLogger(MemberProductExchangeModel.class);

    @Resource
    private MemberProductExchangeWriteDao memberProductExchangeWriteDao;
    @Resource
    private MemberProductExchangeReadDao  memberProductExchangeReadDao;
    @Resource
    private OrdersReadDao                 ordersReadDao;
    @Resource
    private ProductReadDao                productReadDao;
    @Resource
    private OrdersProductWriteDao         ordersProductWriteDao;
    @Resource
    private OrdersProductReadDao          ordersProductReadDao;
    @Resource
    private DataSourceTransactionManager  transactionManager;

    /**
    * 根据id取得用户换货
    * @param  memberProductExchangeId
    * @return
    */
    public MemberProductExchange getMemberProductExchangeById(Integer memberProductExchangeId) {
        return memberProductExchangeWriteDao.get(memberProductExchangeId);
    }

    /**
     * 保存用户换货
     * @param memberProductExchange
     * @param member
     * @return
     */
    public boolean saveMemberProductExchange(MemberProductExchange memberProductExchange,
                                             Member member) {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            // 参数校验
            if (memberProductExchange == null) {
                throw new BusinessException("换货申请不能为空，请重试！");
            } else if (memberProductExchange.getProductId() == null
                       || memberProductExchange.getProductId() == 0) {
                throw new BusinessException("换货申请产品id不能为空，请重试！");
            } else if (memberProductExchange.getOrderProductId() == null
                       || memberProductExchange.getOrderProductId() == 0) {
                throw new BusinessException("换货申请网单id不能为空，请重试！");
            } else if (memberProductExchange.getOrderId() == null
                       || memberProductExchange.getOrderId() == 0) {
                throw new BusinessException("换货申请订单id不能为空，请重试！");
            }

            //根据订单id取对应的订单信息 
            Orders order = ordersReadDao.get(memberProductExchange.getOrderId());
            if (order == null) {
                log.error("订单信息获取失败。");
                throw new BusinessException("订单信息获取失败，请联系管理员！");
            } else {
                int orderState = order.getOrderState();
                if (orderState == Orders.ORDER_STATE_1 || orderState == Orders.ORDER_STATE_2
                    || orderState == Orders.ORDER_STATE_3 || orderState == Orders.ORDER_STATE_4) {

                    log.error("订单此时所处状态不允许提交换货申请。");
                    throw new BusinessException("订单此时所处状态不允许提交换货申请！");
                }
            }

            // 如果换货单的地址信息为空则默认用原单地址信息
            if (memberProductExchange.getProvinceId() == null
                || memberProductExchange.getProvinceId().intValue() == 0) {
                memberProductExchange.setProvinceId(order.getProvinceId());
                memberProductExchange.setCityId(order.getCityId());
                memberProductExchange.setAreaId(order.getAreaId());
                memberProductExchange.setAddressInfo(order.getAddressInfo());
                memberProductExchange.setAddressAll(order.getAddressAll());
                memberProductExchange.setPhone(order.getMobile());
                memberProductExchange.setChangeName(order.getName());
            }
            if (memberProductExchange.getProvinceId2() == null
                || memberProductExchange.getProvinceId2().intValue() == 0) {
                memberProductExchange.setProvinceId2(order.getProvinceId());
                memberProductExchange.setCityId2(order.getCityId());
                memberProductExchange.setAreaId2(order.getAreaId());
                memberProductExchange.setAddressInfo2(order.getAddressInfo());
                memberProductExchange.setAddressAll2(order.getAddressAll());
                memberProductExchange.setPhone2(order.getMobile());
                memberProductExchange.setChangeName2(order.getName());
            }
            if (StringUtil.isEmpty(memberProductExchange.getEmail(), true)) {
                memberProductExchange.setEmail(order.getEmail());
            }
            if (StringUtil.isEmpty(memberProductExchange.getZipCode(), true)) {
                memberProductExchange.setZipCode(order.getZipCode());
            }
            if (StringUtil.isEmpty(memberProductExchange.getName(), true)) {
                memberProductExchange.setName(order.getName());
            }

            //根据条件取换货信息 判断是否已申请过换货
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.put("memberId", member.getId());
            queryMap.put("orderProductId", memberProductExchange.getOrderProductId());
            List<MemberProductExchange> beanList = memberProductExchangeWriteDao
                .queryList(queryMap);
            if (beanList.size() > 0) {
                log.error("该产品已经申请过换货了。");
                throw new BusinessException("该产品已经申请过换货了！");
            }

            memberProductExchange.setMemberId(member.getId());
            memberProductExchange.setMemberName(member.getName());
            memberProductExchange.setState(ConstantsEJS.MEM_PROD_EXCHG_STATE_1);
            //1、保存信息
            Integer count = memberProductExchangeWriteDao.save(memberProductExchange);
            if (count == 0) {
                throw new BusinessException("产品换货申请保存失败，请重试！");
            }
            //获取网单信息
            OrdersProduct ordersProduct = ordersProductWriteDao
                .get(memberProductExchange.getOrderProductId());
            if (ordersProduct == null) {
                log.error("网单不存在。");
                throw new BusinessException("网单不存在！");
            } else {
                ordersProduct.setMemberProductExchangeId(memberProductExchange.getId());
                Integer pbcount = ordersProductWriteDao.update(ordersProduct);
                if (pbcount == 0) {
                    throw new BusinessException("网单换货信息更新失败，请重试！");
                }
            }

            transactionManager.commit(status);
            return count > 0;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
    * 更新用户换货
    * @param  memberProductExchange
    * @return
    */
    public boolean updateMemberProductExchange(MemberProductExchange memberProductExchange) {
        return memberProductExchangeWriteDao.update(memberProductExchange) > 0;
    }

    /**
     * 根据登录用户取得用户换货列表 分页
     * @param pager
     * @param request
     * @return
     */
    public List<MemberProductExchange> getMemberProductExchangeList(Map<String, Object> queryMap,
                                                                    PagerInfo pager,
                                                                    Integer memberId) {

        //取所有的换货申请
        queryMap.put("memberId", memberId);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(memberProductExchangeReadDao.queryCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        queryMap.put("start", start);
        queryMap.put("size", size);
        List<MemberProductExchange> beanList = memberProductExchangeReadDao.queryList(queryMap);

        for (MemberProductExchange bean : beanList) {
            //取对应的订单信息 根据订单id
            Orders order = ordersReadDao.get(bean.getOrderId());
            if (order == null) {
                log.error("订单信息获取失败。");
                throw new BusinessException("订单信息获取失败，请联系管理员！");
            }
            //获得对应产品信息
            Product product = productReadDao.get(bean.getProductId());
            bean.setProductName(product.getName1());
            bean.setOrderSn(order.getOrderSn());
        }
        return beanList;
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return memberProductExchangeWriteDao.getCount(queryMap);
    }

    public List<MemberProductExchange> page(Map<String, String> queryMap, Integer start,
                                            Integer size) {
        List<MemberProductExchange> list = memberProductExchangeWriteDao.page(queryMap, start,
            size);
        for (MemberProductExchange exchange : list) {
            exchange.setProductName(productReadDao.get(exchange.getProductId()).getName1());
            exchange.setOrderSn(ordersReadDao.get(exchange.getOrderId()).getOrderSn());
        }
        return list;
    }

    /**
     * 根据登录用户取得用户换货列表(封装商品对象和网单对象)
     * @param pager
     * @param memberId
     * @return
     */
    public List<MemberProductExchange> getExchangeListWithPrdAndOp(PagerInfo pager,
                                                                   Integer memberId) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("memberId", memberId);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(memberProductExchangeReadDao.queryCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        queryMap.put("start", start);
        queryMap.put("size", size);
        List<MemberProductExchange> beanList = memberProductExchangeReadDao.queryList(queryMap);

        for (MemberProductExchange bean : beanList) {
            bean.setProduct(productReadDao.get(bean.getProductId()));
            bean.setOrdersProduct(ordersProductReadDao.get(bean.getOrderProductId()));
        }
        return beanList;
    }
}
