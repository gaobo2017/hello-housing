package com.ejavashop.service.news;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.news.NewsType;

public interface INewsTypeService {

    /**
     * 根据id取得文章分类
     * @param  newsTypeId
     * @return
     */
    ServiceResult<NewsType> getNewsTypeById(Integer newsTypeId);

    /**
     * 保存文章分类
     * @param  newsType
     * @return
     */
    ServiceResult<Integer> saveNewsType(NewsType newsType);

    /**
    * 更新文章分类
    * @param  newsType
    * @return
    */
    ServiceResult<Integer> updateNewsType(NewsType newsType);

    /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<NewsType>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 所有分类列表
     * @return
     */
    List<NewsType> list();

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);
}