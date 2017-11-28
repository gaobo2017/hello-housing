package com.ejavashop.model.member;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.Md5;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.TimeUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.member.MemberCollectionProductReadDao;
import com.ejavashop.dao.shop.read.member.MemberCollectionSellerReadDao;
import com.ejavashop.dao.shop.read.product.ProductAskReadDao;
import com.ejavashop.dao.shop.read.product.ProductCommentsReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.write.member.MemberBalanceLogsWriteDao;
import com.ejavashop.dao.shop.write.member.MemberCollectionProductWriteDao;
import com.ejavashop.dao.shop.write.member.MemberCollectionSellerWriteDao;
import com.ejavashop.dao.shop.write.member.MemberGradeConfigWriteDao;
import com.ejavashop.dao.shop.write.member.MemberGradeIntegralLogsWriteDao;
import com.ejavashop.dao.shop.write.member.MemberGradeUpLogsWriteDao;
import com.ejavashop.dao.shop.write.member.MemberLoginLogsWriteDao;
import com.ejavashop.dao.shop.write.member.MemberRuleWriteDao;
import com.ejavashop.dao.shop.write.member.MemberWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersWriteDao;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberBalanceLogs;
import com.ejavashop.entity.member.MemberCollectionProduct;
import com.ejavashop.entity.member.MemberCollectionSeller;
import com.ejavashop.entity.member.MemberGradeConfig;
import com.ejavashop.entity.member.MemberGradeIntegralLogs;
import com.ejavashop.entity.member.MemberGradeUpLogs;
import com.ejavashop.entity.member.MemberLoginLogs;
import com.ejavashop.entity.member.MemberRule;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAsk;
import com.ejavashop.entity.product.ProductComments;
import com.ejavashop.vo.member.FrontCheckPwdVO;
import com.ejavashop.vo.member.FrontMemberProductBehaveStatisticsVO;

@Component(value = "memberModel")
public class MemberModel {
    private static Logger                   log = LogManager.getLogger(MemberModel.class);

    @Resource
    private MemberWriteDao                  memberWriteDao;

    @Resource
    private MemberGradeIntegralLogsWriteDao memberGradeIntegralLogsWriteDao;

    @Resource
    private DataSourceTransactionManager    transactionManager;

    @Resource
    private MemberGradeConfigWriteDao       memberGradeConfigWriteDao;

    @Resource
    private MemberGradeUpLogsWriteDao       memberGradeUpLogsWriteDao;

    @Resource
    private MemberLoginLogsWriteDao         memberLoginLogsWriteDao;

    @Resource
    private MemberBalanceLogsWriteDao       memberBalanceLogsWriteDao;

    @Resource
    private MemberCollectionSellerWriteDao  memberCollectionSellerWriteDao;
    @Resource
    private MemberCollectionSellerReadDao   memberCollectionSellerReadDao;

    @Resource
    private MemberCollectionProductWriteDao memberCollectionProductWriteDao;
    @Resource
    private MemberCollectionProductReadDao  memberCollectionProductReadDao;

    @Resource
    private MemberRuleWriteDao              memberRuleWriteDao;

    @Resource
    private OrdersWriteDao                  ordersWriteDao;
    @Resource
    private ProductReadDao                  productReadDao;
    @Resource
    private ProductAskReadDao               productAskReadDao;
    @Resource
    private ProductCommentsReadDao          productCommentsReadDao;

    /**
    * 根据id取得会员
    * @param  memberId
    * @return
    */
    public Member getMemberById(Integer memberId) {
        return memberWriteDao.get(memberId);
    }

    /**
     * 保存会员
     * @param  member
     * @return
     */
    public Integer saveMember(Member member) {
        return memberWriteDao.save(member);
    }

    /**
    * 更新会员
    * @param  member
    * @return
    */
    public boolean updateMember(Member member) {
        return memberWriteDao.update(member) > 0;
    }

    public Integer getMembersCount(Map<String, String> queryMap) {
        return memberWriteDao.getMembersCount(queryMap);
    }

    public List<Member> getMembers(Map<String, String> queryMap, Integer start, Integer size) {
        return memberWriteDao.getMembers(queryMap, start, size);
    }

