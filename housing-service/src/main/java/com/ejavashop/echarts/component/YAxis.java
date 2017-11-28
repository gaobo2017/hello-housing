package com.ejavashop.echarts.component;

/**
 * y轴
 *                       
 * @Filename: YAxis.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public class YAxis {
    private String    type;
    private String    name;
    private AxisLabel axisLabel;
    //坐标轴在 grid 区域中的分隔线
    private SplitLine splitLine;

    public class AxisLabel {
        String formatter;

        public String getFormatter() {
            return formatter;
        }

        public void setFormatter(String formatter) {
            this.formatter = formatter;
        }
    }

    public class SplitLine {
        boolean show = true;

        public boolean isShow() {
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
        }

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AxisLabel getAxisLabel() {
        return axisLabel;
    }

    public void setAxisLabel(AxisLabel axisLabel) {
        this.axisLabel = axisLabel;
    }

    public SplitLine getSplitLine() {
        return splitLine;
    }

    public void setSplitLine(SplitLine splitLine) {
        this.splitLine = splitLine;
    }

}
