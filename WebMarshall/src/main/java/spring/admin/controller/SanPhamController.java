package spring.admin.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import spring.entity.Products;
import spring.service.ProductService;

@Controller
public class SanPhamController {
	@Autowired
	private ProductService productService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sf, true));
	}


	@RequestMapping("/admin/listPro")
	public String listPro(Integer page, Model model) {
		
		int numOfItems = 9; //  dữ liệu trên 1 trang
		int offset;
		if (page == null) { // ngay từ đầu mình chưa click vào trang nào cả vậy nên cái page này nó sẽ null
			offset = 0;
		} else {
			offset = (page - 1) * numOfItems;
		}
		List<Products> listPro = productService.findAllPro(offset, numOfItems);

		// xu ly hien thi so trang:
		Long totalProducts = productService.getTotalProducts();
		int totalPages = (int) ((totalProducts / numOfItems) + (totalProducts % numOfItems == 0 ? 0 : 1));
		List<Integer> listPages = new ArrayList<>();
		for (int i = 1; i <= totalPages; i++) {
			listPages.add(i);
		}
		model.addAttribute("listPages", listPages);
		
		
		model.addAttribute("listPro", listPro);
		return "admin/listPro";
	}

	@GetMapping("/admin/addPro")
	public String addPro(Model model) {
		Products pro = new Products();
		model.addAttribute("pro", pro);
		return "/admin/addPro";
	}

	@PostMapping("/admin/addPro")
	public String addPro(@Validated @Valid Model model, @ModelAttribute("pro") Products pro,
			MultipartFile imgPro, HttpServletRequest request, BindingResult result) throws IllegalStateException, IOException {
		
		if (result.hasErrors()) {
			model.addAttribute("notification", "Có lỗi xảy ra. Vui lòng kiểm tra lại !!");
			return "/admin/addPro";
		}else {
			Products pro2 = productService.findByProId(pro.getProId());
			if (pro2 != null) {
				model.addAttribute("notification", "Sản phẩm đã tồn tại !!");
				return "/admin/addPro";
			} 
		}
		
		
		String path = request.getServletContext().getRealPath("static/images/product");
		File f = new File(path);
		File dest = new File(f.getAbsoluteFile()+"/"+imgPro.getOriginalFilename());
		try {
			byte[] dataImg = imgPro.getBytes();
			if (!dest.exists()) {
				Files.write(dest.toPath(), dataImg, StandardOpenOption.CREATE);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pro.setImage(imgPro.getOriginalFilename());
		productService.createPro(pro);
		return "redirect:/admin/listPro";
	}

	@GetMapping("/admin/editPro")
	public String editPro(Model model, @ModelAttribute("proId") String proId) {
		Products pro = productService.findByProId(proId);
		model.addAttribute("pro", pro);
		return "admin/editPro";
	}

	@PostMapping("/admin/editPro")
	public String updateProduct(Model model, @ModelAttribute("pro") Products pro,
			 MultipartFile imgPro, HttpServletRequest request) throws IllegalStateException, IOException {
		String path = request.getServletContext().getRealPath("static/images/product");
		File f = new File(path);
		File dest = new File(f.getAbsoluteFile()+"/"+imgPro.getOriginalFilename());
		try {
			byte[] dataImg = imgPro.getBytes();
			if (!dest.exists()) {
				Files.write(dest.toPath(), dataImg, StandardOpenOption.CREATE);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pro.setImage(imgPro.getOriginalFilename());
		productService.update(pro);
		return "redirect:/admin/listPro";
	}

	@RequestMapping("/admin/deletePro")
	public String deletePro(Model model, @RequestParam("proId") String proId) {
		productService.delete(proId);
		return "redirect:/admin/listPro";
	}
	
	@RequestMapping("/admin/group-by-type/{typeId}")
	public String listProductByType(@PathVariable("typeId")Integer typeId, Model model) {
		List<Products> listTypePro = productService.findByType(typeId);
		model.addAttribute("listTypePro", listTypePro);
		return "admin/proByType";
	}
}
