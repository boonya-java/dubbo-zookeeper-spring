package dubbo.web.api.service;

import java.util.List;
/**
 * 基础接口定义
 * 
 * @package dobbu.web.api.service.BaseService
 * @date   2017年4月6日  上午10:18:25
 * @author pengjunlin
 * @comment   
 * @update
 */
public interface BaseService<T> {
	
	public Integer insert(T t);
	
	public Integer update(T t);
	
	public Integer delete(String id);
	
	public List<T> getAll();

}
