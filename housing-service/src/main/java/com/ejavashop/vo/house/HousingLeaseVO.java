package com.ejavashop.vo.house;

import com.ejavashop.entity.house.HousingLease;

public class HousingLeaseVO extends HousingLease {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 600975387901680920L;

    private String            houseName;

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

}