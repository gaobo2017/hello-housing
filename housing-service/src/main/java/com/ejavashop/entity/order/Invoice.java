package com.ejavashop.entity.order;

import java.io.Serializable;

/**
 * 发票信息表
 * <p>Table: <strong>invoice</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>content</td><td>{@link java.lang.String}</td><td>content</td><td>varchar</td><td>发票抬头</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>状态1、显示；2、不显示</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>创建人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 * </table>
 *
 */
public class Invoice implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5433412859761360908L;

    /** 发票 状态state   1、显示；  */
    public final static int   STATE_1          = 1;
    /** 发票 状态state   2、不显示；  */
    public final static int   STATE_2          = 2;

    private java.lang.Integer id;                                     //id
    private java.lang.String  content;                                //发票抬头
    private java.lang.Integer state;                                  //状态1、显示；2、不显示
    private java.lang.Integer createId;                               //创建人
    private java.util.Date    createTime;                             //创建时间

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
     * 获取发票抬头
     */
    public java.lang.String getContent() {
        return this.content;
    }

    /**
     * 设置发票抬头
     */
    public void setContent(java.lang.String content) {
        this.content = content;
    }

    /**
     * 获取状态1、显示；2、不显示
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置状态1、显示；2、不显示
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取创建人
     */
    public java.lang.Integer getCreateId() {
        return this.createId;
    }

    /**
     * 设置创建人
     */
    public void setCreateId(java.lang.Integer createId) {
        this.createId = createId;
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
}