package com.ejavashop.entity.operate;

import java.io.Serializable;

/**
 * 快递公司
 * <p>Table: <strong>courier_company</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Long}</td><td>id</td><td>bigint</td><td>id</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>state</td><td>{@link Integer}</td><td>state</td><td>int</td><td>状态 1-可用 2-不可用</td></tr>
 *   <tr><td>companyMark</td><td>{@link String}</td><td>company_mark</td><td>varchar</td><td>快递代码</td></tr>
 *   <tr><td>companyName</td><td>{@link String}</td><td>company_name</td><td>varchar</td><td>快递公司名称</td></tr>
 *   <tr><td>seq</td><td>{@link Integer}</td><td>seq</td><td>int</td><td>排序号</td></tr>
 *   <tr><td>companyType</td><td>{@link String}</td><td>company_type</td><td>varchar</td><td>快递类型 1-平邮 2-EMS 3-快递</td></tr>
 * </table>
 *
 */
public class CourierCompany implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private Integer           id;
    private java.util.Date    createTime;
    private Integer           state;
    private String            companyMark;
    private String            companyName;
    private Integer           seq;
    private String            companyType;
    private Integer           sellerId;
    private String            imagePath;            // 快递模板路径
    private String            content;              // 打印模板生成的js文件

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
     * 获取状态 1-可用 2-不可用
     */
    public Integer getState() {
        return this.state;
    }

    /**
     * 设置状态 1-可用 2-不可用
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取快递代码
     */
    public String getCompanyMark() {
        return this.companyMark;
    }

    /**
     * 设置快递代码
     */
    public void setCompanyMark(String companyMark) {
        this.companyMark = companyMark;
    }

    /**
     * 获取快递公司名称
     */
    public String getCompanyName() {
        return this.companyName;
    }

    /**
     * 设置快递公司名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 获取排序号
     */
    public Integer getSeq() {
        return this.seq;
    }

    /**
     * 设置排序号
     */
    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    /**
     * 获取快递类型 1-平邮 2-EMS 3-快递
     */
    public String getCompanyType() {
        return this.companyType;
    }

    /**
     * 设置快递类型 1-平邮 2-EMS 3-快递
     */
    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}