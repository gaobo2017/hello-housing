package com.ejavashop.model.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.product.ProductAttrReadDao;
import com.ejavashop.dao.shop.read.product.ProductBrandReadDao;
import com.ejavashop.dao.shop.read.product.ProductCateReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.read.product.ProductTypeAttrReadDao;
import com.ejavashop.dao.shop.read.product.ProductTypeReadDao;
import com.ejavashop.dao.shop.read.seller.SellerCateReadDao;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAttr;
import com.ejavashop.entity.product.ProductBrand;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.entity.seller.SellerCate;
import com.ejavashop.vo.product.ProductTypeAttrVO;

@Component(value = "productFrontModel")
public class ProductFrontModel {

    @Resource
    private ProductReadDao         productReadDao;
    @Resource
    private ProductCateReadDao     productCateReadDao;
    @Resource
    private ProductBrandReadDao    productBrandReadDao;
    @Resource
    private ProductAttrReadDao     productAttrReadDao;
    @Resource
    private ProductTypeReadDao     productTypeReadDao;
    @Resource
    private ProductTypeAttrReadDao productTypeAttrReadDao;

    @Resource
    private SellerCateReadDao      sellerCateReadDao;

    public List<Product> getProductsBySellerId(Integer sellerid) {
        if (sellerid == null)
            throw new BusinessException("没有商家");
        return productReadDao.getProductsBySellerId(sellerid);
    }

    /**
     * 根据分类列表页查询商品
     * @param cateId
     * @param mapCondition
     * @param stack
     */
    public void getProducts(Integer cateId, Map<String, Object> mapCondition,
                            Map<String, Object> stack) {
        //取得分类
        if (cateId == null) {
            throw new BusinessException("传入的分类路径不能为空");
        }
        ProductCate productCate = findCateAll(cateId, stack);

        //取得类型
        ProductType productType = productTypeReadDao.get(productCate.getProductTypeId());

        //查询出类型下面关联的品牌，并对首字母进行排序
        findBrandAll(stack, productType.getProductBrandIds());

        //查询出类型下面关联的查询属性
        List<ProductTypeAttr> productTypeAttrs = productTypeAttrReadDao
            .getByTypeIdAndQuery(productType.getId());
        List<ProductTypeAttr> productTypeAttrsAll = new ArrayList<ProductTypeAttr>();
        for (ProductTypeAttr productTypeAttr : productTypeAttrs) {
            productTypeAttrsAll.add(productTypeAttr);
        }
        mapCondition.put("productTypeAttrsAll", productTypeAttrsAll);

        //组装类型查询Map对象，键是ID，值是查询的对象
        Map<String, ProductTypeAttr> mapProductTypeAttrs = new HashMap<String, ProductTypeAttr>();
        for (ProductTypeAttr productTypeAttr : productTypeAttrs) {
            mapProductTypeAttrs.put(productTypeAttr.getId().toString(), productTypeAttr);
        }

        //查询出商品
        Integer sort = 0;
        try {
            sort = (Integer) mapCondition.get("sort");
        } catch (Exception e) {
            sort = 0;
        }
        if (sort == null || sort > 4) {
            sort = 0;
        }
        stack.put("sort", sort);

        Integer doSelf = (Integer) mapCondition.get("doSelf");
        Integer store = (Integer) mapCondition.get("store");
        List<Product> products = productReadDao.getByCateId(cateId, sort, doSelf, store);

        //根据查询条件过滤商品的到查询条件的Name的集合
        Set<Integer> productTypeAttrIds = filterQueryAttr(mapCondition, stack, mapProductTypeAttrs,
            products);
        noQueryProductAttr(mapCondition, stack, mapProductTypeAttrs, productTypeAttrIds);

        //在查询条件中去掉已经存在的条件
        filterRemoveQueryAttr(productTypeAttrs, productTypeAttrIds);

        //拼装列表页查询条件的VO对象
        installProductAttr(stack, productTypeAttrs);

        //筛选品牌
        filterBrand(mapCondition, stack, products);

        //分页
        pageProductsList(mapCondition, stack, products);
    }

