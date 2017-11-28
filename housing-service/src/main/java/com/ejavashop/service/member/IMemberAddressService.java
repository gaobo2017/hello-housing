package com.ejavashop.service.member;

import java.util.List;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.MemberAddress;

/**
 * 用户收获地址接口
 *                       
 * @Filename: IMemberAddressService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IMemberAddressService {

    /**
     * 根据会员ID获取会员地址
     * @param memberId 会员ID
     * @param pager 分页信息
     * @return
     */
    ServiceResult<List<MemberAddress>> getMemberAddresses(Integer memberId, PagerInfo pager);

    /**
     * 根据用户ID获得收货地址
     * @param memberId
     * @return
     */
    ServiceResult<List<MemberAddress>> getMemberAddressByMId(Integer memberId);

    /**
     * 根据id获得收货地址信息
     * @param  id  收货地址id
     * @return
     */
    ServiceResult<MemberAddress> getMemberAddressById(Integer id);

    /**
     * 保存收货地址
     * @param memberAddress
     * @return
     */
    ServiceResult<Boolean> saveMemberAddress(MemberAddress memberAddress);

    /**
     * 更新收货地址
     * @param  
     * @return
     */
    ServiceResult<Boolean> updateMemberAddress(MemberAddress memberAddress);

    /**
     * 删除收货地址
     * @param id 地址ID
     * @param memberId 用户ID
     * @return
     */
    ServiceResult<Boolean> deleteMemberAddress(Integer id, Integer memberId);

    /**
     * 设置为默认地址
     * @param id 默认地址ID
     * @param memberId 用户ID
     * @return
     */
    ServiceResult<Boolean> defaultMemberAddress(Integer id, Integer memberId);

}
