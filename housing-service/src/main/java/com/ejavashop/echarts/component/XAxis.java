package com.ejavashop.echarts.component;

import java.util.List;

/**
 * x轴
 *                       
 * @Filename: XAxis.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public class XAxis {
    private String       type;
    private String       name;
    //坐标轴在 grid 区域中的分隔线
    private SplitLine    splitLine;
    //坐标轴轴线相关设置
    private AxisLine     axisLine;
    private List<String> data;
    private Boolean      boundaryGap;

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

    public SplitLine getSplitLine() {
        return splitLine;
    }

    public void setSplitLine(SplitLine splitLine) {
        this.splitLine = splitLine;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
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

    public class AxisLine {
        boolean show;
        boolean onZero;

        LineStyle lineStyle;

        public class LineStyle {
            String color;
            float  width;
            String type;
            float  shadowBlur;

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public float getWidth() {
                return width;
            }

            public void setWidth(float width) {
                this.width = width;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public float getShadowBlur() {
                return shadowBlur;
            }

            public void setShadowBlur(float shadowBlur) {
                this.shadowBlur = shadowBlur;
            }

        }

        public boolean getShow() {
            return show;
        }

        public void setShow(boolean show) {
            this.show = show;
        }

        public boolean isOnZero() {
            return onZero;
        }

        public void setOnZero(boolean onZero) {
            this.onZero = onZero;
        }

        public LineStyle getLineStyle() {
            return lineStyle;
        }

        public void setLineStyle(LineStyle lineStyle) {
            this.lineStyle = lineStyle;
        }

    }

    public AxisLine getAxisLine() {
        return axisLine;
    }

    public void setAxisLine(AxisLine axisLine) {
        this.axisLine = axisLine;
    }

    public Boolean getBoundaryGap() {
        return boundaryGap;
    }

    public void setBoundaryGap(Boolean boundaryGap) {
        this.boundaryGap = boundaryGap;
    }

}
