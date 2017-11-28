package com.ejavashop.entity.member;

import java.io.Serializable;

/**
 * 会员
 * <p>Table: <strong>member</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>用户名</td></tr>
 *   <tr><td>password</td><td>{@link java.lang.String}</td><td>password</td><td>varchar</td><td>密码</td></tr>
 *   <tr><td>grade</td><td>{@link java.lang.Integer}</td><td>grade</td><td>int</td><td>会员等级</td></tr>
 *   <tr><td>gradeValue</td><td>{@link java.lang.Integer}</td><td>grade_value</td><td>int</td><td>会员经验值</td></tr>
 *   <tr><td>integral</td><td>{@link java.lang.Integer}</td><td>integral</td><td>int</td><td>会员积分</td></tr>
 *   <tr><td>registerTime</td><td>{@link java.util.Date}</td><td>register_time</td><td>datetime</td><td>注册时间</td></tr>
 *   <tr><td>lastLoginTime</td><td>{@link java.util.Date}</td><td>last_login_time</td><td>datetime</td><td>最后登录时间</td></tr>
 *   <tr><td>lastLoginIp</td><td>{@link java.lang.String}</td><td>last_login_ip</td><td>varchar</td><td>最后登录IP</td></tr>
 *   <tr><td>loginNumber</td><td>{@link java.lang.Integer}</td><td>login_number</td><td>int</td><td>登陆次数</td></tr>
 *   <tr><td>lastAddressId</td><td>{@link java.lang.Integer}</td><td>last_address_id</td><td>int</td><td>上次使用的地址</td></tr>
 *   <tr><td>lastPaymentCode</td><td>{@link java.lang.Integer}</td><td>last_payment_code</td><td>int</td><td>上次使用的支付方式</td></tr>
 *   <tr><td>gender</td><td>{@link java.lang.Integer}</td><td>gender</td><td>tinyint</td><td>性别0、保密；1、男；2、女</td></tr>
 *   <tr><td>birthday</td><td>{@link java.util.Date}</td><td>birthday</td><td>date</td><td>生日</td></tr>
 *   <tr><td>email</td><td>{@link java.lang.String}</td><td>email</td><td>varchar</td><td>email</td></tr>
 *   <tr><td>qq</td><td>{@link java.lang.String}</td><td>qq</td><td>varchar</td><td>qq</td></tr>
 *   <tr><td>mobile</td><td>{@link java.lang.String}</td><td>mobile</td><td>varchar</td><td>mobile</td></tr>
 *   <tr><td>phone</td><td>{@link java.lang.String}</td><td>phone</td><td>varchar</td><td>电话</td></tr>
 *   <tr><td>pwdErrCount</td><td>{@link java.lang.Integer}</td><td>pwd_err_count</td><td>int</td><td>密码输入错误次数</td></tr>
 *   <tr><td>source</td><td>{@link java.lang.Integer}</td><td>source</td><td>tinyint</td><td>会员来源1、pc；2、H5；3、Android；4、IOS</td></tr>
 *   <tr><td>balance</td><td>{@link java.math.BigDecimal}</td><td>balance</td><td>decimal</td><td>账户余额</td></tr>
 *   <tr><td>balancePwd</td><td>{@link java.lang.String}</td><td>balance_pwd</td><td>varchar</td><td>账户支付密码</td></tr>
 *   <tr><td>isEmailVerify</td><td>{@link java.lang.Integer}</td><td>is_email_verify</td><td>tinyint</td><td>是否验证邮箱0、未验证；1、验证</td></tr>
 *   <tr><td>isSmsVerify</td><td>{@link java.lang.Integer}</td><td>is_sms_verify</td><td>tinyint</td><td>是否验证手机0、未验证；1、验证</td></tr>
 *   <tr><td>smsVerifyCode</td><td>{@link java.lang.String}</td><td>sms_verify_code</td><td>varchar</td><td>短信验证码</td></tr>
 *   <tr><td>emailVerifyCode</td><td>{@link java.lang.String}</td><td>email_verify_code</td><td>varchar</td><td>邮件验证码</td></tr>
 *   <tr><td>canReceiveSms</td><td>{@link java.lang.Integer}</td><td>can_receive_sms</td><td>tinyint</td><td>是否接受短信0、不接受；1、接受</td></tr>
 *   <tr><td>canReceiveEmail</td><td>{@link java.lang.Integer}</td><td>can_receive_email</td><td>tinyint</td><td>是否接收邮件</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>1、正常使用；2、停用</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class Member implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID  = 6934708541683110498L;

    /** 会员状态：1、正常使用 */
    public final static int      STATUS_1_ON       = 1;
    /** 会员状态：2、停用 */
    public final static int      STATUS_2_OFF      = 2;

    /** 会员等级配置表，会员等级：1、注册会员 */
    public final static int      GRADE_1           = 1;
    /** 会员等级配置表，会员等级：2、铜牌会员 */
    public final static int      GRADE_2           = 2;
    /** 会员等级配置表，会员等级：3、银牌会员 */
    public final static int      GRADE_3           = 3;
    /** 会员等级配置表，会员等级：4、金牌会员 */
    public final static int      GRADE_4           = 4;
    /** 会员等级配置表，会员等级：5、钻石会员 */
    public final static int      GRADE_5           = 5;

    /** 会员表status，状态：1、正常使用 */
    public final static int      STATUS_1          = 1;
    /** 会员表status，状态：2、停用 */
    public final static int      STATUS_2          = 2;

    /** 是否验证邮箱：0、未验证 */
    public final static int      IS_EMAIL_VERIFY_0 = 0;
    /** 是否验证邮箱：1、验证 */
    public final static int      IS_EMAIL_VERIFY_1 = 1;

    /** 是否验证手机：0、未验证 */
    public final static int      IS_SMS_VERIFY_0   = 0;
    /** 是否验证手机：1、验证 */
    public final static int      IS_SMS_VERIFY_1   = 1;

    private java.lang.Integer    id;
    private java.lang.String     name;
    private java.lang.String     password;
    private java.lang.Integer    grade;
    private java.lang.Integer    gradeValue;
    private java.lang.Integer    integral;
    private java.util.Date       registerTime;
    private java.util.Date       lastLoginTime;
    private java.lang.String     lastLoginIp;
    private java.lang.Integer    loginNumber;
    private java.lang.Integer    lastAddressId;
    private java.lang.Integer    lastPaymentCode;
    private java.lang.Integer    gender;
    private java.util.Date       birthday;
    private java.lang.String     email;
    private java.lang.String     qq;
    private java.lang.String     mobile;
    private java.lang.String     phone;
    private java.lang.Integer    pwdErrCount;
    private java.lang.Integer    source;
    private java.math.BigDecimal balance;
    private java.lang.String     balancePwd;
    private java.lang.Integer    isEmailVerify;
    private java.lang.Integer    isSmsVerify;
    private java.lang.String     smsVerifyCode;
    private java.lang.String     emailVerifyCode;
    private java.lang.Integer    canReceiveSms;
    private java.lang.Integer    canReceiveEmail;
    private java.lang.Integer    status;
    private java.util.Date       updateTime;

    /**
     * 获取id
     */
    public java.lang.Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    /**
     * 获取用户名
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 设置用户名
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * 获取密码
     */
    public java.lang.String getPassword() {
        return this.password;
    }

    /**
     * 设置密码
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    /**
     * 获取会员等级
     */
    public java.lang.Integer getGrade() {
        return this.grade;
    }

    /**
     * 设置会员等级
     */
    public void setGrade(java.lang.Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取会员经验值
     */
    public java.lang.Integer getGradeValue() {
        return this.gradeValue;
    }

    /**
     * 设置会员经验值
     */
    public void setGradeValue(java.lang.Integer gradeValue) {
        this.gradeValue = gradeValue;
    }

    /**
     * 获取会员积分
     */
    public java.lang.Integer getIntegral() {
        return this.integral;
    }

    /**
     * 设置会员积分
     */
    public void setIntegral(java.lang.Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取注册时间
     */
    public java.util.Date getRegisterTime() {
        return this.registerTime;
    }

    /**
     * 设置注册时间
     */
    public void setRegisterTime(java.util.Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 获取最后登录时间
     */
    public java.util.Date getLastLoginTime() {
        return this.lastLoginTime;
    }

    /**
     * 设置最后登录时间
     */
    public void setLastLoginTime(java.util.Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取最后登录IP
     */
    public java.lang.String getLastLoginIp() {
        return this.lastLoginIp;
    }

    /**
     * 设置最后登录IP
     */
    public void setLastLoginIp(java.lang.String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    /**
     * 获取登陆次数
     */
    public java.lang.Integer getLoginNumber() {
        return this.loginNumber;
    }

    /**
     * 设置登陆次数
     */
    public void setLoginNumber(java.lang.Integer loginNumber) {
        this.loginNumber = loginNumber;
    }

    /**
     * 获取上次使用的地址
     */
    public java.lang.Integer getLastAddressId() {
        return this.lastAddressId;
    }

    /**
     * 设置上次使用的地址
     */
    public void setLastAddressId(java.lang.Integer lastAddressId) {
        this.lastAddressId = lastAddressId;
    }

    /**
     * 获取上次使用的支付方式
     */
    public java.lang.Integer getLastPaymentCode() {
        return this.lastPaymentCode;
    }

    /**
     * 设置上次使用的支付方式
     */
    public void setLastPaymentCode(java.lang.Integer lastPaymentCode) {
        this.lastPaymentCode = lastPaymentCode;
    }

    /**
     * 获取性别0、保密；1、男；2、女
     */
    public java.lang.Integer getGender() {
        return this.gender;
    }

    /**
     * 设置性别0、保密；1、男；2、女
     */
    public void setGender(java.lang.Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取生日
     */
    public java.util.Date getBirthday() {
        return this.birthday;
    }

    /**
     * 设置生日
     */
    public void setBirthday(java.util.Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取email
     */
    public java.lang.String getEmail() {
        return this.email;
    }

    /**
     * 设置email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    /**
     * 获取qq
     */
    public java.lang.String getQq() {
        return this.qq;
    }

    /**
     * 设置qq
     */
    public void setQq(java.lang.String qq) {
        this.qq = qq;
    }

    /**
     * 获取mobile
     */
    public java.lang.String getMobile() {
        return this.mobile;
    }

    /**
     * 设置mobile
     */
    public void setMobile(java.lang.String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取电话
     */
    public java.lang.String getPhone() {
        return this.phone;
    }

    /**
     * 设置电话
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }

    /**
     * 获取密码输入错误次数
     */
    public java.lang.Integer getPwdErrCount() {
        return this.pwdErrCount;
    }

    /**
     * 设置密码输入错误次数
     */
    public void setPwdErrCount(java.lang.Integer pwdErrCount) {
        this.pwdErrCount = pwdErrCount;
    }

    /**
     * 获取会员来源1、pc；2、H5；3、Android；4、IOS
     */
    public java.lang.Integer getSource() {
        return this.source;
    }

    /**
     * 设置会员来源1、pc；2、H5；3、Android；4、IOS
     */
    public void setSource(java.lang.Integer source) {
        this.source = source;
    }

    /**
     * 获取账户余额
     */
    public java.math.BigDecimal getBalance() {
        return this.balance;
    }

    /**
     * 设置账户余额
     */
    public void setBalance(java.math.BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * 获取账户支付密码
     */
    public java.lang.String getBalancePwd() {
        return this.balancePwd;
    }

    /**
     * 设置账户支付密码
     */
    public void setBalancePwd(java.lang.String balancePwd) {
        this.balancePwd = balancePwd;
    }

    /**
     * 获取是否验证邮箱0、未验证；1、验证
     */
    public java.lang.Integer getIsEmailVerify() {
        return this.isEmailVerify;
    }

    /**
     * 设置是否验证邮箱0、未验证；1、验证
     */
    public void setIsEmailVerify(java.lang.Integer isEmailVerify) {
        this.isEmailVerify = isEmailVerify;
    }

    /**
     * 获取是否验证手机0、未验证；1、验证
     */
    public java.lang.Integer getIsSmsVerify() {
        return this.isSmsVerify;
    }

    /**
     * 设置是否验证手机0、未验证；1、验证
     */
    public void setIsSmsVerify(java.lang.Integer isSmsVerify) {
        this.isSmsVerify = isSmsVerify;
    }

    /**
     * 获取短信验证码
     */
    public java.lang.String getSmsVerifyCode() {
        return this.smsVerifyCode;
    }

    /**
     * 设置短信验证码
     */
    public void setSmsVerifyCode(java.lang.String smsVerifyCode) {
        this.smsVerifyCode = smsVerifyCode;
    }

    /**
     * 获取邮件验证码
     */
    public java.lang.String getEmailVerifyCode() {
        return this.emailVerifyCode;
    }

    /**
     * 设置邮件验证码
     */
    public void setEmailVerifyCode(java.lang.String emailVerifyCode) {
        this.emailVerifyCode = emailVerifyCode;
    }

    /**
     * 获取是否接受短信0、不接受；1、接受
     */
    public java.lang.Integer getCanReceiveSms() {
        return this.canReceiveSms;
    }

    /**
     * 设置是否接受短信0、不接受；1、接受
     */
    public void setCanReceiveSms(java.lang.Integer canReceiveSms) {
        this.canReceiveSms = canReceiveSms;
    }

    /**
     * 获取是否接收邮件
     */
    public java.lang.Integer getCanReceiveEmail() {
        return this.canReceiveEmail;
    }

    /**
     * 设置是否接收邮件
     */
    public void setCanReceiveEmail(java.lang.Integer canReceiveEmail) {
        this.canReceiveEmail = canReceiveEmail;
    }

    /**
     * 获取1、正常使用；2、停用
     */
    public java.lang.Integer getStatus() {
        return this.status;
    }

    /**
     * 设置1、正常使用；2、停用
     */
    public void setStatus(java.lang.Integer status) {
        this.status = status;
    }

    /**
     * 获取updateTime
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置updateTime
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }
}