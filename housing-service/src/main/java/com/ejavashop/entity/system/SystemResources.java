package com.ejavashop.entity.system;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 资源表
 * <p>Table: <strong>system_resources</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>pid</td><td>{@link Integer}</td><td>pid</td><td>int</td><td>pid</td></tr>
 *   <tr><td>url</td><td>{@link String}</td><td>url</td><td>varchar</td><td>url</td></tr>
 *   <tr><td>content</td><td>{@link String}</td><td>content</td><td>varchar</td><td>content</td></tr>
 *   <tr><td>createTime</td><td>{@link Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>state</td><td>{@link Integer}</td><td>state</td><td>tinyint</td><td>1、菜单；2、按钮</td></tr>
 *   <tr><td>status</td><td>{@link Integer}</td><td>status</td><td>tinyint</td><td>1、未删除2、删除</td></tr>
 * </table>
 *
 */
public class SystemResources implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4149807044573235060L;

    /**
     * 资源使用范围：1、商家
     */
    public final static Integer SCOPE_SELLER = 1;
    /**
     * 资源使用范围：2、平台
     */
    public final static Integer SCOPE_ADMIN  = 2;

    private Integer id;         //id
    private Integer pid;        //pid
    private String  url;        //url
    private String  content;    //content
    private Date    createTime; //createTime
    private Integer type;       //1、菜单；2、按钮
    private Integer status;     //1、未删除2、删除
    private Integer scope;
    private String  resId;      //资源id,有些菜单及按钮需要特定id以注册点击事件
    private String  resIcon;    //资源图标,按钮资源可能需要指定图标

    //树结点状态
    private String                state;
    private List<SystemResources> children = new ArrayList<SystemResources>();

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
     * 获取pid
     */
    public Integer getPid() {
        return this.pid;
    }

    /**
     * 设置pid
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取content
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取createTime
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取1、菜单；2、按钮
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * 设置1、菜单；2、按钮
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取1、未删除2、删除
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * 设置1、未删除2、删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<SystemResources> getChildren() {
        return children;
    }

    public void setChildren(List<SystemResources> children) {
        this.children = children;
    }

    @Override
    public int hashCode() {
        return this.id.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SystemResources other = (SystemResources) obj;
        if (this.id.intValue() == other.id.intValue())
            return true;
        return false;
    }

    public Integer getScope() {
        return scope;
    }

    public void setScope(Integer scope) {
        this.scope = scope;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getResIcon() {
        return resIcon;
    }

    public void setResIcon(String resIcon) {
        this.resIcon = resIcon;
    }

}