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
import com.ejavashop.entity.member.MemberCollectionSeller;
import com.ejavashop.model.member.MemberCollectionSellerModel;
import com.ejavashop.service.member.IMemberCollectionSellerService;
import com.ejavashop.vo.member.FrontMemberCollectionSellerVO;

/**
 * 会员收藏店铺
 *
 */
@Service(value = "memberCollectionSellerService")
public class MemberCollectionSellerServiceImpl implements IMemberCollectionSellerService {
    private static Logger               log = LogManager
        .getLogger(MemberCollectionSellerServiceImpl.class);

    @Resource
    private MemberCollectionSellerModel memberCollectionSellerModel;

    @Override
    public ServiceResult<Boolean> collectionSeller(Integer sellerId, Integer memberId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult
                .setResult(memberCollectionSellerModel.collectionSeller(sellerId, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[MemberCollectionSellerService][collectionSeller]会员收藏商铺时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberCollectionSellerService][collectionSeller]会员收藏商铺时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> cancelCollectionSeller(Integer sellerId, Integer memberId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult
                .setResult(memberCollectionSellerModel.cancelCollectionSeller(sellerId, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberCollectionSellerService][cancelCollectionSeller]取消收藏商铺表时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberCollectionSellerService][cancelCollectionSeller]取消收藏商铺表时发生异常:", e);
        }
        return serviceResult;
    }

    /**
    * 根据id取得会员收藏商铺表
    * @param  memberCollectionSellerId
    * @return
    */
    @Override
    public ServiceResult<MemberCollectionSeller> getMemberCollectionSellerById(Integer memberCollectionSellerId) {
        ServiceResult<MemberCollectionSeller> result = new ServiceResult<MemberCollectionSeller>();
        try {
            result.setResult(memberCollectionSellerModel
                .getMemberCollectionSellerById(memberCollectionSellerId));
        } catch (Exception e) {
            log.error("根据id[" + memberCollectionSellerId + "]取得会员收藏商铺表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + memberCollectionSellerId + "]取得会员收藏商铺表时出现未知异常");
        }
        return result;
    }

    /**
     * 根据会员id取得会员收藏商铺表
     * @param  memberId
     * @return
     */
    @Override
    public ServiceResult<List<FrontMemberCollectionSellerVO>> getCollectionSellerByMemberid(Integer memberId,
                                                                                            PagerInfo pager) {
        ServiceResult<List<FrontMemberCollectionSellerVO>> serviceResult = new ServiceResult<List<FrontMemberCollectionSellerVO>>();

        try {
            serviceResult.setResult(
                memberCollectionSellerModel.getCollectionSellerByMemberid(memberId, pager));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[MemberCollectionSellerService][getCollectionSellerByMemberid]获取会员收藏商铺列表时发生异常:",
                e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MemberCollectionSeller>> getBySellerIdAndMId(Integer sellerId,
                                                                           Integer memberId) {
        ServiceResult<List<MemberCollectionSeller>> serviceResult = new ServiceResult<List<MemberCollectionSeller>>();
        try {
            serviceResult
                .setResult(memberCollectionSellerModel.getBySellerIdAndMId(sellerId, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberCollectionSellerService][getBySellerIdAndMId]获取会员收藏商铺列表时发生异常:", e);
        }
        return serviceResult;
    }

}