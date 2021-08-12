package spring.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.entity.Accounts;
import spring.entity.Orders;
import spring.entity.OrderDetail;
import spring.entity.Products;
import spring.service.AccountService;
import spring.service.CartService;
import spring.service.OrderDetailService;
import spring.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private CartService cartService;
	@Autowired
	private AccountService accountService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sf, true));
	}
	
	
	@GetMapping("/order/checkout")
	public String form(@ModelAttribute("order")Orders order,Model model) {
		model.addAttribute("listCart", cartService.getItem());
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Accounts user = accountService.findByUserName(userDetails.getUsername());
		order.setAccount(user);
		order.setOrderDate(new Date());
		order.setAmount(cartService.getAmount());
		
		return "order/checkout";
	}
	@PostMapping("/order/checkout")
	public String purchase(@ModelAttribute("order")Orders order, Model model) {
		
		Collection<Products> list = cartService.getItem();
		List<OrderDetail> details = new ArrayList<>();
		
		for (Products product : list) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setProduct(product);
			detail.setPrice(product.getPrice());
			detail.setDiscount(product.getDiscount());
			detail.setQuanity(product.getQuantity());
			details.add(detail);
		}
		
		orderService.create(order, details);
		
		cartService.clear();
		return "redirect:/order/orderSuccess";  //redirect" list"
	}
	
	@RequestMapping("/order/orderSuccess")
	public String success(Model model) {
		return "order/orderSuccess";
	}
	
	@GetMapping("/order/listOrder")
	public String list(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Accounts user = accountService.findByUserName(userDetails.getUsername());
		
		List<Orders> order = orderService.findByUser(user);
		model.addAttribute("orderList", order);
		return "order/listOrder";
	}
	
	@GetMapping("/order/listOrderDetail/{orderId}")
	public String detail(Model model, @PathVariable("orderId")Integer orderId) {
		Orders order = orderService.findByOrderId(orderId);
		model.addAttribute("order", order);
		
		List<OrderDetail> details = orderDetailService.findByOrder(order);
		model.addAttribute("details", details);
		
		return "order/listOrderDetail";
	}
	
	@GetMapping("/order/listBought") //sp da mua
	public String bought(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Accounts user = accountService.findByUserName(userDetails.getUsername());
		List<Products> listProducts = orderService.findBoughtByUser(user);   // co hang theo user -- sai phan DAO impl
		List<Products> listBought = new ArrayList<Products>(); 	//tao danh sach hang da mua	moi listBought
		for (Products products : listProducts) {			// hibernate query ko nhan distinct nen dung vong lap	
			if(!listBought.contains(products)) 	 			//neu danh sasch hhag da mua chua co thi..
				listBought.add(products);					// ..them product vao danh sasch
		}
		model.addAttribute("listBought", listBought); 
		return"order/listBought";
	}
}
