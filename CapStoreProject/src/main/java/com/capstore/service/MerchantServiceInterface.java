package com.capstore.service;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.capstore.model.MerchantDetails;

@Service
public interface MerchantServiceInterface {
	MerchantDetails findMerchantByEmailIgnoreCase(String email);
	MerchantDetails findMerchantById(int id);
	ResponseEntity<?> registerMerchant(MerchantDetails md) throws Exception;

}