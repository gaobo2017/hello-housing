package com.ejavashop.service.operate;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.operate.Config;

public interface IConfigService {

    /**
     * 根据id取得系统配置表
     * @param  configId
     * @return
     */
    ServiceResult<Config> getConfigById(Integer configId);

    /**
     * 保存系统配置表
     * @param  config
     * @return
     */
    ServiceResult<Integer> saveConfig(Config config);

    /**
    * 更新系统配置表
    * @param  config
    * @return
    */
    ServiceResult<Integer> updateConfig(Config config);
}