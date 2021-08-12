package spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.entity.Accounts;

@Transactional
@Repository
public class AccountDAOImpl implements AccountDAO{
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Accounts> findAllAcc(Integer offset, Integer maxResult) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession(); 
		try {
			session.beginTransaction();
			String hql = "from Accounts";
			TypedQuery<Accounts> query = session.createQuery(hql, Accounts.class).setFirstResult(offset).setMaxResults(maxResult);
			List<Accounts> listAcc = query.getResultList();
			session.getTransaction().commit();
			return listAcc;
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
	public Long getTotalAccounts() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession(); 
		try {
			session.beginTransaction();
			List list = session.createQuery("select count(*) from Accounts").list();
			session.getTransaction().commit();
			return (Long) list.get(0);
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
	public Accounts createAcc(Accounts user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		return user;
	}

	@Override
	public void update(Accounts user) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		
	}

	@Override
	public Accounts delete(String userId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Accounts user = session.find(Accounts.class, userId);
		session.delete(user);
		return user;
		
		
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
	public Accounts findByUserName(String userName) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Accounts user = session.find(Accounts.class, userName);
		return user;
	}

	

	
}