    public boolean updateMemberValue(MemberGradeIntegralLogs logs) {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Assert.notNull(memberWriteDao, "Property 'memberWriteDao' is required.");
            Assert.notNull(memberGradeIntegralLogsWriteDao,
                "Property 'memberGradeIntegralLogsWriteDao' is required.");
            Assert.notNull(memberGradeUpLogsWriteDao,
                "Property 'memberGradeUpLogsWriteDao' is required.");
            //  参数校验
            if (logs.getMemberId() == null) {
                throw new BusinessException("会员ID不能为空，请重试！");
            } else if (logs.getValue() == null) {
                throw new BusinessException("经验值或积分值不能为空，请重试！");
            } else if (logs.getOptType() == null) {
                throw new BusinessException("动作类型不能为空，请重试！");
            } else if (logs.getType() == null) {
                throw new BusinessException("操作类型不能为空，请重试！");
            }

            // 如果动作类型是6、系统减少，则value值变成负数
            if (logs.getOptType().intValue() == ConstantsEJS.MEMBER_GRD_INT_LOG_OPT_T_6) {
                Integer value = logs.getValue();
                logs.setValue(value - (value * 2));
            }

            // 取得会员
            Member member = memberWriteDao.get(logs.getMemberId());
            if (member == null) {
                throw new BusinessException("会员不存在，请重试！");
            }

            // 新的等级，初始化跟旧等级一致
            Integer newGrd = member.getGrade();
            // 操作类型是经验值的情况
            if (ConstantsEJS.MEMBER_GRD_INT_LOG_T_1 == logs.getType().intValue()) {
                /** 如果是操作经验值，需要相应判断会员的等级 */
                // 会员已有经验值
                Integer oldGrdVal = member.getGradeValue();
                // 会员旧的等级
                Integer oldGrd = member.getGrade();
                // 新的经验值
                Integer newGrdVal = oldGrdVal + logs.getValue();
                // 如果新的经验值是负数，说明经验值不够扣减，返回错误
                if (newGrdVal < 0) {
                    throw new BusinessException("对不起，您的经验值不够了！");
                }

                // 取得会员等级配置，判断会员等级
                MemberGradeConfig memberGradeConfig = memberGradeConfigWriteDao
                    .get(ConstantsEJS.MEMBER_GRADE_CONFIG_ID);

                if (newGrdVal >= memberGradeConfig.getGrade5()) {
                    newGrd = Member.GRADE_5;
                } else if (newGrdVal >= memberGradeConfig.getGrade4()) {
                    newGrd = Member.GRADE_4;
                } else if (newGrdVal >= memberGradeConfig.getGrade3()) {
                    newGrd = Member.GRADE_3;
                } else if (newGrdVal >= memberGradeConfig.getGrade2()) {
                    newGrd = Member.GRADE_2;
                } else if (newGrdVal >= memberGradeConfig.getGrade1()) {
                    newGrd = Member.GRADE_1;
                }
                // 如果等级有变化，记录等级变化日志表
                if (!newGrd.equals(oldGrd)) {
                    // 会员等级升级日志表
                    MemberGradeUpLogs upLogs = new MemberGradeUpLogs();
                    upLogs.setMemberId(logs.getMemberId());
                    upLogs.setMemberName(logs.getMemberName());
                    upLogs.setBeforeExper(oldGrdVal);
                    upLogs.setAfterExper(newGrdVal);
                    upLogs.setBeforeGrade(oldGrd);
                    upLogs.setAfterGrade(newGrd);
                    upLogs.setCreateTime(new Date());
                    // 记录日志
                    int upLog = memberGradeUpLogsWriteDao.save(upLogs);
                    if (upLog == 0) {
                        throw new BusinessException("会员等级升级日志记录失败，请重试！");
                    }
                }
                // 更新会员经验值、等级
                Member newMember = new Member();
                newMember.setId(logs.getMemberId());
                newMember.setGrade(newGrd);
                newMember.setGradeValue(logs.getValue());
                Integer updateGrade = memberWriteDao.updateGrade(newMember);
                if (updateGrade == 0) {
                    throw new BusinessException("更新会员经验值及等级失败，请重试！");
                }
            } else if (ConstantsEJS.MEMBER_GRD_INT_LOG_T_2 == logs.getType().intValue()) {
                // 如果新的经验值是负数，说明经验值不够扣减，返回错误
                if ((member.getIntegral() + logs.getValue()) < 0) {
                    throw new BusinessException("对不起，您的积分值不够了！");
                }
                // 更新会员的积分
                Member newMember = new Member();
                newMember.setId(logs.getMemberId());
                newMember.setIntegral(logs.getValue());
                Integer updateIntegral = memberWriteDao.updateIntegral(newMember);
                if (updateIntegral == 0) {
                    throw new BusinessException("更新会员积分值失败，请重试！");
                }
            }

            // 记录【会员经验积分日志表】
            Integer grdIntLog = memberGradeIntegralLogsWriteDao.save(logs);
            if (grdIntLog == 0) {
                throw new BusinessException("会员经验积分日志记录失败，请重试！");
            }

            transactionManager.commit(status);
            return true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            log.error("[MemberService][updateMemberValue]更新会员经验值与积分发生异常:", be);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[MemberService][updateMemberValue]更新会员经验值与积分发生异常:", e);
            throw e;
        }
    }

    /**
     * 根据会员ID获取会员等级升级日志数量
     * @param memberId
     * @return
     */
    public Integer getMemberGradeUpLogsCount(Integer memberId) {
        return memberGradeUpLogsWriteDao.getMemberGradeUpLogsCount(memberId);
    }

    /**
     * 根据会员ID获取会员等级升级日志
     * @param memberId
     * @param start
     * @param size
     * @return
     */
    public List<MemberGradeUpLogs> getMemberGradeUpLogs(Integer memberId, Integer start,
                                                        Integer size) {
        return memberGradeUpLogsWriteDao.getMemberGradeUpLogs(memberId, start, size);
    }

    /**
     * 根据会员ID和类型获取会员经验值积分值变更日志数量
     * @param memberId
     * @param type
     * @return
     */
    public Integer getMemberGradeIntegralLogsCount(Integer memberId, Integer type) {
        return memberGradeIntegralLogsWriteDao.getMemberGradeIntegralLogsCount(memberId, type);
    }

    /**
     * 根据会员ID和类型获取会员经验值积分值变更日志
     * @param memberId
     * @param type
     * @param start
     * @param size
     * @return
     */
    public List<MemberGradeIntegralLogs> getMemberGradeIntegralLogs(Integer memberId, Integer type,
                                                                    Integer start, Integer size) {
        return memberGradeIntegralLogsWriteDao.getMemberGradeIntegralLogs(memberId, type, start,
            size);
    }

    /**
     * 根据会员ID获取会员登录日志数量
     * @param memberId
     * @return
     */
    public Integer getMemberLoginLogsCount(Integer memberId) {
        return memberLoginLogsWriteDao.getMemberLoginLogsCount(memberId);
    }

    /**
     * 根据会员ID获取会员登录日志
     * @param memberId
     * @param start
     * @param size
     * @return
     */
    public List<MemberLoginLogs> getMemberLoginLogs(Integer memberId, Integer start, Integer size) {
        return memberLoginLogsWriteDao.getMemberLoginLogs(memberId, start, size);
    }

    /**
     * 根据会员ID获取会员收藏商铺数量
     * @param memberId
     * @return
     */
    public Integer getMemberCollectionSellersCount(Integer memberId) {
        return memberCollectionSellerWriteDao.getMemberCollectionSellersCount(memberId);
    }

    /**
     * 根据会员ID获取会员收藏商铺
     * @param memberId
     * @param start
     * @param size
     * @return
     */
    public List<MemberCollectionSeller> getMemberCollectionSellers(Integer memberId, Integer start,
                                                                   Integer size) {
        return memberCollectionSellerWriteDao.getMemberCollectionSellers(memberId, start, size);
    }

    /**
     * 根据会员ID获取会员收藏商品数量
     * @param memberId
     * @return
     */
    public Integer getMemberCollectionProductsCount(Integer memberId) {
        return memberCollectionProductWriteDao.getMemberCollectionProductsCount(memberId);
    }

    /**
     * 根据会员ID获取会员收藏商品
     * @param memberId
     * @param start
     * @param size
     * @return
     */
    public List<MemberCollectionProduct> getMemberCollectionProducts(Integer memberId,
                                                                     Integer start, Integer size) {
        return memberCollectionProductWriteDao.getMemberCollectionProducts(memberId, start, size);
    }

    /**
     * 更新会员余额
     * @param logs
     * @return
     */
    public boolean updateMemberBalance(MemberBalanceLogs logs) {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Assert.notNull(memberWriteDao, "Property 'memberWriteDao' is required.");
            Assert.notNull(memberBalanceLogsWriteDao,
                "Property 'memberBalanceLogsWriteDao' is required.");
            //  参数校验
            if (logs.getMemberId() == null) {
                throw new BusinessException("会员ID不能为空，请重试！");
            } else if (logs.getMoney() == null) {
                throw new BusinessException("变更金额不能为空，请重试！");
            } else if (logs.getState() == null) {
                throw new BusinessException("动作类型不能为空，请重试！");
            }

            // 如果动作类型是6、系统减少，则value值变成负数
            if (logs.getState().equals(MemberBalanceLogs.STATE_3)
                || logs.getState().equals(MemberBalanceLogs.STATE_4)
                || logs.getState().equals(MemberBalanceLogs.STATE_6)) {
                BigDecimal money = logs.getMoney();
                logs.setMoney(money.subtract(money.multiply(new BigDecimal(2))));
            }

            // 取得会员
            Member member = memberWriteDao.get(logs.getMemberId());
            if (member == null) {
                throw new BusinessException("会员不存在，请重试！");
            }

            // 如果余额变更后小于零，则提示错误
            if (member.getBalance().add(logs.getMoney()).compareTo(new BigDecimal(0)) < 0) {
                throw new BusinessException("您的余额不足了，请充值！");
            }

            // 更新会员的积分
            Member newMember = new Member();
            newMember.setId(logs.getMemberId());
            newMember.setBalance(logs.getMoney());
            Integer updateBalance = memberWriteDao.updateBalance(newMember);
            if (updateBalance == 0) {
                throw new BusinessException("更新会员余额失败，请重试！");
            }

            logs.setMemberName(member.getName());
            logs.setMoneyBefore(member.getBalance());
            logs.setMoneyAfter(member.getBalance().add(logs.getMoney()));

            // 记录【会员账户余额变化日志表】
            Integer balanceLog = memberBalanceLogsWriteDao.save(logs);
            if (balanceLog == 0) {
                throw new BusinessException("会员账户余额变化日志记录失败，请重试！");
            }

            transactionManager.commit(status);
            return true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            log.error("[MemberService][updateMemberBalance]更新会员余额发生异常:", be);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[MemberService][updateMemberBalance]更新会员余额发生异常:", e);
            throw e;
        }
    }

    /**
     * 会员登录，修改会员登录信息，记录登录日志
     * @param memberName 用户名
     * @param password 密码
     * @param ip
     * @param source 登录来源
     * @return
     * @throws Exception
     */
    public Member memberLogin(String memberName, String password, String ip,
                              Integer source) throws Exception {

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            Assert.notNull(memberWriteDao, "Property 'memberWriteDao' is required.");
            Assert.notNull(memberLoginLogsWriteDao,
                "Property 'memberLoginLogsWriteDao' is required.");
            // 参数校验
            if (StringUtil.isEmpty(memberName)) {
                throw new BusinessException("会员名称不能为空，请重试！");
            } else if (StringUtil.isEmpty(password)) {
                throw new BusinessException("密码不能为空，请重试！");
            }

            password = Md5.getMd5String(password);

            //查询用户
            List<Member> members = memberWriteDao.getByNameAndPwd(memberName, password);
            Member member = null;
            if (members.size() == 0) {
                throw new BusinessException("用户名或密码错误！");
            } else if (members.size() > 1) {
                throw new BusinessException("用户名重复，请联系系统管理员！");
            }
            member = members.get(0);

            if (member.getStatus() != Member.STATUS_1_ON) {
                throw new BusinessException("会员账户状态异常，请联系系统管理员！");
            }
            //1、更新会员表登录信息
            Member newMember = new Member();
            newMember.setId(member.getId());
            newMember.setLastLoginTime(new Date());
            newMember.setLastLoginIp(ip);
            //登录次数累加
            newMember.setLoginNumber(member.getLoginNumber() + 1);
            memberWriteDao.update(newMember);

            //2、记录登录日志
            MemberLoginLogs memberLoginLogs = new MemberLoginLogs();
            memberLoginLogs.setMemberId(member.getId());
            memberLoginLogs.setMemberName(member.getName());
            memberLoginLogs.setLoginIp(ip);
            memberLoginLogs.setSource(source);
            memberLoginLogsWriteDao.save(memberLoginLogs);

            transactionManager.commit(status);
            return member;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 会员注册
     * @param member
     * @return
     * @throws Exception
     */
    public Member memberRegister(Member member) throws Exception {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Assert.notNull(memberWriteDao, "Property 'memberWriteDao' is required.");
            // 参数校验
            if (member == null) {
                throw new BusinessException("注册信息不能为空，请重试！");
            } else if (StringUtil.isEmpty(member.getName())) {
                throw new BusinessException("会员名称不能为空，请重试！");
            } else if (StringUtil.isEmpty(member.getPassword())) {
                throw new BusinessException("密码不能为空，请重试！");
            }
            //判断用户名是否已存在
            List<Member> byNameList = memberWriteDao.getByName(member.getName());
            if (byNameList != null && byNameList.size() > 0) {
                throw new BusinessException("会员名称已存在，请重试！");
            }

            // 保存会员注册信息
            int count = memberWriteDao.save(member);
            if (count == 0) {
                throw new BusinessException("注册信息保存失败，请重试！");
            }

            transactionManager.commit(status);
            return member;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 根据会员名称取会员
     * @param name
     * @return
     */
    public List<Member> getMemberByName(String name) {
        return memberWriteDao.getByName(name);
    }

    public MemberGradeConfig getMemberGradeConfig(Integer memberGradeConfigId) {
        return memberGradeConfigWriteDao.get(memberGradeConfigId);
    }

    public MemberRule getMemberRule(Integer memberRuleId, Integer state) {
        return memberRuleWriteDao.get(memberRuleId, state);
    }

    public boolean memberRegistSendValue(Integer memberId, String memberName) throws Exception {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Assert.notNull(memberWriteDao, "Property 'memberWriteDao' is required.");
            Assert.notNull(memberGradeIntegralLogsWriteDao,
                "Property 'memberGradeIntegralLogsWriteDao' is required.");
            Assert.notNull(memberGradeUpLogsWriteDao,
                "Property 'memberGradeUpLogsWriteDao' is required.");
            //  参数校验
            if (memberId == null) {
                throw new BusinessException("会员ID不能为空，请重试！");
            } else if (memberName == null) {
                throw new BusinessException("会员名称不能为空，请重试！");
            }

            // 会员等级规则配置
            MemberGradeConfig memberGradeConfig = this
                .getMemberGradeConfig(ConstantsEJS.MEMBER_GRADE_CONFIG_ID);
            // 经验值规则
            MemberRule gradeRule = this.getMemberRule(ConstantsEJS.MEMBER_RULE_GRADE_ID, 1);
            // 积分规则
            MemberRule integralRule = this.getMemberRule(ConstantsEJS.MEMBER_RULE_INTEGRAL_ID, 1);
            // 注册送经验值数
            Integer gradeValue = gradeRule == null ? 0 : gradeRule.getRegister();
            // 注册送积分数
            Integer integralValue = integralRule == null ? 0 : integralRule.getRegister();

            // 取得会员
            Member member = memberWriteDao.get(memberId);
            if (member == null) {
                throw new BusinessException("会员不存在，请重试！");
            }

            // 经验值或者积分值至少有一个不为0时才调用送分逻辑，都为0直接返回
            if (gradeValue.equals(0) && integralValue.equals(0)) {
                transactionManager.commit(status);
                return true;
            }

            // 设置经验值积分日志表的共同属性
            MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
            memberGradeIntegralLogs.setMemberId(memberId);
            memberGradeIntegralLogs.setMemberName(memberName);
            memberGradeIntegralLogs.setOptType(ConstantsEJS.MEMBER_GRD_INT_LOG_OPT_T_1);
            memberGradeIntegralLogs.setOptDes("会员注册");
            memberGradeIntegralLogs.setCreateTime(new Date());

            // 会员初始等级
            Integer grade = Member.GRADE_1;
            // 操作类型是经验值，且不为零的情况
            if (!gradeValue.equals(0)) {
                /** 判断会员的等级，如果注册送的经验值肯定比Member.GRADE_2小，这步可省略 */
                if (memberGradeConfig != null) {
                    if (gradeValue >= memberGradeConfig.getGrade5()) {
                        grade = Member.GRADE_5;
                    } else if (gradeValue >= memberGradeConfig.getGrade4()) {
                        grade = Member.GRADE_4;
                    } else if (gradeValue >= memberGradeConfig.getGrade3()) {
                        grade = Member.GRADE_3;
                    } else if (gradeValue >= memberGradeConfig.getGrade2()) {
                        grade = Member.GRADE_2;
                    } else if (gradeValue >= memberGradeConfig.getGrade1()) {
                        grade = Member.GRADE_1;
                    }
                }
                // 如果等级有变化，记录等级变化日志表
                if (!grade.equals(Member.GRADE_1)) {
                    // 会员等级升级日志表
                    MemberGradeUpLogs upLogs = new MemberGradeUpLogs();
                    upLogs.setMemberId(memberId);
                    upLogs.setMemberName(memberName);
                    upLogs.setBeforeExper(0);
                    upLogs.setAfterExper(gradeValue);
                    upLogs.setBeforeGrade(Member.GRADE_1);
                    upLogs.setAfterGrade(grade);
                    upLogs.setCreateTime(new Date());
                    // 记录日志
                    int upLog = memberGradeUpLogsWriteDao.save(upLogs);
                    if (upLog == 0) {
                        throw new BusinessException("会员等级升级日志记录失败，请重试！");
                    }
                }

                // 记录送经验值的日志【会员经验积分日志表】
                memberGradeIntegralLogs.setType(ConstantsEJS.MEMBER_GRD_INT_LOG_T_1);
                memberGradeIntegralLogs.setValue(gradeValue);
                Integer grdLog = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                if (grdLog == 0) {
                    throw new BusinessException("会员经验值日志记录失败，请重试！");
                }
            }
            // 操作类型是积分值，且不为零的情况
            if (!integralValue.equals(0)) {
                // 记录送积分值的日志【会员经验积分日志表】
                memberGradeIntegralLogs.setType(ConstantsEJS.MEMBER_GRD_INT_LOG_T_2);
                memberGradeIntegralLogs.setValue(integralValue);
                Integer intLog = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
                if (intLog == 0) {
                    throw new BusinessException("会员积分值日志记录失败，请重试！");
                }
            }
            // 更新会员的经验值和积分
            member.setGrade(grade);
            member.setGradeValue(gradeValue);
            member.setIntegral(integralValue);
            member.setUpdateTime(new Date());
            Integer update = memberWriteDao.updateValue(member);
            if (update == 0) {
                throw new BusinessException("更新会员经验值、积分值失败，请重试！");
            }

            transactionManager.commit(status);
            return true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[FrontMemberService][memberRegistSendValue]会员注册时送经验值与积分发生异常:", e);
            throw e;
        }
    }

    public boolean memberLoginSendValue(Integer memberId, String memberName) throws Exception {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Assert.notNull(memberWriteDao, "Property 'memberWriteDao' is required.");
            Assert.notNull(memberGradeIntegralLogsWriteDao,
                "Property 'memberGradeIntegralLogsWriteDao' is required.");
            Assert.notNull(memberGradeUpLogsWriteDao,
                "Property 'memberGradeUpLogsWriteDao' is required.");
            //  参数校验
            if (memberId == null) {
                throw new BusinessException("会员ID不能为空，请重试！");
            } else if (memberName == null) {
                throw new BusinessException("会员名称不能为空，请重试！");
            }

            // 每天首次登陆才送
            String startTime = TimeUtil.getToday() + " 00:00:00";
            String endTime = TimeUtil.getToday() + " 23:59:59";
            List<MemberLoginLogs> logsList = memberLoginLogsWriteDao.getByMemberIdAndTime(memberId,
                startTime, endTime);
            // 如果超过一次了则返回
            if (logsList != null && logsList.size() > 1) {
                transactionManager.commit(status);
                return true;
            }

            // 会员等级规则配置
            MemberGradeConfig memberGradeConfig = this
                .getMemberGradeConfig(ConstantsEJS.MEMBER_GRADE_CONFIG_ID);
            // 经验值规则
            MemberRule gradeRule = this.getMemberRule(ConstantsEJS.MEMBER_RULE_GRADE_ID, 1);
            // 积分规则
            MemberRule integralRule = this.getMemberRule(ConstantsEJS.MEMBER_RULE_INTEGRAL_ID, 1);
            // 登录送经验值数
            Integer gradeValue = gradeRule == null ? 0 : gradeRule.getLogin();
            // 登录送积分数
            Integer integralValue = integralRule == null ? 0 : integralRule.getLogin();

            sendValue(memberId, memberName, gradeValue, integralValue, memberGradeConfig,
                ConstantsEJS.MEMBER_GRD_INT_LOG_OPT_T_2, "会员登录");

            transactionManager.commit(status);
            return true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[FrontMemberService][memberLoginSendValue]会员登录时送经验值与积分发生异常:", e);
            throw e;
        }
    }

    public boolean memberOrderSendValue(Integer memberId, String memberName,
                                        Integer orderId) throws Exception {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Assert.notNull(memberWriteDao, "Property 'memberWriteDao' is required.");
            Assert.notNull(memberGradeIntegralLogsWriteDao,
                "Property 'memberGradeIntegralLogsWriteDao' is required.");
            Assert.notNull(memberGradeUpLogsWriteDao,
                "Property 'memberGradeUpLogsWriteDao' is required.");
            //  参数校验
            if (memberId == null) {
                throw new BusinessException("会员ID不能为空，请重试！");
            } else if (memberName == null) {
                throw new BusinessException("会员名称不能为空，请重试！");
            } else if (orderId == null) {
                throw new BusinessException("订单号不能为空，请重试！");
            }
            // 会员等级规则配置
            MemberGradeConfig memberGradeConfig = this
                .getMemberGradeConfig(ConstantsEJS.MEMBER_GRADE_CONFIG_ID);
            // 经验值规则
            MemberRule gradeRule = this.getMemberRule(ConstantsEJS.MEMBER_RULE_GRADE_ID, 1);
            // 积分规则
            MemberRule integralRule = this.getMemberRule(ConstantsEJS.MEMBER_RULE_INTEGRAL_ID, 1);

            Orders orders = ordersWriteDao.get(orderId);
            BigDecimal amount = orders.getMoneyOrder().subtract(orders.getMoneyIntegral());
            // 下单送经验值数比例
            Integer gradeValuePro = gradeRule == null ? 0 : gradeRule.getOrderBuy();
            // 送经验值上限
            Integer gradeValuemax = gradeRule == null ? 0 : gradeRule.getOrderMax();
            // 计算送经验值，如果比例是0，则直接设定送0经验值，否则计算（订单总额/比例）
            // 如果（订单总额/比例）大于max送max分
            // 如果（订单总额/比例）小于max送（订单总额/比例）分
            Integer gradeValue = 0;
            if (!gradeValuePro.equals(0)) {
                gradeValue = amount.intValue() / gradeValuePro;
                gradeValue = gradeValue > gradeValuemax ? gradeValuemax : gradeValue;
            }
            // 下单送积分数比例
            Integer integralValuePro = integralRule == null ? 0 : integralRule.getOrderBuy();
            // 送积分数上限
            Integer integralValuemax = integralRule == null ? 0 : integralRule.getOrderMax();
            Integer integralValue = 0;
            // 计算送积分数，原则跟经验值一样
            if (!integralValuePro.equals(0)) {
                integralValue = amount.intValue() / integralValuePro;
                integralValue = integralValue > integralValuemax ? integralValuemax : integralValue;
            }

            sendValue(memberId, memberName, gradeValue, integralValue, memberGradeConfig,
                ConstantsEJS.MEMBER_GRD_INT_LOG_OPT_T_3, "订单" + orders.getOrderSn() + "送积分或经验值");

            transactionManager.commit(status);
            return true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[FrontMemberService][memberOrderSendValue]会员下单时送经验值与积分发生异常:", e);
            throw e;
        }
    }

    /**
     * 下单送积分（不带事务管理，在调用的方法中有事务处理）
     * @param memberId
     * @param memberName
     * @param orderId
     * @return
     * @throws Exception
     */
    public boolean memberOrderSendValueNoTrans(Integer memberId, String memberName,
                                               Integer orderId) throws Exception {
        Assert.notNull(memberWriteDao, "Property 'memberWriteDao' is required.");
        Assert.notNull(memberGradeIntegralLogsWriteDao,
            "Property 'memberGradeIntegralLogsWriteDao' is required.");
        Assert.notNull(memberGradeUpLogsWriteDao,
            "Property 'memberGradeUpLogsWriteDao' is required.");
        //  参数校验
        if (memberId == null) {
            throw new BusinessException("会员ID不能为空，请重试！");
        } else if (memberName == null) {
            throw new BusinessException("会员名称不能为空，请重试！");
        } else if (orderId == null) {
            throw new BusinessException("订单号不能为空，请重试！");
        }
        // 会员等级规则配置
        MemberGradeConfig memberGradeConfig = this
            .getMemberGradeConfig(ConstantsEJS.MEMBER_GRADE_CONFIG_ID);
        // 经验值规则
        MemberRule gradeRule = this.getMemberRule(ConstantsEJS.MEMBER_RULE_GRADE_ID, 1);
        // 积分规则
        MemberRule integralRule = this.getMemberRule(ConstantsEJS.MEMBER_RULE_INTEGRAL_ID, 1);

        Orders orders = ordersWriteDao.get(orderId);
        BigDecimal amount = orders.getMoneyOrder().subtract(orders.getMoneyIntegral());
        // 下单送经验值数比例
        Integer gradeValuePro = gradeRule == null ? 0 : gradeRule.getOrderBuy();
        // 送经验值上限
        Integer gradeValuemax = gradeRule == null ? 0 : gradeRule.getOrderMax();
        // 计算送经验值，如果比例是0，则直接设定送0经验值，否则计算（订单总额/比例）
        // 如果（订单总额/比例）大于max送max分
        // 如果（订单总额/比例）小于max送（订单总额/比例）分
        Integer gradeValue = 0;
        if (!gradeValuePro.equals(0)) {
            gradeValue = amount.intValue() / gradeValuePro;
            gradeValue = gradeValue > gradeValuemax ? gradeValuemax : gradeValue;
        }
        // 下单送积分数比例
        Integer integralValuePro = integralRule == null ? 0 : integralRule.getOrderBuy();
        // 送积分数上限
        Integer integralValuemax = integralRule == null ? 0 : integralRule.getOrderMax();
        Integer integralValue = 0;
        // 计算送积分数，原则跟经验值一样
        if (!integralValuePro.equals(0)) {
            integralValue = amount.intValue() / integralValuePro;
            integralValue = integralValue > integralValuemax ? integralValuemax : integralValue;
        }

        sendValue(memberId, memberName, gradeValue, integralValue, memberGradeConfig,
            ConstantsEJS.MEMBER_GRD_INT_LOG_OPT_T_3, "订单" + orders.getOrderSn() + "送积分或经验值");

        return true;
    }

    public boolean memberEvaluateSendValue(Integer memberId, String memberName,
                                           Integer productId) throws Exception {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Assert.notNull(memberWriteDao, "Property 'memberWriteDao' is required.");
            Assert.notNull(memberGradeIntegralLogsWriteDao,
                "Property 'memberGradeIntegralLogsWriteDao' is required.");
            Assert.notNull(memberGradeUpLogsWriteDao,
                "Property 'memberGradeUpLogsWriteDao' is required.");
            //  参数校验
            if (memberId == null) {
                throw new BusinessException("会员ID不能为空，请重试！");
            } else if (memberName == null) {
                throw new BusinessException("会员名称不能为空，请重试！");
            } else if (productId == null) {
                throw new BusinessException("商品ID不能为空，请重试！");
            }

            // 会员等级规则配置
            MemberGradeConfig memberGradeConfig = this
                .getMemberGradeConfig(ConstantsEJS.MEMBER_GRADE_CONFIG_ID);
            // 经验值规则
            MemberRule gradeRule = this.getMemberRule(ConstantsEJS.MEMBER_RULE_GRADE_ID, 1);
            // 积分规则
            MemberRule integralRule = this.getMemberRule(ConstantsEJS.MEMBER_RULE_INTEGRAL_ID, 1);

            // 评论送经验值数
            Integer gradeValue = gradeRule == null ? 0 : gradeRule.getOrderEvaluate();
            // 评论送积分数
            Integer integralValue = integralRule == null ? 0 : integralRule.getOrderEvaluate();

            sendValue(memberId, memberName, gradeValue, integralValue, memberGradeConfig,
                ConstantsEJS.MEMBER_GRD_INT_LOG_OPT_T_4, "评论商品" + productId + "送积分");

            transactionManager.commit(status);
            return true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("[FrontMemberService][memberEvaluateSendValue]会员评论时送经验值与积分发生异常:", e);
            throw e;
        }
    }

    /**
     * 给会员送经验值、积分
     * 
     * @param memberId 会员ID，必须
     * @param memberName 会员名，不能为null
     * @param gradeValue 送的经验值，必须
     * @param integralValue 送的积分值，必须
     * @param memberGradeConfig 会员等级配置
     * @param optType 具体操作1、会员注册；2、会员登录；3、商品购买；4、商品评论；5、系统添加；6、系统减少
     * @param optDes 操作描述，订单记录订单号，商品评论记录商品ID
     * @return
     */
    private Member sendValue(Integer memberId, String memberName, Integer gradeValue,
                             Integer integralValue, MemberGradeConfig memberGradeConfig,
                             Integer optType, String optDes) {
        // 取得会员
        Member member = memberWriteDao.get(memberId);
        if (member == null) {
            throw new BusinessException("会员不存在，请重试！");
        }

        // 经验值或者积分值至少有一个不为0时才调用送分逻辑，都为0直接返回
        if (gradeValue.equals(0) && integralValue.equals(0)) {
            return member;
        }

        // 设置经验值积分日志表的共同属性
        MemberGradeIntegralLogs memberGradeIntegralLogs = new MemberGradeIntegralLogs();
        memberGradeIntegralLogs.setMemberId(memberId);
        memberGradeIntegralLogs.setMemberName(memberName);
        memberGradeIntegralLogs.setOptType(optType);
        memberGradeIntegralLogs.setOptDes(optDes);
        memberGradeIntegralLogs.setCreateTime(new Date());

        // 会员原来等级
        Integer oldGrade = member.getGrade();
        // 会员新的等级初始化跟原来等级一样
        Integer grade = oldGrade;
        // 会员新的经验值
        Integer newGradeValInteger = gradeValue + member.getGradeValue();
        // 操作类型是经验值，且不为零的情况
        if (!gradeValue.equals(0)) {
            // 判断会员的等级
            if (memberGradeConfig != null) {
                if (newGradeValInteger >= memberGradeConfig.getGrade5()) {
                    grade = Member.GRADE_5;
                } else if (newGradeValInteger >= memberGradeConfig.getGrade4()) {
                    grade = Member.GRADE_4;
                } else if (newGradeValInteger >= memberGradeConfig.getGrade3()) {
                    grade = Member.GRADE_3;
                } else if (newGradeValInteger >= memberGradeConfig.getGrade2()) {
                    grade = Member.GRADE_2;
                } else if (newGradeValInteger >= memberGradeConfig.getGrade1()) {
                    grade = Member.GRADE_1;
                }
            }
            // 如果等级有变化，记录等级变化日志表
            if (!grade.equals(oldGrade)) {
                // 会员等级升级日志表
                MemberGradeUpLogs upLogs = new MemberGradeUpLogs();
                upLogs.setMemberId(memberId);
                upLogs.setMemberName(memberName);
                upLogs.setBeforeExper(member.getGradeValue());
                upLogs.setAfterExper(newGradeValInteger);
                upLogs.setBeforeGrade(oldGrade);
                upLogs.setAfterGrade(grade);
                upLogs.setCreateTime(new Date());
                // 记录日志
                int upLog = memberGradeUpLogsWriteDao.save(upLogs);
                if (upLog == 0) {
                    throw new BusinessException("会员等级升级日志记录失败，请重试！");
                }
            }

            // 记录送经验值的日志【会员经验积分日志表】
            memberGradeIntegralLogs.setType(ConstantsEJS.MEMBER_GRD_INT_LOG_T_1);
            memberGradeIntegralLogs.setValue(gradeValue);
            Integer grdLog = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
            if (grdLog == 0) {
                throw new BusinessException("会员经验值日志记录失败，请重试！");
            }
        }
        // 操作类型是积分值，且不为零的情况
        if (!integralValue.equals(0)) {
            // 记录送积分值的日志【会员经验积分日志表】
            memberGradeIntegralLogs.setType(ConstantsEJS.MEMBER_GRD_INT_LOG_T_2);
            memberGradeIntegralLogs.setValue(integralValue);
            Integer intLog = memberGradeIntegralLogsWriteDao.save(memberGradeIntegralLogs);
            if (intLog == 0) {
                throw new BusinessException("会员积分值日志记录失败，请重试！");
            }
        }
        // 更新会员的经验值和积分
        member.setGrade(grade);
        member.setGradeValue(gradeValue);
        member.setIntegral(integralValue);
        member.setUpdateTime(new Date());
        Integer update = memberWriteDao.updateValue(member);
        if (update == 0) {
            throw new BusinessException("更新会员经验值、积分值失败，请重试！");
        }

        return member;
    }

    /**
     * 修改密码提交
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param request
     * @return
     */
    public Member editPassword(String oldPwd, String newPwd, Member member) {
        Assert.notNull(memberWriteDao, "Property 'memberWriteDao' is required.");
        // 参数校验
        if (StringUtil.isEmpty(oldPwd)) {
            throw new BusinessException("旧密码不能为空，请重试！");
        } else if (StringUtil.isEmpty(newPwd)) {
            throw new BusinessException("新密码不能为空，请重试！");
        }

        Member memberDb = memberWriteDao.get(member.getId());
        if (memberDb == null) {
            throw new BusinessException("用户信息获取失败！");
        }

        String oldPwdMd5 = Md5.getMd5String(oldPwd);

        if (!oldPwdMd5.equals(memberDb.getPassword())) {
            throw new BusinessException("原密码不正确");
        }

        Member memberNew = new Member();
        memberNew.setId(member.getId());
        memberNew.setPassword(Md5.getMd5String(newPwd));

        //1、更新个人资料信息
        Integer count = memberWriteDao.update(memberNew);
        if (count == 0) {
            throw new BusinessException("修改密码失败，请重试！");
        }

        return member;
    }

    /**
     * 根据产品获得用户评价数 
     * @param request
     * @param pager
     * @return
     */
    public FrontMemberProductBehaveStatisticsVO getBehaveStatisticsByProductId(Integer productId,
                                                                               Member member) {
        FrontMemberProductBehaveStatisticsVO bean = new FrontMemberProductBehaveStatisticsVO();

        if (productId == null || productId == 0) {
            throw new BusinessException("产品id不能为空，请重试！");
        }

        //取得商品信息
        Product product = productReadDao.get(productId);

        if (product == null) {
            throw new BusinessException("产品不存在，请重试！");
        }

        Integer productAskCount = 0; //咨询数
        Integer productCommentsAllCount = 0; //评价总数
        Integer productCommentsHighCount = 0; //好评数
        Integer productCommentsMiddleCount = 0; //中评数
        Integer productCommentsLowCount = 0; //差评数

        BigDecimal productCommentsHighProportion = new BigDecimal(0); //好评占百分比 
        BigDecimal productCommentsMiddleProportion = new BigDecimal(0); //中评占百分比 
        BigDecimal productCommentsLowProportion = new BigDecimal(0); //差评占百分比 

        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("productId", productId);

        //获得咨询总数
        queryMap.put("state", ProductAsk.STATE_3);

        productAskCount = productAskReadDao.queryCount(queryMap);

        //好评  grade = 4或5 ,中评：3，差评：1或2
        queryMap.put("state", ProductComments.STATE_2);
        queryMap.put("grades", new String[] { "1", "2", "3", "4", "5" });
        productCommentsAllCount = productCommentsReadDao.queryCount(queryMap);

        if (productCommentsAllCount != 0) {
            //好评
            queryMap.put("grades", new String[] { "4", "5" });
            productCommentsHighCount = productCommentsReadDao.queryCount(queryMap);
            //好评占比
            productCommentsHighProportion = new BigDecimal(productCommentsHighCount)
                .divide(new BigDecimal(productCommentsAllCount), 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100));

            //中评
            queryMap.put("grades", new String[] { "3" });
            productCommentsMiddleCount = productCommentsReadDao.queryCount(queryMap);
            //中评占比
            productCommentsMiddleProportion = new BigDecimal(productCommentsMiddleCount)
                .divide(new BigDecimal(productCommentsAllCount), 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100));
            //差评
            queryMap.put("grades", new String[] { "1", "2" });
            productCommentsLowCount = productCommentsReadDao.queryCount(queryMap);
            //差评占比
            productCommentsLowProportion = new BigDecimal(productCommentsLowCount)
                .divide(new BigDecimal(productCommentsAllCount), 2, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(100));
        }

        //判断是否收藏过该商品
        boolean collectedProduct = false; //收藏过该商品
        boolean collectedShop = false; //收藏过该商铺

        if (member != null) {
            queryMap.clear();
            //取未删除的收藏商品
            queryMap.put("memberId", member.getId());
            queryMap.put("productId", productId);
            queryMap.put("state", ConstantsEJS.MEMBER_COLLECTION_PRODUCT_STATE_1);
            int cpcount = memberCollectionProductReadDao.queryCount(queryMap);
            if (cpcount > 0) {
                collectedProduct = true;
            }

            queryMap.clear();
            //取所有未删除的收藏店铺
            queryMap.put("memberId", member.getId());
            queryMap.put("sellerId", product.getSellerId());
            queryMap.put("state", ConstantsEJS.MEMBER_COLLECTION_PRODUCT_STATE_1);
            int cscount = memberCollectionSellerReadDao.queryCount(queryMap);
            if (cscount > 0) {
                collectedShop = true;
            }

        }

        //封装对象
        bean.setProductCommentsAllCount(productCommentsAllCount);
        bean.setProductCommentsHighCount(productCommentsHighCount);
        bean.setProductCommentsMiddleCount(productCommentsMiddleCount);
        bean.setProductCommentsLowCount(productCommentsLowCount);
        bean.setProductCommentsHighProportion(productCommentsHighProportion);
        bean.setProductCommentsMiddleProportion(productCommentsMiddleProportion);
        bean.setProductCommentsLowProportion(productCommentsLowProportion);
        bean.setProductAskCount(productAskCount);
        bean.setCollectedProduct(collectedProduct);
        bean.setCollectedShop(collectedShop);

        return bean;
    }

    /**
     * 判断支付密码是否正确
     * @param balancePwd
     * @param request
     * @return  返回错误次数
     */
    public FrontCheckPwdVO checkcheckBalancePwd(String balancePwd, Integer memberId) {

        FrontCheckPwdVO vo = new FrontCheckPwdVO();
        boolean correct = false;
        // 参数校验
        if (StringUtil.isEmpty(balancePwd)) {
            throw new BusinessException("支付密码不能为空，请重试！");
        }

        Member memberdb = memberWriteDao.get(memberId);
        if (StringUtil.isEmpty(memberdb.getBalancePwd())) {
            throw new BusinessException("未设置支付密码，请在余额菜单设置");
        }

        if (!memberdb.getBalancePwd().equals(Md5.getMd5String(balancePwd))) {
            //密码不正确，记录输错次数
            int errcount = memberdb.getPwdErrCount();
            memberdb.setPwdErrCount(errcount + 1);
            int count = memberWriteDao.update(memberdb);
            if (count == 0) {
                throw new BusinessException("验证密码时失败，请重试！");
            }
        } else {
            correct = true;
            // 输入正确后，错误次数清零
            memberdb.setPwdErrCount(0);
            int count = memberWriteDao.update(memberdb);
            if (count == 0) {
                throw new BusinessException("验证密码时失败，请重试！");
            }
        }
        vo.setCorrect(correct);
        vo.setPwdErrCount(memberdb.getPwdErrCount());

        return vo;
    }

    /**
     * 支付密码修改
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param request
     * @return
     */
    public Member editBalancePassword(String oldPwd, String newPwd, Integer memberId) {
        // 参数校验
        if (StringUtil.isEmpty(oldPwd)) {
            throw new BusinessException("旧支付密码不能为空，请重试！");
        } else if (StringUtil.isEmpty(newPwd)) {
            throw new BusinessException("新支付密码不能为空，请重试！");
        }

        //获得会员信息
        Member memberinfo = memberWriteDao.get(memberId);
        if (!memberinfo.getBalancePwd().equals(Md5.getMd5String(oldPwd))) {
            throw new BusinessException("原支付密码不正确");
        } else {
            memberinfo.setBalancePwd(Md5.getMd5String(newPwd));
            //1、更新个人资料信息
            Integer count = memberWriteDao.update(memberinfo);
            if (count == 0) {
                throw new BusinessException("修改支付密码失败，请重试！");
            }
        }

        return memberinfo;
    }

    /**
     * 设置支付密码
     * @param password 支付密码
     * @param request
     * @return
     */
    public Member addBalancePassword(String password, Member member) {

        // 参数校验
        if (StringUtil.isEmpty(password)) {
            throw new BusinessException("支付密码不能为空，请重试！");
        }

        //获得会员信息
        Member memberinfo = memberWriteDao.get(member.getId());
        if (!StringUtil.isEmpty(memberinfo.getBalancePwd())) {
            throw new BusinessException("已经设置过支付密码了");
        } else {
            memberinfo.setBalancePwd(Md5.getMd5String(password));
            //1、更新个人资料信息
            Integer count = memberWriteDao.update(memberinfo);
            if (count == 0) {
                throw new BusinessException("设置支付密码失败，请重试！");
            }
        }

        return memberinfo;
    }

}
