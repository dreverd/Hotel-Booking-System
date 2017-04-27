package com.aegon.webservice.customer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="customer")
public class Customer {

    @Id @GeneratedValue
    private long customerId;

    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;

    public Customer() {}

    public Customer(long customerId, String firstName, String lastName) {
    	this.customerId = customerId;
    	this.firstName = firstName;
    	this.lastName = lastName;
	}

	/**
	 * @return the customerId
	 */
	public long getCustomerId() {
		return customerId;
	}


	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}


	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}


	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	@Override
    public String toString() {
    	return getCustomerId() + "," + getFirstName() + "," + getLastName();
    }
}
