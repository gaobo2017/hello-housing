package com.ejavashop.entity.promotion.flash;

import java.io.Serializable;
import java.util.List;

/**
 * 限时抢购活动阶段表
 * <p>Table: <strong>act_flash_sale_stage</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>actFlashSaleId</td><td>{@link java.lang.Integer}</td><td>act_flash_sale_id</td><td>int</td><td>限时抢购活动id</td></tr>
 *   <tr><td>startTime</td><td>{@link java.lang.Integer}</td><td>start_time</td><td>varchar</td><td>开始时间</td></tr>
 *   <tr><td>endTime</td><td>{@link java.lang.Integer}</td><td>end_time</td><td>varchar</td><td>结束时间</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>varchar</td><td>阶段描述</td></tr>
 * </table>
 *
 */
public class ActFlashSaleStage implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long         serialVersionUID = -6202447739842787511L;

    private java.lang.Integer         id;
    private java.lang.Integer         actFlashSaleId;
    private java.lang.Integer         startTime;
    private java.lang.Integer         endTime;
    private java.lang.String          remark;

    //=======================custom param===========================//
    //活动名称
    private String                    actName;
    private List<ActFlashSaleProduct> productList;

    //=======================custom param===========================//

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
     * 获取限时抢购活动id
     */
    public java.lang.Integer getActFlashSaleId() {
        return this.actFlashSaleId;
    }

    /**
     * 设置限时抢购活动id
     */
    public void setActFlashSaleId(java.lang.Integer actFlashSaleId) {
        this.actFlashSaleId = actFlashSaleId;
    }

    /**
     * 获取开始时间
     */
    public java.lang.Integer getStartTime() {
        return this.startTime;
    }

    /**
     * 设置开始时间
     */
    public void setStartTime(java.lang.Integer startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     */
    public java.lang.Integer getEndTime() {
        return this.endTime;
    }

    /**
     * 设置结束时间
     */
    public void setEndTime(java.lang.Integer endTime) {
        this.endTime = endTime;
    }

    public java.lang.String getRemark() {
        return remark;
    }

    public void setRemark(java.lang.String remark) {
        this.remark = remark;
    }

    public List<ActFlashSaleProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<ActFlashSaleProduct> productList) {
        this.productList = productList;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

}