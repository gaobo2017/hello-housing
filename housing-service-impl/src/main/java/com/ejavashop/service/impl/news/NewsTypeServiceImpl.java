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
import com.ejavashop.entity.news.NewsType;
import com.ejavashop.model.news.NewsTypeModel;
import com.ejavashop.service.news.INewsTypeService;

@Service(value = "newsTypeService")
public class NewsTypeServiceImpl implements INewsTypeService {
    private static Logger log = LogManager.getLogger(NewsTypeServiceImpl.class);

    @Resource
    private NewsTypeModel newsTypeModel;

    /**
    * 根据id取得文章分类
    * @param  newsTypeId
    * @return
    */
    @Override
    public ServiceResult<NewsType> getNewsTypeById(Integer newsTypeId) {
        ServiceResult<NewsType> result = new ServiceResult<NewsType>();
        try {
            result.setResult(newsTypeModel.get(newsTypeId));
        } catch (Exception e) {
            log.error("根据id[" + newsTypeId + "]取得文章分类时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + newsTypeId + "]取得文章分类时出现未知异常");
        }
        return result;
    }

    /**
     * 保存文章分类
     * @param  newsType
     * @return
     */
    @Override
    public ServiceResult<Integer> saveNewsType(NewsType newsType) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();

        try {
            result.setResult(newsTypeModel.save(newsType));
        } catch (Exception e) {
            log.error("保存文章分类时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存文章分类时出现未知异常");
        }
        return result;
    }

    /**
    * 更新文章分类
    * @param  newsType
    * @return
    */
    @Override
    public ServiceResult<Integer> updateNewsType(NewsType newsType) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();

        try {
            result.setResult(newsTypeModel.update(newsType));
        } catch (Exception e) {
            log.error("更新文章分类时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新文章分类时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<NewsType>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<NewsType>> serviceResult = new ServiceResult<List<NewsType>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(newsTypeModel.getCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<NewsType> list = newsTypeModel.page(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[NewsTypeServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[NewsTypeServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public List<NewsType> list() {
        List<NewsType> list = newsTypeModel.list();
        return list;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> sr = new ServiceResult<Boolean>();
        try {
            if (id == null)
                throw new BusinessException("删除文章类型[" + id + "]时出错");
            sr.setResult(this.newsTypeModel.del(id) > 0);
        } catch (Exception e) {
            log.error("[NewsTypeServiceImpl][del] exception:" + e.getMessage());
            e.printStackTrace();
        }
        return sr;
    }

}