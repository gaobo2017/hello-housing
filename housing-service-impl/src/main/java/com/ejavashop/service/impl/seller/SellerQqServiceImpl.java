package com.ejavashop.service.impl.seller;

import java.util.HashMap;
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
import com.ejavashop.entity.seller.SellerQq;
import com.ejavashop.model.seller.SellerQqModel;
import com.ejavashop.service.seller.ISellerQqService;

@Service(value = "sellerQqService")
public class SellerQqServiceImpl implements ISellerQqService {
    private static Logger log = LogManager.getLogger(SellerQqServiceImpl.class);

    @Resource
    private SellerQqModel sellerQqModel;

    @Override
    public ServiceResult<List<SellerQq>> getInusedSellerQqBySId(Integer sellerId) {
        ServiceResult<List<SellerQq>> serviceResult = new ServiceResult<List<SellerQq>>();
        try {
            serviceResult.setResult(sellerQqModel.getInusedSellerQqBySId(sellerId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error(
                "[SellerQqService][getInusedSellerQqBySId]根据店铺ID获取店铺正在使用的QQ:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerQqService][getInusedSellerQqBySId]根据店铺ID获取店铺正在使用的QQ:", e);
        }
        return serviceResult;
    }

    /**
    * 根据id取得商家客服QQ
    * @param  sellerQqId
    * @return
    */
    @Override
    public ServiceResult<SellerQq> getById(Integer sellerQqId) {
        ServiceResult<SellerQq> result = new ServiceResult<SellerQq>();
        try {
            result.setResult(sellerQqModel.getById(sellerQqId));
        } catch (Exception e) {
            log.error("根据id[" + sellerQqId + "]取得商家客服QQ时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + sellerQqId + "]取得商家客服QQ时出现未知异常");
        }
        return result;
    }

    /**
     * 保存商家客服QQ
     * @param  sellerQq
     * @return
     */
    @Override
    public ServiceResult<Integer> save(SellerQq sellerQq) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerQqModel.save(sellerQq));
        } catch (Exception e) {
            log.error("保存商家客服QQ时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存商家客服QQ时出现未知异常");
        }
        return result;
    }

    /**
    * 更新商家客服QQ
    * @param  sellerQq
    * @return
    */
    @Override
    public ServiceResult<Integer> update(SellerQq sellerQq) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerQqModel.update(sellerQq));
        } catch (Exception e) {
            log.error("更新商家客服QQ时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新商家客服QQ时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SellerQq>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<SellerQq>> serviceResult = new ServiceResult<List<SellerQq>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sellerQqModel.pageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            Map<String, Object> map = new HashMap<String, Object>(queryMap);
            map.put("start", start);
            map.put("size", size);

            List<SellerQq> list = sellerQqModel.page(map);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerQqService][save] param1:" + JSON.toJSONString(queryMap) + " &param2:"
                      + JSON.toJSONString(pager));
            log.error("[SellerQqService][page] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public List<SellerQq> list() {
        List<SellerQq> list = sellerQqModel.list();
        return list;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerQqModel.del(id));
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerQqService][save] id:" + id);
            log.error("[SellerQqService][page] exception:", e);
        }
        return serviceResult;
    }
}