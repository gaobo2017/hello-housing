package com.ejavashop.core;

public class ConstantsEJS {

    public final static String NAMESPACE_MOBILEMALL_WEB_MEMBER         = "ejavashop:b2b2c:web:member";
    public final static String NAMESPACE_MOBILEMALL_WEB_MEMBER_SESSION = NAMESPACE_MOBILEMALL_WEB_MEMBER
                                                                         + ":sessionid:";

    /**
     * 渠道：1、通用
     */
    public static final int    CHANNEL_1                               = 1;
    /**
     * 渠道：2、PC
     */
    public static final int    CHANNEL_2                               = 2;
    /**
     * 渠道：3、mobile
     */
    public static final int    CHANNEL_3                               = 3;

    /**
     * 数据来源 1、PC
     */
    public static final int    PC_MOBILE_PC                            = 1;

    /**
     * 数据来源 2、mobile
     */
    public static final int    PC_MOBILE_MOBILE                        = 2;

    /**
     * 数据操作类型：C、新建
     */
    public static final String DATA_OPT_TYPE_C                         = "C";

    /**
     * 数据操作类型：U、修改
     */
    public static final String DATA_OPT_TYPE_U                         = "U";

    /**
     * 数据操作类型：D、删除
     */
    public static final String DATA_OPT_TYPE_D                         = "D";

    /**
     * PC首页缓存KEY
     */
    public static final String PC_INDEX_HTML_CACHE                     = "pcIndexCache";

    /**
     * 移动端首页缓存KEY
     */
    public static final String M_INDEX_HTML_CACHE                      = "mIndexCache";

    /**
     * 微信支付access-token
     */
    public static final String WX_ACCESS_TOKEN                         = "wx_access_token";

    /**
     * 默认显示5
     */
    public static final int    DEFAULT_PAGE_SIZE_10                    = 10;

    /**
     * 默认显示页数20
     */
    public static final int    DEFAULT_PAGE_SIZE                       = 20;
    public static final String SERVICE_RESULT_CODE_SYSERROR            = "syserror";
    public static final String SERVICE_RESULT_EXCEPTION_SYSERROR       = "服务异常，请联系系统管理员。";

    /**系统资源管理-商家管理根结点，与数据库初始化数据（system_resources）相关，如果修改数据库请修改此值 */
    public static final int    SYSTEM_RESOURCE_ROOT                    = 28;
    /**系统资源管理-系统管理根结点，与数据库初始化数据（system_resources）相关，如果修改数据库请修改此值 */
    public static final int    SELLER_RESOURCE_ROOT                    = 27;

    /** 来源：1、pc；2、H5；3、Android；4、IOS */
    public final static int    SOURCE_1_PC                             = 1;
    /** 来源：1、pc；2、H5；3、Android；4、IOS */
    public final static int    SOURCE_2_H5                             = 2;
    /** 来源：1、pc；2、H5；3、Android；4、IOS */
    public final static int    SOURCE_3_ANDROID                        = 3;
    /** 来源：1、pc；2、H5；3、Android；4、IOS */
    public final static int    SOURCE_4_IOS                            = 4;

    /**
     * 搜索配置ID，系统初始化数据，ID不变
     */
    public final static int    SEARCHSETTINGID                         = 1;

    /**
     * 系统配置ID，系统初始化数据，ID不变
     */
    public static final int    CONFIG_ID                               = 1;

    /** 验证码key */
    public static final String VERIFY_NUMBER_NAME                      = "verify_number";

    /**
     * 前台用户订单列表显示每页条数
     */
    public static final int    DEFAULT_ORDER_PAGE_SIZE                 = 5;

    /**
     * 前台店铺首页显示 推荐商品个数
     */
    public static final int    DEFAULT_STORES_PRODUCT_SIZE             = 4;

    /**
     *  是否使用 1 使用
     */
    public final static int    USE_YN_Y                                = 1;

    /**
     *  是否使用 0 未使用
     */
    public final static int    USE_YN_N                                = 0;

    /**
     * 和商城的变量同步，存放商家上传图片内容，以seller/商家ID
     */
    public final static String SELLER_ID                               = "seller_id";

    /**
     * 商家申请上传图片
     */
    public final static String SELLER_APPLY                            = "seller_apply";

    /**
     * 和商城的变量同步，存放所有品牌信息，由平台上传
     */
    public final static String BRAND                                   = "brand";

