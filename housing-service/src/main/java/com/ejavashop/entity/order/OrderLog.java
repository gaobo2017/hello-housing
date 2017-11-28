package com.ejavashop.entity.order;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单操作日志表
 * <p>Table: <strong>order_log</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>operatingId</td><td>{@link java.lang.Integer}</td><td>operating_id</td><td>int</td><td>操作人，系统操作为0</td></tr>
 *   <tr><td>operatingName</td><td>{@link java.lang.String}</td><td>operating_name</td><td>varchar</td><td>operatingName</td></tr>
 *   <tr><td>ordersId</td><td>{@link java.lang.Integer}</td><td>orders_id</td><td>int</td><td>ordersId</td></tr>
 *   <tr><td>ordersSn</td><td>{@link java.lang.String}</td><td>orders_sn</td><td>varchar</td><td>ordersSn</td></tr>
 *   <tr><td>content</td><td>{@link java.lang.String}</td><td>content</td><td>varchar</td><td>内容</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 * </table>
 *
 */
public class OrderLog implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5634754347824223897L;
    private java.lang.Integer id;                                     //id
    private java.lang.Integer operatingId;                            //操作人，系统操作为0
    private java.lang.String  operatingName;                          //operatingName
    private java.lang.Integer ordersId;                               //ordersId
    private java.lang.String  ordersSn;                               //ordersSn
    private java.lang.String  content;                                //内容
    private java.util.Date    createTime;                             //createTime

    public OrderLog() {
    }

    public OrderLog(Integer operatingId, String operatingName, Integer ordersId, String ordersSn,
                    String content, Date createTime) {
        this.operatingId = operatingId;
        this.operatingName = operatingName;
        this.ordersId = ordersId;
        this.ordersSn = ordersSn;
        this.content = content;
        this.createTime = createTime;
    }

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
     * 获取操作人，系统操作为0
     */
    public java.lang.Integer getOperatingId() {
        return this.operatingId;
    }

    /**
     * 设置操作人，系统操作为0
     */
    public void setOperatingId(java.lang.Integer operatingId) {
        this.operatingId = operatingId;
    }

    /**
     * 获取operatingName
     */
    public java.lang.String getOperatingName() {
        return this.operatingName;
    }

    /**
     * 设置operatingName
     */
    public void setOperatingName(java.lang.String operatingName) {
        this.operatingName = operatingName;
    }

    /**
     * 获取ordersId
     */
    public java.lang.Integer getOrdersId() {
        return this.ordersId;
    }

    /**
     * 设置ordersId
     */
    public void setOrdersId(java.lang.Integer ordersId) {
        this.ordersId = ordersId;
    }

    /**
     * 获取ordersSn
     */
    public java.lang.String getOrdersSn() {
        return this.ordersSn;
    }

    /**
     * 设置ordersSn
     */
    public void setOrdersSn(java.lang.String ordersSn) {
        this.ordersSn = ordersSn;
    }

    /**
     * 获取内容
     */
    public java.lang.String getContent() {
        return this.content;
    }

    /**
     * 设置内容
     */
    public void setContent(java.lang.String content) {
        this.content = content;
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
}