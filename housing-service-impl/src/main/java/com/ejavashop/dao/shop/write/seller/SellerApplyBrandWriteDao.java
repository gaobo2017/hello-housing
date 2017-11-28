package com.ejavashop.dao.shop.write.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerApplyBrand;

/**
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
@Repository
public interface SellerApplyBrandWriteDao {
    /**
     * 根据id查询
     * @param id
     * @return
     */
    SellerApplyBrand getById(Integer id);

    /**
     * 根据name查询SellerApplyBrand，用于检测name是否唯一
     * @param name
     * @return
     */
    Integer getByName(@Param("name") String name);

    /**
     * 根据条件查询brand的count
     * @param queryMap
     * @return
     */
    Integer count(@Param("param1") Map<String, String> queryMap);

    /**
     * 分页查询
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<SellerApplyBrand> page(@Param("param1") Map<String, String> queryMap,
                                @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 待提交审核数
     * @param queryMap
     * @return
     */
    Integer todoCount(@Param("param1") Map<String, String> queryMap);

    /**
     * 待提交审核列表
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<SellerApplyBrand> todoList(@Param("param1") Map<String, String> queryMap,
                                    @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 保存brand<br/>
     * @param brand
     * @return
     */
    Integer save(SellerApplyBrand brand);

    /**
     * 修改brand<br/>
     * <li>商家提交申请品牌：修改state状态为 1（提交审核）</li>
     * <li>平台审核品牌成功：修改state状态为 2（审核成功）</li>
     * <li>平台审核品牌失败：修改state状态为 3（审核失败）</li>
     * <li>删除功能：修改state状态为 4（删除）</li>
     * @param brand
     * @return
     */
    Integer update(SellerApplyBrand brand);

}
