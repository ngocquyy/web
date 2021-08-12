package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.OrderDAO;
import spring.entity.Accounts;
import spring.entity.OrderDetail;
import spring.entity.Orders;
import spring.entity.Products;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderDAO orderDAO;

	@Override
	public List<Orders> findAllOrder(Integer offset, Integer maxResult) {
		// TODO Auto-generated method stub
		return orderDAO.findAllOrder(offset, maxResult);
	}

	@Override
	public Long getTotalOrders() {
		// TODO Auto-generated method stub
		return orderDAO.getTotalOrders();
	}

	@Override
	public Orders findByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return orderDAO.findByOrderId(orderId);
	}

	@Override
	public Orders createOrder(Orders order) {
		// TODO Auto-generated method stub
		return orderDAO.createOrder(order);
	}

	@Override
	public void update(Orders order) {
		// TODO Auto-generated method stub
		orderDAO.update(order);
	}

	@Override
	public Orders delete(Integer orderId) {
		// TODO Auto-generated method stub
		return orderDAO.delete(orderId);
	}

	@Override
	public void create(Orders order, List<OrderDetail> listDetail) {
		// TODO Auto-generated method stub
		orderDAO.create(order, listDetail);
	}

	@Override
	public List<Orders> findByUser(Accounts user) {
		// TODO Auto-generated method stub
		return orderDAO.findByUser(user);
	}

	@Override
	public List<Products> findBoughtByUser(Accounts user) {
		// TODO Auto-generated method stub
		return orderDAO.findBoughtByUser(user);
	}

	@Override
	public Long getTotalOrder() {
		// TODO Auto-generated method stub
		return orderDAO.getTotalOrders();
	}

}
