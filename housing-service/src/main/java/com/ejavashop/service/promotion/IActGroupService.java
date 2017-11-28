package com.ejavashop.service.promotion;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.promotion.group.ActGroup;
import com.ejavashop.entity.promotion.group.ActGroupType;

public interface IActGroupService {

    /**
     * 根据id取得团购
     * @param  actGroupId
     * @return
     */
    ServiceResult<ActGroup> getActGroupById(Integer actGroupId);

    /**
     * 保存团购
     * @param  actGroup
     * @return
     */
    ServiceResult<Integer> saveActGroup(ActGroup actGroup);

    /**
    * 更新团购
    * @param  actGroup
    * @return
    */
    ServiceResult<Integer> updateActGroup(ActGroup actGroup);

    /**
     * 根据id取得团购分类
     * @param  actGroupTypeId
     * @return
     */
    ServiceResult<ActGroupType> getActGroupTypeById(Integer actGroupTypeId);

    /**
     * 保存团购分类
     * @param  actGroupType
     * @return
     */
    ServiceResult<Integer> saveActGroupType(ActGroupType actGroupType);

    /**
    * 更新团购分类
    * @param  actGroupType
    * @return
    */
    ServiceResult<Integer> updateActGroupType(ActGroupType actGroupType);

    /**
     * 查询团购分类
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActGroupType>> getActGroupTypes(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除团购分类
     * @param id
     * @return
     */
    ServiceResult<Boolean> delActGroupType(Integer id);

    /**
     * 启用团购分类
     * @param id
     * @return
     */
    ServiceResult<Boolean> auditYesActGroupType(Integer id);

    /**
     * 停用团购分类
     * @param id
     * @return
     */
    ServiceResult<Boolean> auditNoActGroupType(Integer id);

    /**
     * 查询所有团购信息
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActGroup>> getActGroups(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 查询所有可用的团购分类
     * @return
     */
    ServiceResult<List<ActGroupType>> getActGroupTypesAll();

    /**
     * 更改团购的审核状态
     * @param id
     * @param state2
     * @return
     */
    ServiceResult<Boolean> updateActGroupState(Integer id, int state2);

    /**
     * 更改团购的活动状态
     * @param id
     * @param activityState
     * @return
     */
    ServiceResult<Boolean> updateActGroupActivityState(Integer id, int activityState);

    /**
     * 平台管理员审核，审核通过之后更改状态，并填写审核意见
     * @param id
     * @param state
     * @param auditOpinion
     * @return
     */
    ServiceResult<Boolean> updateActGroupStateAndAuditOpinion(Integer id, int state,
                                                              String auditOpinion);

    /**
     * 团购前台查看
     * @param page 分页
     * @param type 团购类型
     * @param channel 渠道
     * @return
     */
    ServiceResult<List<ActGroup>> getActGroupsFront(PagerInfo page, int type, int channel);

    /**
     * 查询所有可用的团购分类
     * @return
     */
    ServiceResult<List<ActGroupType>> getActGroupTypesFront();

    /**
     * 根据类型查询topNum条团购
     * @param type
     * @param topNum
     * @return
     */
    ServiceResult<List<ActGroup>> getActGroupsByType(Integer type, Integer topNum);
}