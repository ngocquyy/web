package spring.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.entity.OrderDetail;
import spring.entity.Orders;
import spring.service.OrderDetailService;
import spring.service.OrderService;

@Controller
public class DonHangController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	
	@RequestMapping("/admin/listOrder")
	public String listOrder(Model model, Integer page) {				//, Integer page
		int numOfItems = 9; //  dữ liệu trên 1 trang
		int offset;
		if (page == null) { // ngay từ đầu mình chưa click vào trang nào cả vậy nên cái page này nó sẽ null
			offset = 0;
		} else {
			offset = (page - 1) * numOfItems;
		}
		List<Orders> listOrder = orderService.findAllOrder(offset, numOfItems);

		// xu ly hien thi so trang:
		Long totalOrders = orderService.getTotalOrders();
		int totalPages = (int) ((totalOrders / numOfItems) + (totalOrders % numOfItems == 0 ? 0 : 1));
		List<Integer> listPages = new ArrayList<>();
		for (int i = 1; i <= totalPages; i++) {
			listPages.add(i);
		}
		model.addAttribute("listPages", listPages);
		
		model.addAttribute("listOrder", listOrder);
		return "admin/listOrder";
	}
	
	@RequestMapping("/admin/listOrderDetail/{orderId}")
	public String detail(@PathVariable("orderId")Integer orderId, Model model) {
		Orders order = orderService.findByOrderId(orderId);
		model.addAttribute("order", order);
		
		List<OrderDetail> or = orderDetailService.findByOrder(order);
		model.addAttribute("listDetail", or);
		
		return"admin/listOrderDetail";
	}
	// kiem cai font chuw giosng no aasy
	@RequestMapping("/admin/deleteOrder")
	public String deletePro(Model model, @RequestParam("orderId")Integer orderId) {
		orderService.delete(orderId);
		return "redirect:/admin/listOrder";
	}
	
	
	

	
	
}
