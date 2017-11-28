package com.ejavashop.vo.search;

import java.io.Serializable;

public class SearchProductVO implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long  serialVersionUID = -7265668108099137831L;

    private String             id;                                      //商品ID
    private String             brand;                                   //商品品牌
    private String             seller;                                  //商品商家
    private String             cate;                                    //商品分类
    private String             sort;                                    //排序
    private String             name1;                                   //标题
    private String             content;                                 //描述相关，需要分词其他不需要分词

    private String             masterImg;
    private String             mallPcPrice;
    private String             malMobilePrice;
    private String             productStock;
    private String             commentsNumber;
    private String             sellerId;

    public final static String ID_              = "id";
    public final static String BRAND_           = "brand";
    public final static String SELLER_          = "seller";
    public final static String CATE_            = "cate";
    public final static String SORT_            = "sortIndex";
    public final static String CONTENT_         = "content";
    public final static String NAME1_           = "name1";

    public final static String MASTERIMG_       = "masterImg";
    public final static String MALLPCPRICE_     = "mallPcPrice";
    public final static String MALMOBILEPRICE_  = "malMobilePrice";
    public final static String PRODUCTSTOCK_    = "productStock";
    public final static String COMMENTSNUMBER_  = "commentsNumber";
    public final static String SELLERID_        = "sellerId";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getMasterImg() {
        return masterImg;
    }

    public void setMasterImg(String masterImg) {
        this.masterImg = masterImg;
    }

    public String getMallPcPrice() {
        return mallPcPrice;
    }

    public void setMallPcPrice(String mallPcPrice) {
        this.mallPcPrice = mallPcPrice;
    }

    public String getMalMobilePrice() {
        return malMobilePrice;
    }

    public void setMalMobilePrice(String malMobilePrice) {
        this.malMobilePrice = malMobilePrice;
    }

    public String getProductStock() {
        return productStock;
    }

    public void setProductStock(String productStock) {
        this.productStock = productStock;
    }

    public String getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(String commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 搜索条件的拼装
     * @param searchKeyword
     * @return
     */
    public static String searchIndexAssembling(String searchKeyword) {
        StringBuilder sb = new StringBuilder();
        sb.append(SearchProductVO.CONTENT_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.NAME1_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.BRAND_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.SELLER_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.CATE_);
        sb.append(":");
        sb.append(searchKeyword);
        return sb.toString();
    }

    @Override
    public String toString() {
        return "SearchProductVO [id=" + id + ", brand=" + brand + ", seller=" + seller + ", cate="
               + cate + ", sort=" + sort + ", name1=" + name1 + ", content=" + content
               + ", masterImg=" + masterImg + ", mallPcPrice=" + mallPcPrice + ", malMobilePrice="
               + malMobilePrice + ", productStock=" + productStock + ", commentsNumber="
               + commentsNumber + ", sellerId=" + sellerId + "]";
    }

}
