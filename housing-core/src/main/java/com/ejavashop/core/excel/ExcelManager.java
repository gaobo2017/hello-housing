package com.ejavashop.core.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;

import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.excel.ExcelConfig.CellType;
import com.ejavashop.core.excel.ExcelConfig.ExcelVersion;
import com.ejavashop.core.exception.BusinessException;

public class ExcelManager {

    public static final Integer MAX_CLUMN_NUM = 256;   //列数最大限制
    public static final Integer MAX_LINE_NUM  = 65536; //允许导出的最大行数
    public static final String  NULL_STR      = "";

    /**
     * 导出Excel
     * @param response
     * @param config
     * @throws Exception
     */
    public static <T extends Object> void export(HttpServletResponse response,
                                                 ExcelConfig<T> config) {
        //参数合法性检查
        checkParam(config);
        String errMsg = config.getBusinessErrMsg();
        if (StringUtil.isEmpty(errMsg)) {
            errMsg = bunsinessCheck(config);
        }
        setDownloadableHeader(response, config, errMsg);
        if (StringUtil.isEmpty(errMsg)) {
            exportData(response, config);
        }
    }

    /**
     * 导出
     * @param response
     * @param config
     */
    private static <T extends Object> void exportData(HttpServletResponse response,
                                                      ExcelConfig<T> config) {
        Workbook wb = getWorkbook(config);
        String[] titles = new String[config.getLineConfig().size()];
        String[] properties = new String[config.getLineConfig().size()];
        Map<String, CellStyle> styles = createStylesAndTitles(wb, titles, properties, config);
        Sheet sheet = wb.createSheet(config.getSheetName());
        int rowNum = 0;//行号
        rowNum = exportTime(wb, sheet, rowNum);
        if (config.getIsShowCodition()) {
            rowNum = exportSearchCondition(wb, sheet, config.getSearchConditionKeys(),
                config.getSearchConditionValues(), rowNum);
        }
        rowNum = exportEmptyRow(sheet, rowNum);
        rowNum = exportHeaderRow(wb, sheet, titles, rowNum);
        exportBody(sheet, config, styles, properties, rowNum);
        for (int i = 0; i < properties.length; i++) {
            int cellWidth = config.getLineConfig().get(properties[i]).getWidth();
            if (cellWidth != 0) {
                sheet.setColumnWidth(i, cellWidth);
            } else {
                sheet.autoSizeColumn(i);
            }

        }
        doExport(response, wb);
    }

    /**
     * 导出时间
     * @param sheet
     * @param rowNum
     * @return
     */
    private static int exportTime(Workbook wb, Sheet sheet, int rowNum) {
        Row row = sheet.createRow(rowNum++);
        Cell keyCell = row.createCell(0);
        keyCell.setCellValue("导出时间");
        keyCell.setCellStyle(getHeaderStyle(wb));
        Cell valueCell = row.createCell(1);
        SimpleDateFormat dataFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        valueCell.setCellValue(dataFormater.format(new Date()));
        //CellStyle valueCellStyle = getDefaultDateTypeStyle(wb);
        //valueCell.setCellStyle(valueCellStyle);
        return rowNum;
    }

    /**
     * 取得默认的时间类型style
     * @param wb
     * @return
     */
    private static CellStyle getDefaultDateTypeStyle(Workbook wb) {
        CellStyle valueCellStyle = wb.createCellStyle();
        DataFormat df = wb.createDataFormat();
        valueCellStyle.setDataFormat(df.getFormat("yyyy-MM-dd HH:mm:ss"));
        valueCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        return valueCellStyle;
    }

    /**
     * 导出搜索条件
     * @param wb
     * @param sheet
     * @param searchConditionKeys
     * @param searchConditionValues
     * @param rowNum
     * @return
     */
    private static int exportSearchCondition(Workbook wb, Sheet sheet, String[] searchConditionKeys,
                                             String[] searchConditionValues, int rowNum) {
        if (searchConditionKeys != null && searchConditionKeys.length > 0
            && searchConditionValues != null && searchConditionValues.length > 0) {
            for (int i = 0; i < searchConditionKeys.length; i++) {
                Row row = sheet.createRow(rowNum++);
                exportSearchCondition(wb, row, searchConditionKeys[i], searchConditionValues[i]);
            }
        }
        return rowNum;
    }

    /**
     * 导出搜索条件
     * @param wb
     * @param row
     * @param key
     * @param value
     */
    private static void exportSearchCondition(Workbook wb, Row row, String key, String value) {
        Cell keyCell = row.createCell(0);
        keyCell.setCellValue(notnull(key));
        keyCell.setCellStyle(getHeaderStyle(wb));

        Cell valueCell = row.createCell(1);
        CellStyle valueCellStyle = wb.createCellStyle();
        //valueCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        valueCell.setCellStyle(valueCellStyle);
        valueCell.setCellValue(notnull(value));
    }

