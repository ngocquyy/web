package spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.service.CartService;

@Controller
public class ShoppingCartController {
	@Autowired
	private CartService cartService;
	
	
	@RequestMapping("/cart/shoppingCart")
	public String listCart(Model model) {
		model.addAttribute("listCart", cartService.getItem());
		model.addAttribute("amount", cartService.getAmount());
		model.addAttribute("count", cartService.getCount());
		return"cart/shoppingCart";
	}
	
	@RequestMapping("/cart/add/{proId}")
	public String add(@PathVariable("proId")String proId) {
		cartService.add(proId);
		return "redirect:/cart/shoppingCart";
	}
	
	@RequestMapping("/cart/remove/{proId}")
	public String remove(@PathVariable("proId")String proId) {
		cartService.remove(proId);
		return"redirect:/cart/shoppingCart";
	}
	
	@RequestMapping("/cart/update")
	public String update(@RequestParam("proId")String proId, @RequestParam("quantity")Integer quantity) {
		cartService.update(proId, quantity);
		return "redirect:/cart/shoppingCart";
	}
	
	@RequestMapping("/cart/clear")
	public String clear() {
		cartService.clear();
		return "redirect:/cart/shoppingCart";
	}
}
