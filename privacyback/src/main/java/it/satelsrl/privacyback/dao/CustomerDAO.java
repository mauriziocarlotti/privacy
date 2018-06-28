/**
 * 
 */
package it.satelsrl.privacyback.dao;

import java.util.List;

import it.satelsrl.privacyback.dto.Address;
import it.satelsrl.privacyback.dto.Customer;

/**
 * @author Maurizio Carlotti
 *
 */
public interface CustomerDAO {
	Customer get(int customerID);
	List<Customer> list();
	boolean add(Customer customer);
	boolean update(Customer customer);
	boolean delete(Customer customer);
	Customer getByEmail(String email);

	// business methods
	List<Customer> listActiveCustomers();
	List<Customer> listActiveCustomersByCategory(int categoryId);
	List<Customer> getLatestActiveCustomers(int count);
	boolean addAddress(Address address);
	boolean updateAddress(Address address);
	List<Address> listShippingAddresses(Customer customer);
	Address getAddress(int addressId);
	
}
