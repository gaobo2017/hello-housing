package com.ejavashop.vo.product;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品分类VO
 *                       
 * @Filename: ProductCateVO.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public class ProductCateVO implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -264584636750741280L;

    private Integer           id;

    private Integer           productTypeId;                          //类型ID

    private Integer           pid;                                    //父类ID

    private String            name;                                   //分类名称

    private String            path;                                   //分类路径 规则:一级分类 / ;二级分类 /1 ;三级分类 /1/2

    private BigDecimal        scaling;                                //分佣比例(商家给平台的分佣比例，填写1到100的数字)

    private Integer           createId;                               //创建人

    private Integer           updateId;                               //更新人

    private java.util.Date    createTime;                             //创建时间

    private java.util.Date    updateTime;                             //更新时间

    private Integer           sort;                                   //排序

    private Integer           status;                                 //1、显示；2、不显示；3、删除

    private String            state;                                  //treeGrid的状态

    private String            creater;                                //创建人

    private String            updater;                                //更新人

    //申请商家
    private String            seller;
    //商家名称
    private String            sellerName;

    //申请分类名(带路径)
    private String            pathName;

    private String            checked;

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
     * 获取类型ID
     */
    public Integer getProductTypeId() {
        return this.productTypeId;
    }

    /**
     * 设置类型ID
     */
    public void setProductTypeId(Integer productTypeId) {
        this.productTypeId = productTypeId;
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
     * 获取分佣比例(商家给平台的分佣比例，填写1到100的数字)
     */
    public BigDecimal getScaling() {
        return this.scaling;
    }

    /**
     * 设置分佣比例(商家给平台的分佣比例，填写1到100的数字)
     */
    public void setScaling(BigDecimal scaling) {
        this.scaling = scaling;
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
     * 获取更新人
     */
    public Integer getUpdateId() {
        return this.updateId;
    }

    /**
     * 设置更新人
     */
    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
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

    /**
     * 获取更新时间
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置更新时间
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
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
     * 获取1、显示；2、不显示；3、删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置1、显示；2、不显示；3、删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}