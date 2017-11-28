package com.ejavashop.dao.shop.write.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.Seller;

@Repository
public interface SellerWriteDao {

    /**
     * 根据用户ID修改商家表
     * @param memberId
     * @return
     */
    Integer deleteByMemberId(Integer memberId);

    /**
     * 根据用户ID修改商家状态，用户ID为条件修改商家状态
     * @param memberId
     * @param auditStatus
     * @return
     */
    Integer auditSeller(@Param("memberId") Integer memberId,
                        @Param("auditStatus") Integer auditStatus);

    /**
     * 冻结或者解冻商家
     * @param id 商家ID
     * @param auditStatus auditStatus=3冻结商家；auditStatus=2解冻商家
     * @return
     */
    Integer freezeSeller(@Param("id") Integer id, @Param("auditStatus") Integer auditStatus);

    Seller get(Integer id);

    Integer save(Seller seller);

    /**
     * 更新对象中不为null的字段
     * @param seller
     * @return
     */
    Integer update(Seller seller);

    /**
     * 以用户ID获取商家
     * @param userid
     * @return
     */
    Seller getSellerByMemberId(Integer memberId);

    Integer getSellersCount(@Param("queryMap") Map<String, String> queryMap);

    List<Seller> getSellers(@Param("queryMap") Map<String, String> queryMap,
                            @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 根据用户ID批量删除商家
     * @param ids
     * @return
     */
    Integer deleteByMemberIds(@Param("memberIds") List<Integer> memberIds);

    /**
     * 根据名称取商家
     * @param name
     * @return
     */
    List<Seller> getSellerByName(@Param("name") String name);

    /**
     * 根据店铺名称取商家
     * @param name
     * @return
     */
    List<Seller> getSellerBySellerName(@Param("sellerName") String sellerName);
}
