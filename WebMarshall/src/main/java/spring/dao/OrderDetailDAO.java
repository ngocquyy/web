package spring.dao;

import java.util.List;

import spring.entity.Orders;
import spring.entity.Products;
import spring.entity.OrderDetail;

public interface OrderDetailDAO {
	public List<OrderDetail> findAllOrderDetail();
	public OrderDetail findByOrderDetailId(Integer orDeId);
	public OrderDetail createOrderDetail(OrderDetail orderDetail);
	public void update(OrderDetail orderDetail);
	public OrderDetail delete(Integer orDeId);
	public List<OrderDetail> findByOrder(Orders order);
	public OrderDetail findByQuantity(Integer quantity);
	public List<OrderDetail> findByOrderId(Integer orderId);
	public List<OrderDetail> getBestSeller(Products pro);
}
