package com.ejavashop.core.sms;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 短信发送
 *                       
 * @Filename: AbstractSMSSender.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public abstract class AbstractSMSSender {
    /**
     * 短信请求url
     */
    protected static final String URL      = "url";
    /**
     * 授权key
     */
    protected static final String ACCOUNT  = "AA00269";
    /**
     * 密钥
     */
    protected static final String PASSWORD = "AA0026974";

    protected static final String MOBILE   = "mobile";

    /**
     * 全部被叫号码<br>
     * 短信发送的目的号码.多个号码之间用半角逗号隔开 
     */
    protected String              mobile   = "";
    /**
     * 短信内容
     */
    protected String              content  = "";

    /**
     * 能用发送短信方法
     * @return
     */
    public String sendSMS() {
        CloseableHttpClient httpclient = null;
        try {
            httpclient = HttpClients.createDefault();
            Map<String, String> param = setSMSParamMap();

            String url = param.get("url");
            HttpPost httpPost = new HttpPost(url);

            // 设置请求的header
            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // 设置请求的的参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            removeAuthParam(param);

            Iterator<Entry<String, String>> entrys = param.entrySet().iterator();
            while (entrys.hasNext()) {
                Entry<String, String> entry = entrys.next();
                String key = entry.getKey();
                String value = entry.getValue();
                // 参数 jsonArray形式
                nvps.add(new BasicNameValuePair(key, value));
            }
            nvps.add(new BasicNameValuePair("account", ACCOUNT));
            nvps.add(new BasicNameValuePair("password", PASSWORD));
            nvps.add(new BasicNameValuePair("action", "send"));

            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            // 执行请求
            HttpResponse response = httpclient.execute(httpPost);
            return EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;

    }

    /**
     * 移除非内容参数
     * @param param
     */
    private void removeAuthParam(Map<String, String> param) {
        param.remove(URL);
    }

    /**
     * 短信发送需要的参数,对应短信模板的替换变量,短信发送子类需实现此方法
     * @return
     */
    protected abstract Map<String, String> setSMSParamMap();
}