    /**
     * 没有查询出商品，点击过滤条件的筛选功能
     * @param mapCondition
     * @param stack
     * @param mapProductTypeAttrs
     * @param productTypeAttrIds
     */
    private void noQueryProductAttr(Map<String, Object> mapCondition, Map<String, Object> stack,
                                    Map<String, ProductTypeAttr> mapProductTypeAttrs,
                                    Set<Integer> productTypeAttrIds) {
        if (productTypeAttrIds.size() == 0) {
            String whereall = (String) mapCondition.get("filterAll");
            if (whereall != null && !"".equals(whereall.trim())) {
                Map<String, String> productTypeAttrWhereAlls = new HashMap<String, String>();
                String[] wherealls = whereall.split("-");
                for (String strings : wherealls) {
                    String[] wherealls_ = strings.split("_");
                    if (wherealls_.length == 2) {
                        int attrId = Integer.valueOf(wherealls_[0]).intValue(); //属性ID
                        int attrValueIndex = Integer.valueOf(wherealls_[1]).intValue(); //属性值所属的ID位置
                        productTypeAttrIds.add(attrId);
                        String attrName = null;//查询属性名称
                        String attrValue = null;//存放属性值
                        ProductTypeAttr productTypeAttr = mapProductTypeAttrs.get(attrId + "");
                        if (productTypeAttr != null) {
                            String[] trypeValues = productTypeAttr.getValue().split(",");
                            attrValue = trypeValues[attrValueIndex];
                            attrName = productTypeAttr.getName();
                        }
                        if (attrValue != null && attrName != null) {
                            productTypeAttrWhereAlls.put(strings, attrName + ":" + attrValue);
                        }
                    }
                }
                stack.put("productTypeAttrWhereAlls", productTypeAttrWhereAlls);
            }
        }
    }

    /**
     * 分页
     * @param mapCondition
     * @param stack
     * @param products
     * @param copier
     */
    private void pageProductsList(Map<String, Object> mapCondition, Map<String, Object> stack,
                                  List<Product> products) {
        int productSize = products.size();
        stack.put("productSize", productSize);
        Integer page = 0;
        try {
            page = (Integer) mapCondition.get("page");
        } catch (Exception e) {
            page = 0;
        }
        if (page == null || page.intValue() == 0 || page.intValue() == 1) {
            page = 1;
        }

        int startNumber = (page - 1) * ConstantsEJS.DEFAULT_PAGE_SIZE;
        int endNumber = page * ConstantsEJS.DEFAULT_PAGE_SIZE;
        int pageEndNumber = (productSize > endNumber) ? endNumber : productSize;
        List<Product> productsResult = new ArrayList<Product>();
        for (int i = startNumber; i < pageEndNumber; i++) {
            productsResult.add(products.get(i));
        }

        stack.put("producListVOs", productsResult);
    }

    /**
     * 拼装列表页查询条件的VO对象
     * @param stack
     * @param productTypeAttrs
     */
    private void installProductAttr(Map<String, Object> stack,
                                    List<ProductTypeAttr> productTypeAttrs) {
        List<ProductTypeAttrVO> productTypeAttrVOs = new ArrayList<ProductTypeAttrVO>();
        for (ProductTypeAttr productTypeAttr : productTypeAttrs) {
            ProductTypeAttrVO productTypeAttrVO = new ProductTypeAttrVO();
            productTypeAttrVO.setId(productTypeAttr.getId());
            productTypeAttrVO.setName(productTypeAttr.getName());
            productTypeAttrVO.setValue(productTypeAttr.getValue());

            String value = productTypeAttr.getValue();
            if (value != null && !"".equals(value)) {
                List<String> values = new ArrayList<String>();
                String[] str = value.split(",");
                for (int i = 0; i < str.length; i++) {
                    values.add(str[i]);
                }
                productTypeAttrVO.setValues(values);
            }
            productTypeAttrVOs.add(productTypeAttrVO);
        }
        stack.put("productTypeAttrVOs", productTypeAttrVOs);
    }

