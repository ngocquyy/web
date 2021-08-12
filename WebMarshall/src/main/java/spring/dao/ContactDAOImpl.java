package spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.entity.Contact;

@Repository
@Transactional
public class ContactDAOImpl implements ContactDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Contact> getListContact() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from Contact";
			TypedQuery<Contact> query = session.createQuery(hql, Contact.class);
			List<Contact> contacts = query.getResultList();
			return contacts;

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
	public Contact insertContact(Contact ct) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(ct);
		return ct;
		
		
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
	public void updateContact(Contact ct) {
		// TODO Auto-generated method stub
		
		Session session = sessionFactory.getCurrentSession();
		session.update(ct);
		
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
	public Contact findById(Integer contId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Contact contact = session.find(Contact.class, contId);
			return contact;

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
