package com.ejavashop.model.member;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.write.member.MemberApplyDrawingWriteDao;
import com.ejavashop.dao.shop.write.member.MemberBalanceLogsWriteDao;
import com.ejavashop.dao.shop.write.member.MemberWriteDao;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberApplyDrawing;
import com.ejavashop.entity.member.MemberBalanceLogs;

@Component(value = "memberApplyDrawingModel")
public class MemberApplyDrawingModel {

    @Resource
    private MemberApplyDrawingWriteDao   memberApplyDrawingWriteDao;

    @Resource
    private MemberWriteDao               memberWriteDao;

    @Resource
    private MemberBalanceLogsWriteDao    memberBalanceLogsWriteDao;

    @Resource
    private DataSourceTransactionManager transactionManager;

    /**
    * 根据id取得会员提款申请
    * @param  memberApplyDrawingId
    * @return
    */
    public MemberApplyDrawing getMemberApplyDrawing(Integer memberApplyDrawingId) {
        return memberApplyDrawingWriteDao.get(memberApplyDrawingId);
    }

    /**
     * 保存会员提款申请
     * @param  memberApplyDrawing
     * @return
     */
    public Integer saveMemberApplyDrawing(MemberApplyDrawing memberApplyDrawing) {

        // 参数校验
        if (memberApplyDrawing == null) {
            throw new BusinessException("提现申请不能为空，请重试！");
        } else if (memberApplyDrawing.getMoney() == null) {
            throw new BusinessException("提现申请金额不能为空，请重试！");
        } else if (StringUtil.isEmpty(memberApplyDrawing.getBankCode())) {
            throw new BusinessException("账号不能为空，请重试！");
        } else {
            BigDecimal money = memberApplyDrawing.getMoney();
            if (money.compareTo(new BigDecimal(0)) <= 0) {
                throw new BusinessException("提现申请金额应该大于0，请重试！");
            }
        }

        //根据条件取申请
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_memberId", memberApplyDrawing.getMemberId().toString());
        List<MemberApplyDrawing> beanList = memberApplyDrawingWriteDao
            .getMemberApplyDrawings(queryMap, 0, 0);
        if (beanList.size() > 0) {
            for (MemberApplyDrawing bean : beanList) {
                int state = bean.getState();
                if (state == ConstantsEJS.MEMBER_DRAWING_STATE_1) {
                    throw new BusinessException("已经申请过提现了，请耐心等待！");
                }
            }
        }

        //设置提现编号
        //1、保存信息
        Integer count = memberApplyDrawingWriteDao.save(memberApplyDrawing);
        if (count == 0) {
            throw new BusinessException("提现申请保存失败，请重试！");
        }

        return count;
    }

    /**
    * 更新会员提款申请
    * @param  memberApplyDrawing
    * @return
    */
    public Integer updateMemberApplyDrawing(MemberApplyDrawing memberApplyDrawing) {
        int resultInt = 0;
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            //打款操作扣除余额
            if (ConstantsEJS.MEMBER_DRAWING_STATE_3 == memberApplyDrawing.getState().intValue()) {
                Member member = memberWriteDao.get(memberApplyDrawing.getMemberId());
                if (member == null) {
                    throw new BusinessException("用户信息错误，请检查");
                }
                if (member.getBalance().intValue() < memberApplyDrawing.getMoney().intValue()) {
                    throw new BusinessException("用户账户余额不够，不能提款");
                }

                // 更新会员的余额
                Member newMember = new Member();
                newMember.setId(member.getId());
                newMember.setBalance(memberApplyDrawing.getMoney().multiply(new BigDecimal(-1)));
                Integer updateBalance = memberWriteDao.updateBalance(newMember);
                if (updateBalance == 0) {
                    throw new BusinessException("更新会员余额失败，请重试！");
                }

                MemberBalanceLogs memberBalanceLogs = new MemberBalanceLogs();
                memberBalanceLogs.setMemberId(member.getId());
                memberBalanceLogs.setMemberName(member.getName());
                memberBalanceLogs
                    .setMoney(memberApplyDrawing.getMoney().multiply(new BigDecimal(-1)));
                memberBalanceLogs
                    .setMoneyAfter(member.getBalance().subtract(memberApplyDrawing.getMoney()));
                memberBalanceLogs.setMoneyBefore(member.getBalance());
                memberBalanceLogs.setOptId(memberApplyDrawing.getOptId());
                memberBalanceLogs.setOptName(memberApplyDrawing.getOptName());
                memberBalanceLogs.setRemark("申请提款处理成功");
                memberBalanceLogs.setState(MemberBalanceLogs.STATE_4);
                memberBalanceLogsWriteDao.save(memberBalanceLogs);
            }

            resultInt = memberApplyDrawingWriteDao.update(memberApplyDrawing);
            transactionManager.commit(status);
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }

        return resultInt;
    }

    /**
     * 根据条件获取会员提款申请数量
     * @param queryMap
     * @return
     */
    public Integer getMemberApplyDrawingsCount(Map<String, String> queryMap) {
        return memberApplyDrawingWriteDao.getMemberApplyDrawingsCount(queryMap);
    }

    /**
     * 根据条件获取会员提款申请
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    public List<MemberApplyDrawing> getMemberApplyDrawings(Map<String, String> queryMap,
                                                           Integer start, Integer size) {
        return memberApplyDrawingWriteDao.getMemberApplyDrawings(queryMap, start, size);
    }

    /**
     * 批量审核退款申请
     * @param ids
     * @param optId
     * @param optName
     * @return
     */
    public Integer auditing(List<Integer> ids, Integer optId, String optName) {
        return memberApplyDrawingWriteDao.auditing(ids, ConstantsEJS.MEMBER_DRAWING_STATE_2, optId,
            optName);
    }

}
