package com.ejavashop.model.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.TimeUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.promotion.read.full.ActFullReadDao;
import com.ejavashop.dao.promotion.read.single.ActSingleReadDao;
import com.ejavashop.dao.shop.read.cart.CartReadDao;
import com.ejavashop.dao.shop.read.product.ProductGoodsReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.dao.shop.write.cart.CartWriteDao;
import com.ejavashop.dao.shop.write.product.ProductGoodsWriteDao;
import com.ejavashop.entity.cart.Cart;
import com.ejavashop.entity.member.MemberAddress;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.promotion.full.ActFull;
import com.ejavashop.entity.promotion.single.ActSingle;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.model.seller.SellerTransportModel;
import com.ejavashop.vo.cart.CartInfoVO;
import com.ejavashop.vo.cart.CartListVO;

@Component(value = "cartModel")
public class CartModel {

    @Resource
    private CartReadDao          cartReadDao;
    @Resource
    private CartWriteDao         cartWriteDao;
    @Resource
    private SellerReadDao        sellerReadDao;
    @Resource
    private ProductReadDao       productReadDao;
    @Resource
    private ProductGoodsReadDao  productGoodsReadDao;
    @Resource
    private ProductGoodsWriteDao productGoodsWriteDao;
    @Resource
    private SellerTransportModel sellerTransportModel;
    @Resource
    private ActSingleReadDao     actSingleReadDao;
    @Resource
    private ActFullReadDao       actFullReadDao;

    /**
     * 添加商品到购物车
     * @param cart
     * @return
     */
    public boolean addToCart(Cart cart) {

        int result = 0;
        // 参数校验
        if (cart == null) {
            throw new BusinessException("购物车信息不能为空，请重试！");
        } else if (cart.getProductId() == null || cart.getProductId() == 0
                   || cart.getProductGoodsId() == null || cart.getProductGoodsId() == 0) {
            throw new BusinessException("请先选择商品后重试！");
        }

        if (cart.getCount() == null || cart.getCount() < 1) {
            cart.setCount(1);
        }

        ProductGoods productGoods = productGoodsWriteDao.get(cart.getProductGoodsId());
        if (productGoods == null) {
            throw new BusinessException("对不起，该商品型号已下架！");
        }

        // 判断购物车是否已有该商品，如果存在产品个数累加
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("q_productGoodsId", cart.getProductGoodsId());
        queryMap.put("q_memberId", cart.getMemberId());
        List<Cart> cartList = cartWriteDao.queryList(queryMap);
        if (cartList.size() > 1) {
            throw new BusinessException("购物车中同一产品有多条数据！");
        } else if (cartList.size() == 1) {
            Cart bean = cartList.get(0);
            int count = bean.getCount() + cart.getCount();
            // 判断库存
            if (count > productGoods.getProductStock()) {
                throw new BusinessException("对不起，库存不足！");
            }
            result = cartWriteDao.addCount(bean.getId(), cart.getCount());
        } else {
            // 判断库存
            if (cart.getCount() > productGoods.getProductStock()) {
                throw new BusinessException("对不起，库存不足！");
            }
            result = cartWriteDao.save(cart);
        }

        return result > 0;
    }

