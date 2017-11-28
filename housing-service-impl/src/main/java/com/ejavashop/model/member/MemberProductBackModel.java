package com.ejavashop.model.member;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.ejavashop.dao.promotion.read.coupon.CouponUserReadDao;
import com.ejavashop.dao.promotion.write.coupon.CouponOptLogWriteDao;
import com.ejavashop.dao.promotion.write.coupon.CouponUserWriteDao;
import com.ejavashop.dao.shop.read.member.MemberProductBackReadDao;
import com.ejavashop.dao.shop.read.member.MemberProductExchangeReadDao;
import com.ejavashop.dao.shop.read.order.OrdersProductReadDao;
import com.ejavashop.dao.shop.read.order.OrdersReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.write.member.MemberBalanceLogsWriteDao;
import com.ejavashop.dao.shop.write.member.MemberGradeIntegralLogsWriteDao;
import com.ejavashop.dao.shop.write.member.MemberProductBackWriteDao;
import com.ejavashop.dao.shop.write.member.MemberWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersProductWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersWriteDao;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberBalanceLogs;
import com.ejavashop.entity.member.MemberGradeIntegralLogs;
import com.ejavashop.entity.member.MemberProductBack;
import com.ejavashop.entity.member.MemberProductExchange;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.coupon.CouponOptLog;
import com.ejavashop.entity.promotion.coupon.CouponUser;

/**
 * 会员退货管理
 *
 */
@Component(value = "memberProductBackModel")
public class MemberProductBackModel {
    private static Logger                   log = LogManager
        .getLogger(MemberProductBackModel.class);

    @Resource
    private MemberProductBackWriteDao       memberProductBackWriteDao;
    @Resource
    private MemberProductBackReadDao        memberProductBackReadDao;
    @Resource
    private MemberProductExchangeReadDao    memberProductExchangeReadDao;
    @Resource
    private OrdersReadDao                   ordersReadDao;
    @Resource
    private OrdersWriteDao                  ordersWriteDao;
    @Resource
    private ProductReadDao                  productReadDao;
    @Resource
    private OrdersProductReadDao            ordersProductReadDao;
    @Resource
    private OrdersProductWriteDao           ordersProductWriteDao;
    @Resource
    private MemberWriteDao                  memberWriteDao;
    @Resource
    private MemberBalanceLogsWriteDao       memberBalanceLogsWriteDao;
    @Resource
    private DataSourceTransactionManager    transactionManager;
    @Resource
    private MemberGradeIntegralLogsWriteDao memberGradeIntegralLogsWriteDao;
    @Resource
    private CouponUserReadDao               couponUserReadDao;
    @Resource
    private CouponUserWriteDao              couponUserWriteDao;
    @Resource
    private CouponOptLogWriteDao            couponOptLogWriteDao;

    /**
     * 根据id取得用户退货
     * @param memberProductBackId
     * @return
     */
    public MemberProductBack getMemberProductBackById(Integer memberProductBackId) {
        MemberProductBack memberProductBack = memberProductBackWriteDao.get(memberProductBackId);
        memberProductBack
            .setProductName(productReadDao.get(memberProductBack.getProductId()).getName1());
        memberProductBack
            .setOrderSn(ordersReadDao.get(memberProductBack.getOrderId()).getOrderSn());
        return memberProductBack;
    }

    /**
     * 更新用户退货
     * @param memberProductBack
     * @return
     */
    public boolean updateMemberProductBack(MemberProductBack memberProductBack) {
        return memberProductBackWriteDao.update(memberProductBack) > 0;
    }

