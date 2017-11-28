package com.ejavashop.core.excel;

import com.ejavashop.core.excel.ExcelConfig.CellType;

public class CellConfig {

    public CellConfig() {
    }

    public CellConfig(String cellTitle) {
        this.cellTitle = cellTitle;
    }

    public CellConfig(String cellTitle, String format) {
        this.cellTitle = cellTitle;
        this.format = format;
    }

    public CellConfig(String cellTitle, String format, CellType cellType) {
        this.cellTitle = cellTitle;
        this.format = format;
        this.cellType = cellType;
    }

    public CellConfig(String cellTitle, String format, CellType cellType, int width) {
        this.cellTitle = cellTitle;
        this.format = format;
        this.cellType = cellType;
        this.width = width;
    }

    private String   cellTitle;                  //列名
    private CellType cellType = CellType.STRING; //列字段类型
    private String   format;                     //格式化
    private int      width;                      //单元格宽度

    /**
     * 列名
     * @return
     */
    public String getCellTitle() {
        return cellTitle;
    }

    /**
     * 列名
     * @return
     */
    public void setCellTitle(String cellTitle) {
        this.cellTitle = cellTitle;
    }

    /**
     * 列字段类型
     * @return
     */
    public CellType getCellType() {
        return cellType;
    }

    /**
     * 列字段类型
     * @return
     */
    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

    /**
     * 格式化
     * @return
     */
    public String getFormat() {
        return format;
    }

    /**
     * 格式化
     * @return
     */
    public void setFormat(String format) {
        this.format = format;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
