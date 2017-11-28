package com.ejavashop.echarts.component;

import java.util.List;

/**
 * 图示
 *                       
 * @Filename: Legend.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public class Legend {
    private String orient;
    private String x;
    List<String>   data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getOrient() {
        return orient;
    }

    public void setOrient(String orient) {
        this.orient = orient;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

}
