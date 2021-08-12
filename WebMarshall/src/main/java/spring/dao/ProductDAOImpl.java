package spring.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.entity.Products;

@Transactional
@Repository
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Products findByProId(String proId) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Products pro = session.find(Products.class, proId);
			return pro;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

	public List<Products> findByType(Integer typeId) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from Products p where p.productType.typeId = : typeId";
			TypedQuery<Products> query = session.createQuery(hql, Products.class);
			query.setParameter("typeId", typeId);
			List<Products> pro = query.getResultList();
			return pro;

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
	public List<Products> findAllPro(Integer offset, Integer maxResult) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from Products";
			TypedQuery<Products> query = session.createQuery(hql, Products.class).setFirstResult(offset)
					.setMaxResults(maxResult);
			List<Products> listPro = query.getResultList();
			return listPro;

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
	public Long getTotalProducts() {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			List list = session.createQuery("select count(*) from Products").list();
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
	public Products createPro(Products pro) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(pro);
		return pro;
		
		
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
	public void update(Products pro) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(pro);
		
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
	public Products delete(String proId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Products pro = session.find(Products.class, proId);
		session.delete(pro);
		return pro;
		
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
	public List<Products> getNewArrivals() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from Products order by createDate DESC";
			TypedQuery<Products> query = session.createQuery(hql, Products.class).setMaxResults(6);
			List<Products> listPro = query.getResultList();
			return listPro;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return null;
	}

	

//	@Override
//	public Products findByOrderDetail(OrderDetail or) {
//		// TODO Auto-generated method stub
//		String hql = "from Products p where p.orderDetails.orDeId = : orDeId";
//		Session session = sessionFactory.getCurrentSession();
//		TypedQuery<Products> query = session.createQuery(hql, Products.class);
//		query.setParameter("orDeId", orDeId);
//		List<Products> pro = query.getResultList();
//		return pro;
//	}

}
