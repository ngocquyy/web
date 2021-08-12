package spring.dao;
import java.util.List;


import spring.entity.Categories;


public interface CategoriesDAO {
	public List<Categories> getListCat();
	public Long getTotalCat();
	public Categories findById(Integer catId);
	public Categories create(Categories cate);
	public void update(Categories cate);
	public Categories delete(Integer catId);
}