    /**
     * 导出空行
     * @param sheet
     * @param rowNum
     * @return
     */
    private static int exportEmptyRow(Sheet sheet, int rowNum) {
        sheet.createRow(rowNum++);
        return rowNum;
    }

    private static String notnull(String str) {
        if (str == null)
            str = NULL_STR;
        return str;
    }

    /**
     * 导出数据体
     * @param sheet
     * @param config
     * @param styles
     * @param properties
     */
    private static <T extends Object> int exportBody(Sheet sheet, ExcelConfig<T> config,
                                                     Map<String, CellStyle> styles,
                                                     String[] properties, int rowNum) {
        List<T> data = config.getData();
        for (int i = 0; i < data.size(); i++) {
            Row row = sheet.createRow(rowNum++);
            exportDataRow(row, data.get(i), properties, styles, config);
        }

        return rowNum;
    }

    /**
     * 导出数据体的一行
     * @param row
     * @param t
     * @param properties
     * @param styles
     * @param config
     */
    private static <T extends Object> void exportDataRow(Row row, T t, String[] properties,
                                                         Map<String, CellStyle> styles,
                                                         ExcelConfig<T> config) {
        for (int i = 0; i < properties.length; i++) {
            Cell cell = row.createCell(i);
            CellStyle style = styles.get(properties[i]);
            if (style != null)
                cell.setCellStyle(style);
            //Object originalValue = ReflectionUtil.invokeGetterMethod(t, properties[i]);//JDK1.6
            Object originalValue = invokeGetterMethod(t, properties[i]);//JDK1.7
            if (originalValue == null) {
                cell.setCellValue(NULL_STR);
            } else {
                CellConfig cellConfig = config.getLineConfig().get(properties[i]);
                if (cellConfig.getCellType() == CellType.DATE) {
                    cell.setCellValue((Date) originalValue);
                } else if (cellConfig.getCellType() == CellType.NUMERIC) {
                    cell.setCellValue(ConvertUtil.toDouble(originalValue, 0.0));
                } else if (cellConfig.getCellType() == CellType.BIGDECIMAL) {
                    cell.setCellValue(originalValue.toString());
                } else {
                    cell.setCellValue(originalValue.toString());
                }
            }
        }
    }

    /**
     * 导出头
     * @param sheet
     * @param titles
     */
    private static int exportHeaderRow(Workbook wb, Sheet sheet, String[] titles, int rowNum) {
        Row headerRow = sheet.createRow(rowNum++);
        for (int i = 0; i < titles.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(notnull(titles[i]));
            cell.setCellStyle(getHeaderStyle(wb));
        }
        return rowNum;
    }

