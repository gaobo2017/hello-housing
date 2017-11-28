package com.ejavashop.entity.system;

import java.io.Serializable;
import java.util.Date;

public class Code implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1541894852131265200L;
    private String            codeDiv;
    private String            codeCd;
    private String            codeText;
    private int               sortOrder;
    private int               useYn;
    private int               createUserId;
    private String            createUser;
    private Date              createTime;
    private int               updateUserId;
    private String            updateUser;
    private Date              updateTime;

    public String getCodeDiv() {
        return codeDiv;
    }

    public void setCodeDiv(String codeDiv) {
        this.codeDiv = codeDiv;
    }

    public String getCodeCd() {
        return codeCd;
    }

    public void setCodeCd(String codeCd) {
        this.codeCd = codeCd;
    }

    public String getCodeText() {
        return codeText;
    }

    public void setCodeText(String codeText) {
        this.codeText = codeText;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getUseYn() {
        return useYn;
    }

    public void setUseYn(int useYn) {
        this.useYn = useYn;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(int updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    //@CacheKeyMethod
    public String cacheKey() {
        return this.getCodeDiv() + "/" + this.getCodeCd();
    }

}
