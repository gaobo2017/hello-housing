package com.ejavashop.core;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 *                       
 * @Filename: HttpJsonResult.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public class HttpJsonResult<T> implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8637111820477625638L;

    public HttpJsonResult() {

    }

    public HttpJsonResult(T rows) {
        this.rows = rows;
    }

    public HttpJsonResult(String errorMessage) {
        this.success = false;
        this.message = errorMessage;
    }

    private Boolean success = true;

    public Boolean getSuccess() {
        return this.success;
    }

    private T rows;

    public T getRows() {
        return rows == null ? (T) new ArrayList() : rows;
    }

    public void setRows(T rows) {
        this.rows = rows;
    }

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.success = false;
        this.message = message;
    }

    private Integer total = 0;

    public void setTotal(Integer count) {
        this.total = count;
    }

    public Integer getTotal() {
        return this.total;
    }

    private String backUrl;

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(String backUrl) {
        this.backUrl = backUrl;
    }

    private T footer;

    public T getFooter() {
        return footer == null ? (T) new ArrayList() : footer;
    }

    public void setFooter(T footer) {
        this.footer = footer;
    }
}
