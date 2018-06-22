/**
 * 
 */
package it.satelsrl.privacyfront.exception;

import java.io.Serializable;

/**
 * @author maurizio
 *
 */
public class CustomerNotFoundException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public CustomerNotFoundException() {
		this("Product is not available!");
	}
	
	public CustomerNotFoundException(String message) {
		this.message = System.currentTimeMillis() + ": " + message;
	}

	public String getMessage() {
		return message;
	}
	

}
