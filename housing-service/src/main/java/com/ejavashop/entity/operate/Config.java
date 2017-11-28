package com.ejavashop.entity.operate;

import java.io.Serializable;

/**
 * 系统配置表
 * <p>Table: <strong>config</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>integralScale</td><td>{@link java.lang.Integer}</td><td>integral_scale</td><td>int</td><td>积分换算比例，多少积分换算成1元人民币</td></tr>
 * </table>
 *
 */
public class Config implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -5698974020722187543L;

    private java.lang.Integer id;
    private java.lang.Integer integralScale;

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
     * 获取积分换算比例，多少积分换算成1元人民币
     */
    public java.lang.Integer getIntegralScale() {
        return this.integralScale;
    }

    /**
     * 设置积分换算比例，多少积分换算成1元人民币
     */
    public void setIntegralScale(java.lang.Integer integralScale) {
        this.integralScale = integralScale;
    }
}