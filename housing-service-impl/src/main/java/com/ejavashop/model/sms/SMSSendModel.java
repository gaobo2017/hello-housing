package com.ejavashop.model.sms;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import com.ejavashop.core.EjavashopConfig;
import com.ejavashop.core.exception.ArgumentException;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.sms.AbstractSMSSender;
import com.ejavashop.core.sms.TemplateSMSSender;
import com.ejavashop.core.sms.VerifyCodeSender;

/**
 * 验证码短信发送<br/>
 * <i>注意:所有短信发送都是异步的</i>
 *                       
 * @Filename: SMSVerifyCodeModel.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
@Component(value = "SMSSendModel")
public class SMSSendModel {
    /**
     * 验证码短信
     */
    private static final int VERIFY_CODE  = 0;
    /**
     * 模板短信
     */
    private static final int TEMPLATE_SMS = 1;

    /**
     * 发送验证码短信
     * @param map
     * @return
     * @throws Exception
     */
    public Object sendVerifyCode(Map<String, Object> map) throws Exception {
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(EjavashopConfig.DEFAULT_RUN_THREAD_NUM);
        Object obj = null;
        if (map.get("mobile") == null) {
            throw new BusinessException("请指定要发送的手机号码");
        }
        obj = pool.submit(new SMSCall(map, VERIFY_CODE)).get();
        //关闭线程池
        pool.shutdown();
        return obj;
    }

    /**
     * 发送模板短信,并返回该短信的执行结果
     * @param map
     * @return
     * @throws Exception
     */
    public Object sendSMSWidthCallable(Map<String, Object> map) throws Exception {
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(EjavashopConfig.DEFAULT_RUN_THREAD_NUM);
        Object obj = null;
        if (map.get("mobile") == null) {
            throw new BusinessException("请指定要发送的手机号码");
        }
        if (map.get("content") == null) {
            throw new BusinessException("请指定短信参数");
        }

        obj = pool.submit(new SMSCall(map, TEMPLATE_SMS)).get();
        //关闭线程池
        pool.shutdown();
        return obj;
    }

    /**
     * 发送模板短信
     * @param map
     * @throws Exception
     */
    public void sendSMS(Map<String, Object> map) throws Exception {
        if (map.get("mobile") == null) {
            throw new BusinessException("请指定要发送的手机号码");
        }
        if (map.get("content") == null) {
            throw new BusinessException("请指定短信参数");
        }
        new Thread(new SMSThread(map, TEMPLATE_SMS)).start();
    }

    class SMSCall implements Callable<Object> {
        private Map<String, Object> param;
        private int                 type;

        SMSCall(Map<String, Object> param, int type) {
            this.param = param;
            this.type = type;
        }

        @Override
        public Object call() throws Exception {
            AbstractSMSSender sender = null;
            if (type == VERIFY_CODE)
                sender = new VerifyCodeSender(param.get("mobile").toString());
            else if (type == TEMPLATE_SMS)
                sender = new TemplateSMSSender(param.get("mobile").toString(), param.get("content")
                    .toString());
            if (sender == null) {
                throw new ArgumentException("参数错误");
            }
            return sender.sendSMS();
        }
    }

    class SMSThread implements Runnable {
        private Map<String, Object> param;
        private int                 type;

        SMSThread(Map<String, Object> map, int type) {
            this.param = map;
            this.type = type;
        }

        @Override
        public void run() {
            AbstractSMSSender sender = null;
            if (type == VERIFY_CODE)
                sender = new VerifyCodeSender(param.get("mobile").toString());
            else if (type == TEMPLATE_SMS)
                sender = new TemplateSMSSender(param.get("mobile").toString(), param.get("content")
                    .toString());
            if (sender == null) {
                throw new ArgumentException("参数错误");
            }
            sender.sendSMS();
        }
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", "13718848029");
        Object obj = new SMSSendModel().sendVerifyCode(map);
        System.out.println(obj);
    }
}
