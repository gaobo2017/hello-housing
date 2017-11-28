package com.ejavashop.entity.seller;

import java.io.Serializable;

/**
* 商家类型修改日志表
* <p>Table: <strong>seller_type_logs</strong>
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
        <td>createId</td>
        <td>{@link Integer}</td>
        <td>create_id</td>
        <td>int</td>
        <td>修改人</td>
    </tr>
    *
    <tr>
        <td>createName</td>
        <td>{@link String}</td>
        <td>create_name</td>
        <td>varchar</td>
        <td>修改人账号</td>
    </tr>
    *
    <tr>
        <td>content</td>
        <td>{@link String}</td>
        <td>content</td>
        <td>varchar</td>
        <td>修改内容</td>
    </tr>
    *
    <tr>
        <td>createTime</td>
        <td>{@link java.util.Date}</td>
        <td>create_time</td>
        <td>datetime</td>
        <td>修改时间</td>
    </tr>
    *
</table>
*
*/
public class SellerTypeLogs implements Serializable {

    /**
    *Comment for <code>serialVersionUID</code>
    */
    private static final long serialVersionUID = -264584636750741280L;

    private Integer id;

    private Integer createId; //修改人

    private String createName; //修改人账号

    private String content; //修改内容

    private java.util.Date createTime; //修改时间

    private Integer productCateId; //商品分类id

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
    * 获取修改人
    */
    public Integer getCreateId() {
        return this.createId;
    }

    /**
    * 设置修改人
    */
    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    /**
    * 获取修改人账号
    */
    public String getCreateName() {
        return this.createName;
    }

    /**
    * 设置修改人账号
    */
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
    * 获取修改内容
    */
    public String getContent() {
        return this.content;
    }

    /**
    * 设置修改内容
    */
    public void setContent(String content) {
        this.content = content;
    }

    /**
    * 获取修改时间
    */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
    * 设置修改时间
    */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public Integer getProductCateId() {
        return productCateId;
    }

    public void setProductCateId(Integer productCateId) {
        this.productCateId = productCateId;
    }
}