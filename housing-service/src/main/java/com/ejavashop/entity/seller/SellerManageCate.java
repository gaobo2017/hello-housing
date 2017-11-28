package com.ejavashop.entity.seller;

import java.io.Serializable;

/**
* 商家可以经营商品分类表
* <p>Table: <strong>seller_manage_cate</strong>
    * <p>
<table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
    *
    <tr style="background-color:#ddd;Text-align:Left;">
        *
        <th nowrap>属性名</th>
        <th nowrap>属性类型</th>
        <th nowrap>字段名</th>
        <th nowrap>字段类型</th>
        <th nowrap>说明</th>
        *
    </tr>
    *
    <tr>
        <td>id</td>
        <td>{@link Integer}</td>
        <td>id</td>
        <td>int</td>
        <td>id</td>
    </tr>
    *
    <tr>
        <td>seller</td>
        <td>{@link Integer}</td>
        <td>seller</td>
        <td>int</td>
        <td>商家ID</td>
    </tr>
    *
    <tr>
        <td>createId</td>
        <td>{@link Integer}</td>
        <td>create_id</td>
        <td>int</td>
        <td>申请人</td>
    </tr>
    *
    <tr>
        <td>createTime</td>
        <td>{@link java.util.Date}</td>
        <td>create_time</td>
        <td>datetime</td>
        <td>申请时间</td>
    </tr>
    *
    <tr>
        <td>productCateId</td>
        <td>{@link Integer}</td>
        <td>product_cate_id</td>
        <td>int</td>
        <td>申请分类ID</td>
    </tr>
    *
    <tr>
        <td>productCateName</td>
        <td>{@link String}</td>
        <td>product_cate_name</td>
        <td>varchar</td>
        <td>申请类目名称，提交类目组合</td>
    </tr>
    *
    <tr>
        <td>state</td>
        <td>{@link Integer}</td>
        <td>state</td>
        <td>tinyint</td>
        <td>1、审核中；2、审核通过；3、审核失败；4、停用</td>
    </tr>
    *
    <tr>
        <td>optId</td>
        <td>{@link Integer}</td>
        <td>opt_id</td>
        <td>int</td>
        <td>审核人</td>
    </tr>
    *
    <tr>
        <td>optTime</td>
        <td>{@link java.util.Date}</td>
        <td>opt_time</td>
        <td>datetime</td>
        <td>审核时间</td>
    </tr>
    *
    <tr>
        <td>stopId</td>
        <td>{@link Integer}</td>
        <td>stop_id</td>
        <td>int</td>
        <td>停用人</td>
    </tr>
    *
    <tr>
        <td>stopTime</td>
        <td>{@link java.util.Date}</td>
        <td>stop_time</td>
        <td>datetime</td>
        <td>停用时间</td>
    </tr>
    *
    <tr>
        <td>stopReason</td>
        <td>{@link String}</td>
        <td>stop_reason</td>
        <td>varchar</td>
        <td>停用原由</td>
    </tr>
    *
</table>
*
*/
public class SellerManageCate implements Serializable {

    /**
    *Comment for <code>serialVersionUID</code>
    */
    private static final long serialVersionUID = -264584636750741280L;

    private Integer id;

    private Integer seller; //商家ID

    private Integer createId; //申请人

    private String createUser;

    private java.util.Date createTime; //申请时间

    private Integer productCateId; //申请分类ID

    private String productCateName; //申请类目名称，提交类目组合

    private Integer state; //0、默认；1、提交审核；2、审核通过；3、审核失败；4、停用

    private Integer optId; //审核人

    private java.util.Date optTime; //审核时间

    private Integer stopId; //停用人

    private java.util.Date stopTime; //停用时间

    private String stopReason; //停用原由

    private String sellerName; //商家名称

    /**
    * 获取id
    */
    public Integer getId() {
        return this.id;
    }

    /**
    * 设置id
    */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
    * 获取商家ID
    */
    public Integer getSeller() {
        return this.seller;
    }

    /**
    * 设置商家ID
    */
    public void setSeller(Integer seller) {
        this.seller = seller;
    }

    /**
    * 获取申请人
    */
    public Integer getCreateId() {
        return this.createId;
    }

    /**
    * 设置申请人
    */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
    * 获取申请时间
    */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
    * 设置申请时间
    */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
    * 获取申请分类ID
    */
    public Integer getProductCateId() {
        return this.productCateId;
    }

    /**
    * 设置申请分类ID
    */
    public void setProductCateId(Integer productCateId) {
        this.productCateId = productCateId;
    }

    /**
    * 获取申请类目名称，提交类目组合
    */
    public String getProductCateName() {
        return this.productCateName;
    }

    /**
    * 设置申请类目名称，提交类目组合
    */
    public void setProductCateName(String productCateName) {
        this.productCateName = productCateName;
    }

    /**
    * 获取1、审核中；2、审核通过；3、审核失败；4、停用
    */
    public Integer getState() {
        return this.state;
    }

    /**
    * 设置1、审核中；2、审核通过；3、审核失败；4、停用
    */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
    * 获取审核人
    */
    public Integer getOptId() {
        return this.optId;
    }

    /**
    * 设置审核人
    */
    public void setOptId(Integer optId) {
        this.optId = optId;
    }

    /**
    * 获取审核时间
    */
    public java.util.Date getOptTime() {
        return this.optTime;
    }

    /**
    * 设置审核时间
    */
    public void setOptTime(java.util.Date optTime) {
        this.optTime = optTime;
    }

    /**
    * 获取停用人
    */
    public Integer getStopId() {
        return this.stopId;
    }

    /**
    * 设置停用人
    */
    public void setStopId(Integer stopId) {
        this.stopId = stopId;
    }

    /**
    * 获取停用时间
    */
    public java.util.Date getStopTime() {
        return this.stopTime;
    }

    /**
    * 设置停用时间
    */
    public void setStopTime(java.util.Date stopTime) {
        this.stopTime = stopTime;
    }

    /**
    * 获取停用原由
    */
    public String getStopReason() {
        return this.stopReason;
    }

    /**
    * 设置停用原由
    */
    public void setStopReason(String stopReason) {
        this.stopReason = stopReason;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public enum Status {
        //0、默认；1、提交审核；2、审核通过；3、审核失败；4、停用
        DEFAULT("默认", 0), COMMIT("提交审核", 1), SUCCESS("审核通过", 2), ERROR("审核失败", 3), DEL("停用", 4);

        private String name; //显示的名字
        private int value; //实际存储的值

        //构造方法
        private Status(String name, int value) {
            this.name = name;
            this.value = value;
        }

        /**
         * 检查value是否合法
         * @param value
         * @return
         */
        public static Boolean chkStatus(int value) {
            for (Status status : Status.values()) {
                if (status.getValue() == value)
                    return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
}