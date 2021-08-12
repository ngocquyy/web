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

import spring.entity.ProductType;
import spring.service.TypeService;

@Controller
public class HangMucController {
	@Autowired
	private TypeService typeService;
	
	@RequestMapping("/admin/listType")
	public String listType(Model model) {
		model.addAttribute("listType", typeService.getListType());
		return "admin/listType";
	}
	
	@GetMapping("/admin/addType")
	public String addType(Model model) {
		ProductType type = new ProductType();
		model.addAttribute("type", type);
		return "/admin/addType";
	}
	@PostMapping("/admin/addType")
	public String addType(@Validated @Valid @ModelAttribute("type")ProductType type, Model model, BindingResult result) {
		
		if (result.hasErrors()) {
			model.addAttribute("notification", "Có lỗi xảy ra. Vui lòng kiểm tra lại !!");
			return "/admin/addType";
		}else {
			ProductType type2 = typeService.findTypeById(type.getTypeId());
			if (type2 != null) {
				model.addAttribute("notification", "Hạng mục đã tồn tại !!");
				return "/admin/addType";
			} 
		}
		
		typeService.createType(type);
		return "redirect:/admin/listType";
	}
	
	@GetMapping("/admin/editType")
	public String preeditType(Model model, @RequestParam("typeId")Integer typeId) {
		ProductType type = typeService.findTypeById(typeId);
		model.addAttribute("type", type);
		return "admin/editType";
	}
	
	@PostMapping("/admin/editType")
	public String editType(@ModelAttribute("type")ProductType type , Model model) {
		typeService.updateType(type);
		return"redirect:/admin/listType";
	}
	
	@RequestMapping("/admin/deleteType")
	public String deleteType(@RequestParam("typeId")Integer typeId, Model model) {
		typeService.deleteType(typeId);
		return"redirect:/admin/listType";
	}
}
