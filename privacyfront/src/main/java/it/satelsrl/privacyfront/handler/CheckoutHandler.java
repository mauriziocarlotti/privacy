package it.satelsrl.privacyfront.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.satelsrl.privacyback.dao.CustomerDAO;
import it.satelsrl.privacyback.dao.UserDAO;
import it.satelsrl.privacyback.dto.Address;
import it.satelsrl.privacyback.dto.User;
import it.satelsrl.privacyfront.model.CheckoutModel;


@Component
public class CheckoutHandler {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutHandler.class);
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private CustomerDAO customerDAO;

	@Autowired
	private HttpSession session;
	
	
	public CheckoutModel init(String name) throws Exception{

		User user = userDAO.getByEmail(name);
		CheckoutModel checkoutModel = null;	
		return checkoutModel;
	}
	
	
	public List<Address> getShippingAddresses(CheckoutModel model) {
				
		List<Address> addresses = userDAO.listShippingAddresses(model.getUser().getId());
		
		if(addresses.size() == 0) {
			addresses = new ArrayList<>();
		}

		addresses.add(addresses.size(), userDAO.getBillingAddress(model.getUser().getId()));			
		
		return addresses;
		
	}
	
			
	
		


	
	
	
	
}



