package com.ejavashop.service.impl.cart;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.cart.Cart;
import com.ejavashop.entity.member.MemberAddress;
import com.ejavashop.model.cart.CartModel;
import com.ejavashop.service.cart.ICartService;
import com.ejavashop.vo.cart.CartInfoVO;

@Service(value = "cartService")
public class CartServiceImpl implements ICartService {
    private static Logger log = LogManager.getLogger(CartServiceImpl.class);

    @Resource
    private CartModel     cartModel;

    @Override
    public ServiceResult<Boolean> addToCart(Cart cart) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(cartModel.addToCart(cart));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[CartService][addToCart]添加商品到购物车时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[CartService][addToCart]添加商品到购物车时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<CartInfoVO> getCartInfoByMId(Integer memberId, MemberAddress memberAddress,
                                                      Integer source, Integer useType) {
        ServiceResult<CartInfoVO> serviceResult = new ServiceResult<CartInfoVO>();
        try {
            serviceResult
                .setResult(cartModel.getCartInfoByMId(memberId, memberAddress, source, useType));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[CartService][getCartInfoByMId]根据用户ID获取购物车时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[CartService][getCartInfoByMId]根据用户ID获取购物车时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateCartNumber(Integer id, Integer number) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(cartModel.updateCartNumber(id, number));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[CartService][updateCartNumber]根据购物车id更新商城购物车时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[CartService][updateCartNumber]根据购物车id更新商城购物车时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteCartByIds(List<Integer> ids) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(cartModel.deleteCartByIds(ids));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[CartService][deleteCartByIds]批量删除购物车商品时出现未知异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[CartService][deleteCartByIds]批量删除购物车商品时出现未知异常:", e);
        }
        return result;
    }

    /**
    * 根据id取得商城购物车
    * @param  cartId
    * @return
    */
    @Override
    public ServiceResult<Cart> getCartById(Integer cartId) {
        ServiceResult<Cart> result = new ServiceResult<Cart>();
        try {
            result.setResult(cartModel.getCartById(cartId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[CartService][getCartById]根据id[" + cartId + "]取得商城购物车时出现未知异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[CartService][getCartById]根据id[" + cartId + "]取得商城购物车时出现未知异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> jobClearCart() {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(cartModel.jobClearCart());
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[CartService][jobClearCart]系统定时清理购物车数据时出现未知异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[CartService][jobClearCart]系统定时清理购物车数据时出现未知异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> getCartNumberByMId(Integer memberId) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(cartModel.getCartNumberByMId(memberId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[CartService][getCartNumberByMId]根据用户ID获取用户购物车数量时出现未知异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[CartService][getCartNumberByMId]根据用户ID获取用户购物车数量时出现未知异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateChecked(Integer memberId, Integer cartId, Integer checked) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(cartModel.updateChecked(memberId, cartId, checked));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error(
                "[CartService][updateChecked]根据用户ID、购物车ID修改购物车的选中状态时出现未知异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[CartService][updateChecked]根据用户ID、购物车ID修改购物车的选中状态时出现未知异常:", e);
        }
        return result;
    }
}