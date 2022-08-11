package com.cg.service;

import com.cg.model.Customer;
import com.cg.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
