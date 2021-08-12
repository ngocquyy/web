package spring.intercreptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import spring.entity.Categories;
import spring.entity.Contact;
import spring.entity.ProductType;
import spring.service.CartService;
import spring.service.CategoriesService;
import spring.service.ContactService;
import spring.service.TypeService;

@Component
public class ShareIntercreptor extends HandlerInterceptorAdapter{
	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private TypeService typeService;
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		List<Categories> listCat = categoriesService.getListCat();
		request.setAttribute("listCat", listCat);
		
		//so luong hang trong gio
		request.setAttribute("count", cartService.getCount());
		request.setAttribute("amount", cartService.getAmount());
		
		List<Contact> listContact = contactService.getListContact();
		request.setAttribute("listContact", listContact);
		
		List<ProductType> listType = typeService.getListType();
		request.setAttribute("listType", listType);
		
	}

}
// --------------------quy--------------------------//