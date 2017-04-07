package com.aegon.webservice.customer.dao;

import org.springframework.data.repository.Repository;

import com.aegon.webservice.customer.model.Customer;

public interface CustomerRepository extends Repository<Customer, Long>{
	   Customer findByCustomerId( long customerId );
}
