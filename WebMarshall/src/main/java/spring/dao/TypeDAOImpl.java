package spring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import spring.entity.Categories;
import spring.entity.ProductType;
import spring.entity.Products;

@Transactional
@Repository
public class TypeDAOImpl implements TypeDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ProductType> getListType() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from ProductType";
			TypedQuery<ProductType> query = session.createQuery(hql, ProductType.class);
			List<ProductType> type = query.getResultList();
			return type;

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
	public ProductType findTypeById(Integer typeId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			ProductType type = session.find(ProductType.class, typeId);
			return type;

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
	public ProductType createType(ProductType type) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.save(type);
		return type;
		
//		Session session = sessionFactory.openSession();
//		try {
//			session.beginTransaction();
//			session.save(type);
//			return type;
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
	public void updateType(ProductType type) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(type);

		
//		Session session = sessionFactory.openSession();
//		try {
//			session.beginTransaction();
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
	public ProductType deleteType(Integer typeId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		ProductType type = session.find(ProductType.class, typeId);
		session.delete(type);
		return type;
		
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
	public List<ProductType> findTypeByCatId(Integer catId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from ProductType type where type.categories.catId = : catId";
			TypedQuery<ProductType> query = session.createQuery(hql, ProductType.class);
			query.setParameter("catId", catId);
			List<ProductType> type = query.getResultList();
			return type;

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
	public List<Products> findProByCat(Categories cate) {      ///getpro by cat
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "select distinct t.products from ProductType t where t.productType.categories.catId = : catId ";
			TypedQuery<Products> query = session.createQuery(hql, Products.class);
			query.setParameter("catId", cate.getCatId());
			List<Products> list = query.getResultList();
			session.getTransaction().commit();
			return list;
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
	public Long getTotalType() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession(); 
		try {
			session.beginTransaction();
			List list = session.createQuery("select count(*) from ProductType").list();
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
