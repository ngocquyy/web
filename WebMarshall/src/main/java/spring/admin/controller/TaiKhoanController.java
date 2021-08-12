package spring.admin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.entity.Accounts;
import spring.service.AccountService;

@Controller
public class TaiKhoanController {
	@Autowired
	private AccountService accountService;
	
	
	@RequestMapping("/admin/listAccount")
	public String listAcc(Model model, Integer page) {
		int numOfItems = 9; //  dữ liệu trên 1 trang
		int offset;
		if (page == null) { // ngay từ đầu mình chưa click vào trang nào cả vậy nên cái page này nó sẽ null
			offset = 0;
		} else {
			offset = (page - 1) * numOfItems;
		}
		
		List<Accounts> listAccount = accountService.findAllAcc(offset, numOfItems);
		
		// xu ly hien thi so trang:
		Long totalAccounts = accountService.getTotalAccounts();
		int totalPages = (int) ((totalAccounts / numOfItems) + (totalAccounts % numOfItems == 0 ? 0 : 1));
		List<Integer> listPages = new ArrayList<>();
		for (int i = 1; i <= totalPages; i++) {
			listPages.add(i);
		}
		model.addAttribute("listPages", listPages);
		
		model.addAttribute("listAccount", listAccount);
		return "admin/listAccount"; 
	}
	
	@GetMapping("/admin/editAccount")
	public String preEditAccount(Model model, @RequestParam("userName")String userName) {
		//UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Accounts account = accountService.findByUserName(userName);
		model.addAttribute("account", account);
		return "admin/editAccount";
	}
	
	@PostMapping("/admin/editAccount")
	public String editAccount(@ModelAttribute("account")Accounts account , Model model) {
		
		accountService.update(account);
		return"redirect:/admin/listAccount";
	}
	
	@RequestMapping("/admin/deleteAccount")
	public String deleteAccount(Model model, @RequestParam("userName")String userName) {
		accountService.delete(userName);
		return "redirect:/admin/listAccount";
	}
}
