package com.ejavashop.entity.house;

import java.io.Serializable;
import java.util.Date;

public class HousingVacancyDays implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2097176966951558063L;

	private Integer id;

    private Integer leaseId;

    private Integer houseId;

    private Integer vacancyDay;

    private Date vacancyStartTime;

    private Date vacancyEndTime;

    private String remark;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(Integer leaseId) {
        this.leaseId = leaseId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getVacancyDay() {
        return vacancyDay;
    }

    public void setVacancyDay(Integer vacancyDay) {
        this.vacancyDay = vacancyDay;
    }

    public Date getVacancyStartTime() {
        return vacancyStartTime;
    }

    public void setVacancyStartTime(Date vacancyStartTime) {
        this.vacancyStartTime = vacancyStartTime;
    }

    public Date getVacancyEndTime() {
        return vacancyEndTime;
    }

    public void setVacancyEndTime(Date vacancyEndTime) {
        this.vacancyEndTime = vacancyEndTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}