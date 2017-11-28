package com.ejavashop.entity.member;

import java.io.Serializable;

/**
 * 会员等级配置表
 * <p>Table: <strong>member_grade_config</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>grade1</td><td>{@link java.lang.Integer}</td><td>grade1</td><td>int</td><td>注册会员经验值</td></tr>
 *   <tr><td>grade2</td><td>{@link java.lang.Integer}</td><td>grade2</td><td>int</td><td>铜牌会员经验值</td></tr>
 *   <tr><td>grade3</td><td>{@link java.lang.Integer}</td><td>grade3</td><td>int</td><td>银牌会员经验值</td></tr>
 *   <tr><td>grade4</td><td>{@link java.lang.Integer}</td><td>grade4</td><td>int</td><td>金牌会员经验值</td></tr>
 *   <tr><td>grade5</td><td>{@link java.lang.Integer}</td><td>grade5</td><td>int</td><td>钻石会员经验值</td></tr>
 * </table>
 *
 */
public class MemberGradeConfig implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8907305070913506211L;

    private java.lang.Integer id;
    private java.lang.Integer grade1;
    private java.lang.Integer grade2;
    private java.lang.Integer grade3;
    private java.lang.Integer grade4;
    private java.lang.Integer grade5;

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
     * 获取注册会员经验值
     */
    public java.lang.Integer getGrade1() {
        return this.grade1;
    }

    /**
     * 设置注册会员经验值
     */
    public void setGrade1(java.lang.Integer grade1) {
        this.grade1 = grade1;
    }

    /**
     * 获取铜牌会员经验值
     */
    public java.lang.Integer getGrade2() {
        return this.grade2;
    }

    /**
     * 设置铜牌会员经验值
     */
    public void setGrade2(java.lang.Integer grade2) {
        this.grade2 = grade2;
    }

    /**
     * 获取银牌会员经验值
     */
    public java.lang.Integer getGrade3() {
        return this.grade3;
    }

    /**
     * 设置银牌会员经验值
     */
    public void setGrade3(java.lang.Integer grade3) {
        this.grade3 = grade3;
    }

    /**
     * 获取金牌会员经验值
     */
    public java.lang.Integer getGrade4() {
        return this.grade4;
    }

    /**
     * 设置金牌会员经验值
     */
    public void setGrade4(java.lang.Integer grade4) {
        this.grade4 = grade4;
    }

    /**
     * 获取钻石会员经验值
     */
    public java.lang.Integer getGrade5() {
        return this.grade5;
    }

    /**
     * 设置钻石会员经验值
     */
    public void setGrade5(java.lang.Integer grade5) {
        this.grade5 = grade5;
    }
}