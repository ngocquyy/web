package spring.service;

import java.util.List;

import spring.entity.Products;

public interface ProductService {
	public List<Products> findAllPro(Integer offset, Integer maxResult);
	public Long getTotalProducts();
	public Products findByProId(String proId);
	public Products createPro(Products pro);
	public void update(Products pro);
	public Products delete(String proId);
	public List<Products> findByType(Integer typeId);
	public List<Products> getNewArrivals();
}
