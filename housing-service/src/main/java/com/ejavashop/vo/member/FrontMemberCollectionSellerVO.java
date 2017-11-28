package com.ejavashop.vo.member;

import com.ejavashop.entity.member.MemberCollectionSeller;
import com.ejavashop.entity.seller.Seller;

/**
 * 会员收藏商铺表VO
 *
 */
public class FrontMemberCollectionSellerVO extends MemberCollectionSeller {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6296294908156238247L;
    private String            sellerName;                              // 店铺名称
    private String            sellerLogo;                              // 商家信息
    private Seller            seller;                                  // 店铺对象

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerLogo() {
        return sellerLogo;
    }

    public void setSellerLogo(String sellerLogo) {
        this.sellerLogo = sellerLogo;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

}