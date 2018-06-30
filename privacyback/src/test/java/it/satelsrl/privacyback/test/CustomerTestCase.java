/**
 * 
 */
package it.satelsrl.privacyback.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.satelsrl.privacyback.dao.CustomerDAO;
import it.satelsrl.privacyback.dto.Address;
import it.satelsrl.privacyback.dto.Customer;

/**
 * @author Maurizio Carlotti
 *
 */
public class CustomerTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CustomerDAO customerDAO;

	private Customer customer=null;
	private Address address = null;
	

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("it.satelsrl.privacyback");
		context.refresh();
		customerDAO = (CustomerDAO) context.getBean("customerDAO");
	}

	@Test
	public void testCRUDCustomer() {
		// create operation
		customer = new Customer();
		customer.setFirst_name("Marco");
		customer.setLast_name("Giorgi");
		customer.setEmail("marco.giorgi@libero.it");
		customer.setContact_number("222222");
		customer.setActive(true);
		customer.setCategoryId(1);
		customer.setSupplierId(1);

		assertEquals("Qualcosa è andato male quando ho inserito un nuovo customer", true, customerDAO.addCustomer(customer));

		// reading and updating the category
		customer = customerDAO.get(2);
		customer.setFirst_name("Geronimo");
		assertEquals("Qualcosa è andato male quando ho modificato un customer", true, customerDAO.updateCustomer(customer));

		assertEquals("Qualcosa è andato male quando ho cancellato un customer", true, customerDAO.deleteCustomer(customer));

	}
	
	@Test
	public void testAdd() {
		customer = new Customer();
		customer.setFirst_name("Franco");
		customer.setLast_name("Magnanti");
		customer.setEmail("marco.giorgi@libero.it");
		customer.setContact_number("222222");
		customer.setActive(true);
		customer.setCategoryId(1);
		customer.setSupplierId(1);

		assertEquals("Qualcosa è andato male quando ho inserito un nuovo customer", true, customerDAO.addCustomer(customer));
		

		address = new Address();
		address.setAddressLineOne("Via del Tritone, 2");
		address.setAddressLineTwo("00100 Roma  (RM)");
		address.setCity("Roma");
		address.setState("Italia");
		address.setCountry("Italia");
		address.setPostalCode("00100");
		address.setBilling(true);
		address.setCustomer(customer);
		
		
		assertEquals("Fallito ad aggiungere l indirizzo",true,customerDAO.addAddress(address));
		
		
	}
}
