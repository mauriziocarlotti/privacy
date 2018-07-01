/**
 * 
 */
package it.satelsrl.privacyfront.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.satelsrl.privacyback.dao.CategoryDAO;
import it.satelsrl.privacyback.dao.CustomerDAO;
import it.satelsrl.privacyback.dto.Category;
import it.satelsrl.privacyback.dto.Customer;
import it.satelsrl.privacyfront.exception.CustomerNotFoundException;

/**
 * @author Maurizio Carlotti
 *
 */
@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", "Benvenuto to spring");
		mv.addObject("title", "Home");
		
		logger.info("PageController metodo index");
		logger.debug("PageController metodo index in debug");
		
		// passing la lista delle categorie
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickHome", true);
		return mv;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Chi siamo");
		mv.addObject("userClickAbout", true);
		return mv;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contatti");
		mv.addObject("userClickContact", true);
		return mv;
	}

	// @RequestMapping(value= "/test")
	// public ModelAndView test(@RequestParam(value="greeting",
	// required=false)String greeting) {
	// if (greeting == null) {
	// greeting = "Ciao a tutti";
	// }
	// ModelAndView mv = new ModelAndView ("page");
	// mv.addObject("greeting",greeting);
	//
	// return mv;
	//
	// }
	@RequestMapping(value = "/test/{greeting}")
	public ModelAndView test(@PathVariable("greeting") String greeting) {
		if (greeting == null) {
			greeting = "Ciao a tutti";
		}
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("greeting", greeting);

		return mv;

	}
	/*
	 * Metodi per caricare tutti i customer
	 * 
	 */

	@RequestMapping(value = "/show/all/customers")
	public ModelAndView showAllCustomers() {
		ModelAndView mv = new ModelAndView("page");
		// categoryDAO a trovare i clienti per categoria

		mv.addObject("title", "Tutti i clienti");
		// passing la lista delle categorie
		mv.addObject("categories", categoryDAO.list());

		mv.addObject("userClickAllCustomers", true);
		return mv;
	}

	/*
	 * Metodi per caricare tutte le categorie
	 * 
	 */

	@RequestMapping(value = "/show/category/{id}/customers")
	public ModelAndView showCategoryCustomers(@PathVariable("id") int id) throws CustomerNotFoundException {
		ModelAndView mv = new ModelAndView("page");
		// categoryDAO a trovare i clienti per categoria

		Category category = null;

		category = categoryDAO.get(id);

		mv.addObject("title", category.getName());
		// passing la lista delle categorie
		mv.addObject("categories", categoryDAO.list());

		// passare la singola categoria
		mv.addObject("category", category);

		mv.addObject("userClickCategoryCustomers", true);
		return mv;
	}

	/*
	 * 
	 */
	
	@RequestMapping(value = "/show/{id}/customer")
	public ModelAndView showSingleCustomer(@PathVariable int id) throws CustomerNotFoundException {

		ModelAndView mv = new ModelAndView("page");

		Customer customer = customerDAO.get(id);

		logger.info("PageController metodo /show/{id}/customer");
		if (customer == null)
			throw new CustomerNotFoundException();

		// update the view count
//		customer.setViews(customer.getViews() + 1);
//		customerDAO.update(customer);
		// ---------------------------

		mv.addObject("title", customer.getLast_name());
		mv.addObject("customer", customer);

		mv.addObject("userClickShowCustomer", true);

		return mv;

	}
	@RequestMapping(value="/membership")
	public ModelAndView register() {
		ModelAndView mv= new ModelAndView("page");
		
		logger.info("Page Controller membership called!");
		
		return mv;
	}
	
	
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam(name="error", required = false)	String error,
			@RequestParam(name="logout", required = false) String logout) {
		ModelAndView mv= new ModelAndView("login");
		mv.addObject("title", "Login");
		if(error!=null) {
			mv.addObject("message", "Username and Password is invalid!");
		}
		if(logout!=null) {
			mv.addObject("logout", "You have logged out successfully!");
		}
		return mv;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// Invalidates HTTP Session, then unbinds any objects bound to it.
	    // Removes the authentication from securitycontext 		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		
		return "redirect:/login?logout";
	}	
	
	
	@RequestMapping(value="/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");		
		mv.addObject("errorTitle", "Aha! Caught You.");		
		mv.addObject("errorDescription", "You are not authorized to view this page!");		
		mv.addObject("title", "403 Access Denied");		
		return mv;
	}	
		
	
	

}