    /**
     * 前台广告
     */
    public final static String AD_FRONT                                = "ad_front";

    /**
     * 移动端广告存放位置
     */
    public final static String AD_MOBILE                               = "ad_mobile";

    /**
     * 快递公司
     */
    public final static String COURIER_COMPANY                         = "courier_company";

    /**
     * 和商城的变量同步，存放前台用户上传的所有图片信息
     */
    public final static String USERFRONT                               = "userfront";

    /**
     * 和商城的变量同步，存放商家上传商品的小图片
     */
    public final static String PRODUCT_LITTLE                          = "little";

    /**
     * 和商城的变量同步，存放商家上传商品的中图片
     */
    public final static String PRODUCT_MIDDLE                          = "middle";

    /**
     * 和商城的变量同步，商品图片 小图片 200＊200
     */
    public final static int    PRODUCT_LITTLE_IMAGE                    = 200;

    /**
     * 和商城的变量同步，商品图片 小图片 400＊400
     */
    public final static int    PRODUCT_MIDDLE_IMAGE                    = 400;

    /**
     * 和商城的变量同步，商品图片 小图片 800＊800
     */
    public final static int    PRODUCT_BIG_IMAGE                       = 800;

    /** 调用支付宝接口 操作类型  ；   "orders": 订单支付 ；*/
    public final static String ALI_PAY_TYPE_ORDERS                     = "orders";

    /** 会员经验值和积分规则表（只有两条初始化数据）：经验值规则ID：1 */
    public final static int    MEMBER_RULE_GRADE_ID                    = 1;

    /** 会员经验值和积分规则表（只有两条初始化数据）：经验值规则key，用于存入ServletContext的key */
    public final static String MEMBER_RULE_GRADE_KEY                   = "member_rule_grade";

    /** 会员经验值和积分规则表（只有两条初始化数据）：积分规则ID:2 */
    public final static int    MEMBER_RULE_INTEGRAL_ID                 = 2;

    /** 会员经验值和积分规则表（只有两条初始化数据）：积分规则key，用于存入ServletContext的key */
    public final static String MEMBER_RULE_INTEGRAL_KEY                = "member_rule_integral";

    /** 会员等级配置表（只有一条初始化数据）：ID */
    public final static int    MEMBER_GRADE_CONFIG_ID                  = 1;

    /** 会员等级配置key，用于存入ServletContext的key */
    public final static String MEMBER_GRADE_CONFIG_KEY                 = "member_grade_config";

    /** 会员退款申请表state，申请状态：1、提交申请 */
    public final static int    MEMBER_DRAWING_STATE_1                  = 1;

    /** 会员退款申请表state，申请状态：2、申请通过 */
    public final static int    MEMBER_DRAWING_STATE_2                  = 2;

    /** 会员退款申请表state，申请状态：3、已打款 */
    public final static int    MEMBER_DRAWING_STATE_3                  = 3;

    /** 会员退款申请表state，申请状态：4、处理失败 */
    public final static int    MEMBER_DRAWING_STATE_4                  = 4;

    /** 会员经验积分日志表type，类型：1、经验值 */
    public final static int    MEMBER_GRD_INT_LOG_T_1                  = 1;

    /** 会员经验积分日志表type，类型：2、积分 */
    public final static int    MEMBER_GRD_INT_LOG_T_2                  = 2;

    /** 会员经验积分日志表opt_type，操作类型：1、会员注册 */
    public final static int    MEMBER_GRD_INT_LOG_OPT_T_1              = 1;

    /** 会员经验积分日志表opt_type，操作类型：2、会员登录 */
    public final static int    MEMBER_GRD_INT_LOG_OPT_T_2              = 2;

    /** 会员经验积分日志表opt_type，操作类型：3、商品购买 */
    public final static int    MEMBER_GRD_INT_LOG_OPT_T_3              = 3;

    /** 会员经验积分日志表opt_type，操作类型：4、商品评论 */
    public final static int    MEMBER_GRD_INT_LOG_OPT_T_4              = 4;

    /** 会员经验积分日志表opt_type，操作类型：5、系统添加  */
    public final static int    MEMBER_GRD_INT_LOG_OPT_T_5              = 5;

    /** 会员经验积分日志表opt_type，操作类型：6、系统减少 */
    public final static int    MEMBER_GRD_INT_LOG_OPT_T_6              = 6;

    /** 会员经验积分日志表opt_type，操作类型：7、订单消费 */
    public final static int    MEMBER_GRD_INT_LOG_OPT_T_7              = 7;

