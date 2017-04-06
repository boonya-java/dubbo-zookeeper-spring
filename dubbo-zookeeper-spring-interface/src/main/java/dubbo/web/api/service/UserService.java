package dubbo.web.api.service;

import dubbo.web.api.entity.User;
/**
 * 用户接口定义
 * 
 * @package dobbu.web.api.service.UserService
 * @date   2017年4月6日  上午10:20:06
 * @author pengjunlin
 * @comment   
 * @update
 */
public interface UserService extends BaseService<User>{
	
	/**
	 * 用户登录
	 * 
	 * @MethodName: login 
	 * @Description: 
	 * @param user
	 * @return
	 * @throws
	 */
	public boolean login(User user);
	
	/**
	 * 用户退出
	 * 
	 * @MethodName: logout 
	 * @Description: 
	 * @param user
	 * @return
	 * @throws
	 */
	public boolean logout(User user);

}
