package com.ejavashop.model.member;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.write.member.MemberAddressWriteDao;
import com.ejavashop.entity.member.MemberAddress;

/**
 * 收货地址Model
 *                       
 * @Filename: MemberAddressModel.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Component(value = "memberAddressModel")
public class MemberAddressModel {

    @Resource
    private MemberAddressWriteDao        memberAddressWriteDao;

    @Resource
    private DataSourceTransactionManager transactionManager;

    /**
     * 根据会员ID获取会员地址数量
     * @param memberId
     * @return
     */
    public Integer getMemberAddressesCount(Integer memberId) {
        return memberAddressWriteDao.getMemberAddressesCount(memberId);
    }

    /**
     * 根据会员ID获取会员地址
     * @param memberId
     * @param start
     * @param size
     * @return
     */
    public List<MemberAddress> getMemberAddresses(Integer memberId, Integer start, Integer size) {
        return memberAddressWriteDao.getMemberAddresses(memberId, start, size);
    }

    /**
     * 根据用户ID获得所有收货地址
     * @param memberId
     * @return
     */
    public List<MemberAddress> getMemberAddressByMId(Integer memberId) {
        return memberAddressWriteDao.getMemberAddresses(memberId, 0, null);
    }

    /**
     * 根据id获得收货地址信息
     * @param id
     * @return
     */
    public MemberAddress getAddressById(Integer id) {
        // 参数校验
        if (id == null || id.equals(0)) {
            throw new BusinessException("地址id不能为空，请重试！");
        }
        return memberAddressWriteDao.get(id);
    }

    /**
     * 增加收货地址
     * @param memberAddress
     * @return
     */
    public boolean saveMemberAddress(MemberAddress memberAddress) {

        // 参数校验
        if (memberAddress == null) {
            throw new BusinessException("收货地址不能为空，请重试！");
        } else if (StringUtil.isEmpty(memberAddress.getMobile())) {
            throw new BusinessException("手机号不能为空，请重试！");
        }

        // 获取该会员用户地址个数，不能超过设置的最大值
        int countall = memberAddressWriteDao.getMemberAddressesCount(memberAddress.getMemberId());
        if (countall >= ConstantsEJS.MEMBER_ADDRESS_MAX) {
            throw new BusinessException(
                "对不起，收货地址不能超过" + ConstantsEJS.MEMBER_ADDRESS_MAX + "个，请删除不常用地址后再添加！");
        }

        int count = memberAddressWriteDao.save(memberAddress);
        if (count == 0) {
            throw new BusinessException("收货地址信息保存失败，请重试！");
        }

        return count > 0;
    }

    /**
     * 更新某条收货地址
     * @param memberAddress 
     * @param request 
     * @return
     */
    public boolean updateAddress(MemberAddress memberAddress) {

        // 参数校验
        if (memberAddress == null) {
            throw new BusinessException("收货地址不能为空，请重试！");
        } else if (memberAddress.getId() == null || memberAddress.getId() == 0) {
            throw new BusinessException("地址id不能为空，请重试！");
        }

        int count = memberAddressWriteDao.update(memberAddress);
        if (count == 0) {
            throw new BusinessException("收货地址更新失败，请重试！");
        }

        return count > 0;
    }

    /**
     * 删除收货地址
     * @param  
     * @return
     */
    public boolean deleteMemberAddress(Integer id, Integer memberId) {
        // 参数校验
        if (id == null || id == 0) {
            throw new BusinessException("地址id不能为空，请重试！");
        }
        int count = memberAddressWriteDao.deleteByIdAndMId(id, memberId);
        if (count == 0) {
            throw new BusinessException("收货地址删除失败，请重试！");
        }
        return count > 0;
    }

    /**
     * 设置为默认地址
     * @param  
     * @return
     */
    public boolean defaultMemberAddress(Integer id, Integer memberId) {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            // 参数校验
            if (id == null || id == 0) {
                throw new BusinessException("地址id不能为空，请重试！");
            }

            MemberAddress memberAddress = new MemberAddress();
            // 设为默认
            memberAddress.setState(MemberAddress.STATE_1);
            memberAddress.setId(id);
            int count = memberAddressWriteDao.update(memberAddress);
            if (count == 0) {
                throw new BusinessException("收货地址更新失败，请重试！");
            }

            // 更新该用户其他地址为非默认
            memberAddressWriteDao.updateNotDefByMId(id, memberId);

            transactionManager.commit(status);
            return true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

}
