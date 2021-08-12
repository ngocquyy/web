package spring.entity;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Orders")
public class Orders  {
	
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderId", length = 50)
    private Integer orderId;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "OrderDate", nullable = false)
    private Date orderDate;
 
    @Column(name = "Amount", nullable = false)
    private double amount;
 
    @Column(name = "CustomerAdress", length = 300, nullable = false)
    private String customerAddress;
    
    @Column(name = "Descriptions")
    private String descriptions;
    
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private List<OrderDetail> orderDetails;
    
    @ManyToOne
    @JoinColumn(name = "UserName")
    private Accounts accounts;
    
    
 
    public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public Date getOrderDate() {
        return orderDate;
    }
 
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
 
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }
 
 
    public String getCustomerAddress() {
        return customerAddress;
    }
 
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
 
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Accounts getAccount() {
		return accounts;
	}

	public void setAccount(Accounts accounts) {
		this.accounts = accounts;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

    
    
}
