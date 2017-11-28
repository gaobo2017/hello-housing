package com.ejavashop.echarts.component;

/**
 * 提示
 *                       
 * @Filename: Tooltip.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public class Tooltip {
    private String trigger = "axis";
    private String formatter;

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

}
