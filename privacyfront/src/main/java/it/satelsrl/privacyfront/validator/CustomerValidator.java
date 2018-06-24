/**
 * 
 */
package it.satelsrl.privacyfront.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.satelsrl.privacyback.dto.Customer;

/**
 * @author Maurizio Carlotti
 *
 */
public class CustomerValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Customer.class.equals(clazz);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {
		//
		Customer customer = (Customer) target;

		// whether file has been selected or not
		if (customer.getFile() == null || customer.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file", null, "Attenzione seleziona un file immagine da caricare");
			return;
		}
		if (!(customer.getFile().getContentType().equals("image/jpeg")
				|| customer.getFile().getContentType().equals("image/png")
				|| customer.getFile().getContentType().equals("image/gif"))) {
			errors.rejectValue("file", null, "Attenzione seleziona un file immagine da caricare");
			return;

		}

	}

}
