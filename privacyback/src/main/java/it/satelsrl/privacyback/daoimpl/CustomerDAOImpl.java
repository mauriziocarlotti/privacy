/**
 * 
 */
package it.satelsrl.privacyback.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import it.satelsrl.privacyback.dto.Customer;

/**
 * @author Maurizio Carlotti
 *
 */
@Repository("customerDAO")
@Transactional
public class CustomerDAOImpl {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * SINGLE
	 */
	public Customer get(int customerId) {
		try {
			// leggi il customer dal database
			sessionFactory.getCurrentSession().get(Customer.class,Integer.valueOf(customerId));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
		
	}
	/*
	 * LIST
	 */
	public List<Customer> list() {
		return sessionFactory.getCurrentSession().createQuery("FROM Customer", Customer.class).getResultList();
	}
	/*
	 * INSERT
	 */
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
	public List<Customer> listActiveCustomers() {
		String selectActiveCustomers = "FROM Customer WHERE active= :active";
		return sessionFactory
				.getCurrentSession()
				.createQuery(selectActiveCustomers, Customer.class)
				.setParameter("active", true)
				.getResultList();
	}
	
	public List<Customer> listActiveCustomersByCategory(int categoryId) {
		String selectActiveCustomersByCategory = "FROM Customer WHERE active= :active AND categoryId = :categoryId";
		return sessionFactory
				.getCurrentSession()
				.createQuery(selectActiveCustomersByCategory, Customer.class)
				.setParameter("active", true)
				.setParameter("categoryId", categoryId)
				.getResultList();
	}
	
	public List<Customer> getLatestActiveCustomers(int count) {
		return sessionFactory
				.getCurrentSession()
				.createQuery("FROM Customer WHERE active = :active ORDER BY id", Customer.class)
				.setParameter("active", true)
				.setFirstResult(0)
				.setMaxResults(count)
				.getResultList();
	}
}
