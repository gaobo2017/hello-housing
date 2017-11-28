package com.ejavashop.entity.seller;

import java.io.Serializable;
import java.util.List;

/**
* 商家分类
* <p>Table: <strong>seller_cate</strong>
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
        <td>sellerId</td>
        <td>{@link Integer}</td>
        <td>seller_id</td>
        <td>int</td>
        <td>商家id</td>
    </tr>
    *
    <tr>
        <td>pid</td>
        <td>{@link Integer}</td>
        <td>pid</td>
        <td>int</td>
        <td>父类ID</td>
    </tr>
    *
    <tr>
        <td>name</td>
        <td>{@link String}</td>
        <td>name</td>
        <td>varchar</td>
        <td>分类名称</td>
    </tr>
    *
    <tr>
        <td>path</td>
        <td>{@link String}</td>
        <td>path</td>
        <td>varchar</td>
        <td>分类路径</td>
    </tr>
    *
    <tr>
        <td>sort</td>
        <td>{@link Integer}</td>
        <td>sort</td>
        <td>int</td>
        <td>排序</td>
    </tr>
    *
    <tr>
        <td>createId</td>
        <td>{@link Integer}</td>
        <td>create_id</td>
        <td>int</td>
        <td>创建人</td>
    </tr>
    *
    <tr>
        <td>createTime</td>
        <td>{@link java.util.Date}</td>
        <td>create_time</td>
        <td>datetime</td>
        <td>创建时间</td>
    </tr>
    *
    <tr>
        <td>state</td>
        <td>{@link Integer}</td>
        <td>state</td>
        <td>tinyint</td>
        <td>1、显示；2、不显示；3、删除</td>
    </tr>
    *
</table>
*
*/
public class SellerCate implements Serializable {

    /**
    *Comment for <code>serialVersionUID</code>
    */
    private static final long serialVersionUID = -264584636750741280L;

    private Integer           id;
    private Integer           sellerId;                               //商家id
    private Integer           pid;                                    //父类ID 所有商家分类的pid都为0
    private String            name;                                   //分类名称
    private String            path;                                   //分类路径
    private Integer           sort;                                   //排序
    private Integer           createId;                               //创建人
    private java.util.Date    createTime;                             //创建时间
    private String            state;                                  //表格树状态
    private Integer           status;                                 //1、显示；2、不显示；3、删除

    // --------额外属性（entity对应表结构之外的属性） start------------------------------
    private List<SellerCate>  childs;
    // --------额外属性（entity对应表结构之外的属性） end--------------------------------

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
    * 获取商家id
    */
    public Integer getSellerId() {
        return this.sellerId;
    }

    /**
    * 设置商家id
    */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
    * 获取父类ID
    */
    public Integer getPid() {
        return this.pid;
    }

    /**
    * 设置父类ID
    */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
    * 获取分类名称
    */
    public String getName() {
        return this.name;
    }

    /**
    * 设置分类名称
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
    * 获取分类路径
    */
    public String getPath() {
        return this.path;
    }

    /**
    * 设置分类路径
    */
    public void setPath(String path) {
        this.path = path;
    }

    /**
    * 获取排序
    */
    public Integer getSort() {
        return this.sort;
    }

    /**
    * 设置排序
    */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
    * 获取创建人
    */
    public Integer getCreateId() {
        return this.createId;
    }

    /**
    * 设置创建人
    */
    public void setCreateId(Integer createId) {
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

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SellerCate> getChilds() {
        return childs;
    }

    public void setChilds(List<SellerCate> childs) {
        this.childs = childs;
    }
}