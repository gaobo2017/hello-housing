package com.ejavashop.entity.house;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HousingResources implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8457621597774531273L;

	private Integer id;

	private String roomCodeNo;
	
    private String houseName;

    private String houseBlock;

    private String houseAddress;

    private String floor;

    private String roomCode;

    private String houseType;

    private String toward;

    private Date gainTime;

    private Integer status;

    private Integer isSold;

    private Date contractStartTime;

    private Date contractEndTime;

    private BigDecimal monthlyRent;

    private BigDecimal pricesSum;

    private String seller;

    private Date lastSoldTime;

    private String operationName;

    private Integer operationId;

    private String remark;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomCodeNo() {
		return roomCodeNo;
	}

	public void setRoomCodeNo(String roomCodeNo) {
		this.roomCodeNo = roomCodeNo;
	}

	public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName == null ? null : houseName.trim();
    }

    public String getHouseBlock() {
        return houseBlock;
    }

    public void setHouseBlock(String houseBlock) {
        this.houseBlock = houseBlock == null ? null : houseBlock.trim();
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress == null ? null : houseAddress.trim();
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode == null ? null : roomCode.trim();
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType == null ? null : houseType.trim();
    }

    public String getToward() {
        return toward;
    }

    public void setToward(String toward) {
        this.toward = toward == null ? null : toward.trim();
    }

    public Date getGainTime() {
        return gainTime;
    }

    public void setGainTime(Date gainTime) {
        this.gainTime = gainTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsSold() {
        return isSold;
    }

    public void setIsSold(Integer isSold) {
        this.isSold = isSold;
    }

    public Date getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public BigDecimal getPricesSum() {
        return pricesSum;
    }

    public void setPricesSum(BigDecimal pricesSum) {
        this.pricesSum = pricesSum;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller == null ? null : seller.trim();
    }

    public Date getLastSoldTime() {
        return lastSoldTime;
    }

    public void setLastSoldTime(Date lastSoldTime) {
        this.lastSoldTime = lastSoldTime;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName == null ? null : operationName.trim();
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
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