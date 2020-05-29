package com.capstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capstore.model.CustomerDetails;
import com.capstore.repository.CustomerRepository;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
@Service
public class customerService implements customerServiceInterface {
 @Autowired
 CustomerRepository customerRepo;
 @Autowired
	@PersistenceContext
	EntityManager entityManager;
	@Override
	public CustomerDetails findCustomerByEmailIgnoreCase(String email)
	{   CustomerDetails cd=null;
	try {
	TypedQuery<CustomerDetails> q2 = entityManager
			.createQuery("SELECT cd from CustomerDetails cd where email='"+email+"'",CustomerDetails.class);
	cd = (CustomerDetails)q2.getSingleResult();
	}
	catch(NoResultException nre) { }
	
	return cd;
	}
	
	public void saveCustomer(CustomerDetails cd) {
		entityManager.persist(cd);
		entityManager.flush();
	}
	
	@Override
	public CustomerDetails findCustomerById(int id) {
		
		return customerRepo.findById(id).get();
	}

}
