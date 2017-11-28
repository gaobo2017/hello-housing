package com.ejavashop.core;

/**
 * TODO：ejavashop 核心配置类，商城启动时首先配置此配置文件<br/>
 * 其中支付有打印的一些报文，用于替换正式账号之后调试使用，调试通过之后建议去掉<br/>
 * 配置常量类，主要配置有支付方式、短信通道、快递100、邮箱等等
 *                       
 * @Filename: EjavashopConfig.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public class EjavashopConfig {

    // -----------邮箱配置start-------------
    /** 邮箱服务提供商host，根据使用的邮箱服务供应商对应填写 */
    public static final String MAIL_SERVER_HOST          = "smtp.ym.163.com";
    /** 邮箱服务提供商port，根据使用的邮箱服务供应商对应填写 */
    public static final String MAIL_SERVER_PORT          = "25";
    /** 发送邮件的邮箱地址 */
    public static final String SEND_EMAIL_NAME           = "ejavashop@ejavashop.com";
    /** 发送邮件的邮箱密码 */
    public static final String SEND_EMAIL_PASSWORD       = "";

    // -----------邮箱配置end---------------

    // -----------JAVA定时器执行配置start-------------
    //是web项目的定时器，主要对首页进行静态化更新的，可以根据自己的业务逻辑进行调整
    /** java定时器第一次执行时间（毫秒） */
    public static final long   TIME_DELAY                = 60000L;
    /** java定时器执行间隔时间（毫秒） */
    public static final long   TIME_PERIOD               = 600000L;

    public static final long   ACCESS_TOKEN_DELAY        = 0;
    public static final long   ACCESS_TOKEN_PERIOD       = 1000 * 60 * 60 * 2;
    // -----------JAVA定时器执行配置end---------------

    /** 快递100授权key */
    public static String       KUAIDI100_KEY             = "989f7ca4007d02b0";

    /** 国信互联短信发送URL(向短信供应商申请) 其中URL中包括用户名和密码 */
    public static String       SEND_SMS_URL              = "";

    /**
     * 多订单支付的分隔符，注意不能使用 “_” 、 “-” 和一些特殊字符，否则银联不能支付成功，建议2到5位的字母，数字可能会解析错误
     */
    public final static String ORDER_SEPARATOR           = "aa";

    ///////alipay start////////////
    /**
     * 支付宝，显示订单名称，PC和Mobile公用，一般设置商城的名称
     */
    public final static String ALIPAY_ALL_SUBJECT        = "ejavashop";

    /**
     * HTML5 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
     */
    public static String       ALIPAY_MOBILE_PARTNER     = "2088121746953956";

    /**
     * HTML5 支付宝商户的私钥 HTML5支付采用RSA加密方式
     */
    public static String       ALIPAY_MOBILE_PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKh6HkfwcsHIz69VUYPLbZS9z3IBkIAFL5BdMEvXybWDwzm2eFD6a7nvinqfzTyWLzBHvisBMyqR0a+zOBe3Q7Pdb6eNK5kYc4loIraSxn0PvqeduSa2KLtqyeg12ndA0+9RpMM0xAPhhVGFbsA1z1DCi1o+j1i3DgyiDXQPMuzjAgMBAAECgYACzKyuI1e00qJo1nEZUIsMmq7UxSPijLOCoZnI2NgYNQ1MazTKm66ok0toyDj/1bsJAVgunMF95phmZAL9meT9FCPP6tG/tMGVMAh7BdakaN8rc7ccw2Zo8CUbizGHXIazosF6g53Wl4dy97+fECBdUE82k04UpoCS1AkCUfoQUQJBAPXVk7ajaMqu46JF04d1UaiVWSBTDswKjFGrs0YnEkr2A3VBK6AxjQwzjVBj/pPV8VmiLp+ogRyBWnHidNO1nAkCQQCvcaFxDoeiVA7xbiboIlSn6B0mhbdpfU+kvl49Qm0s3Dza86Gqli0q4M/qJhHahwVzmb23qnAIR0U0M9iu0pSLAkBtKuYIsfJEJ3vgwN9ZhQi5M3E7wIaOp5R+ZCAdZBZkgXExrbogzkBTjcUQUQdpQRWHd1T7A4oqTWsLrcOxjDF5AkBvQndoNBetyPLlBr7jrYnUJW3/FQN4gYB1cEhhPvPvZAevrptSjAQ+0ezoh5YA6Gl7ov7eL9b2WQQ5E7wROff7AkA5R2sHOdqYAwfWMUxJzFIZ0ZYe5JsWt5vn7dfIJ3DLWIWllctHJl4i5Xg4+z+PdQ8Sv33O04qTBGHu89G/d7hy";

    /**
     * HTML5 支付宝的公钥，无需修改该值， HTML5支付采用RSA加密方式
     */
    public static String       ALIPAY_MOBILE_PUBLIC_KEY  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    /**
     * PC 支付宝：合作身份者ID，以2088开头由16位纯数字组成的字符串
     */
    public static String       ALIPAY_PC_PARTNER         = "2088121746953956";

    /**
     * PC 支付宝，收款支付宝账号，一般情况下收款账号就是签约账号
     */
    public static String       ALIPAY_PC_SELLER_EMAIL    = "wangpeng@ejavashop.com";

    /**
     * PC 支付宝，商户的私钥，PC支付采用MD5 方式
     */
    public static String       ALIPAY_PC_KEY             = "kng63xz4dfhew7j61vzwul007ogzaplb";
                                                         ///////alipay end///////////

    ////////银联支付 start 根据业务需求acp_sdk.properties中key 和 value 值进行替换/////////////
    /**
     * 银联支付，用户ID
     */
    public final static String UNIONPAY_MERID            = "777290058126056";

    /**
     * 银联支付，请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节  
     */
    public final static String UNIONPAY_REQRESERVED      = "易写科技";

    /** 配置文件中的前台URL常量. 银联支付中对应的文件：acpsdk.frontTransUrl */
    public static final String SDK_FRONT_URL             = "https://101.231.204.80:5000/gateway/api/frontTransReq.do";
    /** 配置文件中的后台URL常量. 银联支付中对应的文件：acpsdk.backTransUrl*/
    public static final String SDK_BACK_URL              = "https://101.231.204.80:5000/gateway/api/backTransReq.do";
    /** 配置文件中的单笔交易查询URL常量. 银联支付中对应的文件：acpsdk.singleQueryUrl*/
    public static final String SDK_SIGNQ_URL             = "https://101.231.204.80:5000/gateway/api/queryTrans.do";
    /** 配置文件中的批量交易查询URL常量. 银联支付中对应的文件：acpsdk.batchQueryUrl*/
    public static final String SDK_BATQ_URL              = "acpsdk.batchQueryUrl";
    /** 配置文件中的批量交易URL常量. 银联支付中对应的文件：acpsdk.batchTransUrl*/
    public static final String SDK_BATTRANS_URL          = "https://101.231.204.80:5000/gateway/api/batchTransReq.do";
    /** 配置文件中的文件类交易URL常量. 银联支付中对应的文件：acpsdk.fileTransUrl*/
    public static final String SDK_FILETRANS_URL         = "https://101.231.204.80:9080/";
    /** 配置文件中的有卡交易URL常量. 银联支付中对应的文件：acpsdk.cardTransUrl*/
    public static final String SDK_CARD_URL              = "acpsdk.cardTransUrl";
    /** 配置文件中的app交易URL常量. 银联支付中对应的文件：acpsdk.appTransUrl*/
    public static final String SDK_APP_URL               = "acpsdk.appTransUrl";

    /** 配置文件中签名证书路径常量. 银联支付中对应的文件：acpsdk.signCert.path*/
    //    public static final String SDK_SIGNCERT_PATH         = "/Users/wpjava/Downloads/ACPSample_B2C/src/assets/700000000000001_acp.pfx";
    public static final String SDK_SIGNCERT_PATH         = "C:/Users/Administrator/Desktop/assets/700000000000001_acp.pfx";
    /** 配置文件中签名证书密码常量. 银联支付中对应的文件：acpsdk.signCert.pwd*/
    public static final String SDK_SIGNCERT_PWD          = "000000";
    /** 配置文件中签名证书类型常量. 银联支付中对应的文件：acpsdk.signCert.type*/
    public static final String SDK_SIGNCERT_TYPE         = "PKCS12";
    /** 配置文件中密码加密证书路径常量. 银联支付中对应的文件：acpsdk.encryptCert.path*/
    //    public static final String SDK_ENCRYPTCERT_PATH      = "/Users/wpjava/Downloads/ACPSample_B2C/src/assets/acp_test_enc.cer";
    public static final String SDK_ENCRYPTCERT_PATH      = "C:/Users/Administrator/Desktop/assets/acp_test_enc.cer";
    /** 配置文件中磁道加密证书路径常量. 银联支付中对应的文件：acpsdk.encryptTrackCert.path*/
    public static final String SDK_ENCRYPTTRACKCERT_PATH = "acpsdk.encryptTrackCert.path";
    /** 配置文件中验证签名证书目录常量. 银联支付中对应的文件：acpsdk.validateCert.dir*/
    //    public static final String SDK_VALIDATECERT_DIR      = "/Users/wpjava/Downloads/ACPSample_B2C/src/assets/";
    public static final String SDK_VALIDATECERT_DIR      = "C:/Users/Administrator/Desktop/assets/";

    /** 配置文件中是否加密cvn2常量. 银联支付中对应的文件：acpsdk.cvn2.enc*/
    public static final String SDK_CVN_ENC               = "acpsdk.cvn2.enc";
    /** 配置文件中是否加密cvn2有效期常量. 银联支付中对应的文件：acpsdk.date.enc*/
    public static final String SDK_DATE_ENC              = "acpsdk.date.enc";
    /** 配置文件中是否加密卡号常量. 银联支付中对应的文件：acpsdk.pan.enc*/
    public static final String SDK_PAN_ENC               = "acpsdk.pan.enc";
    /** 配置文件中证书使用模式 银联支付中对应的文件：true*/
    public static final String SDK_SINGLEMODE            = "true";
                                                         ////////银联支付 end//////////////

    //////////微信支付 start////////////////////
    /**
     * 微信支付显示的订单名称
     */
    public static final String WEIXIN_ORDER_NAME         = "ejavashop订单";
                                                         //////////微信支付 end/////////////////////

    //------------------------短信发送相关参数bg--------------------------//
    /**
     * 默认固定线程数
     */
    public static final int    DEFAULT_RUN_THREAD_NUM    = 2;

    /**
     * 用户每日最多获取的短信验证码数量
     */
    public static final int    SMS_MAX_GIVEN_NUM         = 5;

    public static final String SMS_URL                   = "http://dx.ipyy.net/smsJson.aspx";
    public static final String SMS_VERIFY_CODE           = "您好，您已成功注册,您的验证码是@【易写科技】";

    //------------------------短信发送相关参数ed--------------------------//

    //-------------------------h5微信支付 bg-------------------------//
    /**开发者测试验证token*/
    public static final String WXPAY_VALIDATE_TOKEN      = "dawawang";
    /**微信支付商户号*/
    public static final String WXPAY_APPID               = "wx6985c6d0181b5855";
    /**应用密钥*/
    public static final String WXPAY_APPSECRET           = "0579b7fb727284a96d832573ffa875cf";
    /***/
    public static final String WXPAY_PARTNER             = "1355844702";
    /***/
    public static final String WXPAY_PARTNER_KEY         = "tZsVc95OwVmx3sVeriNfZfortM4Ijqnl";
    //    /**网站域名*/
    //    public static final String WXPAY_WEBSIT              = "http://www.ejavashop.com";
    /**网页授权地址，无需修改*/
    public static final String WXPAY_OAUTH2_URL          = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    /**获取网页授权access-token地址,无需修改*/
    public static final String WXPAY_OAUTH2_TOKEN        = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    /**创建订单,微信内部操作,无需修改*/
    public static final String WXPAY_CREATE_ORDER_URL    = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**微信访问token,无需修改*/
    public static final String WXPAY_ACCESS_TOKEN        = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
    //    /**支付成功后的通知地址*/
    //    public static final String WXPAY_NOTIFY_URL          = "http://www.ejavashop.com/wxpay/notify.html";
    /**
     * 应用授权作用域,有两种:
     * 1、snsapi_base （不弹出授权页面，直接跳转，只能获取用户openid），
     * 2、snsapi_userinfo （弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
     */
    public static final String WXPAY_SCOPE               = "snsapi_base";
    /**重定向后会带上state参数，开发者可以填写a-zA-Z0-9的参数值，最多128字节*/
    public static final String WXPAY_STATE               = "ejavashop";
    /**支付成功后,由公共号发送此消息给用户*/
    public static final String WXPAY_PAYSUCCESS_MESSAGE  = "您的订单:[ORERID]已经支付成功！欢迎关注我们，祝您生活愉快SUFFIX";
    //-------------------------h5微信支付 ed-------------------------//
}
