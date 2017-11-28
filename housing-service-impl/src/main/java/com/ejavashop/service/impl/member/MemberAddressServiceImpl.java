package com.ejavashop.service.impl.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.MemberAddress;
import com.ejavashop.model.member.MemberAddressModel;
import com.ejavashop.service.member.IMemberAddressService;

@Service(value = "memberAddressService")
public class MemberAddressServiceImpl implements IMemberAddressService {

    private static Logger      log = LogManager.getLogger(MemberAddressServiceImpl.class);

    @Resource
    private MemberAddressModel memberAddressModel;

    @Override
    public ServiceResult<List<MemberAddress>> getMemberAddresses(Integer memberId,
                                                                 PagerInfo pager) {
        ServiceResult<List<MemberAddress>> serviceResult = new ServiceResult<List<MemberAddress>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(memberAddressModel.getMemberAddressesCount(memberId));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(memberAddressModel.getMemberAddresses(memberId, start, size));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberService][getMemberAddresses]根据会员ID获取会员地址发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][getMemberAddresses]根据会员ID获取会员地址发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MemberAddress>> getMemberAddressByMId(Integer memberId) {
        ServiceResult<List<MemberAddress>> serviceResult = new ServiceResult<List<MemberAddress>>();
        try {
            serviceResult.setResult(memberAddressModel.getMemberAddressByMId(memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberAddressService][getMemberAddressByMId]根据用户ID获得收货地址时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberAddressService][getMemberAddressByMId]根据用户ID获得收货地址时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<MemberAddress> getMemberAddressById(Integer id) {
        ServiceResult<MemberAddress> serviceResult = new ServiceResult<MemberAddress>();
        try {
            serviceResult.setResult(memberAddressModel.getAddressById(id));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberAddressService][getAddressById]收货地址获取时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberAddressService][getAddressById]收货地址获取时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> saveMemberAddress(MemberAddress memberAddress) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(memberAddressModel.saveMemberAddress(memberAddress));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberAddressService][saveMemberAddress]保存收货地址信息时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberAddressService][saveMemberAddress]保存收货地址信息时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateMemberAddress(MemberAddress memberAddress) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(memberAddressModel.updateAddress(memberAddress));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberAddressService][updateMemberAddress]收货地址更新时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberAddressService][updateMemberAddress]收货地址更新时发生异常:", e);
        }
        return serviceResult;

    }

    @Override
    public ServiceResult<Boolean> deleteMemberAddress(Integer id, Integer memberId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(memberAddressModel.deleteMemberAddress(id, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberAddressService][deleteMemberAddress]删除收货地址发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberAddressService][deleteMemberAddress]删除收货地址发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 设置为默认地址
     * @param  
     * @return
     */
    @Override
    public ServiceResult<Boolean> defaultMemberAddress(Integer id, Integer memberId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(memberAddressModel.defaultMemberAddress(id, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberAddressService][defaultMemberAddress]设置为默认地址发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberAddressService][defaultMemberAddress]设置为默认地址发生异常:", e);
        }
        return serviceResult;
    }
}
