package spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.entity.Categories;
import spring.entity.ProductType;
import spring.entity.Products;
import spring.service.CategoriesService;
import spring.service.TypeService;

@Controller
public class TypeController {
	@Autowired
	private TypeService typeService;
	@Autowired
	private CategoriesService categoriesService;
	
	@RequestMapping("list-by-categories/{catId}")
	public String listByCat(@PathVariable("catId")Integer catId,   Model model) {
		List<ProductType> listType = typeService.findTypeByCatId(catId);
		model.addAttribute("listType", listType);
		return "list";
	}
	
	@GetMapping("/product/proByCat")
	public String proByCat(Model model, @RequestParam("catId")Integer catId) {
		Categories cate = categoriesService.findById(catId);
		List<Products> list = typeService.findProByCat(cate);
		model.addAttribute("proByCat", list);
		return"product/proByCat";
	}
}
