package dubbo.web.api.entity;

import java.io.Serializable;
import java.util.UUID;
/**
 * 系统用户实体对象
 * 
 * @package dobbu.web.api.entity.User
 * @date   2017年4月6日  上午9:58:34
 * @author pengjunlin
 * @comment   
 * @update
 */
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3141983825631531986L;

	private String id;
	
	private String username;
	
	private String password;
	
	private String email;

	public String getId() {
		if(id==null||"".equals(id)){
			id=UUID.randomUUID().toString();
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
