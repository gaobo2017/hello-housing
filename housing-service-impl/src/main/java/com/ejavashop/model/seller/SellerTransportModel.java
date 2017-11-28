package com.ejavashop.model.seller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.seller.SellerTransportReadDao;
import com.ejavashop.dao.shop.write.seller.SellerTransportWriteDao;
import com.ejavashop.entity.seller.SellerTransport;
import com.ejavashop.vo.seller.TransportJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component(value = "sellerTransportModel")
public class SellerTransportModel {

    @Resource
    private SellerTransportWriteDao      sellerTransportWriteDao;
    @Resource
    private SellerTransportReadDao       sellerTransportReadDao;
    @Resource
    private DataSourceTransactionManager transactionManager;

    /**
    * 根据id取得卖家运费模板
    * @param  sellerTransportId
    * @return
    */
    public SellerTransport getSellerTransportById(Integer sellerTransportId) {
        return sellerTransportWriteDao.get(sellerTransportId);
    }

    /**
     * 保存卖家运费模板
     * @param  sellerTransport
     * @return
     */
    public Integer saveSellerTransport(SellerTransport sellerTransport) {
        return sellerTransportWriteDao.save(sellerTransport);
    }

    /**
    * 更新卖家运费模板
    * @param  sellerTransport
    * @return
    */
    public Integer updateSellerTransport(SellerTransport sellerTransport) {
        return sellerTransportWriteDao.update(sellerTransport);
    }

    public Integer pageCount(Map<String, Object> queryMap) {
        return sellerTransportWriteDao.getCount(queryMap);
    }

    public List<SellerTransport> page(Map<String, Object> queryMap) {
        return sellerTransportWriteDao.page(queryMap);
    }

    public boolean del(Integer id) {

        if (id == null)
            throw new BusinessException("删除卖家运费模板[" + id + "]时出错");
        return sellerTransportWriteDao.del(id) > 0;
    }

    /**
     * 组装运费信息
     * @param st
     * @return
     */
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

