package com.ejavashop.entity.settlement;

import java.io.Serializable;

/**
 * 结算网单表
 * <p>Table: <strong>settlement_op</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>ordersId</td><td>{@link java.lang.Integer}</td><td>orders_id</td><td>int</td><td>订单ID</td></tr>
 *   <tr><td>ordersSn</td><td>{@link java.lang.String}</td><td>orders_sn</td><td>varchar</td><td>订单号</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>商品id</td></tr>
 *   <tr><td>productGoodsId</td><td>{@link java.lang.Integer}</td><td>product_goods_id</td><td>int</td><td>货品ID</td></tr>
 *   <tr><td>specInfo</td><td>{@link java.lang.String}</td><td>spec_info</td><td>varchar</td><td>规格详情</td></tr>
 *   <tr><td>productName</td><td>{@link java.lang.String}</td><td>product_name</td><td>varchar</td><td>商品名称</td></tr>
 *   <tr><td>productSku</td><td>{@link java.lang.String}</td><td>product_sku</td><td>varchar</td><td>抽象商品sku</td></tr>
 *   <tr><td>packageGroupsId</td><td>{@link java.lang.Integer}</td><td>package_groups_id</td><td>int</td><td>促销套装0、不是促销套装；大于0，促销套装ID，价格是促销套装的ID</td></tr>
 *   <tr><td>mallGroupsId</td><td>{@link java.lang.Integer}</td><td>mall_groups_id</td><td>int</td><td>商城套装0，不是上次套装；大于0商城套装ID</td></tr>
 *   <tr><td>giftId</td><td>{@link java.lang.Integer}</td><td>gift_id</td><td>int</td><td>赠品ID 0:不是赠品；大于0：是赠品，存赠品的ID</td></tr>
 *   <tr><td>isGift</td><td>{@link java.lang.Integer}</td><td>is_gift</td><td>tinyint</td><td>是否是赠品，0、不是；1、是；</td></tr>
 *   <tr><td>moneyPrice</td><td>{@link java.math.BigDecimal}</td><td>money_price</td><td>decimal</td><td>商品单价</td></tr>
 *   <tr><td>number</td><td>{@link java.lang.Integer}</td><td>number</td><td>int</td><td>商品数量</td></tr>
 *   <tr><td>moneyAmount</td><td>{@link java.math.BigDecimal}</td><td>money_amount</td><td>decimal</td><td>网单金额（减去立减优惠后的金额和）</td></tr>
 *   <tr><td>moneyActSingle</td><td>{@link java.math.BigDecimal}</td><td>money_act_single</td><td>decimal</td><td>立减优惠金额和</td></tr>
 *   <tr><td>actSingleId</td><td>{@link java.lang.Integer}</td><td>act_single_id</td><td>int</td><td>单品立减活动ID，无设置0</td></tr>
 *   <tr><td>activityId</td><td>{@link java.lang.Integer}</td><td>activity_id</td><td>int</td><td>团购ID，为0正常购买</td></tr>
 *   <tr><td>flashsalesId</td><td>{@link java.lang.Integer}</td><td>flashsales_id</td><td>int</td><td>限时抢购ID，为0正常购买</td></tr>
 *   <tr><td>logisticsId</td><td>{@link java.lang.Integer}</td><td>logistics_id</td><td>int</td><td>物流</td></tr>
 *   <tr><td>logisticsName</td><td>{@link java.lang.String}</td><td>logistics_name</td><td>varchar</td><td>物流name</td></tr>
 *   <tr><td>logisticsNumber</td><td>{@link java.lang.String}</td><td>logistics_number</td><td>varchar</td><td>发票快递单号</td></tr>
 *   <tr><td>shippingTime</td><td>{@link java.util.Date}</td><td>shipping_time</td><td>datetime</td><td>发货时间</td></tr>
 *   <tr><td>closeTime</td><td>{@link java.util.Date}</td><td>close_time</td><td>datetime</td><td>网单完成关闭或取消关闭时间</td></tr>
 *   <tr><td>systemRemark</td><td>{@link java.lang.String}</td><td>system_remark</td><td>text</td><td>系统备注，不给用户显示</td></tr>
 *   <tr><td>memberProductBackId</td><td>{@link java.lang.Integer}</td><td>member_product_back_id</td><td>int</td><td>退货ID，默认为0</td></tr>
 *   <tr><td>memberProductExchangeId</td><td>{@link java.lang.Integer}</td><td>member_product_exchange_id</td><td>int</td><td>换货ID，默认为0</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 *   <tr><td>settlementId</td><td>{@link java.lang.Integer}</td><td>settlement_id</td><td>int</td><td>结算ID</td></tr>
 *   <tr><td>settleCycle</td><td>{@link java.lang.String}</td><td>settle_cycle</td><td>varchar</td><td>结算周期</td></tr>
 *   <tr><td>productCateId</td><td>{@link java.lang.Integer}</td><td>product_cate_id</td><td>int</td><td>商品分类ID</td></tr>
 *   <tr><td>productCateName</td><td>{@link java.lang.String}</td><td>product_cate_name</td><td>varchar</td><td>商品分类名称</td></tr>
 *   <tr><td>scaling</td><td>{@link java.math.BigDecimal}</td><td>scaling</td><td>decimal</td><td>商品分类分佣比例</td></tr>
 *   <tr><td>commision</td><td>{@link java.math.BigDecimal}</td><td>commision</td><td>decimal</td><td>网单佣金</td></tr>
 *   <tr><td>settleTime</td><td>{@link java.util.Date}</td><td>settle_time</td><td>datetime</td><td>结算日期</td></tr>
 * </table>
 *
 */
