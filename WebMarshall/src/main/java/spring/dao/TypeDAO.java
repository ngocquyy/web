package spring.dao;

import java.util.List;

import spring.entity.Categories;
import spring.entity.ProductType;
import spring.entity.Products;

public interface TypeDAO {
	public List<ProductType> getListType();
	public Long getTotalType() ;
	public ProductType findTypeById(Integer typeId);
	public ProductType createType(ProductType type);
	public void updateType(ProductType type);
	public ProductType deleteType(Integer typeId);
	public List<ProductType> findTypeByCatId(Integer catId);
	public List<Products> findProByCat(Categories cate);
}
