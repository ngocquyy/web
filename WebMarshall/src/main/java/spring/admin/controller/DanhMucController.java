package spring.admin.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.entity.Categories;
import spring.service.CategoriesService;

@Controller
public class DanhMucController {
	@Autowired
	private CategoriesService categoriesService;
	
	@RequestMapping("/admin/listCat")
	public String listCat(Model model) {
		model.addAttribute("listCat", categoriesService.getListCat());
		return "admin/listCat";
	}
	
	
	@GetMapping("/admin/addCat")
	public String addCat(Model model) {
		Categories cate = new Categories();
		model.addAttribute("cate", cate);
		return "/admin/addCat";
	}
	@PostMapping("/admin/addCat")
	public String addCat(@Validated @Valid @ModelAttribute("cate")Categories cate, Model model, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("notification", "Có lỗi xảy ra. Vui lòng kiểm tra lại !!");
			return "/admin/addCat";
		}else {
			Categories cate2 = categoriesService.findById(cate.getCatId());
			if (cate2 != null) {
				model.addAttribute("notification", "Danh mục đã tồn tại !!");
				return "/admin/addCat";
			} 
		}
		categoriesService.create(cate);
		return "redirect:/admin/listCat";
	}
	
	@GetMapping("/admin/editCat")
	public String preEditCat(Model model, @RequestParam("catId")Integer catId) {
		Categories cate = categoriesService.findById(catId);
		model.addAttribute("cate", cate);
		return "admin/editCat";
	}
	
	@PostMapping("/admin/editCat")
	public String editCat(@ModelAttribute("cate")Categories cate , Model model) {
		categoriesService.update(cate);
		return"redirect:/admin/listCat";
	}
	
	@RequestMapping("/admin/deleteCat")
	public String deleteCat(@RequestParam("catId")Integer catId, Model model) {
		categoriesService.delete(catId);
		return"redirect:/admin/listCat";
	}
}
