package com.ejavashop.dao.analysis.read;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.dto.PVDto;
import com.ejavashop.entity.analysis.BrowseLog;

@Repository
public interface BrowseLogReadDao {

    BrowseLog get(java.lang.Integer id);

    List<PVDto> getProductPV(Map<String, String> queryMap);
}