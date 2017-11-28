package com.ejavashop.model.system;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.system.RegionsReadDao;
import com.ejavashop.dao.shop.write.system.RegionsWriteDao;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.vo.system.RegionsVO;

@Component(value = "regionsModel")
public class RegionsModel {

    @Resource
    private RegionsWriteDao regionsWriteDao;

    @Resource
    private RegionsReadDao  regionsReadDao;

    /**
    * 根据id取得regions对象
    * @param  regionsId
    * @return
    */
    public Regions getRegionsById(Integer regionsId) {
        return regionsWriteDao.get(regionsId);
    }

    public List<Regions> getProvince() {
        List<Regions> list = this.regionsWriteDao.getProvince();
        return list;
    }

    public List<Regions> getChildrenArea(Integer parentid, String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("parentid", parentid);
        map.put("type", type);
        List<Regions> list = this.regionsWriteDao.getChildrenArea(map);
        return list;
    }

    public List<RegionsVO> getAllArea() throws IllegalAccessException, InvocationTargetException,
                                        NoSuchMethodException {
        List<Regions> pros = regionsWriteDao.getProvince();
        List<RegionsVO> prosVO = new ArrayList<RegionsVO>();
        for (Regions pro : pros) {
            RegionsVO proVO = new RegionsVO();
            PropertyUtils.copyProperties(proVO, pro);

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("parentid", pro.getId());
            map.put("type", 2);
            List<Regions> citys = regionsWriteDao.getChildrenArea(map);
            List<RegionsVO> citysVO = new ArrayList<RegionsVO>();
            for (Regions city : citys) {
                RegionsVO cityVO = new RegionsVO();
                PropertyUtils.copyProperties(cityVO, city);
                citysVO.add(cityVO);
            }
            proVO.setChildren(citysVO);

            prosVO.add(proVO);
        }
        return prosVO;
    }

    public List<Regions> getRegionsByParentId(Integer parentId) {
        return regionsReadDao.getByParentId(parentId);
    }
}
