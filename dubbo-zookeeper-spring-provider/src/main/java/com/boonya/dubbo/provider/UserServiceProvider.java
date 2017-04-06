package com.boonya.dubbo.provider;

import java.util.List;
import org.springframework.stereotype.Component;
import dubbo.web.api.entity.User;
import dubbo.web.api.service.UserService;
/**
 * 提供者的用户接口实现
 * 
 * @package com.boonya.dubbo.provider.UserServiceProvider
 * @date   2017年4月6日  上午10:33:14
 * @author pengjunlin
 * @comment   
 * @update
 */
@Component
public class UserServiceProvider implements UserService{
	

	@Override
	public Integer insert(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean login(User user) {
		if(user.getUsername().equals("boonya")){
			if("boonya".equals(user.getPassword())){
				System.out.println(">>>Login success!");
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean logout(User user) {
		System.out.println(">>>Logout success!");
		return false;
	}

}
