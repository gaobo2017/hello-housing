package com.ejavashop.service.system;

import java.util.List;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.vo.system.RegionsVO;

/**
 * 区域
 *                       
 * @Filename: IRegionsService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IRegionsService {

    /**
     * 根据id取得regions对象
     * @param  regionsId
     * @return
     */
    ServiceResult<Regions> getRegionsById(Integer regionsId);

    List<Regions> getProvince();

    /**
     * 以上级地区获取其下所有子地区
     * @param parent
     * @param type
     * @return
     */
    List<Regions> getChildrenArea(Integer parent, String type);

    /**
     * 所有地区
     * @return
     */
    List<RegionsVO> getAllArea();

    /**
     * 根据父id取得regions对象
     * @param parentId
     * @return
     */
    ServiceResult<List<Regions>> getRegionsByParentId(Integer parentId);
}