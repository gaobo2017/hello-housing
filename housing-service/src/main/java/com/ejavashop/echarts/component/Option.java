package com.ejavashop.echarts.component;

import java.util.List;

/**
 * echarts数据填充参数
 *                       
 * @Filename: Option.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public class Option {
    private String       backgroundColor;
    private float        animationDuration = 3000;
    private Tooltip      tooltip;
    private Toolbox      toolbox;
    private Legend       legend;
    private XAxis        xAxis;
    private List<YAxis>  yAxis;
    private List<Series> series;
    private Title        title;

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Tooltip getTooltip() {
        return tooltip;
    }

    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    public Legend getLegend() {
        return legend;
    }

    public void setLegend(Legend legend) {
        this.legend = legend;
    }

    public XAxis getxAxis() {
        return xAxis;
    }

    public void setxAxis(XAxis xAxis) {
        this.xAxis = xAxis;
    }

    public List<YAxis> getyAxis() {
        return yAxis;
    }

    public void setyAxis(List<YAxis> yAxis) {
        this.yAxis = yAxis;
    }

    public Toolbox getToolbox() {
        return toolbox;
    }

    public void setToolbox(Toolbox toolbox) {
        this.toolbox = toolbox;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public float getAnimationDuration() {
        return animationDuration;
    }

    public void setAnimationDuration(float animationDuration) {
        this.animationDuration = animationDuration;
    }

}
