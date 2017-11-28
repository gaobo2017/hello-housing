package com.ejavashop.vo.system;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * <p>VO对象: <strong>RegionsVO</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>parentId</td><td>{@link Integer}</td><td>parentId</td><td>int</td><td>parentId</td></tr>
 *   <tr><td>regionName</td><td>{@link String}</td><td>regionName</td><td>varchar</td><td>regionName</td></tr>
 *   <tr><td>parentPath</td><td>{@link String}</td><td>parentPath</td><td>varchar</td><td>parentPath</td></tr>
 *   <tr><td>firstLetter</td><td>{@link String}</td><td>firstLetter</td><td>char</td><td>firstLetter</td></tr>
 *   <tr><td>regionType</td><td>{@link Integer}</td><td>regionType</td><td>tinyint</td><td>regionType</td></tr>
 *   <tr><td>agencyId</td><td>{@link Integer}</td><td>agencyId</td><td>smallint</td><td>agencyId</td></tr>
 *   <tr><td>shippingId</td><td>{@link Integer}</td><td>shippingId</td><td>int</td><td>市场级别</td></tr>
 *   <tr><td>visible</td><td>{@link Integer}</td><td>visible</td><td>tinyint</td><td>是否可见</td></tr>
 *   <tr><td>rowId</td><td>{@link String}</td><td>rowId</td><td>varchar</td><td>HP地址库映射字段</td></tr>
 * </table>
 *
 */
public class RegionsVO implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6768120337214387717L;
    private Integer           id;
    private Integer           parentId;
    private String            regionName;
    private String            parentPath;
    private String            firstLetter;
    private Integer           regionType;
    private Integer           agencyId;
    private Integer           shippingId;
    private Integer           visible;
    private String            rowId;
    /**
     * 下级地区
     */
    private List<RegionsVO>   children;

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
     * 获取parentId
     */
    public Integer getParentId() {
        return this.parentId;
    }

    /**
     * 设置parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取regionName
     */
    public String getRegionName() {
        return this.regionName;
    }

    /**
     * 设置regionName
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * 获取parentPath
     */
    public String getParentPath() {
        return this.parentPath;
    }

    /**
     * 设置parentPath
     */
    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }

    /**
     * 获取firstLetter
     */
    public String getFirstLetter() {
        return this.firstLetter;
    }

    /**
     * 设置firstLetter
     */
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    /**
     * 获取regionType
     */
    public Integer getRegionType() {
        return this.regionType;
    }

    /**
     * 设置regionType
     */
    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    /**
     * 获取agencyId
     */
    public Integer getAgencyId() {
        return this.agencyId;
    }

    /**
     * 设置agencyId
     */
    public void setAgencyId(Integer agencyId) {
        this.agencyId = agencyId;
    }

    /**
     * 获取市场级别
     */
    public Integer getShippingId() {
        return this.shippingId;
    }

    /**
     * 设置市场级别
     */
    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    /**
     * 获取是否可见
     */
    public Integer getVisible() {
        return this.visible;
    }

    /**
     * 设置是否可见
     */
    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    /**
     * 获取HP地址库映射字段
     */
    public String getRowId() {
        return this.rowId;
    }

    /**
     * 设置HP地址库映射字段
     */
    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public List<RegionsVO> getChildren() {
        return children;
    }

    public void setChildren(List<RegionsVO> children) {
        this.children = children;
    }

}