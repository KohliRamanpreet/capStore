package com.capstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.capstore.model.ConfirmationToken;


public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {
	ConfirmationToken findByConfirmationToken(String confirmationToken);
	
	

}
