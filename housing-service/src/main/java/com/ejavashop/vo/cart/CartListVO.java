package com.ejavashop.vo.cart;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.ejavashop.entity.cart.Cart;
import com.ejavashop.entity.promotion.full.ActFull;
import com.ejavashop.entity.seller.Seller;

/**
 * 购物车货品对应商家VO，用于购物车列表页、订单结算页显示
 *
 * @Filename: CartListVO.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public class CartListVO implements Serializable {

    private static final long serialVersionUID = -8404390784648809907L;

    private Seller            seller;                                  // 当前商品所属的商家
    private List<Cart>        cartList;                                // 购物车中属于当前商家的货品

    private BigDecimal        sellerLogisticsFee;                      // 当前商家所有货品的运费（订单结算页使用，只计算选中购物车的运费）

    private ActFull           actFull;                                 // 当前商家的满减活动
    private BigDecimal        sellerAmount;                            // 店铺 所有 商品小计，商品价格*数量之和，没有减去优惠的小计
    private BigDecimal        sellerDiscounted;                        // 店铺 所有 商品优惠金额小计（所有立减金额）
    private BigDecimal        sellerCheckedDiscounted;                 // 店铺 选中 商品优惠金额小计（满减+所有立减金额）
    private BigDecimal        sellerDiscountedAmount;                  // 店铺 所有 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（所有立减金额）的小计
    private BigDecimal        sellerCheckedAmount;                     // 店铺 选中 商品小计，商品价格*数量之和，没有减去优惠的小计
    private BigDecimal        sellerCheckedDiscountedAmount;           // 店铺 选中 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计
    private BigDecimal        orderFull;                               // 订单满减的满额，从actfull的3个档次中取满额（根据选中商品金额计算得出）
    private BigDecimal        orderDiscount;                           // 订单满减的减免额，从actfull的3个档次中取减免额（根据选中商品金额计算得出）

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }

    public BigDecimal getSellerLogisticsFee() {
        return sellerLogisticsFee;
    }

    public void setSellerLogisticsFee(BigDecimal sellerLogisticsFee) {
        this.sellerLogisticsFee = sellerLogisticsFee;
    }

    public ActFull getActFull() {
        return actFull;
    }

    public void setActFull(ActFull actFull) {
        this.actFull = actFull;
    }

    public BigDecimal getSellerAmount() {
        return sellerAmount;
    }

    public void setSellerAmount(BigDecimal sellerAmount) {
        this.sellerAmount = sellerAmount;
    }

    public BigDecimal getSellerDiscounted() {
        return sellerDiscounted;
    }

    public void setSellerDiscounted(BigDecimal sellerDiscounted) {
        this.sellerDiscounted = sellerDiscounted;
    }

    public BigDecimal getSellerCheckedDiscounted() {
        return sellerCheckedDiscounted;
    }

    public void setSellerCheckedDiscounted(BigDecimal sellerCheckedDiscounted) {
        this.sellerCheckedDiscounted = sellerCheckedDiscounted;
    }

    public BigDecimal getSellerDiscountedAmount() {
        return sellerDiscountedAmount;
    }

    public void setSellerDiscountedAmount(BigDecimal sellerDiscountedAmount) {
        this.sellerDiscountedAmount = sellerDiscountedAmount;
    }

    public BigDecimal getSellerCheckedAmount() {
        return sellerCheckedAmount;
    }

    public void setSellerCheckedAmount(BigDecimal sellerCheckedAmount) {
        this.sellerCheckedAmount = sellerCheckedAmount;
    }

    public BigDecimal getSellerCheckedDiscountedAmount() {
        return sellerCheckedDiscountedAmount;
    }

    public void setSellerCheckedDiscountedAmount(BigDecimal sellerCheckedDiscountedAmount) {
        this.sellerCheckedDiscountedAmount = sellerCheckedDiscountedAmount;
    }

    public BigDecimal getOrderFull() {
        return orderFull;
    }

    public void setOrderFull(BigDecimal orderFull) {
        this.orderFull = orderFull;
    }

    public BigDecimal getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(BigDecimal orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

}
