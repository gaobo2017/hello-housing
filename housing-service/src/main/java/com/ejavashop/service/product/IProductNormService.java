package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductNorm;
import com.ejavashop.entity.product.ProductNormAttr;
import com.ejavashop.entity.product.ProductNormAttrOpt;
import com.ejavashop.entity.product.ProductType;

/**
 * 商品规格、规格属性管理
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
public interface IProductNormService {

    /**
     * 保存商品规格
     * @param norm
     * @return
     */
    ServiceResult<Boolean> saveNorm(ProductNorm norm);

    /**
     * 保存商品规格、规格属性
     * @param map key:norm、attr
     * @return
     */
    ServiceResult<Boolean> saveNorm(Map<String, Object> map);

    /**
     * 根据id查询商品规格
     * @param id
     * @return
     */
    ServiceResult<ProductNorm> getNormById(Integer id);

    /**
     * 根据ids查询商品规格
     * @param ids
     * @return
     */
    ServiceResult<List<ProductNorm>> getNormByIds(String ids);

    /**
     * 分页展示商品规格
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ProductNorm>> pageNorm(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 更新商品规格
     * @param norm
     * @return
     */
    ServiceResult<Boolean> updateNorm(ProductNorm norm);

    /**
     * 更新商品规格
     * @param map key:norm attr
     * @return
     */
    ServiceResult<Boolean> updateNorm(Map<String, Object> map);

    /**
     * 根据规格id查询商品类型,检查规格是否被占用
     * @param id
     * @return
     */
    ServiceResult<List<ProductType>> chkHasUsed(Integer id);

    /**
     * 商城商品规格
     * @param id
     * @return
     */
    ServiceResult<Boolean> delNorm(Integer id);

    /**
     * 保存商品规格属性
     * @param attr
     * @return
     */
    ServiceResult<Boolean> saveNormAttr(ProductNormAttr attr);

    /**
     * 根据商品规格id查询商品规格属性
     * @param id
     * @return
     */
    ServiceResult<ProductNormAttr> getNormAttrById(Integer id);

    /**
     * 根据normId获取attr
     * @param id
     * @return
     */
    ServiceResult<List<ProductNormAttr>> getAttrByNormId(Integer id);

    /**
     * 根据normId获取attr
     * @param ids
     * @return
     */
    ServiceResult<List<ProductNormAttr>> getAttrByNormIds(String ids);

    /**
     * 分页列表展示商品规格属性
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ProductNormAttr>> pageNormAttr(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 修改商品规格属性
     * @param attr
     * @return
     */
    ServiceResult<Boolean> updateNormAttr(ProductNormAttr attr);

    /**
     * 删除商品规格属性
     * @param id
     * @return
     */
    ServiceResult<Boolean> delNormAttr(Integer id);

    /**********************商品选定的规格属性操作************************/

    /**
     * 保存商品选定的规格属性
     * @param opt
     * @return
     */
    ServiceResult<Boolean> saveNormAttrOpt(ProductNormAttrOpt opt);

    /**
     * 修改商品选定的规格属性
     * @param opt
     * @return
     */
    ServiceResult<Boolean> updateNormAttrOpt(ProductNormAttrOpt opt);

    /**
     * 根据id删除商品选定的规格属性
     * @param id
     * @return
     */
    ServiceResult<Boolean> delNormAttrOpt(Integer id);

    /**
     * 分页获取商品选定的规格属性
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ProductNormAttrOpt>> pageNormAttrOpt(Map<String, String> queryMap,
                                                            PagerInfo pager);

    /**
     * 根据id获取商品选定的规格属性
     * @param id
     * @return
     */
    ServiceResult<ProductNormAttrOpt> getNormAttrOptById(Integer id);

    /**
     * 查询所有的规格
     * @return
     */
    ServiceResult<List<ProductNorm>> listNoPage();

}
