package com.boonya.zookeeper.config;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 测试系统配置服务
 * 
 * @package com.boonya.zookeeper.config.SysConfigTest
 * @date   2017年5月9日  下午5:20:28
 * @author pengjunlin
 * @comment   
 * @update
 */
public class SysConfigTest {
	
  private final static Logger logger = LoggerFactory
			.getLogger(SysConfigTest.class);
	
  @Test
   public void testConfig(){
	   SysConfig root = new SysConfigImpl("192.168.200.122:2181");
       root.add("api_service_property1", "1");  
       root.add("api_service_property2", "2");  
       root.add("api_service_property3", "3");  
       root.add("api_service_property4", "4");  
       root.add("api_service_property5", "5");  
       root.add("api_service_property6", "6");  
       logger.info("value is===>"+root.get("api_service_property1"));  
       logger.info("value is===>"+root.get("api_service_property2"));  
       logger.info("value is===>"+root.get("api_service_property3"));  
       logger.info("value is===>"+root.get("api_service_property4"));  
       logger.info("value is===>"+root.get("api_service_property5"));  
       logger.info("value is===>"+root.get("api_service_property6"));  
       root.update("api_service_property6", "api_service_property6");  
       logger.info("update api_service_property6 value is===>"+root.get("api_service_property6"));  
       root.delete("api_service_property1");  
       root.delete("api_service_property2");  
       root.delete("api_service_property3");  
       root.delete("api_service_property4");  
       root.delete("api_service_property5");  
       root.delete("api_service_property6");  
   }

}
