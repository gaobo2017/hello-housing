package com.ejavashop.model.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.promotion.read.group.ActGroupReadDao;
import com.ejavashop.dao.promotion.read.group.ActGroupTypeReadDao;
import com.ejavashop.dao.promotion.write.group.ActGroupTypeWriteDao;
import com.ejavashop.dao.promotion.write.group.ActGroupWriteDao;
import com.ejavashop.entity.promotion.group.ActGroup;
import com.ejavashop.entity.promotion.group.ActGroupType;

@Component(value = "actGroupModel")
public class ActGroupModel {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
        .getLogger(ActGroupModel.class);

    @Resource
    private ActGroupWriteDao               actGroupWriteDao;

    @Resource
    private ActGroupReadDao                actGroupReadDao;

    @Resource
    private ActGroupTypeWriteDao           actGroupTypeWriteDao;

    @Resource
    private ActGroupTypeReadDao            actGroupTypeReadDao;

    @Resource
    private DataSourceTransactionManager   promotionTransactionManager;

    /**
     * 根据id取得团购分类
     * @param  actGroupTypeId
     * @return
     */
    public ActGroupType getActGroupTypeById(Integer actGroupTypeId) {
        return actGroupTypeReadDao.get(actGroupTypeId);
    }

    /**
     * 保存团购分类
     * @param  actGroupType
     * @return
     */
    public Integer saveActGroupType(ActGroupType actGroupType) {
        this.dbActGroupTypeConstrains(actGroupType);
        return actGroupTypeWriteDao.insert(actGroupType);
    }

    /**
    * 更新团购分类
    * @param  actGroupType
    * @return
    */
    public Integer updateActGroupType(ActGroupType actGroupType) {
        this.dbActGroupTypeConstrains(actGroupType);
        return actGroupTypeWriteDao.update(actGroupType);
    }

    /**
     * 根据id取得团购
     * @param  actGroupId
     * @return
     */
    public ActGroup getActGroupById(Integer actGroupId) {
        return actGroupReadDao.get(actGroupId);
    }

    /**
     * 保存团购
     * @param  actGroup
     * @return
     */
    public Integer saveActGroup(ActGroup actGroup) {
        this.dbActGroupConstrains(actGroup);
        return actGroupWriteDao.insert(actGroup);
    }

    /**
    * 更新团购
    * @param  actGroup
    * @return
    */
    public Integer updateActGroup(ActGroup actGroup) {
        this.dbActGroupConstrains(actGroup);
        return actGroupWriteDao.update(actGroup);
    }

    private void dbActGroupConstrains(ActGroup actGroup) {
        actGroup.setName(StringUtil.dbSafeString(actGroup.getName(), false, 65535));
        actGroup.setDescinfo(StringUtil.dbSafeString(actGroup.getDescinfo(), false, 65535));
        actGroup.setAuditName(StringUtil.dbSafeString(actGroup.getAuditName(), true, 50));
        actGroup.setReason(StringUtil.dbSafeString(actGroup.getReason(), true, 200));
        actGroup.setAuditOpinion(StringUtil.dbSafeString(actGroup.getAuditOpinion(), false, 255));
    }

    private void dbActGroupTypeConstrains(ActGroupType actGroupType) {
        actGroupType.setName(StringUtil.dbSafeString(actGroupType.getName(), false, 20));
        actGroupType.setCreateName(StringUtil.dbSafeString(actGroupType.getCreateName(), true, 50));
        actGroupType.setUpdateName(StringUtil.dbSafeString(actGroupType.getUpdateName(), true, 50));
    }

