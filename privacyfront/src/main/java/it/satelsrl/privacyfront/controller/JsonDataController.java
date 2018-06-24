/**
 * 
 */
package it.satelsrl.privacyfront.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import it.satelsrl.privacyback.dao.CustomerDAO;
import it.satelsrl.privacyback.dto.Customer;

/**
 * @author maurizio
 *
 */
@Controller
@RequestMapping("/json/data")
public class JsonDataController {

	@Autowired
	CustomerDAO customerDAO;
	
	@RequestMapping("/all/customers")
	@ResponseBody
	public List<Customer> getAllCustomers() {
		
		return customerDAO.listActiveCustomers();
		
	}
	
	@RequestMapping("/admin/all/customers")
	@ResponseBody
	public List<Customer> getAllCustomersForAdmin() {
		
		return customerDAO.list();
		
	}
	
	@RequestMapping("/category/{id}/customers")
	@ResponseBody
	public List<Customer> getCustomersByCategory(@PathVariable int id) {
		
		return customerDAO.listActiveCustomersByCategory(id);
		
	}
	
}
