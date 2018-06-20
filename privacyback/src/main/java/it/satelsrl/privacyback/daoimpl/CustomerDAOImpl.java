/**
 * 
 */
package it.satelsrl.privacyback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.satelsrl.privacyback.dao.CustomerDAO;
import it.satelsrl.privacyback.dto.Customer;

/**
 * @author Maurizio Carlotti
 *
 */
@Repository("customerDAO")
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * SINGLE
	 */
	@Override
	public Customer get(int customerId) {
		try {
			// leggi il customer dal database
			return sessionFactory.getCurrentSession().get(Customer.class,Integer.valueOf(customerId));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
		
	}
	/*
	 * LIST
	 */
	@Override
	public List<Customer> list() {
		return sessionFactory.getCurrentSession().createQuery("FROM customer", Customer.class).getResultList();
	}
	/*
	 * INSERT
	 */
	@Override
	public boolean add(Customer customer) {
		try {
			// aggiungi il customer al database
			sessionFactory.getCurrentSession().persist(customer);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
		
	}
	/*
	 * UPDATE
	 */
	@Override
	public boolean update(Customer customer) {
		try {
			// aggiungi il customer al database
			sessionFactory.getCurrentSession().update(customer);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	/*
	 * DELETE
	 */
	@Override
	public boolean delete(Customer customer) {
		try {
			// aggiungi il customer al database
			customer.setActive(false);
			return this.update(customer);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	@Override
	public List<Customer> listActiveCustomers() {
		String selectActiveCustomers = "FROM customer WHERE active = :active";
		return sessionFactory
				.getCurrentSession()
				.createQuery(selectActiveCustomers, Customer.class)
				.setParameter("active", true)
				.getResultList();
	}
	@Override
	public List<Customer> listActiveCustomersByCategory(int categoryId) {
		String selectActiveCustomersByCategory = "FROM customer WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory
				.getCurrentSession()
				.createQuery(selectActiveCustomersByCategory, Customer.class)
				.setParameter("active", true)
				.setParameter("categoryId", categoryId)
				.getResultList();
	}
	
	@Override
	public List<Customer> getLatestActiveCustomers(int count) {
		return sessionFactory
				.getCurrentSession()
				.createQuery("FROM customer WHERE active = :active ORDER BY id", Customer.class)
				.setParameter("active", true)
				.setFirstResult(0)
				.setMaxResults(count)
				.getResultList();
	}
}