    public List<SellerTransport> getTransportBySellerId(Integer sellerid) {
        Map<String, Object> map = new HashMap<String, Object>();
        List<SellerTransport> list = null;
        try {
            if (sellerid == null)
                throw new BusinessException("卖家未知");
            map.put("sellerId", sellerid);
            map.put("state", ConstantsEJS.SELLER_TRANSPORT_STATUS_USE);
            list = this.sellerTransportWriteDao.page(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list == null || list.size() == 0) {
            list = new ArrayList<SellerTransport>();
            list.add(new SellerTransport());
        }
        return list;
    }

    /**
     * 计算运费
     * @param sellerId 商家ID
     * @param num 数量
     * @param cityId 订单地址的城市ID
     * @return
     */
    public BigDecimal calculateTransFee(Integer sellerId, Integer num, Integer cityId) {
        BigDecimal price = BigDecimal.ZERO;
        Gson gson = new Gson();
        SellerTransport tp = sellerTransportReadDao.getInUseBySellerId(sellerId);
        if (tp == null) {
            return BigDecimal.ZERO;
        }

        List<TransportJson> feelist = null;
        Map<String, Object> mailRlt = null;
        Map<String, Object> expressRlt = null;
        Map<String, Object> emsRlt = null;
        // 价格计算优先级： 平邮 > 快递 > EMS 
        if (tp.getTransMail() != null && tp.getTransMail() == 1) {
            feelist = gson.fromJson(tp.getTransMailInfo(),
                new TypeToken<ArrayList<TransportJson>>() {
                }.getType());
            mailRlt = calculationPrice(feelist, num, cityId);
            // 如果平邮计算出了运费信息，则返回
            if (mailRlt.get("price") != null
                && ((BigDecimal) mailRlt.get("price")).compareTo(BigDecimal.ZERO) > 0) {
                return (BigDecimal) mailRlt.get("price");
            }
        }

        if (tp.getTransExpress() != null && tp.getTransExpress() == 1) {
            feelist = gson.fromJson(tp.getTransExpressInfo(),
                new TypeToken<ArrayList<TransportJson>>() {
                }.getType());
            expressRlt = calculationPrice(feelist, num, cityId);
            // 如果快递计算出了运费信息，则返回
            if (expressRlt.get("price") != null
                && ((BigDecimal) expressRlt.get("price")).compareTo(BigDecimal.ZERO) > 0) {
                return (BigDecimal) expressRlt.get("price");
            }
        }

        if (tp.getTransEms() != null && tp.getTransEms() == 1) {
            feelist = gson.fromJson(tp.getTransEmsInfo(),
                new TypeToken<ArrayList<TransportJson>>() {
                }.getType());

            emsRlt = calculationPrice(feelist, num, cityId);
            // 如果EMS计算出了运费信息，则返回
            if (emsRlt.get("price") != null
                && ((BigDecimal) emsRlt.get("price")).compareTo(BigDecimal.ZERO) > 0) {
                return (BigDecimal) emsRlt.get("price");
            }
        }

        // 如果程序运行到此处，说明还没有计算出运费，则根据 平邮 > 快递 > EMS 的优先顺序计算全国的运费
        if (mailRlt != null && mailRlt.get("allArea") != null) {
            TransportJson json = (TransportJson) mailRlt.get("allArea");
            price = this.getFee(json, num);
            if (price.compareTo(BigDecimal.ZERO) > 0) {
                return price;
            }
        }

        if (expressRlt != null && expressRlt.get("allArea") != null) {
            TransportJson json = (TransportJson) expressRlt.get("allArea");
            price = this.getFee(json, num);
            if (price.compareTo(BigDecimal.ZERO) > 0) {
                return price;
            }
        }

        if (emsRlt != null && emsRlt.get("allArea") != null) {
            TransportJson json = (TransportJson) emsRlt.get("allArea");
            price = this.getFee(json, num);
            if (price.compareTo(BigDecimal.ZERO) > 0) {
                return price;
            }
        }

        return price;
    }

    /**
     * 计算运费
     * @param feelist
     * @param num
     * @param areaid
     * @return
     */
    private Map<String, Object> calculationPrice(List<TransportJson> feelist, int num,
                                                 Integer cityId) {
        Map<String, Object> result = new HashMap<String, Object>();
        BigDecimal price = BigDecimal.ZERO;
        for (TransportJson json : feelist) {
            String[] arr = json.getCity_id().split(",");
            for (String city : arr) {
                Integer cityid = ConvertUtil.toInt(city, 0);
                if (cityid == -1) {
                    // 全国，记录下全国的运费，如果没有区域匹配则调用全国的运费计算方法
                    result.put("allArea", json);
                    continue;
                } else if (cityid.equals(cityId)) {
                    // 如果传入的城市ID与当前的相等，表示订单地址符合当前运费信息计算方法
                    price = this.getFee(json, num);
                    break;
                }
            }
            // 如果有区域匹配则跳出循环，返回运费
            if (price.compareTo(BigDecimal.ZERO) > 0) {
                result.put("price", price);
                break;
            }
        }
        return result;
    }

    /**
     * 根据运费信息计算运费
     * @param json
     * @param num
     * @return
     */
    private BigDecimal getFee(TransportJson json, int num) {
        BigDecimal price = BigDecimal.ZERO;
        int weight = json.getTrans_weight().intValue();
        Double fee = json.getTrans_fee();
        int addWeight = json.getTrans_add_weight().intValue();
        Double addFee = json.getTrans_add_fee();

        //多出的件数/重量
        int surplus = (num - weight) > 0 ? (num - weight) : 0;

        //首件的钱
        price = price.add(new BigDecimal(fee));
        //计算多出的钱
        for (int i = 0; i < surplus; i += addWeight) {
            price = price.add(new BigDecimal(addFee));
        }
        return price;
    }

    /**
     * 根据运费末班ID启用某个模板，同时更新该商家的其他模板为禁用状态
     * @param id
     * @return
     * @throws Exception
     */
    public Boolean transportInUse(Integer sellerId, Integer id) throws Exception {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            sellerTransportWriteDao.updateUnUseBySellerId(sellerId);
            sellerTransportWriteDao.updateInUseById(id);
            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}
