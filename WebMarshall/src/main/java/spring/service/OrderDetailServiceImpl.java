package spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.dao.OrderDetailDAO;
import spring.entity.OrderDetail;
import spring.entity.Orders;
import spring.entity.Products;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{
	@Autowired
	private OrderDetailDAO orderDetailDAO;

	@Override
	public List<OrderDetail> findAllOrderDetail() {
		// TODO Auto-generated method stub
		return orderDetailDAO.findAllOrderDetail();
	}

	@Override
	public OrderDetail findByOrderDetailId(Integer orDeId) {
		// TODO Auto-generated method stub
		return orderDetailDAO.findByOrderDetailId(orDeId);
	}

	@Override
	public OrderDetail createOrderDetail(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		return orderDetailDAO.createOrderDetail(orderDetail);
	}

	@Override
	public void update(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		orderDetailDAO.update(orderDetail);
	}

	@Override
	public OrderDetail delete(Integer orDeId) {
		// TODO Auto-generated method stub
		return orderDetailDAO.delete(orDeId);
	}

	@Override
	public List<OrderDetail> findByOrder(Orders order) {
		// TODO Auto-generated method stub
		return orderDetailDAO.findByOrder(order);
	}

	@Override
	public OrderDetail findByQuantity(Integer quantity) {
		// TODO Auto-generated method stub
		return orderDetailDAO.findByQuantity(quantity);
	}

	@Override
	public List<OrderDetail> findByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		return orderDetailDAO.findByOrderId(orderId);
	}

	@Override
	public List<OrderDetail> getBestSeller(Products pro) {
		// TODO Auto-generated method stub
		return orderDetailDAO.getBestSeller(pro);
	}


}
