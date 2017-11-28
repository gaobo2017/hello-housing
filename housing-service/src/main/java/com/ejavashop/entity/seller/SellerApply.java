package com.ejavashop.entity.seller;

import java.io.Serializable;

/**
 * 商家申请表
 * <p>Table: <strong>seller_apply</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>userId</td><td>{@link Integer}</td><td>user_id</td><td>int</td><td>用户id</td></tr>
 *   <tr><td>name</td><td>{@link String}</td><td>name</td><td>varchar</td><td>用户名</td></tr>
 *   <tr><td>password</td><td>{@link String}</td><td>password</td><td>varchar</td><td>密码</td></tr>
 *   <tr><td>company</td><td>{@link String}</td><td>company</td><td>varchar</td><td>公司名称</td></tr>
 *   <tr><td>bussinessLicense</td><td>{@link String}</td><td>bussiness_license</td><td>varchar</td><td>营业执照注册号</td></tr>
 *   <tr><td>taxLicense</td><td>{@link String}</td><td>tax_license</td><td>varchar</td><td>税务登记号</td></tr>
 *   <tr><td>companyProvince</td><td>{@link String}</td><td>company_province</td><td>varchar</td><td>企业注册省</td></tr>
 *   <tr><td>companyCity</td><td>{@link String}</td><td>company_city</td><td>varchar</td><td>企业注册市</td></tr>
 *   <tr><td>companyStartTime</td><td>{@link java.util.Date}</td><td>company_start_time</td><td>datetime</td><td>营业开始日期</td></tr>
 *   <tr><td>companyEndTime</td><td>{@link java.util.Date}</td><td>company_end_time</td><td>datetime</td><td>营业结束日期</td></tr>
 *   <tr><td>companyAdd</td><td>{@link String}</td><td>company_add</td><td>varchar</td><td>常用地址</td></tr>
 *   <tr><td>telephone</td><td>{@link String}</td><td>telephone</td><td>varchar</td><td>联系电话</td></tr>
 *   <tr><td>fax</td><td>{@link String}</td><td>fax</td><td>varchar</td><td>传真</td></tr>
 *   <tr><td>bussinessLicenseImage</td><td>{@link String}</td><td>bussiness_license_image</td><td>varchar</td><td>营业执照扫描件</td></tr>
 *   <tr><td>organization</td><td>{@link String}</td><td>organization</td><td>varchar</td><td>组织机构代码</td></tr>
 *   <tr><td>bankUser</td><td>{@link String}</td><td>bank_user</td><td>varchar</td><td>开户行账户名称</td></tr>
 *   <tr><td>bankName</td><td>{@link String}</td><td>bank_name</td><td>varchar</td><td>开户行</td></tr>
 *   <tr><td>bankNameBranch</td><td>{@link String}</td><td>bank_name_branch</td><td>varchar</td><td>开户行支行名称</td></tr>
 *   <tr><td>brandNameCode</td><td>{@link String}</td><td>brand_name_code</td><td>varchar</td><td>开户行支行联行号</td></tr>
 *   <tr><td>bankCode</td><td>{@link String}</td><td>bank_code</td><td>varchar</td><td>银行账号</td></tr>
 *   <tr><td>bankProvince</td><td>{@link String}</td><td>bank_province</td><td>varchar</td><td>开户行省</td></tr>
 *   <tr><td>bankCity</td><td>{@link String}</td><td>bank_city</td><td>varchar</td><td>开户行市</td></tr>
 *   <tr><td>legalPerson</td><td>{@link String}</td><td>legal_person</td><td>varchar</td><td>法定代表人</td></tr>
 *   <tr><td>legalPersonCard</td><td>{@link String}</td><td>legal_person_card</td><td>varchar</td><td>法定代表人身份证</td></tr>
 *   <tr><td>personCardUp</td><td>{@link String}</td><td>person_card_up</td><td>varchar</td><td>身份证正面扫描件</td></tr>
 *   <tr><td>personCardDown</td><td>{@link String}</td><td>person_card_down</td><td>varchar</td><td>身份证背面扫描件</td></tr>
 *   <tr><td>personPhone</td><td>{@link String}</td><td>person_phone</td><td>varchar</td><td>手机</td></tr>
 *   <tr><td>email</td><td>{@link String}</td><td>email</td><td>varchar</td><td>email</td></tr>
 *   <tr><td>state</td><td>{@link Integer}</td><td>state</td><td>tinyint</td><td>1、提交申请；2、审核通过；3、缴纳保证金；4、审核失败</td></tr>
 *   <tr><td>type</td><td>{@link Integer}</td><td>type</td><td>tinyint</td><td>1、平台自营；2、商家入驻</td></tr>
 *   <tr><td>optId</td><td>{@link Integer}</td><td>opt_id</td><td>int</td><td>审核人ID</td></tr>
 *   <tr><td>bond</td><td>{@link Integer}</td><td>bond</td><td>int</td><td>保证金额度</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>更新时间</td></tr>
 * </table>
 *
 */