    /** 会员经验积分日志表opt_type，操作类型：8、商品退货 */
    public final static int    MEMBER_GRD_INT_LOG_OPT_T_8              = 8;

    /** 会员收货地址最大值   */
    public final static int    MEMBER_ADDRESS_MAX                      = 20;

    /** 会员收藏商品表 state 状态：1、显示   */
    public final static int    MEMBER_COLLECTION_PRODUCT_STATE_1       = 1;

    /** 会员收藏商品表 state 状态：2、删除   */
    public final static int    MEMBER_COLLECTION_PRODUCT_STATE_2       = 2;

    /**  是否：0、否 */
    public final static int    YES_NO_0                                = 0;

    /**  是否：1、是 */
    public final static int    YES_NO_1                                = 1;

    /** 关注的商品和店铺默认显示条数*/
    public static final int    DEFAULT_PAGE_SIZE_COLLECTION_SHOP       = 20;

    /** 产品表 is_top  是否推荐：1、不推荐*/
    public final static int    PRODUCT_IS_TOP_1                        = 1;

    /** 产品表 is_top  是否推荐：2、推荐*/
    public final static int    PRODUCT_IS_TOP_2                        = 2;

    /** 用户换货表  state   换货状态：1、未处理；'*/
    public final static int    MEM_PROD_EXCHG_STATE_1                  = 1;

    /** 用户换货表  state   换货状态：2、审核通过待收货；'*/
    public final static int    MEM_PROD_EXCHG_STATE_2                  = 2;

    /** 用户换货表  state   换货状态：3、已经收货；'*/
    public final static int    MEM_PROD_EXCHG_STATE_3                  = 3;

    /** 用户换货表  state   换货状态：4、发货处理完成；'*/
    public final static int    MEM_PROD_EXCHG_STATE_4                  = 4;

    /** 用户换货表  state   换货状态：5、不予处理原件退还；'*/
    public final static int    MEM_PROD_EXCHG_STATE_5                  = 5;

    /** 用户换货表  state   换货状态：6、不处理；'*/
    public final static int    MEM_PROD_EXCHG_STATE_6                  = 6;

    /** 商家投诉管理表  state   状态：1、买家投诉待审核；*/
    public final static int    SELLER_COMPLAINT_1                      = 1;

    /** 商家投诉管理表  state   状态：2、买家投诉不通过；*/
    public final static int    SELLER_COMPLAINT_2                      = 2;

    /** 商家投诉管理表  state   状态：3、买家投诉通过；*/
    public final static int    SELLER_COMPLAINT_3                      = 3;

    /** 商家投诉管理表  state   状态：4、卖家申诉待审核；*/
    public final static int    SELLER_COMPLAINT_4                      = 4;

    /** 商家投诉管理表  state   状态：5、卖家申诉不通过；*/
    public final static int    SELLER_COMPLAINT_5                      = 5;

    /** 商家投诉管理表  state   状态：6、卖家申诉通过；*/
    public final static int    SELLER_COMPLAINT_6                      = 6;

    /** 产品分类表  status   状态：1、显示；*/
    public final static int    PRODUCT_CATE_STATUS_1                   = 1;

    /** 产品分类表  status   状态：2、不显示；*/
    public final static int    PRODUCT_CATE_STATUS_2                   = 2;

    /** 产品分类表  status   状态：3、删除',*/
    public final static int    PRODUCT_CATE_STATUS_3                   = 3;

    /** 商品单品页中 显示推荐产品（top)的个数  */
    public final static int    PRODUCT_PAGE_TOP_SIZE                   = 6;

    /** 商家QQ表state  状态 1、显示；  */
    public final static int    SELLER_QQ_STATE_1                       = 1;

    /** 商家QQ表state  状态 2、不显示；  */
    public final static int    SELLER_QQ_STATE_2                       = 2;

    /** 商家QQ表state  状态 3、删除  */
    public final static int    SELLER_QQ_STATE_3                       = 3;

    /** 发票表 type 类型  1、普通发票；  */
    public final static int    INVOICE_TYPE_1                          = 1;

    /** 发票表 type 类型  2、电子发票；  */
    public final static int    INVOICE_TYPE_2                          = 2;

    /** 发票表 type 类型  3、增值税发票  */
    public final static int    INVOICE_TYPE_3                          = 3;

