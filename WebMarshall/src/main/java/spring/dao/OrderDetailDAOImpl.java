package spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.entity.OrderDetail;
import spring.entity.Orders;
import spring.entity.Products;

@Transactional
@Repository
public class OrderDetailDAOImpl implements OrderDetailDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<OrderDetail> findAllOrderDetail() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from OrderDetail";
			TypedQuery<OrderDetail> query = session.createQuery(hql, OrderDetail.class);
			List<OrderDetail> listOrderDetail = query.getResultList();
			return listOrderDetail;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public OrderDetail findByOrderDetailId(Integer orDeId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			OrderDetail orderDetail = session.find(OrderDetail.class, orDeId);
			return orderDetail;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public OrderDetail createOrderDetail(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(orderDetail);
		return orderDetail;
		
//		Session session = sessionFactory.openSession();
//		try {
//			session.beginTransaction();
//			
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			session.close();
//		}
//		return null;
	}

	@Override
	public void update(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(orderDetail);
		
		
//		Session session = sessionFactory.openSession();
//		try {
//			session.beginTransaction();
//			
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			session.close();
//		}
	}

	@Override
	public OrderDetail delete(Integer orDeId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		OrderDetail orderDetail = session.find(OrderDetail.class, orDeId);
		session.delete(orderDetail);
		return orderDetail;
		
		
//		Session session = sessionFactory.openSession();
//		try {
//			session.beginTransaction();
//			
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		} finally {
//			session.close();
//		}
//		return null;
	}

	@Override
	public OrderDetail findByQuantity(Integer quantity) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			OrderDetail orderDetail = session.find(OrderDetail.class, quantity);
			return orderDetail;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

	
	@Override
	public List<OrderDetail> findByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from OrderDetail d where d.order.orderId = : orderId";
			TypedQuery<OrderDetail> query = session.createQuery(hql, OrderDetail.class);
			query.setParameter("orderId", orderId);
			List<OrderDetail> listOrderDetail = query.getResultList();
			return listOrderDetail;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<OrderDetail> findByOrder(Orders order) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from OrderDetail d where d.order.orderId = : orderId";
			TypedQuery<OrderDetail> query = session.createQuery(hql, OrderDetail.class);
			query.setParameter("orderId", order.getOrderId());
			List<OrderDetail> listOrderDetail = query.getResultList();
			return listOrderDetail;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}


	@Override
	public List<OrderDetail> getBestSeller(Products pro) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "select od.product from OrderDetail od "
					+ "where od.product.proId = : proId order by SUM(od.quantity) DESC ";
			TypedQuery<OrderDetail> query = session.createQuery(hql, OrderDetail.class);
			query.setParameter("proId", pro.getProId());
			List<OrderDetail> list = query.getResultList();
			return list;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

}
