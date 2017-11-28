package com.ejavashop.service.impl.seller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.seller.SellerTransport;
import com.ejavashop.model.seller.SellerTransportModel;
import com.ejavashop.service.seller.ISellerTransportService;
import com.ejavashop.vo.seller.TransportJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service(value = "sellerTransportService")
public class SellerTransportServiceImpl implements ISellerTransportService {
    private static Logger        log = LogManager.getLogger(SellerTransportServiceImpl.class);

    @Resource
    private SellerTransportModel sellerTransportModel;

    /**
    * 根据id取得卖家运费模板
    * @param  sellerTransportId
    * @return
    */
    @Override
    public ServiceResult<SellerTransport> getSellerTransportById(Integer sellerTransportId) {
        ServiceResult<SellerTransport> result = new ServiceResult<SellerTransport>();
        try {
            result.setResult(sellerTransportModel.getSellerTransportById(sellerTransportId));
        } catch (Exception e) {
            log.error("根据id[" + sellerTransportId + "]取得卖家运费模板时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + sellerTransportId + "]取得卖家运费模板时出现未知异常");
        }
        return result;
    }

    /**
     * 保存卖家运费模板
     * @param  sellerTransport
     * @return
     */
    @Override
    public ServiceResult<Integer> saveSellerTransport(SellerTransport sellerTransport) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerTransportModel.saveSellerTransport(sellerTransport));
        } catch (Exception e) {
            log.error("保存卖家运费模板时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存卖家运费模板时出现未知异常");
        }
        return result;
    }

    /**
    * 更新卖家运费模板
    * @param  sellerTransport
    * @return
    */
    @Override
    public ServiceResult<Integer> updateSellerTransport(SellerTransport sellerTransport) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerTransportModel.updateSellerTransport(sellerTransport));
        } catch (Exception e) {
            log.error("更新卖家运费模板时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新卖家运费模板时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SellerTransport>> page(Map<String, String> queryMap,
                                                     PagerInfo pager) {
        ServiceResult<List<SellerTransport>> serviceResult = new ServiceResult<List<SellerTransport>>();
        serviceResult.setPager(pager);
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sellerTransportModel.pageCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            param.put("start", start);
            param.put("size", size);
            List<SellerTransport> list = sellerTransportModel.page(param);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerTransportServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SellerTransportServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerTransportModel.del(id));
            serviceResult.setMessage("删除成功");
        } catch (Exception e) {
            log.error("[SellerTransportServiceImpl][del] exception:" + e.getMessage());
            e.printStackTrace();
        }
        return serviceResult;
    }

    /**
     * 组装运费信息
     * @param st
     * @return
     */
    @Override
    public HttpJsonResult<List<TransportJson>> assembleTransportInfo(SellerTransport st) {
        //{city_id=-1, city_name=全国, trans_add_fee=5.0, trans_fee=12.0, trans_add_weight=1.0, trans_weight=1.0, type=平邮}
        HttpJsonResult<List<TransportJson>> jsonResult = new HttpJsonResult<List<TransportJson>>();
        List<TransportJson> list = new ArrayList<TransportJson>();
        //平邮
        Integer mail = st.getTransMail();
        //快递
        Integer express = st.getTransExpress();
        //EMS
        Integer ems = st.getTransEms();

        if (mail != null && mail.intValue() == ConstantsEJS.YES_NO_1) {
            String mailInfo = st.getTransMailInfo();
            Gson gson = new Gson();
            List<TransportJson> data = gson.fromJson(mailInfo,
                new TypeToken<ArrayList<TransportJson>>() {
                }.getType());
            for (TransportJson json : data) {
                json.setType("平邮");
                list.add(json);
            }
        }

        if (express != null && express.intValue() == ConstantsEJS.YES_NO_1) {
            String expressInfo = st.getTransExpressInfo();
            Gson gson = new Gson();
            List<TransportJson> data = gson.fromJson(expressInfo,
                new TypeToken<ArrayList<TransportJson>>() {
                }.getType());
            for (TransportJson json : data) {
                json.setType("快递");
                list.add(json);
            }
        }

        if (ems != null && ems.intValue() == ConstantsEJS.YES_NO_1) {
            String emsInfo = st.getTransEmsInfo();
            Gson gson = new Gson();
            List<TransportJson> data = gson.fromJson(emsInfo,
                new TypeToken<ArrayList<TransportJson>>() {
                }.getType());
            for (TransportJson json : data) {
                json.setType("EMS");
                list.add(json);
            }
        }
        jsonResult.setRows(list);
        jsonResult.setTotal(list.size());
        return jsonResult;
    }

    @Override
    public List<SellerTransport> getTransportBySellerId(Integer sellerid) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<SellerTransport> list = null;
        try {
            if (sellerid == null)
                throw new BusinessException("卖家未知");
            map.put("sellerId", sellerid);
            map.put("state", ConstantsEJS.SELLER_TRANSPORT_STATUS_USE);
            list = this.sellerTransportModel.page(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list == null || list.size() == 0) {
            list = new ArrayList<SellerTransport>();
            list.add(new SellerTransport());
        }
        return list;
    }

    @Override
    public ServiceResult<Boolean> transportInUse(Integer sellerId, Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerTransportModel.transportInUse(sellerId, id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[SellerTransportServiceImpl][transportInUse] 根据运费末班ID启用某个模板，同时更新该商家的其他模板为禁用状态时发生异常:"
                      + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<BigDecimal> calculateTransFee(Integer sellerId, Integer num,
                                                       Integer cityId) {
        ServiceResult<BigDecimal> serviceResult = new ServiceResult<BigDecimal>();
        try {
            serviceResult.setResult(sellerTransportModel.calculateTransFee(sellerId, num, cityId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerTransportServiceImpl][calculateTransFee]计算运费时发生异常:" + e.getMessage());
        }
        return serviceResult;
    }
}