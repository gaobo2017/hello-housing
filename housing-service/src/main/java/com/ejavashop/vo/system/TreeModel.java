package com.ejavashop.vo.system;

import java.util.ArrayList;
import java.util.List;

/**
 * 树模型
 *                       
 * @Filename: TreeModel.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public class TreeModel {

    private Integer         id;
    private Integer         pid;
    private String          text;
    /** 节点状态, 'open' 或者 'closed', 默认是 'open' */
    private String          state    = "open";
    private String          url;
    /** 图标 */
    private String          iconCls;
    private List<TreeModel> children = new ArrayList<TreeModel>(); ;
    private boolean         checked  = false;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public List<TreeModel> getChildren() {
        return children;
    }

    public void setChildren(List<TreeModel> children) {
        this.children = children;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

}
