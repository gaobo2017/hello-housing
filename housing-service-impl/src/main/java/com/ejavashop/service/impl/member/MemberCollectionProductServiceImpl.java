package com.ejavashop.service.impl.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.MemberCollectionProduct;
import com.ejavashop.model.member.MemberCollectionProductModel;
import com.ejavashop.service.member.IMemberCollectionProductService;
import com.ejavashop.vo.member.FrontMemberCollectionProductVO;

/**
 *                       
 *
 */
@Service(value = "memberCollectionProductService")
public class MemberCollectionProductServiceImpl implements IMemberCollectionProductService {
    private static Logger                     log = LogManager
        .getLogger(MemberCollectionProductServiceImpl.class);

    @Resource
    private MemberCollectionProductModel memberCollectionProductModel;

    /**
    * 根据id取得会员收藏商品表
    * @param  memberCollectionProductId
    * @return
    */
    @Override
    public ServiceResult<MemberCollectionProduct> getMemberCollectionProductById(Integer memberCollectionProductId) {
        ServiceResult<MemberCollectionProduct> result = new ServiceResult<MemberCollectionProduct>();
        try {
            result.setResult(memberCollectionProductModel
                .getMemberCollectionProductById(memberCollectionProductId));
        } catch (Exception e) {
            log.error("根据id[" + memberCollectionProductId + "]取得会员收藏商品表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + memberCollectionProductId + "]取得会员收藏商品表时出现未知异常");
        }
        return result;
    }

    /**
     * 根据会员id取得会员收藏商品表
     * @param request
     * @param pager
     * @return
     */
    @Override
    public ServiceResult<List<FrontMemberCollectionProductVO>> getCollectionProductByMemberId(Map<String, Object> queryMap,
                                                                                              Integer memberId,
                                                                                              PagerInfo pager) {
        ServiceResult<List<FrontMemberCollectionProductVO>> serviceResult = new ServiceResult<List<FrontMemberCollectionProductVO>>();
        try {
            serviceResult.setResult(memberCollectionProductModel
                .getCollectionProductByMemberId(queryMap, memberId, pager));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[memberCollectionProductService][getCollectionProductByMemberId]获取会员收藏商品列表时发生异常:",
                e);
        }
        return serviceResult;
    }

    /**
     * 保存会员收藏商品表
     * @param productId
     * @param request
     * @return
     */
    @Override
    public ServiceResult<MemberCollectionProduct> saveMemberCollectionProduct(Integer productId,
                                                                              Integer memberId) {
        ServiceResult<MemberCollectionProduct> serviceResult = new ServiceResult<MemberCollectionProduct>();
        try {
            serviceResult.setResult(
                memberCollectionProductModel.saveMemberCollectionProduct(productId, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberCollectionProductService][saveMemberCollectionProduct]会员收藏商品时发生异常:",
                e);
        }
        return serviceResult;
    }

    /**
     * 取消收藏商品
     * @param productId
     * @param request
     * @return
     */
    @Override
    public ServiceResult<MemberCollectionProduct> cancelCollectionProduct(Integer productId,
                                                                          Integer memberId) {
        ServiceResult<MemberCollectionProduct> serviceResult = new ServiceResult<MemberCollectionProduct>();
        try {
            serviceResult.setResult(
                memberCollectionProductModel.cancelCollectionProduct(productId, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberCollectionProductService][cancelCollectionProduct]取消收藏商品时发生异常:", e);
        }
        return serviceResult;
    }
}