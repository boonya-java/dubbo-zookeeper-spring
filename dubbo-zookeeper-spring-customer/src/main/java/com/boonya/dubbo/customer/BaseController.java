package com.boonya.dubbo.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;

}
