package spring.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "ProductType")
public class ProductType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TypeId")
	private Integer typeId;
	
	@NotEmpty
	@Column(name = "TypeName", length = 100)
	private String typeName;
	
	@Column(name = "Descriptions")
	private String descriptions;
	
	//Categories ID
    @ManyToOne
    @JoinColumn(name = "CatId")
    private Categories categories;
    
    @OneToMany(mappedBy = "productType", fetch = FetchType.EAGER)
    private List<Products> products;
	
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	

	
	
	
	
}
