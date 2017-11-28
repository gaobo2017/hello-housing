package com.ejavashop.service.impl.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.model.system.RegionsModel;
import com.ejavashop.service.system.IRegionsService;
import com.ejavashop.vo.system.RegionsVO;

@Service(value = "regionsService")
public class RegionsServiceImpl implements IRegionsService {
    private static Logger log = LogManager.getLogger(RegionsServiceImpl.class);

    @Resource
    private RegionsModel  regionsModel;

    /**
    * 根据id取得regions对象
    * @param  regionsId
    * @return
    */
    @Override
    public ServiceResult<Regions> getRegionsById(Integer regionsId) {
        ServiceResult<Regions> result = new ServiceResult<Regions>();
        try {
            result.setResult(regionsModel.getRegionsById(regionsId));
        } catch (Exception e) {
            log.error("根据id[" + regionsId + "]取得regions对象时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + regionsId + "]取得regions对象时出现未知异常");
        }
        return result;
    }

    @Override
    public List<Regions> getProvince() {
        try {
            List<Regions> list = this.regionsModel.getProvince();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("获取城市异常");
        }
    }

    @Override
    public List<Regions> getChildrenArea(Integer parentid, String type) {
        try {
            List<Regions> list = regionsModel.getChildrenArea(parentid, type);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("获取城市异常");
        }
    }

    @Override
    public List<RegionsVO> getAllArea() {
        try {
            List<RegionsVO> list = regionsModel.getAllArea();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("获取城市异常");
        }
    }

    @Override
    public ServiceResult<List<Regions>> getRegionsByParentId(Integer parentId) {
        ServiceResult<List<Regions>> result = new ServiceResult<List<Regions>>();
        try {
            result.setResult(regionsModel.getRegionsByParentId(parentId));
        } catch (Exception e) {
            log.error("根据父id[" + parentId + "]取得regions对象时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据父id[" + parentId + "]取得regions对象时出现未知异常");
        }
        return result;
    }
}