package com.ejavashop.entity.seller;

import java.io.Serializable;
import java.util.Date;

/**
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
public class SellerApplyBrand implements Serializable {
    private static final long serialVersionUID = -699379484539952588L;

    private Integer id;
    private Integer sellerId;    //所属商家
    private String  name;        //品牌名称
    private String  nameFirst;   //品牌首字母
    private String  image;       //图片大小比例3:1
    private Integer createId;    //创建人
    private Date    createTime;  //创建时间
    private Integer updateId;
    private Date    updateTime;
    private String  explainInfo; //品牌说明
    private Integer state;       //0、默认；1、提交审核；2、显示中；3、审核失败；4、删除
    private String  createUser;  //创建人
    private String  updateUser;  //修改人
    private String  sellerName;  //商家名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public void setNameFirst(String nameFirst) {
        this.nameFirst = nameFirst;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getExplainInfo() {
        return explainInfo;
    }

    public void setExplainInfo(String explainInfo) {
        this.explainInfo = explainInfo;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public enum Status {
        //0、默认；1、提交审核；2、显示中；3、审核失败；4、删除
        DEFAULT("默认", 0), COMMIT("提交审核", 1), SUCCESS("显示中", 2), ERROR("审核失败", 3), DEL("删除", 4);

        private String name; //显示的名字
        private Integer value; //实际存储的值

        //构造方法
        private Status(String name, int value) {
            this.name = name;
            this.value = value;
        }

        /**
         * 检查value是否合法
         *
         * @param value
         * @return
         */
        public static Boolean chkStatus(Integer value) {
            for (Status status : Status.values()) {
                if (status.getValue() == value)
                    return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }

        public Integer getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }
}
