package it.satelsrl.privacyfront.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import it.satelsrl.privacyback.dao.UserDAO;
import it.satelsrl.privacyback.dto.Address;
import it.satelsrl.privacyback.dto.User;
import it.satelsrl.privacyfront.model.RegisterModel;

@Component
public class RegisterHandler {


 @Autowired
 private PasswordEncoder passwordEncoder;
	
	
 @Autowired
 private UserDAO userDAO;
 public RegisterModel init() { 
  return new RegisterModel();
 } 
 public void addUser(RegisterModel registerModel, User user) {
  registerModel.setUser(user);
 } 
 public void addBilling(RegisterModel registerModel, Address billing) {
  registerModel.setBilling(billing);
 }

 public String validateUser(User user, MessageContext error) {
  String transitionValue = "success";
   if(!user.getPassword().equals(user.getConfirmPassword())) {
    error.addMessage(new MessageBuilder().error().source(
      "confirmPassword").defaultText("Password does not match confirm password!").build());
    transitionValue = "failure";    
   }  
   if(userDAO.getByEmail(user.getEmail())!=null) {
    error.addMessage(new MessageBuilder().error().source(
      "email").defaultText("Email address is already taken!").build());
    transitionValue = "failure";
   }
  return transitionValue;
 }
 
 public String saveAll(RegisterModel registerModel) {
  String transitionValue = "success";
  User user = registerModel.getUser();
  if(user.getRole().equals("USER")) {
   // create a new cart
  }
   
  // encode the password
  user.setPassword(passwordEncoder.encode(user.getPassword()));
  
  // save the user
  userDAO.add(user);
  // save the billing address
  return transitionValue ;
 } 
}
