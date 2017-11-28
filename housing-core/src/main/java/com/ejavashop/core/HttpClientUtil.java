package com.ejavashop.core;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * http请求工具类
 *                       
 * @Filename: HttpClientUtil.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public class HttpClientUtil {

    public static String sendGet(String url) {
        HttpGet get = null;
        CloseableHttpResponse resp = null;
        CloseableHttpClient client = null;
        try {
            client = HttpClients.createDefault();
            get = new HttpGet(url);
            resp = client.execute(get);
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                HttpEntity entity = resp.getEntity();
                String content = EntityUtils.toString(entity, "utf-8");
                return content;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resp != null) {
                    resp.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * post请求json
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String sendJsonPost(String url, String json) throws Exception {
        return sendPost(url, json, "application/x-www-form-urlencoded");
    }

    //    public static String sendJsonPost(String url, String content) {
    //        //        return sendPost(url, content, "application/json");
    //        return sendPost(url, content, "application/x-www-form-urlencoded");
    //    }

    public static String sendPost(String url, String content, String type) {
        CloseableHttpClient client = null;
        CloseableHttpResponse resp = null;
        try {
            System.out.println(content);
            client = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-type", type);
            StringEntity entity = new StringEntity(content, ContentType.create(type, "UTF-8"));
            // StringEntity entity = new StringEntity(content);
            post.setEntity(entity);
            resp = client.execute(post);
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode >= 200 && statusCode < 300) {
                String str = EntityUtils.toString(resp.getEntity(), "utf-8");
                return str;
            }
        } catch (UnsupportedCharsetException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (client != null)
                    client.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                if (resp != null)
                    resp.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
