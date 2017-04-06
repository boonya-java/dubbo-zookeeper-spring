package com.boonya.dubbo.provider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Component;

import dubbo.web.api.entity.Product;
import dubbo.web.api.service.ProductService;
/**
 * 提供者的商品接口实现
 * 
 * @package com.boonya.dubbo.provider.ProductServiceProvider
 * @date   2017年4月6日  上午11:01:39
 * @author pengjunlin
 * @comment   
 * @update
 */
@Component
public class ProductServiceProvider implements ProductService {

	@Override
	public Integer insert(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Product t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAll() {
		List<Product> products=new ArrayList<Product>();
		Product product=null;
		for (int i = 0; i < 20; i++) {
			product=new Product();
			product.setId(UUID.randomUUID().toString());
			product.setName("PRO-ITEM-2017-"+i);
			product.setPrice(new BigDecimal(new Random().nextDouble()));
			product.setCategroy("DRUG");
			product.setDiscount(new BigDecimal(7.5));
			product.setAddress("Chengdu,Sichuan,China");
			products.add(product);
		}
		return products;
	}



	
	

	
}