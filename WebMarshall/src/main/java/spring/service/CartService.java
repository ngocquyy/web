package spring.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import spring.dao.ProductDAO;
import spring.entity.Products;

@Service
@SessionScope //scopdTarget.cartService
public class CartService {
	@Autowired
	private ProductDAO productDAO;
	
	Map<String, Products> map = new HashMap<>();
	
	public void  add(String proId) {
		Products pro = map.get(proId);
		if (pro == null) {
			pro = productDAO.findByProId(proId);
			pro.setQuantity(1);
			map.put(proId, pro);
		} else {
			pro.setQuantity(pro.getQuantity() + 1);
		}
	}
	public void remove(String proId) {
		map.remove(proId);
	}
	public void update(String proId, int quantity) {
		Products p = map.get(proId);
		p.setQuantity(quantity);
		if (quantity <= 0) {
			map.remove(proId);
		}
	}
	public void clear() {
		map.clear();
	}
	public int getCount() {		//lay tong so luowjng mat hang
		Collection<Products> pro = this.getItem();
		int count = 0;
		for (Products p : pro) {
			count += p.getQuantity();
		}
		return count;
	}
	public double getAmount() { // lay tien gio hang
		Collection<Products> pro = this.getItem();
		double amount = 0;
		for (Products p : pro) {
			amount += p.getQuantity() * p.getPrice() * (1 - p.getDiscount());
		}
		return amount;
	}
	
	public Collection<Products> getItem(){ //lay tat ca mat hang trong gio------ List=collection==set
		return map.values();
	}
	
}
