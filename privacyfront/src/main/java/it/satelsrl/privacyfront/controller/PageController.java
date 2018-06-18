/**
 * 
 */
package it.satelsrl.privacyfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import it.satelsrl.privacyback.dao.CategoryDAO;
//import it.satelsrl.privacyback.dao.CustomerDAO;
import it.satelsrl.privacyback.dto.Category;

/**
 * @author Maurizio Carlotti
 *
 */
@Controller
public class PageController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
//	@Autowired
//	private CustomerDAO customerDAO;

	@RequestMapping(value = {"/", "/home", "/index"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView ("page");
		mv.addObject("greeting","Benvenuto to spring");
		mv.addObject("title","Home");
		// passing la lista delle categorie
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickHome",true);
		return mv;
	}
	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView ("page");
		mv.addObject("title","Chi siamo");
		mv.addObject("userClickAbout",true);
		return mv;
	}
	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView ("page");
		mv.addObject("title","Contatti");
		mv.addObject("userClickContact",true);
		return mv;
	}

//	@RequestMapping(value= "/test")
//	public ModelAndView test(@RequestParam(value="greeting", required=false)String greeting) {
//		if (greeting == null) {
//			greeting = "Ciao a tutti";
//		}
//		ModelAndView mv = new ModelAndView ("page");
//		mv.addObject("greeting",greeting);
//		
//		return mv;
//		
//	}
	@RequestMapping(value= "/test/{greeting}")
	public ModelAndView test(@PathVariable("greeting")String greeting) {
		if (greeting == null) {
			greeting = "Ciao a tutti";
		}
		ModelAndView mv = new ModelAndView ("page");
		mv.addObject("greeting",greeting);
		
		return mv;
		
	}
	/*
	 * Metodi per caricare tutti i customer
	 * 
	 */
	
	@RequestMapping(value = "/show/all/customers")
	public ModelAndView showAllCustomers() {
		ModelAndView mv = new ModelAndView ("page");
		// categoryDAO a trovare i clienti per categoria
		
		mv.addObject("title","Tutti i clienti");
		// passing la lista delle categorie
		mv.addObject("categories", categoryDAO.list());
		
		mv.addObject("userClickAllCustomers",true);
		return mv;
	}
	
	/*
	 * Metodi per caricare tutte le categorie
	 * 
	 */
	
	@RequestMapping(value = "/show/category/{id}/customers")
	public ModelAndView showCategoryCustomers(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView ("page");
		// categoryDAO a trovare i clienti per categoria
		
		Category category = null;
		
		category = categoryDAO.get(id);
		
		mv.addObject("title",category.getName());
		// passing la lista delle categorie
		mv.addObject("categories", categoryDAO.list());
		
		// passare la singola categoria
		mv.addObject("category", category);
		
		mv.addObject("userClickCategoryCustomers",true);
		return mv;
	}
	
}
