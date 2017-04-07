package com.aegon.webservice.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.aegon.webservice.customer.api.CustomerService;
import com.aegon.webservice.customer.dao.CustomerRepository;
import com.aegon.webservice.customer.model.Customer;

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
