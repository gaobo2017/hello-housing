package com.ejavashop.model.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.member.MemberCollectionProductReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.write.member.MemberCollectionProductWriteDao;
import com.ejavashop.entity.member.MemberCollectionProduct;
import com.ejavashop.entity.product.Product;
import com.ejavashop.util.FrontProductPictureUtil;
import com.ejavashop.vo.member.FrontMemberCollectionProductVO;

import net.sf.cglib.beans.BeanCopier;

/**
 * 用户收藏商品
 *                       
 * @Filename: MemberCollectionProductModel.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Component(value = "memberCollectionProductModel")
public class MemberCollectionProductModel {

    @Resource
    private MemberCollectionProductWriteDao memberCollectionProductWriteDao;

    @Resource
    private MemberCollectionProductReadDao  memberCollectionProductReadDao;

    @Resource
    private ProductReadDao                  productReadDao;

    /**
     * 产品图片获取工具类
     */
    @Resource
    private FrontProductPictureUtil         frontProductPictureUtil;

    /**
    * 根据id取得会员收藏商品表
    * @param  memberCollectionProductId
    * @return
    */
    public MemberCollectionProduct getMemberCollectionProductById(Integer memberCollectionProductId) {
        return memberCollectionProductReadDao.get(memberCollectionProductId);
    }

    /**
     * 根据会员id取得会员收藏商品表
     * @param request
     * @param pager
     * @return
     */
    public List<FrontMemberCollectionProductVO> getCollectionProductByMemberId(Map<String, Object> queryMap,
                                                                               Integer memberId,
                                                                               PagerInfo pager) {

        //取所有未删除的收藏商品
        queryMap.put("memberId", memberId);
        queryMap.put("state", ConstantsEJS.MEMBER_COLLECTION_PRODUCT_STATE_1);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(memberCollectionProductReadDao.queryCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        queryMap.put("start", start);
        queryMap.put("size", size);
        List<MemberCollectionProduct> beanList = memberCollectionProductReadDao.queryList(queryMap);

        List<FrontMemberCollectionProductVO> collProductList = new ArrayList<FrontMemberCollectionProductVO>();
        for (MemberCollectionProduct productBean : beanList) {
            //获得产品对应的小图 
            String productLeadLittle = frontProductPictureUtil
                .getproductLeadMiddle(productBean.getProductId());

            //获得产品详细信息
            Product product = productReadDao.get(productBean.getProductId());

            FrontMemberCollectionProductVO collProductVO = new FrontMemberCollectionProductVO();
            final BeanCopier copier = BeanCopier.create(MemberCollectionProduct.class,
                FrontMemberCollectionProductVO.class, false);
            copier.copy(productBean, collProductVO, null);
            collProductVO.setProductLeadLittle(productLeadLittle);
            collProductVO.setProductName(product.getName1());
            collProductVO.setCommentsNumber(product.getCommentsNumber());
            collProductVO.setMallPcPrice(product.getMallPcPrice());
            collProductVO.setMallMobilePrice(product.getMalMobilePrice());
            collProductVO.setSellerId(product.getSellerId());

            //封装产品列表
            collProductList.add(collProductVO);
        }
        return collProductList;
    }

    /**
     * 保存会员收藏商品表
     * @param productId
     * @param request
     * @return
     */
    public MemberCollectionProduct saveMemberCollectionProduct(Integer productId,
                                                               Integer memberId) {
        // 参数校验
        if (productId == null || productId == 0) {
            throw new BusinessException("产品id不能为空，请重试！");
        }

        //判断会员是否收藏过该产品 ,如果收藏过且是删除的，置为关注状态
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("productId", productId);
        queryMap.put("memberId", memberId);
        List<MemberCollectionProduct> productList = memberCollectionProductWriteDao
            .queryList(queryMap);

        MemberCollectionProduct product = null;
        if (productList.size() == 0) {
            product = new MemberCollectionProduct();
            product.setMemberId(memberId);
            product.setProductId(productId);
            product.setState(ConstantsEJS.MEMBER_COLLECTION_PRODUCT_STATE_1);
            //1、保存信息
            Integer count = memberCollectionProductWriteDao.save(product);
            if (count == 0) {
                throw new BusinessException("会员收藏商品失败，请重试！");
            }
        } else if (productList.size() > 0) {
            product = productList.get(0);
            if (product.getState().equals(ConstantsEJS.MEMBER_COLLECTION_PRODUCT_STATE_2)) {
                //1、保存信息
                product.setState(ConstantsEJS.MEMBER_COLLECTION_PRODUCT_STATE_1);
                Integer count = memberCollectionProductWriteDao.update(product);
                if (count == 0) {
                    throw new BusinessException("会员收藏商品失败，请重试！");
                }
            }

        }
        return product;
    }

    /**
     * 取消收藏商品
     * @param productId
     * @param request
     * @return
     */
    public MemberCollectionProduct cancelCollectionProduct(Integer productId, Integer memberId) {
        // 参数校验
        if (productId == null) {
            throw new BusinessException("产品id不能为空，请重试！");
        }

        //判断会员是否收藏过该商品
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("productId", productId);
        queryMap.put("memberId", memberId);
        List<MemberCollectionProduct> beanList = memberCollectionProductWriteDao
            .queryList(queryMap);
        MemberCollectionProduct bean = null;
        if (beanList.size() > 0) {
            bean = beanList.get(0);
            bean.setState(ConstantsEJS.MEMBER_COLLECTION_PRODUCT_STATE_2);
            //1、保存信息
            Integer count = memberCollectionProductWriteDao.update(bean);
            if (count == 0) {
                throw new BusinessException("商品取消收藏失败，请重试！");
            }
        }

        return bean;
    }
}
