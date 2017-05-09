package com.boonya.zookeeper.config;

import java.util.Map;
/**
 * 系统配置接口
 * 
 * @package com.boonya.zookeeper.config.SysConfig
 * @date   2017年5月9日  下午1:35:10
 * @author pengjunlin
 * @comment   
 * @update
 */
public interface SysConfig {
	
	/** 
     * 配置平台根节点名称 
     */  
    static String root = "/root";  
      
    /** 
     * 初始化配置 
     */  
    void init();  
      
    /** 
     * 重新加载配置资源 
     */  
    void reload();  
      
    /** 
     * 添加配置 
     * @param key 
     * @param value 
     */  
    void add(String key, String value);  
  
    /** 
     * 更新配置 
     * @param key 
     * @param value 
     */  
    void update(String key, String value);  
  
    /** 
     * 删除配置 
     * @param key 
     */  
    void delete(String key);  
  
    /** 
     * 获取配置 
     * @param key 
     * @return 
     */  
    String get(String key);  
  
    /** 
     * 获取所有的配置内容 
     * @return 
     */  
    Map<String, String> getAll();  

}