    /**
     * 退货逻辑简略说明（详细请参考代码注释）<br>
     * 退货涉及退款主要影响字段：orders.money_order,orders.money_integral
     * <li>money_order：订单支付的金额，现金支付、余额支付、积分支付之和，退款时现金支付和余额支付部分无区别，都按照余额退到账户余额中，
     *                  积分支付部分是在现金支付和余额支付部分金额退完之后再退积分；
     * <li>money_integral：订单使用的积分转换后的金额，这部分金额只退回积分，不退余额；<br>
     * <br>
     * <li>退款原则：
     * <li>1、退到余额的金额不能大于现金支付+余额支付之和，退回的积分不能大于integral值；<br>
     * <li>2、先退现金支付+余额支付部分，再退积分支付部分，最后退优惠券；<br>
     * <li>3、优惠券只有在退到最后一个网单的时候才会退回（因为优惠券不可拆分，所以即使优惠券的金额大于最后一个网单的金额也不会退）；<br>
     * <li>4、退款金额计算不考虑是否参加了单品立减活动；<br>
     * <li>5、退货网单金额按照最终支付金额乘以网单在整个订单中的金额比例计算；
     * <li>6、运费不退；
     * <br>
     * <br>
     * 避免主从库数据同步延迟所有退货表的数据从写库读取
     * 
     * @param memberProductBack
     * @param request
     * @return
     */
    public boolean saveMemberProductBack(MemberProductBack memberProductBack, Member member) {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            // 参数校验
            if (memberProductBack == null) {
                throw new BusinessException("退货申请不能为空，请重试！");
            } else if (memberProductBack.getProductId() == null
                       || memberProductBack.getProductId() == 0) {
                throw new BusinessException("退货申请产品id不能为空，请重试！");
            } else if (memberProductBack.getOrderProductId() == null
                       || memberProductBack.getOrderProductId() == 0) {
                throw new BusinessException("退货申请网单id不能为空，请重试！");
            } else if (memberProductBack.getOrderId() == null
                       || memberProductBack.getOrderId() == 0) {
                throw new BusinessException("退货申请订单id不能为空，请重试！");
            }

            // 获取该订单下已经申请退货的退货表数据
            Map<String, Object> queryMap = new HashMap<String, Object>();
            queryMap.clear();
            queryMap.put("memberId", member.getId());
            queryMap.put("orderId", memberProductBack.getOrderId());
            List<MemberProductBack> backedList = memberProductBackWriteDao.queryList(queryMap);
            // 已经申请过退货的退款金额和
            BigDecimal backedMoney = BigDecimal.ZERO;
            // 已经申请过退货的返回积分和
            Integer backedIntegral = 0;
            // 已经申请过退货的返回积分金额
            BigDecimal backedIntegralMoney = BigDecimal.ZERO;
            // 已经返回的优惠券
            List<Integer> couponUserList = new ArrayList<Integer>();
            if (backedList.size() > 0) {
                for (MemberProductBack back : backedList) {
                    // 如果该网单已经申请过退货，抛异常
                    if (memberProductBack.getOrderProductId().equals(back.getOrderProductId())) {
                        log.error("该产品已经申请过退货了。");
                        throw new BusinessException("该产品已经申请过退货了！");
                    }
                    // 累计退款金额
                    backedMoney = backedMoney.add(back.getBackMoney());
                    // 累计退回的积分、积分金额
                    if (back.getBackIntegral() != null && back.getBackIntegral() > 0) {
                        backedIntegral = backedIntegral + back.getBackIntegral();
                        backedIntegralMoney = backedIntegralMoney.add(back.getBackIntegralMoney());
                    }
                    // 记录已退还的优惠券
                    if (back.getBackCouponUserId() != null && back.getBackCouponUserId() > 0) {
                        couponUserList.add(back.getBackCouponUserId());
                    }
                }
            }

            //根据订单id取对应的订单信息 
            Orders order = ordersReadDao.get(memberProductBack.getOrderId());
            if (order == null) {
                log.error("订单信息获取失败。");
                throw new BusinessException("订单信息获取失败，请联系管理员！");
            } else {
                int orderState = order.getOrderState();
                if (orderState == Orders.ORDER_STATE_1 || orderState == Orders.ORDER_STATE_2
                    || orderState == Orders.ORDER_STATE_3 || orderState == Orders.ORDER_STATE_4) {
                    log.error("订单此时所处状态不允许提交退货申请。");
                    throw new BusinessException("订单此时所处状态不允许提交退货申请！");
                }
            }
            //获取网单信息
            List<OrdersProduct> opList = ordersProductWriteDao.getByOrderId(order.getId());
            if (opList == null || opList.size() == 0) {
                log.error("根据订单号（" + order.getId() + "）获取网单为空。");
                throw new BusinessException("网单不存在！");
            }
            OrdersProduct ordersProduct = null;
            for (OrdersProduct op : opList) {
                if (op.getId().equals(memberProductBack.getOrderProductId())) {
                    ordersProduct = op;
                    break;
                }
            }
            if (ordersProduct == null) {
                log.error("获取网单(网单号：" + memberProductBack.getOrderProductId() + ")为空。");
                throw new BusinessException("网单不存在！");
            }

            /*
             * 计算退款金额
             */
            // 订单总退款限制金额（现金+余额-运费），运费不退
            BigDecimal backLimit = order.getMoneyPaidBalance().add(order.getMoneyPaidReality())
                .subtract(order.getMoneyLogistics());
            backLimit = backLimit.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : backLimit;
            // 订单总返回积分限制数量
            Integer integralLimit = order.getIntegral();
            // 订单返回积分金额限制金额
            BigDecimal integralMoneyLimit = order.getMoneyIntegral();

            // 整个订单还能退回的金额：总退款限制金额-已经退款的金额和
            BigDecimal backBalance = backLimit.subtract(backedMoney);
            // 整个订单还能退回的积分：总返回积分限制数量-已经返回积分和
            Integer backIntBalance = integralLimit - backedIntegral;
            // 整个订单还能退回的积分金额：总返回积分金额限制金额-已经退回的积分金额和
            BigDecimal backIntMoneyBalance = integralMoneyLimit.subtract(backedIntegralMoney);

            if (opList.size() == 1) {
                /*
                 * 如果只有一个网单，则不需要计算比例等
                 */
                // 退款金额就是总退款限制金额
                memberProductBack.setBackMoney(backLimit);
                // 返回积分就是积分限制数量
                memberProductBack.setBackIntegral(integralLimit);
                // 返回积分金额就是积分金额限制金额
                memberProductBack.setBackIntegralMoney(integralMoneyLimit);
                // 返回优惠券为订单记录的优惠券
                memberProductBack.setBackCouponUserId(order.getCouponUserId());
            } else {
                /*
                 * 如果是多个网单，从新计算网单金额（解决用户下单后由商家修改订单金额的情况）
                 * 新网单金额=（订单总退款限制金额 + 积分金额）*（原网单金额/商品总金额）
                 * 原网单金额=order_product.money_amount + order_product.money_act_single(因为order.moneyProduct是没有减去立减金额的钱，所以计算时按原价计算)
                 */
                // 原网单金额
                BigDecimal oldOrderProductAmount = ordersProduct.getMoneyAmount()
                    .add(ordersProduct.getMoneyActSingle());
                // 新网单金额
                BigDecimal opMoneyAmountNew = (backLimit.add(order.getMoneyIntegral()))
                    .multiply(oldOrderProductAmount.divide(order.getMoneyProduct(), 4,
                        BigDecimal.ROUND_HALF_UP));
                opMoneyAmountNew = opMoneyAmountNew.setScale(2, BigDecimal.ROUND_HALF_UP);

                /*
                 * 多个网单
                 */
                if (opMoneyAmountNew.compareTo(backBalance) < 1) {
                    /*
                     * 如果网单金额 <= 【整个订单还能退回的金额】，则直接使用网单金额作为退款金额
                     */
                    // 此时直接使用网单金额作为退款金额
                    memberProductBack.setBackMoney(opMoneyAmountNew);
                    // 返回积分设定0
                    memberProductBack.setBackIntegral(0);
                    // 返回积分金额设定0
                    memberProductBack.setBackIntegralMoney(BigDecimal.ZERO);
                    // 返回优惠券ID设定0
                    memberProductBack.setBackCouponUserId(0);
                } else {
                    /*
                     * 如果网单金额 >  【整个订单还能退回的金额】，则使用积分补充，如果是最后一个网单，则优惠券也退回
                     */
                    // 退款金额直接设定成【整个订单还能退回的金额】
                    memberProductBack.setBackMoney(backBalance);
                    // 计算需要积分补充的金额
                    if ((opList.size() - 1) == backedList.size()) {
                        /*
                         * 如果是最后一个网单，直接退回所有积分，积分金额，优惠券
                         */
                        // 返回积分设定【整个订单还能退回的积分】
                        memberProductBack.setBackIntegral(backIntBalance);
                        // 返回积分金额设定【整个订单还能退回的积分金额】
                        memberProductBack.setBackIntegralMoney(backIntMoneyBalance);
                        // 返回优惠券ID设定为订单的优惠券ID
                        if (!couponUserList.contains(order.getCouponUserId())) {
                            // 当前代码版本的逻辑couponUserList中不会出现有数据的情况
                            // 此判断是为防止客户修改优惠券使用逻辑但是没有修改退货逻辑导致一个优惠券多次退还的风险
                            memberProductBack.setBackCouponUserId(order.getCouponUserId());
                        }
                    } else {
                        // 不是最后一个网单，返回优惠券ID设定0
                        memberProductBack.setBackCouponUserId(0);
                        /*
                         * 如果不是最后一个订单则计算返回的积分数，返回优惠券设定成0
                         */
                        // 如果使用了积分则计算，如果没有使用积分则设定0
                        if (order.getIntegral() > 0) {
                            // 倒推下单时积分和金额之间的换算比例
                            int integralScale = order.getIntegral()
                                                / (order.getMoneyIntegral().intValue());
                            // 计算需要补充的积分数量
                            int supplement = ((opMoneyAmountNew.subtract(backBalance))
                                .multiply(new BigDecimal(integralScale))).intValue();
                            // 如果需要补充的数量小于等于【整个订单还能退回的积分】，则积分数量设定成需要补充的数量
                            // 否则设定成【整个订单还能退回的积分】
                            if (supplement <= backIntBalance) {
                                // 返回积分设定成【需要补充的数量】
                                memberProductBack.setBackIntegral(supplement);
                                // 返回积分金额设定设定：【需要补充的数量】/换算比例
                                BigDecimal supplementMoney = (new BigDecimal(supplement)).divide(
                                    new BigDecimal(integralScale), 2, BigDecimal.ROUND_HALF_UP);
                                memberProductBack.setBackIntegralMoney(supplementMoney);
                            } else {
                                // 返回积分设定成【整个订单还能退回的积分】
                                memberProductBack.setBackIntegral(backIntBalance);
                                // 返回积分金额设定【整个订单还能退回的积分金额】
                                memberProductBack.setBackIntegralMoney(backIntMoneyBalance);
                            }
                        } else {
                            // 返回积分设定0
                            memberProductBack.setBackIntegral(0);
                            // 返回积分金额设定0
                            memberProductBack.setBackIntegralMoney(BigDecimal.ZERO);
                        }
                    }
                }
            }

            // 退货人信息
            if (StringUtil.isEmpty(memberProductBack.getPhone(), true)) {
                memberProductBack.setPhone(order.getMobile());
            }
            if (StringUtil.isEmpty(memberProductBack.getReturnName(), true)) {
                memberProductBack.setReturnName(order.getName());
            }

            memberProductBack.setMemberId(member.getId());
            memberProductBack.setMemberName(member.getName());
            memberProductBack.setStateReturn(MemberProductBack.STATE_RETURN_1);
            memberProductBack.setStateMoney(MemberProductBack.STATE_MONEY_1);

            //1、保存信息
            Integer count = memberProductBackWriteDao.save(memberProductBack);
            if (count == 0) {
                throw new BusinessException("产品退货申请保存失败，请重试！");
            }

            OrdersProduct ordersProductNew = new OrdersProduct();
            ordersProductNew.setId(ordersProduct.getId());
            ordersProductNew.setMemberProductBackId(memberProductBack.getId());
            Integer pbcount = ordersProductWriteDao.update(ordersProductNew);
            if (pbcount == 0) {
                throw new BusinessException("网单退货信息更新失败，请重试！");
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
     * 根据登录用户取得用户退货列表
     * @param request
     * @return
     */
    public List<MemberProductBack> getMemberProductBackList(PagerInfo pager, Integer memberId) {

        //取所有的退货申请
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("memberId", memberId);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(memberProductBackReadDao.queryCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        queryMap.put("start", start);
        queryMap.put("size", size);
        List<MemberProductBack> beanList = memberProductBackReadDao.queryList(queryMap);

        for (MemberProductBack bean : beanList) {
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

    /**
     * 判断是否可以发起退换货申请
     * @param orderId
     * @param orderProductId
     * @param request
     * @return
     */
    public boolean canApplyProductBackOrExchange(Integer orderId, Integer orderProductId,
                                                 Integer memberId) {

        // 参数校验
        if (orderProductId == null || orderProductId == 0) {
            throw new BusinessException("网单id不能为空，请重试！");
        } else if (orderId == null || orderId == 0) {
            throw new BusinessException("订单id不能为空，请重试！");
        }

        //根据订单id取对应的订单信息 
        Orders order = ordersReadDao.get(orderId);
        if (order == null) {
            log.error("订单信息获取失败。");
            throw new BusinessException("订单信息获取失败，请联系管理员！");
        } else {
            int orderState = order.getOrderState();
            if (orderState == Orders.ORDER_STATE_1 || orderState == Orders.ORDER_STATE_2
                || orderState == Orders.ORDER_STATE_3 || orderState == Orders.ORDER_STATE_4) {
                throw new BusinessException("订单此时所处状态不允许提交退换货申请。");
            }
        }
        //根据条件取退货信息 判断是否已申请过退货
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("memberId", memberId);
        queryMap.put("orderProductId", orderProductId);
        //退货信息
        List<MemberProductBack> beanList = memberProductBackWriteDao.queryList(queryMap);
        //换货信息
        List<MemberProductExchange> list = memberProductExchangeReadDao.queryList(queryMap);
        if (beanList.size() > 0) {
            throw new BusinessException("该产品已经申请过退货了。");
        } else if (list.size() > 0) {
            throw new BusinessException("该产品已经申请过换货了。");
        }

        return true;
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return memberProductBackWriteDao.getCount(queryMap);
    }

    public List<MemberProductBack> page(Map<String, String> queryMap, Integer start, Integer size) {
        List<MemberProductBack> list = memberProductBackWriteDao.page(queryMap, start, size);
        for (MemberProductBack back : list) {
            back.setProductName(productReadDao.get(back.getProductId()).getName1());
            back.setOrderSn(ordersReadDao.get(back.getOrderId()).getOrderSn());
            if (back.getBackCouponUserId() != null && back.getBackCouponUserId() > 0) {
                back.setCouponUser(couponUserReadDao.get(back.getBackCouponUserId()));
            }
        }
        return list;
    }

    public List<MemberProductBack> list() {
        List<MemberProductBack> list = memberProductBackWriteDao.list();
        return list;
    }

    public boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除用户退货[" + id + "]时出错");
        return memberProductBackWriteDao.del(id) > 0;
    }

    public boolean backMoney(Integer memberProductBackId, Integer optId, String optName) {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            MemberProductBack backDB = memberProductBackReadDao.get(memberProductBackId);
            if (backDB == null) {
                throw new BusinessException("退款申请信息获取失败，请重试！");
            }
            if (backDB.getStateReturn().intValue() != MemberProductBack.STATE_RETURN_3) {
                throw new BusinessException("只有申请状态是已经收货的申请才能退款！");
            }
            if (backDB.getStateMoney().intValue() != MemberProductBack.STATE_MONEY_1) {
                throw new BusinessException("该申请已经退款，请勿重复操作！");
            }

            MemberProductBack back = new MemberProductBack();
            back.setId(memberProductBackId);
            // 退货状态-已收货
            back.setStateMoney(MemberProductBack.STATE_MONEY_2);
            back.setBackMoneyTime(new Date());
            Integer updateResult = memberProductBackWriteDao.update(back);
            if (updateResult == 0) {
                throw new BusinessException("退款失败，请重试！");
            }

            Member member = memberWriteDao.get(backDB.getMemberId());

            // 获取订单
            Orders order = ordersReadDao.get(backDB.getOrderId());
            if (order == null) {
                throw new BusinessException("订单信息获取失败，请重试！");
            }

            // 获取网单
            OrdersProduct ordersProduct = ordersProductReadDao.get(backDB.getOrderProductId());
            if (ordersProduct == null) {
                throw new BusinessException("网单信息获取失败，请重试！");
            }

            // 修改订单退款金额
            Integer updateMoneyBack = ordersWriteDao.updateMoneyBack(order.getId(),
                backDB.getBackMoney().toString());
            if (updateMoneyBack == 0) {
                throw new BusinessException("修改订单退款金额时失败，请重试！");
            }

            // 修改用户余额
            Member memberNew = new Member();
            memberNew.setId(member.getId());
            memberNew.setBalance(backDB.getBackMoney());
            Integer updateBalance = memberWriteDao.updateBalance(memberNew);
            if (updateBalance == 0) {
                throw new BusinessException("修改用户余额时失败，请重试！");
            }

            // 变动日志
            MemberBalanceLogs logs = new MemberBalanceLogs();
            logs.setMoneyBefore(member.getBalance());
            logs.setMemberId(member.getId());
            logs.setMemberName(member.getName());
            logs.setMoneyAfter(member.getBalance().add(backDB.getBackMoney()));
            logs.setMoney(backDB.getBackMoney());
            logs.setCreateTime(new Date());
            logs.setState(MemberBalanceLogs.STATE_2);
            logs.setRemark("订单" + order.getOrderSn() + "退货退款");
            logs.setOptId(optId);
            logs.setOptName(optName);

            Integer save = memberBalanceLogsWriteDao.save(logs);
            if (save == 0) {
                throw new BusinessException("记录用户余额变更日志时失败，请重试！");
            }

            // 如果有返回积分，则修改用户积分，记录积分变更日志
            if (backDB.getBackIntegral() != null && backDB.getBackIntegral() > 0) {
                memberNew.setId(member.getId());
                memberNew.setIntegral(backDB.getBackIntegral());
                Integer updateIntegral = memberWriteDao.updateIntegral(memberNew);
                if (updateIntegral == 0) {
                    throw new BusinessException("修改用户积分时失败，请重试！");
                }
                MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
                memberGradeIntegralLogs.setMemberId(backDB.getMemberId());
                memberGradeIntegralLogs.setMemberName(backDB.getMemberName());
                memberGradeIntegralLogs.setValue(backDB.getBackIntegral());
                memberGradeIntegralLogs.setOptType(ConstantsEJS.MEMBER_GRD_INT_LOG_OPT_T_8);
                memberGradeIntegralLogs.setOptDes("订单" + order.getOrderSn() + "发生退货返还积分");
                memberGradeIntegralLogs.setType(ConstantsEJS.MEMBER_GRD_INT_LOG_T_2);
                memberGradeIntegralLogs.setCreateTime(new Date());
                Integer saveLog = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                if (saveLog == 0) {
                    throw new BusinessException("记录用户积分日志失败，请重试！");
                }
            }

            // 如果有返回优惠券，则修改用户优惠券使用次数，并记录操作日志
            if (backDB.getBackCouponUserId() != null && backDB.getBackCouponUserId() > 0) {
                CouponUser couponUser = couponUserWriteDao.get(backDB.getBackCouponUserId());
                if (couponUser == null) {
                    throw new BusinessException("获取用户优惠券信息时失败，请重试！");
                }
                // 返回优惠券（使用次数加1）
                Integer backCouponUser = couponUserWriteDao.backCouponUser(backDB.getMemberId(),
                    backDB.getBackCouponUserId());
                if (backCouponUser == 0) {
                    throw new BusinessException("返回用户优惠券时失败，请重试！");
                }

                // 记录优惠券操作日志
                CouponOptLog couponOptLog = new CouponOptLog();
                couponOptLog.setCouponUserId(couponUser.getId());
                couponOptLog.setMemberId(couponUser.getMemberId());
                couponOptLog.setSellerId(couponUser.getSellerId());
                couponOptLog.setCouponId(couponUser.getCouponId());
                couponOptLog.setOptType(CouponOptLog.OPT_TYPE_4);
                couponOptLog.setOrderId(order.getId());
                couponOptLog.setCreateUserId(optId);
                couponOptLog.setCreateUserName(optName);
                couponOptLog.setCreateTime(new Date());
                couponOptLogWriteDao.insert(couponOptLog);
            }

            transactionManager.commit(status);
            return true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    public Integer getSettleBacksCount(Integer sellerId, String startTime, String endTime) {
        return memberProductBackReadDao.getSettleBacksCount(sellerId, startTime, endTime);
    }

    public List<MemberProductBack> getSettleBacks(Integer sellerId, String startTime,
                                                  String endTime, Integer start, Integer size) {
        List<MemberProductBack> settleBacks = memberProductBackReadDao.getSettleBacks(sellerId,
            startTime, endTime, start, size);
        if (settleBacks != null && settleBacks.size() > 0) {
            for (MemberProductBack back : settleBacks) {
                // 设定orderSn和商品名称
                Orders orders = ordersReadDao.get(back.getOrderId());
                back.setOrderSn(orders == null ? "" : orders.getOrderSn());
                Product product = productReadDao.get(back.getProductId());
                back.setProductName(product == null ? "" : product.getName1());
            }
        }
        return settleBacks;
    }

    /**
     * 根据登录用户取得用户退货列表
     * @param pager
     * @param memberId
     * @return
     */
    public List<MemberProductBack> getBackListWithPrdAndOp(PagerInfo pager, Integer memberId) {

        //取所有的退货申请
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("memberId", memberId);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(memberProductBackReadDao.queryCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        queryMap.put("start", start);
        queryMap.put("size", size);
        List<MemberProductBack> beanList = memberProductBackReadDao.queryList(queryMap);

        for (MemberProductBack bean : beanList) {
            //获得对应产品信息
            bean.setProduct(productReadDao.get(bean.getProductId()));
            bean.setOrdersProduct(ordersProductReadDao.get(bean.getOrderProductId()));
            // 如果有返回优惠券则查询
            if (bean.getBackCouponUserId() != null && bean.getBackCouponUserId() > 0) {
                bean.setCouponUser(couponUserReadDao.get(bean.getBackCouponUserId()));
            }
        }
        return beanList;
    }
}
