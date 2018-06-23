/**
 * 
 */
package it.satelsrl.privacyfront.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Maurizio Carlotti
 *
 */
@Controller
@RequestMapping("/manage")
public class ManagementController {
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public ModelAndView showManageCustomers() {
		
		ModelAndView mv = new ModelAndView("page");
		
		mv.addObject("userClickManageCustomers", true);
		mv.addObject("title", "Gestione clienti");
		return mv;
		
	}

}
