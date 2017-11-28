package com.ejavashop.core;

/**
 * <p>
 *     Ajax提交更新token
 *     <ul>
 *         需要调用的js方法是：func.js
 *             <li>getCSRFTokenParam</li>
 *             <li>refrushCSRFToken</li>
 *     </ul>
 * </p>                      
 * @Filename: HttpJsonResultForAjax.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public class HttpJsonResultForAjax<T> extends HttpJsonResult<T> {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8551330383300897900L;

    public HttpJsonResultForAjax(T data, String csrfToken) {
        super.setData(data);
        this.csrfToken = csrfToken;
    }

    public HttpJsonResultForAjax() {
    }

    private String csrfToken = "";

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

}
