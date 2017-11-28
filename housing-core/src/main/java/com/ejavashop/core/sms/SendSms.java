package com.ejavashop.core.sms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.ejavashop.core.EjavashopConfig;

@Deprecated
public class SendSms {

    protected static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                     .getLogger(SendSms.class);

    public Boolean sendSms(String mobile, String content) throws UnsupportedEncodingException {
        // 发送短信的方法，需要运营商提供请求URL
        StringBuilder sbURL = new StringBuilder();
        sbURL.append(EjavashopConfig.SEND_SMS_URL);
        sbURL.append(mobile);
        sbURL.append("&Content=");
        sbURL.append(URLEncoder.encode(content, "GBK"));
        sbURL.append("&ContentType=8");
        String code = queryXml(sbURL.toString());
        if (!"00".equals(code) && !"01".equals(code) && !"03".equals(code)) {
            log.error("请求：" + sbURL.toString() + "发生错误，错误代码code：" + code);
            return false;
        }

        return true;
    }

    /**
     * 解析xml文档
     * */
    private String queryXml(String xmlString) {
        String code = "";
        try {
            //得到DOM解析器的工厂实例
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            //从DOM工厂中获得DOM解析器
            DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
            //把要解析的xml文档读入DOM解析器
            Document doc = dbBuilder.parse(xmlString);
            //得到文档名称为Student的元素的节点列表
            NodeList nList = doc.getElementsByTagName("response");
            //遍历该集合，显示结合中的元素及其子元素的名字
            for (int i = 0; i < nList.getLength(); i++) {
                Element node = (Element) nList.item(i);
                code = node.getElementsByTagName("code").item(0).getFirstChild().getNodeValue();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code;
    }
}
