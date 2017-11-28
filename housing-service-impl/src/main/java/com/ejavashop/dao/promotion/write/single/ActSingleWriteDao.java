package com.ejavashop.dao.promotion.write.single;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.single.ActSingle;

@Repository
public interface ActSingleWriteDao {

    ActSingle get(java.lang.Integer id);

    Integer insert(ActSingle actSingle);

    Integer update(ActSingle actSingle);

    /**
     * 只修改活动状态、审核意见、修改者信息
     * @param actFull
     * @return
     */
    Integer updateStatus(ActSingle actSingle);

    Integer delete(Integer id);
}