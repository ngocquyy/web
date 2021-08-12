package spring.service;

import java.util.List;

import spring.entity.Accounts;
import spring.entity.OrderDetail;
import spring.entity.Orders;
import spring.entity.Products;

public interface OrderService {
	public List<Orders> findAllOrder(Integer offset, Integer maxResult) ; 			//
	public Long getTotalOrders();
	public Orders findByOrderId(Integer orderId);
	public Orders createOrder(Orders order);
	public void update(Orders order);
	public Orders delete(Integer orderId);
	public void create(Orders order, List<OrderDetail> listDetail);
	public List<Orders> findByUser(Accounts user);
	public List<Products> findBoughtByUser(Accounts user);
	public Long getTotalOrder();
}	
