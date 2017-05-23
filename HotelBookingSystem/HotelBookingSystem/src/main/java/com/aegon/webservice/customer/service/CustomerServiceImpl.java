package com.aegon.webservice.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aegon.webservice.customer.api.CustomerService;
import com.aegon.webservice.customer.model.Customer;
import com.aegon.webservice.customer.repository.CustomerRepository;

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