    /**
     * 删除已经选择的查询条件
     * @param productTypeAttrs 类型查询属性的集合
     * @param productTypeAttrIds 已经选择查询条件ID的集合
     */
    private void filterRemoveQueryAttr(List<ProductTypeAttr> productTypeAttrs,
                                       Set<Integer> productTypeAttrIds) {
        if (productTypeAttrIds.size() > 0) {
            for (Iterator<ProductTypeAttr> iterator = productTypeAttrs.iterator(); iterator
                .hasNext();) {
                ProductTypeAttr productTypeAttr = iterator.next();
                for (Integer productTypeAttrId : productTypeAttrIds) {
                    if (productTypeAttrId.intValue() == productTypeAttr.getId().intValue()) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
    }

    /**
     * 根据查询条件过滤商品
     * @param mapCondition
     * @param stack
     * @param mapProductTypeAttrs 类型查询属性的Map集合
     * @param products 商品
     * @return
     */
    private Set<Integer> filterQueryAttr(Map<String, Object> mapCondition,
                                         Map<String, Object> stack,
                                         Map<String, ProductTypeAttr> mapProductTypeAttrs,
                                         List<Product> products) {
        Set<Integer> productTypeAttrIds = new HashSet<Integer>();
        //存放已经查找的Product
        List<Product> productWhereall = new ArrayList<Product>();
        String whereall = (String) mapCondition.get("filterAll");
        if (whereall != null && !"".equals(whereall.trim())) {
            Map<String, String> productTypeAttrWhereAlls = new HashMap<String, String>();
            String[] wherealls = whereall.split("-");
            if (wherealls.length > 0) {
                for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
                    Product product = iterator.next();
                    int countAttr = filterAttrCount(productTypeAttrIds, productTypeAttrWhereAlls,
                        wherealls, product, mapProductTypeAttrs);
                    if (countAttr == wherealls.length) {
                        productWhereall.add(product);
                    }
                }
                products.clear();
                products.addAll(productWhereall);
            }
            stack.put("productTypeAttrWhereAlls", productTypeAttrWhereAlls);
        }
        return productTypeAttrIds;
    }

    /**
     * 判断商品是否存在查询条件中
     * @param productTypeAttrIds 查询条件的Id集合
     * @param productTypeAttrWhereAlls 查询条件name和value
     * @param wherealls 查询条件
     * @param product 商品
     * @param productTypeAttrs 分类的查询属性
     * @return
     */
    private int filterAttrCount(Set<Integer> productTypeAttrIds,
                                Map<String, String> productTypeAttrWhereAlls, String[] wherealls,
                                Product product, Map<String, ProductTypeAttr> mapProductTypeAttrs) {
        int countAttr = 0;
        List<ProductAttr> productAttrs = productAttrReadDao.getByProductId(product.getId());
        for (ProductAttr productAttr : productAttrs) {
            int productTypeAttrId = productAttr.getProductTypeAttrId().intValue();
            String value = productAttr.getValue();
            for (String string : wherealls) {
                String[] strings = string.split("_");
                if (strings.length == 2) {
                    int attrId = Integer.valueOf(strings[0]).intValue(); //属性ID
                    int attrValueIndex = Integer.valueOf(strings[1]).intValue(); //属性值所属的ID位置
                    productTypeAttrIds.add(attrId);
                    //                    Integer attrIdNew = null;//查询属性ID
                    String attrName = null;//查询属性名称
                    String attrValue = null;//存放属性值
                    if (productTypeAttrId == attrId) { //查询属性ID和商品所有的查询属性ID相同，继续判断属性值是否存在，存在包含
                        ProductTypeAttr productTypeAttr = mapProductTypeAttrs.get(attrId + "");
                        if (productTypeAttr != null) {
                            String[] trypeValues = productTypeAttr.getValue().split(",");
                            attrValue = trypeValues[attrValueIndex];
                            attrName = productTypeAttr.getName();
                            //                            attrIdNew = productTypeAttr.getId();
                        }
                    }

                    if (attrValue != null && value.equals(attrValue)) {
                        countAttr++;
                    }
                    if (attrValue != null && attrName != null) {
                        productTypeAttrWhereAlls.put(string, attrName + ":" + attrValue);
                    }
                }
            }
        }
        return countAttr;
    }

    /**
     * 查询分类下面所关联的品牌
     * @param stack
     * @param brandIds
     */
    private void findBrandAll(Map<String, Object> stack, String brandIds) {
        List<ProductBrand> productBrands = productBrandReadDao.getByIds(brandIds);
        stack.put("productBrands", productBrands);
        List<String> productBrandNameFirsts = new ArrayList<String>();
        for (ProductBrand productBrand : productBrands) {
            if (!productBrandNameFirsts.contains(productBrand.getNameFirst())) {
                productBrandNameFirsts.add(productBrand.getNameFirst());
            }
        }
        Collections.sort(productBrandNameFirsts);
        stack.put("productBrandNameFirsts", productBrandNameFirsts);
    }

    /**
     * 查询三级分类、二级分类、一级分类
     * @param cateId
     * @param stack
     * @return 三级分类
     */
    private ProductCate findCateAll(Integer cateId, Map<String, Object> stack) {
        ProductCate productCate = productCateReadDao.get(cateId);
        if (productCate == null) {
            throw new BusinessException("传入的类目不正确不能为空");
        }
        stack.put("productCate", productCate);
        if (productCate.getPid().intValue() != 0) {
            ProductCate productCatePid = productCateReadDao.get(productCate.getPid());
            stack.put("productCatePid", productCatePid);

            List<ProductCate> productCate3s = productCateReadDao.getByPid(productCate.getPid());
            productCate3s.remove(productCate);
            stack.put("productCate3s", productCate3s);

            if (productCatePid.getPid() != 0) {
                ProductCate productCatePidPid = productCateReadDao.get(productCatePid.getPid());
                stack.put("productCatePidPid", productCatePidPid);

                List<ProductCate> productCate2s = productCateReadDao
                    .getByPid(productCatePid.getPid());
                productCate2s.remove(productCatePid);
                stack.put("productCate2s", productCate2s);
            }
        }
        return productCate;
    }

    /**
     * 筛选品牌
     * @param mapCondition
     * @param stack
     * @param products 商品
     */
    private void filterBrand(Map<String, Object> mapCondition, Map<String, Object> stack,
                             List<Product> products) {
        Integer brandId = 0;
        try {
            brandId = (Integer) mapCondition.get("brandId");
        } catch (Exception e) {
            brandId = 0;
        }
        if (brandId != null && brandId != 0) {
            ProductBrand productBrand = productBrandReadDao.getById(brandId);
            stack.put("brandName", "品牌：" + productBrand.getName());
            for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
                Product product = iterator.next();
                if (brandId.intValue() != product.getProductBrandId().intValue()) {
                    iterator.remove();
                }
            }
        } else {
            brandId = 0;
        }
        stack.put("brandId", brandId);
    }

    /**
     * 根据分类查询列表页推荐的头部商品列表
     * @param cateId
     * @return
     */
    public List<Product> getProductListByCateIdTop(int cateId) {
        return productReadDao.getByCateIdTop(cateId, 4);
    }

    /**
     * 根据分类查询列表页推荐的左侧商品列表
     * @param cateId
     * @return
     */
    public List<Product> getProductListByCateIdLeft(int cateId) {
        return productReadDao.getByCateIdLeft(cateId);
    }

    /**
     * 查询二级分类下三级分类的商品
     * @param cateId
     * @return
     */
    public List<Product> getProductListByCateId2(int cateId) {
        return productReadDao.getByCateIdTop(cateId, 5);
    }

    /**
     * 搜索页面推荐商品信息头部
     * @return
     */
    public List<Product> getProductSearchByTop() {
        return productReadDao.getByCateIdTop(0, 4);
    }

    /**
     * 搜索页面推荐商品信息左部
     * @return
     */
    public List<Product> getProductSearchByLeft() {
        return productReadDao.getByCateIdLeft(0);
    }

    /**
      * 查询商家列表页 商品信息
     * @param start 
     * @param size
     * @param cateId 商家分类
     * @param sellerId 商家ID
     * @param sort 排序
     * @return
     */
    public List<Product> getProductListBySellerCateId(int start, int size, int cateId, int sellerId,
                                                      int sort) {
        StringBuilder sb = new StringBuilder();
        if (cateId != 0) {
            SellerCate sellerCate = sellerCateReadDao.get(cateId);
            if (sellerCate.getPid().intValue() != 0) {
                sb.append(sellerCate.getId());
            } else {
                List<SellerCate> listCate = sellerCateReadDao.getByPid(cateId, sellerId);
                sb.append(sellerCate.getId());
                for (SellerCate sellerCate2 : listCate) {
                    sb.append(",");
                    sb.append(sellerCate2.getId());
                }
            }
        }

        return productReadDao.getProductListBySellerCateId(start, size, sb.toString(), sellerId,
            sort);
    }

    /**
     * 根据商家商品分类统计商家商品
     * @param cateId 商家分类
     * @param sellerId 商家ID
     * @return
     */
    public Integer getProductListBySellerCateIdCount(int cateId, int sellerId) {
        StringBuilder sb = new StringBuilder();

        if (cateId != 0) {
            SellerCate sellerCate = sellerCateReadDao.get(cateId);
            if (sellerCate.getPid().intValue() != 0) {
                sb.append(sellerCate.getId());
            } else {
                List<SellerCate> listCate = sellerCateReadDao.getByPid(cateId, sellerId);
                sb.append(sellerCate.getId());
                for (SellerCate sellerCate2 : listCate) {
                    sb.append(",");
                    sb.append(sellerCate2.getId());
                }
            }
        }

        return productReadDao.getProductListBySellerCateIdCount(sb.toString(), sellerId);
    }

}
