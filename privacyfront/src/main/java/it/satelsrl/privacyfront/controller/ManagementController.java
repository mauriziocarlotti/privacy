/**
 * 
 */
package it.satelsrl.privacyfront.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import it.satelsrl.privacyback.dao.CategoryDAO;
import it.satelsrl.privacyback.dao.CustomerDAO;
import it.satelsrl.privacyback.dto.Category;
import it.satelsrl.privacyback.dto.Customer;
import it.satelsrl.privacyfront.util.FileUploadUtility;

/**
 * @author Maurizio Carlotti
 *
 */
@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public ModelAndView showManageCustomers(@RequestParam(name="operation", required=false) String operation) {
		
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageCustomers", true);
		mv.addObject("title", "Gestione clienti");
		Customer nCustomer = new Customer();
		
		nCustomer.setSupplierId(1);
		nCustomer.setActive(true);

		mv.addObject("customer",nCustomer);
		
		if(operation!=null) {
			if(operation.equals("customer")) {
				mv.addObject("message", "Cliente inserito con successo");
			}
		}
		return mv;
		
	}
	
	//handling customer submission
	@RequestMapping(value="/customers", method=RequestMethod.POST)
	public String handleCustomerSubmission(@Valid @ModelAttribute("customer") Customer mCustomer, BindingResult results, Model model,
			HttpServletRequest request) {
		
		//check if there any errors
		if(results.hasErrors()) {
			model.addAttribute("userClickManageCustomers", true);
			model.addAttribute("title", "Gestione clienti");
			model.addAttribute("message","Errore nella validazione dei dati!");
			
			return "page";	
		}
		logger.info(mCustomer.toString());
		// create a new customer
		customerDAO.add(mCustomer);
		
		if(!mCustomer.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request, mCustomer.getFile(), mCustomer.getCodice());
		}
		
		return "redirect:/manage/customers?operation=customer";
	}
	
	// returning categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}
	

}
