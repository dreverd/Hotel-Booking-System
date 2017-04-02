package com.aegon.customer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aegon.customer.api.CustomerService;
import com.aegon.customer.dao.CustomerRepository;
import com.aegon.customer.model.Customer;

@Component("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer getCustomer(long customerId) {
        return customerRepository.findByCustomerId(customerId);
    }
}
