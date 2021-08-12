package spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "Categories")
public class Categories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CatId")
	private  Integer catId;
	
	@NotEmpty
	@Column(name = "CatName")
	private String catName;
	
	@OneToMany(mappedBy = "categories", fetch = FetchType.EAGER)
	List<ProductType> productsTypes;

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public List<ProductType> getProductsTypes() {
		return productsTypes;
	}

	public void setProductsTypes(List<ProductType> productsTypes) {
		this.productsTypes = productsTypes;
	}

	
	
	
}
