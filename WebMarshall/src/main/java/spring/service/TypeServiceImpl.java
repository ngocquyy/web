package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.TypeDAO;
import spring.entity.Categories;
import spring.entity.ProductType;
import spring.entity.Products;

@Service
public class TypeServiceImpl implements TypeService{
	@Autowired
	private TypeDAO typeDAO;

	@Override
	public List<ProductType> getListType() {
		// TODO Auto-generated method stub
		return typeDAO.getListType();
	}

	@Override
	public ProductType findTypeById(Integer typeId) {
		// TODO Auto-generated method stub
		return typeDAO.findTypeById(typeId);
	}

	@Override
	public ProductType createType(ProductType type) {
		// TODO Auto-generated method stub
		return typeDAO.createType(type);
	}

	@Override
	public void updateType(ProductType type) {
		// TODO Auto-generated method stub
		typeDAO.updateType(type);
	}

	@Override
	public ProductType deleteType(Integer typeId) {
		// TODO Auto-generated method stub
		return typeDAO.deleteType(typeId);
	}

	@Override
	public List<ProductType> findTypeByCatId(Integer catId) {
		// TODO Auto-generated method stub
		return typeDAO.findTypeByCatId(catId);
	}

	@Override
	public List<Products> findProByCat(Categories cate) {
		// TODO Auto-generated method stub
		return typeDAO.findProByCat(cate);
	}

	@Override
	public Long getTotalType() {
		// TODO Auto-generated method stub
		return typeDAO.getTotalType();
	}

}
