package com.ejavashop.entity.promotion.group;

import java.io.Serializable;

/**
 * 团购
 * <p>Table: <strong>act_group</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.Integer}</td><td>type</td><td>int</td><td>团购分类</td></tr>
 *   <tr><td>typeState</td><td>{@link java.lang.Integer}</td><td>type_state</td><td>tinyint</td><td>分类状态：0、不显示；1、显示</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>text</td><td>活动名称</td></tr>
 *   <tr><td>descinfo</td><td>{@link java.lang.String}</td><td>descinfo</td><td>text</td><td>活动描述</td></tr>
 *   <tr><td>virtualSaleNum</td><td>{@link java.lang.Integer}</td><td>virtual_sale_num</td><td>int</td><td>虚拟销量</td></tr>
 *   <tr><td>saleNum</td><td>{@link java.lang.Integer}</td><td>sale_num</td><td>int</td><td>实际销量</td></tr>
 *   <tr><td>startTime</td><td>{@link java.util.Date}</td><td>start_time</td><td>datetime</td><td>活动开始时间</td></tr>
 *   <tr><td>endTime</td><td>{@link java.util.Date}</td><td>end_time</td><td>datetime</td><td>活动结束时间</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>商品ID</td></tr>
 *   <tr><td>marketPrice</td><td>{@link java.math.BigDecimal}</td><td>market_price</td><td>decimal</td><td>原价或市场价</td></tr>
 *   <tr><td>price</td><td>{@link java.math.BigDecimal}</td><td>price</td><td>decimal</td><td>团购价格</td></tr>
 *   <tr><td>purchase</td><td>{@link java.lang.Integer}</td><td>purchase</td><td>int</td><td>限购数量</td></tr>
 *   <tr><td>stock</td><td>{@link java.lang.Integer}</td><td>stock</td><td>int</td><td>库存</td></tr>
 *   <tr><td>sortNum</td><td>{@link java.lang.Integer}</td><td>sort_num</td><td>tinyint</td><td>排序</td></tr>
 *   <tr><td>isBest</td><td>{@link java.lang.Integer}</td><td>is_best</td><td>tinyint</td><td>是否推荐</td></tr>
 *   <tr><td>activityState</td><td>{@link java.lang.Integer}</td><td>activity_state</td><td>tinyint</td><td>活动状态1、未发布；2、发布；3、结束</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>审核状态1、未审核；2、提交审核；3、审核通过；4、审核失败</td></tr>
 *   <tr><td>auditId</td><td>{@link java.lang.Integer}</td><td>audit_id</td><td>int</td><td>审核人ID</td></tr>
 *   <tr><td>auditName</td><td>{@link java.lang.String}</td><td>audit_name</td><td>varchar</td><td>审核人Name</td></tr>
 *   <tr><td>reason</td><td>{@link java.lang.String}</td><td>reason</td><td>varchar</td><td>审核失败原因</td></tr>
 *   <tr><td>image</td><td>{@link java.lang.String}</td><td>image</td><td>varchar</td><td>活动列表图片</td></tr>
 *   <tr><td>channel</td><td>{@link java.lang.Integer}</td><td>channel</td><td>tinyint</td><td>活动应用渠道1、通用；2、PC；3、Mobile</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>更新时间</td></tr>
 *   <tr><td>auditOpinion</td><td>{@link java.lang.String}</td><td>audit_opinion</td><td>varchar</td><td>审核意见</td></tr>
 * </table>
 *
 */
