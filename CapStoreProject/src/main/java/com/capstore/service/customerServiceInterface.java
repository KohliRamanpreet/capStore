package com.capstore.service;

import org.springframework.stereotype.Service;
import com.capstore.model.CustomerDetails;

@Service
public interface CustomerServiceInterface {
	CustomerDetails findCustomerByEmailIgnoreCase(String email);

	CustomerDetails findCustomerById(int id);

}