    /**
     * 根据登录用户取得购物车信息，所有数据都从写库获取
     * @param memberId
     * @param memberAddress 用户选择的地址信息，用于计算运费，如果是null表示不计算运费
     * @param source 来源，1、pc；2、H5；3、Android；4、IOS；
     * @param useType 使用类型：1、购物车用（取所有购物车记录），2、订单结算用（取用户勾选的记录）
     * @return
     */
    public CartInfoVO getCartInfoByMId(Integer memberId, MemberAddress memberAddress,
                                       Integer source, Integer useType) {

        // 默认使用类型是购物车使用
        if (useType == null) {
            useType = 1;
        }
        //取用户购物车
        List<Cart> cartList = cartWriteDao.getByMemberId(memberId, useType);

        // 商家Map，记录货品所属的商家
        Map<Integer, Seller> sellerMap = new HashMap<Integer, Seller>();
        // 商家满减活动Map，记录商家当前的满减活动
        Map<Integer, ActFull> actFullMap = new HashMap<Integer, ActFull>();
        // 商家货品list Map，记录商家下得所有cart对象
        Map<Integer, List<Cart>> sellerCartMap = new HashMap<Integer, List<Cart>>();
        // 商家所有货品金额小计，记录属于该商家的货品金额
        Map<Integer, BigDecimal> sellerAmountMap = new HashMap<Integer, BigDecimal>();
        // 店铺 所有 商品优惠金额小计（所有立减金额）
        Map<Integer, BigDecimal> sellerDiscountedMap = new HashMap<Integer, BigDecimal>();
        // 店铺 选中 商品优惠金额小计（满减+所有立减金额）
        Map<Integer, BigDecimal> sellerCheckedDiscountedMap = new HashMap<Integer, BigDecimal>();
        // 店铺 所有 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（所有立减金额）的小计
        Map<Integer, BigDecimal> sellerDiscountedAmountMap = new HashMap<Integer, BigDecimal>();
        // 店铺 选中 商品金额小计，商品价格*数量之和，没有减去优惠的小计
        Map<Integer, BigDecimal> sellerCheckedAmountMap = new HashMap<Integer, BigDecimal>();
        // 店铺 选中 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计
        Map<Integer, BigDecimal> sellerCheckedDiscountedAmountMap = new HashMap<Integer, BigDecimal>();

        // 商品Map，记录货品所属的商品（有多个货品属于同一个商品时可以减少数据库查询次数）
        Map<Integer, Product> productMap = new HashMap<Integer, Product>();
        // 商家商品数量Map，用于记录各个商家商品的数量，计算运费（只记录选中商品的数量）
        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        // 商家单品立减活动Map，用于记录各个商家商品的立减活动（主要是同一个商品的多个货品），key为商品ID
        Map<Integer, ActSingle> actSingleMap = new HashMap<Integer, ActSingle>();

        // 购物车总金额
        BigDecimal cartAmount = BigDecimal.ZERO;
        // 购物车总数量
        int totalNumber = 0;
        // 购物车选中总金额
        BigDecimal checkedCartAmount = BigDecimal.ZERO;
        // 购物车选中数量
        int totalCheckedNumber = 0;
        // 购物车选中商品优惠后合计
        BigDecimal checkedDiscountedCartAmount = BigDecimal.ZERO;
        // 所有商品优惠金额合计
        BigDecimal discountAmount = BigDecimal.ZERO;
        // 选中商品优惠合计
        BigDecimal checkedDiscountedAmount = BigDecimal.ZERO;

        // 根据来源判断渠道，默认渠道为PC
        int channel = ConstantsEJS.CHANNEL_2;
        if (source != null && (source.equals(ConstantsEJS.SOURCE_2_H5)
                               || source.equals(ConstantsEJS.SOURCE_3_ANDROID)
                               || source.equals(ConstantsEJS.SOURCE_4_IOS))) {
            channel = ConstantsEJS.CHANNEL_3;
        }

        for (Cart cart : cartList) {
            // 查询货品，每一条购物车记录都要查询，购物车中货品不重复
            ProductGoods productGoods = productGoodsReadDao.get(cart.getProductGoodsId());
            cart.setProductGoods(productGoods);

            // 本条购物车记录的金额小计
            // 根据来源确定使用PC价格或者移动端价格，默认使用PC端价格
            BigDecimal price = productGoods.getMallPcPrice();
            if (source != null && (source.equals(ConstantsEJS.SOURCE_2_H5)
                                   || source.equals(ConstantsEJS.SOURCE_3_ANDROID)
                                   || source.equals(ConstantsEJS.SOURCE_4_IOS))) {
                price = productGoods.getMallMobilePrice();
            }
            BigDecimal currAmount = price.multiply(new BigDecimal(cart.getCount()));
            // 四舍五入，保留两位小数点
            currAmount = currAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
            cart.setCurrAmount(currAmount);

            // 当前购物车商品优惠的金额和（立减金额*数量）
            BigDecimal currDiscounted = BigDecimal.ZERO;
            // 当前购物车商品优惠后的价格和（（单价-立减金额）*数量）
            BigDecimal currDiscountedAmount = BigDecimal.ZERO;

            String productIdStr = "," + cart.getProductId() + ",";
            // 商品单品立减活动信息
            ActSingle actSingle = null;
            if (actSingleMap.get(cart.getProductId()) == null) {
                actSingle = actSingleReadDao.getEffectiveActSingle(cart.getSellerId(), channel,
                    productIdStr);
                actSingleMap.put(cart.getProductId(), actSingle);
            } else {
                actSingle = actSingleMap.get(cart.getProductId());
            }
            if (actSingle != null) {
                // 当前商品参加了单品立减活动，计算优惠金额
                // 如果立减活动是直接减金额
                if (actSingle.getType().intValue() == ActSingle.TYPE_1) {
                    // 优惠金额
                    currDiscounted = actSingle.getDiscount()
                        .multiply(new BigDecimal(cart.getCount()));
                    currDiscounted = currDiscounted.setScale(2, BigDecimal.ROUND_HALF_UP);
                    // 优惠后价格
                    price = price.subtract(actSingle.getDiscount());
                    // 优惠后价格与0比较，如果小于0则价格设定成0
                    price = price.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : price;
                    currDiscountedAmount = price.multiply(new BigDecimal(cart.getCount()));
                    currDiscountedAmount = currDiscountedAmount.setScale(2,
                        BigDecimal.ROUND_HALF_UP);
                } else if (actSingle.getType().intValue() == ActSingle.TYPE_2) {
                    // 如果活动是折扣类型
                    // 优惠后价格
                    price = price.multiply(actSingle.getDiscount());
                    // 优惠后价格与0比较，如果小于0则价格设定成0
                    price = price.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : price;
                    currDiscountedAmount = price.multiply(new BigDecimal(cart.getCount()));
                    currDiscountedAmount = currDiscountedAmount.setScale(2,
                        BigDecimal.ROUND_HALF_UP);
                    currDiscounted = currAmount.subtract(currDiscountedAmount);
                }
                cart.setActSingle(actSingle);
            } else {
                // 当前商品没有参加单品立减活动，优惠金额设定
                // 优惠金额为0
                currDiscounted = BigDecimal.ZERO;
                // 优惠后价格和设定为原价和
                currDiscountedAmount = currAmount;
            }
            cart.setCurrDiscounted(currDiscounted);
            cart.setCurrDiscountedAmount(currDiscountedAmount);

            // 购物车总金额
            cartAmount = cartAmount.add(currAmount);
            // 购物车总数量
            totalNumber += cart.getCount();
            // 所有商品优惠金额合计
            discountAmount = discountAmount.add(currDiscounted);
            if (cart.getChecked().intValue() == Cart.CHECKED_1) {
                // 购物车选中总金额
                checkedCartAmount = checkedCartAmount.add(currAmount);
                // 购物车选中数量
                totalCheckedNumber += cart.getCount();
                // 购物车选中商品优惠后合计
                checkedDiscountedCartAmount = checkedDiscountedCartAmount.add(currDiscountedAmount);
                // 选中商品优惠合计
                checkedDiscountedAmount = checkedDiscountedAmount.add(currDiscounted);
            }

            // 商家是空，查询商家，如果不为空不查询
            if (sellerMap.get(cart.getSellerId()) == null) {
                // 查询商家
                Seller seller = sellerReadDao.get(cart.getSellerId());
                sellerMap.put(cart.getSellerId(), seller);

                // 查询商家当前的满减活动（只查询一次，记录以备用）
                ActFull actFull = actFullReadDao.getEffectiveActFull(cart.getSellerId(), channel);
                actFullMap.put(cart.getSellerId(), actFull);

                // 记录商家的购物车
                List<Cart> sellerCartList = new ArrayList<Cart>();
                sellerCartList.add(cart);
                sellerCartMap.put(cart.getSellerId(), sellerCartList);

                // 查询商品
                Product product = productReadDao.get(cart.getProductId());
                cart.setProduct(product);
                productMap.put(product.getId(), product);

                // 店铺 所有 商品小计，商品价格*数量之和，没有减去优惠的小计
                sellerAmountMap.put(cart.getSellerId(), currAmount);
                // 店铺 所有 商品优惠金额小计（所有立减金额）
                sellerDiscountedMap.put(cart.getSellerId(), currDiscounted);
                // 店铺 所有 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（所有立减金额）的小计
                sellerDiscountedAmountMap.put(cart.getSellerId(), currDiscountedAmount);
                if (cart.getChecked().intValue() == Cart.CHECKED_1) {
                    // 店铺 选中 商品优惠金额小计（满减+所有立减金额），此处只记录了立减金额，满减金额在最后统一计算
                    sellerCheckedDiscountedMap.put(cart.getSellerId(), currDiscounted);
                    // 店铺 选中 商品金额小计，商品价格*数量之和，没有减去优惠的小计
                    sellerCheckedAmountMap.put(cart.getSellerId(), currAmount);
                    // 店铺 选中 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计，此处只记录了立减金额，满减金额在最后统一计算
                    sellerCheckedDiscountedAmountMap.put(cart.getSellerId(), currDiscountedAmount);

                    // 记录商家商品的数量，只记录选中的商品数量，用于计算运费
                    countMap.put(cart.getSellerId(), cart.getCount());
                }

            } else {
                // 如果商家已经存在

                // 记录商家的购物车
                List<Cart> sellerCartList = sellerCartMap.get(cart.getSellerId());
                sellerCartList.add(cart);
                sellerCartMap.put(cart.getSellerId(), sellerCartList);

                // 查询商品
                if (productMap.get(cart.getProductId()) == null) {
                    Product product = productReadDao.get(cart.getProductId());
                    cart.setProduct(product);
                    productMap.put(product.getId(), product);
                } else {
                    cart.setProduct(productMap.get(cart.getProductId()));
                }

                // 店铺 所有 商品小计，商品价格*数量之和，没有减去优惠的小计
                sellerAmountMap.put(cart.getSellerId(),
                    sellerAmountMap.get(cart.getSellerId()).add(currAmount));
                // 店铺 所有 商品优惠金额小计（所有立减金额）
                sellerDiscountedMap.put(cart.getSellerId(),
                    sellerDiscountedMap.get(cart.getSellerId()).add(currDiscounted));
                // 店铺 所有 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（所有立减金额）的小
                sellerDiscountedAmountMap.put(cart.getSellerId(),
                    sellerDiscountedAmountMap.get(cart.getSellerId()).add(currDiscountedAmount));
                if (cart.getChecked().intValue() == Cart.CHECKED_1) {
                    // 店铺 选中 商品优惠金额小计（满减+所有立减金额），此处只记录了立减金额，满减金额在最后统一计算
                    if (sellerCheckedDiscountedMap.get(cart.getSellerId()) == null) {
                        sellerCheckedDiscountedMap.put(cart.getSellerId(), currDiscounted);
                    } else {
                        sellerCheckedDiscountedMap.put(cart.getSellerId(),
                            sellerCheckedDiscountedMap.get(cart.getSellerId()).add(currDiscounted));
                    }

                    // 店铺 选中 商品金额小计，商品价格*数量之和，没有减去优惠的小计
                    if (sellerCheckedAmountMap.get(cart.getSellerId()) == null) {
                        sellerCheckedAmountMap.put(cart.getSellerId(), currAmount);
                    } else {
                        sellerCheckedAmountMap.put(cart.getSellerId(),
                            sellerCheckedAmountMap.get(cart.getSellerId()).add(currAmount));
                    }

                    // 店铺 选中 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计，此处只记录了立减金额，满减金额在最后统一计算
                    if (sellerCheckedDiscountedAmountMap.get(cart.getSellerId()) == null) {
                        sellerCheckedDiscountedAmountMap.put(cart.getSellerId(),
                            currDiscountedAmount);
                    } else {
                        sellerCheckedDiscountedAmountMap.put(cart.getSellerId(),
                            sellerCheckedDiscountedAmountMap.get(cart.getSellerId())
                                .add(currDiscountedAmount));
                    }

                    // 记录商家商品的数量，只记录选中的商品数量，用于计算运费
                    if (countMap.get(cart.getSellerId()) == null) {
                        countMap.put(cart.getSellerId(), cart.getCount());
                    } else {
                        countMap.put(cart.getSellerId(),
                            countMap.get(cart.getSellerId()) + cart.getCount());
                    }
                }
            }
        }

        List<CartListVO> listVOs = new ArrayList<CartListVO>();

        BigDecimal logisticsFeeAmount = BigDecimal.ZERO;
        Iterator<Integer> iterator = sellerMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer sellerId = iterator.next();
            CartListVO listVO = new CartListVO();
            listVO.setSeller(sellerMap.get(sellerId));
            listVO.setCartList(sellerCartMap.get(sellerId));
            //            listVO.setSellerAmount(sellerAmountMap.get(sellerId));
            if (memberAddress != null) {
                // 地址不为空则计算运费
                BigDecimal fee = sellerTransportModel.calculateTransFee(sellerId,
                    countMap.get(sellerId), memberAddress.getCityId());
                listVO.setSellerLogisticsFee(fee);
                logisticsFeeAmount = logisticsFeeAmount.add(fee);
            }

            // 店铺 所有 商品小计，商品价格*数量之和，没有减去优惠的小计
            listVO.setSellerAmount(sellerAmountMap.get(sellerId));
            // 店铺 所有 商品优惠金额小计（所有立减金额）
            listVO.setSellerDiscounted(sellerDiscountedMap.get(sellerId));
            // 店铺 所有 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（所有立减金额）的小计
            listVO.setSellerDiscountedAmount(sellerDiscountedAmountMap.get(sellerId));
            // 店铺 选中 商品金额小计，商品价格*数量之和，没有减去优惠的小计
            listVO.setSellerCheckedAmount(sellerCheckedAmountMap.get(sellerId));
            // 店铺 选中 商品优惠金额小计（满减+所有立减金额），如果满减活动存在，则计算满减
            BigDecimal sellerCheckedDiscounted = sellerCheckedDiscountedMap.get(sellerId);
            // 如果没有选中的商品则该值可能是空
            sellerCheckedDiscounted = sellerCheckedDiscounted == null ? BigDecimal.ZERO
                : sellerCheckedDiscounted;
            // 店铺 选中 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计，如果满减活动存在，则计算满减
            // 此时sellerCheckedDiscountedAmount是原价减去单品立减金额后的价格和
            // 满减优先级小于立减优先级
            BigDecimal sellerCheckedDiscountedAmount = sellerCheckedDiscountedAmountMap
                .get(sellerId);
            // 如果没有选中的商品则该值可能是空
            sellerCheckedDiscountedAmount = sellerCheckedDiscountedAmount == null ? BigDecimal.ZERO
                : sellerCheckedDiscountedAmount;
            // 当前商家的满减活动
            if (actFullMap.get(sellerId) != null) {
                ActFull actFull = actFullMap.get(sellerId);
                listVO.setActFull(actFull);

                // 订单满减的满额，从actfull的3个档次中取满额（根据选中商品金额计算得出）
                BigDecimal orderFull = null;
                // 订单满减的减免额，从actfull的3个档次中取减免额（根据选中商品金额计算得出）
                BigDecimal orderDiscount = null;

                // 根据店铺选中商品优惠后小计与活动满减等级的比较，得出订单满足的活动等级
                if (actFull.getThirdFull() != null
                    && actFull.getThirdFull().compareTo(BigDecimal.ZERO) > 0
                    && sellerCheckedDiscountedAmount.compareTo(actFull.getThirdFull()) >= 0) {
                    orderFull = actFull.getThirdFull();
                    orderDiscount = actFull.getThirdDiscount();
                } else if (actFull.getSecondFull() != null
                           && actFull.getSecondFull().compareTo(BigDecimal.ZERO) > 0
                           && sellerCheckedDiscountedAmount
                               .compareTo(actFull.getSecondFull()) >= 0) {
                    orderFull = actFull.getSecondFull();
                    orderDiscount = actFull.getSecondDiscount();
                } else if (actFull.getFirstFull() != null
                           && actFull.getFirstFull().compareTo(BigDecimal.ZERO) > 0
                           && sellerCheckedDiscountedAmount
                               .compareTo(actFull.getFirstFull()) >= 0) {
                    orderFull = actFull.getFirstFull();
                    orderDiscount = actFull.getFirstDiscount();
                }

                if (orderFull != null) {
                    // 优惠金额加上满减的优惠金额
                    sellerCheckedDiscounted = sellerCheckedDiscounted.add(orderDiscount);
                    // 优惠后订单合计减去优惠金额
                    sellerCheckedDiscountedAmount = sellerCheckedDiscountedAmount
                        .subtract(orderDiscount);

                    listVO.setOrderFull(orderFull);
                    listVO.setOrderDiscount(orderDiscount);

                    // 计算全部订单相关满减金额
                    // 购物车选中商品优惠后合计
                    checkedDiscountedCartAmount = checkedDiscountedCartAmount
                        .subtract(orderDiscount);
                    // 选中商品优惠合计
                    checkedDiscountedAmount = checkedDiscountedAmount.add(orderDiscount);
                }
            }
            listVO.setSellerCheckedDiscounted(sellerCheckedDiscounted);
            listVO.setSellerCheckedDiscountedAmount(sellerCheckedDiscountedAmount);

            listVOs.add(listVO);
        }

