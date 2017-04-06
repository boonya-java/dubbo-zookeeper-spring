package dubbo.web.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 产品实体类
 * @package dobbu.web.api.entity.Product
 * @date   2017年4月6日  上午10:00:59
 * @author pengjunlin
 * @comment   
 * @update
 */
public class Product implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6566798952389500741L;

	private String id;
	
	private String name;
	
	private String categroy;
	
	private BigDecimal price;
	
	private BigDecimal discount;
	
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategroy() {
		return categroy;
	}

	public void setCategroy(String categroy) {
		this.categroy = categroy;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