    /**
     * 取得头样式
     * @param wb
     * @return
     */
    private static CellStyle getHeaderStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        Font headerFont = wb.createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(headerFont);
        return style;
    }

    /**
     * 取得cell的style和title数据和导出数据属性
     * @param wb
     * @param titles
     * @param config
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private static Map<String, CellStyle> createStylesAndTitles(Workbook wb, String[] titles,
                                                                String[] properties,
                                                                ExcelConfig config) {
        Map<String, CellConfig> lineConfig = config.getLineConfig();
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        int i = 0;
        for (String key : lineConfig.keySet()) {
            CellConfig cc = lineConfig.get(key);
            titles[i] = cc.getCellTitle();
            properties[i] = key;
            CellStyle style = getStyle(cc, wb);
            if (style != null)
                styles.put(key, style);
            i++;
        }
        return styles;
    }

    /**
     * 根据cell配置取得style
     * @param cc
     * @param wb
     * @return
     */
    private static CellStyle getStyle(CellConfig cc, Workbook wb) {
        CellStyle style = wb.createCellStyle();
        short align = getAlignment(cc.getCellType());
        style.setAlignment(align);
        style.setWrapText(true);
        Short dataFormat = getBodyDataFormat(cc.getCellType(), cc.getFormat(), wb);
        if (dataFormat != null) {
            style.setDataFormat(dataFormat.shortValue());
        }
        return style;
    }

    /**
     * 取得体数据的cell的format
     * @param cellType
     * @param format
     * @param wb
     * @return
     */
    private static Short getBodyDataFormat(CellType cellType, String format, Workbook wb) {
        DataFormat df = wb.createDataFormat();
        if (!StringUtil.isEmpty(format)) {
            return df.getFormat(format);
        }
        if (cellType == CellType.DATE) {
            return df.getFormat("yyyy-MM-dd HH:mm:ss");
        } else if (cellType == CellType.NUMERIC) {
            return null;
        } else if (cellType == CellType.BIGDECIMAL) {
            return null;
        }
        return null;
    }

    /**
     * 取得横向style
     * @param cellType
     * @return
     */
    private static short getAlignment(CellType cellType) {
        return cellType == CellType.NUMERIC ? CellStyle.ALIGN_RIGHT : CellStyle.ALIGN_CENTER;
    }

    /**
     * 取得工作簿
     * @param manager
     * @return
     */
    private static <T extends Object> Workbook getWorkbook(ExcelConfig<T> manager) {
        Workbook wb = null;
        if (manager.getExcelVersion() == ExcelVersion.EXECL_VERSION_2003)
            wb = new HSSFWorkbook();
        else
            wb = new XSSFWorkbook();
        return wb;
    }

    /**
     * 导出
     * @param response
     * @param wb
     */
    private static void doExport(HttpServletResponse response, Workbook wb) {
        try {
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 业务检查
     * @param manager
     * @param <T>
     * @return
     */
    private static <T extends Object> String bunsinessCheck(ExcelConfig<T> manager) {
        if (manager.getData().size() > manager.getMaxLineNum()) {
            return "数据集合中的数据条数大于允许条数上限";
        }

        if (manager.getData() == null || manager.getData().size() == 0) {
            return "没有符合条件的数据";
        }
        return null;
    }

    /**
     * 参数合法性检查
     * @param manager
     */
    private static <T extends Object> void checkParam(ExcelConfig<T> manager) {
        Assert.notNull(manager, "Property 'manager' is required.");
        if (manager.getExcelVersion() == null)
            throw new BusinessException("文件版本不能为空");
        if (StringUtil.isEmpty(manager.getFileName()))
            throw new BusinessException("文件名不能为空");
        if (manager.getLineConfig() == null || manager.getLineConfig().size() == 0)
            throw new BusinessException("行配置不能为空");
        if (manager.getLineConfig().size() > MAX_CLUMN_NUM)
            throw new BusinessException("行配置不能大于" + MAX_CLUMN_NUM);
        if (manager.getMaxLineNum() == null)
            throw new BusinessException("最大行数配置不能为空");
        if (manager.getMaxLineNum() > MAX_LINE_NUM)
            throw new BusinessException("最大行数配置不能大于" + MAX_LINE_NUM);
        if (StringUtil.isEmpty(manager.getSheetName()))
            throw new BusinessException("表格名不能为空");
        if (StringUtil.isEmpty(manager.getUserAgent()))
            throw new BusinessException("浏览器类型不能为空");
    }

    /**
     * 设置让浏览器弹出下载对话框的Header.
     * 兼容各个浏览器下载
     */
    private static <T extends Object> void setDownloadableHeader(HttpServletResponse response,
                                                                 ExcelConfig<T> manager,
                                                                 String businessErrMsg) {
        try {
            String busiSuccess = "true";
            if (StringUtil.isEmpty(businessErrMsg)) {
                response.setCharacterEncoding("GB2312");
                String userAgent = manager.getUserAgent();
                String fileName = manager.getFileName() + ".xls";
                if (manager.getExcelVersion() == ExcelVersion.EXECL_VERSION_2007)
                    fileName += "x";
                if (userAgent.toLowerCase().indexOf("firefox") > 0) {
                    fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                } else if (userAgent.toUpperCase().indexOf("MSIE") > 0) {
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                } else if (userAgent.toUpperCase().indexOf("CHROME") > 0
                           || userAgent.toUpperCase().indexOf("SAFARI") > 0) {
                    fileName = URLEncoder.encode(fileName, "UTF-8");
                }
                response.reset();
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "online; filename=\"" + fileName + "\"");
                response.setHeader("Connetion", "close");
            } else {
                Cookie msgCookie = new Cookie("busiErrMsg",
                    URLEncoder.encode(businessErrMsg, "utf-8"));
                msgCookie.setPath("/");
                response.addCookie(msgCookie);
                busiSuccess = "false";
            }
            Cookie fileDownloadCookie = new Cookie("fileDownload", busiSuccess);
            fileDownloadCookie.setPath("/");
            response.addCookie(fileDownloadCookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <E> E invokeGetterMethod(Object object, String fieldName) {
        String method = "get" + toFirstLetterUpperCase(fieldName);
        return invokeMethod(object, method, null, null);
    }

    public static <E> E invokeMethod(Object object, String methodName, Object[] argValues,
                                     Class<?>[] argTypes) {
        try {
            return (E) getAccessibleMethod(object, methodName, argTypes).invoke(object, argValues);
        } catch (ClassCastException e) { // must catch ClassCastException
            throw e;
        } catch (NullPointerException e) { // must catch NullPointerException
            throw e;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static Method getAccessibleMethod(Object object, String methodName, Class<?>... types) {
        Class<?> entityClass = object instanceof Class ? (Class<?>) object : object.getClass();
        while (entityClass != null) {
            try {
                Method target = entityClass.getDeclaredMethod(methodName, types);
                target.setAccessible(true);
                return target;
            } catch (Throwable e) { /* ignore the thrown exception */
            }
            /* go to the super class */
            entityClass = entityClass.getSuperclass();
        }
        return null;
    }

    public static String toFirstLetterUpperCase(String source) {
        return source.substring(0, 1).toUpperCase() + source.substring(1);
    }
}
