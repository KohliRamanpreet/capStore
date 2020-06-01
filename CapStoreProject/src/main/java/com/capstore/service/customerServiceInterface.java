package com.capstore.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.capstore.model.CustomerDetails;

@Service
public interface CustomerServiceInterface {
	CustomerDetails findCustomerByEmailIgnoreCase(String email);

	CustomerDetails findCustomerById(int id);
	ResponseEntity<?> loginCustomer(String email,String password) throws Exception;
	ResponseEntity<?> registerCustomer(CustomerDetails cd) throws Exception;
	ResponseEntity<?> forgotPassword(String email) throws Exception;

}