    /** 发票表   内容 content 1、 明细；  */
    public final static int    INVOICE_CONTENT_TYPE_1                  = 1;

    /** 发票表   内容 content 2、办公用品；  */
    public final static int    INVOICE_CONTENT_TYPE_2                  = 2;

    /** 发票表   内容 content 3、电脑配件；  */
    public final static int    INVOICE_CONTENT_TYPE_3                  = 3;

    /** 发票表   内容 content 4、耗材；  */
    public final static int    INVOICE_CONTENT_TYPE_4                  = 4;

    /** 支付方式CODE  'LINE':  在线支付；  */
    public final static String PAYMENT_CODE_ONLINE                     = "ONLINE";

    /** 支付方式CODE  'OFFLINE':  货到付款；  */
    public final static String PAYMENT_CODE_OFFLINE                    = "OFFLINE";

    /** 支付密码 最多输错个数  */
    public final static int    BALANCEPWD_ERROR_COUNT                  = 6;

    /** 满减活动表状态 state  1、刚创建； */
    public final static int    ACTIVITY_FUOFF_STATE_1                  = 1;
    /** 满减活动表状态 state  2、提交审核； */
    public final static int    ACTIVITY_FUOFF_STATE_2                  = 2;
    /** 满减活动表状态 state  3、上架； */
    public final static int    ACTIVITY_FUOFF_STATE_3                  = 3;
    /** 满减活动表状态 state  4、审核不通过 */
    public final static int    ACTIVITY_FUOFF_STATE_4                  = 4;
    /** 满减活动表状态 state  5、 下架*/
    public final static int    ACTIVITY_FUOFF_STATE_5                  = 5;

    /** 满减活动表类型 type  1、 满减  */
    public final static int    ACTIVITY_FUOFF_TYPE_1                   = 1;

    /** 满减活动表类型 type  2、 满折  */
    public final static int    ACTIVITY_FUOFF_TYPE_2                   = 2;

    /** 合作伙伴,不可见*/
    public static final int    NEWS_PARTNER_DISPLAY_NO                 = 0;
    /** 合作伙伴,可见*/
    public static final int    NEWS_PARTNER_DISPLAY_YES                = 1;

    /**默认*/
    public static final int    SELLER_MANAGE_CATE_STATE_0              = 0;
    /**提交审核*/
    public static final int    SELLER_MANAGE_CATE_STATE_1              = 1;
    /**审核通过*/
    public static final int    SELLER_MANAGE_CATE_STATE_2              = 2;
    /**审核失败*/
    public static final int    SELLER_MANAGE_CATE_STATE_3              = 3;
    /**停用*/
    public static final int    SELLER_MANAGE_CATE_STATE_4              = 4;
    /**角色状态:未删除*/
    public static final int    SYSTEM_ROLE_STATUS_1                    = 1;
    /**角色状态:删除*/
    public static final int    SYSTEM_ROLE_STATUS_2                    = 2;
    /**资源状态:未删除*/
    public static final int    SYSTEM_SYSTEM_STATUS_1                  = 1;
    /**资源状态:删除*/
    public static final int    SYSTEM_SYSTEM_STATUS_2                  = 2;
    /**管理员状态:正常*/
    public static final int    SYSTEM_ADMIN_STATUS_NORM                = 1;
    /**管理员状态:冻结*/
    public static final int    SYSTEM_ADMIN_STATUS_FREEZE              = 2;
    /**管理员状态:删除*/
    public static final int    SYSTEM_ADMIN_STATUS_DEL                 = 3;

    /**广告位状态 展示方式 可以发布多条广告并随机展示*/
    public static final int    ADVPOS_SHOW_RANDOM                      = 1;
    /**广告位状态 展示方式 只允许发布并展示一条广告*/
    public static final int    ADVPOS_SHOW_ONLY_ONE                    = 2;

    /**卖家运费模板 使用中*/
    public static final int    SELLER_TRANSPORT_STATUS_USE             = 1;
    /**卖家运费模板 禁用*/
    public static final int    SELLER_TRANSPORT_STATUS_NOUSE           = 2;

    /** 充值表状态 status  1、待支付；  */
    public final static int    MEMBER_PREDEP_STATUS_1                  = 1;

    /** 充值表状态 status  2、充值成功；  */
    public final static int    MEMBER_PREDEP_STATUS_2                  = 2;

    /** 充值表状态 status  3、充值失败；  */
    public final static int    MEMBER_PREDEP_STATUS_3                  = 3;
}
