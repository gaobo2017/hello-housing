package com.ejavashop.model.house;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.TimeUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.write.house.HousingCostWriteDao;
import com.ejavashop.dao.shop.write.house.HousingIncomeWriteDao;
import com.ejavashop.dao.shop.write.house.HousingResourcesWriteDao;
import com.ejavashop.entity.house.HousingCost;
import com.ejavashop.entity.house.HousingIncome;
import com.ejavashop.entity.house.HousingResources;

@Component(value = "houseManageModel")
public class HouseManageModel {
    @Resource
    private HousingCostWriteDao          housingCostWriteDao;
    @Resource
    private HousingResourcesWriteDao     housingResourcesWriteDao;

    @Resource
    private HousingIncomeWriteDao        housingIncomeWriteDao;

    @Resource
    private DataSourceTransactionManager transactionManager;
    //    @Resource
    //    private SellerWriteDao               sellerWriteDao;
    //    @Resource
    //    private SellerReadDao                sellerReadDao;
    //    @Resource
    //    private SellerRolesReadDao           sellerRolesReadDao;
    //    @Resource
    //    private SellerRolesWriteDao          sellerRolesWriteDao;
    //    @Resource
    //    private SellerResourcesRolesWriteDao sellerResourcesRolesWriteDao;
    //    @Resource
    //    private SystemResourcesReadDao       systemResourcesReadDao;
    //    @Resource
    //    private MemberReadDao                memberReadDao;
    //    @Resource
    //    private MemberWriteDao               memberWriteDao;
    //    @Resource
    //    private SellerUserReadDao            sellerUserReadDao;
    //    @Resource
    //    private SellerUserWriteDao           sellerUserWriteDao;

    //    public boolean updateSellerApply(SellerApply sellerApply) {
    //        return sellerApplyWriteDao.update(sellerApply) > 0;
    //    }

    public Integer getHousingResourcesCount(Map<String, String> queryMap) {
        return housingResourcesWriteDao.getHousingResourcesCount(queryMap);
    }

    public List<HousingResources> getHousingResourcesList(Map<String, String> queryMap,
                                                          Integer start, Integer size) {
        return housingResourcesWriteDao.getHousingResourcesList(queryMap, start, size);
    }

    /**
    * 更新表
    * @param  housingResources
    * @return
    */

    public Integer updateHousingResources(HousingResources housingResources) {
        return housingResourcesWriteDao.updateByPrimaryKeySelective(housingResources);
    }

    /**
     * 根据id取得房源信息
     * @param  housingResourcesId
     * @return
     */
    public HousingResources getHousingResourcesById(Integer housingResourcesId) {
        return housingResourcesWriteDao.get(housingResourcesId);
    }

    /**
     * 新增房源表
     * @param  housingResources
     * @return
     */

