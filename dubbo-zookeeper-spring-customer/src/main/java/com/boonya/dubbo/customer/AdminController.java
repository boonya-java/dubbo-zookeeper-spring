package com.boonya.dubbo.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dubbo.web.api.entity.Product;
import dubbo.web.api.entity.User;
import dubbo.web.api.service.ProductService;
import dubbo.web.api.service.UserService;

@Controller
public class AdminController {

	private final String SESSION_USER = "SESSION_USER";

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;

	@RequestMapping("/login")
	public String login(HttpServletRequest request,
			HttpServletResponse response, User user, Model model) {
		boolean flag = userService.login(user);
		if (flag) {
			request.setAttribute(SESSION_USER, user);
			List<Product> goodList = productService.getAll();
			model.addAttribute("products", goodList);
			return "/productList.jsp";
		}
		model.addAttribute("error", "登录失败！");
		return "/index.jsp";
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		request.removeAttribute(SESSION_USER);
		model.addAttribute("error", "退出登录成功！");
		return "/index.jsp";
	}

}
