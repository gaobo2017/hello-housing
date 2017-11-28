package com.ejavashop.service.impl.news;

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
import com.ejavashop.entity.news.NewsPartner;
import com.ejavashop.model.news.NewsPartnerModel;
import com.ejavashop.service.news.INewsPartnerService;

@Service(value = "newsPartnerService")
public class NewsPartnerServiceImpl implements INewsPartnerService {
    private static Logger    log = LogManager.getLogger(NewsPartnerServiceImpl.class);

    @Resource
    private NewsPartnerModel newsPartnerModel;

    /**
    * 根据id取得合作伙伴
    * @param  newsPartnerId
    * @return
    */
    @Override
    public ServiceResult<NewsPartner> getById(Integer newsPartnerId) {
        ServiceResult<NewsPartner> result = new ServiceResult<NewsPartner>();
        try {
            result.setResult(newsPartnerModel.get(newsPartnerId));
        } catch (Exception e) {
            log.error("根据id[" + newsPartnerId + "]取得合作伙伴时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + newsPartnerId + "]取得合作伙伴时出现未知异常");
        }
        return result;
    }

    /**
     * 保存合作伙伴
     * @param  newsPartner
     * @return
     */
    @Override
    public ServiceResult<Integer> save(NewsPartner newsPartner) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();

        try {
            result.setResult(newsPartnerModel.save(newsPartner));
        } catch (Exception e) {
            log.error("保存合作伙伴时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存合作伙伴时出现未知异常");
        }
        return result;
    }

    /**
    * 更新合作伙伴
    * @param  newsPartner
    * @return
    */
    @Override
    public ServiceResult<Integer> update(NewsPartner newsPartner) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();

        try {
            result.setResult(newsPartnerModel.update(newsPartner));
        } catch (Exception e) {
            log.error("更新合作伙伴时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新合作伙伴时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<NewsPartner>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<NewsPartner>> serviceResult = new ServiceResult<List<NewsPartner>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(newsPartnerModel.getCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<NewsPartner> list = newsPartnerModel.page(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[BrandServiceImpl][save] param1:" + JSON.toJSONString(queryMap) + " &param2:"
                      + JSON.toJSONString(pager));
            log.error("[BrandServiceImpl][page] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public List<NewsPartner> list() {
        List<NewsPartner> list = newsPartnerModel.list();
        return list;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> sr = new ServiceResult<Boolean>();
        try {
            if (id == null)
                throw new BusinessException("删除[" + id + "]时出错");
            sr.setResult(this.newsPartnerModel.del(id) > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sr;
    }
}