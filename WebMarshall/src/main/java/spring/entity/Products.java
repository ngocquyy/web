package spring.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "Products")
public class Products implements Serializable {
	private static final long serialVersionUID = -1000119078147252957L;
	 
    @Id
    @NotEmpty
    @Column(name = "ProId", length = 20, nullable = false)
    private String proId;
    
    @NotEmpty
    @Column(name = "ProName", length = 200, nullable = false)
    private String proName;
    
    @NotEmpty
    @Column(name = "Price", nullable = false)
    private double price;

    @NotEmpty
    @Column(name = "Image")
    private String image;
     
    @NotEmpty
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "CreatDate", nullable = false)
    private Date createDate;
    
    @NotEmpty
    @Column(name = "Unit", length = 10)
    private String unit;
    
    @Column(name = "Discount")
    private float discount;
    
    @Column(name = "Descriptions", length = 200)
    private String descriptions;
    
    @Column(name = "Detail")
    private String detail;
    
    @NotEmpty
    @Column(name = "Status")
    private boolean status;
    
    @Column(name = "Quantity")
    private Integer quantity;
    
    //Type ID
    @ManyToOne
    @JoinColumn(name = "TypeId")
    private ProductType productType;
    
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    List<OrderDetail> orderDetails;
    public Products() {
    }
 
    public String getProId() {
        return proId;
    }
 
    public void setProId(String proId) {
        this.proId = proId;
    }
 
    public String getProName() {
        return proName;
    }
 
    public void setProName(String proName) {
        this.proName = proName;
    }
 
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
 
    public Date getCreateDate() {
        return createDate;
    }
 
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
 
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public int hashCode() {
		return Objects.hash(proId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Products other = (Products) obj;
		return Objects.equals(proId, other.proId);
	}   
	
	 
    
}
