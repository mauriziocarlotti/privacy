/**
 * 
 */
package it.satelsrl.privacyback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.satelsrl.privacyback.dao.CategoryDAO;
import it.satelsrl.privacyback.dto.Category;

/**
 * @author Maurizio Carlotti
 *
 */
@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.satelsrl.privacyback.dao.CategoryDAO#list()
	 */
	@Override
	public List<Category> list() {
		String selectActiveCategory = "FROM category WHERE active = :active";

		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);

		query.setParameter("active", true);
		return query.getResultList();
	}

	/*
	 * (non-Javadoc) Getting single category based on id
	 */
	@Override
	public Category get(int id) {

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	/*
	 * (non-Javadoc) Add category
	 */
	@Override
	public boolean add(Category category) {
		try {
			// add la categoria al database
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc) Updating single category
	 */
	@Override
	public boolean update(Category category) {
		try {
			// update la categoria al database
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	/*
	 * (non-Javadoc) Delete single category
	 */
	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try {
			// delete la categoria dal database
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
