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
import it.satelsrl.privacyback.dto.Address;
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
		String selectActiveCustomers = "FROM Customer WHERE active = :active";
		return sessionFactory
				.getCurrentSession()
				.createQuery(selectActiveCustomers, Customer.class)
				.setParameter("active", true)
				.getResultList();
	}
	
	public List<Customer> listActiveCustomersByCategory(int categoryId) {
		String selectActiveCustomersByCategory = "FROM Customer WHERE active = :active AND categoryId = :categoryId";
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
	@Override
	public Customer getByEmail(String email) {
		String selectQuery ="FROM Customer WHERE email = :email";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, Customer.class).setParameter("email", email).getSingleResult();
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	@Override
	public boolean addAddress(Address address) {
		try {			
			// will look for this code later and why we need to change it
			sessionFactory.getCurrentSession().persist(address);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	@Override
	public boolean updateAddress(Address address) {
		try {			
			sessionFactory.getCurrentSession().update(address);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}	
	

	@Override
	public List<Address> listShippingAddresses(Customer customer) {
		
		String selectQuery = "FROM Address where customer = :customer";
		try {
			return sessionFactory
					.getCurrentSession()
						.createQuery(selectQuery,Address.class)
							.setParameter("customer", customer)
								.getResultList();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		
	}

	@Override
	public Address getAddress(int addressId) {
		try {			
			return sessionFactory.getCurrentSession().get(Address.class, addressId);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

}