    /**
     * 分页查询团购分类
     * @param queryMap
     * @param pager
     * @return
     */
    public List<ActGroupType> getActGroupTypes(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(actGroupTypeReadDao.count(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        return actGroupTypeReadDao.getActGroupTypes(queryMap, start, size);
    }

    /**
     * 分页查询团购
     * @param queryMap
     * @param pager
     * @return
     */
    public List<ActGroup> getActGroups(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(actGroupReadDao.count(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        return actGroupReadDao.getActGroups(queryMap, start, size);
    }

    /**
     * 删除团购分类
     * @param id
     * @return
     */
    public Boolean delActGroupType(Integer id) {
        int count = actGroupWriteDao.countByType(id);
        if (count > 0) {
            throw new BusinessException("此分类下面有商品，不能进行删除操作。");
        }
        return actGroupTypeWriteDao.del(id) > 0;
    }

    /**
     * 团购分类的启用
     * @param id
     * @return
     */
    public Boolean auditYesActGroupType(Integer id) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        boolean mark = false;
        try {
            ActGroupType actGroupType = actGroupTypeWriteDao.get(id);
            if (actGroupType.getState().intValue() != ActGroupType.ACTGROUPTYPE_STATE_0) {
                throw new BusinessException("状态只有停用才能启用");
            }
            mark = actGroupTypeWriteDao.audit(id, ActGroupType.ACTGROUPTYPE_STATE_1) > 0;

            actGroupWriteDao.updateByTypeState(id, ActGroup.TYPE_STATE_1);

            promotionTransactionManager.commit(status);
        } catch (BusinessException be) {
            promotionTransactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            throw e;
        }
        return mark;
    }

    /**
     * 团购分类的停用
     * @param id
     * @return
     */
    public Boolean auditNoActGroupType(Integer id) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        boolean mark = false;
        try {
            ActGroupType actGroupType = actGroupTypeWriteDao.get(id);
            if (actGroupType.getState().intValue() != ActGroupType.ACTGROUPTYPE_STATE_1) {
                throw new BusinessException("状态只有停用才能启用");
            }
            mark = actGroupTypeWriteDao.audit(id, ActGroupType.ACTGROUPTYPE_STATE_0) > 0;

            actGroupWriteDao.updateByTypeState(id, ActGroup.TYPE_STATE_0);

            promotionTransactionManager.commit(status);
        } catch (BusinessException be) {
            promotionTransactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            throw e;
        }
        return mark;
    }

    /**
     * 查询所有可用的团购分类
     * @return
     */
    public List<ActGroupType> getActGroupTypesAll() {
        return actGroupTypeReadDao.getAll();
    }

    /**
     * 更改团购的审核状态
     * @param id
     * @param state
     * @return
     */
    public Boolean updateActGroupState(Integer id, int state) {
        return actGroupWriteDao.updateState(id, state) > 0;
    }

    /**
     * 更改团购的活动状态
     * @param id
     * @param activityState
     * @return
     */
    public Boolean updateActGroupActivityState(Integer id, int activityState) {
        return actGroupWriteDao.updateActivityState(id, activityState) > 0;
    }

    /**
     * 平台管理员审核，审核通过之后更改状态，并填写审核意见
     * @param id
     * @param state
     * @param auditOpinion
     * @return
     */
    public Boolean updateActGroupStateAndAuditOpinion(Integer id, int state, String auditOpinion) {
        return actGroupWriteDao.updateStateAndAuditOpinion(id, state, auditOpinion) > 0;
    }

    /**
     * 团购前台查看列表页
     * @param pager
     * @param type
     * @param channel
     * @return
     */
    public List<ActGroup> getActGroupsFront(PagerInfo pager, int type, int channel) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(actGroupReadDao.countFront(type, channel));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        return actGroupReadDao.getActGroupsFront(type, channel, start, size);
    }

    /**
     * 查询所有可用的团购分类
     * @return
     */
    public List<ActGroupType> getActGroupTypesFront() {
        return actGroupTypeReadDao.getAll();
    }

    /**
     * 根据类型查询topNum条团购
     * @param type
     * @param topNum
     * @return
     */
    public List<ActGroup> getActGroupsByType(Integer type, Integer topNum) {
        return actGroupReadDao.getActGroupsByType(type, topNum);
    }

}
