package spring.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.entity.News;
import spring.entity.Products;
import spring.service.ContactService;
import spring.service.NewsService;
import spring.service.ProductService;


@Controller
@Transactional
public class MarshallController {
	@Autowired
	private ContactService contactService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private ProductService productService;
	
    @RequestMapping(value = {"/","/index"})
    public String home(Model model) {
    	
    	
		List<Products> newArrivals = productService.getNewArrivals();
    	model.addAttribute("newArrivals", newArrivals);
    	
        return "index";
    }
    
   
  
//    
    
    //Trang liên hệ
    @RequestMapping("/contact")
    public String contact(Model model) {
    	model.addAttribute("listContact", contactService.getListContact());
    	return "contact";
    }
    
    // Trang tin tức
    @RequestMapping("/news")
    public String news(Model model, Integer page) {
    	int numOfItems = 9; //  dữ liệu trên 1 trang
		int offset;
		if (page == null) { // ngay từ đầu mình chưa click vào trang nào cả vậy nên cái page này nó sẽ null
			offset = 0;
		} else {
			offset = (page - 1) * numOfItems;
		}
		List<News> listNews = newsService.getListNews(offset, numOfItems);

		// xu ly hien thi so trang:
		Long totalNews = newsService.getTotalNews();
		int totalPages = (int) ((totalNews / numOfItems) + (totalNews % numOfItems == 0 ? 0 : 1));
		List<Integer> listPages = new ArrayList<>();
		for (int i = 1; i <= totalPages; i++) {
			listPages.add(i);
		}
		model.addAttribute("listPages", listPages);
    	
    	model.addAttribute("listNews", listNews);
    	return "news";
    }
    
   
 
   
 
   
 
    
 
    
 
   
 
   
    
    
}
