package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.CategoriesDAO;
import spring.entity.Categories;

@Service
public class CategoriesServiceImpl implements CategoriesService{
	@Autowired
	private CategoriesDAO categoriesDAO;

	@Override
	public List<Categories> getListCat() {
		// TODO Auto-generated method stub
		return categoriesDAO.getListCat();
	}

	@Override
	public Categories findById(Integer catId) {
		// TODO Auto-generated method stub
		return categoriesDAO.findById(catId);
	}

	@Override
	public Categories create(Categories cate) {
		// TODO Auto-generated method stub
		return categoriesDAO.create(cate);
	}

	@Override
	public void update(Categories cate) {
		// TODO Auto-generated method stub
		categoriesDAO.update(cate);
	}

	@Override
	public Categories delete(Integer catId) {
		// TODO Auto-generated method stub
		return categoriesDAO.delete(catId);
	}

	@Override
	public Long getTotalCat() {
		// TODO Auto-generated method stub
		return categoriesDAO.getTotalCat();
	}

}
