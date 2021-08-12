package spring.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
 

@Entity
@Table(name = "OrderDetail")
public class OrderDetail {
	 
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "OrDeId", length = 50, nullable = false)
	    private Integer orDeId;
	 
	    @ManyToOne
	    @JoinColumn(name = "OrderId", nullable = false )
	    private Orders order;
	 
	    @ManyToOne
	    @JoinColumn(name = "ProId", nullable = false )
	    private Products product;
	 
	    @Column(name = "Quantity", nullable = false)
	    private int quantity;
	 
	    @Column(name = "Price", nullable = false)
	    private double price;
	 
	    @Column(name = "Discount", nullable = false)
	    private double discount;
	 
	    public void setOrDeId(Integer orDeId) {
			this.orDeId = orDeId;
		}

		public Orders getOrder() {
	        return order;
	    }
	 
	    public void setOrder(Orders order) {
	        this.order = order;
	    }
	 
	    public Products getProduct() {
	        return product;
	    }
	 
	    public void setProduct(Products product) {
	        this.product = product;
	    }
	 
	    public int getQuantity() {
	        return quantity;
	    }
	 
	    public void setQuanity(int quantity) {
	        this.quantity = quantity;
	    }
	 
	    public double getPrice() {
	        return price;
	    }
	 
	    public void setPrice(double price) {
	        this.price = price;
	    }

		public double getDiscount() {
			return discount;
		}

		public void setDiscount(double discount) {
			this.discount = discount;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public Integer getOrDeId() {
			return orDeId;
		}
		
		
	  
}
