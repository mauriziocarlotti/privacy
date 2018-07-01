/**
 * 
 */
package it.satelsrl.privacyback.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Maurizio Carlotti
 *
 */
@Entity
@Table(name = "address")
public class Address implements Serializable {

	/*
	 * private fields
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	// ---------------------------------
//	@ManyToOne
//	private Customer customer;
//	
//	/**
//	 * @return the customer
//	 */
//	public Customer getCustomer() {
//		return customer;
//	}
//	/**
//	 * @param customer the customer to set
//	 */
//	public void setCustomer(Customer customer) {
//		this.customer = customer;
//	}

	// ---------------------------------
	@Column(name="customer_id")
	private int custId;
	@NotBlank(message = "Please enter address line one!")
	@Column(name="address_line_one")
	private String addressLineOne;
	@Column(name="address_line_two")
	private String addressLineTwo;
	@NotBlank(message = "Please enter City!")
	private String city;
	@NotBlank(message = "Please enter State!")	
	private String state;
	@NotBlank(message = "Please enter country!")	
	private String country;
	@Column(name ="postal_code")
	@NotBlank(message = "Please enter Postal Code!")	
	private String postalCode;
	@Column(name="is_shipping")
	private boolean shipping;
	@Column(name="is_billing")
	private boolean billing;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddressLineOne() {
		return addressLineOne;
	}
	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}
	public String getAddressLineTwo() {
		return addressLineTwo;
	}
	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public boolean isBilling() {
		return billing;
	}
	public void setBilling(boolean billing) {
		this.billing = billing;
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", addressLineOne=" + addressLineOne + ", addressLineTwo=" + addressLineTwo
				+ ", city=" + city + ", state=" + state + ", country=" + country + ", postalCode=" + postalCode
				+ ", billing=" + billing + "]";
	}
		
	public boolean isShipping() {
		return shipping;
	}
	public void setShipping(boolean shipping) {
		this.shipping = shipping;
	}
	/**
	 * @return the custId
	 */
	public int getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(int custId) {
		this.custId = custId;
	}
	
	
}
