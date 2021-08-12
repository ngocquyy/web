package spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.entity.News;

@Repository
@Transactional
public class NewsDAOImpl implements NewsDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<News> getListNews(Integer offset, Integer maxResult) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from News";
			TypedQuery<News> query = session.createQuery(hql, News.class).setFirstResult(offset)
					.setMaxResults(maxResult);
			List<News> news = query.getResultList();
			return news;

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
	public Long getTotalNews() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			List list = session.createQuery("select count(*) from News").list();
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
	public News getNewsById(Integer newsId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			News news = session.find(News.class, newsId);
			return news;

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
	public News insertNews(News ns) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(ns);
		return ns;
		
//		Session session = sessionFactory.openSession();
//		try {
//			session.beginTransaction();
//			session.save(ns);
//			return ns;
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
	public void updateNews(News ns) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(ns);
		
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
	public News deleteNews(Integer newsId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		News news = session.find(News.class, newsId);
		session.delete(news);
		return news;
		
		
		
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

}
