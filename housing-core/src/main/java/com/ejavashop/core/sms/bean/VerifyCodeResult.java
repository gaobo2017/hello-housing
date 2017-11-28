package com.ejavashop.core.sms.bean;

/**
 * 验证码
 *                       
 * @Filename: VerifyCodeResult.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public class VerifyCodeResult {
    //状态码
    private String code;
    //此次发送的sendid
    private String msg;
    //此次发送的验证码
    private String obj;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

}
