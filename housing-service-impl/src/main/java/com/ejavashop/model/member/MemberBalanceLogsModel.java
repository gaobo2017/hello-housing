package com.ejavashop.model.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.write.member.MemberBalanceLogsWriteDao;
import com.ejavashop.entity.member.MemberBalanceLogs;

@Service(value = "memberBalanceLogsModel")
public class MemberBalanceLogsModel {

    @Resource
    private MemberBalanceLogsWriteDao memberBalanceLogsWriteDao;

    /**
     * 根据会员ID获取会员账户余额变化日志数量
     * @param memberId
     * @return
     */
    public Integer getMemberBalanceLogsCount(Integer memberId) {
        return memberBalanceLogsWriteDao.getMemberBalanceLogsCount(memberId);
    }

    /**
     * 根据会员ID获取会员账户余额变化日志
     * @param memberId
     * @param start
     * @param size
     * @return
     */
    public List<MemberBalanceLogs> getMemberBalanceLogs(Integer memberId, Integer start,
                                                        Integer size) {
        return memberBalanceLogsWriteDao.getMemberBalanceLogs(memberId, start, size);
    }

    /**
    * 根据id取得会员账户余额变化日志表
    * @param  memberBalanceLogsId
    * @return
    */
    public MemberBalanceLogs getMemberBalanceLogsById(Integer memberBalanceLogsId) {
        return memberBalanceLogsWriteDao.get(memberBalanceLogsId);
    }

    /**
     * 保存会员账户余额变化日志表
     * @param  memberBalanceLogs
     * @return
     */
    public Integer saveMemberBalanceLogs(MemberBalanceLogs memberBalanceLogs) {
        return memberBalanceLogsWriteDao.save(memberBalanceLogs);
    }

    /**
    * 更新会员账户余额变化日志表
    * @param  memberBalanceLogs
    * @return
    */
    public Integer updateMemberBalanceLogs(MemberBalanceLogs memberBalanceLogs) {
        return memberBalanceLogsWriteDao.update(memberBalanceLogs);
    }

    public Integer pageCount(Map<String, Object> queryMap) {
        return memberBalanceLogsWriteDao.getCount(queryMap);
    }

    public List<MemberBalanceLogs> page(Map<String, Object> queryMap) {
        return memberBalanceLogsWriteDao.page(queryMap);
    }

    public boolean del(Integer id) {

        if (id == null)
            throw new BusinessException("删除会员账户余额变化日志表[" + id + "]时出错");
        return memberBalanceLogsWriteDao.del(id) > 0;
    }
}
