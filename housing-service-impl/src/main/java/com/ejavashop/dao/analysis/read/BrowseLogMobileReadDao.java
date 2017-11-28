package com.ejavashop.dao.analysis.read;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.analysis.BrowseLogMobile;

@Repository
public interface BrowseLogMobileReadDao {

    BrowseLogMobile get(java.lang.Integer id);

}