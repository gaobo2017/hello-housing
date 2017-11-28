package com.ejavashop.echarts.component;

import java.util.List;

/**
 * 系列
 *                       
 * @Filename: Series.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public class Series {
    private String       name;
    private String       type;
    private ItemStyle    itemStyle;
    private LineStyle    lineStyle;
    private Integer      yAxisIndex;
    private List<Object> data;
    private String       radius;
    private List<String> center;
    private String       stack;
    private Boolean      smooth;
    private MarkPoint    markPoint;
    private MarkLine     markLine;

    public String getRadius() {
        return radius;
    }

    public void setRadius(String radius) {
        this.radius = radius;
    }

    public List<String> getCenter() {
        return center;
    }

    public void setCenter(List<String> center) {
        this.center = center;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ItemStyle getItemStyle() {
        return itemStyle;
    }

    public void setItemStyle(ItemStyle itemStyle) {
        this.itemStyle = itemStyle;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public Integer getyAxisIndex() {
        return yAxisIndex;
    }

    public void setyAxisIndex(Integer yAxisIndex) {
        this.yAxisIndex = yAxisIndex;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public Boolean isSmooth() {
        return smooth;
    }

    public void setSmooth(Boolean smooth) {
        this.smooth = smooth;
    }

    public LineStyle getLineStyle() {
        return lineStyle;
    }

    public void setLineStyle(LineStyle lineStyle) {
        this.lineStyle = lineStyle;
    }

    public class LineStyle {
        Normal normal;

        public Normal getNormal() {
            return normal;
        }

        public void setNormal(Normal normal) {
            this.normal = normal;
        }

        public class Normal {
            Float  width;
            String shadowColor;
            Float  shadowBlur;
            Float  shadowOffsetY;
            String type;

            public float getWidth() {
                return width;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setWidth(float width) {
                this.width = width;
            }

            public String getShadowColor() {
                return shadowColor;
            }

            public void setShadowColor(String shadowColor) {
                this.shadowColor = shadowColor;
            }

            public float getShadowBlur() {
                return shadowBlur;
            }

            public void setShadowBlur(float shadowBlur) {
                this.shadowBlur = shadowBlur;
            }

            public float getShadowOffsetY() {
                return shadowOffsetY;
            }

            public void setShadowOffsetY(float shadowOffsetY) {
                this.shadowOffsetY = shadowOffsetY;
            }

        }
    }

    public class ItemStyle {
        private Normal   normal;
        private Emphasis emphasis;

        public class Normal {
            String      color;
            List<Float> barBorderRadius;

            public List<Float> getBarBorderRadius() {
                return barBorderRadius;
            }

            public void setBarBorderRadius(List<Float> barBorderRadius) {
                this.barBorderRadius = barBorderRadius;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public class Emphasis {
            float  shadowBlur;
            float  shadowOffsetX;
            String shadowColor;

            public float getShadowBlur() {
                return shadowBlur;
            }

            public void setShadowBlur(float shadowBlur) {
                this.shadowBlur = shadowBlur;
            }

            public float getShadowOffsetX() {
                return shadowOffsetX;
            }

            public void setShadowOffsetX(float shadowOffsetX) {
                this.shadowOffsetX = shadowOffsetX;
            }

            public String getShadowColor() {
                return shadowColor;
            }

            public void setShadowColor(String shadowColor) {
                this.shadowColor = shadowColor;
            }

        }

        public Normal getNormal() {
            return normal;
        }

        public void setNormal(Normal normal) {
            this.normal = normal;
        }

        public Emphasis getEmphasis() {
            return emphasis;
        }

        public void setEmphasis(Emphasis emphasis) {
            this.emphasis = emphasis;
        }

    }

    public class MarkPoint {
        private List<MarkPointData> data;

        public class MarkPointData {
            private String name;
            private Double value;
            private Double xAxis;
            private Double yAxis;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Double getValue() {
                return value;
            }

            public void setValue(Double value) {
                this.value = value;
            }

            public Double getxAxis() {
                return xAxis;
            }

            public void setxAxis(Double xAxis) {
                this.xAxis = xAxis;
            }

            public Double getyAxis() {
                return yAxis;
            }

            public void setyAxis(Double yAxis) {
                this.yAxis = yAxis;
            }

        }

        public List<MarkPointData> getData() {
            return data;
        }

        public void setData(List<MarkPointData> data) {
            this.data = data;
        }

    }

    public class MarkLine {
        private List<MarkLineData> data;

        public class MarkLineData {
            String type;
            String name;

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

        }

        public List<MarkLineData> getData() {
            return data;
        }

        public void setData(List<MarkLineData> data) {
            this.data = data;
        }
    }

    public MarkLine getMarkLine() {
        return markLine;
    }

    public void setMarkLine(MarkLine markLine) {
        this.markLine = markLine;
    }

    public MarkPoint getMarkPoint() {
        return markPoint;
    }

    public void setMarkPoint(MarkPoint markPoint) {
        this.markPoint = markPoint;
    }

    public Boolean getSmooth() {
        return smooth;
    }

}