public class ActGroup implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = -876249993957257077L;
    private java.lang.Integer    id;
    private java.lang.Integer    type;
    private java.lang.Integer    typeState;
    private java.lang.String     name;
    private java.lang.String     descinfo;
    private java.lang.Integer    virtualSaleNum;
    private java.lang.Integer    saleNum;
    private java.util.Date       startTime;
    private java.util.Date       endTime;
    private java.lang.Integer    productId;
    private java.math.BigDecimal marketPrice;
    private java.math.BigDecimal price;
    private java.lang.Integer    purchase;
    private java.lang.Integer    stock;
    private java.lang.Integer    sortNum;
    private java.lang.Integer    isBest;
    private java.lang.Integer    activityState;
    private java.lang.Integer    state;
    private java.lang.Integer    auditId;
    private java.lang.String     auditName;
    private java.lang.String     reason;
    private java.lang.String     image;
    private java.lang.Integer    channel;
    private java.lang.Integer    sellerId;
    private java.util.Date       createTime;
    private java.util.Date       updateTime;
    private java.lang.String     auditOpinion;

    /**
     * 团购分类状态：不显示 0
     */
    public final static int      TYPE_STATE_0     = 0;

    /**
     * 团购分类状态：显示 1
     */
    public final static int      TYPE_STATE_1     = 1;

    /**
     * 1、未发布
     */
    public static final int      ACTIVITY_STATE_1 = 1;

    /**
     * 2、发布
     */
    public static final int      ACTIVITY_STATE_2 = 2;

    /**
     * 3、结束
     */
    public static final int      ACTIVITY_STATE_3 = 3;

    /**
     * 1、未审核
     */
    public static final int      STATE_1          = 1;

    /**
     * 2、提交审核
     */
    public static final int      STATE_2          = 2;

    /**
     * 3、审核通过
     */
    public static final int      STATE_3          = 3;

    /**
     * 4、审核失败
     */
    public static final int      STATE_4          = 4;

    /**
     * 未推荐 0
     */
    public static final int      IS_BEST_0        = 0;

    /**
     * 推荐 1
     */
    public static final int      IS_BEST_1        = 1;

    private String               productName;                            //团购名称
    private String               typeName;                               //团购分类
    private String               sellerName;                             //所属商家

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
     * 获取团购分类
     */
    public java.lang.Integer getType() {
        return this.type;
    }

    /**
     * 设置团购分类
     */
    public void setType(java.lang.Integer type) {
        this.type = type;
    }

    /**
     * 获取分类状态：0、不显示；1、显示
     */
    public java.lang.Integer getTypeState() {
        return this.typeState;
    }

    /**
     * 设置分类状态：0、不显示；1、显示
     */
    public void setTypeState(java.lang.Integer typeState) {
        this.typeState = typeState;
    }

    /**
     * 获取活动名称
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 设置活动名称
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * 获取活动描述
     */
    public java.lang.String getDescinfo() {
        return this.descinfo;
    }

    /**
     * 设置活动描述
     */
    public void setDescinfo(java.lang.String descinfo) {
        this.descinfo = descinfo;
    }

    /**
     * 获取虚拟销量
     */
    public java.lang.Integer getVirtualSaleNum() {
        return this.virtualSaleNum;
    }

    /**
     * 设置虚拟销量
     */
    public void setVirtualSaleNum(java.lang.Integer virtualSaleNum) {
        this.virtualSaleNum = virtualSaleNum;
    }

    /**
     * 获取实际销量
     */
    public java.lang.Integer getSaleNum() {
        return this.saleNum;
    }

    /**
     * 设置实际销量
     */
    public void setSaleNum(java.lang.Integer saleNum) {
        this.saleNum = saleNum;
    }

    /**
     * 获取活动开始时间
     */
    public java.util.Date getStartTime() {
        return this.startTime;
    }

    /**
     * 设置活动开始时间
     */
    public void setStartTime(java.util.Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取活动结束时间
     */
    public java.util.Date getEndTime() {
        return this.endTime;
    }

    /**
     * 设置活动结束时间
     */
    public void setEndTime(java.util.Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取商品ID
     */
    public java.lang.Integer getProductId() {
        return this.productId;
    }

    /**
     * 设置商品ID
     */
    public void setProductId(java.lang.Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取原价或市场价
     */
    public java.math.BigDecimal getMarketPrice() {
        return this.marketPrice;
    }

    /**
     * 设置原价或市场价
     */
    public void setMarketPrice(java.math.BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取团购价格
     */
    public java.math.BigDecimal getPrice() {
        return this.price;
    }

    /**
     * 设置团购价格
     */
    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取库存
     */
    public java.lang.Integer getStock() {
        return this.stock;
    }

    /**
     * 设置库存
     */
    public void setStock(java.lang.Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取排序
     */
    public java.lang.Integer getSortNum() {
        return this.sortNum;
    }

    /**
     * 设置排序
     */
    public void setSortNum(java.lang.Integer sortNum) {
        this.sortNum = sortNum;
    }

    /**
     * 获取是否推荐
     */
    public java.lang.Integer getIsBest() {
        return this.isBest;
    }

    /**
     * 设置是否推荐
     */
    public void setIsBest(java.lang.Integer isBest) {
        this.isBest = isBest;
    }

    /**
     * 获取活动状态1、未发布；2、发布；3、结束
     */
    public java.lang.Integer getActivityState() {
        return this.activityState;
    }

    /**
     * 设置活动状态1、未发布；2、发布；3、结束
     */
    public void setActivityState(java.lang.Integer activityState) {
        this.activityState = activityState;
    }

    /**
     * 获取审核状态1、未审核；2、提交审核；3、审核通过；4、审核失败
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置审核状态1、未审核；2、提交审核；3、审核通过；4、审核失败
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取审核人ID
     */
    public java.lang.Integer getAuditId() {
        return this.auditId;
    }

    /**
     * 设置审核人ID
     */
    public void setAuditId(java.lang.Integer auditId) {
        this.auditId = auditId;
    }

    /**
     * 获取审核人Name
     */
    public java.lang.String getAuditName() {
        return this.auditName;
    }

    /**
     * 设置审核人Name
     */
    public void setAuditName(java.lang.String auditName) {
        this.auditName = auditName;
    }

    /**
     * 获取审核失败原因
     */
    public java.lang.String getReason() {
        return this.reason;
    }

    /**
     * 设置审核失败原因
     */
    public void setReason(java.lang.String reason) {
        this.reason = reason;
    }

    /**
     * 获取活动列表图片
     */
    public java.lang.String getImage() {
        return this.image;
    }

    /**
     * 设置活动列表图片
     */
    public void setImage(java.lang.String image) {
        this.image = image;
    }

    /**
     * 获取活动应用渠道1、通用；2、PC；3、Mobile
     */
    public java.lang.Integer getChannel() {
        return this.channel;
    }

    /**
     * 设置活动应用渠道1、通用；2、PC；3、Mobile
     */
    public void setChannel(java.lang.Integer channel) {
        this.channel = channel;
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
     * 获取创建时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置更新时间
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取审核意见
     */
    public java.lang.String getAuditOpinion() {
        return this.auditOpinion;
    }

    /**
     * 设置审核意见
     */
    public void setAuditOpinion(java.lang.String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public java.lang.Integer getPurchase() {
        return purchase;
    }

    public void setPurchase(java.lang.Integer purchase) {
        this.purchase = purchase;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

}