package com.aegon.customer.dao;

import org.springframework.data.repository.Repository;

import com.aegon.customer.model.Customer;

public interface CustomerRepository extends Repository<Customer, Long>{
	   Customer findByCustomerId( long customerId );
}
