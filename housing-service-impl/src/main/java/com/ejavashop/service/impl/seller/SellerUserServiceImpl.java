package com.ejavashop.service.impl.seller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.model.seller.SellerUserModel;
import com.ejavashop.service.seller.ISellerUserService;

@Service(value = "sellerUserService")
public class SellerUserServiceImpl implements ISellerUserService {
    private static Logger   log = LogManager.getLogger(SellerUserServiceImpl.class);

    @Resource
    private SellerUserModel sellerUserModel;

    @Override
    public ServiceResult<SellerUser> getSellerUserById(Integer sellerUserId) {
        ServiceResult<SellerUser> result = new ServiceResult<SellerUser>();
        try {
            result.setResult(sellerUserModel.getSellerUserById(sellerUserId));
        } catch (Exception e) {
            log.error("根据id[" + sellerUserId + "]取得系统管理员表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + sellerUserId + "]取得系统管理员表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> saveSellerUser(SellerUser sellerUser) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerUserModel.saveSellerUser(sellerUser));
            result.setMessage("保存成功");
        } catch (Exception e) {
            log.error("保存系统管理员表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存系统管理员表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updateSellerUser(SellerUser sellerUser) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerUserModel.updateSellerUser(sellerUser));
            result.setMessage("更新成功");
        } catch (Exception e) {
            log.error("更新系统管理员表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新系统管理员表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SellerUser>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<SellerUser>> serviceResult = new ServiceResult<List<SellerUser>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sellerUserModel.pageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<SellerUser> list = sellerUserModel.page(queryMap, start, size);

            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerUserServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SellerUserServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> sr = new ServiceResult<Boolean>();
        try {
            sr.setResult(sellerUserModel.del(id));
        } catch (Exception e) {
            log.error("[SellerUserServiceImpl][del] exception:" + e.getMessage());
            e.printStackTrace();
        }
        return sr;
    }

    @Override
    public ServiceResult<SellerUser> getSellerUserByNamePwd(String name, String password) {
        ServiceResult<SellerUser> result = new ServiceResult<SellerUser>();
        try {
            result.setResult(sellerUserModel.getSellerUserByNamePwd(name, password));
        } catch (Exception e) {
            log.error("[SellerUserServiceImpl][getSellerUserByNamePwd] exception:", e);
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("用户名或密码错误");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SellerUser>> getSellerUserByName(String name) {
        ServiceResult<List<SellerUser>> result = new ServiceResult<List<SellerUser>>();
        try {
            result.setResult(sellerUserModel.getSellerUserByName(name));
        } catch (BusinessException e) {
            result.setMessage(e.getMessage());
            result.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerUserServiceImpl][getSellerUserByName]根据用户名取商家用户时发生错误:", e);
        }
        return result;
    }
}