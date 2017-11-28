package com.ejavashop.model.operate;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.operate.ConfigReadDao;
import com.ejavashop.dao.shop.write.operate.ConfigWriteDao;
import com.ejavashop.entity.operate.Config;

@Component
public class ConfigModel {

    @Resource
    private ConfigReadDao  configReadDao;
    @Resource
    private ConfigWriteDao configWriteDao;

    /**
     * 根据id取得系统配置表
     * @param  configId
     * @return
     */
    public Config getConfigById(Integer configId) {
        return configReadDao.get(configId);
    }

    /**
     * 保存系统配置表
     * @param  config
     * @return
     */
    public Integer saveConfig(Config config) {
        return configWriteDao.insert(config);
    }

    /**
    * 更新系统配置表
    * @param  config
    * @return
    */
    public Integer updateConfig(Config config) {
        return configWriteDao.update(config);
    }

}