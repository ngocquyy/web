package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.ProductDAO;
import spring.entity.Products;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<Products> findAllPro(Integer offset, Integer maxResult) {
		// TODO Auto-generated method stub
		return productDAO.findAllPro(offset, maxResult);
	}

	@Override
	public Long getTotalProducts() {
		// TODO Auto-generated method stub
		return productDAO.getTotalProducts();
	}

	@Override
	public Products findByProId(String proId) {
		// TODO Auto-generated method stub
		return productDAO.findByProId(proId);
	}

	@Override
	public Products createPro(Products pro) {
		// TODO Auto-generated method stub
		return productDAO.createPro(pro);
	}

	@Override
	public void update(Products pro) {
		// TODO Auto-generated method stub
		productDAO.update(pro);
	}

	@Override
	public Products delete(String proId) {
		// TODO Auto-generated method stub
		return productDAO.delete(proId);
	}

	@Override
	public List<Products> findByType(Integer typeId) {
		// TODO Auto-generated method stub
		return productDAO.findByType(typeId);
	}

	@Override
	public List<Products> getNewArrivals() {
		// TODO Auto-generated method stub
		return productDAO.getNewArrivals();
	}

}
