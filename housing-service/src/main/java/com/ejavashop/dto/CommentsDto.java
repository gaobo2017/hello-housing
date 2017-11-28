package com.ejavashop.dto;

import java.io.Serializable;

/**
 * 商品评论DTO
 *
 */
public class CommentsDto implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -8862702845005226749L;

    private Integer           grade;
    private Integer           description;
    private Integer           serviceAttitude;
    private Integer           productSpeed;
    private Integer           logisticsSpeed;
    private Integer           expressAttitude;
    private Integer           number;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getDescription() {
        return description;
    }

    public void setDescription(Integer description) {
        this.description = description;
    }

    public Integer getServiceAttitude() {
        return serviceAttitude;
    }

    public void setServiceAttitude(Integer serviceAttitude) {
        this.serviceAttitude = serviceAttitude;
    }

    public Integer getProductSpeed() {
        return productSpeed;
    }

    public void setProductSpeed(Integer productSpeed) {
        this.productSpeed = productSpeed;
    }

    public Integer getLogisticsSpeed() {
        return logisticsSpeed;
    }

    public void setLogisticsSpeed(Integer logisticsSpeed) {
        this.logisticsSpeed = logisticsSpeed;
    }

    public Integer getExpressAttitude() {
        return expressAttitude;
    }

    public void setExpressAttitude(Integer expressAttitude) {
        this.expressAttitude = expressAttitude;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}