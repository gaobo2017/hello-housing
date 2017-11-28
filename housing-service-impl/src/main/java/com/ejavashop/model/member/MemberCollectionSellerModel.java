package com.ejavashop.model.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.member.MemberCollectionSellerReadDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.dao.shop.write.member.MemberCollectionSellerWriteDao;
import com.ejavashop.entity.member.MemberCollectionSeller;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.vo.member.FrontMemberCollectionSellerVO;

/**
 * 会员收藏店铺
 *
 * @Filename: MemberCollectionSellerModel.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Component(value = "memberCollectionSellerModel")
public class MemberCollectionSellerModel {

    @Resource
    private MemberCollectionSellerWriteDao memberCollectionSellerWriteDao;

    @Resource
    private MemberCollectionSellerReadDao  memberCollectionSellerReadDao;

    @Resource
    private SellerReadDao                  sellerReadDao;

    /**
     * 会员收藏商铺
     * @param sellerId
     * @param memberId
     * @return 如果会员已经收藏过该店铺，返回true，收藏成功返回true
     */
    public Boolean collectionSeller(Integer sellerId, Integer memberId) {
        // 参数校验
        if (sellerId == null) {
            throw new BusinessException("店铺不能为空，请重试！");
        }
        if (memberId == null) {
            throw new BusinessException("会员不能为空，请重试！");
        }

        //判断会员是否收藏过该商家 ,如果收藏过且是删除的，置为关注状态
        List<MemberCollectionSeller> list = memberCollectionSellerWriteDao
            .getBySellerIdAndMId(sellerId, memberId);
        // 如果查询结果是空，则新增加一条数据
        if (list == null || list.size() == 0) {
            MemberCollectionSeller memberCollectionSeller = new MemberCollectionSeller();
            memberCollectionSeller.setMemberId(memberId);
            memberCollectionSeller.setSellerId(sellerId);
            memberCollectionSeller.setState(MemberCollectionSeller.STATE_1);
            //1、保存信息
            Integer count = memberCollectionSellerWriteDao.save(memberCollectionSeller);
            if (count == 0) {
                throw new BusinessException("收藏商铺失败，请重试！");
            }

        } else if (list.size() > 0) {
            MemberCollectionSeller memberCollectionSeller = list.get(0);
            if (memberCollectionSeller.getState().equals(MemberCollectionSeller.STATE_2)) {
                //1、保存信息
                memberCollectionSeller.setState(MemberCollectionSeller.STATE_1);
                Integer count = memberCollectionSellerWriteDao.update(memberCollectionSeller);
                if (count == 0) {
                    throw new BusinessException("收藏商铺失败，请重试！");
                }
            }
        }
        return true;

    }

    /**
     * 取消收藏商铺
     * @param sellerId
     * @param memberId
     * @return
     */
    public Boolean cancelCollectionSeller(Integer sellerId, Integer memberId) {
        // 参数校验
        if (sellerId == null) {
            throw new BusinessException("店铺不能为空，请重试！");
        }
        if (memberId == null) {
            throw new BusinessException("会员不能为空，请重试！");
        }

        // 不管是否收藏过该店铺，直接全部修改成删除状态
        memberCollectionSellerWriteDao.cancelCollectionSeller(sellerId, memberId);

        return true;
    }

    /**
    * 根据id取得会员收藏商铺表
    * @param  memberCollectionSellerId
    * @return
    */
    public MemberCollectionSeller getMemberCollectionSellerById(Integer memberCollectionSellerId) {
        return memberCollectionSellerReadDao.get(memberCollectionSellerId);
    }

    /**
     * 根据会员id取得会员收藏商铺表
     * @param  memberId
     * @return
     */
    public List<FrontMemberCollectionSellerVO> getCollectionSellerByMemberid(Integer memberId,
                                                                             PagerInfo pager) {

        //取所有未删除的收藏店铺
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("memberId", memberId);
        queryMap.put("state", MemberCollectionSeller.STATE_1);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(memberCollectionSellerReadDao.queryCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        queryMap.put("start", start);
        queryMap.put("size", size);
        List<MemberCollectionSeller> beanList = memberCollectionSellerReadDao.queryList(queryMap);

        List<FrontMemberCollectionSellerVO> collSellerList = new ArrayList<FrontMemberCollectionSellerVO>();
        for (MemberCollectionSeller collSeller : beanList) {
            //获得对应商家信息
            Seller seller = sellerReadDao.get(collSeller.getSellerId());

            FrontMemberCollectionSellerVO collSellerVO = new FrontMemberCollectionSellerVO();
            //设置商家名称
            collSellerVO.setSellerName(seller.getSellerName());
            //设置商家logo
            collSellerVO.setSellerLogo(seller.getSellerLogo());
            collSellerVO.setSeller(seller);
            collSellerVO.setId(collSeller.getId());
            collSellerVO.setCreateTime(collSeller.getCreateTime());
            collSellerVO.setMemberId(collSeller.getMemberId());
            collSellerVO.setSellerId(collSeller.getSellerId());
            collSellerVO.setState(collSeller.getState());
            collSellerVO.setUpdateTime(collSeller.getUpdateTime());

            collSellerList.add(collSellerVO);
        }
        return collSellerList;
    }

    /**
     * 根据用户ID和店铺ID获取收藏信息
     * @param sellerId
     * @param memberId
     * @return
     */
    public List<MemberCollectionSeller> getBySellerIdAndMId(Integer sellerId, Integer memberId) {
        return memberCollectionSellerWriteDao.getBySellerIdAndMId(sellerId, memberId);
    }
}
