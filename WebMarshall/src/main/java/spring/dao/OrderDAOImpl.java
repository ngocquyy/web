package spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.entity.Accounts;
import spring.entity.Orders;
import spring.entity.Products;
import spring.entity.OrderDetail;

@Transactional
@Repository
public class OrderDAOImpl implements OrderDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Orders> findAllOrder(Integer offset, Integer maxResult) { ///
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession(); 
		try {
			session.beginTransaction();
			String hql = "from Orders order by orderId DESC";
			TypedQuery<Orders> query = session.createQuery(hql, Orders.class).setFirstResult(offset).setMaxResults(maxResult);
			List<Orders> listOrder = query.getResultList();
			session.getTransaction().commit();
			return listOrder;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			session.close();
		}
		return null;	
	}

	@Override
	public Long getTotalOrders() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			List list = session.createQuery("select count(*) from Orders").list();
			session.getTransaction().commit();
			return (Long) list.get(0);
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
	public Orders findByOrderId(Integer orderId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Orders order = session.find(Orders.class, orderId);
			return order;	
	
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
	public Orders createOrder(Orders order) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(order);
		return order;
		
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
	public void update(Orders order) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(order);
		
//		Session session = sessionFactory.openSession();
//		try {
//			session.beginTransaction();
//			
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
	public Orders delete(Integer orderId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Orders order = session.find(Orders.class, orderId);
		session.delete(order);
		return order;
		
		
//		Session session = sessionFactory.openSession();
//		try {
//			session.beginTransaction();
//			
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
	public void create(Orders order, List<OrderDetail> details) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(order);


			for (OrderDetail detail : details) {
				detail.setOrder(order);
				session.save(detail);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}

	}

	@Override
	public List<Orders> findByUser(Accounts user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from Orders o Where o.accounts.userName = : userName order by orderId DESC"; // don hang moi hien len tren : order by
			TypedQuery<Orders> query = session.createQuery(hql, Orders.class);
			query.setParameter("userName", user.getUserName());
			List<Orders> listOrder = query.getResultList();
			session.getTransaction().commit();
			return listOrder;
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
	public List<Products> findBoughtByUser(Accounts user) {			
		// TODO Auto-generated method stub									// select product of user
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "select d.product from OrderDetail d where d.order.accounts.userName = :userName "; 
			TypedQuery<Products> query = session.createQuery(hql, Products.class);
			query.setParameter("userName", user.getUserName());
			List<Products> list = query.getResultList();
			session.getTransaction().commit();
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
