package com.ejavashop.service.impl.promotion;

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
import com.ejavashop.entity.promotion.group.ActGroup;
import com.ejavashop.entity.promotion.group.ActGroupType;
import com.ejavashop.model.promotion.ActGroupModel;
import com.ejavashop.service.promotion.IActGroupService;

@Service(value = "actGroupService")
public class ActGroupServiceImpl implements IActGroupService {
    private static Logger log = LogManager.getLogger(ActGroupServiceImpl.class);

    @Resource
    private ActGroupModel actGroupModel;

    /**
    * 根据id取得团购
    * @param  actGroupId
    * @return
    */
    @Override
    public ServiceResult<ActGroup> getActGroupById(Integer actGroupId) {
        ServiceResult<ActGroup> result = new ServiceResult<ActGroup>();
        try {
            result.setResult(actGroupModel.getActGroupById(actGroupId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupService][getActGroupById]根据id[" + actGroupId + "]取得团购时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存团购
     * @param  actGroup
     * @return
     */
    @Override
    public ServiceResult<Integer> saveActGroup(ActGroup actGroup) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(actGroupModel.saveActGroup(actGroup));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupService][saveActGroup]保存团购时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新团购
    * @param  actGroup
    * @return
    * @see com.ejavashop.service.ActGroupService#updateActGroup(ActGroup)
    */
    @Override
    public ServiceResult<Integer> updateActGroup(ActGroup actGroup) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(actGroupModel.updateActGroup(actGroup));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupService][updateActGroup]更新团购时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 根据id取得团购分类
     * @param  actGroupTypeId
     * @return
     */
    @Override
    public ServiceResult<ActGroupType> getActGroupTypeById(Integer actGroupTypeId) {
        ServiceResult<ActGroupType> result = new ServiceResult<ActGroupType>();
        try {
            result.setResult(actGroupModel.getActGroupTypeById(actGroupTypeId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][getActGroupTypeById]根据id[" + actGroupTypeId
                      + "]取得团购分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存团购分类
     * @param  actGroupType
     * @return
     */
    @Override
    public ServiceResult<Integer> saveActGroupType(ActGroupType actGroupType) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(actGroupModel.saveActGroupType(actGroupType));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][saveActGroupType]保存团购分类时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新团购分类
    * @param  actGroupType
    * @return
    * @see com.ejavashop.service.ActGroupTypeService#updateActGroupType(ActGroupType)
    */
    @Override
    public ServiceResult<Integer> updateActGroupType(ActGroupType actGroupType) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(actGroupModel.updateActGroupType(actGroupType));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][updateActGroupType]更新团购分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 查询团购分类
     * @param queryMap
     * @param pager
     * @return
     * @see com.ejavashop.service.promotion.IActGroupService#getActGroupTypes(java.util.Map, com.ejavashop.core.PagerInfo)
     */
    @Override
    public ServiceResult<List<ActGroupType>> getActGroupTypes(Map<String, String> queryMap,
                                                              PagerInfo pager) {
        ServiceResult<List<ActGroupType>> result = new ServiceResult<List<ActGroupType>>();
        result.setPager(pager);
        try {
            result.setResult(actGroupModel.getActGroupTypes(queryMap, pager));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][getActGroupTypes]查询团购分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 删除团购分类
     * @param id
     * @return
     * @see com.ejavashop.service.promotion.IActGroupService#delActGroupType(java.lang.Integer)
     */
    @Override
    public ServiceResult<Boolean> delActGroupType(Integer id) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actGroupModel.delActGroupType(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][delActGroupType]删除团购分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 启用团购分类
     * @param id
     * @return
     * @see com.ejavashop.service.promotion.IActGroupService#auditYesActGroupType(java.lang.Integer)
     */
    @Override
    public ServiceResult<Boolean> auditYesActGroupType(Integer id) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actGroupModel.auditYesActGroupType(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][auditYesActGroupType]启用团购分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 停用团购分类
     * @param id
     * @return
     * @see com.ejavashop.service.promotion.IActGroupService#auditNoActGroupType(java.lang.Integer)
     */
    @Override
    public ServiceResult<Boolean> auditNoActGroupType(Integer id) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actGroupModel.auditNoActGroupType(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][auditNoActGroupType]停用团购分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 查询所有团购信息
     * @param queryMap
     * @param pager
     * @return
     * @see com.ejavashop.service.promotion.IActGroupService#getActGroups(java.util.Map, com.ejavashop.core.PagerInfo)
     */
    @Override
    public ServiceResult<List<ActGroup>> getActGroups(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<ActGroup>> result = new ServiceResult<List<ActGroup>>();
        result.setPager(pager);
        try {
            result.setResult(actGroupModel.getActGroups(queryMap, pager));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][getActGroups]查询团购信息时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 查询所有可用的团购分类
     * @return
     * @see com.ejavashop.service.promotion.IActGroupService#getActGroupTypesAll()
     */
    @Override
    public ServiceResult<List<ActGroupType>> getActGroupTypesAll() {
        ServiceResult<List<ActGroupType>> result = new ServiceResult<List<ActGroupType>>();
        try {
            result.setResult(actGroupModel.getActGroupTypesAll());
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][getActGroupTypesAll]查询所有可用的团购分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 更改团购的状态
     * @param id
     * @param state
     * @return
     * @see com.ejavashop.service.promotion.IActGroupService#updateActGroupState(java.lang.Integer, int)
     */
    @Override
    public ServiceResult<Boolean> updateActGroupState(Integer id, int state) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actGroupModel.updateActGroupState(id, state));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][updateActGroupState]更改团购的状态时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 更改团购的活动状态
     * @param id
     * @param activityState
     * @return
     * @see com.ejavashop.service.promotion.IActGroupService#updateActGroupActivityState(java.lang.Integer, int)
     */
    @Override
    public ServiceResult<Boolean> updateActGroupActivityState(Integer id, int activityState) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actGroupModel.updateActGroupActivityState(id, activityState));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][updateActGroupActivityState]更改团购的活动状态时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 平台管理员审核，审核通过之后更改状态，并填写审核意见
     * @param id
     * @param state
     * @param auditOpinion
     * @return
     * @see com.ejavashop.service.promotion.IActGroupService#updateActGroupStateAndAuditOpinion(java.lang.Integer, int, java.lang.String)
     */
    @Override
    public ServiceResult<Boolean> updateActGroupStateAndAuditOpinion(Integer id, int state,
                                                                     String auditOpinion) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actGroupModel.updateActGroupStateAndAuditOpinion(id, state,
                auditOpinion));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IActGroupTypeService][updateActGroupStateAndAuditOpinion]平台管理员审核，审核通过之后更改状态，并填写审核意见出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 团购前台查看列表页
     * @param page
     * @param type
     * @param channel
     * @return
     * @see com.ejavashop.service.promotion.IActGroupService#getActGroupsFront(com.ejavashop.core.PagerInfo, int, int)
     */
    @Override
    public ServiceResult<List<ActGroup>> getActGroupsFront(PagerInfo pager, int type, int channel) {
        ServiceResult<List<ActGroup>> result = new ServiceResult<List<ActGroup>>();
        result.setPager(pager);
        try {
            result.setResult(actGroupModel.getActGroupsFront(pager, type, channel));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][getActGroups]查询团购信息时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 查询所有可用的团购分类
     * @return
     * @see com.ejavashop.service.promotion.IActGroupService#getActGroupTypesFront()
     */
    @Override
    public ServiceResult<List<ActGroupType>> getActGroupTypesFront() {
        ServiceResult<List<ActGroupType>> result = new ServiceResult<List<ActGroupType>>();
        try {
            result.setResult(actGroupModel.getActGroupTypesFront());
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][getActGroupTypesFront]查询所有可用的团购分类时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ActGroup>> getActGroupsByType(Integer type, Integer topNum) {
        ServiceResult<List<ActGroup>> result = new ServiceResult<List<ActGroup>>();
        try {
            result.setResult(actGroupModel.getActGroupsByType(type, topNum));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActGroupTypeService][getActGroupsByType]根据类型查询topNum条团购时出现未知异常：", e);
        }
        return result;
    }
}