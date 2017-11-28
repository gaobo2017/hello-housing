package com.ejavashop.entity.promotion.flash;

import java.io.Serializable;

/**
 * 限时抢购活动阶段日志表
 * <p>Table: <strong>log_act_flash_sale_stage</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>actFlashSaleStageId</td><td>{@link java.lang.Integer}</td><td>act_flash_sale_stage_id</td><td>int</td><td>actFlashSaleStageId</td></tr>
 *   <tr><td>actFlashSaleId</td><td>{@link java.lang.Integer}</td><td>act_flash_sale_id</td><td>int</td><td>限时抢购活动id</td></tr>
 *   <tr><td>startTime</td><td>{@link java.lang.Integer}</td><td>start_time</td><td>varchar</td><td>开始时间</td></tr>
 *   <tr><td>endTime</td><td>{@link java.lang.Integer}</td><td>end_time</td><td>varchar</td><td>结束时间</td></tr>
 * </table>
 *
 */
public class LogActFlashSaleStage implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3147139956008538766L;

    private java.lang.Integer id;
    private java.lang.Integer actFlashSaleStageId;
    private java.lang.Integer actFlashSaleId;
    private java.lang.Integer startTime;
    private java.lang.Integer endTime;

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
     * 获取actFlashSaleStageId
     */
    public java.lang.Integer getActFlashSaleStageId() {
        return this.actFlashSaleStageId;
    }

    /**
     * 设置actFlashSaleStageId
     */
    public void setActFlashSaleStageId(java.lang.Integer actFlashSaleStageId) {
        this.actFlashSaleStageId = actFlashSaleStageId;
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
}