    public Integer createHousingResources(HousingResources housingResources) {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            int hrId = housingResourcesWriteDao.insertSelective(housingResources);

            //重新统计 成本总表数据
            //            List<HousingCostDetail> housingCostDetailSumlist = housingCostDetailWriteDao
            //                .getHousingCostDetailSum(housingCostDetail.getCostId());

            HousingCost housingCost = new HousingCost();

            long days = TimeUtil.getDaysBetweenDates(housingResources.getContractStartTime(),
                housingResources.getContractEndTime()) + 1;//加一天

            housingCost.setHouseId(housingResources.getId());// insert后会赋值，insert前是null
            //            housingCost.setOperationId(housingResources.getOperationId());
            //            housingCost.setOperationName(housingResources.getOperationName());

            // 计算日租成本， day=合同结束日期-合同开始日期    ，四舍五入，保留2位小数
            BigDecimal dayRentCost = housingResources.getPricesSum().divide(new BigDecimal(days), 2,
                BigDecimal.ROUND_HALF_UP);

            housingCost.setDayRentCost(dayRentCost);

            housingCost.setPricesSum(housingResources.getPricesSum());

            housingCost.setAllCostSum(housingResources.getPricesSum());
            //            NumberFormat percent = NumberFormat.getPercentInstance(); //建立百分比格式化用
            //            percent.setMaximumFractionDigits(2); //百分比小数点最多2位
            //            for (HousingCostDetail CostDetailSum : housingCostDetailSumlist) {
            //
            //                if (HousingCostDetail.COST_TYPE_1 == CostDetailSum.getCostType().intValue()) {
            //                    housingCost.setRenovationCostSum(CostDetailSum.getMoney());// 装修费统计
            //                } else {
            //                    housingCost.setOtherCostSum(CostDetailSum.getMoney()); //其他费用统计
            //                }
            //            }

            //            //总成本=装修成本总额+其他成本总额+房源总价
            //            housingCost.setAllCostSum(housingCost.getRenovationCostSum()
            //                .add(housingCost.getOtherCostSum()).add(housingCost.getPricesSum()));

            boolean isCreateHousingCost = housingCostWriteDao.insertSelective(housingCost) > 0;

            //该房源总收入表  初始化数据
            HousingIncome housingIncome = new HousingIncome();
            housingIncome.setHouseId(housingResources.getId());

            boolean isCreateIncome = housingIncomeWriteDao.insertSelective(housingIncome) > 0;

            if (hrId <= 0 || !isCreateHousingCost || !isCreateIncome) {
                throw new BusinessException(" 添加房源失败！");
            }

            transactionManager.commit(status);

            return 1;
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
            throw e;
        }

    }

    /**
     * 新增房源表
     * @param  housingResources
     * @return
     */

    public Boolean deleteHousingResources(Integer housingResourcesId) {
        return housingResourcesWriteDao.deleteByPrimaryKey(housingResourcesId) > 0;
    }
    //    public boolean delete(Integer id, Integer memberId) throws Exception {
    //        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    //        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    //        TransactionStatus status = transactionManager.getTransaction(def);
    //
    //        try {
    //            // 数据库中的申请
    //            SellerApply applyDb = sellerApplyWriteDao.get(id);
    //            if (applyDb.getState().intValue() != SellerApply.STATE_1_SEND
    //                && applyDb.getState().intValue() != SellerApply.STATE_4_FAIL) {
    //                throw new BusinessException("只能删除提交申请和审核失败状态的商家申请！");
    //            }
    //
    //            // 删除商家入驻申请
    //            boolean applyDel = sellerApplyWriteDao.delete(id) > 0;
    //            if (!applyDel) {
    //                throw new BusinessException("删除商家入驻申请时失败！");
    //            }
    //
    //            Seller sellerDb = sellerWriteDao.getSellerByMemberId(memberId);
    //            if (sellerDb.getAuditStatus().intValue() != Seller.AUDIT_STATE_1_SEND) {
    //                throw new BusinessException("只能删除提交申请状态的商家申请！");
    //            }
    //
    //            //删除商家账号
    //            boolean sellerDel = sellerWriteDao.deleteByMemberId(memberId) > 0;
    //            if (!sellerDel) {
    //                throw new BusinessException("删除商家入驻申请时失败（删除关联账号时）！");
    //            }
    //
    //            transactionManager.commit(status);
    //
    //            return applyDel && sellerDel;
    //        } catch (Exception e) {
    //            transactionManager.rollback(status);
    //            throw e;
    //        }
    //    }
    //

    //
    //    /**
    //     * 根据用户ID获取其商家入驻申请
    //     * @param userid
    //     * @return
    //     */
    //    public SellerApply getSellerApplyByUser(Integer userid) {
    //        return sellerApplyWriteDao.getSellerApplyByUserId(userid);
    //    }
    //
    //    public boolean auditSellerApply(Integer sellerApplyId, Integer state,
    //                                    Integer optUserId) throws Exception {
    //        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    //        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    //        TransactionStatus status = transactionManager.getTransaction(def);
    //
    //        try {
    //            // 数据库中的申请
    //            SellerApply applyDb = sellerApplyWriteDao.get(sellerApplyId);
    //
    //            // 如果审核通过，同步修改商家表
    //            if (SellerApply.STATE_2_DONE == state.intValue()) {
    //                // 修改商家申请
    //                SellerApply sellerApply = new SellerApply();
    //                sellerApply.setId(sellerApplyId);
    //                sellerApply.setState(SellerApply.STATE_2_DONE);
    //                sellerApply.setOptId(optUserId);
    //                Integer row = sellerApplyWriteDao.update(sellerApply);
    //                if (row == 0) {
    //                    throw new BusinessException("修改商家申请状态时失败！");
    //                }
    //
    //                // 修改商家状态
    //                row = sellerWriteDao.auditSeller(applyDb.getUserId(), Seller.AUDIT_STATE_2_DONE);
    //                if (row == 0) {
    //                    throw new BusinessException("修改商家状态时失败！");
    //                }
    //
    //                // 初始化商家管理的数据
    //                // 1、建立超级管理员的角色
    //                // 2、给超级管理员角色赋权限
    //                // 3、建立一个用户初始化为超级管理员
    //                Seller seller = sellerReadDao.getByMemberId(applyDb.getUserId());
    //                Member member = memberReadDao.get(applyDb.getUserId());
    //                // 1
    //                Map<String, String> queryMap = new HashMap<String, String>();
    //                queryMap.put("q_sellerId", seller.getId() + "");
    //                Integer sellerRolesCount = sellerRolesReadDao.getSellerRolesCount(queryMap);
    //                Integer count = sellerUserReadDao.getCount(queryMap);
    //                if (sellerRolesCount == 0 && count == 0) {
    //                    SellerRoles roles = new SellerRoles();
    //                    roles.setSellerId(seller.getId());
    //                    roles.setRoleCode("seller_admin");
    //                    roles.setRolesName("店铺超级管理员");
    //                    roles.setStatus(SellerRoles.STATUS_1);
    //                    roles.setContent("店铺超级管理员");
    //                    roles.setUserId(0);
    //                    Integer insert = sellerRolesWriteDao.insert(roles);
    //                    if (insert == 0) {
    //                        throw new BusinessException("初始化商家角色数据时失败！");
    //                    }
    //                    // 2
    //                    initRolesResource(roles.getId(), ConstantsEJS.SELLER_RESOURCE_ROOT);
    //
    //                    // 3
    //                    SellerUser sellerUser = new SellerUser();
    //                    sellerUser.setName(seller.getName());
    //                    sellerUser.setPassword(member.getPassword());
    //                    sellerUser.setCode("");
    //                    sellerUser.setRealName("");
    //                    sellerUser.setPhone("");
    //                    sellerUser.setJob("");
    //                    sellerUser.setSellerId(seller.getId());
    //                    sellerUser.setRoleId(roles.getId());
    //                    sellerUser.setState(SellerUser.STATE_NORM);
    //                    sellerUser.setCreateId(0);
    //                    sellerUser.setUpdateId(0);
    //                    insert = sellerUserWriteDao.insert(sellerUser);
    //                    if (insert == 0) {
    //                        throw new BusinessException("初始化商家管理员数据时失败！");
    //                    }
    //                }
    //
    //            } else if (SellerApply.STATE_4_FAIL == state.intValue()) {
    //                // 商家申请的状态不可逆，审核通过后不能再修改成审核失败状态
    //                if (applyDb.getState().intValue() != SellerApply.STATE_1_SEND
    //                    && applyDb.getState().intValue() != SellerApply.STATE_4_FAIL) {
    //                    throw new BusinessException("商家申请已经审核通过，不能修改状态！");
    //                }
    //                // 如果商家申请被驳回，直接修改商家申请状态，不修改商家状态
    //                // 修改商家申请
    //                SellerApply sellerApply = new SellerApply();
    //                sellerApply.setId(sellerApplyId);
    //                sellerApply.setState(SellerApply.STATE_4_FAIL);
    //                sellerApply.setOptId(optUserId);
    //                Integer row = sellerApplyWriteDao.update(sellerApply);
    //                if (row == 0) {
    //                    throw new BusinessException("修改商家申请状态时失败！");
    //                }
    //            }
    //            transactionManager.commit(status);
    //            return true;
    //        } catch (Exception e) {
    //            transactionManager.rollback(status);
    //            throw e;
    //        }
    //    }
    //
    //    /**
    //     * 递归为商家角色赋权限
    //     * @param roleId 角色ID
    //     * @param resourcePId 资源父ID
    //     */
    //    private void initRolesResource(Integer roleId, Integer resourcePId) {
    //        // 赋自己的权限
    //        SellerResourcesRoles resourcesRoles = new SellerResourcesRoles();
    //        resourcesRoles.setResourcesId(resourcePId);
    //        resourcesRoles.setSellerRolesId(roleId);
    //        sellerResourcesRolesWriteDao.insert(resourcesRoles);
    //        List<SystemResources> children = systemResourcesReadDao.getByPId(resourcePId);
    //        if (children != null && children.size() > 0) {
    //            for (SystemResources child : children) {
    //                initRolesResource(roleId, child.getId());
    //            }
    //        }
    //    }
    //
    //    /**
    //     * 保存商家申请表(平台添加商家用)<br>
    //     * <li>新增一个普通用户表（兼容用户端申请）
    //     * <li>添加商家申请表数据
    //     * <li>添加商家数据
    //     * 
    //     * @param  sellerApply
    //     * @return
    //     */
    //    public boolean saveSellerApplyForAdmin(SellerApply sellerApply, String userName,
    //                                           String sellerName, String ip) {
    //        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    //        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    //        TransactionStatus status = transactionManager.getTransaction(def);
    //
    //        try {
    //
    //            // 初始化会员信息
    //            Member member = new Member();
    //            member.setName("admin-seller-apply-" + userName);
    //            member.setPassword(Md5.getMd5String("123456"));
    //            member.setGrade(Member.GRADE_1);
    //            member.setGradeValue(0);
    //            member.setIntegral(0);
    //            member.setLastLoginIp(ip);
    //            member.setLoginNumber(0);
    //            member.setPwdErrCount(0);
    //            member.setSource(ConstantsEJS.SOURCE_1_PC);
    //            member.setBalance(new BigDecimal(0.00));
    //            member.setBalancePwd("");
    //            member.setIsEmailVerify(ConstantsEJS.YES_NO_0);
    //            member.setIsSmsVerify(ConstantsEJS.YES_NO_0);
    //            member.setSmsVerifyCode("");
    //            member.setEmailVerifyCode("");
    //            member.setCanReceiveSms(1);
    //            member.setCanReceiveEmail(1);
    //            member.setStatus(Member.STATUS_1);
    //            member.setEmail("");
    //
    //            // 判断用户名是否已存在
    //            List<Member> byNameList = memberWriteDao.getByName(member.getName());
    //            if (byNameList != null && byNameList.size() > 0) {
    //                throw new BusinessException("商家账号已存在，请重试！");
    //            }
    //
    //            // 申请信息校验
    //            this.checkForInsert(sellerApply, userName, sellerName);
    //
    //            // 保存会员注册信息（因为需要加密的字段都是空所以不需要再加密）
    //            int count = memberWriteDao.save(member);
    //            if (count == 0) {
    //                throw new BusinessException("信息保存失败，请重试！");
    //            }
    //
    //            // 保存商家申请
    //            sellerApply.setUserId(member.getId());
    //            sellerApply.setName(member.getName());
    //            sellerApply.setPassword(member.getPassword());
    //            Integer insertRow = sellerApplyWriteDao.insert(sellerApply);
    //            if (insertRow == 0) {
    //                throw new BusinessException("商家申请保存失败，请重试！");
    //            }
    //
    //            // 保存商家表
    //            this.saveSellerForApply(member, userName, sellerName);
    //
    //            transactionManager.commit(status);
    //            return true;
    //        } catch (Exception e) {
    //            transactionManager.rollback(status);
    //            throw e;
    //        }
    //    }
    //
    //    /**
    //     * 新增商家入驻申请时的校验
    //     * @param sellerApply
    //     * @param userName
    //     * @param sellerName
    //     */
    //    private void checkForInsert(SellerApply sellerApply, String userName, String sellerName) {
    //        // 判断公司名称是否已经存在
    //        List<SellerApply> sellerApplys = sellerApplyWriteDao
    //            .getSellerApplyByCompany(sellerApply.getCompany());
    //        if (sellerApplys != null && sellerApplys.size() > 0) {
    //            throw new BusinessException("该公司已经注册过商家了！");
    //        }
    //        // 判断商家登录账号名是否已经存在
    //        List<Seller> sellers = sellerWriteDao.getSellerByName(userName);
    //        if (sellers != null && sellers.size() > 0) {
    //            throw new BusinessException("商家账号已存在，请重试！");
    //        }
    //        // 判断店铺名称是否已经存在
    //        sellers = sellerWriteDao.getSellerBySellerName(sellerName);
    //        if (sellers != null && sellers.size() > 0) {
    //            throw new BusinessException("店铺名称已存在，请重试！");
    //        }
    //    }
    //
    //    /**
    //     * 商家申请入驻时保存商家信息表
    //     * @param member
    //     * @param userName
    //     * @param sellerName
    //     */
    //    private void saveSellerForApply(Member member, String userName, String sellerName) {
    //        Seller seller = new Seller();
    //        seller.setMemberId(member.getId());
    //        //商家账号
    //        seller.setName(userName);
    //        //店铺名称
    //        seller.setSellerName(sellerName);
    //        seller.setSellerGrade(1);
    //        seller.setScoreService("0");
    //        seller.setScoreDeliverGoods("0");
    //        seller.setScoreDescription("0");
    //        seller.setProductNumber(0);
    //        seller.setCollectionNumber(0);
    //        seller.setCreateTime(new Date());
    //        seller.setSaleMoney(new BigDecimal(0));
    //        seller.setOrderCount(0);
    //        seller.setOrderCountOver(0);
    //        seller.setSellerKeyword("");
    //        seller.setSellerDes("");
    //        seller.setAuditStatus(Seller.AUDIT_STATE_1_SEND);
    //
    //        //保存商家
    //        int insertRow = sellerWriteDao.save(seller);
    //        if (insertRow == 0) {
    //            throw new BusinessException("商家信息保存失败，请重试！");
    //        }
    //    }
    //
    //    /**
    //     * 修改商家申请(平台修改商家申请用)
    //     * 
    //     * @param sellerApply
    //     * @param userName
    //     * @param sellerName
    //     * @return
    //     */
    //    public boolean updateSellerApplyForAdmin(SellerApply sellerApply, String userName,
    //                                             String sellerName) {
    //        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    //        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    //        TransactionStatus status = transactionManager.getTransaction(def);
    //
    //        try {
    //
    //            // 数据库中的申请
    //            SellerApply applyDb = sellerApplyWriteDao.get(sellerApply.getId());
    //            if (applyDb.getState().intValue() != SellerApply.STATE_1_SEND
    //                && applyDb.getState().intValue() != SellerApply.STATE_4_FAIL) {
    //                throw new BusinessException("只能修改提交申请和审核失败状态的商家申请！");
    //            }
    //
    //            Seller sellerDb = sellerWriteDao.getSellerByMemberId(applyDb.getUserId());
    //            if (sellerDb.getAuditStatus().intValue() != Seller.AUDIT_STATE_1_SEND) {
    //                throw new BusinessException("只能修改提交申请状态的商家申请！");
    //            }
    //
    //            // 商家申请修改时校验
    //            this.checkForUpdate(sellerApply, sellerDb, userName, sellerName);
    //
    //            // 保存商家申请
    //            Integer updateRow = sellerApplyWriteDao.update(sellerApply);
    //            if (updateRow == 0) {
    //                throw new BusinessException("商家申请修改失败，请重试！");
    //            }
    //
    //            Seller seller = new Seller();
    //            seller.setId(sellerDb.getId());
    //            //商家账号
    //            seller.setName(userName);
    //            //店铺名称
    //            seller.setSellerName(sellerName);
    //
    //            //保存商家
    //            updateRow = sellerWriteDao.update(seller);
    //            if (updateRow == 0) {
    //                throw new BusinessException("商家信息修改失败，请重试！");
    //            }
    //
    //            transactionManager.commit(status);
    //            return true;
    //        } catch (Exception e) {
    //            transactionManager.rollback(status);
    //            throw e;
    //        }
    //    }
    //
    //    /**
    //     * 商家申请修改时校验
    //     * @param sellerApply
    //     * @param sellerDb
    //     * @param userName
    //     * @param sellerName
    //     */
    //    private void checkForUpdate(SellerApply sellerApply, Seller sellerDb, String userName,
    //                                String sellerName) {
    //        // 判断公司名称是否已经存在
    //        List<SellerApply> sellerApplys = sellerApplyWriteDao
    //            .getSellerApplyByCompany(sellerApply.getCompany());
    //        if (sellerApplys != null) {
    //            if (sellerApplys.size() > 1) {
    //                throw new BusinessException("该公司已经注册过商家了！");
    //            } else if (sellerApplys.size() == 1
    //                       && !sellerApplys.get(0).getId().equals(sellerApply.getId())) {
    //                throw new BusinessException("该公司已经注册过商家了！");
    //            }
    //        }
    //        // 判断商家登录账号名是否已经存在
    //        List<Seller> sellers = sellerWriteDao.getSellerByName(userName);
    //        if (sellers != null) {
    //            if (sellers.size() > 1) {
    //                throw new BusinessException("商家账号已存在，请重试！");
    //            } else if (sellers.size() == 1 && !sellers.get(0).getId().equals(sellerDb.getId())) {
    //                throw new BusinessException("商家账号已存在，请重试！");
    //            }
    //        }
    //        // 判断店铺名称是否已经存在
    //        sellers = sellerWriteDao.getSellerBySellerName(sellerName);
    //        if (sellers != null) {
    //            if (sellers.size() > 1) {
    //                throw new BusinessException("店铺名称已存在，请重试！");
    //            } else if (sellers.size() == 1 && !sellers.get(0).getId().equals(sellerDb.getId())) {
    //                throw new BusinessException("店铺名称已存在，请重试！");
    //            }
    //        }
    //    }
    //
    //    /**
    //     * 保存商家申请表(用户端申请入驻时用)<br>
    //     * <li>添加商家申请表数据
    //     * <li>添加商家数据
    //     * 
    //     * @param  sellerApply
    //     * @return
    //     */
    //    public boolean saveSellerApplyForFront(SellerApply sellerApply, String userName,
    //                                           String sellerName, Integer memberId) {
    //        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    //        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    //        TransactionStatus status = transactionManager.getTransaction(def);
    //
    //        try {
    //
    //            SellerApply sellerApplyByUserId = sellerApplyWriteDao.getSellerApplyByUserId(memberId);
    //            if (sellerApplyByUserId != null) {
    //                throw new BusinessException("您已经申请过了，请不要重复申请！");
    //            }
    //
    //            // 申请信息校验
    //            this.checkForInsert(sellerApply, userName, sellerName);
    //
    //            // 获取用户
    //            Member member = memberWriteDao.get(memberId);
    //
    //            // 保存商家申请
    //            sellerApply.setUserId(member.getId());
    //            sellerApply.setName(member.getName());
    //            sellerApply.setPassword(member.getPassword());
    //            Integer insertRow = sellerApplyWriteDao.insert(sellerApply);
    //            if (insertRow == 0) {
    //                throw new BusinessException("商家申请保存失败，请重试！");
    //            }
    //
    //            // 保存商家表
    //            this.saveSellerForApply(member, userName, sellerName);
    //
    //            transactionManager.commit(status);
    //            return true;
    //        } catch (Exception e) {
    //            transactionManager.rollback(status);
    //            throw e;
    //        }
    //    }
    //
    //    /**
    //     * 修改商家申请(用户端商家申请时用)
    //     * 
    //     * @param sellerApply
    //     * @param userName
    //     * @param sellerName
    //     * @return
    //     */
    //    public boolean updateSellerApplyForFront(SellerApply sellerApply, String userName,
    //                                             String sellerName) {
    //        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    //        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    //        TransactionStatus status = transactionManager.getTransaction(def);
    //
    //        try {
    //
    //            // 数据库中的申请
    //            SellerApply applyDb = sellerApplyWriteDao.get(sellerApply.getId());
    //            if (applyDb.getState().intValue() != SellerApply.STATE_1_SEND
    //                && applyDb.getState().intValue() != SellerApply.STATE_4_FAIL) {
    //                throw new BusinessException("只能修改提交申请和审核失败状态的商家申请！");
    //            }
    //
    //            Seller sellerDb = sellerWriteDao.getSellerByMemberId(applyDb.getUserId());
    //            if (sellerDb.getAuditStatus().intValue() != Seller.AUDIT_STATE_1_SEND) {
    //                throw new BusinessException("只能修改提交申请状态的商家申请！");
    //            }
    //
    //            // 商家申请修改时校验
    //            this.checkForUpdate(sellerApply, sellerDb, userName, sellerName);
    //
    //            // 保存商家申请
    //            Integer updateRow = sellerApplyWriteDao.update(sellerApply);
    //            if (updateRow == 0) {
    //                throw new BusinessException("商家申请修改失败，请重试！");
    //            }
    //
    //            Seller seller = new Seller();
    //            seller.setId(sellerDb.getId());
    //            //商家账号
    //            seller.setName(userName);
    //            //店铺名称
    //            seller.setSellerName(sellerName);
    //
    //            //保存商家
    //            updateRow = sellerWriteDao.update(seller);
    //            if (updateRow == 0) {
    //                throw new BusinessException("商家信息修改失败，请重试！");
    //            }
    //
    //            transactionManager.commit(status);
    //            return true;
    //        } catch (Exception e) {
    //            transactionManager.rollback(status);
    //            throw e;
    //        }
    //    }
    //
    //    /**
    //     * 根据公司名称查询入驻申请
    //     * @param company
    //     * @return
    //     */
    //    public List<SellerApply> getSellerApplyByCompany(String company) {
    //        return sellerApplyWriteDao.getSellerApplyByCompany(company);
    //    }
}
