package com.ejavashop.model.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.ArgumentException;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.write.system.CodeWriteDao;
import com.ejavashop.entity.system.Code;

@Component(value = "codeModel")
public class CodeModel {

    @Resource
    private CodeWriteDao                 codeDao;

    @Resource
    private DataSourceTransactionManager transactionManager;

    public List<Code> getCodes(Map<String, String> queryMap, PagerInfo pager) {
        Assert.notNull(codeDao, "Property 'codeDao' is required.");

        Integer start = 0, size = 0;

        if (pager != null) {
            pager.setRowsCount(codeDao.getCodesCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        return codeDao.getCodes(queryMap, start, size);
    }

    public Code getCode(String codeDiv, String codeCd) {
        Assert.notNull(codeDao, "Property 'codeDao' is required.");
        if (StringUtil.isEmpty(codeDiv) || StringUtil.isEmpty(codeCd))
            throw new ArgumentException("查询条件不完整，无法获取字典信息。");
        return codeDao.getCode(codeDiv, codeCd);
    }

    public Integer createCode(Code code) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //如果当前存在事务，则加入改事务；如果当前没有事务，则创建一个新得事务
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        Integer result = 0;
        try {
            Assert.notNull(codeDao, "Property 'codeDao' is required.");
            Code dbCode = codeDao.getCode(code.getCodeDiv(), code.getCodeCd());
            if (dbCode != null)
                throw new BusinessException("该字典索引已存在。");
            this.dbConstrains(code);
            result = codeDao.createCode(code);
            transactionManager.commit(status);
        } catch (BusinessException be) {
            transactionManager.rollback(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
        }
        return result;
    }

    public Boolean updateCode(Code code) {
        Assert.notNull(codeDao, "Property 'codeDao' is required.");
        this.dbConstrains(code);
        return codeDao.updateCode(code) > 0;
    }

    private void dbConstrains(Code code) {
        code.setCodeDiv(StringUtil.dbSafeString(code.getCodeDiv(), false, 20));
        code.setCodeCd(StringUtil.dbSafeString(code.getCodeCd(), false, 20));
        code.setCodeText(StringUtil.dbSafeString(code.getCodeText(), false, 100));
        code.setCreateUser(StringUtil.dbSafeString(code.getCreateUser(), false, 40));
        code.setUpdateUser(StringUtil.dbSafeString(code.getUpdateUser(), false, 40));
    }

}
