package spring.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name = "Accounts")
public class Accounts implements Serializable {
	
	private static final long serialVersionUID = -2054386655979281969L;
	 
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
	
	@Id
	@NotEmpty(message = "Tên tài khoản không được trống")
    @Column(name = "UserName", length = 20, nullable = false)
    private String userName;
	
	@NotEmpty(message = "Mật khẩu không được trống")
    @Column(name = "Password", length = 200, nullable = false)
    private String password;
    
    @Column(name = "FullName")
    private String fullName;
    
    @NotEmpty(message = "Email không được trống")
    @Email
    @Column(name = "Email")
    private String email;
    
    
    @Column(name = "Active")
    private boolean active;
 
    @Column(name = "User_Role")
    private String userRole;
    
    
    @OneToMany(mappedBy = "accounts",  fetch = FetchType.EAGER)
    private List<Orders> orders;
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public boolean isActive() {
        return active;
    }
 
    public void setActive(boolean active) {
        this.active = active;
    }
 

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
    public String toString() {
        return "[" + this.userName + "," + this.password + "," + this.userRole + "]";
    }
	
}