public class SettlementOp implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = -6855627572403316410L;

    private java.lang.Integer    id;
    private java.lang.Integer    ordersId;
    private java.lang.String     ordersSn;
    private java.lang.Integer    sellerId;
    private java.lang.Integer    productId;
    private java.lang.Integer    productGoodsId;
    private java.lang.String     specInfo;
    private java.lang.String     productName;
    private java.lang.String     productSku;
    private java.lang.Integer    packageGroupsId;
    private java.lang.Integer    mallGroupsId;
    private java.lang.Integer    giftId;
    private java.lang.Integer    isGift;
    private java.math.BigDecimal moneyPrice;
    private java.lang.Integer    number;
    private java.math.BigDecimal moneyAmount;
    private java.math.BigDecimal moneyActSingle;
    private java.lang.Integer    actSingleId;
    private java.lang.Integer    activityId;
    private java.lang.Integer    actFlashSaleId;
    private java.lang.Integer    actFlashSaleProductId;
    private java.lang.Integer    logisticsId;
    private java.lang.String     logisticsName;
    private java.lang.String     logisticsNumber;
    private java.util.Date       shippingTime;
    private java.util.Date       closeTime;
    private java.lang.String     systemRemark;
    private java.lang.Integer    memberProductBackId;
    private java.lang.Integer    memberProductExchangeId;
    private java.util.Date       createTime;
    private java.util.Date       updateTime;
    private java.lang.Integer    settlementId;
    private java.lang.String     settleCycle;
    private java.lang.Integer    productCateId;
    private java.lang.String     productCateName;
    private java.math.BigDecimal scaling;
    private java.math.BigDecimal commision;
    private java.util.Date       settleTime;

    /**
     * 获取id
     */
    public java.lang.Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    /**
     * 获取订单ID
     */
    public java.lang.Integer getOrdersId() {
        return this.ordersId;
    }

    /**
     * 设置订单ID
     */
    public void setOrdersId(java.lang.Integer ordersId) {
        this.ordersId = ordersId;
    }

    /**
     * 获取订单号
     */
    public java.lang.String getOrdersSn() {
        return this.ordersSn;
    }

    /**
     * 设置订单号
     */
    public void setOrdersSn(java.lang.String ordersSn) {
        this.ordersSn = ordersSn;
    }

    /**
     * 获取商家ID
     */
    public java.lang.Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置商家ID
     */
    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取商品id
     */
    public java.lang.Integer getProductId() {
        return this.productId;
    }

    /**
     * 设置商品id
     */
    public void setProductId(java.lang.Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取货品ID
     */
    public java.lang.Integer getProductGoodsId() {
        return this.productGoodsId;
    }

    /**
     * 设置货品ID
     */
    public void setProductGoodsId(java.lang.Integer productGoodsId) {
        this.productGoodsId = productGoodsId;
    }

    /**
     * 获取规格详情
     */
    public java.lang.String getSpecInfo() {
        return this.specInfo;
    }

    /**
     * 设置规格详情
     */
    public void setSpecInfo(java.lang.String specInfo) {
        this.specInfo = specInfo;
    }

    /**
     * 获取商品名称
     */
    public java.lang.String getProductName() {
        return this.productName;
    }

    /**
     * 设置商品名称
     */
    public void setProductName(java.lang.String productName) {
        this.productName = productName;
    }

    /**
     * 获取抽象商品sku
     */
    public java.lang.String getProductSku() {
        return this.productSku;
    }

    /**
     * 设置抽象商品sku
     */
    public void setProductSku(java.lang.String productSku) {
        this.productSku = productSku;
    }

    /**
     * 获取促销套装0、不是促销套装；大于0，促销套装ID，价格是促销套装的ID
     */
    public java.lang.Integer getPackageGroupsId() {
        return this.packageGroupsId;
    }

    /**
     * 设置促销套装0、不是促销套装；大于0，促销套装ID，价格是促销套装的ID
     */
    public void setPackageGroupsId(java.lang.Integer packageGroupsId) {
        this.packageGroupsId = packageGroupsId;
    }

    /**
     * 获取商城套装0，不是上次套装；大于0商城套装ID
     */
    public java.lang.Integer getMallGroupsId() {
        return this.mallGroupsId;
    }

    /**
     * 设置商城套装0，不是上次套装；大于0商城套装ID
     */
    public void setMallGroupsId(java.lang.Integer mallGroupsId) {
        this.mallGroupsId = mallGroupsId;
    }

    /**
     * 获取赠品ID 0:不是赠品；大于0：是赠品，存赠品的ID
     */
    public java.lang.Integer getGiftId() {
        return this.giftId;
    }

    /**
     * 设置赠品ID 0:不是赠品；大于0：是赠品，存赠品的ID
     */
    public void setGiftId(java.lang.Integer giftId) {
        this.giftId = giftId;
    }

    /**
     * 获取是否是赠品，0、不是；1、是；
     */
    public java.lang.Integer getIsGift() {
        return this.isGift;
    }

    /**
     * 设置是否是赠品，0、不是；1、是；
     */
    public void setIsGift(java.lang.Integer isGift) {
        this.isGift = isGift;
    }

    /**
     * 获取商品单价
     */
    public java.math.BigDecimal getMoneyPrice() {
        return this.moneyPrice;
    }

    /**
     * 设置商品单价
     */
    public void setMoneyPrice(java.math.BigDecimal moneyPrice) {
        this.moneyPrice = moneyPrice;
    }

    /**
     * 获取商品数量
     */
    public java.lang.Integer getNumber() {
        return this.number;
    }

    /**
     * 设置商品数量
     */
    public void setNumber(java.lang.Integer number) {
        this.number = number;
    }

    /**
     * 获取网单金额（减去立减优惠后的金额和）
     */
    public java.math.BigDecimal getMoneyAmount() {
        return this.moneyAmount;
    }

    /**
     * 设置网单金额（减去立减优惠后的金额和）
     */
    public void setMoneyAmount(java.math.BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    /**
     * 获取立减优惠金额和
     */
    public java.math.BigDecimal getMoneyActSingle() {
        return this.moneyActSingle;
    }

    /**
     * 设置立减优惠金额和
     */
    public void setMoneyActSingle(java.math.BigDecimal moneyActSingle) {
        this.moneyActSingle = moneyActSingle;
    }

    /**
     * 获取单品立减活动ID，无设置0
     */
    public java.lang.Integer getActSingleId() {
        return this.actSingleId;
    }

    /**
     * 设置单品立减活动ID，无设置0
     */
    public void setActSingleId(java.lang.Integer actSingleId) {
        this.actSingleId = actSingleId;
    }

    /**
     * 获取团购ID，为0正常购买
     */
    public java.lang.Integer getActivityId() {
        return this.activityId;
    }

    /**
     * 设置团购ID，为0正常购买
     */
    public void setActivityId(java.lang.Integer activityId) {
        this.activityId = activityId;
    }

    public java.lang.Integer getActFlashSaleId() {
        return actFlashSaleId;
    }

    public void setActFlashSaleId(java.lang.Integer actFlashSaleId) {
        this.actFlashSaleId = actFlashSaleId;
    }

    public java.lang.Integer getActFlashSaleProductId() {
        return actFlashSaleProductId;
    }

    public void setActFlashSaleProductId(java.lang.Integer actFlashSaleProductId) {
        this.actFlashSaleProductId = actFlashSaleProductId;
    }

    /**
     * 获取物流
     */
    public java.lang.Integer getLogisticsId() {
        return this.logisticsId;
    }

    /**
     * 设置物流
     */
    public void setLogisticsId(java.lang.Integer logisticsId) {
        this.logisticsId = logisticsId;
    }

    /**
     * 获取物流name
     */
    public java.lang.String getLogisticsName() {
        return this.logisticsName;
    }

    /**
     * 设置物流name
     */
    public void setLogisticsName(java.lang.String logisticsName) {
        this.logisticsName = logisticsName;
    }

    /**
     * 获取发票快递单号
     */
    public java.lang.String getLogisticsNumber() {
        return this.logisticsNumber;
    }

    /**
     * 设置发票快递单号
     */
    public void setLogisticsNumber(java.lang.String logisticsNumber) {
        this.logisticsNumber = logisticsNumber;
    }

    /**
     * 获取发货时间
     */
    public java.util.Date getShippingTime() {
        return this.shippingTime;
    }

    /**
     * 设置发货时间
     */
    public void setShippingTime(java.util.Date shippingTime) {
        this.shippingTime = shippingTime;
    }

    /**
     * 获取网单完成关闭或取消关闭时间
     */
    public java.util.Date getCloseTime() {
        return this.closeTime;
    }

    /**
     * 设置网单完成关闭或取消关闭时间
     */
    public void setCloseTime(java.util.Date closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * 获取系统备注，不给用户显示
     */
    public java.lang.String getSystemRemark() {
        return this.systemRemark;
    }

    /**
     * 设置系统备注，不给用户显示
     */
    public void setSystemRemark(java.lang.String systemRemark) {
        this.systemRemark = systemRemark;
    }

    /**
     * 获取退货ID，默认为0
     */
    public java.lang.Integer getMemberProductBackId() {
        return this.memberProductBackId;
    }

    /**
     * 设置退货ID，默认为0
     */
    public void setMemberProductBackId(java.lang.Integer memberProductBackId) {
        this.memberProductBackId = memberProductBackId;
    }

    /**
     * 获取换货ID，默认为0
     */
    public java.lang.Integer getMemberProductExchangeId() {
        return this.memberProductExchangeId;
    }

    /**
     * 设置换货ID，默认为0
     */
    public void setMemberProductExchangeId(java.lang.Integer memberProductExchangeId) {
        this.memberProductExchangeId = memberProductExchangeId;
    }

    /**
     * 获取createTime
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置createTime
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取updateTime
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置updateTime
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取结算ID
     */
    public java.lang.Integer getSettlementId() {
        return this.settlementId;
    }

    /**
     * 设置结算ID
     */
    public void setSettlementId(java.lang.Integer settlementId) {
        this.settlementId = settlementId;
    }

    /**
     * 获取结算周期
     */
    public java.lang.String getSettleCycle() {
        return this.settleCycle;
    }

    /**
     * 设置结算周期
     */
    public void setSettleCycle(java.lang.String settleCycle) {
        this.settleCycle = settleCycle;
    }

    /**
     * 获取商品分类ID
     */
    public java.lang.Integer getProductCateId() {
        return this.productCateId;
    }

    /**
     * 设置商品分类ID
     */
    public void setProductCateId(java.lang.Integer productCateId) {
        this.productCateId = productCateId;
    }

    /**
     * 获取商品分类名称
     */
    public java.lang.String getProductCateName() {
        return this.productCateName;
    }

    /**
     * 设置商品分类名称
     */
    public void setProductCateName(java.lang.String productCateName) {
        this.productCateName = productCateName;
    }

    /**
     * 获取商品分类分佣比例
     */
    public java.math.BigDecimal getScaling() {
        return this.scaling;
    }

    /**
     * 设置商品分类分佣比例
     */
    public void setScaling(java.math.BigDecimal scaling) {
        this.scaling = scaling;
    }

    /**
     * 获取网单佣金
     */
    public java.math.BigDecimal getCommision() {
        return this.commision;
    }

    /**
     * 设置网单佣金
     */
    public void setCommision(java.math.BigDecimal commision) {
        this.commision = commision;
    }

    /**
     * 获取结算日期
     */
    public java.util.Date getSettleTime() {
        return this.settleTime;
    }

    /**
     * 设置结算日期
     */
    public void setSettleTime(java.util.Date settleTime) {
        this.settleTime = settleTime;
    }
}