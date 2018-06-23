/**
 * 
 */
package it.satelsrl.privacyfront.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author Maurizio Carlotti
 *
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException() {
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "La pagina non è costruita!");
		mv.addObject("errorDescription","La pagina che stai vedendo non è disponibile al momento!");
		mv.addObject("title", "404 Error page");
		
		return mv;
		
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public ModelAndView handlerCustomerNotFoundException() {
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Cliente non disponibile!");
		mv.addObject("errorDescription","Il cliente non è disponibile!");
		mv.addObject("title", "Cliente non disponibile");
		
		return mv;
		
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex) {
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("errorTitle", "Contatta l'amministratore!");
		mv.addObject("errorDescription",ex.toString());
		mv.addObject("title", "Errore");
		
		return mv;
		
	}

}