public class SellerApply implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 6436353215957889168L;

    /** 申请状态：提交申请  */
    public static final int   STATE_1_SEND     = 1;
    /** 申请状态：审核通过  */
    public static final int   STATE_2_DONE     = 2;
    /** 申请状态：缴纳保证金  */
    public static final int   STATE_3_CASH     = 3;
    /** 申请状态：审核失败  */
    public static final int   STATE_4_FAIL     = 4;

    private Integer           id;
    private Integer           userId;
    private String            name;
    private String            password;
    private String            company;
    private String            bussinessLicense;
    private String            taxLicense;
    private String            companyProvince;
    private String            companyCity;
    private java.util.Date    companyStartTime;
    private java.util.Date    companyEndTime;
    private String            companyAdd;
    private String            telephone;
    private String            fax;
    private String            bussinessLicenseImage;
    private String            organization;
    private String            bankUser;
    private String            bankName;
    private String            bankNameBranch;
    private String            brandNameCode;
    private String            bankCode;
    private String            bankProvince;
    private String            bankCity;
    private String            legalPerson;
    private String            legalPersonCard;
    private String            personCardUp;
    private String            personCardDown;
    private String            personPhone;
    private String            email;
    private Integer           state;
    private Integer           type;
    private Integer           optId;
    private Integer           bond;
    private java.util.Date    createTime;
    private java.util.Date    updateTime;

    /**
     * 获取id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户id
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取密码
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * 设置密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取公司名称
     */
    public String getCompany() {
        return this.company;
    }

    /**
     * 设置公司名称
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * 获取营业执照注册号
     */
    public String getBussinessLicense() {
        return this.bussinessLicense;
    }

    /**
     * 设置营业执照注册号
     */
    public void setBussinessLicense(String bussinessLicense) {
        this.bussinessLicense = bussinessLicense;
    }

    /**
     * 获取税务登记号
     */
    public String getTaxLicense() {
        return this.taxLicense;
    }

    /**
     * 设置税务登记号
     */
    public void setTaxLicense(String taxLicense) {
        this.taxLicense = taxLicense;
    }

    /**
     * 获取企业注册省
     */
    public String getCompanyProvince() {
        return this.companyProvince;
    }

    /**
     * 设置企业注册省
     */
    public void setCompanyProvince(String companyProvince) {
        this.companyProvince = companyProvince;
    }

    /**
     * 获取企业注册市
     */
    public String getCompanyCity() {
        return this.companyCity;
    }

    /**
     * 设置企业注册市
     */
    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    /**
     * 获取营业开始日期
     */
    public java.util.Date getCompanyStartTime() {
        return this.companyStartTime;
    }

    /**
     * 设置营业开始日期
     */
    public void setCompanyStartTime(java.util.Date companyStartTime) {
        this.companyStartTime = companyStartTime;
    }

    /**
     * 获取营业结束日期
     */
    public java.util.Date getCompanyEndTime() {
        return this.companyEndTime;
    }

    /**
     * 设置营业结束日期
     */
    public void setCompanyEndTime(java.util.Date companyEndTime) {
        this.companyEndTime = companyEndTime;
    }

    /**
     * 获取常用地址
     */
    public String getCompanyAdd() {
        return this.companyAdd;
    }

    /**
     * 设置常用地址
     */
    public void setCompanyAdd(String companyAdd) {
        this.companyAdd = companyAdd;
    }

    /**
     * 获取联系电话
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * 设置联系电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取传真
     */
    public String getFax() {
        return this.fax;
    }

    /**
     * 设置传真
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * 获取营业执照扫描件
     */
    public String getBussinessLicenseImage() {
        return this.bussinessLicenseImage;
    }

    /**
     * 设置营业执照扫描件
     */
    public void setBussinessLicenseImage(String bussinessLicenseImage) {
        this.bussinessLicenseImage = bussinessLicenseImage;
    }

    /**
     * 获取组织机构代码
     */
    public String getOrganization() {
        return this.organization;
    }

    /**
     * 设置组织机构代码
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * 获取开户行账户名称
     */
    public String getBankUser() {
        return this.bankUser;
    }

    /**
     * 设置开户行账户名称
     */
    public void setBankUser(String bankUser) {
        this.bankUser = bankUser;
    }

    /**
     * 获取开户行
     */
    public String getBankName() {
        return this.bankName;
    }

    /**
     * 设置开户行
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * 获取开户行支行名称
     */
    public String getBankNameBranch() {
        return this.bankNameBranch;
    }

    /**
     * 设置开户行支行名称
     */
    public void setBankNameBranch(String bankNameBranch) {
        this.bankNameBranch = bankNameBranch;
    }

    /**
     * 获取开户行支行联行号
     */
    public String getBrandNameCode() {
        return this.brandNameCode;
    }

    /**
     * 设置开户行支行联行号
     */
    public void setBrandNameCode(String brandNameCode) {
        this.brandNameCode = brandNameCode;
    }

    /**
     * 获取银行账号
     */
    public String getBankCode() {
        return this.bankCode;
    }

    /**
     * 设置银行账号
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * 获取开户行省
     */
    public String getBankProvince() {
        return this.bankProvince;
    }

    /**
     * 设置开户行省
     */
    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    /**
     * 获取开户行市
     */
    public String getBankCity() {
        return this.bankCity;
    }

    /**
     * 设置开户行市
     */
    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    /**
     * 获取法定代表人
     */
    public String getLegalPerson() {
        return this.legalPerson;
    }

    /**
     * 设置法定代表人
     */
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    /**
     * 获取法定代表人身份证
     */
    public String getLegalPersonCard() {
        return this.legalPersonCard;
    }

    /**
     * 设置法定代表人身份证
     */
    public void setLegalPersonCard(String legalPersonCard) {
        this.legalPersonCard = legalPersonCard;
    }

    /**
     * 获取身份证正面扫描件
     */
    public String getPersonCardUp() {
        return this.personCardUp;
    }

    /**
     * 设置身份证正面扫描件
     */
    public void setPersonCardUp(String personCardUp) {
        this.personCardUp = personCardUp;
    }

    /**
     * 获取身份证背面扫描件
     */
    public String getPersonCardDown() {
        return this.personCardDown;
    }

    /**
     * 设置身份证背面扫描件
     */
    public void setPersonCardDown(String personCardDown) {
        this.personCardDown = personCardDown;
    }

    /**
     * 获取手机
     */
    public String getPersonPhone() {
        return this.personPhone;
    }

    /**
     * 设置手机
     */
    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    /**
     * 获取email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * 设置email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取1、提交申请；2、审核通过；3、缴纳保证金；4、审核失败
     */
    public Integer getState() {
        return this.state;
    }

    /**
     * 设置1、提交申请；2、审核通过；3、缴纳保证金；4、审核失败
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取1、平台自营；2、商家入驻
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * 设置1、平台自营；2、商家入驻
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取审核人ID
     */
    public Integer getOptId() {
        return this.optId;
    }

    /**
     * 设置审核人ID
     */
    public void setOptId(Integer optId) {
        this.optId = optId;
    }

    /**
     * 获取保证金额度
     */
    public Integer getBond() {
        return this.bond;
    }

    /**
     * 设置保证金额度
     */
    public void setBond(Integer bond) {
        this.bond = bond;
    }

    /**
     * 获取创建时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置更新时间
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }
}