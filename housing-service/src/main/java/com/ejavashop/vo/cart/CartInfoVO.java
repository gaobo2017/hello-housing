package com.ejavashop.vo.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车信息VO，用于购物车列表页、订单结算页显示
 *
 * @Filename: CartInfoVO.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public class CartInfoVO implements Serializable {

    private static final long serialVersionUID = 343228346630329707L;

    private List<CartListVO>  cartListVOs;                           // 购物车各个商家的货品集合
    private BigDecimal        logisticsFeeAmount;                    // 物流总费用

    private Integer           totalNumber;                           // 购物车数量合计
    private Integer           totalCheckedNumber;                    // 购物车选中数量合计

    private BigDecimal        discountAmount;                        // 所有商品优惠金额合计，减去优惠（所有立减金额）的合计
    private BigDecimal        checkedDiscountedAmount;               // 选中商品优惠合计，减去优惠（满减+所有立减金额）的合计

    private BigDecimal        cartAmount;                            // 购物车所有商品合计，所有商品价格*数量之和，没有减去优惠的合计价格
    private BigDecimal        checkedCartAmount;                     // 选中商品合计，选中商品价格*数量之和，没有减去优惠的合计价格
    private BigDecimal        checkedDiscountedCartAmount;           // 选中商品优惠后合计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计

    // private BigDecimal        finalAmount;                           // 最终金额合计，等于 选中商品优惠后合计 + 运费 后的实际需要支付的金额，不减去余额支付的金额

    /**
    * 计算最终金额合计，等于 选中商品优惠后合计 + 运费 后的实际需要支付的金额，如果减出来的金额是负数，赋值为零，不减去余额支付的金额
    * @return
    */
    public BigDecimal getFinalAmount() {
        BigDecimal checkedDiscountedCartAmountP = this.checkedDiscountedCartAmount == null
            ? BigDecimal.ZERO : this.checkedDiscountedCartAmount;
        BigDecimal logisticsFeeAmountP = this.logisticsFeeAmount == null ? BigDecimal.ZERO
            : this.logisticsFeeAmount;

        BigDecimal amount = checkedDiscountedCartAmountP.add(logisticsFeeAmountP);
        return amount.compareTo(BigDecimal.ZERO) <= 0 ? BigDecimal.ZERO : amount;
    }

    public List<CartListVO> getCartListVOs() {
        return cartListVOs;
    }

    public void setCartListVOs(List<CartListVO> cartListVOs) {
        this.cartListVOs = cartListVOs;
    }

    public BigDecimal getLogisticsFeeAmount() {
        return logisticsFeeAmount;
    }

    public void setLogisticsFeeAmount(BigDecimal logisticsFeeAmount) {
        this.logisticsFeeAmount = logisticsFeeAmount;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getTotalCheckedNumber() {
        return totalCheckedNumber;
    }

    public void setTotalCheckedNumber(Integer totalCheckedNumber) {
        this.totalCheckedNumber = totalCheckedNumber;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getCheckedDiscountedAmount() {
        return checkedDiscountedAmount;
    }

    public void setCheckedDiscountedAmount(BigDecimal checkedDiscountedAmount) {
        this.checkedDiscountedAmount = checkedDiscountedAmount;
    }

    public BigDecimal getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(BigDecimal cartAmount) {
        this.cartAmount = cartAmount;
    }

    public BigDecimal getCheckedCartAmount() {
        return checkedCartAmount;
    }

    public void setCheckedCartAmount(BigDecimal checkedCartAmount) {
        this.checkedCartAmount = checkedCartAmount;
    }

    public BigDecimal getCheckedDiscountedCartAmount() {
        return checkedDiscountedCartAmount;
    }

    public void setCheckedDiscountedCartAmount(BigDecimal checkedDiscountedCartAmount) {
        this.checkedDiscountedCartAmount = checkedDiscountedCartAmount;
    }

}
