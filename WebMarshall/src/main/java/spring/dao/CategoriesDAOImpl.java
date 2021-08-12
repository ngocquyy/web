package spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.entity.Categories;

@Repository
@Transactional
public class CategoriesDAOImpl implements CategoriesDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Categories> getListCat() {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from Categories";
			TypedQuery<Categories> query = session.createQuery(hql, Categories.class);
			List<Categories> listCat = query.getResultList();
			return listCat;

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
	public Categories findById(Integer catId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Categories cate = session.find(Categories.class, catId);
			return cate;

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
	public Categories create(Categories cate) { // cate tại đây hiện chưa có giá trị (ngoài hibernate)
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(cate); // == persist
		return cate;
		
//		Session session = sessionFactory.openSession();
//		try {
//			session.beginTransaction();
//			session.save(cate); // == persist
//			return cate;// cate tại đây đã có giá trị (trong hibernate)
//			// nên tạo xong phải return
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
	public void update(Categories cate) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(cate);
		
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
	public Categories delete(Integer catId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Categories cate = session.find(Categories.class, catId);
		session.delete(cate);
		return cate;
		
		
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
	public Long getTotalCat() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession(); 
		try {
			session.beginTransaction();
			List list = session.createQuery("select count(*) from Categories").list();
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
}
