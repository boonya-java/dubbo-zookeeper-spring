package com.boonya.zookeeper.config;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysConfigTest {
	
  private final static Logger logger = LoggerFactory
			.getLogger(SysConfigTest.class);
	
  @Test
   public void testConfig(){
	   SysConfig root = new SysConfigImpl("192.168.200.122:2181");
       root.add("testKey1", "1");  
       root.add("testKey2", "2");  
       root.add("testKey3", "3");  
       root.add("testKey4", "4");  
       root.add("testKey5", "5");  
       root.add("testKey6", "6");  
       logger.info("value is===>"+root.get("testKey1"));  
       logger.info("value is===>"+root.get("testKey2"));  
       logger.info("value is===>"+root.get("testKey3"));  
       logger.info("value is===>"+root.get("testKey4"));  
       logger.info("value is===>"+root.get("testKey5"));  
       logger.info("value is===>"+root.get("testKey6"));  
       root.update("testKey6", "testKey6");  
       logger.info("update testKey6 value is===>"+root.get("testKey6"));  
       root.delete("testKey1");  
       root.delete("testKey2");  
       root.delete("testKey3");  
       root.delete("testKey4");  
       root.delete("testKey5");  
       root.delete("testKey6");  
   }

}
