package spring.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.service.AccountService;
import spring.service.CategoriesService;
import spring.service.NewsService;
import spring.service.OrderService;
import spring.service.ProductService;
import spring.service.TypeService;


@Controller
public class AdminController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private TypeService typeService;
	@Autowired
	private ProductService productService;
	@Autowired 
	private OrderService orderService;
	@Autowired 
	private NewsService newsService;
	
	@RequestMapping("/admin/statistic")
	public String manage(Model model) {
		
		model.addAttribute("countAcc", accountService.getTotalAccounts());
		model.addAttribute("countCat", categoriesService.getTotalCat());
		model.addAttribute("countType", typeService.getTotalType());
		model.addAttribute("countPro", productService.getTotalProducts());
		model.addAttribute("countOrder", orderService.getTotalOrder());
		model.addAttribute("countNews", newsService.getTotalNews());
		return "admin/statistic";
	}
	
	
}
