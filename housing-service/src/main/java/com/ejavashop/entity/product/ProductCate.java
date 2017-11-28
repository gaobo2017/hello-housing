package com.ejavashop.entity.product;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品分类
 * <p>Table: <strong>product_cate</strong>
 * <p/>
 * <table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 * <p/>
 * <tr style="background-color:#ddd;Text-align:Left;">
 * <p/>
 * <th nowrap>属性名</th>
 * <th nowrap>属性类型</th>
 * <th nowrap>字段名</th>
 * <th nowrap>字段类型</th>
 * <th nowrap>说明</th>
 * <p/>
 * </tr>
 * <p/>
 * <tr>
 * <td>id</td>
 * <td>{@link Integer}</td>
 * <td>id</td>
 * <td>int</td>
 * <td>id</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>productTypeId</td>
 * <td>{@link Integer}</td>
 * <td>product_type_id</td>
 * <td>int</td>
 * <td>类型ID</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>pid</td>
 * <td>{@link Integer}</td>
 * <td>pid</td>
 * <td>int</td>
 * <td>父类ID</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>name</td>
 * <td>{@link String}</td>
 * <td>name</td>
 * <td>varchar</td>
 * <td>分类名称</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>path</td>
 * <td>{@link String}</td>
 * <td>path</td>
 * <td>varchar</td>
 * <td>分类路径</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>scaling</td>
 * <td>{@link Integer}</td>
 * <td>scaling</td>
 * <td>int</td>
 * <td>分佣比例(商家给平台的分佣比例，填写1到100的数字)</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>createId</td>
 * <td>{@link Integer}</td>
 * <td>create_id</td>
 * <td>int</td>
 * <td>创建人</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>updateId</td>
 * <td>{@link Integer}</td>
 * <td>update_id</td>
 * <td>int</td>
 * <td>更新人</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>createTime</td>
 * <td>{@link java.util.Date}</td>
 * <td>create_time</td>
 * <td>datetime</td>
 * <td>创建时间</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>updateTime</td>
 * <td>{@link java.util.Date}</td>
 * <td>update_time</td>
 * <td>datetime</td>
 * <td>更新时间</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>sort</td>
 * <td>{@link Integer}</td>
 * <td>sort</td>
 * <td>int</td>
 * <td>排序</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>state</td>
 * <td>{@link Integer}</td>
 * <td>state</td>
 * <td>tinyint</td>
 * <td>1、显示；2、不显示；3、删除</td>
 * </tr>
 * <p/>
 * </table>
 */
public class ProductCate implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -264584636750741280L;

    private Integer           id;

    private Integer           productTypeId;                          //类型ID
    private String typeName;//类型名称

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createId == null) ? 0 : createId.hashCode());
        result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((path == null) ? 0 : path.hashCode());
        result = prime * result + ((pid == null) ? 0 : pid.hashCode());
        result = prime * result + ((productTypeId == null) ? 0 : productTypeId.hashCode());
        result = prime * result + ((scaling == null) ? 0 : scaling.hashCode());
        result = prime * result + ((sort == null) ? 0 : sort.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((updateId == null) ? 0 : updateId.hashCode());
        result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProductCate other = (ProductCate) obj;
        if (createId == null) {
            if (other.createId != null)
                return false;
        } else if (!createId.equals(other.createId))
            return false;
        if (createTime == null) {
            if (other.createTime != null)
                return false;
        } else if (!createTime.equals(other.createTime))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (path == null) {
            if (other.path != null)
                return false;
        } else if (!path.equals(other.path))
            return false;
        if (pid == null) {
            if (other.pid != null)
                return false;
        } else if (!pid.equals(other.pid))
            return false;
        if (productTypeId == null) {
            if (other.productTypeId != null)
                return false;
        } else if (!productTypeId.equals(other.productTypeId))
            return false;
        if (scaling == null) {
            if (other.scaling != null)
                return false;
        } else if (!scaling.equals(other.scaling))
            return false;
        if (sort == null) {
            if (other.sort != null)
                return false;
        } else if (!sort.equals(other.sort))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        if (updateId == null) {
            if (other.updateId != null)
                return false;
        } else if (!updateId.equals(other.updateId))
            return false;
        if (updateTime == null) {
            if (other.updateTime != null)
                return false;
        } else if (!updateTime.equals(other.updateTime))
            return false;
        return true;
    }

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}