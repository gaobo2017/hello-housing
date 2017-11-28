package com.ejavashop.vo.seller;

/**
 * 运费json对象
 *                       
 * @Filename: TransportJson.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public class TransportJson {
    private String city_id;
    private String city_name;
    private Double trans_weight;
    private Double trans_fee;
    private Double trans_add_weight;
    private Double trans_add_fee;
    private String type;

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public Double getTrans_weight() {
        return trans_weight;
    }

    public void setTrans_weight(Double trans_weight) {
        this.trans_weight = trans_weight;
    }

    public Double getTrans_fee() {
        return trans_fee;
    }

    public void setTrans_fee(Double trans_fee) {
        this.trans_fee = trans_fee;
    }

    public Double getTrans_add_weight() {
        return trans_add_weight;
    }

    public void setTrans_add_weight(Double trans_add_weight) {
        this.trans_add_weight = trans_add_weight;
    }

    public Double getTrans_add_fee() {
        return trans_add_fee;
    }

    public void setTrans_add_fee(Double trans_add_fee) {
        this.trans_add_fee = trans_add_fee;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
