package spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.entity.Products;
import spring.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@RequestMapping("list-by-type/{typeId}")
	public String listProductByType(@PathVariable("typeId")Integer typeId, Model model) {
		List<Products> listPro = productService.findByType(typeId);
		model.addAttribute("listPro", listPro);
		return "product/listProd";
	}
	
	@RequestMapping("/product/productDetail/{proId}")
	public String productDetail(@PathVariable("proId")String proId, Model model) {
		Products detailPro = (Products) productService.findByProId(proId);
		model.addAttribute("detailPro", detailPro);
		
		List<Products> similar = productService.findByType(detailPro.getProductType().getTypeId()); // sp tuong tu
		model.addAttribute("similar", similar);
		return "product/productDetail";
	}
	
	@RequestMapping("/product/allProduct")
	public String allProduct(Integer page, Model model) {
		int numOfItems = 12; //  dữ liệu trên 1 trang
		int offset;
		if (page == null) { // ngay từ đầu mình chưa click vào trang nào cả vậy nên cái page này nó sẽ null
			offset = 0;
		} else {
			offset = (page - 1) * numOfItems;
		}
		List<Products> allPro = productService.findAllPro(offset, numOfItems);

		// xu ly hien thi so trang:
		Long totalProducts = productService.getTotalProducts();
		int totalPages = (int) ((totalProducts / numOfItems) + (totalProducts % numOfItems == 0 ? 0 : 1));
		List<Integer> listPages = new ArrayList<>();
		for (int i = 1; i <= totalPages; i++) {
			listPages.add(i);
		}
		model.addAttribute("listPages", listPages);
		
		
		model.addAttribute("allPro", allPro);
		return "product/allProduct";
	}
}