        CartInfoVO cartInfoVO = new CartInfoVO();
        cartInfoVO.setCartListVOs(listVOs);
        // 购物车所有商品合计，所有商品价格*数量之和，没有减去优惠的合计价格
        cartInfoVO.setCartAmount(cartAmount);
        cartInfoVO.setTotalNumber(totalNumber);
        cartInfoVO.setTotalCheckedNumber(totalCheckedNumber);
        cartInfoVO.setLogisticsFeeAmount(logisticsFeeAmount);
        // 所有商品优惠金额合计，减去优惠（所有立减金额）的合计
        cartInfoVO.setDiscountAmount(discountAmount);
        // 选中商品优惠合计，减去优惠（满减+所有立减金额）的合计
        cartInfoVO.setCheckedDiscountedAmount(checkedDiscountedAmount);
        // 选中商品合计，选中商品价格*数量之和，没有减去优惠的合计价格
        cartInfoVO.setCheckedCartAmount(checkedCartAmount);
        // 选中商品优惠后合计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计
        cartInfoVO.setCheckedDiscountedCartAmount(checkedDiscountedCartAmount);

        return cartInfoVO;
    }

    /**
     * 根据购物车id更新商城购物车，只更新商品数量
     * @param cart
     * @param request
     * @return
     */
    public boolean updateCartNumber(Integer id, Integer number) {
        //参数校验
        if (id == null || id == 0) {
            throw new BusinessException("购物车ID不能为空，请重试！");
        } else if (number == null || number < 1) {
            throw new BusinessException("购物车商品数量不能为空且不能小于0，请重试！");
        }
        Cart cart = new Cart();
        cart.setId(id);
        cart.setCount(number);
        cart.setChecked(1);
        cartWriteDao.update(cart);
        return true;
    }

    /**
     * 删除购物车商品
     * @param cart
     * @param request
     * @return
     */
    public boolean deleteCartByIds(List<Integer> ids) {

        //  参数校验
        if (ids == null || ids.size() == 0) {
            return true;
        }
        //批量删除购物车数据
        cartWriteDao.deleteByIds(ids);
        return true;
    }

    /**
    * 根据id取得商城购物车
    * @param  cartId
    * @return
    */
    public Cart getCartById(Integer cartId) {
        return cartWriteDao.get(cartId);
    }

    /**
     * 定时任务清除7天之前添加的购物车数据
     * @return
     */
    public boolean jobClearCart() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        String clearTime = TimeUtil.getDateTimeString(calendar.getTime());
        cartWriteDao.jobClearCart(clearTime);
        return true;
    }

    /**
     * 根据用户ID获取用户购物车数量
     * @param memberId
     * @return
     */
    public Integer getCartNumberByMId(Integer memberId) {
        return cartReadDao.getCartNumberByMId(memberId);
    }

    /**
     * 根据用户ID、购物车ID修改购物车的选中状态，cartId为0时表示全部选中或不选中
     * @param memberId
     * @param cartId
     * @param checked
     * @return
     */
    public boolean updateChecked(Integer memberId, Integer cartId, Integer checked) {
        if (cartId != null && cartId > 0) {
            return cartWriteDao.updateChecked(memberId, cartId, checked) > 0;
        } else {
            return cartWriteDao.updateCheckedAll(memberId, checked) > 0;
        }
    }
}
