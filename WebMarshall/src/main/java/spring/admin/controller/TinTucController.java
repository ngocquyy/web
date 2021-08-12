package spring.admin.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.multipart.MultipartFile;

import spring.entity.News;
import spring.service.NewsService;

@Controller
public class TinTucController {

	@Autowired
	private NewsService newsService;
	
	
	
	@RequestMapping("/admin/listNews")
	public String listNews(Integer page, Model model) {
		
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
		return "admin/listNews";
	}

	@GetMapping("/admin/addNews")
	public String addNews(Model model) {
		News news = new News();
		model.addAttribute("news", news);
		return "/admin/addNews";
	}

	@PostMapping("/admin/addNews")
	public String addNews(@Validated @Valid Model model, @ModelAttribute("news") News news,
			MultipartFile imgNews, HttpServletRequest request, BindingResult result) throws IllegalStateException, IOException {
		if (result.hasErrors()) {
			model.addAttribute("notification", "Có lỗi xảy ra. Vui lòng kiểm tra lại !!");
			return "/admin/addNews";
		}else {
			News news2 = newsService.getNewsById(news.getNewsId());
			if (news2 != null) {
				model.addAttribute("notification", "Tin tức đã tồn tại !!");
				return "/admin/addNews";
			} 
		}
		String path = request.getServletContext().getRealPath("static/images");
		File f = new File(path);
		File dest = new File(f.getAbsoluteFile()+"/"+imgNews.getOriginalFilename());
		try {
			byte[] dataImg = imgNews.getBytes();
			if (!dest.exists()) {
				Files.write(dest.toPath(), dataImg, StandardOpenOption.CREATE);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		news.setImages(imgNews.getOriginalFilename());
		newsService.insertNews(news);
		return "redirect:/admin/listNews";
	}

	@GetMapping("/admin/editNews")
	public String editNews(Model model, @ModelAttribute("newsId") Integer newsId) {
		News news = newsService.getNewsById(newsId);
		model.addAttribute("news", news);
		return "admin/editNews";
	}

	@PostMapping("/admin/editNews")
	public String updateNews(Model model, @ModelAttribute("news") News news,
			MultipartFile imgNews, HttpServletRequest request) throws IllegalStateException, IOException {
		String path = request.getServletContext().getRealPath("static/images");
		File f = new File(path);
		File dest = new File(f.getAbsoluteFile()+"/"+imgNews.getOriginalFilename());
		try {
			byte[] dataImg = imgNews.getBytes();
			if (!dest.exists()) {
				Files.write(dest.toPath(), dataImg, StandardOpenOption.CREATE);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		news.setImages(imgNews.getOriginalFilename());
		newsService.updateNews(news);
		return "redirect:/admin/listNews";
	}

	@RequestMapping("/admin/deleteNews")
	public String deletenews(Model model, @RequestParam("newsId") Integer newsId) {
		newsService.deleteNews(newsId);
		return "redirect:/admin/listNews";
	}
}
