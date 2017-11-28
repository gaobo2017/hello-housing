package com.ejavashop.core.excel;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Excel导出用
 *                       
 * @Filename: ExcelManager.java
 * @Version: 1.0
 * @Author: maqiang 马强
 * @Email: 
 *
 */
public class ExcelConfig<T> {

    private String                            fileName;                                         //文件名
    private String                            sheetName;                                        //表格名
    private Integer                           maxLineNum     = 65536;                           //允许导出的最大行数,不能超过65536
    private LinkedHashMap<String, CellConfig> lineConfig;                                       //配置一行，每对key，value代表一个cell。key代表cell的属性名称用于取得用户数据
    private ExcelVersion                      excelVersion   = ExcelVersion.EXECL_VERSION_2003; //文件类型 2003/2007
    private String                            userAgent;                                        //浏览器类型
    private List<T>                           data;                                             //数据
    private boolean                           isShowCodition = true;                            //是否显示导出数据的搜索条件
    private String                            businessErrMsg;                                   //业务错误消息
    private String[]                          searchConditionKeys;                              //搜索条件key
    private String[]                          searchConditionValues;                            //搜索条件value

    /**
     * 配置一个cell
     * @param property
     */
    public void addCell(String property, CellConfig cellCofing) {
        if (null == lineConfig)
            lineConfig = new LinkedHashMap<String, CellConfig>();
        lineConfig.put(property, cellCofing);
    }

    public enum CellType {
        STRING, NUMERIC, DATE, BIGDECIMAL
    }

    public enum ExcelVersion {
        EXECL_VERSION_2003, EXECL_VERSION_2007
    }

    /**
     * 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 表格名
     * @return
     */
    public String getSheetName() {
        return sheetName;
    }

    /**
     * 表格名
     * @return
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * 允许导出的最大行数,不能超过65536
     * @return
     */
    public Integer getMaxLineNum() {
        return maxLineNum;
    }

    /**
     * 允许导出的最大行数,不能超过65536
     * @return
     */
    public void setMaxLineNum(Integer maxLineNum) {
        this.maxLineNum = maxLineNum;
    }

    /**
     * 配置一行，每对key，value代表一个cell。key代表cell的属性名称用于取得用户数据
     * @return
     */
    public LinkedHashMap<String, CellConfig> getLineConfig() {
        return lineConfig;
    }

    /**
     * 配置一行，每对key，value代表一个cell。key代表cell的属性名称用于取得用户数据
     * @return
     */
    public void setLineConfig(LinkedHashMap<String, CellConfig> lineConfig) {
        this.lineConfig = lineConfig;
    }

    /**
     * 文件类型 2003/2007
     * @return
     */
    public ExcelVersion getExcelVersion() {
        return excelVersion;
    }

    /**
     * 文件类型 2003/2007
     * @return
     */
    public void setExcelVersion(ExcelVersion excelVersion) {
        this.excelVersion = excelVersion;
    }

    /**
     * 浏览器类型
     * @return
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * 浏览器类型
     * @return
     */
    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    /**
     * 数据
     * @return
     */
    public List<T> getData() {
        return data;
    }

    /**
     * 数据
     * @return
     */
    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * 是否显示导出数据的搜索条件
     * @return
     */
    public boolean getIsShowCodition() {
        return isShowCodition;
    }

    /**
     * 是否显示导出数据的搜索条件
     * @param isShowCodition
     */
    public void setIsShowCodition(boolean isShowCodition) {
        this.isShowCodition = isShowCodition;
    }

    /**
     * 业务错误消息
     * @return
     */
    public String getBusinessErrMsg() {
        return businessErrMsg;
    }

    /**
     * 业务错误消息
     * @return
     */
    public void setBusinessErrMsg(String businessErrMsg) {
        this.businessErrMsg = businessErrMsg;
    }

    /**
     * 搜索条件key
     * @return
     */
    public String[] getSearchConditionKeys() {
        return searchConditionKeys;
    }

    /**
     * 搜索条件key
     * @return
     */
    public void setSearchConditionKeys(String[] searchConditionKeys) {
        this.searchConditionKeys = searchConditionKeys;
    }

    /**
     * 搜索条件value
     * @return
     */
    public String[] getSearchConditionValues() {
        return searchConditionValues;
    }

    /**
     * 搜索条件value
     * @return
     */
    public void setSearchConditionValues(String[] searchConditionValues) {
        this.searchConditionValues = searchConditionValues;
    }

}
