package com.ejavashop.entity.seller;

import java.io.Serializable;

/**
 * 卖家运费模板
 * <p>Table: <strong>seller_transport</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>transName</td><td>{@link java.lang.String}</td><td>trans_name</td><td>varchar</td><td>模板名</td></tr>
 *   <tr><td>transEmsInfo</td><td>{@link java.lang.String}</td><td>trans_ems_info</td><td>text</td><td>ems模板信息</td></tr>
 *   <tr><td>transExpress</td><td>{@link java.lang.Integer}</td><td>trans_express</td><td>int</td><td>transExpress</td></tr>
 *   <tr><td>transExpressInfo</td><td>{@link java.lang.String}</td><td>trans_express_info</td><td>text</td><td>快递模板信息</td></tr>
 *   <tr><td>transMail</td><td>{@link java.lang.Integer}</td><td>trans_mail</td><td>int</td><td>transMail</td></tr>
 *   <tr><td>transMailInfo</td><td>{@link java.lang.String}</td><td>trans_mail_info</td><td>text</td><td>平邮模板信息</td></tr>
 *   <tr><td>createtime</td><td>{@link java.util.Date}</td><td>createtime</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家id</td></tr>
 *   <tr><td>transType</td><td>{@link java.lang.Integer}</td><td>trans_type</td><td>int</td><td>计价方式</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>int</td><td>状态 1-使用中 2-禁用</td></tr>
 *   <tr><td>transTime</td><td>{@link java.lang.Integer}</td><td>trans_time</td><td>int</td><td>发货时间</td></tr>
 *   <tr><td>transEms</td><td>{@link java.lang.Integer}</td><td>trans_ems</td><td>int</td><td>transEms</td></tr>
 * </table>
 *
 */
public class SellerTransport implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8812272436085621171L;
    private java.lang.Integer id;                                      //id
    private java.lang.Integer state;                                   //状态 1-使用中 2-禁用
    private java.lang.Integer sellerId;                                //商家id
    private java.lang.String  transName;                               //模板名
    private java.lang.String  transExpressInfo;                        //快递模板信息
    private java.lang.String  transEmsInfo;                            //ems模板信息
    private java.lang.String  transMailInfo;                           //平邮模板信息
    private java.lang.Integer transExpress;                            //transExpress
    private java.lang.Integer transMail;                               //transMail
    private java.lang.Integer transEms;                                //transEms
    private java.util.Date    createtime;                              //创建时间
    private java.lang.Integer transType;                               //计价方式 1-按件数 2-按重量
    private java.lang.Integer transTime;                               //发货时间

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
     * 获取模板名
     */
    public java.lang.String getTransName() {
        return this.transName;
    }

    /**
     * 设置模板名
     */
    public void setTransName(java.lang.String transName) {
        this.transName = transName;
    }

    /**
     * 获取ems模板信息
     */
    public java.lang.String getTransEmsInfo() {
        return this.transEmsInfo;
    }

    /**
     * 设置ems模板信息
     */
    public void setTransEmsInfo(java.lang.String transEmsInfo) {
        this.transEmsInfo = transEmsInfo;
    }

    /**
     * 获取transExpress
     */
    public java.lang.Integer getTransExpress() {
        return this.transExpress;
    }

    /**
     * 设置transExpress
     */
    public void setTransExpress(java.lang.Integer transExpress) {
        this.transExpress = transExpress;
    }

    /**
     * 获取快递模板信息
     */
    public java.lang.String getTransExpressInfo() {
        return this.transExpressInfo;
    }

    /**
     * 设置快递模板信息
     */
    public void setTransExpressInfo(java.lang.String transExpressInfo) {
        this.transExpressInfo = transExpressInfo;
    }

    /**
     * 获取transMail
     */
    public java.lang.Integer getTransMail() {
        return this.transMail;
    }

    /**
     * 设置transMail
     */
    public void setTransMail(java.lang.Integer transMail) {
        this.transMail = transMail;
    }

    /**
     * 获取平邮模板信息
     */
    public java.lang.String getTransMailInfo() {
        return this.transMailInfo;
    }

    /**
     * 设置平邮模板信息
     */
    public void setTransMailInfo(java.lang.String transMailInfo) {
        this.transMailInfo = transMailInfo;
    }

    /**
     * 获取创建时间
     */
    public java.util.Date getCreatetime() {
        return this.createtime;
    }

    /**
     * 设置创建时间
     */
    public void setCreatetime(java.util.Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取商家id
     */
    public java.lang.Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置商家id
     */
    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取计价方式
     */
    public java.lang.Integer getTransType() {
        return this.transType;
    }

    /**
     * 设置计价方式
     */
    public void setTransType(java.lang.Integer transType) {
        this.transType = transType;
    }

    /**
     * 获取状态 1-使用中 2-禁用
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置状态 1-使用中 2-禁用
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取发货时间
     */
    public java.lang.Integer getTransTime() {
        return this.transTime;
    }

    /**
     * 设置发货时间
     */
    public void setTransTime(java.lang.Integer transTime) {
        this.transTime = transTime;
    }

    /**
     * 获取transEms
     */
    public java.lang.Integer getTransEms() {
        return this.transEms;
    }

    /**
     * 设置transEms
     */
    public void setTransEms(java.lang.Integer transEms) {
        this.transEms = transEms;
    }
}